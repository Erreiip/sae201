package src.app1;

import iut.algo.Clavier;
import src.app1.ihm.FrameApp1;
import src.common.Cuve;
import src.common.Reseau;
import src.common.Tuyau;
import src.common.reseau.fichier.FichierReseau;
import src.common.reseau.format.ReseauFormatType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ControleurApp1
{
    private static final boolean debug = false;

    private Reseau reseau;
    private FrameApp1 ihm;

    public ControleurApp1()
    {
        this.reseau = new Reseau();
    }

    public List<Cuve> getCuves()   { return this.reseau.getCuves(); }
    public List<Tuyau> getTuyaux() { return this.reseau.getTuyaux(); }

    public boolean creerTuyau(int section, Cuve cuve1, Cuve cuve2)
    {
        return reseau.creerTuyau(section, cuve1, cuve2) != null;
    }

    public boolean creerTuyau(int section, char idCuve1, char idCuve2)
    {
        Tuyau tuyau = reseau.creerTuyau(section, idCuve1, idCuve2);
        if( tuyau == null ) return false;

        if(this.ihm != null) this.ihm.majListeTuyaux();

        return true;
    }

    public boolean creerCuve(int capacite )
    {
        Cuve cuve = reseau.creerCuve(capacite);
        if ( cuve == null ) return false;

        if(ihm != null) this.ihm.majListeCuves();

        return true;
    }

    public boolean supprimerCuve()
    {
        if(this.ihm == null) return false;

        int ligne = this.ihm.getCuveActive();

        if(ligne == -1) return false;

        if(!this.reseau.supprimerCuve(ligne)) return false;

        this.ihm.majListeCuves();
        this.ihm.majListeTuyaux();
        return true;
    }

    public void setReseau(String absolutePath)
    {
        try {
            FichierReseau fichierReseau = FichierReseau.fromString(Files.readString(Path.of(absolutePath)));
            this.reseau = fichierReseau.getReseau();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sauvegarderReseau(ReseauFormatType reseauFormatType, String path) {
        try
        {
            Files.writeString(Path.of(path), reseauFormatType.getFormat().toString(this.reseau));
        } catch (Exception e){ e.printStackTrace(); }
    }

    public FrameApp1 getIhm() {
        return ihm;
    }

    public void setIhm(FrameApp1 ihm) {
        this.ihm = ihm;
    }

    public String toStringReseau(){
        return reseau.toString();
    }

 



    public static void main(String[] args)
    {
        ControleurApp1 controleur = new ControleurApp1();
        if(args.length > 0 && args[0].equalsIgnoreCase("gui")) {
            FrameApp1 ihm = new FrameApp1(controleur);
            controleur.setIhm(ihm);
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
            if (nbCuves > 26) System.out.println("Le nombre de cuves maximum a été atteint.");
        } while (nbCuves > 26);

        /**Creations des cuves */
        /*---------------------*/
        char idCuveEnCreation = 'A';
        for (int cpt=0; cpt < nbCuves; cpt ++)
        {
            /**Renseigner capacite de cuve en creation */
            do {
            System.out.print("Entrez la capacité maximale de la cuve "+ idCuveEnCreation + " (entre 200 et 2000) :");
            capaciteMaximal = Clavier.lire_int();

            valideCuve = false;

            try {
                valideCuve = controleur.creerCuve(capaciteMaximal);
            } catch (Exception e) {
                //TODO: handle exception
            }



            if (!valideCuve) { System.out.println("Invalide"); continue ;}
            } while (!valideCuve);

            System.out.println("La cuve " + idCuveEnCreation + " a été créée avec succès.");
            idCuveEnCreation ++;
        }


        if (debug){

            System.out.println(controleur.toStringReseau());

        }

        /*Creation des tuyaux*/
        char  resCreerTuyau;
        int   section;
        char  idCuve1, idCuve2;
        boolean valideTuyau;

        System.out.println ("Voulez-vous creer un tuyau (O/N) ?");

        do
        {

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

                    valideTuyau = false;

                    try {
                        valideTuyau = controleur.creerTuyau(section, idCuve1, idCuve2);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }

                    if (!valideTuyau) System.out.println ("Invalide");

                } while (!valideTuyau);
            }
            else break;

            System.out.println ("Voulez-vous continuer de creer un tuyau (O/N) ?");

        } while (resCreerTuyau == 'O');

        /*Sélection de la Matrice voulu*/

        int selectionFormat;

        do
        {
            System.out.println("Veuillez choisir le format de la structure de sortie :\n"+
            "[1]-Liste d'adjacence             \n"+
            "[2]-Matrice de cout               \n"+
            "[3]-Matrice de cout optimisée     \n"+
            "[Autres]-Quitter l'application    \n"+
            "Veuillez chosir entre 1 et 3 : ");
            selectionFormat = Clavier.lire_int();

            switch (selectionFormat) {
                case 1:

                    controleur.sauvegarderReseau(ReseauFormatType.LISTE_ADJA,"sortie.txt");
                    break;

                case 2:

                    controleur.sauvegarderReseau(ReseauFormatType.COUTS,"sortie.txt");
                    break;

                case 3:

                    controleur.sauvegarderReseau(ReseauFormatType.COUTS_OPTI,"sortie.txt");                    
                    break;
                default:
                    break;
            }
        } while (selectionFormat >=1 && selectionFormat <= 3); 
        /**selectionFormat doit etre 1 ou 2 ou 3 pour continuer */


    }
}
