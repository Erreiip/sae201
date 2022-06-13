package src.app1.ihm;

import src.app1.ControleurApp1;
import src.app1.ihm.cuves.PanelCuves;
import src.app1.ihm.tuyaux.PanelTuyaux;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FrameApp1 extends JFrame implements ActionListener
{
    private final ControleurApp1 ctrl;

    private final PanelCuves  panelCuves;
    private final PanelTuyaux panelTuyaux;

    private final JMenuItem menuiFichierOuvrir;
    private final JMenuItem menuiFichierEnregistrer;
    private final JMenuItem menuiFichierQuitter;

    public FrameApp1(ControleurApp1 ctrl)
    {
        this.ctrl = ctrl;
        this.setTitle("Créateur de cuves et tuyaux");
        this.setSize(1000, 500);
        this.setLayout(new GridLayout(2, 1));
        this.setLocationRelativeTo(null);

        /*-------------------------------*/
        /* Création des composants       */
        /*-------------------------------*/
        this.panelCuves = new PanelCuves(ctrl);
        this.panelTuyaux = new PanelTuyaux(ctrl);
        JMenuBar menuFichier = new JMenuBar();

        JMenu menu = new JMenu("Fichier");
        menu.setMnemonic('F');

        this.menuiFichierOuvrir = new JMenuItem("Ouvrir");
        this.menuiFichierOuvrir.setMnemonic('O');
        this.menuiFichierOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));

        this.menuiFichierEnregistrer = new JMenuItem("Enregistrer");
        this.menuiFichierEnregistrer.setMnemonic('E');
        this.menuiFichierEnregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        this.menuiFichierQuitter = new JMenuItem("Quitter");
        this.menuiFichierQuitter.setMnemonic('Q');
        this.menuiFichierQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));

        menu.add(this.menuiFichierOuvrir);
        menu.add(this.menuiFichierEnregistrer);
        menu.add(this.menuiFichierQuitter);

        menuFichier.add(menu);

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add(panelCuves);
        this.add(panelTuyaux);
        this.setJMenuBar(menuFichier);

        /*-------------------------------*/
        /*   Activation des composants   */
        /*-------------------------------*/
        this.menuiFichierOuvrir.addActionListener(this);
        this.menuiFichierEnregistrer.addActionListener(this);
        this.menuiFichierQuitter.addActionListener(this);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void majListeCuves()
    {
        this.panelCuves.majListe();
    }

    public int getCuveActive()
    {
        return this.panelCuves.getTblGrilleDonnees().getSelectedRow();
    }

    public void majListeTuyaux()
    {
        this.panelTuyaux.majListe();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.menuiFichierOuvrir)
        {
            this.ouvrir();
        } else if (e.getSource() == this.menuiFichierEnregistrer)
        {
            //this.panelCuves.enregistrer();
        } else if (e.getSource() == this.menuiFichierQuitter)
        {
            this.dispose();
        }
    }

    private void ouvrir()
    {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            this.ctrl.setReseau(jFileChooser.getSelectedFile().getAbsolutePath());
    }
}
