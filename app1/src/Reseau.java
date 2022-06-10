package src;


import java.util.ArrayList;


public class Reseau 
{
    
    private ArrayList<Tuyau> lstTuyau;
    private ArrayList<Cuve>  lstCuve;

    private int nbCuve;

    // Constructeur
    public Reseau( int nbCuve )
    {
        this.lstTuyau = new ArrayList<Tuyau>();
        this.lstCuve  = new ArrayList<Cuve>();

    }

    // Accesseurs
    public Cuve getCuve( char indice ) 
    {
        return this.lstCuve.get( (int) (indice - 'A') );
    }

    public Tuyau getTuyau( int indice )
    {
        return this.lstTuyau.get( indice );
    }

    public boolean ajouterTuyau( int section, Cuve cuve1, Cuve cuve2 )
    {
        if ( tuyauExiste(section, cuve1, cuve2) )
            return false;
        this.lstTuyau.add( new Tuyau(section, cuve1, cuve2) );
        return true;
    }

    public boolean ajouterCuve( int capacite )
    {
        this.lstCuve.add( Cuve.creer(capacite) );
    }


    public boolean tuyauExiste( int section, Cuve cuve1, Cuve cuve2 )
    {
        boolean tuyauExiste = false;
        for ( Tuyau t:this.lstTuyau )
        {
            if ( t.getSection() == section &&
                 t.estRelie  (cuve1)       &&
                 t.estRelie  (cuve2)          ) { tuyauExiste = true; }
        }

        return tuyauExiste;
    }

}
