package app2.ihm;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;

import app2.Controleur;

public class PanelMenu extends JFrame implements ActionListener
{
    private Controleur    ctrl;

	private JMenuItem     menuiFichierOuvrir;
	private JMenuItem     menuiFichierQuitter;


    public PanelMenu( Controleur ctrl )
    {
        this.ctrl = ctrl;
        
        this.setLayout( new BorderLayout() );


        /*-------------------------*/
		/* Création des composants */
		/*-------------------------*/

        JMenuBar menubMaBarre    = new JMenuBar();

		JMenu menuFicher         = new JMenu("Fichier");

		this.menuiFichierOuvrir  = new JMenuItem("Ouvrir");
		this.menuiFichierQuitter = new JMenuItem("Quitter");

        /*-------------------------------*/
		/* positionnement des composants */
		/*-------------------------------*/

		menuFicher  .add(this.menuiFichierOuvrir);
		menuFicher  .add(this.menuiFichierQuitter);

		menubMaBarre.add(menuFicher);

        this.add( menubMaBarre, BorderLayout.CENTER );

        /*-------------------------------*/
		/* Activation des composants     */
		/*-------------------------------*/

        this.menuiFichierOuvrir.addActionListener(this);
		this.menuiFichierQuitter.addActionListener(this);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible( true );
    }

    public void actionPerformed ( ActionEvent e )
	{

		JFileChooser fileChooser = new JFileChooser("" + new File("../") + "");

		// Création et ouverture d'un JFileChooser pour affecter

		if (e.getSource() instanceof JMenuItem )
		{
			if ( ((JMenuItem) e.getSource()).getText() == "Ouvrir" )
			{
				
				int tmp = fileChooser.showOpenDialog(this);
				if(tmp == JFileChooser.APPROVE_OPTION)
					this.ctrl.setPath ( fileChooser.getSelectedFile().getPath() );
			}

			// Fermeture de l'application
			if ( ((JMenuItem) e.getSource()).getText() == "Quitter" ) {this.dispose();}

		}
	}
}
