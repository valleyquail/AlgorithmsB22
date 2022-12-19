import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        PrelimQueen queen = new PrelimQueen();
        System.out.println(queen.isLegalPosition(new int[]{1, 6, 8, 3, 7, 4, 2, 5}, 8));
        System.out.println(queen.isLegalPosition(new int[]{1, 6, 8, 3, 7, 0, 0, 0}, 5));
        System.out.println(Arrays.toString(queen.nextLegalPosition(new int[]{1, 6, 8, 3, 7, 0, 0, 0})));
        System.out.println();
        System.out.println(Arrays.toString(queen.nextLegalPosition(new int[]{1, 6, 8, 3, 5, 0, 0, 0})));
        System.out.println(Arrays.toString(queen.nextLegalPosition(new int[]{1,0,0,0,0,0,0,0})));
        System.out.println("Filled board test");
        System.out.println(Arrays.toString(queen.nextLegalPosition(new int[]{1, 6, 8, 3, 7, 4, 2, 5})));

        StartingQueenSolver queenSolver = new StartingQueenSolver();
        for (int i = 4; i <= 36; i++) {
            queenSolver.firstLegalBoard(i);
        }
        System.out.println("Done");
        AllQueens queenSolutions = new AllQueens();
        for (int i = 4; i <= 17; i++) {
          queenSolutions.allSolutions(i);
        }
    }

}
