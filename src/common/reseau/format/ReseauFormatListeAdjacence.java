package src.common.reseau.format;

import src.common.Reseau;

public class ReseauFormatListeAdjacence implements ReseauFormat<char[][]>
{
	public void ajouterLiens(char[][] objet, Reseau reseau)
	{

	}

	public char[][] fromReseau(Reseau r)
	{
		return new char[0][];
	}

	public char[][] fromString(String s)
	{
		String[] lignes = s.split("\n");

		for(int lig = 0; lig < lignes.length; lig++)
		{

		}
	}

	public String toString(char[][] tab)
	{
		StringBuilder sb = new StringBuilder();
		for(char[] lig :tab) sb.append(lig[0]).append(lig[1]).append("\n");
		return sb.toString();
	}
}
