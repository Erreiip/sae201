package src.app1.ihm.composants.cuves;

import src.app1.ControleurApp1;
import src.common.reseau.element.Cuve;
import src.app1.ihm.composants.GrilleReseauElementModel;
import src.common.reseau.Reseau;

import java.util.List;

/**
 * {@link GrilleReseauElementModel} permettant de contenir les cuves d'un {@link Reseau}.
 */
public class GrilleCuvesModel extends GrilleReseauElementModel<Cuve>
{
    private static final String[] ENTETES = new String[]{"Identifiant", "Capacité"};

    public GrilleCuvesModel(ControleurApp1 ctrl) { super(ctrl); }

    protected List<Cuve> getElements() { return this.ctrl.getCuves(); }
    protected String[]   getEntetes()  { return ENTETES; }

    protected void initTab(int lig, Cuve element)
    {
        this.tabDonnees[lig][0] = element.getIdentifiant();
        this.tabDonnees[lig][1] = element.getCapacite();
    }
}
