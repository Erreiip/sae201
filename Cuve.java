public class Cuve 
{
    private static int nbCuves = 0;

    private char      id;
    private int       capacite;
    private int       contenu;
    private int       x;
    private int       y;
    private Position  positionInfos;

    private Cuve(char id, int capacite)
    {
        this.id       = id;
        this.capacite = capacite;
        this.contenu  = 0;
    }

    public static Cuve creerCuve(int capacite)
    {
        if(capacite < 200 || capacite > 2000)
        {
            System.err.println("Erreur: la capacité de la cuve doit être comprise entre 200 et 2000");
            return null;
        }
        else if(nbCuves >= 26)
        {
            System.err.println("Erreur: le nombre de cuves dépasse le nombre maximal autorisé (26)");
            return null;
        }
        else
        {
            char id = (char) ('A' + nbCuves);
            Cuve cuve = new Cuve(id, capacite);
            nbCuves++;
            return cuve;
        }
    }

    public char getIdentifiant() { return this.id; }

    public int      getX()             { return x; }
    public int      getY()             { return y; }
    public Position getPositionInfos() { return positionInfos; }
    public int      getCapacite()      { return capacite; }
    public int      getContenu()       { return contenu; }

    public void setX(int x)                              { this.x = x; }
    public void setY(int y)                              { this.y = y; }
    public void setPositionInfos(Position positionInfos) { this.positionInfos = positionInfos; }
    public void setContenu(int contenu)                  { this.contenu = contenu; }
}