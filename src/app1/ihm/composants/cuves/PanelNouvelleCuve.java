package src.app1.ihm.composants.cuves;

import src.app1.ihm.composants.PanelNouvelElement;
import src.common.reseau.Reseau;

import javax.swing.*;
import java.awt.*;

/**
 * {@link JPanel} permettant d'ajouter une cuve d'un {@link Reseau}.
 */
public class PanelNouvelleCuve extends PanelNouvelElement
{

    private JTextField txtCapacite;

    public PanelNouvelleCuve(PanelCuves panelCuves)
    {
        super(panelCuves);
    }

    @Override
    protected void initComposants(JPanel panelNord)
    {
        panelNord.setLayout(new GridLayout(2, 1));

        JLabel lblCapacite = new JLabel("Capacité:");
        this.txtCapacite = new JTextField();
        this.txtCapacite.requestFocus();

        panelNord.add(lblCapacite);
        panelNord.add(txtCapacite);
    }

    @Override
    protected void valider()
    {
        try
        {
            this.getPanelTable().getCtrl().creerCuve(Integer.parseInt(this.txtCapacite.getText()));
            this.txtCapacite.setText("");
            this.close();

        } catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(null, "La capacité doit être un nombre entier", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Une erreur s'est produite !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
