package ihm;

import javax.swing.*;

import java.awt.Dimension;

import Reseau;


public class FrameReseau extends JFrame 
{

    private Reseau reseau;

    private PanelPaint panelPrincipal;

    public FrameReseau ( Reseau reseau )
    {
        Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height  = d.height;
        int width   = d.width;

        this.reseau = reseau;
        
        /*-------------------------------------------------*/

        this.setSize    ( height/2, width/2 );
        this.setLocation( height/2 - this.HEIGHT, width/2 - this.WIDTH );

        this.panelPrincipal = new PanelPaint( this );
    }


}
