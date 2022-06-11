package src.app1;

import java.util.ArrayList;

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

    public Cuve getCuve(int index)
    {
        for(Cuve c : this.cuves)
        {
            if(c.getIdentifiant() == index + 'A')
            {
                return c;
            }
        }
        return null;
    }

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

    public Tuyau getTuyau(int indice)
    {
        try
        {
            return tuyaux.get(indice);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    public boolean ajouterCuve(Cuve cuve)
    {
        if (cuve == null) return false;

        for (Cuve c : this.cuves)
            if (cuve.getIdentifiant() == c.getIdentifiant() ) return false;
        
        return this.cuves.add( cuve );
    }
    public boolean ajouterCuve (int capacite)
    {
        //Verification si cuve est valide
        Cuve cuve = Cuve.creer(capacite);
        if (cuve ==null) return false;
        
        //Verification si cuve existe dans la liste
        for (Cuve c : this.cuves)
            if (cuve.getIdentifiant() == c.getIdentifiant() ) return false;
        
        //Ajouter
        this.cuves.add (cuve);
        return true;
    }


    public boolean ajouterTuyau(Tuyau tuyau)
    {
        if(tuyau == null) return false;
        if(this.aUnTuyau(tuyau.getCuve1(), tuyau.getCuve2()))
        {
            return false;
        }
        return false;
    }
    
    public boolean ajouterTuyau(int section, char idCuve1, char idCuve2)
    {
        //Verification si idCuve1 et idCuve2 exist dans la liste
        Cuve cuve1,cuve2;
        cuve1 = cuve2 = null;
        for (Cuve c : this.cuves)
        {
            if (idCuve1 == c.getIdentifiant()) cuve1 = c;
            if (idCuve2 == c.getIdentifiant()) cuve2 = c;
        }
        if (cuve1 == null || cuve2 == null) return false;

        //Verification si cuve1 et cuve2 sont deja lié
        if (this.aUnTuyau(cuve1, cuve2) )   return false;

        //Creer un tuyau et verfier si possible
        Tuyau tuyau = Tuyau.creer(section, cuve1, cuve2);
        if (tuyau ==null) return false;

        //ajouter le tuyau
        this.tuyaux.add(tuyau);
        return true;
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

    public boolean aUnTuyau(Cuve cuve1, Cuve cuve2)
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
