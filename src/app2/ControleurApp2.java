package src.app2;

import java.util.HashMap;

import java.io.FileReader;
import java.util.Scanner;

import src.app2.ihm.*;
import src.common.Reseau;
import src.common.Tuyau;
import src.common.Cuve;


public class ControleurApp2
{
    private FrameReseau frame;
    private Reseau      metier;

	private String      pathMatrice;


    public ControleurApp2()
    {
        this.frame       = new FrameReseau( this );
        this.metier      = null;
        this.pathMatrice = null; 
    }


    public void optiCuve()
    {
        HashMap<String,Integer> voisinPref = new HashMap<>();

        for ( Cuve c : this.metier.getCuves() )
        {
            String str = "";

            for ( Tuyau t : this.metier.getTuyaux())
            {
                if ( t.getCuve1() != c && t.getCuve2() == c )
                    str += t.getCuve1().getIdentifiant();

                if ( t.getCuve1() == c && t.getCuve2() != c )
                    str += t.getCuve2().getIdentifiant();
            }

            voisinPref.put(c.getIdentifiant() + "",  str.length());
        }

        //completer
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
            boolean continuer = true;


            String   type      = Test.getType(); 

            Object[][] matrice;

            {
                String temp = Test.initScan();
                int lig     = Integer.parseInt(temp.charAt(0) + ""); 
                int col     = Integer.parseInt(temp.charAt(1) + ""); 

                matrice   = new Object[lig][col];
            }

            Scanner sc = new Scanner( new FileReader( "./source.data" ) );              
            sc.nextLine();

            for ( int lig = 0; sc.hasNextLine() && continuer; lig++ )
            {
                String str = sc.nextLine();
                continuer  = !str.equals("---");

                String[] strSplit = str.split(" ");

                for ( int col = 0; col < strSplit.length && continuer; col++ )
                {
                    matrice[lig][col] = strSplit[col];
                }
                
            }
            

            continuer = true;
            while( sc.hasNextLine() && continuer )
            {
                String str = sc.nextLine();
                continuer  = !str.equals("---");
                
                if ( continuer )
                    /*création de nouelle cuve sans position*/ System.out.println(str);
            }


            if ( type.equals("Liste d'adjacence"))
            {
                for ( int lig = 0; lig < matrice.length; lig++ )
                {
                    //création d'un tuyau entre matrice[lig][0] et matrice [lig][1]
                }
            }


            /* test pour voir la matrice */
            /*
            for ( int lig = 0; lig < matrice.length; lig++ )
            {
                for ( int col = 0; col < matrice[lig].length; col++ )
                {
                    System.out.print(matrice[lig][col] + "|");
                }
                System.out.println();
            }
            */
            
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


    private static String initScan() 
    {  
        int     lig = 0;
        int     col = 0;
        int maxCol  = 0;

        boolean continuer = true;

        try 
        {
            Scanner sc = new Scanner( new FileReader( "./source.data" ) );

            sc.nextLine();

            for ( lig = 0; sc.hasNextLine() && continuer; lig++ ) 
            { 
                String str = sc.nextLine();
                continuer = !str.equals("---");
                
                col = str.split(" ").length;

                if ( col > maxCol )
                    maxCol = col;
            }
    
            sc.close();


            //paassage en coordonnées réels de lig//
            lig--;

            return lig + "" + maxCol;
            
        } catch (Exception e) { System.out.println(e); return null;}   
    }

    
    private static String getType()
    {
        try  
        {
            Scanner sc = new Scanner( new FileReader( "./source.data" ) );

            String temp = sc.nextLine(); 

            sc.close();

            return temp;    
        } 
        catch ( Exception e ) { return null; }
    }
    

    public static void main ( String args[] )
    {
        new ControleurApp2();
    }
}
