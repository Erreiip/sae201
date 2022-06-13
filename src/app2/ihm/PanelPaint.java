package src.app2.ihm;

import javax.swing.*;
import javax.swing.text.Position;

import src.common.Cuve;
import src.common.PositionInfos;
import src.common.Tuyau;
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
        this.tabPoints = new Ellipse2D[this.ctrl.getMetier().getCuves().size()];
    }

    public void initPaint()
    {
        this.tabPoints = new Ellipse2D[this.ctrl.getMetier().getCuves().size()];

        int i = 0;
        for ( Cuve c : this.ctrl.getMetier().getCuves() )
        {
            int taille = c.getCapacite()/10;

            int x = c.getX() - taille/2;
            int y = c.getY() - taille/2;
            
            
            tabPoints[i++] = new Ellipse2D.Double(x, y, taille, taille);
        }       
    }


    public void paintComponent (Graphics g)
    {
        super.paintComponent(g); 

        Graphics2D g2d = (Graphics2D) g;

        for ( Tuyau t : this.ctrl.getMetier().getTuyaux() )
        {
            int xDepart = t.getCuve1().getX();
            int xFin    = t.getCuve2().getX();

            int yDepart = t.getCuve1().getY();
            int yFin    = t.getCuve2().getY();

            g.setColor( Color.BLACK );
            g.drawLine( xDepart, yDepart, xFin, yFin );
        }
         
        int i = 0;
        for ( Cuve c : this.ctrl.getMetier().getCuves() )
        {
            g.setColor( this.degrade((int) c.getContenu(), c.getCapacite()) );
            g2d.fill  ( tabPoints[i] );


            int taille = c.getCapacite()/10;

            int x = c.getX() - taille/2;
            int y = c.getY() - taille/2;

            g.setColor(Color.BLACK);
            if ( c.getPositionInfos() == PositionInfos.HAUT   )  g.drawString( c.getInfos(), x             , y              );
            if ( c.getPositionInfos() == PositionInfos.BAS    )  g.drawString( c.getInfos(), x             , y + taille + 10);
            if ( c.getPositionInfos() == PositionInfos.GAUCHE )  g.drawString( c.getInfos(), x - 60        , y + taille /  2);
            if ( c.getPositionInfos() == PositionInfos.DROITE )  g.drawString( c.getInfos(), x + taille +10, y + taille/2   );

            i++;
        }


    
    }

    private Color degrade ( int contenu, int capa)
    {
        return Color.RED;
    }

}
