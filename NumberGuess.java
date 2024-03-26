import java.util.Scanner;

public class NumberGuess {

    //declare global scanner variable to be used across multiple methods
    static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {

        // variables that will be used for difficulty selection & customizable inputs
        int difficulty;
        int customRange;
        int customGuessCount;


        System.out.println("Welcome to gcc's number guess game!");
        System.out.println("In this game you will have to guess a number, pretty obvious. Anyways below is a choice of options to choose a difficulty. Input the number beside the difficulty name to go into that difficulty!\n");


        // Error checker to check if the difficulty you selected is a valid input.
        do {
            System.out.println("Choose your difficulty\n=====================\n1. Easy\n2. Normal\n3. Hard\n4. Insane\n5. Reverse Russian Roulette\n6. Custom\n====================\n");
            while (!INPUT.hasNextInt()) {
                System.out.println("Invalid input! Enter a number!.\n");
                System.out.println("Choose your difficulty\n=====================\n1. Easy\n2. Normal\n3. Hard\n4. Insane\n5. Reverse Russian Roulette\n6. Custom\n====================\n");
                INPUT.next();
            }
            difficulty = INPUT.nextInt();
        } while (difficulty < 1 || difficulty > 6);


        // difficulty selection
        switch (difficulty) {
            case 1:
                System.out.println("Welcome to easy mode. In this difficulty you will guess a small range of 0-50, and will have 10 tries to guess the number\nBeing the easiest difficulty, you shouldn't have a hard time, anyways have fun!!!\n");
                PlayGame(50, 10);
                break;
            case 2:
                System.out.println("Welcome to normal difficult. In this difficulty you will guess a typical range of 0-100, and will have 5 tries to guess the number.\nHave fun!\n");
                PlayGame(100, 5);
                break;
            case 3:
                System.out.println("Welcome to hard difficulty. In this difficulty you will guess in the ranges of 0-200, and will have 3 tries to guess the number\nHave fun!\n");
                PlayGame(200, 3);
                break;
            case 4:
                System.out.println("Welcome to insane difficulty, In this difficulty you will guess in the ranges of 0-500, and will have 3 tries to guess the number.\nHave fun!\n");
                PlayGame(500, 3);
                break;
            case 5:
                System.out.println("Welcome to the hardest difficulty in this number guessing game... Reverse Russian Roulette!\nAs the name suggests, it is kinda like russian roulette when guessing the number, except instead of the one bullet in the revolver that will kill you,\nthere is a bullet in every part of the revolver except one, and not getting that one will kill you(or in this case, crash your program).\nIn this difficulty, you are tasked with guessing in the ranges of 0-1000, with one chance to do so....\nGood luck, and have fun...\n");
                PlayGame(1000, 1);
                break;
            case 6:
                System.out.println("Welcome to the custom difficulty. In this difficulty you are at free will to add a custom max range and a custom guess count!\nDo as you wish!\n");

                // Error handling for customRange input
                do {
                    System.out.print("Enter a max range: ");
                    while (!INPUT.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        System.out.print("Enter a max range: ");
                        INPUT.next();
                    }
                    customRange = INPUT.nextInt();
                    if (customRange <= 0) {
                        System.out.println("Range must be a positive number. Please try again.");
                    }
                } while (customRange <= 0);

                // Error handling for customGuessCount input
                do {
                    System.out.print("Enter a custom amount of guesses: ");
                    while (!INPUT.hasNextInt()) {
                        System.out.println("Invalid input! Please enter a valid number.");
                        System.out.print("Enter a custom amount of guesses: ");
                        INPUT.next();
                    }
                    customGuessCount = INPUT.nextInt();
                    if (customGuessCount <= 0) {
                        System.out.println("Number of guesses must be a positive number. Please try again.");
                    }
                } while (customGuessCount <= 0);

                PlayGame(customRange, customGuessCount);
                break;
        } //end of difficulty selection

    } //end main


    static void PlayGame(int maxRange, int amountOfGuesses) {


        int guess = 0;
        int attemptedGuessed = 0;

        // Declare a number for the user to guess
        int randomNumber = (int) (Math.random() * maxRange) + 1; // pick a random number from 1-100, which the user will have to guess


        //While loop that checks if the input is not equal to the random number
        //or if the amount of guesses isn't 0, and if either of those cases happen, ends the program.
        while (guess != randomNumber && amountOfGuesses != 0) {
            System.out.print("I am thinking of a number from 1-" + maxRange + ", guess it if you can: ");

            // First if statement will check if the input is a number or not.
            if (INPUT.hasNextInt()) {
                guess = INPUT.nextInt();

                // Checks if the number entered is greater than the selected range.
                if (guess < 0 || guess > maxRange) {
                    System.out.println("The number you entered was not in the range of 0-" + maxRange + ", please guess the number within that range: ");
                    continue;
                }

                // Checks if the number entered is greater than or less than the random number and if so, take away the amount of guesses
                // and add an attempted guess.
                if (guess > randomNumber) {
                    System.out.println("Too high!");
                    amountOfGuesses--;
                    attemptedGuessed++;
                }
                if (guess < randomNumber) {
                    System.out.println("To low!");
                    amountOfGuesses--;
                    attemptedGuessed++;
                }
            } else {
                System.out.print("The value you entered is not valid: enter an integer: ");
                INPUT.nextLine();
            }

        }

        // If the user wins/looses, they will be prompted with either of these messages.
        if (guess == randomNumber) {
            System.out.println("Congrats, you guessed the number! the number was: " + randomNumber);
            System.out.println("You had " + amountOfGuesses + " guesses left and you guessed " + attemptedGuessed + " times!");
        } else {
            System.out.println("Ah, you ran out of numbers, very disappointing.. The correct number was " + randomNumber + ". Better luck next time!");
        }
    } //end MainGame method
}