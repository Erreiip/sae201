package src.app1.ihm.composants;

import src.app1.ControleurApp1;
import src.common.reseau.element.IReseauElement;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * {@link AbstractTableModel} permettant de gérer les grilles de données pour les éléments du réseau.
 * @param <T> type de l'élément du réseau (qui implémente l'interface {@link IReseauElement})
 */
public abstract class GrilleReseauElementModel<T extends IReseauElement> extends AbstractTableModel
{
    protected final ControleurApp1 ctrl;
    protected final Object[][]     tabDonnees;
    private   final String[]       tabEntetes;

    public GrilleReseauElementModel(ControleurApp1 ctrl)
    {
        this.ctrl = ctrl;

        List<T> elements = this.getElements();
        tabDonnees = new Object[elements.size()][4];

        for(int lig = 0; lig < elements.size(); lig++)
        {
            T element = elements.get(lig);
            this.initTab(lig, element);
        }

        this.tabEntetes = this.getEntetes();
        this.fireTableRowsInserted(elements.size() - 1, elements.size() - 1);
    }

    protected abstract List<T> getElements();
    protected abstract String[] getEntetes();
    protected abstract void initTab(int lig, T element);

    public int     getColumnCount()                              { return this.tabEntetes.length; }
    public int     getRowCount()                                 { return this.tabDonnees.length; }
    public String  getColumnName(int col)                        { return this.tabEntetes[col]; }
    public Object  getValueAt(int row, int col)                  { return this.tabDonnees[row][col]; }
    public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
}
