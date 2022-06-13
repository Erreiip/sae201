package src.common.matrice;

public class FormatsReseau
{
	public static final FormatReseau<int[][]> BINAIRE      = new FormatReseauMatrice("binaire", true, false);
	public static final FormatReseau<int[][]> BINAIRE_OPTI = new FormatReseauMatrice("binaire_opti", true, false);
	public static final FormatReseau<int[][]> COUTS        = new FormatReseauMatrice("couts", false, false);
	public static final FormatReseau<int[][]> COUTS_OPTI   = new FormatReseauMatrice("couts_opti", false, true);
}
