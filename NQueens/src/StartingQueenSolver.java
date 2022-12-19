import java.util.Arrays;

public class StartingQueenSolver extends PrelimQueen {

    public void firstLegalBoard(int boardSize) {
        if (boardSize == 3 || boardSize == 2)
            System.out.println("There are no possible solutions for a board this size");
        int[] board = new int[boardSize];
        board[0] = 1;
        double preTime = System.currentTimeMillis();
        board = fillBoard(board);
        clock = System.currentTimeMillis() - preTime;
        System.out.println("Board of size " + board.length + ":" + Arrays.toString(board));
//        printBoard(board);
        System.out.println("The time it took to calculate this size is: " + clock + " milliseconds\n");
//        System.out.println(isLegalPosition(board, boardSize));
    }

    public int[] fillBoard(int[] board) {
        do {
            board = nextLegalPosition(board);
//            System.out.println(Arrays.toString(board));
        } while (notFilled(board));
        return board;
    }
}