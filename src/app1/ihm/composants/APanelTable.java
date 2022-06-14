package src.app1.ihm.composants;

import src.app1.ControleurApp1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class APanelTable extends JPanel implements ActionListener
{

    private final ControleurApp1 ctrl;
    private final JTable         tblGrilleDonnees;
    private final JScrollPane    spGrilleDonnees;
    private final JButton        btnCreerElement;
    private final JButton        btnSupprimerElement;

    public APanelTable(ControleurApp1 ctrl, AGrilleDonneesModel<?> grilleDonneesModel)
    {
        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());

        /*-------------------------------*/
        /* Création des composants       */
        /*-------------------------------*/
        this.tblGrilleDonnees = new JTable(grilleDonneesModel);
        this.tblGrilleDonnees.setFillsViewportHeight(true);
        this.tblGrilleDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.spGrilleDonnees = new JScrollPane(this.tblGrilleDonnees);

        JPanel panelBas = new JPanel();

        this.btnCreerElement = new JButton("Créer " + this.getLabel());
        this.btnSupprimerElement = new JButton("Supprimer " + this.getLabel());

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/

        panelBas.add(this.btnCreerElement);
        panelBas.add(this.btnSupprimerElement);

        this.add(this.spGrilleDonnees, BorderLayout.CENTER);
        this.add(panelBas, BorderLayout.SOUTH);

        /*-------------------------------*/
        /* Activation des composants     */
        /*-------------------------------*/
        this.btnCreerElement.addActionListener(this);
        this.btnSupprimerElement.addActionListener(this);
    }

    protected abstract String getLabel();

    protected abstract void ajouterElement();

    protected abstract void supprimerElement();

    public ControleurApp1 getCtrl()
    {
        return ctrl;
    }

    public JTable getTblGrilleDonnees()
    {
        return tblGrilleDonnees;
    }

    public JButton getBtnCreerElement()
    {
        return btnCreerElement;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnCreerElement) ajouterElement();
        else if (e.getSource() == this.btnSupprimerElement) supprimerElement();
    }

    public void majListe(AGrilleDonneesModel<?> model)
    {
        this.tblGrilleDonnees.setModel(model);
    }
}
