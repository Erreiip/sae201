package src.app1.ihm;

import src.app1.ControleurApp1;
import src.app1.ihm.composants.cuves.PanelCuves;
import src.app1.ihm.composants.tuyaux.PanelTuyaux;
import src.common.reseau.format.ReseauFormatType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;

/**
 * {@link JFrame} principale de l'application.
 */
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

    public void majListeCuves()  { this.panelCuves.majListe(); }
    public void majListeTuyaux() { this.panelTuyaux.majListe(); }
    public int getCuveActive()   { return this.panelCuves.getTblGrilleDonnees().getSelectedRow();  }
    public int getTuyauActif()   { return this.panelTuyaux.getTblGrilleDonnees().getSelectedRow(); }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.menuiFichierOuvrir)
        {
            this.ouvrir();
            this.majListeCuves();
            this.majListeTuyaux();
        }
        else if(e.getSource() == this.menuiFichierEnregistrer) this.sauvegarder();
        else if(e.getSource() == this.menuiFichierQuitter)     this.dispose();
    }

    private void sauvegarder()
    {
        JFileChooser jFileChooser = new JFileChooser(".");
        jFileChooser.setDialogTitle("Sauvegarder votre fichier");

        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            String[] types           = Arrays.stream(ReseauFormatType.values()).map(ReseauFormatType::getNom).toArray(String[]::new);
            String   typeSelectionne = (String) JOptionPane.showInputDialog(null, "Sélectionnez le format de sortie :", "Enregistrez", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            if (typeSelectionne == null)
                return;

            ReseauFormatType reseauFormatType = ReseauFormatType.get(typeSelectionne);

            this.ctrl.sauvegarderReseau(reseauFormatType, jFileChooser.getSelectedFile().getPath());
        }
    }

    private void ouvrir()
    {
        JFileChooser jFileChooser = new JFileChooser(".");
        if(jFileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File selectedFile = jFileChooser.getSelectedFile();
        if(!selectedFile.exists())
        {
            JOptionPane.showMessageDialog(null, "Le fichier sélectionné n'existe pas !", "Une erreur s'est produite !", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.ctrl.setReseau(selectedFile.getAbsolutePath());
    }
}
