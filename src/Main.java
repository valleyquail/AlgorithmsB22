import java.util.*;

public class Main {

    public static void main(String[] args) {
        String singleWinner;
        String totalWinner;
        int numOfGames = 0;
        int CPUWins = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Welcome to "Double Trouble", otherwise known as NIM. This game runs based on the
                optimal winning strategy proof demonstrated by C. Bouton. Please enjoy playing!
                How many games would you like to play (at least 1 and odd numbers only)""");
        do {
            try {
                numOfGames = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Not a valid number of games");
                //Clears the scanner input
                scanner.next();
                continue;
            }
            if (numOfGames % 2 == 0) {
                System.out.println("Not a valid number of games");
            }
        } while (numOfGames <= 0 || numOfGames % 2 == 0);

        int gameCounter = numOfGames;
        while (gameCounter != 0) {
            Game game = new Game();
            System.out.println("Game " + (numOfGames - gameCounter + 1) + " out of " + numOfGames + "\n");
            System.out.println("Please type " +
                    "either \"f\" or \"s\" to decide if you would like to go first or let the CPU move first");
            char turnDecision = 'f';
            do {
                try {
                    turnDecision = scanner.next().charAt(0);
                } catch (
                        InputMismatchException e) {
                    System.out.println(
                            "Invalid input: please retype your decision");
                }
            } while (!(turnDecision == 'f' || turnDecision == 's'));

            game.setTurn(turnDecision);
            System.out.println("Here is the initial board setup: \n");
            game.board.printBoard();
            while (game.gameNotOver()) {
                if (game.getTurn() == 'f') {
                    System.out.println("Please enter a color (g, r, y) and the number of that color to remove." +
                            "\nEnter the desired move in the following format: g 2");
                    char color = 'a';
                    int toRemove = 0;
                    do {
                        try {
                            color = scanner.next().charAt(0);
//                        System.out.println(color);
                            toRemove = scanner.nextInt();
//                        System.out.println(toRemove);
                        } catch (InputMismatchException e) {
                            System.out.println("Please try entering your choice again\n");
                        }
                        if (!(color == 'g' || color == 'r' || color == 'y') || toRemove == 0) {
                            System.out.println("Please try entering your choice again\n");
                        }
                    } while (!(color == 'g' || color == 'r' || color == 'y') || toRemove == 0);
                    game.playerMove(color, toRemove);
                } else {
                    game.CPUMove();
                }
                game.board.printBoard();
            }
            if (game.getTurn() == 'f') {
                CPUWins++;
                singleWinner = "CPU";
            } else {
                singleWinner = "Player";
            }
            System.out.println("The winner of this round of the game is the " + singleWinner + "!!!\n");
            gameCounter--;
            if (numOfGames - CPUWins < CPUWins && numOfGames != 1) {
                System.out.println("The CPU has already won a majority of the games.");
                break;
            }
        }

        if (numOfGames - CPUWins < CPUWins) {
            totalWinner = "CPU";
        } else {
            totalWinner = "Player";
        }
        System.out.println("Out of " + numOfGames + " games played:\n" +
                "\tPlayer wins:\t" + (numOfGames - CPUWins) + "\n" +
                "\tCPU wins:   \t" + CPUWins +
                "\n\nMeaning the " + totalWinner + " is the winner!!!!");

    }

}
