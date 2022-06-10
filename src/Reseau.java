package src;

import java.util.List;

public class Reseau 
{
    
    int      nbCuves;
    Cuve[]   tabCuve;

    int[]    capaCuves;
    int      nbCapaCuve;
    
    Tuyau[]  tabTuyau;
    int      nbTuyaux;

    
    // liste d’adjacence
    public Reseau( Tuyau[] listAdja )
    {

    }

    
    // matrice de cout (même constructeur pour matrice opti)
    public Reseau( int[][] matriceCout, int[] capaCuves )
    {
        this.tabCuve = new Cuve[matriceCout.length];
        this.nbCuves = this.tabCuve.length+1;

        for ( int lig=0 ; lig < matriceCout.length ; lig++ )
        {
            for ( int col=0 ; col < matriceCout[lig].length ; col++ )
            {
                if ( matriceCout[lig][col] != 0 )
                {
                    if ( !tuyauExiste( matriceCout[lig][col], this.tabCuve[lig], this.tabCuve[col] ) )
                        tabTuyau[nbTuyaux++] = new Tuyau ( matriceCout[lig][col], this.tabCuve[lig], this.tabCuve[col] );
                }
            }
        }
    }
    

    public void ajouterCapa ( int capacite )
    {
        this.capaCuves[nbCapaCuve++] = capacite;
    }


    public boolean tuyauExiste ( int section, Cuve cuve1, Cuve cuve2 )
    {
        boolean tuyauExiste = false;
        for ( Tuyau t:this.tabTuyau )
        {
            if ( t.getSection() == section &&
                 t.estRelie  (cuve1)       &&
                 t.estRelie  (cuve2)          ) { tuyauExiste = true; }
        }

        return tuyauExiste;
    }

}
