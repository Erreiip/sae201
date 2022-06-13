package src.app2.ihm;

import javax.swing.*;

import src.app2.ControleurApp2;

import java.awt.Dimension;
import java.awt.BorderLayout;

public class FrameReseau extends JFrame 
{

    private ControleurApp2 ctrl;

    private PanelPaint panelPrincipal;
    private PanelMenu  panelMenu;

    public FrameReseau ( ControleurApp2 controleurApp2)
    {
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height  = d.height;
        int width   = d.width;

        this.ctrl = controleurApp2;
        
        /*-------------------------------------------------*/
        
        this.setLayout  ( new BorderLayout() );
        this.setSize    ( width/2, height/2 );
        this.setLocationRelativeTo( null );

        this.panelMenu      = new PanelMenu ( this.ctrl );
        this.panelPrincipal = new PanelPaint( this.ctrl );
        this.panelPrincipal.setPreferredSize( new Dimension (width/2, height/2) );

        System.out.println( width + "/" + height);

        this.add( this.panelMenu     , BorderLayout.NORTH  );
        this.add( this.panelPrincipal, BorderLayout.CENTER );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setVisible( true );
    }

    public void dessiner()
    {
        panelPrincipal.initPaint();
        panelPrincipal.repaint();
    }

    public Dimension getDim() { return new Dimension(this.panelPrincipal.getWidth(), this.panelPrincipal.getHeight()); }
}
