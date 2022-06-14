package src.app1.ihm.tuyaux;

import src.app1.ControleurApp1;
import src.app1.ihm.composants.APanelTable;
import src.app1.ihm.FrameNouvelElement;
import src.app1.ihm.cuves.CuvesGrilleDonneesModel;

public class PanelTuyaux extends APanelTable
{

    private FrameNouvelElement frameNouvelElement;

    public PanelTuyaux(ControleurApp1 ctrl)
    {
        super(ctrl, new TuyauxGrilleDonneesModel(ctrl));
    }

    @Override
    protected String getLabel()
    {
        return "Tuyau";
    }

    @Override
    public void ajouterElement()
    {
        if (frameNouvelElement == null)
            frameNouvelElement = new FrameNouvelElement(new PanelNouveauTuyau(this));

        frameNouvelElement.setVisible(true);
    }

    @Override
    public void supprimerElement()
    {

    }

    public void majListe()
    {
        super.majListe(new TuyauxGrilleDonneesModel(this.getCtrl()));
    }
}
