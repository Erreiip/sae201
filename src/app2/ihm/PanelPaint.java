package src.app2.ihm;

import javax.swing.*;

import src.common.Cuve;
import src.common.PositionInfos;
import src.common.Tuyau;
import src.app2.ControleurApp2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.*;

import java.awt.geom.Ellipse2D;

public class PanelPaint extends JPanel
{
    private ControleurApp2 ctrl;

    private Ellipse2D[]    tabPoints;

    private Ellipse2D      ellipseSelectionnee;

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
            
            
            this.tabPoints[i++] = new Ellipse2D.Double(x, y, taille, taille);
        }       

        this.addMouseListener      ( new EllipseTake () );
        this.addMouseMotionListener( new EllispseDrag() );
    }


    public void paintComponent (Graphics g)
    {
        super.paintComponent(g); 

        Graphics2D g2d = (Graphics2D) g;

        for ( Tuyau t : this.ctrl.getMetier().getTuyaux() )
        {
            Ellipse2D temp1   = this.tabPoints[this.ctrl.getMetier().getCuves().indexOf(t.getCuve1())];
            Ellipse2D temp2   = this.tabPoints[this.ctrl.getMetier().getCuves().indexOf(t.getCuve2())];

            int taille1 = (int) temp1.getWidth();
            int taille2 = (int) temp2.getWidth();


            int xDepart = (int) temp1.getX() + taille1/2;
            int xFin    = (int) temp2.getX() + taille2/2;

            int yDepart = (int) temp1.getY() + taille1/2;
            int yFin    = (int) temp2.getY() + taille2/2;

            g.setColor( Color.BLACK );
            g.drawLine( xDepart, yDepart, xFin, yFin );
        }
         
        int i = 0;
        for ( Cuve c : this.ctrl.getMetier().getCuves() )
        {
            g.setColor( this.degrade( c.getContenu(), c.getCapacite()) );
            g2d.fill  ( tabPoints[i] );


            g.setColor( Color.BLACK );
            g2d.draw  ( tabPoints[i] );

            int taille = (int) tabPoints[i].getWidth();

            int x = (int) tabPoints[i].getX();
            int y = (int) tabPoints[i].getY();

            if ( c.getPositionInfos() == PositionInfos.HAUT   )  g.drawString( c.getInfos(), x             , y - 10         );
            if ( c.getPositionInfos() == PositionInfos.BAS    )  g.drawString( c.getInfos(), x             , y + taille + 20);
            if ( c.getPositionInfos() == PositionInfos.GAUCHE )  g.drawString( c.getInfos(), x - 80        , y + taille / 2 );
            if ( c.getPositionInfos() == PositionInfos.DROITE )  g.drawString( c.getInfos(), x + taille +10, y + taille / 2 );

            i++;
        }    
    }


    private Color degrade ( Double contenu, int capa)
    {
        int nbIte = 0;

        if ( contenu != 0)
            nbIte = (int) ((capa / contenu) / (double) capa) * 255;

        Color c = new Color( 255 , 0,0 );

        if ( nbIte > 127 )
            for ( int cpt = 0; cpt < nbIte; cpt++) { c.brighter(); }
        else
            for ( int cpt = 255; cpt > nbIte; cpt--) { c.darker(); }
        
        return c;
    }


    private class EllispseDrag extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e) 
        {
            if ( PanelPaint.this.ellipseSelectionnee != null) {
                for ( Ellipse2D ellipse : PanelPaint.this.tabPoints )
                {
                    if ( ellipse == PanelPaint.this.ellipseSelectionnee )
                    {
                        double taille = ellipse.getWidth();

                        PanelPaint.this.ellipseSelectionnee.setFrame(e.getX() - taille/2, e.getY() - taille/2, ellipse.getWidth(), ellipse.getHeight());
                    }
                }

                repaint();
            }
        }
    }


    private class EllipseTake extends MouseAdapter
    {
        public void mousePressed(MouseEvent e) 
        {      
            for (Ellipse2D ellipse : tabPoints) 
            {
                if(ellipse.contains(e.getPoint())) 
                {
                    PanelPaint.this.ellipseSelectionnee = ellipse;
                    return;
                }
            }
        }

        public void mouseReleased(MouseEvent e) 
        {       
            PanelPaint.this.ellipseSelectionnee = null;
        }
    }

}
