package src.common.reseau.fichier;

import src.common.reseau.element.Cuve;
import src.common.reseau.Reseau;
import src.common.reseau.format.ReseauFormatType;

public class FichierReseau
{
    private static final String DELIMITEUR_CONTENU = "\n" + "=".repeat(15) + "\n";

    private final ReseauFormatType typeFormat;
    private final Reseau           reseau;

    public FichierReseau(ReseauFormatType typeFormat, Reseau reseau)
    {
        this.typeFormat = typeFormat;
        this.reseau = reseau;
    }

    public static FichierReseau fromString(String s)
    {
        Reseau reseau = new Reseau();

        String[] contenu = s.split(FichierReseau.DELIMITEUR_CONTENU);

        for (String sCapacite : contenu[2].split("\n"))
        {
            try
            {
                reseau.creerCuve(Integer.parseInt(sCapacite));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        ReseauFormatType formatType = ReseauFormatType.fromId(contenu[0]);
        formatType.getFormat().ajouterTuyaux(contenu[1], reseau);
        return new FichierReseau(formatType, reseau);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb
                .append(this.typeFormat.getId())
                .append(FichierReseau.DELIMITEUR_CONTENU)
                .append(this.typeFormat.getFormat().construireString(this.reseau))
                .append(FichierReseau.DELIMITEUR_CONTENU);
        for (Cuve cuve : this.reseau.getCuves())
        {
            sb.append(cuve.getCapacite()).append("\n");
        }
        return sb.toString();
    }

    public Reseau getReseau()
    {
        return reseau;
    }

    public ReseauFormatType getTypeFormat()
    {
        return typeFormat;
    }
}
