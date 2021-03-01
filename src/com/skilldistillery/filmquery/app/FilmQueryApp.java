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

	private DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    	app.test();
		app.launch();
	}

	private void test() {

//		List<Film> films = db.findFilmsByText("Deep Silver");
//		for (Film film : films) {
//			System.out.println(film.getGenre());
//			System.out.println();
//		}
//		System.out.println(films.size());
//		System.out.println(db.findFilmById(23).getGenre());
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean runningInterface = true;
		start: while (runningInterface) {

			welcome();
			menu();
			switch (chooseSearchType(input)) {
			case 1:
				seachFilmById(input);
				break;
			case 2:
				seachFilmByText(input);
				break;
			case 3:
				System.out.println("Thanks for browsing, hope to see you again soon!");
				break start;
			}

		}
	}

	private void welcome() {
		System.out.println(
				"	Welcome to Film Store™! \nPlease enjoy browsing our library, our franchise carries 1000 films.");

	}

	private String getUserString(Scanner input) {
		boolean gettingString = true;
		System.out.println("Enter Text: ");
		String userStr = input.nextLine();
		return userStr;
	}

	private boolean ensureYesOrNo(Scanner input) {
		boolean gettingBool = true;
		boolean searchAgain = false;
		while (gettingBool) {
			String userBool = getUserString(input).toLowerCase().trim();
			if (userBool.equals("true") || userBool.equals("yes") || userBool.equals("y") || userBool.equals("yeah")
					|| userBool.equals("please") || userBool.equals("oaky")|| userBool.equals("sí")) {
				searchAgain = true;
				gettingBool = false;

			} else if (userBool.equals("false") || userBool.equals("no") || userBool.equals("n") || userBool.equals("nope")
					|| userBool.equals("stop") || userBool.equals("exit")|| userBool.equals("end")) {
				searchAgain = false;
				gettingBool = false;

			} else {
				System.out.println("Input not recognized, try entering: Yes or No.");

			}

		}
		return searchAgain;
	}

	private int getUserInt(Scanner input) {
		int userSeachSelection = 0;
		while (userSeachSelection == 0) {
			try {
				System.out.println("Enter Number: ");
				userSeachSelection = input.nextInt();
				input.nextLine();
			} catch (InputMismatchException e) {
				input.nextLine();
				System.out.println("I'm sorry, input must be a whole number that is not zero. E.g 1, 2, or 3\n");
				continue;
			}
			if (userSeachSelection == 0) {
				System.out.println("I'm sorry, input must be a whole number that is not zero. E.g 1, 2, or 3\n");
			}

		}
		return userSeachSelection;
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

	private int getUserFilmId(Scanner input) {
		boolean gettingFilmId = true;
		int searchID = 0;

		while (gettingFilmId) {
			searchID = getUserInt(input);
			if (0 < searchID) {
				break;
			} else {
				System.out.println("Apologies, but only whole numbers greater than zero are allowed. E.g. 1, 16, or 42");
			}
		}
		return searchID;
	}

	private void seachFilmById(Scanner input) {
		boolean seachingById = true;
		while (seachingById) {
			System.out.println("\nPlease input a Film Id Number");
			int filmId = getUserFilmId(input);
			Film foundFilm = db.findFilmById(filmId);

			if (foundFilm == null) {
				System.out.println("No Film was found with that Id Number.");
			} else {
				System.out.println();
				System.out.println(foundFilm.userToString());
				System.out.println();
			}
			System.out.println("Film Id searched: "+filmId);
			System.out.println();
			System.out.println("Search again by Id? (yes / no)");
			seachingById = ensureYesOrNo(input);

		}

	}
	private void seachFilmByText(Scanner input) {
		boolean seachingByText = true;
		while (seachingByText) {
			System.out.println("\nPlease input keywords you would like to search for.");
			System.out.println("(Hint: avoid words like \"The\" or \"A\" as the search will return Films using those common words)");
			String userTextSearch = getUserString(input);
			List<Film> foundFilms = db.findFilmsByText(userTextSearch);
			
			if (foundFilms == null || foundFilms.size() == 0) {
				System.out.println("No films were found matching those key words.");
			} else {
				for (Film film : foundFilms) {
					System.out.println(film.userToString() + "\n");
				}
			}
			
			System.out.println("Keywords searched: " + userTextSearch + ". Results Found: " + foundFilms.size());
			System.out.println();
			System.out.println("Search again by Text? (yes / no)");
			seachingByText = ensureYesOrNo(input);
			
		}
		
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
