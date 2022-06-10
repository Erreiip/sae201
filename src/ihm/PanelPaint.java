package src.ihm;

import src.Cuve;
import src.Reseau;
import src.Tuyau;

import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.PrintWriter;
import java.awt.Color;

import src.Controleur;
import src.Reseau;
import src.Cuve;



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
            
            g.setColor( this.degrade(c.getContenu(), c.getCapacite()) );
            g.fillArc (x, y, taille, taille);
        }

        for ( Tuyau t : this.ctrl.getTuyaux() )
        {
            int xDepart = t.getCuve1().getX();
            int xFin    = t.getCuve2().getX();

            int yDepart = t.getCuve1().getY();
            int yFin    = t.getCuve2().getY();

            g.setColor( Color.BLACK );
            g.drawLine( xDepart, yDepart, xFin, yFin );
        }

               
    }

    private Color degrade ( int contenu, int capa)
    {
        Color c = Color.RED ;

        int nombreIte = (int) Math.ceil(contenu/ (double) capa) * 100;

        if ( nombreIte == 50 )
        {
            for ( int cpt = 0; cpt < nombreIte; cpt++ )
            {
                if ( nombreIte > 250 )
                    c = c.darker();
                else
                    c = c.brighter();
            }
        }

        return c;
    }

}
