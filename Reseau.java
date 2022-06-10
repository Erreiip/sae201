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
    public Reseau( List<Tuyau> listAdja )
    {

    }

    
    // matrice de cout (même constructeur pour matrice opti)
    public Reseau( int[][] matriceCout )
    {
        this.tabCuve = new Cuve[matriceCout.length];

        for ( int lig=0 ; lig < matriceCout.length ; lig++ )
        {
            for ( int col=0 ; col < matriceCout[lig].length ; col++ )
            {
                if ( matriceCout[lig][col] != 0 )
                {
                    if ( this.tabCuve[lig] == null )
                        this.tabCuve[lig] = new Cuve ();
                    
                    if ( this.tabCuve[col] == null )
                        this.tabCuve[col] = new Cuve ();

                    tabTuyau[nbTuyaux] = new Tuyau ( matriceCout[lig][col], tabCuve[lig], tabCuve[col] );
                }
            }
        }
    }

    public void ajouterCapa ( int capacite )
    {
        this.nbCapaCuve++;
    }

    public void ajouterCapa ( int[] tabCapacite )
    {
        
        this.nbCapaCuve = 
    }

}
