package src.common.util;

import src.common.Cuve;

public class Transfert 
{
    private Cuve cuveDepart;
    private Cuve cuveArrivee;
    private int  quantite;

    public Transfert ( Cuve cuveDepart, Cuve cuveArrivee, int quantite )
    {
        this.cuveDepart  = cuveDepart;
        this.cuveArrivee = cuveArrivee;
        this.quantite    = quantite;
    }


    public Cuve getCuveDepart()
    {
        return this.cuveDepart;
    }

    public Cuve getCuveArrivee()
    {
        return this.cuveArrivee;
    }

    public int getQuantite()
    {
        return this.quantite;
    }


    public void setCuveDepart( Cuve cuveDepart )
    {
        this.cuveDepart = cuveDepart;
    }

    public void setCuveArrivee( Cuve cuveArrivee )
    {
        this.cuveArrivee = cuveArrivee;
    }

    public void setQuantite( int quantite )
    {
        this.quantite = quantite;
    }

}