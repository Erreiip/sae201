package src.app1.ihm.cuves;

import src.app1.ControleurApp1;
import src.app1.Cuve;
import src.app1.IReseauElement;
import src.app1.ihm.APanelTable;

import javax.swing.*;
import java.awt.*;

public class PanelCuves extends APanelTable
{
    public PanelCuves(ControleurApp1 ctrl) {
        super(ctrl, new CuvesGrilleDonneesModel(ctrl));
    }

    @Override
    protected String getLabel() {
        return "Cuves";
    }

    @Override
    protected void ajouterElement() {

    }

    @Override
    protected void supprimerElement() {

    }
}
