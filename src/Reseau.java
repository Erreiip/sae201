package src;


public class Reseau 
{
    
    private int      nbCuves;
    private Cuve[]   tabCuve;
    
    private Tuyau[]  tabTuyau;
    private int      nbTuyaux;


    // liste d’adjacence
    public Reseau( int[][] listeAdja, int[] tabCuves, int[] tabCouts )
    {
        this.tabCuve = new Cuve[listeAdja.length];
        this.nbCuves = this.tabCuve.length+1;

        for ( int lig=0 ; lig < listeAdja.length ; lig++ )
        {
            for ( int col=0 ; col < listeAdja[lig].length ; col++ )
            {
                if ( listeAdja[lig][col] != 0 )
                {
                    if ( !tuyauExiste( listeAdja[lig][col], this.tabCuve[lig], this.tabCuve[col] ) )
                        tabTuyau[nbTuyaux++] = new Tuyau ( listeAdja[lig][col], this.tabCuve[lig], this.tabCuve[col] );
                }
            }
        }
    }

    
    // matrice de cout (même constructeur pour matrice opti)
    public Reseau( int[][] matriceCout, int[] tabCuves )
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
