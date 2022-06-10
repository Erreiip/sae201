package src.ihm;

import src.Cuve;
import src.Reseau;
import src.Tuyau;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class PanelPaint extends JPanel
{
    private FrameReseau frameMere;

    public PanelPaint ( FrameReseau frameMere)
    {
        this.frameMere = frameMere;
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g = (Graphics2D) g; 

        for ( Cuve c : Reseau.getCuves() )
        {
            int taille = c.getCapacite() / 100;

            int x = c.getPosX() + taille;
            int y = c.getPosY() - taille;
            
            g.fillArc(x, y, taille, taille);
        }

        for ( Tuyau t : Reseau.getTuyaux() )
        {
            int xDepart = t.getCuve(0).getPosX;
            int xFin    = t.getCuve(1).getPosX;

            int yDepart = t.getCuve(0).getPosY;
            int yFin    = t.getCuve(1).getPosY;


            g.drawLine( xDepart, yDepart, xFin, yFin );
        }

               
    }
}
