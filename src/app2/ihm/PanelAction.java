package src.app2.ihm;

import src.app2.ControleurApp2;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.*;

public class PanelAction extends JPanel implements ActionListener
{
    private ControleurApp2 ctrl;

    private JTextField txtCuve;
    private JTextField txtContenu;
    private JButton    btnAction;
    private JButton    btnInitAll;

    private JButton    btnIte;
    private JButton    btnContinue;

    private JLabel     lblInfo;

    private JLabel     lblIteration;

    private int        nbIteration;

    public PanelAction( ControleurApp2 ctrl )
    {
        this.ctrl = ctrl;

        this.setLayout( new BorderLayout() );


        JPanel panelContenu = new JPanel( new BorderLayout(3,3) );
        JPanel panelText    = new JPanel( new GridLayout( 2, 2) );
        JPanel panelBtn     = new JPanel( new GridLayout( 2, 1) );

        JLabel lblCuve     = new JLabel( "Identifiant Cuve : ", JLabel.RIGHT );
        JLabel lblContenu  = new JLabel( "Contenu : "         , JLabel.RIGHT );

        this.txtCuve    = new JTextField();
        this.txtContenu = new JTextField();
        this.btnAction  = new JButton( "Valider");
        this.btnInitAll = new JButton( "Tout ajouter du contenu" );


        JPanel panelIteration = new JPanel(new GridLayout(2,1));

        this.btnContinue  = new JButton("Itération Continue");
        this.btnIte       = new JButton("Itération suivante");

        this.lblInfo      = new JLabel("", JLabel.CENTER);


        this.nbIteration  = 0;
        this.lblIteration = new JLabel("Nombres d'itérations : " + this.nbIteration, JLabel.CENTER);


        panelIteration.add( this.btnIte      );
        panelIteration.add( this.btnContinue );

        panelText   .add  ( lblCuve          );
        panelText   .add  ( this.txtCuve     );
        panelText   .add  ( lblContenu       );
        panelText   .add  ( this.txtContenu  );

        panelBtn    .add  ( this.btnAction   );
        panelBtn    .add  ( this.btnInitAll  );

        panelContenu.add  ( panelText     , BorderLayout.NORTH  );
        panelContenu.add  ( panelBtn      , BorderLayout.CENTER );
        panelContenu.add  ( this.lblInfo  , BorderLayout.SOUTH  );


        this.add( panelContenu     , BorderLayout.NORTH );
        this.add( this.lblIteration, BorderLayout.CENTER);
        this.add( panelIteration   , BorderLayout.SOUTH );

        this.btnAction  .addActionListener( this );
        this.btnInitAll .addActionListener( this );
        this.btnContinue.addActionListener( this );
        this.btnIte     .addActionListener( this );


    }

    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource() == this.btnAction )
        {
            try 
            { 
                this.ctrl.getMetier().getCuve(this.txtCuve.getText().charAt(0)).ajouterContenu(Integer.parseInt(this.txtContenu.getText())); 
                this.lblInfo.setText( "Contenu changé" );
                this.ctrl.redessiner();
            } catch (Exception ex) { 
                this.lblInfo.setText("Erreur"); 
            }

            this.txtContenu.setText("");
            this.txtCuve   .setText("");

        }

        if ( e.getSource() == this.btnInitAll )
        {
            this.ctrl.getMetier().setAllCuve();
            this.ctrl.redessiner();
        }

        if ( e.getSource() == this.btnIte )
        {
            this.ctrl.tranverser();
            this.nbIteration++;
            this.lblIteration.setText( "Nombre d'itérations : " + this.nbIteration);
        }
    }    
}


