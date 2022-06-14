package src.common.reseau.format;

public enum ReseauFormatType
{
    COUTS("Coûts", new ReseauFormatMatrice(false)),
    COUTS_OPTI("Coûts (Optimisé)", new ReseauFormatMatrice(true)),
    LISTE_ADJA("Liste d'adjacence", new ReseauFormatListeAdjacence());

    private final String       nom;
    private final ReseauFormat format;

    ReseauFormatType(String nom, ReseauFormat format)
    {
        this.nom = nom;
        this.format = format;
    }

    /**
     * @return le format de réseau correspondant à l'identifiant
     */
    public static ReseauFormatType fromId(String id)
    {
        return ReseauFormatType.valueOf(id.toUpperCase());
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
    public ReseauFormat getFormat()
    {
        return format;
    }

    public static ReseauFormatType get(String nom)
    {
        for (ReseauFormatType reseauFormatType : ReseauFormatType.values())
            if (reseauFormatType.getNom().equals(nom))
                return reseauFormatType;
        return null;
    }

}
