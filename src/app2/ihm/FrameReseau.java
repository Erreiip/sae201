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
        
        this.setLayout(new BorderLayout());
        this.setSize    ( height/2, width/2 );
        this.setLocation( height/2 - this.HEIGHT, width/2 - this.WIDTH );

        this.panelMenu      = new PanelMenu ( this.ctrl );
        this.panelPrincipal = new PanelPaint( this.ctrl );

        this.add( this.panelMenu     , BorderLayout.NORTH  );
        this.add( this.panelPrincipal, BorderLayout.CENTER );

    }
    


}
