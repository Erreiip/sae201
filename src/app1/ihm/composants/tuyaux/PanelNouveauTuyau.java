package src.app1.ihm.composants.tuyaux;

import src.app1.ihm.composants.PanelNouvelElement;
import src.common.reseau.Reseau;

import javax.swing.*;
import java.awt.*;

/**
 * {@link JPanel} permettant d'ajouter un tuyau dans un {@link Reseau}.
 */
public class PanelNouveauTuyau extends PanelNouvelElement
{
    private JTextField txtSection;
    private JTextField txtCuve1;
    private JTextField txtCuve2;

    public PanelNouveauTuyau(PanelTuyaux panelTuyaux)
    {
        super(panelTuyaux);
    }

    protected void initComposants(JPanel panelNord)
    {
        panelNord.setLayout(new GridLayout(3, 2));

        JLabel lblSection = new JLabel("Section :");
        JLabel lblCuve1   = new JLabel("Cuve n°1 :");
        JLabel lblCuve2   = new JLabel("Cuve n°2 :");

        this.txtSection = new JTextField();
        this.txtCuve1 = new JTextField();
        this.txtCuve2 = new JTextField();

        panelNord.add(lblSection);
        panelNord.add(this.txtSection);

        panelNord.add(lblCuve1);
        panelNord.add(this.txtCuve1);

        panelNord.add(lblCuve2);
        panelNord.add(this.txtCuve2);
    }

    protected void valider()
    {
        try
        {
            this.getPanelTable().getCtrl().creerTuyau(
                    Integer.parseInt(this.txtSection.getText()),
                    this.txtCuve1.getText().toUpperCase().charAt(0),
                    this.txtCuve2.getText().toUpperCase().charAt(0));

            this.txtSection.setText("");
            this.txtCuve1  .setText("");
            this.txtCuve2  .setText("");
            this.close();

        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "La section doit être un nombre entier.", "Une erreur s'est produite !", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Une erreur s'est produite !", JOptionPane.ERROR_MESSAGE);
        }
    }
}
