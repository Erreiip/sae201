package src.common;

import src.common.util.Transfert;

import java.util.Objects;

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
        Objects.requireNonNull(cuve1, "la première cuve ne doit pas être nulle");
        Objects.requireNonNull(cuve2, "la deuxième cuve ne doit pas être nulle");
        if (section < 2 || section > 10)
        {
            throw new IllegalArgumentException("la section doit être comprise entre 2 et 10");
        }
        if(!reseau.getCuves().contains(cuve1))
        {
            throw new IllegalArgumentException("la première cuve n'est pas dans le réseau");
        }
        if(!reseau.getCuves().contains(cuve2))
        {
            throw new IllegalArgumentException("la deuxième cuve n'est pas dans le réseau");
        }
        if(reseau.sontRelies(cuve1, cuve2))
        {
            throw new IllegalArgumentException("les cuves sont déjà reliées par le tuyau " + reseau.getTuyau(cuve1, cuve2));
        }
        Tuyau tuyau = new Tuyau(section, cuve1, cuve2);
        reseau.getTuyaux().add(tuyau);
        return tuyau;
    }

    public int  getSection() { return this.section; }
    public Cuve getCuve1  () { return cuve1;        }
    public Cuve getCuve2  () { return cuve2;        }

    public Transfert transverser()
    {
        double quantite;
        
        Cuve cuveDepart  = this.cuve1.getCapacite() > this.cuve2.getCapacite() ? this.cuve1 : this.cuve2;
        Cuve cuveArrivee = this.cuve1.getCapacite() < this.cuve2.getCapacite() ? this.cuve1 : this.cuve2;

        if ( this.cuve1.getContenu() > this.getSection() ) quantite = this.getSection();
        else                                               quantite = cuveDepart.getContenu();

        return new Transfert (cuveDepart, cuveArrivee, quantite);
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
