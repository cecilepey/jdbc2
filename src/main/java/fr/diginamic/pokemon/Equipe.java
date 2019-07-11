package fr.diginamic.pokemon;

public class Equipe {

	private int id;
	private String name;

	/**
	 * Constructeur
	 * 
	 * @param id
	 * @param name
	 */
	public Equipe(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
