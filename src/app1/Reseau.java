package src.app1;


import java.util.ArrayList;


public class Reseau 
{
    
    private final ArrayList<Tuyau> lstTuyau;
    private final ArrayList<Cuve>  lstCuve;

    private int nbCuve;

    // Constructeur
    public Reseau(int nbCuve)
    {
        this.lstTuyau = new ArrayList<>();
        this.lstCuve  = new ArrayList<>();
    }

    // Accesseurs
    public Cuve getCuve( char identifiant )
    {
        for( Cuve c : this.lstCuve )
        {
            if( c.getIdentifiant() == identifiant )
            {
                return c;
            }
        }
        return null;
    }

    public Tuyau getTuyau( int indice )
    {
        return this.lstTuyau.get( indice );
    }

    public boolean ajouterCuve(Cuve cuve)
    {
        if(cuve == null) return false;
        return this.lstCuve.add( cuve );
    }

    public boolean ajouterTuyau(Tuyau tuyau)
    {
        if(tuyau == null) return false;
        if(sontRelies(tuyau.getCuve1(), tuyau.getCuve2()))
        {
            return false;
        }
        return this.lstTuyau.add(tuyau);
    }

    public boolean sontRelies(Cuve cuve1, Cuve cuve2 )
    {
        for(Tuyau t : this.lstTuyau)
        {
            if(t.estRelie(cuve1) && t.estRelie(cuve2))
            {
                return true;
            }
        }
        return false;
    }


}
