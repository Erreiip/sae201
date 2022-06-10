public class Tests
{
	public static void main(String[] args)
	{
		Tests.testerCuves();
	}

	public static void testerCuves()
	{
		Cuve cuveA           = Cuve.creer(200);
		Cuve cuveIncorrecte1 = Cuve.creer(100);
		Cuve cuveIncorrecte2 = Cuve.creer(3000);

		if(cuveA == null)           System.err.println("La cuve A ne doit pas être nulle !");
		else                        System.out.println(cuveA);
		if(cuveIncorrecte1 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);
		if(cuveIncorrecte2 != null) System.err.println("La cuve incorrecte 1 doit être nulle ! Valeur : " + cuveIncorrecte1);
	}
}
