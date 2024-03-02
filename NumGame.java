import java.util.Random;
import java.util.Scanner;

public class NumGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 10;
        int roundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;

            System.out.println("\nI have generated a number between " + minRange + " and " + maxRange + ". Try to guess it!");

            while (attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    roundsWon++;
                    break;
                 } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
            }    else {
                    System.out.println("Too high! Try again.");
                 }
            }

            if (attempts == attemptsLimit) {
                System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + targetNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
        } 
        while (scanner.next().equalsIgnoreCase("yes"));

        System.out.println("Thanks for playing! Your total rounds won: " + roundsWon);
        scanner.close();
        
    }
}
