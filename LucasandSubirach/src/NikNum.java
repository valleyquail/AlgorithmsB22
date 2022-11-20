public class NikNum {
    private int startOne = 2;
    private int startTwo = 1;
    private double clockOne;
    private double clockTwo;
    private boolean updatedTime;

    private int prevIteration;

    public void setStartNums(int startO, int startT) {
        startOne = startO;
        startTwo = startT;
    }

    public long calculateNik(int iterationCount) {
        if (iterationCount == 1) {
            return startTwo;
        } else if (iterationCount <= 0) {
            return startOne;
        } else {
            return calculateNik(iterationCount - 1) + calculateNik(iterationCount - 2) + calculateNik(iterationCount - 3);
        }
    }

    public double compareGrowth(int iterationCount) {
        //Save computation time by storing the clock timing as well during growth calculations
        prevIteration = iterationCount;
        double preTime = System.currentTimeMillis();
        double nikOne = calculateNik(iterationCount);
        clockOne = System.currentTimeMillis() - preTime;
        System.out.println("Time to complete calculating " + iterationCount +
                " iterations is: " + clockOne + " milliseconds");

        preTime = System.currentTimeMillis();
        double nikTwo = calculateNik(iterationCount + 1);
        clockTwo = System.currentTimeMillis() - preTime;
        System.out.println("Time to complete calculating " + (iterationCount + 1) +
                " iterations is: " + clockTwo + " milliseconds");
        updatedTime = true;
        return nikTwo/nikOne;
    }

    public double compareGrowthTimes(int iterationCount) {
        if (updatedTime && iterationCount == prevIteration) {
            updatedTime = false;
            return clockTwo / clockOne;
        }
        calculateNik(iterationCount);
        compareGrowthTimes(iterationCount);
        return clockTwo / clockOne;
    }
}
