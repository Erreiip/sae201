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

    public Cuve getCuve(char identifiant)
    {
        for(Cuve c : this.cuves)
        {
            if( c.getIdentifiant() == identifiant )
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
        if(cuve == null) return false;
        return this.cuves.add( cuve );
    }

    public boolean ajouterTuyau(Tuyau tuyau)
    {
        if(tuyau == null) return false;
        if(getTuyau(tuyau.getCuve1(), tuyau.getCuve2()) != null)
        {
            return false;
        }
        return this.tuyaux.add(tuyau);
    }

    public Tuyau getTuyau(Cuve cuve1, Cuve cuve2) {
        for(Tuyau t : this.tuyaux)
        {
            if(t.estRelie(cuve1) && t.estRelie(cuve2))
            {
                return t;
            }
        }
        return null;
    }
}
