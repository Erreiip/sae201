import java.nio.CharBuffer;

public class Tuyau 
{
    private int section;
    private Cuve[] cuves;
    
    public Tuyau ( int section, Cuve cuve1, Cuve cuve2 )
    {
        this.section = section;
        this.cuves = new Cuve[2];

        this.cuves[0] = cuve1;
        this.cuves[1] = cuve2;
    }

    public int getSection() { return this.section; }

    public Cuve getCuve(int i) { return this.cuves[i]; }

    public String transverser()
    {
        char cuveId = this.cuves[0].getCapacite() > this.cuves[1].getCapacite() ? this.cuves[0].getIdentifiant() : this.cuves[1].getIdentifiant();
        int quantite = Math.abs(this.cuves[0].getCapacite() - this.cuves[1].getCapacite());

        return cuveId + ":" + quantite;
    }
}
