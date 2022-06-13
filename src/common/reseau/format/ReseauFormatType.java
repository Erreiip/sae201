package src.common.reseau.format;

public enum ReseauFormatType
{
	BINAIRE     ("binaire",      new ReseauFormatMatrice(true,  false)),
	BINAIRE_OPTI("binaire_opti", new ReseauFormatMatrice(true,  true )),
	COUTS       ("couts",        new ReseauFormatMatrice(false, false)),
	COUTS_OPTI  ("couts_opti",   new ReseauFormatMatrice(false, true ));

	private final String          nom;
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
