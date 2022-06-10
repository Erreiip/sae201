package src.app1;

import src.app1.matrice.FormatteurMatrice;
import src.app1.matrice.FormatteurMatriceAdjacente;

public class Tests
{
	public static void main(String[] args)
	{
		Tests.testerReseauEtMatrices();
	}

	public static void testerCuves()
	{
		Cuve cuveIncorrecte1 = Cuve.creer(100);
		Cuve cuveIncorrecte2 = Cuve.creer(3000);
		if (cuveIncorrecte1 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);
		if (cuveIncorrecte2 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);

		Cuve cuveA = Cuve.creer(200);
		if (cuveA == null) System.err.println("La cuve A ne doit pas être nulle !");
		else
		{
			System.out.println(cuveA.retirerContenu(100));
			System.out.println(cuveA);
			System.out.println(cuveA.ajouterContenu(100));
			System.out.println(cuveA);
			System.out.println(cuveA.ajouterContenu(1000));
			System.out.println(cuveA);
		}
	}

	public static void testerReseauEtMatrices() {
		Reseau reseau = new Reseau();
		Cuve cuveA = Cuve.creer(1000);
		Cuve cuveB = Cuve.creer(900);
		Cuve cuveC = Cuve.creer(200);
		Cuve cuveD = Cuve.creer(700);

		cuveA.ajouterContenu(500);
		cuveB.ajouterContenu(190);

		reseau.ajouterCuve(cuveA);
		reseau.ajouterCuve(cuveB);
		reseau.ajouterCuve(cuveC);
		reseau.ajouterCuve(cuveD);

		reseau.ajouterTuyau(Tuyau.creer(2, cuveA, cuveB));
		reseau.ajouterTuyau(Tuyau.creer(6, cuveA, cuveC));
		reseau.ajouterTuyau(Tuyau.creer(4, cuveB, cuveC));
		reseau.ajouterTuyau(Tuyau.creer(8, cuveB, cuveD));

		System.out.println(reseau);

		FormatteurMatrice formatteur = new FormatteurMatriceAdjacente();
		System.out.println(formatteur.toString(formatteur.fromReseau(reseau)));
	}
}
