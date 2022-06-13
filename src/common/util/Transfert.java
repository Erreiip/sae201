package src.common.util;

import src.common.Cuve;

import java.util.Objects;

public class Transfert 
{
    private Cuve   cuveDepart;
    private Cuve   cuveArrivee;
    private double quantite;

    public static Transfert creer(Cuve cuveDepart, Cuve cuveArrivee, double quantite)
    {
        Objects.requireNonNull(cuveDepart,  "la cuve de départ ne doit pas être nulle");
        Objects.requireNonNull(cuveArrivee, "la cuve d'arrivée ne doit pas être nulle");
        if(cuveDepart == cuveArrivee)
            throw new IllegalArgumentException("les deux cuves doivent être différentes");
        if(quantite <= 0.0D)
            throw new IllegalArgumentException("la quantite doit être positive");
        return new Transfert(cuveDepart, cuveArrivee, quantite);
    }

    private Transfert(Cuve cuveDepart, Cuve cuveArrivee, double quantite)
    {
        this.cuveDepart  = cuveDepart;
        this.cuveArrivee = cuveArrivee;
        this.quantite    = quantite;
    }

    public Cuve getCuveDepart()  { return this.cuveDepart; }
    public Cuve getCuveArrivee() { return this.cuveArrivee; }
    public double getQuantite()  { return this.quantite; }

    public void setCuveDepart(Cuve c)  { this.cuveDepart = c; }
    public void setCuveArrivee(Cuve c) { this.cuveArrivee = c; }
    public void setQuantite(int q)     { this.quantite = q; }

}