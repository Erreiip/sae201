package src.common;

import src.common.reseau.fichier.FichierReseau;
import src.common.reseau.format.ReseauFormatType;

public class Tests
{
    public static void main(String[] args)
    {
        Tests.lancerTests();
    }

    private static void lancerTests()
    {/* 
        try
        {
            Reseau reseau = Tests.creerReseauSujet();

            Tests.testerCuves();
            Thread.sleep(1000);

            System.out.println(reseau);
            Thread.sleep(1000);

            Tests.fichierReseauInput();
            Thread.sleep(1000);

            Tests.fichierReseauOutput(reseau);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
*/
        try
        {
            Reseau reseau = Tests.creerReseauEtude();

            //Tests.testerCuves();
            //Thread.sleep(1000);
/*
            System.out.println(reseau);
            Thread.sleep(1000);
*/
            Tests.fichierReseauInput();
            Thread.sleep(1000);

            Tests.fichierReseauOutput(reseau);

        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void testerCuves()
    {
        Reseau reseau = new Reseau();

        try
        {
            throw new RuntimeException(reseau.creerCuve(100) + " doit être égal à null");
        } catch (Exception e)
        {
            System.out.println("Test OK : création cuve de capacité trop basse (" + e.getMessage() + ")");
        }

        try
        {
            throw new RuntimeException(reseau.creerCuve(3000) + " doit être égal à null");
        } catch (Exception e)
        {
            System.out.println("Test OK : création cuve de capacité trop haute (" + e.getMessage() + ")");
        }

        Cuve cuveA;

        cuveA = reseau.creerCuve(200);
        System.out.println("Test OK : création cuve de capacité correcte");

        try
        {
            cuveA.retirerContenu(100);
        } catch (Exception e)
        {
            System.out.println("Test OK : retrait de contenu à une cuve vide (" + e.getMessage() + ")");
        }
        cuveA.ajouterContenu(100);
        System.out.println("Test OK : ajout de contenu à une cuve");

        try
        {
            cuveA.ajouterContenu(1000);
        } catch (Exception e)
        {
            System.out.println("Test OK : ajout trop important de contenu à une cuve (" + e.getMessage() + ")");
        }
    }

    private static Reseau creerReseauSujet()
    {
        Reseau reseau = new Reseau();

        Cuve cuveA = reseau.creerCuve(1000);
        Cuve cuveB = reseau.creerCuve(900);
        Cuve cuveC = reseau.creerCuve(200);
        Cuve cuveD = reseau.creerCuve(700);

        cuveA.ajouterContenu(500);
        cuveB.ajouterContenu(190);

        reseau.creerTuyau(2, cuveA, cuveB);
        reseau.creerTuyau(6, cuveA, cuveC);
        reseau.creerTuyau(4, cuveB, cuveC);
        reseau.creerTuyau(8, cuveB, cuveD);

        return reseau;
    }

    private static Reseau creerReseauEtude()
    {
        Reseau reseau = new Reseau();

        Cuve cuveA = reseau.creerCuve(1000);
        Cuve cuveB = reseau.creerCuve(500);
        Cuve cuveC = reseau.creerCuve(200);

        cuveA.ajouterContenu(1000);
        cuveB.ajouterContenu(500);
        cuveC.ajouterContenu(195);

        reseau.creerTuyau(2, cuveA, cuveB);
        reseau.creerTuyau(6, cuveA, cuveC);
        reseau.creerTuyau(4, cuveB, cuveC);

        return reseau;
    }

    private static void fichierReseauInput()
    {
        System.out.println(FichierReseau.fromString("""
                couts
                ===============
                X 2 6 X
                2 X 4 8
                6 4 X X
                X 8 X X
                ===============
                1000
                900
                200
                700""").getReseau());

        System.out.println(FichierReseau.fromString("""
                couts_opti
                ===============
                2
                6 4
                X 8 X
                ===============
                1000
                900
                200
                700""").getReseau());


        System.out.println(FichierReseau.fromString("""
                liste_adja
                ===============
                AB
                AC
                BC
                BD
                -----
                2
                6
                4
                8
                ===============
                1000
                900
                200
                700""").getReseau());
    }

    private static void fichierReseauOutput(Reseau reseau)
    {
        for (ReseauFormatType format : ReseauFormatType.values())
        {
            FichierReseau fichier = new FichierReseau(format, reseau);
            System.out.println(fichier);
        }
    }
}
