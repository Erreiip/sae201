package src.common;

/**
 * Une cuve représente un compartiment de stockage dans laquelle se stocke une unité de capacité.
 */
public class Cuve implements IReseauElement
{
    private final char      id;
    private final int       capacite;

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
        if (capacite < 200 || capacite > 2000)
        {
            System.err.println("Erreur : la capacité de la cuve doit être comprise entre 200 et 2000");
            return null;
        }
        if (reseau.getCuves().size() >= 26) {
            System.err.println("Erreur : le nombre de cuves dans le réseau dépasse le nombre maximal autorisé (26)");
            return null;
        }

        char id   = (char) ('A' + reseau.getCuves().size());
        Cuve cuve = new Cuve(id, capacite);
        reseau.getCuves().add(cuve);
        return cuve;
    }

    public char          getIdentifiant()    { return this.id; }
    public int           getX()              { return x; }
    public int           getY()              { return y; }
    public PositionInfos getPositionInfos()  { return positionInfos; }
    public int           getCapacite()       { return capacite; }
    public double        getContenu()        { return contenu; }

    public void setX(int x)                              { this.x = x; }
    public void setY(int y)                              { this.y = y; }
    public void setPositionInfos(PositionInfos positionInfos) { this.positionInfos = positionInfos; }

    public boolean ajouterContenu(double ajout)
    {
        if (ajout < 0)
        {
            System.err.println("Erreur : l'ajout de contenu doit être positif");
            return false;
        }
        else if (this.contenu + ajout > capacite)
        {
            System.err.println("Erreur : l'ajout de contenu fait déborder la cuve");
            return false;
        }
        else
        {
            this.contenu += ajout;
            return true;
        }
    }

    public boolean retirerContenu(double retrait)
    {
        if (retrait < 0)
        {
            System.err.println("Erreur : le retrait de contenu doit être positif");
            return false;
        }
        else if (this.contenu < retrait)
        {
            System.err.println("Erreur : le retrait de contenu est supérieur à ce qu'il reste");
            return false;
        }
        else
        {
            this.contenu -= retrait;
            return true;
        }
    }

    public String toString()
    {
        return "Cuve{" + this.id + " " +
                "[" + x +
                ":" + y +
                "] " +
                contenu + "/" + capacite + "}";
    }
}