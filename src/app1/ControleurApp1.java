package src.app1;

import iut.algo.Clavier;
import src.app2.ihm.FrameReseau;

public class ControleurApp1
{

    private static boolean debug = false;

    private FrameReseau frame;

    private Reseau       res;

    public ControleurApp1(Reseau res)
    {
        
    }

    public static void matriceAdjacence(){

        while (Cuve.getNbCuves() < 26) {

            int capaciteMaximal;
            double capaciteInitial;
            Cuve cuveEnCreation;

            System.out.println("Création de la cuve " + (char) ('A' + Cuve.getNbCuves()) + " :");

            do {
                System.out.print("Entrez la capacité maximale de la cuve (entre 200 et 2000) :");
                capaciteMaximal = Clavier.lire_int();

                cuveEnCreation = Cuve.creer(capaciteMaximal);
            } while (cuveEnCreation == null);

            boolean success;
            do {
                System.out.print("Entrez la capacité initiale de la cuve (entre 0 et " + cuveEnCreation.getCapacite() + ") :");
                capaciteInitial = Clavier.lire_double();
                success = cuveEnCreation.ajouterContenu(capaciteInitial);
            } while (!success);

            System.out.println("La cuve " + cuveEnCreation.getIdentifiant() + " a été créée avec succès.");
        }
    }

    public static void main(String[] args) {


        if (args.length == 0){
            System.out.println("Vide");
        }
        else {

            applicationDebug(args);

        }

        int choixMatrice;

        System.out.println("Veuillez chosir une matrice :\n" +
                "[1] - Liste d'adjacence \n" +
                "[2] - Matrice de cout   \n" +
                "[3] - Matrice de cout optimisé");

        System.out.print("Veuillez choisir entre 1 et 3 : ");

        while (true) {

            choixMatrice = Clavier.lire_int();

            if (choixMatrice < 1 || choixMatrice > 3){


                System.out.print("Entrez invalide, veuillez choisir entre 1 et 3 : ");

            }
            else {
                break;

            }

            switch (choixMatrice) {
                case 1:
                    matriceAdjacence();
                    break;

                case 2:
                    System.out.println("Pas implémenté");

                case 3:
                    System.out.println("Pas implémenté");

                default:
                    break;
            }

        }



    }

}
