package fr.diginamic.pokemon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PokemonService {

	private final String USER_AGENT = "Mozilla/5.0";

	PokemonDao pokeDao = new PokemonDao();

	public void afficherService(Scanner scanner) {
		System.out.println("Choisissez l'équipe 1 - 2 ou 3 :");
		int idEquipe = scanner.nextInt();
		scanner.nextLine();
		List<Pokemon> listePokemon = pokeDao.afficherPokemon(idEquipe);
		for (Pokemon liste : listePokemon) {
			System.out.println(liste.getNom());

		}

	}

	public void supprimerService(Scanner scanner) {
		System.out.println("Quel pokemon voulez-vous supprimer : ");
		String nom = scanner.nextLine();
		System.out.println("Dans l'équipe 1 - 2 ou 3:  ");
		int idEquipe = scanner.nextInt();
		scanner.nextLine();
		pokeDao.supprimerPokemon(nom, idEquipe);

	}

	public void ajouterService(Scanner scanner) throws Exception {

		Random random = new Random();
		int numeroPokemon;

		System.out.println("Veuillez choisir l'équipe 1 - 2 ou 3 :");
		int id = scanner.nextInt();
		scanner.nextLine();
		int pourcentage = random.nextInt(100) + 1;

		if (pourcentage > 60) {

			numeroPokemon = random.nextInt(500) + 1;
			String url = "https://pokeapi.co/api/v2/pokemon/" + numeroPokemon + "/";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JsonObject jsonObj2 = new Gson().fromJson(response.toString(), JsonObject.class);

			String pokeName = jsonObj2.get("name").getAsString();
			System.out.println("Nom du pokemon : " + pokeName);

			pokeDao.ajouterPokemon(pokeName, id);
		}

		else {
			System.out.println("Le pokemon s'est échapé !");
		}
	}
}
