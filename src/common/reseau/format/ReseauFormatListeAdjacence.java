package src.common.reseau.format;

import src.common.Reseau;
import src.common.Tuyau;

import java.util.ArrayList;

public class ReseauFormatListeAdjacence implements ReseauFormat<char[][]>
{
	public void ajouterTuyaux(char[][] objet, Reseau reseau)
	{
		for(char[] chars : objet)
			reseau.creerTuyau(1, (char) ('A' + chars[0]), (char) ('A' + chars[1]));
	}

	public char[][] fromReseau(Reseau r)
	{
		ArrayList<Tuyau> tuyaux = r.getTuyaux();
		char[][] tab = new char[tuyaux.size()][2];
		for(int i = 0; i < tuyaux.size(); i++)
		{
			tab[i][0] = tuyaux.get(i).getCuve1().getIdentifiant();
			tab[i][1] = tuyaux.get(i).getCuve2().getIdentifiant();
		}
		return tab;
	}

	public char[][] fromString(String s)
	{
		String[] lignes = s.split("\n");
		char[][] tab = new char[lignes.length][2];

		for(int lig = 0; lig < tab.length; lig++)
		{
			String[] colonnes = lignes[lig].split("");
			if(colonnes.length != 2)
			{
				throw new IllegalArgumentException("La ligne " + lig + " n'est pas valide.");
			}
			else
			{
				for(int col = 0; col < colonnes.length; col++)
				{
					if(colonnes[col].length() != 1)
					{
						throw new IllegalArgumentException(colonnes[col] + " n'est pas valide. (on attend 2 caractères de cuves)");
					}
					else
					{
						tab[lig][0] = colonnes[col].charAt(0);
						tab[lig][1] = colonnes[col].charAt(0);
					}
				}
			}
		}
		return tab;
	}

	public String toString(char[][] tab)
	{
		StringBuilder sb = new StringBuilder();
		for(char[] lig :tab) sb.append(lig[0]).append(lig[1]).append("\n");
		return sb.toString();
	}
}
