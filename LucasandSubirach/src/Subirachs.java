public class Subirachs {

    private final int[] subirachMatrix = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};
    public final int genericSum = 33; //1 + 14 + 14 + 4 --> Base sum
    private final int totalSum = 132 + 1; //Sum of all the elements
    private int[] Sums = new int[totalSum + 1]; //Value to store the count of each possible sum plus zero

    public Subirachs() {
        //Nothing here lol
        countSumsWithAll();
    }

    private void countSumsWithAll() {
        for (int i = 0; i < Math.pow(2, subirachMatrix.length); i++) {
            int tempSum = 0;
            int temp = i;
            int index = 0;
            while (temp != 0) {
                if ((temp & 0x1) == 1) {
                    tempSum += subirachMatrix[index];
                }
                index++;
                temp >>= 1;
            }
            Sums[tempSum]++;
        }
    }


    public int countSumsWithFour() {
        int sumsOfFour = 0;
        for (int i = 0; i < Math.pow(2, subirachMatrix.length); i++) {
            int tempSum = 0;
            if (Integer.bitCount(i) == 4) {
                int temp = i;
                int index = 0;
                while (temp != 0) {
                    if ((temp & 0x1) == 1) {
                        tempSum += subirachMatrix[index];
                    }
                    index++;
                    temp >>= 1;
                }
                if (tempSum == genericSum) sumsOfFour++;
            }
        }
        return sumsOfFour;
    }

    public int countGenericSum() {
        return Sums[genericSum];
    }

    public void printFinalSums() {
        System.out.println("The frequency of each sum is as follows:");
        for (int i = 0; i < Sums.length - 1; i++) {
            System.out.println(i + " : " + Sums[i] + "\t");
        }
    }

    public void mostCommonSum() {
        int mostCommon = Sums[0];
        int temp = 0;
        for (int i = 0; i < Sums.length - 1; i++) {
            if (Sums[i] > mostCommon) {
                mostCommon = Sums[i];
                temp = i;
            }
        }
        System.out.println("The most common sum using all combinations is " + temp
                + " with " + Sums[temp] + " combinations.");
    }
}
