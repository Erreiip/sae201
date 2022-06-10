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
                        this.tabCuve[lig] = Cuve.creer(0); 
                    
                    if ( this.tabCuve[col] == null )
                        this.tabCuve[col] = Cuve.creer(0); 

                    if ( !tuyauExiste( matriceCout[lig][col], this.tabCuve[lig], this.tabCuve[col] ) )
                        tabTuyau[nbTuyaux] = new Tuyau ( matriceCout[lig][col], this.tabCuve[lig], this.tabCuve[col] );
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
        
        this.nbCapaCuve = 0;
    }

    public boolean tuyauExiste ( int section, Cuve cuve1, Cuve cuve2 )
    {
        boolean tuyauExiste = false;
        for ( Tuyau t:this.tabTuyau )
        {
            if (  t.getSection() == section                             &&
                 (t.getCuve(0) == cuve1 || t.getCuve(0) == cuve2 ) &&
                 (t.getCuve(1) == cuve1 || t.getCuve(1) == cuve2 )    ) { tuyauExiste = true; }
        }

        return tuyauExiste;
    }

}
