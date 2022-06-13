package src.common;

import src.common.reseau.format.ReseauFormatType;

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

		try
		{
			throw new RuntimeException(reseau.creerCuve(100) + " doit être égal à null");
		}
		catch (Exception e)
		{
			System.out.println("Test OK : création cuve de capacité trop basse (" + e.getMessage() + ")");
		}

		try
		{
			throw new RuntimeException(reseau.creerCuve(3000) + " doit être égal à null");
		}
		catch (Exception e)
		{
			System.out.println("Test OK : création cuve de capacité trop haute (" + e.getMessage() + ")");
		}

		Cuve cuveA;

		cuveA = reseau.creerCuve(200);
		System.out.println("Test OK : création cuve de capacité correcte");

		try
		{
			cuveA.retirerContenu(100);
		}
		catch (Exception e)
		{
			System.out.println("Test OK : retrait de contenu à une cuve vide (" + e.getMessage() + ")");
		}
		cuveA.ajouterContenu(100);
		System.out.println("Test OK : ajout de contenu à une cuve");

		try
		{
			cuveA.ajouterContenu(1000);
		}
		catch (Exception e)
		{
			System.out.println("Test OK : ajout trop important de contenu à une cuve (" + e.getMessage() + ")");
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

		ReseauFormatType.COUTS.getFormat().ajouterTuyaux(
				"X 2 6 X\n2 X 4 8\n6 4 X X\nX 8 X X",
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

		System.out.println(ReseauFormatType.COUTS.getFormat().toString(reseau));
	}
}
