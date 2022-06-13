package src.common.reseau.format;

import src.common.Reseau;

public class ReseauFormatListeAdjacence implements ReseauFormat<char[][]>
{

	@Override
	public void ajouterLiens(char[][] objet, Reseau reseau) {

	}

	@Override
	public char[][] fromReseau(Reseau r) {
		return new char[0][];
	}

	@Override
	public char[][] fromString(String s) {
		return new char[0][];
	}

	public String toString(char[][] tab)
	{
		StringBuilder sb = new StringBuilder();
		for(char[] lig :tab) sb.append(lig[0]).append(lig[1]).append("\n");
		return sb.toString();
	}
}
