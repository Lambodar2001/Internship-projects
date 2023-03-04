/*
 *
# Oasis Infobyte task:2
#Student name: Lambodar waghmare 

The system generates a random number from a given range, say 1 to 100.
The user is prompted to enter their given number in a displayed dialogue box.
The computer then tells if the entered number matches the guesses number or it is higher/lower than the generated number.
The game continues under the user guessing the number.

You can also incorporate further details as:

Limiting the number of attempts.
Adding more rounds.
Displaying score.

Giving points based on the number of attempts.

 */


 import java.util.Scanner;
 import java.util.Random;
 
 public class GuessTheNumber{
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         Random random = new Random();
         
         int lowerLimit = 1;
         int upperLimit = 100;
         System.out.println("Welcome to the Guess The Number game!");
         System.out.println("Please enter the lower limit for the random number: ");
         lowerLimit = scanner.nextInt();
         System.out.println("Please enter the upper limit for the random number: ");
         upperLimit = scanner.nextInt();
         int systemInput = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
         int noOfGuesses = 0;
         
         while (noOfGuesses < 10) {
             System.out.print("Guess the number between " + lowerLimit + " and " + upperLimit + ": ");
             int userInput = scanner.nextInt();
             noOfGuesses++;
             
             if (systemInput == userInput) {
                 System.out.println("Congratulations, you guessed the number " + systemInput +
                     " in " + noOfGuesses + " guesses");
                 
                 int score = 100 - (noOfGuesses - 1) * 10;
                 System.out.println("Your score is " + score);
                 
                 break;
             }
             else if (userInput > systemInput) {
                 System.out.println("Too High");
                 upperLimit = userInput - 1;
             }
             else {
                 System.out.println("Too Low");
                 lowerLimit = userInput + 1;
             }
             
             if (noOfGuesses == 10) {
                 System.out.println("Number of attempts finished... Better luck next time");
             }
             else {
                 System.out.println("The number is between " + lowerLimit + " and " + upperLimit);
             }
         }
         
         scanner.close();
     }
 }
 