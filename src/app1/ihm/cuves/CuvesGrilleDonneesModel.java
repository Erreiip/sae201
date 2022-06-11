package src.app1.ihm.cuves;

import src.app1.ControleurApp1;
import src.app1.Cuve;
import src.app1.ihm.AGrilleDonneesModel;

import java.util.List;

public class CuvesGrilleDonneesModel extends AGrilleDonneesModel<Cuve> {

    public CuvesGrilleDonneesModel(ControleurApp1 ctrl) {
        super(ctrl);
    }

    @Override
    protected List<Cuve> getElements() {
        return this.ctrl.getCuves();
    }

    @Override
    protected String[] getEntetes() {
        return new String[] { "Identifiant", "Capacité", "X", "Y" };
    }

    @Override
    protected void initTab(int lig, Cuve element) {
        this.tabDonnees[lig][0] = element.getIdentifiant();
        this.tabDonnees[lig][1] = element.getCapacite();
        this.tabDonnees[lig][2] = element.getX();
        this.tabDonnees[lig][3] = element.getY();
    }
}
