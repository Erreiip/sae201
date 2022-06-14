package src.common.reseau.format;

import src.common.Reseau;
import src.common.Tuyau;

import java.util.ArrayList;

public class ReseauFormatListeAdjacence implements ReseauFormat
{
    private static final String DELIMITEUR = "\n" + "-".repeat(5) + "\n";

    public record ListeAdjacente(char[][] cuveIds, int[] sections)
    {
    }

    public void ajouterTuyaux(String s, Reseau r)
    {
        this.ajouterTuyaux(this.fromString(s), r);
    }

    public String toString(Reseau r)
    {
        return this.toString(this.fromReseau(r));
    }

    public void ajouterTuyaux(ListeAdjacente liste, Reseau reseau)
    {
        for (int i = 0; i < liste.cuveIds().length; i++)
        {
            reseau.creerTuyau(
                    liste.sections()[i],
                    liste.cuveIds()[i][0],
                    liste.cuveIds()[i][1]
            );
        }
    }

    public ListeAdjacente fromReseau(Reseau r)
    {
        ArrayList<Tuyau> tuyaux   = r.getTuyaux();
        char[][]         cuveIds  = new char[tuyaux.size()][2];
        int[]            sections = new int[tuyaux.size()];

        for (int i = 0; i < tuyaux.size(); i++)
        {
            cuveIds[i][0] = tuyaux.get(i).getCuve1().getIdentifiant();
            cuveIds[i][1] = tuyaux.get(i).getCuve2().getIdentifiant();
            sections[i] = tuyaux.get(i).getSection();
        }

        return new ListeAdjacente(cuveIds, sections);
    }

    public ListeAdjacente fromString(String s)
    {
        String[] parties = s.split(ReseauFormatListeAdjacence.DELIMITEUR);
        if (parties.length != 2)
            throw new IllegalArgumentException(
                    "le format de la liste d'adjacence n'est pas correct : " +
                            parties.length + " parties trouvées");
        String[] lignesCuveIds  = parties[0].split("\n");
        String[] lignesSections = parties[1].split("\n");

        char[][] cuveIds  = new char[lignesCuveIds.length][2];
        int[]    sections = new int[lignesSections.length];

        for (int i = 0; i < lignesCuveIds.length; i++)
        {
            String lig = lignesCuveIds[i];
            if (!lig.matches("[A-Z]{2}"))
            {
                throw new IllegalArgumentException("la ligne \"" + lig + "\" n'est pas valide");
            } else
            {
                cuveIds[i][0] = lig.charAt(0);
                cuveIds[i][1] = lig.charAt(1);
            }
        }

        for (int i = 0; i < lignesSections.length; i++)
        {
            String lig = lignesSections[i];
            sections[i] = Integer.parseInt(lig);
        }

        return new ListeAdjacente(cuveIds, sections);
    }

    public String toString(ListeAdjacente liste)
    {
        StringBuilder sb = new StringBuilder();
        for (char[] lig : liste.cuveIds()) sb.append(lig[0]).append(lig[1]).append("\n");
        sb.setLength(sb.length() - 1); // retirer le dernier \n
        sb.append(ReseauFormatListeAdjacence.DELIMITEUR);
        for (int i : liste.sections()) sb.append(i).append("\n");
        sb.setLength(sb.length() - 1); // retirer le dernier \n
        return sb.toString();
    }
}
