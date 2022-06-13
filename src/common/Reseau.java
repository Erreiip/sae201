package src.common;

import java.util.ArrayList;

import src.common.util.Transfert;

/**
 * Un réseau représente un espace dans lequel se trouvent des {@link Cuve}s et des {@link Tuyau}.<br>
 * La création d'éléments doit se faire par l'utilisation de cette classe.
 */
public class Reseau 
{
    private final ArrayList<Tuyau> tuyaux;
    private final ArrayList<Cuve> cuves;

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
        return this.getCuve((char)('A' + index));
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
     * Retourne vrai si les deux listCuves sont reliées par un tuyau dans ce réseau.
     */
    public boolean sontRelies(Cuve cuve1, Cuve cuve2)
    {
        return getTuyau(cuve1, cuve2) != null;
    }

    public void transverser()
    {
        for ( Tuyau t : this.tuyaux )
        {
            Transfert iteTrans = t.transverser();
            if ( iteTrans != null )
            {
                iteTrans.getCuveDepart ().retirerContenu( iteTrans.getQuantite() );
                iteTrans.getCuveArrivee().ajouterContenu( iteTrans.getQuantite() );
            }
        }
    }


    public int[][] getMatriceCout()
    {
        int [][] matrice;
        int nbCuves = this.getCuves().size();
        matrice = new int[nbCuves][nbCuves];
        for (Tuyau tuyau : this.getTuyaux())
        {
            int lig,col;
            lig = tuyau.getCuve1().getIdentifiant() - 'A';
            col = tuyau.getCuve2().getIdentifiant() - 'A';
            matrice[lig][col] = tuyau.getSection() ;
            matrice[col][lig] = tuyau.getSection() ;
        }
        return matrice;
    }


    public String toStringMatriceCout()
    {
		String sRet = "";
        int [][] matrice;
        matrice = this.getMatriceCout();
        int nbCuves = this.getMatriceCout().length;

        for (int lig =0;lig < nbCuves; lig ++)
        {
            for (int col = 0; col < nbCuves; col ++)
            {
                sRet += String.format("%3d",matrice[lig][col]);
                /*default value of integer array is 0*/
            }
            sRet += "\n";
        }
        return sRet;           
    }


    public String toStringListAdjac()
    {
        String sRet = "List d'adjacence:\n";
        for (Tuyau t :this.tuyaux)
            sRet += t.getCuve1().getIdentifiant() + t.getCuve2().getIdentifiant() + "\n";
        
        return sRet;
    }


    public String toStringMatriceCoutOptimise()
    {
        String sRet = "";
        int[][] matrice = this.getMatriceCout();
        int etage = 1;
        
        for (int lig =0;lig < matrice.length; lig ++)
        {
            for (int col = 0; col < etage; col ++)
            {
                sRet += String.format("%3d",matrice[lig][col]);
                /*default value of integer array is 0*/
                etage ++;
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
