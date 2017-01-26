import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

//Methods we need 
// generating a random number
//1st method for choosing a word
//2nd method for inputing a players guess
//3rd comparing the chosen word with with letters that players inputs
// 4thmethod for drawing a picture of hangedman and counting left tries
//
public class HangmanGame {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("�������� ������ ���.");
	    String name = scanner.nextLine();
		boolean areWePlaying = true;
		while (areWePlaying) {
			System.out.printf("�������,%s , ���� � ������ ��������.", name);
			System.out.println("������ ������������ ��������� �� ���� , ����� �� ���� ������� �� ��������.");
			System.out.println();
			System.out.println("___________________________________________");
			System.out.println("����� �� ��������, �� �� ������� �� ������ �� ���� � ������� , ����� ����� �� ��������� �� ����� �� ����.");
			System.out.println(
					"���� ���� �� ����� ����� �� , �� ������ ���� , ����� �� ������ �� �������.");
			System.out.println(
					"���� ����� �� 6 ������ ����� , ����� �� ����� ������ � �� �������.");
			System.out.println(
					"��� ����� �� ��������� ������ � �� ����� �� ������ ������� \"-\" � ������ �� ����.");
			System.out.println("������� ����!");
			System.out.println("___________________________________________");

			char[] randomWordToGuess = chosingWord();
			int amountOfFalseGuesses = 0;
			char[] playerGuess = new char[randomWordToGuess.length];
			playerGuess = fillingCharArray(playerGuess,chosingWord());
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
					;
					drawingHangman(amountOfFalseGuesses);
					if (isTheWordGuessed(playerGuess)) {
						wordIsGuessed = true;
						System.out.println("Congrats, you won!");
						areWePlaying = false;
					}
				}
			}
			areWePlaying=askingForAnotherGame();
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
			if (chosingWord[i]==' '){

				playerGuess[i] = ' ';}
			else {
			playerGuess[i] = '_';
			}
		}
		return playerGuess;
	}

	public static char gettingLetter (char[] playerGuess){ 
		Scanner scanner= new Scanner(System.in);
		System.out.print("������� �����:");
		printArray(playerGuess);
		System.out.println("������ �����");
		char input = scanner.nextLine().charAt(0);
		while(checkingInputs(input)==false){
			System.out.println("�� ��� ������� ����� , ���� �������� ������");
			scanner.nextLine().charAt(0);
		}
		return input;
		
	}
	public static boolean checkingInputs (char input) {
		 boolean isItCorrect = true;
		if (input<'�'||input>'�'&&input!='-'&&input!=' '){
			isItCorrect=false;
		}
		return isItCorrect;
	}
	public static void printArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static boolean isTheWordGuessed(char[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '_') {
				return false;
			}
		}
		return true;
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

	public static void drawingHangman(int amountOfFailTries) throws FileNotFoundException {
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
		System.out.println("���� ��� 5 ������ �����!");
	}

	public static void secondFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|     |");
		System.out.println("|");
		System.out.println("|");
		System.out.println("���� ��� 4 ������ �����");
	}

	public static void thirdFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|");
		System.out.println("|");
		System.out.println("|");
		System.out.println("���� ��� 3 ������ �����");
	}

	public static void fourthFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|");
		System.out.println("|");
		System.out.println("���� ��� 2 ������ �����");
	}

	public static void fifthFailTry() {
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|    /");
		System.out.println("|");
		System.out.println("���� ��� 1 ������ ����");
	}

	public static void sixthFailTry() throws FileNotFoundException{
		System.out.println(" _____");
		System.out.println("|/    |");
		System.out.println("|     o");
		System.out.println("|    \\|/");
		System.out.println("|    / \\");
		System.out.println("|");
		System.out.print("������! ������ � ..." );
		System.out.println(chosingWord());
	}

	public static boolean askingForAnotherGame() {
		boolean areWePlaying= true;
		System.out.println("    ���� ����?(��/��)");
		Scanner scanner = new Scanner(System.in);
		String anotherGame = scanner.nextLine();
		if (anotherGame.equals("��")) {
			System.out.println("������ ��������.");
			areWePlaying = false;
		}
		return areWePlaying;
	}

}
