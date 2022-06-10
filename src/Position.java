package src;

public enum Position
{
    HAUT   (0),
    BAS    (1),
    GAUCHE (2),
    DROITE (3);

    int ordinal;

    Position ( int oridinal ) { this.ordinal = ordinal;}

    public int getOrdial() { return this.ordinal; }

}
