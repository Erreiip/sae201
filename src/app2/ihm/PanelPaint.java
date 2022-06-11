package src.app2.ihm;

import javax.swing.*;

import src.app1.Cuve;
import src.app1.Tuyau;
import src.app2.ControleurApp2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.geom.Ellipse2D;

public class PanelPaint extends JPanel
{
    private ControleurApp2 ctrl;

    private Ellipse2D[]    tabPoints;

    public PanelPaint ( ControleurApp2 ctrl)
    {
        this.ctrl = ctrl;
        this.tabPoints = new Ellipse2D[this.ctrl.getCuves().size()];
    }

    public void paintComponent (Graphics g)
    {
        super.paintComponent(g); 

        int i = 0;
        for ( Cuve c : this.ctrl.getCuves() )
        {
            int taille = c.getCapacite() / 100;

            int x = c.getX() + taille;
            int y = c.getY() - taille;
            
            g.setColor( this.degrade((int) c.getContenu(), c.getCapacite()) );
            tabPoints[i++] = new Ellipse2D.Double(x, y, taille, taille);
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
