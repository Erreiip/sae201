package src;

public class Cuve
{
    private static int nbCuves = 0;

    private final char      id;
    private final int       capacite;

    private double    contenu;
    private int       x;
    private int       y;
    private Position  positionInfos;

    private Cuve(char id, int capacite)
    {
        this.id       = id;
        this.capacite = capacite;
        this.contenu  = 0;
    }

    public static Cuve creer(int capacite)
    {
        // Vérifier que la capacité est correct
        if(capacite < 200 || capacite > 2000)
        {
            System.err.println("Erreur : la capacité de la cuve doit être comprise entre 200 et 2000");
            return null;
        }
        // Vérifier qu'il n'y a pas trop de cuves
        else if(nbCuves >= 26)
        {
            System.err.println("Erreur : le nombre de cuves dépasse le nombre maximal autorisé (26)");
            return null;
        }
        // Si toutes les vérifications sont passées, création de la cuve
        else
        {
            char id = (char) ('A' + nbCuves);
            Cuve cuve = new Cuve(id, capacite);
            nbCuves++;
            return cuve;
        }
    }

    public static int getNbCuves() { return nbCuves;}

    public char     getIdentifiant()   { return this.id; }
    public int      getX()             { return x; }
    public int      getY()             { return y; }
    public Position getPositionInfos() { return positionInfos; }
    public int      getCapacite()      { return capacite; }
    public double   getContenu()       { return contenu; }

    public void setX(int x)                              { this.x = x; }
    public void setY(int y)                              { this.y = y; }
    public void setPositionInfos(Position positionInfos) { this.positionInfos = positionInfos; }

    public boolean ajouterContenu(double ajout)
    {
        if(ajout < 0)
        {
            System.err.println("Erreur : l'ajout de contenu doit être positif");
            return false;
        }
        else if(this.contenu + ajout > capacite)
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
        if(retrait < 0)
        {
            System.err.println("Erreur : le retrait de contenu doit être positif");
            return false;
        }
        else if(this.contenu < retrait)
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
        return "src.Cuve " + this.id + " : " +
                "[" + x +
                ":" + y +
                "] " +
                contenu + "/" + capacite;
    }
}