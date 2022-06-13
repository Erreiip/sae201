package src.app1.ihm.cuves.popup;

import javax.swing.*;

public class FrameNouvelElement extends JFrame
{

    public FrameNouvelElement(APanelNouvelElement APanelNouvelElement)
    {
        this.setTitle("Nouvelle cuve");
        this.setSize(300, 200);

        this.setResizable(false);
        this.add(APanelNouvelElement);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
