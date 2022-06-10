package src.app1;

public class Tests
{
	public static void main(String[] args)
	{
		Tests.testerCuves();
	}

	public static void testerCuves()
	{
		Cuve cuveIncorrecte1 = Cuve.creer(100);
		Cuve cuveIncorrecte2 = Cuve.creer(3000);
		if(cuveIncorrecte1 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);
		if(cuveIncorrecte2 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);

		Cuve cuveA = Cuve.creer(200);
		if(cuveA == null) System.err.println("La cuve A ne doit pas être nulle !");
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
}
