package app2;


import java.io.FileReader;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;


import app2.ihm.*;
import app1.Tuyau;



public class ControleurApp2
{
    private FrameReseau frame;

	private String        pathMatrice;

    public ControleurApp2()
    {
        this.frame = new FrameReseau( this );
    }

    public void setPath ( String path ) 
    { 
        this.pathMatrice = path;
        this.creerReseau(); 
    }

    public ArrayList<Tuyau> getTuyaux ()
    {

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

    private int[][] initScan() 
    {
        int     lig, col;
        int[][] matrice;

        Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );

        for ( lig = 0; sc.hasNextLine(); lig++ )
        {
            for ( col = 0; sc.hasNextInt(); col++ ) {}
        }

        matrice = new int[lig][col];

        sc.close();
        
        return matrice;
    }

    public static void main ( String args[] )
    {
        new ControleurApp2();
    }
}
