
import java.util.Random;

public class Game {
    //Creates a new board in order to play the game
    Board board = new Board();

    //Tracks whose turn it is
    private char turn;

    //Sets the turn value
    public void setTurn(char input) {
        turn = input;
    }

    //Gets the turn value
    public char getTurn() {
        return turn;
    }

    //Checks if there are no places left
    public boolean gameNotOver() {
        return board.getGamePiecesLeft() != 0;
    }

    //Handles player moves to remove pieces
    public void playerMove(char color, int toRemove) {
        boolean check = false;
        switch (color) {
            case 'g':
                if (!board.green.noneLeft()) {
                    check = board.green.remove(toRemove);
                }
                break;
            case 'r':
                if (!board.red.noneLeft()) {
                    check = board.red.remove(toRemove);
                }
                break;
            case 'y':
                if (!board.yellow.noneLeft()) {
                    check = board.yellow.remove(toRemove);
                }
                break;
        }
        if (check) {
            //Redundant check to make sure the move was valid
            setTurn('c');
            board.updateBoard();
            System.out.println("The player has made their move\n");
        } else {
            System.out.println("Not a valid move, try again please. Here is the current board: \n");
        }

    }

    //Container method to cover both types of CPU moves
    public void CPUMove() {
        System.out.println("The CPU is making its move:\n");
        //Uses the XOR operation in order to determine if there is a winning move
        int XORResult = board.green.getNumLeft() ^ board.red.getNumLeft() ^ board.yellow.getNumLeft();
        if (XORResult != 0) {
//            System.out.println("XOR move");
            CPUWinning();
        } else {
            CPURandom();
        }
        //Sets it to be the player's turn
        setTurn('f');
    }

    private void CPURandom() {
        //A "pointer" to a piece
        Piece color = null;
        //Random index to choose a color
        Random rand = new Random();
        do {
            //Gets a random value between 1 and 3
            switch (rand.nextInt(3) + 1) {
                case 1 -> color = board.green;
                case 2 -> color = board.red;
                case 3 -> color = board.yellow;
            }
        } while (color.noneLeft());
        color.remove(rand.nextInt(color.getNumLeft()) + 1);
        board.updateBoard();
    }

    private void CPUWinning() {
        //Algorithm using XOR to determine the winning move
        int GxR = board.green.getNumLeft() ^ board.red.getNumLeft();
        int RxY = board.red.getNumLeft() ^ board.yellow.getNumLeft();
        int GxY = board.green.getNumLeft() ^ board.yellow.getNumLeft();

        if (board.green.getNumLeft() > RxY) {
            board.green.remove(board.green.getNumLeft() - RxY);
        } else if (board.red.getNumLeft() > GxY) {
            board.red.remove(board.red.getNumLeft() - GxY);
        } else if (board.yellow.getNumLeft() > GxR) {
            board.yellow.remove(board.yellow.getNumLeft() - GxR);
        } else {
            CPURandom();
        }
        board.updateBoard();
    }
}
