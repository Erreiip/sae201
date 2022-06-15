package src.app1.ihm.composants.tuyaux;

import src.app1.ControleurApp1;
import src.common.reseau.Reseau;
import src.common.reseau.element.Tuyau;
import src.app1.ihm.composants.GrilleReseauElementModel;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * {@link AbstractTableModel} permettant de contenir les tuyaux d'un {@link Reseau}.
 */
public class GrilleTuyauxModel extends GrilleReseauElementModel<Tuyau>
{
    private static final String[] ENTETES = new String[]{"Cuve n°1", "Cuve n°2", "Section"};

    public GrilleTuyauxModel(ControleurApp1 ctrl) { super(ctrl); }

    protected List<Tuyau> getElements() { return this.ctrl.getTuyaux(); }
    protected String[]    getEntetes()  { return GrilleTuyauxModel.ENTETES; }

    protected void initTab(int lig, Tuyau element)
    {
        this.tabDonnees[lig][0] = element.getCuve1().getIdentifiant();
        this.tabDonnees[lig][1] = element.getCuve2().getIdentifiant();
        this.tabDonnees[lig][2] = element.getSection();
    }
}
