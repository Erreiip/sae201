package src.app1.ihm.composants.tuyaux;

import src.app1.ControleurApp1;
import src.app1.ihm.composants.PanelTableElement;
import src.app1.ihm.FrameNouvelElement;
import src.common.reseau.Reseau;

import javax.swing.*;

/**
 * {@link JPanel} permettant d'ajouter (en ouvrant un {@link PanelNouveauTuyau}),
 * supprimer et modifier les tuyaux d'un {@link Reseau}.
 */
public class PanelTuyaux extends PanelTableElement
{
    private static final String LABEL = "Tuyau";

    private FrameNouvelElement frameNouvelElement;

    public PanelTuyaux(ControleurApp1 ctrl) { super(ctrl, new GrilleTuyauxModel(ctrl)); }

    protected String getLabel() { return LABEL; }

    public void ajouterElement()
    {
        if (frameNouvelElement == null)
            frameNouvelElement = new FrameNouvelElement(new PanelNouveauTuyau(this));

        frameNouvelElement.setVisible(true);
    }

    public void supprimerElement()
    {
        this.getCtrl().supprimerTuyau();
    }
    public void majListe()         { super.majListe(new GrilleTuyauxModel(this.getCtrl())); }
}
