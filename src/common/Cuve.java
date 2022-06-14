package src.common;

/**
 * Une cuve représente un compartiment de stockage dans laquelle se stocke une unité de capacité.
 */
public class Cuve implements IReseauElement, Comparable<Cuve>
{
    public static final int MIN_CAPACITE = 200;
    public static final int MAX_CAPACITE = 1000;
    public static final int NB_CUVES_MAX = 26;

    private final char id;
    private final int  capacite;

    private double        contenu;
    private int           x;
    private int           y;
    private PositionInfos positionInfos;

    private Cuve(char id, int capacite)
    {
        this.id       = id;
        this.capacite = capacite;
        this.contenu  = 0;
    }

    /**
     * Fabrique une cuve.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    protected static Cuve creer(Reseau reseau, int capacite)
    {
        if (capacite < Cuve.MIN_CAPACITE || capacite > Cuve.MAX_CAPACITE)
        {
            throw new IllegalArgumentException("La capacité doit être comprise entre 200 et 2000");
        }
        if (reseau.getCuves().size() >= Cuve.NB_CUVES_MAX)
        {
            throw new IllegalArgumentException("Le nombre de cuves est trop élevé (maximum " + Cuve.NB_CUVES_MAX + ")");
        }

        Cuve cuve = new Cuve(reseau.getNextCuveId(), capacite);
        reseau.getCuves().add(cuve);
        return cuve;
    }

    

    public char          getIdentifiant()    { return this.id; }
    public int           getX()              { return this.x; }
    public int           getY()              { return this.y; }
    public PositionInfos getPositionInfos()  { return this.positionInfos; }
    public int           getCapacite()       { return this.capacite; }
    public double        getContenu()        { return this.contenu; }

    public String        getInfos()          { return this.id + " \n" + this.contenu + "/" + this.capacite; }

    public void    setX(int x)                                   { this.x = x; }
    public void    setY(int y)                                   { this.y = y; }
    public void    setPositionInfos(PositionInfos positionInfos) { this.positionInfos = positionInfos; }

    public void ajouterContenu(double ajout)
    {
        if (ajout < 0)
        {
            throw new IllegalArgumentException("le montant à ajouter doit être positif");
        }
        else if (this.contenu + ajout > capacite)
        {
            throw new IllegalArgumentException("le montant à ajouter fait dépasser la capacité de la cuve");
        }
        this.contenu += ajout;
    }

    public void retirerContenu(double retrait)
    {
        if (retrait < 0)
        {
            throw new IllegalArgumentException("le retrait de contenu doit être positif");
        }
        else if (this.contenu < retrait)
        {
            throw new IllegalArgumentException("le retrait de contenu est supérieur au contenu de la cuve");
        }
        this.contenu -= retrait;
    }

    public String toString()
    {
        return "Cuve{" + this.id + " " +
                "[" + x +
                ":" + y +
                "] " +
                contenu + "/" + capacite + "}";
    }

    public int compareTo( Cuve autreCuve)
    {
        return (int) Math.ceil(this.contenu) - (int) Math.ceil(autreCuve.contenu);
    }
}