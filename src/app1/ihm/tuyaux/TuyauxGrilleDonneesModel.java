package src.app1.ihm.tuyaux;

import src.app1.ControleurApp1;
import src.common.Tuyau;
import src.app1.ihm.AGrilleDonneesModel;

import java.util.List;

public class TuyauxGrilleDonneesModel extends AGrilleDonneesModel<Tuyau> {

    public TuyauxGrilleDonneesModel(ControleurApp1 ctrl) {
        super(ctrl);
    }

    @Override
    protected List<Tuyau> getElements() {
        return this.ctrl.getTuyaux();
    }

    @Override
    protected String[] getEntetes() {
        return new String[] { "Cuve n°1", "Cuve n°2", "Section" };
    }

    @Override
    protected void initTab(int lig, Tuyau element) {
        this.tabDonnees[lig][0] = element.getCuve1().getIdentifiant();
        this.tabDonnees[lig][1] = element.getCuve1().getIdentifiant();
        this.tabDonnees[lig][2] = element.getSection();
    }
}
