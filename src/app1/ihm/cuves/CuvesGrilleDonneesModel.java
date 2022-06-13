package src.app1.ihm.cuves;

import src.app1.ControleurApp1;
import src.common.Cuve;
import src.app1.ihm.composants.AGrilleDonneesModel;

import java.util.List;

public class CuvesGrilleDonneesModel extends AGrilleDonneesModel<Cuve>
{

    public CuvesGrilleDonneesModel(ControleurApp1 ctrl)
    {
        super(ctrl);
    }

    @Override
    protected List<Cuve> getElements()
    {
        return this.ctrl.getCuves();
    }

    @Override
    protected String[] getEntetes()
    {
        return new String[]{"Identifiant", "Capacité"};
    }

    @Override
    protected void initTab(int lig, Cuve element)
    {
        this.tabDonnees[lig][0] = element.getIdentifiant();
        this.tabDonnees[lig][1] = element.getCapacite();
    }
}
