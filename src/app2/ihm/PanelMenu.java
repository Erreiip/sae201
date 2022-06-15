package src.app2.ihm;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

import src.app2.ControleurApp2;

/**
 * {@link JPanel} qui permet de dessiner un réseau.
 */
public class PanelMenu extends JPanel implements ActionListener
{
    private final ControleurApp2 ctrl;

    private final JMenuItem menuiFichierOuvrir;
    private final JMenuItem menuiFichierQuitter;

    public PanelMenu(ControleurApp2 ctrl)
    {
        this.ctrl = ctrl;

        this.setLayout(new BorderLayout());


        /*-------------------------*/
        /* Création des composants */
        /*-------------------------*/

        JMenuBar menubMaBarre = new JMenuBar();

        JMenu menuFicher = new JMenu("Fichier");

        this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
        this.menuiFichierQuitter = new JMenuItem("Quitter");

        /*-------------------------------*/
        /* positionnement des composants */
        /*-------------------------------*/

        menuFicher.add(this.menuiFichierOuvrir);
        menuFicher.add(this.menuiFichierQuitter);

        menubMaBarre.add(menuFicher);

        this.add(menubMaBarre, BorderLayout.CENTER);

        /*-------------------------------*/
        /* Activation des composants     */
        /*-------------------------------*/

        this.menuiFichierOuvrir.addActionListener(this);
        this.menuiFichierQuitter.addActionListener(this);


        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {

        JFileChooser fileChooser = new JFileChooser("" + new File(".") + "");
        if (e.getSource() instanceof JMenuItem)
        {
            if (e.getSource() == menuiFichierOuvrir)
            {

                int tmp = fileChooser.showOpenDialog(this);
                if (tmp == JFileChooser.APPROVE_OPTION)
                    this.ctrl.creerReseau(fileChooser.getSelectedFile().getPath());
            }

            // Fermeture de l'application
            if (e.getSource() == menuiFichierQuitter)
            {
                this.ctrl.fermer();
            }

        }
    }
}
