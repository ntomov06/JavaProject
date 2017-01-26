import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class HangmanGame {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Въведете своето име.");
		String name = scanner.nextLine();
		boolean areWePlaying = true;
		int timePlaying = 1;
		while (areWePlaying) {
			if (timePlaying == 1) {
				System.out.printf("Здравей, %s, това е играта Бесеница.", name);
				System.out.println(" Играта представлява познаване на думи, които са само градове от България.");
				System.out.println();
				System.out.println("___________________________________________");
				System.out.println(
						" Преди да започнем, ще ти разкажа за начина на игра и командите, които можеш да използваш по време на игра.");
				System.out.println(" След като си въвел името си, ще избера дума, която ти трябва да познаещ.");
				System.out.println(" Имаш право на 6 грешни опита, преди да бъдеш обесен и да загубиш.");
				System.out
						.println(" Ако искаш да прекратиш играта и да спреш да играеш натисни \"-\" и играта ще спре.");
				System.out.println(" Приятна игра!");
				System.out.println("___________________________________________");
			}

			char[] randomWordToGuess = chosingWord();
			int amountOfFalseGuesses = 0;
			char[] playerGuess = new char[randomWordToGuess.length];
			playerGuess = fillingCharArray(playerGuess, randomWordToGuess);
			boolean wordIsGuessed = false;
			while (!wordIsGuessed && amountOfFalseGuesses < 6) {
				char input = gettingLetter(playerGuess);
				if (input == '-') {
					areWePlaying = false;
					wordIsGuessed = true;
				} else {
					for (int i = 0; i < randomWordToGuess.length; i++) {
						if (randomWordToGuess[i] == input) {
							playerGuess[i] = input;
						}
					}
					if (isSearchingWordContainsInput(randomWordToGuess, input) == false) {
						amountOfFalseGuesses++;
					}

					drawingHangman(amountOfFalseGuesses, randomWordToGuess);
					if (amountOfFalseGuesses == 6) {
						System.out.println(randomWordToGuess);
					}
					if (isTheWordGuessed(playerGuess)) {
						wordIsGuessed = true;
						System.out.println(" Поздравления, ти спечели!");
						System.out.println(playerGuess);
						areWePlaying = false;
					}
				}
			}
			areWePlaying = askingForAnotherGame();
			timePlaying++;
		}
	}

	public static char[] chosingWord() throws FileNotFoundException {
		ArrayList<String> cities = new ArrayList<String>();

		Scanner scannerFile = new Scanner(new File("src/Cities.txt"));
		while (scannerFile.hasNextLine()) {
			cities.add(scannerFile.nextLine());
		}
		scannerFile.close();
		Random randomCity = new Random();
		int index = randomCity.nextInt(cities.size());
		String city = cities.get(index).toLowerCase();
		char[] randomWordToGuess = city.toCharArray();
		return randomWordToGuess;
	}

	public static char[] fillingCharArray(char[] playerGuess, char[] chosingWord) {
		for (int i = 0; i < chosingWord.length; i++) {
			if (chosingWord[i] != ' ') {
				playerGuess[i] = '_';
			} else {
				playerGuess[i] = ' ';
			}
		}
		return playerGuess;
	}

	public static char gettingLetter(char[] playerGuess) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(" Познати букви:");
		printArray(playerGuess);
		System.out.println(" Въведи буква");
		char input = scanner.nextLine().charAt(0);
		return input;

	}

	public static void printArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static boolean isTheWordGuessed(char[] array) {
		boolean isTheWordGuessed = true;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '_') {
				isTheWordGuessed = false;
			}
		}
		return isTheWordGuessed;
	}

	public static boolean isSearchingWordContainsInput(char[] guesses, char input) {
		boolean isContains = false;
		int timesOfAppears = 0;
		for (int i = 0; i < guesses.length; i++) {
			if (guesses[i] == input) {
				timesOfAppears++;
			}
		}
		if (timesOfAppears > 0) {
			isContains = true;
		}
		return isContains;
	}

	public static void drawingHangman(int amountOfFailTries, char[] randomWordToGuess) throws FileNotFoundException {
		if (amountOfFailTries == 1) {
			firstFailTry();
		} else if (amountOfFailTries == 2) {
			secondFailTry();
		} else if (amountOfFailTries == 3) {
			thirdFailTry();
		} else if (amountOfFailTries == 4) {
			fourthFailTry();
		} else if (amountOfFailTries == 5) {
			fifthFailTry();
		} else if (amountOfFailTries == 6) {
			sixthFailTry();
		}
	}

	public static void firstFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|");
		System.out.println("|");
		System.out.println("|");
		System.out.println(" Имаш още 5 грешни опита!");
	}

	public static void secondFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|     |");
		System.out.println("|");
		System.out.println("|");
		System.out.println(" Имаш още 4 грешни опита");
	}

	public static void thirdFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|");
		System.out.println("|");
		System.out.println("|");
		System.out.println(" Имаш още 3 грешни опита");
	}

	public static void fourthFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|");
		System.out.println("|");
		System.out.println(" Имаш още 2 грешни опита");
	}

	public static void fifthFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|    /");
		System.out.println("|");
		System.out.println(" Имаш още 1 грешен опит");
	}

	public static void sixthFailTry() throws FileNotFoundException {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|    / \\");
		System.out.println("|");
		System.out.print(" Загуби! Думата е ...");
	}

	public static boolean askingForAnotherGame() {
		boolean areWePlaying = true;
		System.out.println(" Нова игра?(да/не)");
		Scanner scanner = new Scanner(System.in);
		String anotherGame = scanner.nextLine();
		if (anotherGame.equals(" не")) {
			System.out.println(" Играта приключи.");
			areWePlaying = false;
		}
		return areWePlaying;
	}
}
