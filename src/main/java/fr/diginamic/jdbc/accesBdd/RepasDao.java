package fr.diginamic.jdbc.accesBdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.diginamic.jdbc.connexion.ConnexionManager;
import fr.diginamic.jdbc.exception.TechnicalException;
import fr.diginamic.jdbc.model.Repas;

public class RepasDao {

	public List<Repas> afficherRepas() {
		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		ResultSet curseur = null;
		List<Repas> listeRepas = new ArrayList<>();

		try {
			conn.setAutoCommit(false);
			statement = conn.prepareStatement("SELECT * FROM repas");
			curseur = statement.executeQuery();

			while (curseur.next()) {
				int id = curseur.getInt("id");
				String nom = curseur.getString("nom");
				int prix = curseur.getInt("prix");
				int quantite = curseur.getInt("quantite");
				String description = curseur.getString("description");

				listeRepas.add(new Repas(id, nom, prix, quantite, description));

			}

			conn.commit();
			return listeRepas;

		} catch (SQLException e) {
			throw new TechnicalException("Une erreur sur l'existence du repas", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (curseur != null) {
					curseur.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

	}

	public void ajouterRepas(Scanner scanner) {
		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		System.out.println("Veuillez saisir le nom du repas : ");
		String nom = scanner.nextLine();

		System.out.println("Veuillez saisir le prix du repas : ");
		int prix = scanner.nextInt();

		System.out.println("Veuillez saisir la quantité de repas : ");
		int quantite = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Veuillez saisir la description du repas : ");
		String description = scanner.nextLine();

		try {
			conn.setAutoCommit(false);

			statement = conn
					.prepareStatement("INSERT INTO repas (nom, prix, quantite, description) VALUES (?, ?, ?, ?)");

			statement.setString(1, nom);
			statement.setInt(2, prix);
			statement.setInt(3, quantite);
			statement.setString(4, description);

			statement.executeUpdate();

			System.out.println("Le repas est ajouté");
			conn.commit();

		} catch (SQLException e) {
			throw new TechnicalException("Une erreur sur l'insertion", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

	}

	public void modifierRepas(Scanner scanner) {
		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		System.out.println("Veuillez saisir le numéro du repas à modifier : ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Veuillez saisir le nouveau nom : ");
		String nom = scanner.nextLine();
		System.out.println("Veuillez saisir le prix : ");
		int prix = scanner.nextInt();
		System.out.println("Veuillez saisir la quantité : ");
		int quantite = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Veuillez saisir la description : ");
		String description = scanner.nextLine();

		try {
			conn.setAutoCommit(false);

			statement = conn
					.prepareStatement("UPDATE repas SET nom=?, prix = ? , quantite = ?, description = ? WHERE id = ? ");

			statement.setString(1, nom);
			statement.setInt(2, prix);
			statement.setInt(3, quantite);
			statement.setString(4, description);
			statement.setInt(5, id);

			statement.executeUpdate();

			System.out.println("la modification a bien été faite");

			conn.commit();

		} catch (SQLException e) {
			throw new TechnicalException("Une erreur sur l'update du repas", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

	}

	public void supprimerRepas(Scanner scanner) {
		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		System.out.println("Veuillez saisir le numéro du repas à supprimer : ");
		int id = scanner.nextInt();

		try {
			conn.setAutoCommit(false);

			statement = conn.prepareStatement("DELETE FROM repas WHERE id = ?");
			statement.setInt(1, id);

			statement.executeUpdate();

			System.out.println("La suppression a bien été faite");

			conn.commit();

		} catch (SQLException e) {
			throw new TechnicalException("Une erreur sur l'update du repas", e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {

				throw new TechnicalException("La fermeture ne s'est pas faite", e);
			}
		}

	}
}
