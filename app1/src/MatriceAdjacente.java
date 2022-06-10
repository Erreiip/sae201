package src;

public class MatriceAdjacente 
{
    public MatriceAdjacente()
    {
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
}
