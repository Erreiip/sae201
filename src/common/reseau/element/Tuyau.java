package src.common.reseau.element;

import src.common.reseau.Reseau;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Un tuyau représente une connexion entre deux {@link Cuve}.<br>
 * Elle se définit par ses deux connections aux cuves, ainsi que de sa capacité de transfert, appelée "section".
 */
public class Tuyau implements IReseauElement
{
    public static final int SECTION_MIN = 2;
    public static final int SECTION_MAX = 10;

    private final int  section;
    private final Cuve cuve1;
    private final Cuve cuve2;

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
    public static Tuyau creer(Reseau reseau, int section, Cuve cuve1, Cuve cuve2)
    {
        Objects.requireNonNull(cuve1, "la première cuve ne doit pas être nulle");
        Objects.requireNonNull(cuve2, "la deuxième cuve ne doit pas être nulle");
        if(cuve1 == cuve2)
        {
            throw new IllegalArgumentException("les deux cuves doivent être différentes");
        }
        if(section < Tuyau.SECTION_MIN || section > Tuyau.SECTION_MAX)
        {
            throw new IllegalArgumentException("la section (ici " + section + ") doit être comprise entre "
                    + Tuyau.SECTION_MIN + " et "
                    + Tuyau.SECTION_MAX);
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


    /**
     * Creer un transfert<br>
     * Retourne un transfert entre la cuve possédant le plus de contenue et celle qui en possède le moins.
     */
    public Transfert transverser(Reseau res, Cuve c, int nbTuyaux)
    {
        double quantite;
        
        Cuve cuveDepart  = this.cuve1.getContenu() > this.cuve2.getContenu() ? this.cuve1 : this.cuve2;
        Cuve cuveArrivee = this.cuve1.getContenu() > this.cuve2.getContenu() ? this.cuve2 : this.cuve1;

        double maxQuant  = cuveDepart.getContenu() / nbTuyaux;

        if ( cuveDepart != c ) return null;

        if ( this.cuve1.getContenu() > this.getSection() ) 
        {
            quantite = (cuveDepart.getContenu() - cuveArrivee.getContenu()) / 2;
            
            if ( quantite > this.getSection() ) 
                quantite = this.getSection();

            if ( quantite > maxQuant )
                quantite = maxQuant;
        }
        else        
        {
            quantite = cuveDepart.getContenu();
        }

        ArrayList<Tuyau> tArrivee = res.getTuyaux(cuveArrivee);
        int somme = 0;

        for ( Tuyau t : tArrivee)
        {
            somme += t.getSection();
        }

        double max = (this.section / (double) somme) * (cuveArrivee.getCapacite() - cuveArrivee.getContenu());


        if ( cuveArrivee.getContenu() + quantite > cuveArrivee.getCapacite() )
            quantite = cuveArrivee.getCapacite() - cuveArrivee.getContenu();
            
        if ( quantite > max )
            quantite = max;

        System.out.println(c + "/" + quantite+ "/" + max + "/" + cuveArrivee);

        return Transfert.creer(cuveDepart, cuveArrivee, quantite);
    }

    /**
     * Retourne un booléen si la cuve passée en paramètre fait partie du tuyau.
     */
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
