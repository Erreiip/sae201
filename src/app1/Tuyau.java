package src.app1;

public class Tuyau
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

    public static Tuyau creer(int section, Cuve cuve1, Cuve cuve2)
    {
        if(cuve1 == cuve2)              return null;
        if(section < 2 || section > 10) return null;
        return new Tuyau(section, cuve1, cuve2);
    }

    public int  getSection() { return this.section; }
    public Cuve getCuve1()   { return cuve1; }
    public Cuve getCuve2()   { return cuve2; }

    public String transverser()
    {
        char cuveId = this.cuve1.getCapacite() > this.cuve2.getCapacite() ? this.cuve1.getIdentifiant() : this.cuve2.getIdentifiant();
        int quantite = Math.abs(this.cuve1.getCapacite() - this.cuve2.getCapacite());

        return cuveId + ":" + quantite;
    }

    public boolean estRelie(Cuve cuve)
    {
        return this.cuve1 == cuve || this.cuve2 == cuve;
    }

    @Override
    public String toString() {
        return "Tuyau(" + this.section+ ")["
                + cuve1.getIdentifiant() + "<->" + cuve2.getIdentifiant() + "]";
    }
}
