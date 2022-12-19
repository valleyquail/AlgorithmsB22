public class AllQueens extends PrelimQueen {

    private int numSolutions = 0;

    public void allSolutions(int boardSize) {

        numSolutions =0;
        if (boardSize == 3 || boardSize == 2)
            System.out.println("There are no possible solutions for a board this size");
        int[] board = new int[boardSize];
        board[0] = 1;
        double preTime = System.currentTimeMillis();
        board = findAll(board);
        clock = System.currentTimeMillis() - preTime;
//        printBoard(board);
        System.out.println("For a board of n = " + board.length +", the number of legal queen placements is: " + numSolutions);
        System.out.println("The time it took to calculate this size is: " + clock + " milliseconds\n");
//        System.out.println(isLegalPosition(board, boardSize));
    }

    public int[] findAll(int[] board) {
        do {
            board = nextLegalPosition(board);
        } while (board[0] != -1);
        return board;
    }

    @Override
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
                if(index == 0 && board[index] == 0){
                    board[0]--;
                    return board;
                }
                //Breaks out of the loop if the program has exhausted all solutions

                board[index - 1]++;
                return nextLegalPosition(board);
            }
        } else {
            //Deals with an entirely full board
            numSolutions++;
            board[index - 1] = 0;
            board[index - 2]++;
            return board;
        }
    }
}
