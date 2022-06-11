package src.app1.matrice;

public class FormatteursMatrice
{
	public static final FormatteurMatrice ADJACENTE = new FormatteurMatriceAdjacente();

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
}
