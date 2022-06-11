package src.app1.ihm;

import src.app1.ControleurApp1;
import src.app1.ihm.cuves.PanelCuves;
import src.app1.ihm.tuyaux.PanelTuyaux;

import javax.swing.*;
import java.awt.*;

public class FrameApp1 extends JFrame
{

    public FrameApp1(ControleurApp1 ctrl)
    {
        this.setTitle  ( "Créateur de cuves et tuyaux"  );
        this.setSize   ( 500, 250 );
        this.setLayout(new GridLayout(2, 1));

        /*-------------------------------*/
        /* Création des composants       */
        /*-------------------------------*/
        JPanel panelCuves  = new PanelCuves(ctrl);
        JPanel panelTuyaux = new PanelTuyaux(ctrl);


        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add ( panelCuves  );
        this.add ( panelTuyaux );

        this.setVisible ( true );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
