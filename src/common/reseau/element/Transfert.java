package src.common.reseau.element;

public class Transfert implements Comparable<Transfert>
{
    private Cuve   cuveDepart;
    private Cuve   cuveArrivee;
    private double quantite;

    /**
     * Un Transfert représente un échange de quantité entre deux {@link Cuve}.<br>
     * Elle se définit par ses deux connections aux cuves, ainsi que de sa capacité de transfert, appelée "quantite".
     */
    public static Transfert creer(Cuve cuveDepart, Cuve cuveArrivee, double quantite)
    {
        if (cuveDepart == null || cuveArrivee == null || cuveDepart == cuveArrivee) return null;
        if (quantite <= 0) return null;
        return new Transfert(cuveDepart, cuveArrivee, quantite);
    }

    private Transfert(Cuve cuveDepart, Cuve cuveArrivee, double quantite)
    {
        this.cuveDepart = cuveDepart;
        this.cuveArrivee = cuveArrivee;
        this.quantite = quantite;
    }

    public Cuve getCuveDepart()
    {
        return this.cuveDepart;
    }

    public Cuve getCuveArrivee()
    {
        return this.cuveArrivee;
    }

    public double getQuantite()
    {
        return this.quantite;
    }

    public void setCuveDepart(Cuve c)
    {
        this.cuveDepart = c;
    }

    public void setCuveArrivee(Cuve c)
    {
        this.cuveArrivee = c;
    }

    public void setQuantite(double q)
    {
        this.quantite = q;
    }


    public int compareTo(Transfert autreTransfert)
    {
        return (int) this.cuveDepart.getContenu() - (int) autreTransfert.cuveDepart.getContenu();
    }
}