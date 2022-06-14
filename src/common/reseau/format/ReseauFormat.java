package src.common.reseau.format;

import src.common.Reseau;

/**
 * Classe permettant l'exportation et l'importation de {@link Reseau} dans un fichier {@code .data}.
 */
public interface ReseauFormat
{
    void ajouterTuyaux(String s, Reseau r);

    String toString(Reseau r); // TODO : renommer en "construireString"
}
