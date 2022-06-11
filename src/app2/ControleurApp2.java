package src.app2;

import java.util.HashMap;

import java.io.FileReader;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import src.app2.ihm.*;
import src.app1.Reseau;
import src.app1.Tuyau;
import src.app1.Cuve;


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


    public ArrayList<Tuyau> getTuyaux()
    {
        int cpt = 0;
        Tuyau c;

        ArrayList<Tuyau> lstTuyaus = new ArrayList<Tuyau>();

        do {
            c = this.metier.getTuyau(cpt);
            
            if ( c != null )
                lstTuyaus.add(c);

            cpt++;

        } while ( c != null );
        
        return lstTuyaus;
    }


    public ArrayList<Cuve> getCuves()
    {
        int cpt = 0;
        Cuve c;

        ArrayList<Cuve> lstCuve = new ArrayList<Cuve>();

        do {
            c = this.metier.getCuve((char) (cpt + 'A'));
            
            if ( c != null )
                lstCuve.add(c);

            cpt++;

        } while ( c != null );
        
        return lstCuve;
    }


    public boolean creerReseau()
    {
        try
        {
            int[][]  matrice   = initScan();

            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );
            
            String type = sc.nextLine();
            

            for ( int lig = 0; sc.hasNextLine() && !sc.nextLine().equals("---"); lig++ )
            {
                for ( int col = 0; sc.hasNextInt(); col++ )
                {
                    matrice[lig][col] = sc.nextInt();
                }
                
            }

            //a completer
            if ( type.equals("Matrice Adjacente"))
            {
        
            }

        } 
        catch (Exception e) 
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    private int[][] initScan() 
    {  
        int     lig, col;
        int[][] matrice;

        try 
        {
            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );

            sc.nextLine();

            for ( lig = 0; sc.hasNextLine() && !sc.nextLine().equals("---"); lig++ )
            {
                for ( col = 0; sc.hasNextInt(); col++ ) 
                    sc.nextInt();
            }
    
            matrice = new int[lig][col];
    
            sc.close();
            
        } catch (Exception e) { System.out.println(e); return null;}

        return matrice;
        
    }

    public static void main ( String args[] )
    {
        new ControleurApp2();
    }
}
