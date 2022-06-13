package src.common.reseau.format;

public enum ReseauFormatType
{
	BINAIRE     ("Binaire",            new ReseauFormatMatrice(true,  false)),
	BINAIRE_OPTI("Binaire (Optimisé)", new ReseauFormatMatrice(true,  true )),
	COUTS       ("Coûts",              new ReseauFormatMatrice(false, false)),
	COUTS_OPTI  ("Coûts (Optimisé)",   new ReseauFormatMatrice(false, true ));

	private final String        nom;
	private final ReseauFormat<?> format;

	ReseauFormatType(String nom, ReseauFormat<?> format)
	{
		this.nom    = nom;
		this.format = format;
	}

	/**
	 * @return le nom du format
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * @return l'identifiant du format de réseau
	 */
	public String getId()
	{
		return this.name().toLowerCase();
	}

	/**
	 * @return le format de réseau
	 */
	public ReseauFormat<?> getFormat()
	{
		return format;
	}
}
