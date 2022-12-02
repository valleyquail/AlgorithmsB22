import java.awt.desktop.ScreenSleepEvent;
import java.text.DecimalFormat;
import java.util.Arrays;

public class GaussianElim {

    private final int matrixX = 13;
    private final int matrixY = 12;
    private float[][] example2 = {
            {1, 1, 1, 6},
            {1, 1, 2, 9},
            {2, 2, 3, 15}
    };
    private float[][] homework = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 364},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 16},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 36},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 64},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 100},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 79},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 61},
            {0, 0, 0, 0, 0, 4, -3, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, -2, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 1, -1, 0, 0, 0},
            {1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -42}
    };
    private float[][] matrixToSolve = homework;
    private float[] systemSolution = new float[12];


    public void solveMatrix() {
        for (int column = 0; column < matrixX - 1; column++) {
            int pivotRow = column;
            for (int i = column + 1; i < matrixY; i++) {
                if (Math.abs(matrixToSolve[i][column]) > Math.abs(matrixToSolve[pivotRow][column]))
                    pivotRow = i;
            }
            if (matrixToSolve[pivotRow][column] != 0) {
                sortMatrix(column, pivotRow);
                reducedRowFormLower(column);
                reducedRowFormUpper(column);
            }
        }
        solve();
    }

    private void sortMatrix(int diagonalIndex, int pivotRow) {
        if (diagonalIndex != pivotRow) {
            for (int i = 0; i < matrixX; i++) {
                float temp = matrixToSolve[diagonalIndex][i];
                matrixToSolve[diagonalIndex][i] = matrixToSolve[pivotRow][i];
                matrixToSolve[pivotRow][i] = temp;
            }
        }
    }

    private void reducedRowFormLower(int diagonalIndex) {
        for (int i = diagonalIndex + 1; i < matrixY; i++) {
            float ratio = matrixToSolve[i][diagonalIndex] / matrixToSolve[diagonalIndex][diagonalIndex];
//            System.out.println(matrixToSolve[matrixY-1][matrixX-1]);
            for (int j = diagonalIndex; j < matrixX; j++) {
                matrixToSolve[i][j] -= matrixToSolve[diagonalIndex][j] * ratio;

            }
        }
    }

    private void reducedRowFormUpper(int diagonalIndex) {
        for (int i = diagonalIndex - 1; i >= 0; i--) {
            float ratio = matrixToSolve[i][diagonalIndex] / matrixToSolve[diagonalIndex][diagonalIndex];
            for (int j = diagonalIndex; j < matrixX; j++) {
                matrixToSolve[i][j] -= matrixToSolve[diagonalIndex][j] * ratio;
            }
        }
    }

    private void solve() {

        for (int i = 0; i < matrixY; i++) {
            if (matrixToSolve[i][i] != 0) {
                matrixToSolve[i][matrixX - 1] /= matrixToSolve[i][i];
                matrixToSolve[i][i] = 1;
                systemSolution[i] = matrixToSolve[i][matrixX - 1];
            } else {
                throw new Error("The system likely either has an infinite " +
                        "number of solutions or is inconsistent, please chech your system input!!!");
            }
        }
    }

    public void printSystem() {
        for (int i = 0; i < matrixY; i++) {
            for (int j = 0; j < matrixX - 1; j++) {
//                System.out.printf("%4.1f%c", matrixToSolve[i][j], j + 97);
                System.out.printf("%5.1f", matrixToSolve[i][j]);
            }
            System.out.printf(" = %-3.1f", matrixToSolve[i][matrixX - 1]);
            System.out.println();
                    }
    }


    public void printSolution() {
        System.out.println("The solution of the system is:");
        DecimalFormat f = new DecimalFormat("#0");
        for (int i = 0; i < matrixY; i++) {
            System.out.printf("x%-2d = %2s\n", i, f.format(systemSolution[i]));
        }
    }
}
