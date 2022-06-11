package src.app1;

import iut.algo.Clavier;
import src.app1.ihm.FrameApp1;

import javax.naming.ldap.Control;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
public class ControleurApp1
{

    private static boolean debug = false;
    private        Reseau  res;


    public ControleurApp1()
    {
        this.res = new Reseau();
    }

    public boolean ajouterTuyau (int section, Cuve cuve1, Cuve cuve2)
    {
        return res.ajouterTuyau(Tuyau.creer(section, cuve1, cuve2));
    }

    public boolean ajouterTuyau (int section, char idCuve1, char idCuve2)
    {
        return this.res.ajouterTuyau(section, idCuve1, idCuve2);
    }

    public boolean ajouterCuve ( int capacite )
    {
        return res.ajouterCuve(capacite);
    }

    public List<Cuve> getCuves() { return this.res.getCuves(); }
    public List<Tuyau> getTuyaux() { return this.res.getTuyaux(); }

    public void sortieFichierTexteMatriceCout ()
    {
        String sRet = this.res.getMatriceCout();
        try
		{
			PrintWriter pw = new PrintWriter( new FileOutputStream("sortie.txt") );

            pw.println (sRet);

			pw.close();
		}
		catch (Exception e){ e.printStackTrace(); }
        /*Test sur le console*/
        
        System.out.println(sRet);
    }

    

    public static void main(String[] args) 
    {
        ControleurApp1 controleur = new ControleurApp1();
        if(args.length > 0 && args[0].equalsIgnoreCase("gui")) {
            new FrameApp1(controleur);
            return;
        }

        int            nbCuves;
        int            capaciteMaximal;
        double         capaciteInitiale;
        boolean        valideCuve;

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
        char idCuveEnCreation = 'A';
        for (int cpt=0; cpt < nbCuves; cpt ++)
        {
            /**Renseigner capacite de cuve en creation */
            do {
            System.out.print("Entrez la capacité maximale de la cuve "+ idCuveEnCreation + " (entre 200 et 2000) :");
            capaciteMaximal = Clavier.lire_int();

            valideCuve = controleur.ajouterCuve(capaciteMaximal);
            
            if (!valideCuve) { System.out.println("Invalide"); continue ;}
            idCuveEnCreation ++;
            } while (!valideCuve);
            
            System.out.println("La cuve " + idCuveEnCreation + " a été créée avec succès.");
        }

        /*Creation des tuyaux*/
        char  resCreerTuyau;
        int   section;
        char  idCuve1, idCuve2;
        boolean valideTuyau;
        do
        {
            System.out.println ("Voulez-vous creer un tuyau (O/N) ?");    
            resCreerTuyau = Clavier.lire_char();
            if (resCreerTuyau == 'O')
            {
                do {
                    System.out.print ("\nSection ? ");
                    section = Clavier.lire_int();

                    System.out.print ("\nidCuve1 ? ");
                    idCuve1 = Clavier.lire_char();
                    
                    System.out.print ("\nidCuve2 ? ");
                    idCuve2 = Clavier.lire_char();
                    
                    valideTuyau = controleur.ajouterTuyau(section, idCuve1, idCuve2);
                    if (!valideTuyau) System.out.println ("Invalide");
                    
                } while (!valideTuyau);
            }
            else break;
        
            System.out.println ("Voulez-vous continuer de creer un tuyau (O/N) ?");    
            
        } while (resCreerTuyau == 'O');
        System.out.println ("Finir de Creer le Reseau ");
        controleur.sortieFichierTexteMatriceCout();
    }

}
