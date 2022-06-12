package src.common.util;

import src.common.Cuve;

public class Transfert 
{
    private Cuve   cuveDepart;
    private Cuve   cuveArrivee;
    private double quantite;

    public Transfert(Cuve cuveDepart, Cuve cuveArrivee, double quantite)
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