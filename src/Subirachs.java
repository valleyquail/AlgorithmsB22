public class Subirachs {

    private final int[] subirachMatrix = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};
    public final int genericSum = 33; //1 + 14 + 14 + 4 --> Base sum
    private final int totalSum = 132 + 1; //Sum of all the elements
    private int[] finalSums = new int[totalSum + 1]; //Value to store the count of each possible sum plus zero

    public Subirachs() {
        //Nothing here lol
        countSumsWithAll();
    }

    private void countSumsWithAll() {
        for (int i = 0; i < Math.pow(2, subirachMatrix.length); i++) {
            int tempSum = 0;
            char[] temp = Integer.toBinaryString(i).toCharArray();
            for (int j = temp.length - 1; j >= 0; j--) {
                if (temp[j] == '1') {
                    tempSum += subirachMatrix[temp.length - j - 1];//gross but needs in reverse the list traversal
                }
            }
            finalSums[tempSum]++;
        }
    }

    public void printFinalSums() {
        System.out.println("The frequency of each sum is as follows:");
        for (int i = 0; i < finalSums.length - 1; i++) {
            System.out.println(i + " : " + finalSums[i] + "\t");
        }
    }


    public int countSumsWithFour() {
        int sumsOfFour = 0;
        for (int i = 0; i < Math.pow(2, subirachMatrix.length); i++) {
            if (Integer.bitCount(i) == 4) {
                int tempSum = 0;
                char[] temp = Integer.toBinaryString(i).toCharArray();
                for (int j = temp.length - 1; j >= 0; j--) {
                    if (temp[j] == '1') {
                        tempSum += subirachMatrix[temp.length - j - 1];//gross but needed for list reverse traversal
                    }
                }
                if (tempSum == genericSum) sumsOfFour++;
            }
        }
        return sumsOfFour;
    }

//    public int countSums() {
//        int sumOfGeneric = 0;
//        for (int i = 0; i < Math.pow(2, 16); i++) {
//            int tempSum = 0;
//            char[] temp = Integer.toBinaryString(i).toCharArray();
//            for (int j = 0; j < 15; j++) {
//                if (temp[i] == '1') {
//                    tempSum += subirachMatrix[j];
//                }
//                if (tempSum == genericSum) sumOfGeneric++;
//            }
//        }
//        return sumOfGeneric;
//    }

    //Serves to answer both questions 2 and 3 at the same time, and then other functions return the values

    public int genericSumOfAll() {
        return finalSums[genericSum];
    }

    public void mostCommonSum() {
        int mostCommon = finalSums[0];
        int temp = 0;
        for (int i = 0; i < finalSums.length -1; i++) {
            if (finalSums[i] > mostCommon) {
                mostCommon = finalSums[i];
                temp = i;
            }
        }
        System.out.println("The most common sum using all combinations is " + temp
                + " with " + finalSums[temp]+ " combinations.");
    }


}
