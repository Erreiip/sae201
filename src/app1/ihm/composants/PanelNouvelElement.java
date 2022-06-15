package src.app1.ihm.composants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@link JPanel} permettant de créer un panel contenant
 * un bouton et un champ de texte pour la création d'un nouvel élément du réseau.
 */
public abstract class PanelNouvelElement extends JPanel implements ActionListener
{
    private final PanelTableElement panelTable;
    private final JButton btnValider;
    private final JButton btnAnnuler;

    public PanelNouvelElement(PanelTableElement panelTable)
    {
        // Attributs de classe
        this.panelTable = panelTable;
        this.btnValider = new JButton("Valider");
        this.btnAnnuler = new JButton("Annuler");

        // Initialisation des composants locals
        JPanel panelNord = new JPanel();
        JPanel panelSud  = new JPanel();

        // Paramétrage du panel
        this.setLayout(new BorderLayout());

        // Ajout des composants
        this.initComposants(panelNord);

        panelSud.add(btnValider);
        panelSud.add(btnAnnuler);
        this    .add(panelNord, BorderLayout.NORTH);
        this    .add(panelSud,  BorderLayout.SOUTH);

        // Ajout des listeners
        btnValider.addActionListener(this);
        btnAnnuler.addActionListener(this);
    }

    protected abstract void initComposants(JPanel panelNord);
    protected abstract void valider();

    public void actionPerformed(ActionEvent e)
    {
        if(this.btnValider == e.getSource()) this.valider();
        if(this.btnAnnuler == e.getSource()) this.close();
    }

    protected void              close()         { SwingUtilities.getRoot(this).setVisible(false);}
    public    PanelTableElement getPanelTable() { return panelTable; }
}
