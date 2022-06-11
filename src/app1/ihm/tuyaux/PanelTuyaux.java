package src.app1.ihm.tuyaux;

import src.app1.ControleurApp1;
import src.app1.ihm.AGrilleDonneesModel;
import src.app1.ihm.APanelTable;

import javax.swing.*;

public class PanelTuyaux extends APanelTable {


    public PanelTuyaux(ControleurApp1 ctrl) {
        super(ctrl, new TuyauxGrilleDonneesModel(ctrl));
    }

    @Override
    protected String getLabel() {
        return null;
    }

    @Override
    protected void ajouterElement() {

    }

    @Override
    protected void supprimerElement() {

    }
}
