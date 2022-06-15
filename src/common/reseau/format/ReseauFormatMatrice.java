package src.common.reseau.format;

import src.common.reseau.element.Cuve;
import src.common.reseau.Reseau;
import src.common.reseau.element.Tuyau;

import java.util.Arrays;

class ReseauFormatMatrice implements ReseauFormat
{
    private static final char SANS_CONNECTION_CHAR = 'X';
    private static final int  SANS_CONNECTION_INT  = -1;

    private final boolean optimise;

    public ReseauFormatMatrice(boolean optimise)
    {
        this.optimise = optimise;
    }

    public void ajouterTuyaux(String s, Reseau r)
    {
        this.ajouterTuyaux(this.fromString(s), r);
    }

    public String construireString(Reseau r)
    {
        return this.toString(this.fromReseau(r));
    }

    public void ajouterTuyaux(int[][] matrice, Reseau reseau)
    {
        for (int lig = 0; lig < matrice.length; lig++)
        {
            for (int col = 0; col < matrice[lig].length; col++)
            {
                Cuve cuve1 = reseau.getCuve(this.optimise ? lig + 1 : lig);
                Cuve cuve2 = reseau.getCuve(col);

                int i = matrice[lig][col];
                if (i >= Tuyau.SECTION_MIN && i <= Tuyau.SECTION_MAX)
                {
                    if (!reseau.sontRelies(cuve1, cuve2))
                    {
                        // on inverse le sens uniquement pour la consistance
                        if (this.optimise) reseau.creerTuyau(i, cuve2, cuve1);
                        else reseau.creerTuyau(i, cuve1, cuve2);
                    }
                }
            }
        }
    }

    public int[][] fromReseau(Reseau r)
    {
        int[][] matrice;
        int     nbCuves = r.getCuves().size();
        matrice = new int[this.optimise ? nbCuves - 1 : nbCuves][];
        for (int i = 0; i < matrice.length; i++)
        {
            matrice[i] = new int[this.optimise ? i + 1 : nbCuves];
            Arrays.fill(matrice[i], ReseauFormatMatrice.SANS_CONNECTION_INT);
        }

        for (Cuve cuve1 : r.getCuves())
        {
            for (Cuve cuve2 : r.getCuves())
            {
                if (cuve1 == cuve2) break;
                int lig = cuve1.getIdentifiant() - 'A';
                int col = cuve2.getIdentifiant() - 'A';
                if (this.optimise) lig--;
                if (matrice[lig].length == 0) continue;
                if (r.sontRelies(cuve1, cuve2))
                {
                    Tuyau tuyau = r.getTuyau(cuve1, cuve2);
                    matrice[lig][col] = tuyau.getSection();
                } else
                {
                    matrice[lig][col] = ReseauFormatMatrice.SANS_CONNECTION_INT;
                }
                if (!this.optimise) matrice[col][lig] = matrice[lig][col];
            }
        }

        return matrice;
    }

    public int[][] fromString(String s)
    {
        String[] lignes  = s.split("\n");
        int[][]  matrice = new int[lignes.length][];

        for (int lig = 0; lig < lignes.length; lig++)
        {
            String[] valeurs = lignes[lig].split(" ");
            matrice[lig] = new int[valeurs.length];

            if (this.optimise)
            {
                if (lig > valeurs.length)
                {
                    throw new IllegalArgumentException("La matrice n'est pas optimisée." +
                            "La ligne " + lig + " est trop longue");
                }
            }

            for (int col = 0; col < valeurs.length; col++)
            {
                int i;
                if (valeurs[col].length() == 1 && valeurs[col].charAt(0) == ReseauFormatMatrice.SANS_CONNECTION_CHAR)
                {
                    i = ReseauFormatMatrice.SANS_CONNECTION_INT;
                } else
                {
                    i = Integer.parseInt(valeurs[col]);
                    if (i < Tuyau.SECTION_MIN || i > Tuyau.SECTION_MAX)
                    {
                        throw new IllegalArgumentException(
                                "La matrice doit contenir des valeurs entre "
                                        + Tuyau.SECTION_MIN + " et " + Tuyau.SECTION_MAX +
                                        ". Valeur " + i + " non autorisée.");
                    }
                }
                matrice[lig][col] = i;
            }
        }
        return matrice;
    }

    public String toString(int[][] matrice)
    {
        StringBuilder sb = new StringBuilder();
        for (int[] lig : matrice)
        {
            for (int col : lig)
            {
                if (col == ReseauFormatMatrice.SANS_CONNECTION_INT)
                    sb.append(ReseauFormatMatrice.SANS_CONNECTION_CHAR);
                else
                    sb.append(col);
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1); // retirer le dernier \n
        return sb.toString();
    }
}
