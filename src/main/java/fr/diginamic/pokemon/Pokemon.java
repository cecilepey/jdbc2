package fr.diginamic.pokemon;

public class Pokemon {

	private int id;
	private String nom;
	private int idEquipe;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param nom
	 * @param idEquipe
	 */
	public Pokemon(int id, String nom, int idEquipe) {
		super();
		this.id = id;
		this.nom = nom;
		this.idEquipe = idEquipe;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the idEquipe
	 */
	public int getIdEquipe() {
		return idEquipe;
	}

	/**
	 * Setter
	 * 
	 * @param idEquipe
	 *            the idEquipe to set
	 */
	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

}
