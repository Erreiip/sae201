package src.common.matrice;

import src.common.Reseau;

/**
 * Classe permettant l'exportation et l'importation de {@link Reseau} dans un fichier {@code .data}.
 * @param <T> Le type de données utilisé pour l'exportation.
 */
public abstract class FormatReseau<T>
{
	private final String nom;

	public FormatReseau(String nom)
	{
		this.nom = nom;
	}

	/**
	 * Ajoute les liens du type d'objet du format au réseau.
	 * @param objet l'objet du format
	 * @param reseau le réseau à remplir
	 */
	public abstract void ajouterLiens(T objet, Reseau reseau);
	public abstract T fromReseau(Reseau r);
	public abstract T fromString(String s);
	public abstract String toString(T matrice);

	public String toString(Reseau r)
	{
		return this.toString(this.fromReseau(r));
	}
}
