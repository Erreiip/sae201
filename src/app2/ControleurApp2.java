package src.app2;

import java.util.HashMap;
import java.util.Map;

import java.awt.Dimension;

import java.io.IOException;

import src.app2.ihm.*;
import src.common.Reseau;
import src.common.Tuyau;
import src.common.reseau.fichier.FichierReseau;
import src.common.Cuve;
import src.common.PositionInfos;

import java.nio.file.Path;
import java.nio.file.Files;

public class ControleurApp2
{
    private FrameReseau frame;
    private Reseau      metier;

    public ControleurApp2()
    {
        this.metier = new Reseau();
        this.frame = new FrameReseau(this);
    }

    public void placementCuves()
    {
        Cuve[] tabCuves = new Cuve[this.metier.getCuves().size()];

        Map<String, Integer> voisinPref = new HashMap<String, Integer>();

        for (Cuve c : this.metier.getCuves())
        {
            String str = "";

            for (Tuyau t : this.metier.getTuyaux())
            {
                if (t.getCuve1() != c && t.getCuve2() == c)
                    str += t.getCuve1().getIdentifiant();

                if (t.getCuve1() == c && t.getCuve2() != c)
                    str += t.getCuve2().getIdentifiant();
            }

            voisinPref.put(c.getIdentifiant() + "", str.length());
        }


        int taille = voisinPref.size();
        for (int cpt = 0; cpt < taille; cpt++)
        {
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : voisinPref.entrySet())
            {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue())
                {
                    maxEntry = entry;
                }
            }

            tabCuves[cpt] = this.metier.getCuve(maxEntry.getKey().charAt(0));

            voisinPref.remove(maxEntry.getKey());
        }


        int heightUnique;
        int widthUnique;
        int ancienneWidth, ancienneHeight;

        {
            Dimension temp   = this.frame.getDim();
            int       height = temp.height;
            int       width  = temp.width;

            heightUnique = height / (int) Math.ceil(tabCuves.length / (double) 2);
            widthUnique = width / 4;

            heightUnique -= heightUnique / Math.ceil(tabCuves.length / (double) 2);
            widthUnique -= widthUnique / 4;


        }

        ancienneWidth = widthUnique;
        ancienneHeight = heightUnique;

        int i = 2;
        for (int cpt = 1; cpt <= tabCuves.length; cpt++)
        {

            tabCuves[cpt - 1].setX(widthUnique);
            tabCuves[cpt - 1].setY(heightUnique);

            if ((cpt % 2) == 0) tabCuves[cpt - 1].setPositionInfos(PositionInfos.DROITE);
            if (((cpt % 2) != 0) && cpt != 1 && cpt != 2) tabCuves[cpt - 1].setPositionInfos(PositionInfos.GAUCHE);
            if (cpt == tabCuves.length) tabCuves[cpt - 1].setPositionInfos(PositionInfos.BAS);
            if (cpt == 1 || cpt == 2) tabCuves[cpt - 1].setPositionInfos(PositionInfos.HAUT);


            if ((cpt % 2) == 0)
            {
                if ((i % 2) == 0) widthUnique = ancienneWidth * 2;
                else widthUnique = ancienneWidth;

                heightUnique += ancienneHeight;

                i++;
            } else
            {
                widthUnique += ancienneWidth * 2;
            }
        }
    }


    public Reseau getMetier()
    {
        return this.metier;
    }

    public void fermer()
    {
        this.frame.dispose();
    }

    public void redessiner()
    {
        this.frame.redessiner();
    }

    public void tranverser()
    {
        this.metier.transverser();
        this.frame.redessiner();
    }

    public boolean creerReseau(String pathFichier)
    {
        String        contenuFichier = "";
        FichierReseau fichierReseau  = null;
        try
        {
            contenuFichier = Files.readString(Path.of(pathFichier));
            fichierReseau = FichierReseau.fromString(contenuFichier);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if (fichierReseau == null) return false;

        this.metier = fichierReseau.getReseau();
        this.placementCuves();
        this.frame.dessiner();
        return true;
    }

    public static void main(String[] args)
    {
        new ControleurApp2();
    }
}
