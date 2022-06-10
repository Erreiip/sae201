package app2;

import java.io.FileReader;
import java.util.Scanner;

import app2.ihm.*;


public class Controleur
{
    private FrameReseau frame;

	private String        pathMatrice;


    public Controleur()
    {
        this.frame = new FrameReseau( this );
    }

    public void setPath ( String path ) 
    { 
        this.pathMatrice = path;
        this.creerReseau(); 
    }

    public boolean creerReseau()
    {
        try
        {
            int[][]  matrice = initScan();

            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );
            
            String type = sc.nextLine();
            

            for ( int lig = 0; sc.hasNextLine(); lig++ )
            {
                for ( int col = 0; sc.hasNextInt(); col++ )
                {
                    matrice[lig][col] = sc.nextInt();
                }
            }

            if ( type.equals("Matrice Adjacente"))
            {
        
            }

        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return false;
        }
    }

    public static void main ( String args[] )
    {
        new Controleur();
    }
}
