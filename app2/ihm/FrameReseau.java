package app2.ihm;

import javax.swing.*;

import app2.Controleur;

import java.awt.Dimension;
import java.awt.BorderLayout;


public class FrameReseau extends JFrame 
{

    private Controleur ctrl;

    private PanelPaint panelPrincipal;
    private PanelMenu  panelMenu;

    public FrameReseau ( Controleur controleur )
    {
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height  = d.height;
        int width   = d.width;

        this.ctrl = controleur;
        
        /*-------------------------------------------------*/
        
        this.setLayout(new BorderLayout());
        this.setSize    ( height/2, width/2 );
        this.setLocation( height/2 - this.HEIGHT, width/2 - this.WIDTH );

        this.panelMenu      = new PanelMenu ( this.ctrl );
        this.panelPrincipal = new PanelPaint( this.ctrl );

        this.add( this.panelMenu     , BorderLayout.NORTH  );
        this.add( this.panelPrincipal, BorderLayout.CENTER );

    }
    


}
