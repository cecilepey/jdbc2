package fr.diginamic.pokemon;

import java.util.Scanner;

public class GetRequest {

	public static void main(String[] args) throws Exception {

		GetRequest http = new GetRequest();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

	}

	// HTTP GET request
	private void sendGet() throws Exception {
		PokemonService pokeService = new PokemonService();

		String choix = "1";
		String menu = "Application Pokemon - Choisissez une fonction : \n1 - Ajouter un pokemon \n2 - Afficher une équipe \n3 - Supprimer un pokemon \n99 - sortie";

		Scanner scanner = new Scanner(System.in);

		while (!choix.equals("99")) {

			System.out.println(menu);
			choix = scanner.nextLine();

			switch (choix) {
			case "1":
				pokeService.ajouterService(scanner);
				System.out.println("");
				break;

			case "2":

				pokeService.afficherService(scanner);
				System.out.println("");

				break;

			case "3":
				pokeService.supprimerService(scanner);
				System.out.println("");
				break;

			case "99":
				System.out.println("Au revoir !");
				break;
			default:
				System.out.println("Vous n'avez pas entré un chiffre valide");
				break;
			}

		}
	}

}