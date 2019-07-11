package fr.diginamic.jdbc.accesBdd;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.jdbc.model.Repas;

public class GestionRepas {

	public static void main(String[] args) {

		RepasDao repasDao = new RepasDao();

		String menu = "Appuyer sur le chiffre correpondant à la fonction : \n1 - afficher les repas\n2 - ajouter un repas\n3 - modifier un repas\n4 - supprimer un repas\n99 - sortie \n";

		String choix = "1";

		Scanner scanner = new Scanner(System.in);

		while (!choix.equals("99")) {

			System.out.println(menu);
			choix = scanner.nextLine();

			switch (choix) {
			case "1":
				List<Repas> listeRepas = repasDao.afficherRepas();

				for (Repas liste : listeRepas) {
					System.out.println(liste.getId() + " - nom : " + liste.getNom() + " - prix : " + liste.getPrix()
							+ " - quantité : " + liste.getQuantite() + " - description : " + liste.getDescription());

				}

				System.out.println("");
				break;

			case "2":
				repasDao.ajouterRepas(scanner);
				System.out.println("");
				break;

			case "3":
				repasDao.modifierRepas(scanner);
				System.out.println("");
				break;

			case "4":
				repasDao.supprimerRepas(scanner);
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