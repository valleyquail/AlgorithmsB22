public class Main {

    public static void main(String[] args) {

        System.out.println("""
                Problem 1:
                    The reason that the forwardelimination algorithm fails to provide a solution to the system of equations:
                        x + y + z = 6
                        x + y + 2z = 9
                        x + 2y + 3z = 14
                    is because the algorithm fails if/when there are any zeros in the diagonal, otherwise known as a singular matrix.
                    Therefore, When doing the elimination, as soon as the first loop of the algorithm executes, it will become unsolvable by itself because A[i][i] == 0.
                \tThis is stated in the textbook also, and suggests that the necessary change is to sap rows so that this is avoided.
                """);

        System.out.println("""
                   Problems 2:
                        The algorithm fails to solve the system:
                         x + y + z = 6
                         x + y + 2z = 9
                         2x + 2y + 3x = 15
                \tbecause it has an infinite number of solutions. Doing a simple row operation shows this since: R2 - R1 gives:
                            x + y + z = 6
                            0 + 0 + z = 3
                            2x + 2y + 3x = 15
                        meaning that there are an infinite number of solutions for the system. The algorithm in the book will result in a NaN error because it will try to divide\s
                \tby zero since there will be no non-zero coefficients in the row. There is a check function implemented in line 85 of the GaussianElim class to account for problems like this, though\s
                \tit does not specify the error.
                """);
        GaussianElim mat = new GaussianElim();
//        mat.printSystem();
        mat.solveMatrix();
        mat.printSystem();
        mat.printSolution();
        System.out.println();
        DynamicGemSeek gems = new DynamicGemSeek();
        gems.gemSeek();

    }
}
