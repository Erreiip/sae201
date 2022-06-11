package src.common;

/**
 * Un tuyau représente une connexion entre deux {@link Cuve}.<br>
 * Elle se définit par ses deux connections aux cuves, ainsi que de sa capacité de transfert, appelée "section".
 */
public class Tuyau implements IReseauElement
{
    private int  section;
    private Cuve cuve1;
    private Cuve cuve2;

    private Tuyau(int section, Cuve cuve1, Cuve cuve2)
    {
        this.section = section;
        this.cuve1   = cuve1;
        this.cuve2   = cuve2;
    }

    /**
     * Fabrique un tuyau.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    protected static Tuyau creer(Reseau reseau, int section, Cuve cuve1, Cuve cuve2)
    {
        if (cuve1 == null || cuve2 == null)
        {
            System.err.println("Erreur : un tuyau doit être relié à deux cuves non nulles.");
            return null;
        }
        if (cuve1 == cuve2)
        {
            System.err.println("Erreur : un tuyau doit être relié à deux cuves différentes.");
            return null;
        }
        if (section < 2 || section > 10)
        {
            System.err.println("Erreur : un tuyau doit avoir une section comprise entre 2 et 10.");
            return null;
        }
        if (!reseau.getCuves().contains(cuve1) || reseau.getCuves().contains(cuve2))
        {
            System.err.println("Erreur : un tuyau doit être relié à deux cuves qui sont présentes dans le réseau.");
            return null;
        }
        if(reseau.sontRelies(cuve1, cuve2))
        {
            System.err.println("Erreur : un tuyau doit être relié à deux cuves qui ne sont pas déjà reliées par un autre tuyau.");
            return null;
        }
        Tuyau tuyau = new Tuyau(section, cuve1, cuve2);
        reseau.getTuyaux().add(tuyau);
        return tuyau;
    }

    public int  getSection() { return this.section; }
    public Cuve getCuve1  () { return cuve1;        }
    public Cuve getCuve2  () { return cuve2;        }

    public String transverser()
    {
        char cuveId   = this.cuve1.getCapacite() > this.cuve2.getCapacite() ? this.cuve1.getIdentifiant() : this.cuve2.getIdentifiant();
        int  quantite = Math.abs(this.cuve1.getCapacite() - this.cuve2.getCapacite());

        return cuveId + ":" + quantite;
    }

    public boolean estRelie(Cuve cuve)
    {
        return this.cuve1 == cuve || this.cuve2 == cuve;
    }

    @Override
    public String toString()
    {
        return "Tuyau(" + this.section+ ")["
                + cuve1.getIdentifiant() + "<->"
                + cuve2.getIdentifiant() + "]";
    }
}
