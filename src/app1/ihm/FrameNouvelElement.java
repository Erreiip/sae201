package src.app1.ihm;

import src.app1.ihm.composants.PanelNouvelElement;
import src.common.reseau.Reseau;

import javax.swing.*;

/**
 * {@link JPanel} permettant d'ajouter une cuve dans un {@link Reseau}.
 * @see PanelNouvelElement
 * @see src.app1.ihm.composants.tuyaux.PanelNouveauTuyau
 * @see src.app1.ihm.composants.cuves.PanelNouvelleCuve
 */
public class FrameNouvelElement extends JFrame
{

    public FrameNouvelElement(PanelNouvelElement APanelNouvelElement)
    {
        this.setTitle("Nouvelle cuve");
        this.setSize(300, 150);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(APanelNouvelElement);
    }
}