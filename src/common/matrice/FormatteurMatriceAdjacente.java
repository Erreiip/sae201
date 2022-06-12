package src.common.matrice;

import iut.algo.Clavier;
import src.common.Cuve;
import src.common.Reseau;
import src.common.Tuyau;

public class FormatteurMatriceAdjacente implements FormatteurMatrice
{
	public void ajouterLiens(int[][] matrice, Reseau reseau)
	{
		for(int lig = 0; lig < matrice.length; lig++)
		{
			for(int col = 0; col < matrice[lig].length; col++)
			{
				if(matrice[lig][col] == 1)
				{
					Cuve cuve1 = reseau.getCuve(lig);
					Cuve cuve2 = reseau.getCuve(col);
					Tuyau tuyau;
					boolean correct = false;
					if(!reseau.sontRelies(cuve1, cuve2)) {
						System.out.print("Rentrez la section du tuyau reliant " + cuve1 + " et " + cuve2 + " : ");
						while(!correct)
						{
							tuyau = reseau.creerTuyau(Clavier.lire_int(), cuve1, cuve2);
							if(tuyau != null) correct = true;
							else System.out.println("Erreur de saisie ! Veuillez recommencer.");
						}
					}
				}
			}
		}
	}

	public String toString(int[][] matrice)
	{
		StringBuilder sb = new StringBuilder();
		for(int[] lig : matrice) {
			for(int col : lig) {
				sb.append(col);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public int[][] fromReseau(Reseau r)
	{
		int[][] tab = new int[r.getCuves().size()][r.getCuves().size()];
		int lig = 0;
		StringBuilder sb = new StringBuilder();
		for(Cuve cuve1 : r.getCuves())
		{
			int col = 0;
			for(Cuve cuve2 : r.getCuves())
			{
				if(cuve1 == cuve2)
					tab[lig][col] = 0;
				else
					tab[lig][col] = r.sontRelies(cuve1, cuve2) ? 1 : 0;
				col++;
			}
			sb.append("\n");
			lig++;
		}
		return tab;
	}

	public int[][] fromString(String s)
	{
		String[] lignes = s.split("\n");
		int[][] matrice = new int[lignes.length][];
		for(int lig = 0; lig < lignes.length; lig++)
		{
			String[] valeurs = lignes[lig].split("");
			matrice[lig] = new int[valeurs.length];
			for(int col = 0; col < valeurs.length; col++)
			{
				int i = Integer.parseInt(valeurs[col]);
				if(i != 0 && i != 1)
				{
					throw new IllegalArgumentException("La matrice adjacente doit contenir que des 0 et des 1");
				}
				matrice[lig][col] = i;
			}
		}
		return matrice;
	}
}
