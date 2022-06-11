package src.app1.ihm;

import src.app1.ControleurApp1;
import src.app1.IReseauElement;

import javax.swing.*;
import java.awt.*;

public abstract class APanelTable extends JPanel
{

    private final ControleurApp1 ctrl;
    private final JTable       tblGrilleDonnees;
    private final JScrollPane  spGrilleDonnees;
    public APanelTable(ControleurApp1 ctrl, AGrilleDonneesModel<?> grilleDonneesModel)
    {
        this.ctrl = ctrl;

        /*-------------------------------*/
        /* Création des composants       */
        /*-------------------------------*/
        this.tblGrilleDonnees = new JTable ( grilleDonneesModel );
        this.tblGrilleDonnees.setFillsViewportHeight(true);
        this.tblGrilleDonnees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.spGrilleDonnees   = new JScrollPane( this.tblGrilleDonnees );

        /*-------------------------------*/
        /* Positionnement des composants */
        /*-------------------------------*/
        this.add ( this.spGrilleDonnees, BorderLayout.CENTER );
    }

    protected abstract String getLabel();

    protected abstract void ajouterElement();
    protected abstract void supprimerElement();

    protected ControleurApp1 getCtrl()
    {
        return ctrl;
    }
}
