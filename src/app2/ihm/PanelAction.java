package src.app2.ihm;

import src.app2.ControleurApp2;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Color;

import java.awt.event.*;


public class PanelAction extends JPanel implements ActionListener
{
    private final ControleurApp2 ctrl;

    private final JTextField txtCuve;
    private final JTextField txtContenu;
    private final JButton    btnAction;
    private final JButton    btnSupprimer;
    private final JButton    btnInitAll;

    private final JButton btnIte;
    private final JButton btnContinue;

    private final JLabel lblInfo;

    private final JLabel lblIteration;

    private int nbIteration;

    private ThreadIterations thIterations;
    private boolean          thStart;

    public PanelAction(ControleurApp2 ctrl)
    {
        this.ctrl = ctrl;

        this.thIterations = null;
        this.thStart = false;


        this.setLayout(new BorderLayout());


        JPanel panelContenu = new JPanel(new BorderLayout(3, 3));
        JPanel panelText    = new JPanel(new GridLayout(2, 2));
        JPanel panelBtn     = new JPanel(new GridLayout(3, 1));

        JLabel lblCuve    = new JLabel("Identifiant Cuve : ", JLabel.RIGHT);
        JLabel lblContenu = new JLabel("Contenu : ", JLabel.RIGHT);

        this.txtCuve      = new JTextField();
        this.txtContenu   = new JTextField();
        this.btnAction    = new JButton("Ajouter contenu");
        this.btnSupprimer = new JButton("Supprimer contenu");
        this.btnInitAll   = new JButton("Tout ajouter du contenu");


        JPanel panelIteration = new JPanel(new GridLayout(2, 1));

        this.btnContinue = new JButton("Itération Continue");
        this.btnIte = new JButton("Itération suivante");

        this.lblInfo = new JLabel("", JLabel.CENTER);


        this.nbIteration = 0;
        this.lblIteration = new JLabel("Nombres d'itérations : " + this.nbIteration, JLabel.CENTER);


        panelIteration.add(this.btnIte);
        panelIteration.add(this.btnContinue);

        panelText.add( lblCuve );
        panelText.add( this.txtCuve );
        panelText.add( lblContenu );
        panelText.add( this.txtContenu );

        panelBtn.add(this.btnSupprimer);
        panelBtn.add(this.btnAction);
        panelBtn.add(this.btnInitAll);

        panelContenu.add(panelText   , BorderLayout.NORTH);
        panelContenu.add(panelBtn    , BorderLayout.CENTER);
        panelContenu.add(this.lblInfo, BorderLayout.SOUTH);


        this        .add(panelContenu     , BorderLayout.NORTH);
        this        .add(this.lblIteration, BorderLayout.CENTER);
        this        .add(panelIteration   , BorderLayout.SOUTH);

        this.btnAction   .addActionListener(this);
        this.btnInitAll  .addActionListener(this);
        this.btnSupprimer.addActionListener(this);
        this.btnContinue .addActionListener(this);
        this.btnIte      .addActionListener(this);


    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnAction)
        {
            try
            {
                this.ctrl.getMetier().getCuve(this.txtCuve.getText().charAt(0)).ajouterContenu(Integer.parseInt(this.txtContenu.getText()));
                this.lblInfo.setText( this.txtContenu.getText() + " ajouté à " + this.txtCuve.getText() );
                this.ctrl.redessiner();
            } catch (Exception ex)
            {
                this.lblInfo.setText("Erreur");
            }

            this.txtContenu.setText("");
            this.txtCuve.setText("");

        }

        if ( e.getSource() == this.btnSupprimer )
        {
            try
            {
                this.ctrl.getMetier().getCuve(this.txtCuve.getText().charAt(0)).retirerContenu(Integer.parseInt(this.txtContenu.getText()));
                this.lblInfo.setText( this.txtContenu.getText() + " retiré à " + this.txtCuve.getText() );
                this.ctrl.redessiner();
            } catch (Exception ex)
            {
                this.lblInfo.setText("Erreur");
            }

            this.txtContenu.setText("");
            this.txtCuve.setText("");
        }

        if (e.getSource() == this.btnInitAll)
        {
            this.ctrl.getMetier().setAllCuve();
            this.ctrl.redessiner();
        }

        if (e.getSource() == this.btnIte)
        {
            this.ctrl.tranverser();
            this.nbIteration++;
            this.lblIteration.setText("Nombre d'itérations : " + this.nbIteration);
        }

        if (e.getSource() == this.btnContinue)
        {

            if (!this.thStart)
            {
                this.thStart = true;
                this.thIterations = new ThreadIterations();
                this.thIterations.start();
                this.btnContinue.setBackground(Color.GREEN);
            } else
            {
                this.thStart = false;
                this.thIterations.stop();
                this.thIterations = null;
                this.btnContinue.setBackground(null);
            }
        }
    }

    private class ThreadIterations extends Thread
    {
        public void run()
        {
            while (PanelAction.this.thStart)
            {
                try
                {
                    Thread.sleep(1000);
                    PanelAction.this.ctrl.tranverser();
                    PanelAction.this.nbIteration++;
                    PanelAction.this.lblIteration.setText("Nombre d'itérations : " + PanelAction.this.nbIteration);
                } catch (Exception e)
                {
                }

            }
        }
    }
}


