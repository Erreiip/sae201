import iut.algo.*;

public class Reseau 
{
    public Reseau()
    {
        
    }

    public static void main ( String args[])
    {
        int     nombresCuves;
        int[]   capaCuves;
        Tuyau[] tuyaux;

        int     nbTuyaux;
        

        System.out.println("Combien de cubes voulez-vous ;");
        nombresCuves = Clavier.lire_int();

        System.out.println("Capacité de chaques cuves :");
        for ( int cpt = 0; cpt < nombresCuves; cpt++ )
        {
            capaCuves[cpt] = Clavier.lire_double();
            System.out.println("Encore que " + (nombresCuves - cpt)  + " :");
        }

        System.out.println("Nombre de tuyaux :");
        nbTuyaux = Clavier.lire_int();

        for ( int cpt = 0; cpt < nbTuyaux; cpt++ )
        {
            tuyaux[cpt] = new Tuyau (Clavier.lire_int());
            System.out.println("Encore " + (nombresCuves - cpt) + " :");
        }

    }   

}
