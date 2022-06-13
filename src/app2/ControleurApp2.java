package src.app2;

import java.util.HashMap;
import java.util.Map;

import java.awt.Dimension;

import java.io.FileReader;
import java.util.Scanner;

import src.app2.ihm.*;
import src.common.Reseau;
import src.common.Tuyau;
import src.common.Cuve;
import src.common.PositionInfos;


public class ControleurApp2
{
    private FrameReseau frame;
    private Reseau      metier;
	private String      pathMatrice;


    public ControleurApp2()
    {
        this.metier      = new Reseau();
        this.frame       = new FrameReseau( this );
        this.pathMatrice = null; 
    }

    public void dessiner() { this.frame.dessiner(); }


    public void placementCuves()
    {
        Cuve[] tabCuves = new Cuve[this.metier.getCuves().size()];
        
        Map<String,Integer> voisinPref = new HashMap<String,Integer>();

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


        int taille = voisinPref.size();
        for ( int cpt = 0; cpt < taille; cpt++)
        {
            Map.Entry<String, Integer> maxEntry = null;

            for (Map.Entry<String, Integer> entry : voisinPref.entrySet()) {
                if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
                }
            }

            tabCuves[cpt] = this.metier.getCuve(maxEntry.getKey().charAt(0));
            
            voisinPref.remove( maxEntry.getKey() );
        }

        
        int       heightUnique;
        int       widthUnique;
        int       ancienneWidth, ancienneHeight;

        {
            Dimension temp   = this.frame.getDim();
            int       height = temp.height;
            int       width  = temp.width;

            heightUnique     = height  / (int) Math.ceil(tabCuves.length  / (double) 2);
            widthUnique      = width   / 4;

            heightUnique -= heightUnique / Math.ceil(tabCuves.length  / (double) 2);
            widthUnique  -= widthUnique / 4;


        }

        ancienneWidth  = widthUnique;
        ancienneHeight = heightUnique;
        
        int i= 2;
        for ( int cpt = 1; cpt <= tabCuves.length; cpt++)
        {

            tabCuves[cpt - 1].setX(widthUnique);
            tabCuves[cpt - 1].setY(heightUnique);

            if ( ( cpt % 2 ) == 0                           ) tabCuves[cpt - 1].setPositionInfos( PositionInfos.DROITE );
            if ( (( cpt % 2 ) != 0) && cpt != 1 && cpt != 2 ) tabCuves[cpt - 1].setPositionInfos( PositionInfos.GAUCHE );
            if (   cpt == tabCuves.length                   ) tabCuves[cpt - 1].setPositionInfos( PositionInfos.BAS    );
            if (   cpt == 1  || cpt == 2                    ) tabCuves[cpt - 1].setPositionInfos( PositionInfos.HAUT   );


            if ( ( cpt % 2 ) == 0 ) 
            {
                if ( (i % 2) == 0) widthUnique  =  ancienneWidth * 2;  
                else               widthUnique  =  ancienneWidth;
                 
                heightUnique += ancienneHeight;

                i++;
            }
            else
            {
                widthUnique += ancienneWidth * 2;
            }
        }
    }


    public void setPath ( String path ) 
    { 
        this.pathMatrice = path;
        if ( this.creerReseau() ) 
            this.frame.dessiner();
    }

    public Reseau getMetier() { return this.metier;   }
    public void   fermer()    { this.frame.dispose(); }


    public boolean creerReseau()
    {
        this.metier = new Reseau();

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

            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );              
            sc.nextLine();

            for ( int lig = 0; sc.hasNextLine() && continuer; lig++ )
            {
                String str = sc.nextLine();
                continuer  = !str.equals("---");

                String[] strSplit = str.split(" +");

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
            if ( type.equals("Matrice de couts") || type.equals("Matrice de couts opti") )
            { 
                for ( int lig = 0; lig < matrice.length; lig++ )
                {
                    for ( int col = 0; col < matrice[lig].length; col++ )
                    {
                        try {
                            this.metier.creerTuyau( Integer.parseInt((String)matrice[lig][col]), tabCuves[lig], tabCuves[col]);
                        }catch(Exception e){}
                    }
                }
            }


            this.placementCuves();

            /* test pour voir la matrice */
            /*
            for ( int lig = 0; lig < matrice.length; lig++ )
            {
                for ( int col = 0; col < matrice[lig].length; col++ )
                {
                    System.out.print((matrice[lig][col] != null?matrice[lig][col]:" ") + "|");
                }
                System.out.println();
            }
            */
            
        } catch (Exception e) { e.printStackTrace(); return false; }


        return true;
    }


    private String initScan( String type ) 
    {  
        int     lig = 0;
        int     col = 0;
        int maxCol  = 0;

        boolean continuer = true;

        try 
        {
            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );

            sc.nextLine();

            for ( lig = 0; sc.hasNextLine() && continuer; lig++ ) 
            { 
                String str = sc.nextLine();
                continuer = !str.equals("---");
                
                col = str.split(" +").length;

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
            Scanner sc = new Scanner( new FileReader( this.pathMatrice ) );

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
