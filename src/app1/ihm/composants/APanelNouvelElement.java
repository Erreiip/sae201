package src.app1.ihm.composants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class APanelNouvelElement extends JPanel implements ActionListener
{

    private final APanelTable panelTable;

    private final JButton btnValider;
    private final JButton btnAnnuler;

    public APanelNouvelElement(APanelTable panelTable)
    {
        this.panelTable = panelTable;
        this.setLayout(new BorderLayout());

        JPanel panelNord = new JPanel();

        this.initComposants(panelNord);

        JPanel panelSud = new JPanel();
        this.btnValider = new JButton("Valider");
        this.btnAnnuler = new JButton("Annuler");

        panelSud.add(btnValider);
        panelSud.add(btnAnnuler);

        this.add(panelNord, BorderLayout.CENTER);
        this.add(panelSud, BorderLayout.SOUTH);

        btnValider.addActionListener(this);
        btnAnnuler.addActionListener(this);
    }

    protected abstract void initComposants(JPanel panelNord);

    protected abstract void valider();

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (this.btnValider == e.getSource()) this.valider();

        if (this.btnAnnuler == e.getSource()) this.close();
    }

    protected void close()
    {
        SwingUtilities.getRoot(this).setVisible(false);
    }

    public APanelTable getPanelTable()
    {
        return panelTable;
    }
}
