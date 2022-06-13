package src.app1.ihm.cuves;

import src.app1.ControleurApp1;
import src.app1.ihm.AGrilleDonneesModel;
import src.app1.ihm.APanelTable;
import src.app1.ihm.cuves.popup.FrameNouvelElement;
import src.app1.ihm.cuves.popup.PanelNouvelleCuve;

public class PanelCuves extends APanelTable
{

    private FrameNouvelElement frameNouvelElement;

    public PanelCuves(ControleurApp1 ctrl) {
        super(ctrl, new CuvesGrilleDonneesModel(ctrl));
    }

    @Override
    protected String getLabel() {
        return "Cuves";
    }

    @Override
    protected void ajouterElement() {
        if(frameNouvelElement == null) {
            frameNouvelElement = new FrameNouvelElement(new PanelNouvelleCuve(this));
        }

        frameNouvelElement.setVisible(true);
    }

    @Override
    protected void supprimerElement() {
        this.
    }

    public void majListe() {
        super.majListe(new CuvesGrilleDonneesModel(this.getCtrl()));
    }
}
