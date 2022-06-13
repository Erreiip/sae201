package src.common.reseau.format;

import src.common.Reseau;

/**
 * Classe permettant l'exportation et l'importation de {@link Reseau} dans un fichier {@code .data}.
 * @param <T> Le type de données utilisé pour l'exportation.
 */
public interface ReseauFormat<T>
{
	/**
	 * Ajoute les liens du type d'objet du format au réseau.
	 * @param objet l'objet du format
	 * @param reseau le réseau à remplir
	 */
	void   ajouterLiens(T objet, Reseau reseau);
	T      fromReseau(Reseau r);
	T      fromString(String s);
	String toString(T objet);

	default void ajouterLiens(String s, Reseau r)
	{
		this.ajouterLiens(this.fromString(s), r);
	}

	default String toString(Reseau r)
	{
		return this.toString(this.fromReseau(r));
	}
}
