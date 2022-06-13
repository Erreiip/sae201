package src.common;

import java.util.ArrayList;
import java.util.Collections;

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

    public boolean supprimerCuve(int ligne) {
        if(ligne >= this.cuves.size())
            return false;

        Cuve cuve = this.cuves.get(ligne);
        this.tuyaux.removeIf(t -> t.estRelie(cuve));
        this.cuves.remove(ligne);

        return true;
    }

    public char getNextCuveId()
    {
        char c = 'A';
        while(this.getCuve(c) != null)
        {
            c++;
        }
        return c;
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
        ArrayList<Transfert> ensTransfert    = new ArrayList<>();
        ArrayList<Integer>   ensNbSectionSor = new ArrayList<>();
        ArrayList<Integer>   ensSommeSection = new ArrayList<>();
        
        for( Tuyau ty : this.tuyaux )
        {
            Transfert iteTrans = ty.transverser();
            if ( iteTrans != null ) ensTransfert.add(iteTrans);
        }

        Collections.sort(ensTransfert);


        for( Cuve c : this.cuves )
        {
            int sommeSection = 0;
            for( Transfert tr : ensTransfert )
            {
                if ( tr.getCuveDepart() == c )
                    sommeSection += tr.getQuantite();
            }
            ensSommeSection.add(sommeSection);
        }

        for( int cpt=0 ; cpt < ensTransfert.size() ; cpt++ )
        {
            Transfert iteTrans       = ensTransfert.get(cpt);
            double    contenuCuveDep = iteTrans.getCuveDepart ().getContenu();
            double    contenuCuveArr = iteTrans.getCuveArrivee().getContenu();

            double tMax = iteTrans.getQuantite();

            if ( contenuCuveDep - tMax < contenuCuveArr + tMax )
                tMax = contenuCuveDep - contenuCuveDep;

            if ( tMax < ensSommeSection.get(cpt) )
                tMax = contenuCuveDep - contenuCuveArr / ensNbSectionSor.get(cpt);
            
            

            if ( tMax > contenuCuveDep ) tMax = contenuCuveDep;
            
            if ( contenuCuveDep + tMax > iteTrans.getCuveDepart().getCapacite() )
                tMax = iteTrans.getCuveDepart().getCapacite() - contenuCuveDep;
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
		String sRet = "Matrice de couts\n";
        int [][] matrice;
        matrice = this.getMatriceCout();
        int nbCuves = this.getMatriceCout().length;

        for (int lig =0;lig < nbCuves; lig ++)
        {
            for (int col = 0; col < nbCuves; col ++)
            {
                sRet += String.format("%-3d",matrice[lig][col]);
                /*default value of integer array is 0*/
            }
            sRet += "\n";
        }
        sRet += "---\n";
        for (Cuve c : this.cuves)
            sRet += c.getCapacite() + "\n";

            
        return sRet;           
    }



    public String toStringListAdjac()
    {
        String sRet = "Liste d'adjacence\n";
        for (Cuve c : this.cuves)
        {
            sRet += String.format("%-3s", Character.toString (c.getIdentifiant() ));
            for (Tuyau t : this.tuyaux)
            {
                Cuve cuve1, cuve2;
                cuve1 = t.getCuve1();
                cuve2 = t.getCuve2();
                if (c == cuve1) sRet += String.format("%-3s", cuve2.getIdentifiant() );
                if (c == cuve2) sRet += String.format("%-3s", cuve1.getIdentifiant() );
            }
            sRet += "\n";
        }
        sRet += "---\n";

        for (Tuyau t :this.tuyaux)
            sRet += t.getSection() + "\n";
       
        sRet += "---\n";
        for (Cuve c : this.cuves)
            sRet += c.getCapacite() + "\n";            
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
