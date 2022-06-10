import iut.algo.Clavier;

public class Main {

    //private boolean debugActiver;

    private static void matriceAdjacence(){



    }

    public static void main(boolean debug) {


        //this.debugActiver = debug;

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
