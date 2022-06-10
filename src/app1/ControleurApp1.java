package src.app1;

import iut.algo.Clavier;
import java.util.HashMap;

public class ControleurApp1
{

    private static boolean debug = false;
    private        Reseau  res;


    public ControleurApp1()
    {
        this.res = new Reseau();
    }

    public boolean ajouterTuyau( int section, Cuve cuve1, Cuve cuve2 )
    {
        return res.ajouterTuyau(Tuyau.creer(section, cuve1, cuve2));
    }


    public boolean ajouterCuve( int capacite )
    {
        return res.ajouterCuve(Cuve.creer(capacite));
    }


    private static void lancerModeDebug() {
        ControleurApp1.debug = true;
        System.out.println("Mode debug activé");
    }


    public void sortieFichierTexte ()
    {

    }

    

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("debug")){
            lancerModeDebug();
            return;
        }

        int            nbCuves;            
        int            capaciteMaximal;
        double         capaciteInitiale;
        Cuve           cuveEnCreation;
        ControleurApp1 controleur = new ControleurApp1();

        /*----------------------------*/
        /**Demander le nombre de cuves*/
        /*----------------------------*/

        System.out.println("Veuillez entrez le nombre de cuves :");
        do
        {
            nbCuves = Clavier.lire_int();
            if (nbCuves >= 26) System.out.println("Le nombre de cuves maximum a été atteint.");
        } while (nbCuves >= 26);

        /**Creations des cuves */
        /*---------------------*/
        for (int cpt=0; cpt < nbCuves; cpt ++)
        {
            /**Renseigner capacite de cuve en creation */
            do {
            System.out.print("Entrez la capacité maximale de la cuve "+ (char)('A'+cpt)+ " (entre 200 et 2000) : ");
            capaciteMaximal = Clavier.lire_int();

            cuveEnCreation = Cuve.creer(capaciteMaximal);
            if (cuveEnCreation == null) System.out.println("Invalide");
            } while (cuveEnCreation == null);
            
            System.out.println("La cuve " + cuveEnCreation.getIdentifiant() + " a été créée avec succès.");

            controleur.res.ajouterCuve(cuveEnCreation);
        }

        /*Creation des tuyaux*/
        char  resCreerTuyau;
        int   section;
        char  idCuveA, idCuveB;
        Cuve  cuve1  , cuve2;
        Tuyau tuyau;
        
        do
        {
            System.out.println ("Voulez-vous creer un tuyau (O/N) ?");    
            resCreerTuyau = Clavier.lire_char();
            if (resCreerTuyau == 'O')
            {
                do {
                    System.out.print ("\nSection ?");
                    section = Clavier.lire_int();

                    System.out.print ("\nIDCuveA ?");
                    idCuveA = Clavier.lire_char();
                    
                    System.out.print ("\nIDCuveB ?");
                    idCuveB = Clavier.lire_char();
                    
                    cuve1   = controleur.res.getCuve(idCuveA);
                    cuve2   = controleur.res.getCuve(idCuveB);
                    tuyau   = Tuyau.creer(section, cuve1, cuve2);
                    
                    if (tuyau == null) System.out.println ("Invalide");
                    
                } while (tuyau == null);
                controleur.res.ajouterTuyau(tuyau);
            }
            else break;
        
            System.out.println ("Voulez-vous continuer de creer un tuyau (O/N) ?");    
            
        } while (resCreerTuyau == 'O');
        System.out.println ("Reseau fini");
    }

}