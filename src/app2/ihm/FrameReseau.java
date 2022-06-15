package src.app2.ihm;

import src.app2.ControleurApp2;

import javax.swing.*;
import java.awt.*;

public class FrameReseau extends JFrame
{
    private final ControleurApp2 ctrl;
    private final PanelPaint     panelPrincipal;
    private final PanelMenu      panelMenu;
    private final PanelAction    panelAction;

    /**
     * {@link FrameReseau} permet de d'instancier tous les panels pour compléter l'ihm.
     */
    public FrameReseau(ControleurApp2 controleurApp2)
    {
        Dimension d      = Toolkit.getDefaultToolkit().getScreenSize();
        int       height = d.height;
        int       width  = d.width;

        this.ctrl = controleurApp2;

        /*-------------------------------------------------*/

        this.setLayout(new BorderLayout());
        this.setSize(width / 2, height / 2);
        this.setLocationRelativeTo(null);

        this.panelMenu      = new PanelMenu(this.ctrl);
        this.panelPrincipal = new PanelPaint(this.ctrl);
        this.panelAction    = new PanelAction(this.ctrl);


        this.add(this.panelMenu,      BorderLayout.NORTH);
        this.add(this.panelPrincipal, BorderLayout.CENTER);
        this.add(this.panelAction,    BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void dessiner()
    {
        panelPrincipal.initPaint();
        panelPrincipal.repaint();
    }

    public void redessiner()
    {
        panelPrincipal.repaint();
    }

    public Dimension getDim()
    {
        return new Dimension(this.panelPrincipal.getWidth(), this.panelPrincipal.getHeight());
    }
}
