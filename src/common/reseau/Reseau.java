package src.common.reseau;

import src.common.reseau.element.Cuve;
import src.common.reseau.element.Transfert;
import src.common.reseau.element.Tuyau;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Un réseau représente un espace dans lequel se trouvent des {@link Cuve}s et des {@link Tuyau}.<br>
 * La création d'éléments doit se faire par l'utilisation de cette classe.
 */
public class Reseau
{
    private final ArrayList<Tuyau> tuyaux;
    private final ArrayList<Cuve> cuves;

    // Constructeur
    public Reseau()
    {
        this.tuyaux = new ArrayList<>();
        this.cuves  = new ArrayList<>();
    }

    // Accesseurs
    public ArrayList<Cuve>  getCuves()  { return cuves; }
    public ArrayList<Tuyau> getTuyaux() { return tuyaux; }

    public Cuve getCuve(char identifiant)
    {
        for (Cuve c : this.cuves)
        {
            if ( c.getIdentifiant() == identifiant )
            {
                return c;
            }
        }
        return null;
    }

    public Cuve getCuve(int index)
    {
        return this.getCuve((char)('A' + index));
    }

    public Tuyau getTuyau(Cuve cuve1, Cuve cuve2)
    {
        for(Tuyau t : this.tuyaux)
        {
            if (t.estRelie(cuve1) && t.estRelie(cuve2))
            {
                return t;
            }
        }
        return null;
    }

    public void setAllCuve() 
    {
        for ( Cuve c : this.cuves )
        {
            c.ajouterContenu( Math.round(Math.random() * (c.getCapacite() - c.getContenu())) );
        }
    }

    // Fabriques

    /**
     * Fabrique une cuve.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Cuve creerCuve(int capacite)
    {
        return Cuve.creer(this, capacite);
    }

    /**
     * Fabrique un tuyau.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Tuyau creerTuyau(int section, Cuve cuve1, Cuve cuve2)
    {
        return Tuyau.creer(this, section, cuve1, cuve2);
    }

    /**
     * Fabrique un tuyau.<br>
     * Si une des contraintes n'est pas respectée, alors cette méthode retourne {@code null}.
     */
    public Tuyau creerTuyau(int section, char idCuve1, char idCuve2)
    {
        return this.creerTuyau(section, this.getCuve(idCuve1), this.getCuve(idCuve2));
    }

    public boolean supprimerCuve(int ligne) {
        if(ligne >= this.cuves.size())
            return false;

        Cuve cuve = this.cuves.get(ligne);
        this.tuyaux.removeIf(t -> t.estRelie(cuve));
        this.cuves.remove(ligne);

        return true;
    }

    public char getNextCuveId()
    {
        char c = 'A';
        while(this.getCuve(c) != null)
        {
            c++;
        }
        return c;
    }

    public ArrayList<Tuyau> getTuyaux(Cuve c)
    {
        ArrayList<Tuyau> lstTuyaux = new ArrayList<>();

        for ( Tuyau t : this.tuyaux)
        {
            if ( t.getCuve1() == c || t.getCuve2() == c)
            {
                lstTuyaux.add(t);
            }
        }
        
        return lstTuyaux;
    }

    

    /**
     * Retourne vrai si les deux listCuves sont reliées par un tuyau dans ce réseau.
     */
    public boolean sontRelies(Cuve cuve1, Cuve cuve2)
    {
        return getTuyau(cuve1, cuve2) != null;
    }

    public void transverser()
    {
        int[] tabNbTuyaux = new int[this.cuves.size()];

        ArrayList<Transfert> transferts = new ArrayList<>();
        ArrayList<Cuve>      alCuve     = new ArrayList<>(this.cuves);

        Collections.sort(alCuve);

        int i = 0;
        for ( Cuve c : alCuve )
        {
            int occu = 0;
            for ( Tuyau t : this.tuyaux )
            {
                if ( (t.getCuve1() == c || t.getCuve2() == c) && t.getCuve1().getContenu() != t.getCuve2().getContenu() )
                {
                    tabNbTuyaux[i] = occu++;
                }
            }
            i++;
        }

        i = 0;
        for ( Cuve c : alCuve )
        {
            for ( Tuyau t : this.tuyaux )
            {
                if ( t.getCuve1() == c || t.getCuve2() == c )
                {
                    Transfert temp = t.transverser(this, c, tabNbTuyaux[i]);

                    if (temp != null )
                        transferts.add(temp);
                }
            }
            i++;
        }

        for ( Transfert t : transferts )
        {
            try{
                t.getCuveArrivee().ajouterContenu(t.getQuantite());
                t.getCuveDepart ().retirerContenu(t.getQuantite());
            }catch(Exception ignored) {}
        }

    }

    @Override
    public String toString() {
        return "Reseau{" +
                "tuyaux=" + tuyaux +
                ", cuves=" + cuves +
                '}';
    }
}
