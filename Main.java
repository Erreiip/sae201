import iut.algo.Clavier;

public class Main {

    private static boolean debugActiver = false;

    private static void matriceAdjacence(){

        

    }

    private static void applicationDebug(String[] args){

        if (args[0].equals("true")){

            debugActiver = true;

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
