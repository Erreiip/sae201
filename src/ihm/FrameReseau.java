package src.ihm;

import javax.swing.*;

import java.awt.Dimension;

import src.Controleur;


public class FrameReseau extends JFrame 
{

    private Controleur ctrl;

    private PanelPaint panelPrincipal;

    public FrameReseau ( Controleur ctrl )
    {
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height  = d.height;
        int width   = d.width;

        this.ctrl = ctrl;
        
        /*-------------------------------------------------*/

        this.setSize    ( height/2, width/2 );
        this.setLocation( height/2 - this.HEIGHT, width/2 - this.WIDTH );

        this.panelPrincipal = new PanelPaint( this.ctrl );
    }


}
