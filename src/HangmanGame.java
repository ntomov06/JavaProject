import java.util.Scanner;
import java.util.Random;

public class HangmanGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		System.out.println("Enter your name.");
		String name = scanner.nextLine();
		String[] guesses = { "asd", "dsadsa", "dwqds", "sad" };
		boolean areWePlaying = true;
		while (areWePlaying) {
			System.out.printf("Welcome to my Hangman game , %s. Let's the game begin.", name);
			System.out.println();
			char[] randomWordToGuess = guesses[random.nextInt(guesses.length)].toCharArray();
			int amountOfGuesses = randomWordToGuess.length;
			char[] playerGuess = new char[amountOfGuesses];
			for (int i = 0; i < playerGuess.length; i++) {
				playerGuess[i] = '_';
			}
			boolean wordIsGuessed = false;
			int tries = 0;
			while (!wordIsGuessed && tries != amountOfGuesses) {
				System.out.print("Current guesses :");
				printArray(playerGuess);
				System.out.printf("You have %d tries left.\n" , amountOfGuesses-tries);
				System.out.println("Enter a letter");
				char input = scanner.nextLine().charAt(0);
				tries++;
				if (input=='-'){
					areWePlaying=false;
					wordIsGuessed=true;
				} else {
					for (int i=0;i<randomWordToGuess.length;i++){
						if (randomWordToGuess[i]==input){
							playerGuess[i]=input;
						}
					}
				}
				if (isTheWordGuessed(playerGuess)){
					wordIsGuessed = true ;
					System.out.println("Congrats, you won!");
					areWePlaying=false;
				}
			}
			if (!wordIsGuessed){
				System.out.println("You ran out of guesses.");
				areWePlaying=false;
			}
		}
		System.out.println("Do you want to play another game? (yes/no)");
		String anotherGame=scanner.nextLine();
		if (anotherGame.equals("yes")){
			areWePlaying=true;
		}
		System.out.println("Game over.");
	}

	public static void printArray(char[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	public static boolean isTheWordGuessed(char[] array){
		for (int i = 0; i < array.length; i++) {
			if (array[i]=='_'){
				return false;
			}
		}
		return true;
	}
}
