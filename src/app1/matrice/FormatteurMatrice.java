package src.app1.matrice;

import src.app1.Reseau;

public interface FormatteurMatrice
{
	void ajouterLiens(int[][] matrice, Reseau reseau);
	String toString(int[][] matrice);
	int[][] fromReseau(Reseau r);
	int[][] fromString(String s);
}
