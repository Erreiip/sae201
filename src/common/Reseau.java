package src.common;

import java.util.ArrayList;

/**
 * Un réseau représente un espace dans lequel se trouvent des {@link Cuve}s et des {@link Tuyau}.<br>
 * La création d'éléments doit se faire par l'utilisation de cette classe.
 */
public class Reseau 
{
    private final ArrayList<Tuyau> tuyaux;
    private final ArrayList<Cuve>  cuves;

    // Constructeur
    public Reseau()
    {
        this.tuyaux = new ArrayList<>();
        this.cuves  = new ArrayList<>();
    }

    // Accesseurs
    public ArrayList<Cuve>  getCuves()  { return cuves; }
    public ArrayList<Tuyau> getTuyaux() { return tuyaux; }

    public Cuve getCuve(char identifiant)
    {
        for (Cuve c : this.cuves)
        {
            if ( c.getIdentifiant() == identifiant )
            {
                return c;
            }
        }
        return null;
    }

    public Cuve getCuve(int index)
    {
        return this.getCuve(index - 'A');
    }

    public Tuyau getTuyau(Cuve cuve1, Cuve cuve2)
    {
        for(Tuyau t : this.tuyaux)
        {
            if (t.estRelie(cuve1) && t.estRelie(cuve2))
            {
                return t;
            }
        }
        return null;
    }

    // Fabriques

    /**
     * Fabrique une cuve.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Cuve creerCuve(int capacite)
    {
        return Cuve.creer(this, capacite);
    }

    /**
     * Fabrique un tuyau.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Tuyau creerTuyau(int section, Cuve cuve1, Cuve cuve2)
    {
        return Tuyau.creer(this, section, cuve1, cuve2);
    }

    /**
     * Fabrique un tuyau.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Tuyau creerTuyau(int section, char idCuve1, char idCuve2)
    {
        return this.creerTuyau(section, this.getCuve(idCuve1), this.getCuve(idCuve2));
    }

    /**
     * Retourne vrai si les deux cuves sont reliées par un tuyau dans ce réseau.
     */
    public boolean sontRelies(Cuve cuve1, Cuve cuve2)
    {
        return getTuyau(cuve1, cuve2) != null;
    }

    public String getMatriceCout()
    {
		String sRet = "";
        String [][] matrice;
        int nbCuves = this.getCuves().size();
        matrice = new String[nbCuves][nbCuves];
        for (Tuyau tuyau : this.getTuyaux())
        {
            int lig,col;
            lig = tuyau.getCuve1().getIdentifiant() - 'A';
            col = tuyau.getCuve2().getIdentifiant() - 'A';
            matrice[lig][col] = tuyau.getSection() + "" ;
            matrice[col][lig] = tuyau.getSection() + "";
        }
        for (int lig =0;lig < nbCuves; lig ++)
        {
            for (int col = 0; col < nbCuves; col ++)
            {
                sRet += matrice[lig][col] != null ? String.format("%3s",matrice[lig][col]):"  0";
            }
            
            sRet += "\n";
        }
        return sRet;           
    }
    @Override
    public String toString() {
        return "Reseau{" +
                "tuyaux=" + tuyaux +
                ", cuves=" + cuves +
                '}';
    }
}
