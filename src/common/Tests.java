package src.common;

import src.app1.matrice.FormatteursMatrice;

public class Tests
{
	public static void main(String[] args)
	{
		//Tests.testerCuves();
		Tests.testerMatriceInput();
		//Tests.testerMatriceOutput();
	}

	public static void testerCuves()
	{
		Reseau reseau = new Reseau();

		Cuve cuveIncorrecte1 = reseau.creerCuve(100);
		Cuve cuveIncorrecte2 = reseau.creerCuve(3000);
		if (cuveIncorrecte1 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);
		if (cuveIncorrecte2 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);

		Cuve cuveA = reseau.creerCuve(200);
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


	public static void testerMatriceInput()
	{
		Reseau reseau = new Reseau();

		Cuve cuveA = reseau.creerCuve(1000);
		Cuve cuveB = reseau.creerCuve(900);
		Cuve cuveC = reseau.creerCuve(200);
		Cuve cuveD = reseau.creerCuve(700);

		cuveA.ajouterContenu(500);
		cuveB.ajouterContenu(190);

		System.out.println(reseau);

		int[][] matriceAdjacente = FormatteursMatrice.ADJACENTE.fromString("0110\n1011\n1100\n0100");
		FormatteursMatrice.ADJACENTE.ajouterLiens(
				matriceAdjacente,
				reseau);

		System.out.println(reseau);
	}

	public static void testerMatriceOutput()
	{
		Reseau reseau = new Reseau();

		Cuve cuveA = reseau.creerCuve(1000);
		Cuve cuveB = reseau.creerCuve(900);
		Cuve cuveC = reseau.creerCuve(200);
		Cuve cuveD = reseau.creerCuve(700);

		cuveA.ajouterContenu(500);
		cuveB.ajouterContenu(190);

		reseau.creerTuyau(2, cuveA, cuveB);
		reseau.creerTuyau(6, cuveA, cuveC);
		reseau.creerTuyau(4, cuveB, cuveC);
		reseau.creerTuyau(8, cuveB, cuveD);

		System.out.println(reseau);

		int[][] matriceAdjacente = FormatteursMatrice.ADJACENTE.fromReseau(reseau);
		System.out.println(FormatteursMatrice.ADJACENTE.toString(matriceAdjacente));
	}
}
