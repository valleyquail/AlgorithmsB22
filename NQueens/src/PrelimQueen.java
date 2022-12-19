import java.util.Arrays;

public class PrelimQueen {
    /*This is a parent class that houses all the methods for the first part of the homework. It makes it nicer because a lot of these get reused, but aren't necessary for the actual homework questions since they are limited in their functionality
     */

    protected double clock = 0;

    protected boolean notFilled(int[] board) {
        for (int i : board) {
            if (i == 0) return true;
        }
        return false;
    }

    protected boolean notValid(int[] board) {
        for (int i = 0; i < board.length - 1; i++)
            if (board[i] == 0 && board[i + 1] != 0) return true;
//        System.out.println("passes valid");
        return false;
    }

    public boolean isLegalPosition(int[] board, int queenNum) {
        if (notValid(board)) return false;
        for (int i = queenNum; i > 0; i--) {
            if (!legalHelper(board, i)) return false;
        }
        return true;
    }

    protected boolean legalHelper(int[] board, int n) {
//        System.out.println(n + " board index in board helper " + board[n - 1]);
//        System.out.println("not dead yet");
        // current queen's column
        int queen = board[n - 1];
        // if this is the starting queen, the board is legal or if no queen in this row, go to the next row
        if (n == 0 || queen == 0) return true;
        if (queen > board.length) return false;
        //check if the current queen is in a valid location
        // check the column above for queens
        for (int i = n - 2; i >= 0; i--) {
            if (board[i] == queen) return false;
        }
//        System.out.println("none above");
        // Check the upper left diagonal
        int col = queen - 2;
        int row = n - 2;
        while (col >= 0 && row >= 0) {
            if (board[row] - 1 == col) return false;
            col -= 1;
            row -= 1;
        }
//        System.out.println("none left up");
        // Check the upper right diagonal
        col = queen;
        row = n - 2;
        while (col < board.length && row >= 0) {
            if (board[row] - 1 == col) return false;
            col += 1;
            row -= 1;
        }
//        System.out.println("none right up");
        return true;
    }

    public boolean isLegalInsert(int[] board, int row) {
//        if (notValid(board)) return false;
        //Checks if the spot on the board is a valid choice
        return legalHelper(board, row);
    }

    public int[] nextLegalPosition(int[] board) {
        int index;
        for (index = 0; index < board.length; index++)
            if (board[index] == 0) break;
        if (index == 0 && board[index] == 0) return board;
        if (isLegalPosition(board, index)) {
            return nextLegalHelper(board, index);
        } else return nextLegalHelper(board, index - 1);
    }

    public int[] nextLegalHelper(int[] board, int index) {
        //Manages a fully filled board to start backtrack
        if (index < board.length) {
            board[index]++;
            if (isLegalInsert(board, index + 1)) {
                //Checks if the position is legal before trying to find the next legal position
//                System.out.println("This is a legal board");
//                System.out.println(Arrays.toString(board));
                return board;
            } else if (board[index] < board.length) {
                //Deal with if the current spot is not valid and move the queen to the right if it is available
//                System.out.println("incrementing the column");
//                System.out.println(Arrays.toString(board));
                return nextLegalHelper(board, index);
            } else {
                //Deals with a partially full board but there are no valid queens in a row
                board[index] = 0;
                board[index - 1]++;
                return nextLegalPosition(board);
            }
        } else {
            //Deals with an entirely full board
            board[index - 1] = 0;
            board[index - 2]++;
            return board;
        }
    }

    protected void printBoard(int[] board) {
        System.out.println("Board of size " + board.length + ":" + Arrays.toString(board));
        for (int i = 0; i < board.length; i++) System.out.print("___");
        System.out.println("__");
        for (int k : board) {
            System.out.print("|");
            for (int j = 0; j < board.length; j++) {
                if (j == k - 1) System.out.printf(" %c ", 'Q');
                else System.out.printf(" %c ", '~');
            }
            System.out.println("|");
        }
        for (int i = 0; i < board.length; i++) System.out.print("___");
        System.out.println("__");
    }
}