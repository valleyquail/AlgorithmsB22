import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class DynamicGemSeek {

    private final int rows = 8;
    private final int columns = 8;
    private final int[][] dungeon = {{96, 33, 44, 98, 75, 68, 99, 84},
            {10, 41, 1, 86, 46, 24, 53, 93},
            {83, 97, 94, 27, 65, 51, 39, 7},
            {56, 70, 47, 64, 22, 88, 67, 12},
            {91, 11, 77, 48, 13, 71, 92, 15},
            {32, 59, 17, 25, 31, 4, 16, 63},
            {79, 5, 14, 23, 78, 37, 40, 74},
            {35, 89, 52, 66, 82, 20, 95, 21}};
    int maxGems = 0;
    int vault = 0;
    int startingIndex = 0;
    private int[][] evaluatedGems = new int[rows][columns]; //Stores the most successful run
    private int[][] tempEvaluated = new int[rows][columns]; //Stores the current run in order to compare to the previous to see
    // which is more successful
    private int[] pathVerbose = new int[8];

    DynamicGemSeek() {
    }

    public void gemSeek() {
        for (int i = 0; i < 8; i++) {
            runSeek(i);
            tempEvaluated = new int[rows][columns]; // clears the array to be used again,
            // technically a bit slow, but makes it nicer than passing an array around randomly
        }
        int[][] path = getPath();
        System.out.println("The max gems obtained in the run is " + maxGems);
        System.out.println("The starting cell in order to get the max numbers of zeros is " + startingIndex);
        System.out.println("The location of the vault is " + vault);
        System.out.println();
//        for (int[] row : evaluatedGems) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println('\n');
//        for (int[] row : path) {
//            System.out.println(Arrays.toString(row));
//        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%-5d", evaluatedGems[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.printf("%2d ", path[i][j]);
            }
            System.out.println();
        }
        System.out.print("\nThe path taken is:\nStart -> ");
        for (int i = 0; i < 8; i++) {
            System.out.printf("(%d, %d) -> ", 8 - i, pathVerbose[8 - i - 1] + 1);
        }
        System.out.println("End");
    }

    private void runSeek(int startingPoint) {
        tempEvaluated[7][startingPoint] = dungeon[7][startingPoint];
        compareCells(rows - 1, startingPoint);
//        for (int[] row : tempEvaluated) {
//            System.out.println(Arrays.toString(row));
//                   }
//        System.out.println();
        compareBoard();
    }

    //Will return either -1 in order to go left, 0 to go forward, or 1 in order to go right
    private void compareCells(int currentRow, int currentColumn) {
        //recursively cringe
        //Creates a recursive function that runs a dynamic programming algorithm that works its way up from the starting
        //cell until it reaches the top of the matrix. It writes to a matrix to store temporary values and compares the currently
        //calculated values to the stored values before then comparing that to the current most optimal variant

        if (currentRow != 0) {
            if (currentColumn > 0) {
                int left = dungeon[currentRow - 1][currentColumn - 1] + tempEvaluated[currentRow][currentColumn];
                tempEvaluated[currentRow - 1][currentColumn - 1] = Math.max(left, tempEvaluated[currentRow - 1][currentColumn - 1]);
                compareCells(currentRow - 1, currentColumn - 1);
            }

            int mid = dungeon[currentRow - 1][currentColumn] + tempEvaluated[currentRow][currentColumn];
            tempEvaluated[currentRow - 1][currentColumn] = Math.max(mid, tempEvaluated[currentRow - 1][currentColumn]);
            compareCells(currentRow - 1, currentColumn);

            if (currentColumn < columns - 1) {
                int right = dungeon[currentRow - 1][currentColumn + 1] + tempEvaluated[currentRow][currentColumn];
                tempEvaluated[currentRow - 1][currentColumn + 1] = Math.max(right, tempEvaluated[currentRow - 1][currentColumn + 1]);
                compareCells(currentRow - 1, currentColumn + 1);
            }
        }
    }

    private void compareBoard() {
        //Checks to see if the newest board is more optimal than the current most-optimal board
        int max1 = 0, max2 = 0, newVault = 0;
        for (int i = 0; i < columns; i++) {
            if (evaluatedGems[0][i] > max1) {
                max1 = evaluatedGems[0][i];
            }
            //The new board max cell is found and then is set to the vault location
            if (tempEvaluated[0][i] > max2) {
                max2 = tempEvaluated[0][i];
                newVault = i;
            }
        }
        if (max1 < max2) {
            evaluatedGems = tempEvaluated;
            maxGems = max2;
            vault = newVault;
        }
    }

    private int[] @NotNull [] getPath() {
        int[][] path = new int[rows][columns];
        path[0][vault] = 1;
        pathVerbose[0] = vault;
        int currentColumn = vault;

        //Backtracks from the optimal cell position at the top of the optimal grid before ending up at the starting cell of the graph
        for (int i = 0; i < rows; i++) {
            int left = 0, right = 0;
            if (currentColumn > 0) left = evaluatedGems[i][currentColumn - 1];
            int mid = evaluatedGems[i][currentColumn];
            if (currentColumn < 7) right = evaluatedGems[i][currentColumn + 1];

            if (left > mid && left > right) {
                currentColumn -= 1;
                path[i][currentColumn] = 1;
                pathVerbose[i] = currentColumn;
            } else if (right > mid && right > left) {
                currentColumn += 1;
                path[i][currentColumn] = 1;
                pathVerbose[i] = currentColumn;
            } else {
                path[i][currentColumn] = 1;
                pathVerbose[i] = currentColumn;
            }
        }
        vault++;
        startingIndex = currentColumn + 1;
        return path;
    }
}