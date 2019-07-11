package fr.diginamic.pokemon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.connexion.ConnexionManager;
import fr.diginamic.jdbc.exception.TechnicalException;

public class PokemonDao {

	public void ajouterPokemon(String nom, int idEquipe) {

		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try {

			String sql = "{call addPokemon(?, ?)}";

			CallableStatement callStmt = conn.prepareCall(sql);

			callStmt.setString(1, nom);
			callStmt.setInt(2, idEquipe);
			callStmt.execute();

			callStmt.close();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback n'a pas fonctionné", e);
			}
			throw new TechnicalException("Une erreur sur l'insertion", e);
		} finally {

			try {
				conn.setAutoCommit(true);
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

	public void supprimerPokemon(String nom, int id) {

		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try {
			conn.setAutoCommit(false);

			statement = conn.prepareStatement("DELETE FROM pokemon WHERE nom = ? and id_equipe=?");
			statement.setString(1, nom);
			statement.setInt(2, id);

			statement.executeUpdate();

			System.out.println("La suppression a bien été faite");

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback n'a pas fonctionné", e);
			}
			throw new TechnicalException("Une erreur sur la suppression", e);
		} finally {
			try {
				conn.setAutoCommit(true);
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

	public List<Pokemon> afficherPokemon(int idEquipe) {

		Connection conn = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		ResultSet curseur = null;
		List<Pokemon> listePokemon = new ArrayList<>();

		try {
			conn.setAutoCommit(false);
			statement = conn.prepareStatement("SELECT * FROM pokemon where id_equipe=?");

			statement.setInt(1, idEquipe);

			curseur = statement.executeQuery();

			while (curseur.next()) {
				int id = curseur.getInt("id");
				String nom = curseur.getString("nom");

				listePokemon.add(new Pokemon(id, nom, idEquipe));

			}

			conn.commit();
			return listePokemon;

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new TechnicalException("Le rollback n'a pas fonctionné", e);
			}
			throw new TechnicalException("Une erreur sur la suppression", e);
		} finally {
			try {
				conn.setAutoCommit(true);
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
