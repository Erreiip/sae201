package src.app1;

import iut.algo.Clavier;
import src.app1.ihm.FrameApp1;
import src.common.reseau.element.Cuve;
import src.common.reseau.Reseau;
import src.common.reseau.element.Tuyau;
import src.common.reseau.fichier.FichierReseau;
import src.common.reseau.format.ReseauFormatType;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Classe principale de l'application.
 */
public class ControleurApp1
{
    private       Reseau    reseau;
    private       FrameApp1 ihm;
    private final int nbCuvesMax;

    public ControleurApp1(int nbCuvesMax)
    {
        this.reseau     = new Reseau();
        this.nbCuvesMax = nbCuvesMax;
    }

    public static void main(String[] args)
    {
        ControleurApp1 controleur;
        if(args.length > 0 && args[0].equalsIgnoreCase("gui"))
        {
            int nbCuves = -1;
            do
            {
                String s = JOptionPane.showInputDialog("Entrez le nombre maximal de cuves:");
                if (s == null) return;

                try
                {
                    nbCuves = Integer.parseInt(s);
                    if (nbCuves < 2)
                    {
                        nbCuves = -1;
                        throw new Exception("Le nombre de cuves doit être positif et supérieur ou égal à 2");
                    }
                }
                catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, "Vous devez rentrer un entier correct", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
            while (nbCuves == -1);

            controleur    = new ControleurApp1(nbCuves);
            FrameApp1 ihm = new FrameApp1(controleur);
            controleur.setIhm(ihm);
            return;
        }


        /*---------MODE CUI----------- */
        /*---------------------------- */
        int     nbCuves;
        int     capaciteMaximal;
        boolean valideCuve;

        /*----------------------------*/
        /* Demander le nombre de cuves*/
        /*----------------------------*/
        do
        {
            System.out.print("Veuillez entrez le nombre de cuves entre 1 et 26: ");
            nbCuves = Clavier.lire_int();
        }
        while (nbCuves > 26);

        controleur = new ControleurApp1(nbCuves);

        /*---------------------*/
        /* Creations des cuves */
        /*---------------------*/
        char idCuveEnCreation = 'A';
        for (int cpt = 0; cpt < nbCuves; cpt++)
        {

            /* Renseigner capacité de cuve en creation */
            do
            {
                System.out.print("Entrez la capacité maximale de la cuve " + idCuveEnCreation + " (entre 200 et 1000) : ");
                capaciteMaximal = Clavier.lire_int();

                valideCuve = false;

                try
                {
                    valideCuve = controleur.creerCuve(capaciteMaximal);
                } catch (Exception e)
                {
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
            resCreerTuyau = Character.toUpperCase(Clavier.lire_char());
            if(resCreerTuyau == 'N') break;

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

        /* Sélection de la Matrice voulue */
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

            switch(selectionFormat)
            {
                case 1, 2, 3 ->
                {
                    ReseauFormatType reseauFormatType = ReseauFormatType.values()[selectionFormat - 1];
                    controleur.sauvegarderReseau(reseauFormatType, "reseau.data");
                    System.out.println("Le fichier a été enregistré dans reseau.data avec le type: " + reseauFormatType.getNom());
                }
                default -> {}
            }
        }
        while (selectionFormat < 1 || selectionFormat > ReseauFormatType.values().length + 1);
    }

    public List<Cuve>  getCuves()      { return this.reseau.getCuves(); }
    public List<Tuyau> getTuyaux()     { return this.reseau.getTuyaux(); }
    public int         getNbCuvesMax() { return nbCuvesMax;}
    public FrameApp1   getIhm()        { return ihm; }

     /**
     * Crée un Tuyau<br>
     * Fait appel à la méthode creerTuyau de {@link Reseau} et met à jour {@link FrameApp1}.
     */
    public boolean creerTuyau(int section, char idCuve1, char idCuve2)
    {
        Tuyau tuyau = reseau.creerTuyau(section, idCuve1, idCuve2);
        if(tuyau == null)    return false;
        if(this.ihm != null) this.ihm.majListeTuyaux();
        return true;
    }

     /**
     * Crée une Cuve<br>
     * Fait appel à la méthode creerCuve de {@link Reseau} et met à jour {@link FrameApp1}.
     */
    public boolean creerCuve(int capacite)
    {
        Cuve cuve = reseau.creerCuve(capacite);
        if(cuve == null)     return false;
        if(this.ihm != null) this.ihm.majListeCuves();
        return true;
    }

    /**
     * Supprime une Cuve<br>
     * Fait appel à la méthode supprimerCuve de {@link Reseau}
     */
    public boolean supprimerCuve()
    {
        if(this.ihm == null) return false;
        int ligne = this.ihm.getCuveActive();

        if(ligne == -1 || !this.reseau.supprimerCuve(ligne)) return false;

        this.ihm.majListeCuves();
        this.ihm.majListeTuyaux();
        return true;
    }

    /**
     * Supprime un Tuyau<br>
     * Fait appel à la méthode supprimerTuyau
     */
    public boolean supprimerTuyau()
    {
        if(this.ihm == null) return false;
        int ligne = this.ihm.getTuyauActif();

        if(ligne == -1 || !this.reseau.supprimerTuyau(ligne)) return false;

        this.ihm.majListeTuyaux();
        return true;
    }

    /**
     * Définit le réseau.
     */
    public void setReseau(String absolutePath)
    {
        try
        {
            FichierReseau fichierReseau = FichierReseau.fromString(Files.readString(Path.of(absolutePath)));
            this.reseau                 = fichierReseau.getReseau();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void setIhm(FrameApp1 ihm) { this.ihm = ihm; }

    /**
     * Sauvegarde le réseau dans un fichier
     */
    public void sauvegarderReseau(ReseauFormatType reseauFormatType, String path)
    {
        try
        {
            FichierReseau fichierReseau = new FichierReseau(reseauFormatType, this.reseau);
            Files.writeString(Path.of(path), fichierReseau.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
