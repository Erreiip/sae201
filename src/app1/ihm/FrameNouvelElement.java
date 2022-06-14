package src.app1.ihm;

import src.app1.ihm.composants.APanelNouvelElement;

import javax.swing.*;

public class FrameNouvelElement extends JFrame
{

    public FrameNouvelElement(APanelNouvelElement APanelNouvelElement)
    {
        this.setTitle("Nouvelle cuve");
        this.setSize(300, 150);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.add(APanelNouvelElement);
    }
}