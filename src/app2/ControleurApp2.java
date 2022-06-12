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
        this.metier      = new Reseau();

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

    public Reseau getMetier() { return this.metier; }


    public boolean creerReseau()
    {
        boolean continuer = true;
        String   type      = this.getType(); 

        Cuve[]     tabCuves;
        Object[][] matrice;

        {
            String temp = this.initScan(type);
            int lig     = Integer.parseInt(temp.charAt(0) + ""); 
            int col     = Integer.parseInt(temp.charAt(1) + ""); 

            matrice   = new Object[lig][col];
            tabCuves  = new Cuve  [col];
        }


        try
        {

            Scanner sc = new Scanner( new FileReader( "./source3.data" ) );              
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

                
            if ( type.equals("Liste d'adjacence"))
            {
                System.out.println(type);
                int lig = 0;
                continuer = true;
                while( sc.hasNextLine() && continuer )
                {
                    String str = sc.nextLine();
                    continuer  = !str.equals("---");
                    

                    if ( continuer )
                        matrice[lig][matrice[lig].length-1] = str;
                    
                    lig++;
                }
            } 


            if ( type.equals("Matrice de couts opti")) { matrice = this.replacement( matrice ); }

            
            int cpt = 0;
            continuer = true;
            while( sc.hasNextLine() && continuer )
            {
                String str = sc.nextLine();
                continuer  = !str.equals("---");
                
                if ( continuer )
                    tabCuves[cpt] = this.metier.creerCuve(Integer.parseInt(str)); 
                
                cpt++;
            }


            //Création du réseau pour la matrice des couts (opti et non)//
            if ( type.equals("Matrice des couts")  )
            { 
                for ( int lig = 0; lig < matrice.length; lig++ )
                {
                    for ( int col = 0; col < matrice[lig].length; col++ )
                    {
                        if ( matrice[lig][col] != null ) 
                            this.metier.creerTuyau( Integer.parseInt((String)matrice[lig][col]), tabCuves[lig], tabCuves[col]);
                    }
                }
            }


            /* test pour voir la matrice */
            for ( int lig = 0; lig < matrice.length; lig++ )
            {
                for ( int col = 0; col < matrice[lig].length; col++ )
                {
                    System.out.print((matrice[lig][col] != null?matrice[lig][col]:" ") + "|");
                }
                System.out.println();
            }
            
        } catch (Exception e) { e.printStackTrace(); return false; }


        return true;
    }


    private Object[][] replacement( Object[][] matrice)
    {
        Object[][] tabRet = new Object[matrice.length][matrice[0].length];
        
        for ( int lig = 0; lig < matrice.length; lig++ )
        {
            for ( int col = 0; col < matrice[lig].length; col++ )
            {
                if (matrice[lig][col] != null)
                    tabRet[lig][col + lig] = matrice[lig][col];
            }
        }

        return tabRet;
    }


    private String initScan( String type ) 
    {  
        int     lig = 0;
        int     col = 0;
        int maxCol  = 0;

        boolean continuer = true;

        try 
        {
            Scanner sc = new Scanner( new FileReader( "./source3.data" ) );

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

            if ( type.equals("Liste d'adjacence"))  maxCol++;

            return lig + "" + maxCol;
            
        } catch (Exception e) { System.out.println(e); return null;}   
    }

    
    private String getType()
    {
        try  
        {
            Scanner sc = new Scanner( new FileReader( "./source3.data" ) );

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
