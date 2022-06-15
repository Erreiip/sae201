package src.app1.ihm.composants.cuves;

import src.app1.ControleurApp1;
import src.app1.ihm.composants.PanelTableElement;
import src.app1.ihm.FrameNouvelElement;
import src.common.reseau.Reseau;

import javax.swing.*;

/**
 * {@link JPanel} permettant d'ajouter (en ouvrant un {@link PanelNouvelleCuve}),
 * supprimer et modifier les cuves d'un {@link Reseau}.
 */
public class PanelCuves extends PanelTableElement
{
    private static final String LABEL = "Cuve";

    private FrameNouvelElement frameNouvelElement;

    public PanelCuves(ControleurApp1 ctrl)
    {
        super(ctrl, new GrilleCuvesModel(ctrl));
    }

    protected String getLabel() { return LABEL; }

    protected void ajouterElement()
    {
        if (frameNouvelElement == null)
            frameNouvelElement = new FrameNouvelElement(new PanelNouvelleCuve(this));

        frameNouvelElement.setVisible(true);
    }

    protected void supprimerElement()
    {
        this.getCtrl().supprimerCuve();
    }

    public void majListe()
    {
        this.getBtnCreerElement().setEnabled(this.getCtrl().getCuves().size() < this.getCtrl().getNbCuvesMax());
        super.majListe(new GrilleCuvesModel(this.getCtrl()));
    }
}
