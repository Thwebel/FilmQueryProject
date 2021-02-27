package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		db.findFilmsByText("cat");

		List<Film> films = db.findFilmsByText("Cat");
		for (Film film : films) {
			System.out.println(film.userToString());
			System.out.println();
		}
		System.out.println(films.size());

	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean runningInterface = true;
		start:
		while (runningInterface) {

			welcome();
			menu();
			switch (chooseSearchType(input)) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					System.out.println("Thanks for browsing, hope to see you again soon!");
					break start;
			}
			
		}
	}
	
	private void seachById(Scanner input) {
		
	}

	private void welcome() {
		System.out.println(
				"	Welcome to Film Store™! \nPlease enjoy browsing our library, our franchise carries 1000 films.");

	}

	private int chooseSearchType(Scanner input) {
		boolean selectingSearch = true;
		int searchOption = 0;
		
		while (selectingSearch) {
			searchOption = getUserInt(input);
			if (0 < searchOption && searchOption <= 3) {
				break;
			} else {
				System.out.println("Apologies, but only options 1, 2, and 3 are accepted here.");
				menu();
			}
		}
		return searchOption;
	}

	private int getUserInt(Scanner input) {
		int userSeachSelection = 0;
		System.out.println("Enter Number: ");
		while (userSeachSelection == 0) {
			try {
				userSeachSelection = input.nextInt();
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("I'm sorry, input must be a whole number that is not zero. E.g 1, 2, or 3");
				continue;
			}
			if(userSeachSelection == 0) {
				System.out.println("I'm sorry, input must be a whole number that is not zero. E.g 1, 2, or 3");
			}

		}
		return userSeachSelection;
	}

	private void menu() {
		System.out.println("----------------------------------------");
		System.out.println("|      Select Search Option            |");
		System.out.println("|--------------------------------------|");
		System.out.println("|1.) Film ID Number                    |");
		System.out.println("|2.) Seach by Text                     |");
		System.out.println("|3.) Quit Program.                     |");
		System.out.println("----------------------------------------");

	}

}
