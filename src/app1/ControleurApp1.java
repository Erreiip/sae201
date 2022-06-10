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

    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("debug")){
            lancerModeDebug();
            return;
        }

        int choixMatrice;
        do {
            System.out.println("Veuillez chosir une matrice :\n" +
                    "[1] - Liste d'adjacence \n" +
                    "[2] - Matrice de cout   \n" +
                    "[3] - Matrice de cout optimisé");

            System.out.print("Veuillez choisir entre 1 et 3 : ");
            choixMatrice = Clavier.lire_int();
        } while (choixMatrice < 1 || choixMatrice > 3);


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

    private static void lancerModeDebug() {
        ControleurApp1.debug = true;
        System.out.println("Mode debug activé");
    }

}
