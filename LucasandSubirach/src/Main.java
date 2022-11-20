import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LucasNum lucas = new LucasNum();
        NikNum nik = new NikNum();
        Subirachs subirach = new Subirachs();
        int iteration = 0;

//        System.out.println("Please enter the values for the Lucas Number Sequence:");
//        int numOne = Integer.parseInt(scanner.next());
//        int numTwo = Integer.parseInt(scanner.next());
//        lucas.setStartNums(numOne, numTwo);


        System.out.println("Please enter the number of iterations that you would like to calculate. Be warned, " +
                "this takes exponentially longer to execute as the value grows.");
        try {
            iteration = Integer.parseInt(scanner.next());
            System.out.println("Value accepted: " + iteration);
        } catch (InputMismatchException e) {
            scanner.next();
        }
        System.out.println("The calculated Lucas number of " + iteration + " iterations is: " +
                lucas.calculateLucas(iteration));
        System.out.println("Please enter another number of iterations that you would like to calculate:");
        try {
            iteration = Integer.parseInt(scanner.next());
            System.out.println("Value accepted:" + iteration);
        } catch (InputMismatchException e) {
            scanner.next();
        }
        System.out.println("The ratio of growth between L(" + iteration + ") and L(" + (iteration + 1) + ")" +
                " is: " + lucas.compareGrowth(iteration));
        System.out.println("The ratio of time growth between L(" + iteration + ") and L(" + (iteration + 1) + ")" +
                " is: " + lucas.compareGrowthTimes(iteration) + "\n");
        System.out.println("The ratio is fairly close to the value of the Golden Mean = 1.618, and " +
                "it approaches that value more as the input grows.");

        //NikNum Time

//        System.out.println("Please enter the values for the Nik Number Sequence:");
//        numOne = Integer.parseInt(scanner.next());
//        numTwo = Integer.parseInt(scanner.next());
//        nik.setStartNums(numOne, numTwo);


        System.out.println("Please enter the number of iterations that you would like to calculate. Be warned, " +
                "this takes exponentially longer to execute as the value grows.");
        try {
            iteration = Integer.parseInt(scanner.next());
            System.out.println("Value accepted: " + iteration);
        } catch (InputMismatchException e) {
            scanner.next();
        }
        System.out.println("The calculated Nik number of " + iteration + " iterations is: " +
                nik.calculateNik(iteration));
        System.out.println("Please enter another number of iterations that you would like to calculate:");
        try {
            iteration = Integer.parseInt(scanner.next());
            System.out.println("Value accepted:" + iteration);
        } catch (InputMismatchException e) {
            scanner.next();
        }
        System.out.println("The ratio of growth between L(" + iteration + ") and L(" + (iteration + 1) + ")" +
                " is: " + nik.compareGrowth(iteration));
        System.out.println("The ratio of time growth between L(" + iteration + ") and L(" + (iteration + 1) + ")" +
                " is: " + nik.compareGrowthTimes(iteration));

        System.out.println("Moving on the the Subirachs's Square.\n The number of combinations of" +
                " four numbers in the square that equal the column/row sum of 33 is: " +
                subirach.countSumsWithFour());

        System.out.println("The number of combinations of any amount of numbers that sums to be 33 is: "
                + subirach.countGenericSum());

        subirach.printFinalSums();
        subirach.mostCommonSum();
        System.out.println("As we can see, the most common sum is 66, which is twice that of 33, the magic number for" +
                " the Subirach's Sqaure. 66 is also half the maximum sum of 132. Also, looking at the distribution, " +
                "the sum 99 also occurs 310 times, so we can determine that the occurrences are symmetric about 66. ");
    }
}
