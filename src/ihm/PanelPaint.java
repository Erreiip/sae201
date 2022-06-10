package src.ihm;

import src.Cuve;
import src.Reseau;
import src.Tuyau;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;


public class PanelPaint extends JPanel
{
    private Controleur ctrl;

    public PanelPaint ( Controleur ctrl)
    {
        this.ctrl = ctrl;
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g = (Graphics2D) g; 

        for ( Cuve c : this.ctrl.getCuves() )
        {
            int taille = c.getCapacite() / 100;

            int x = c.getX() + taille;
            int y = c.getY() - taille;
            
            g.fillArc(x, y, taille, taille);
        }

        for ( Tuyau t : this.ctrl.getTuyaux() )
        {
            int xDepart = t.getCuve(0).getX();
            int xFin    = t.getCuve(1).getX();

            int yDepart = t.getCuve(0).getY();
            int yFin    = t.getCuve(1).getY();


            g.drawLine( xDepart, yDepart, xFin, yFin );
        }

               
    }
}
