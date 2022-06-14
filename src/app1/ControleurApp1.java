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

    private Reseau    reseau;
    private FrameApp1 ihm;

    public ControleurApp1()
    {
        this.reseau = new Reseau();
    }

    public List<Cuve> getCuves()
    {
        return this.reseau.getCuves();
    }

    public List<Tuyau> getTuyaux()
    {
        return this.reseau.getTuyaux();
    }

    public boolean creerTuyau(int section, Cuve cuve1, Cuve cuve2)
    {
        return reseau.creerTuyau(section, cuve1, cuve2) != null;
    }

    public boolean creerTuyau(int section, char idCuve1, char idCuve2)
    {
        Tuyau tuyau = reseau.creerTuyau(section, idCuve1, idCuve2);
        if (tuyau == null) return false;

        if (this.ihm != null) this.ihm.majListeTuyaux();

        return true;
    }

    public boolean creerCuve(int capacite)
    {
        Cuve cuve = reseau.creerCuve(capacite);
        if (cuve == null) return false;

        if (ihm != null) this.ihm.majListeCuves();

        return true;
    }

    public boolean supprimerCuve()
    {
        if (this.ihm == null) return false;

        int ligne = this.ihm.getCuveActive();

        if (ligne == -1) return false;

        if (!this.reseau.supprimerCuve(ligne)) return false;

        this.ihm.majListeCuves();
        this.ihm.majListeTuyaux();
        return true;
    }

    public void setReseau(String absolutePath)
    {
        try
        {
            FichierReseau fichierReseau = FichierReseau.fromString(Files.readString(Path.of(absolutePath)));
            this.reseau = fichierReseau.getReseau();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void sauvegarderReseau(ReseauFormatType reseauFormatType, String path)
    {
        try
        {
            FichierReseau fichierReseau = new FichierReseau(reseauFormatType, this.reseau);
            Files.writeString(Path.of(path), fichierReseau.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public FrameApp1 getIhm()
    {
        return ihm;
    }

    public void setIhm(FrameApp1 ihm)
    {
        this.ihm = ihm;
    }

    public String toStringReseau()
    {
        return reseau.toString();
    }

    public static void main(String[] args)
    {
        ControleurApp1 controleur = new ControleurApp1();
        if (args.length > 0 && args[0].equalsIgnoreCase("gui"))
        {
            FrameApp1 ihm = new FrameApp1(controleur);
            controleur.setIhm(ihm);
            return;
        }

        int     nbCuves;
        int     capaciteMaximal;
        boolean valideCuve;

        /*----------------------------*/
        /**Demander le nombre de cuves*/
        /*----------------------------*/
        do
        {
            System.out.print("Veuillez entrez le nombre de cuves entre 1 et 26: ");
            nbCuves = Clavier.lire_int();
        } while (nbCuves > 26);

        /**Creations des cuves */
        /*---------------------*/
        char idCuveEnCreation = 'A';
        for (int cpt = 0; cpt < nbCuves; cpt++)
        {

            /* Renseigner capacite de cuve en creation */
            do
            {
                System.out.print("Entrez la capacité maximale de la cuve " + idCuveEnCreation + " (entre 200 et 2000) : ");
                capaciteMaximal = Clavier.lire_int();

                valideCuve = false;

                try
                {
                    valideCuve = controleur.creerCuve(capaciteMaximal);
                } catch (Exception e)
                {
                    //TODO: handle exception
                    System.err.println("Erreur: " + e.getMessage());
                }

            } while (!valideCuve);

            System.out.println("La cuve " + idCuveEnCreation + " a été créée avec succès.");
            idCuveEnCreation++;
        }

        /*Creation des tuyaux*/
        char    resCreerTuyau;
        int     section;
        char    idCuve1, idCuve2;
        boolean valideTuyau;

        do
        {
            System.out.println("Voulez-vous créer un tuyau ? (O/N)");
            resCreerTuyau = Clavier.lire_char();
            if(Character.toUpperCase(resCreerTuyau) == 'N')
                break;

            valideTuyau = false;
            do
            {
                System.out.println("Entrez la section du tuyau :");
                section = Clavier.lire_int();

                System.out.println("Entrez l'identifiant de la première cuve :");
                idCuve1 = Clavier.lire_char();

                System.out.println("Entrez l'identifiant de la deuxième cuve :");
                idCuve2 = Clavier.lire_char();

                try
                {
                    valideTuyau = controleur.creerTuyau(section, idCuve1, idCuve2);
                } catch (Exception e)
                {
                    System.err.println("Erreur: " + e.getMessage());
                }

            } while (!valideTuyau);

        } while (resCreerTuyau == 'O');

        /*Sélection de la Matrice voulu*/
        int selectionFormat;

        do
        {

            String question = "Veuillez choisir le format de la structure de sortie:\n";
            for (ReseauFormatType value : ReseauFormatType.values())
            {
                question += "[" + (value.ordinal() + 1) + "] " + value.getNom() + "\n";
            }
            question += "[" + ReseauFormatType.values().length + "] Quitter\n";
            question += "Veuillez choisir entre 1 et " + (ReseauFormatType.values().length + 1) + " :";

            System.out.print(question);
            selectionFormat = Clavier.lire_int();

            switch (selectionFormat)
            {
                case 1:
                case 2:
                case 3:
                    ReseauFormatType reseauFormatType = ReseauFormatType.values()[selectionFormat - 1];
                    controleur.sauvegarderReseau(reseauFormatType, "reseau.data");
                    break;

                default:
                    break;
            }
        } while (selectionFormat < 1 || selectionFormat > ReseauFormatType.values().length + 1);
    }
}
