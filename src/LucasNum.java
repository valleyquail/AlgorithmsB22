public class LucasNum {
    private int startOne = 2;
    private int startTwo = 1;
    private double preTime;
    private double clockOne;
    private double clockTwo;
    private boolean updatedTime;

    private int prevIteration;

    public void setStartNums(int startO, int startT) {
        startOne = startO;
        startTwo = startT;
    }

    public long calculateLucas(int iterationCount) {
        if (iterationCount == 1) {
            return startTwo;
        } else if (iterationCount == 0) {
            return startOne;
        } else {
            return calculateLucas(iterationCount - 1) + calculateLucas(iterationCount - 2) ;
        }
    }

    public double compareGrowth(int iterationCount) {
        //Save computation time by storing the clock timing as well during growth calculations
        prevIteration = iterationCount;
        preTime = System.currentTimeMillis();
        double lucasOne = calculateLucas(iterationCount);
        clockOne = System.currentTimeMillis() - preTime;
        System.out.println("Time to complete calculating " + iterationCount +
                " iterations is: " + clockOne +" milliseconds");

        preTime = System.currentTimeMillis();
        double lucasTwo = calculateLucas(iterationCount + 1);
        clockTwo = System.currentTimeMillis() - preTime;
        System.out.println("Time to complete calculating " + (iterationCount+1) +
                " iterations is: " + clockTwo +" milliseconds");
        updatedTime = true;
        return (double) lucasTwo/lucasOne;
    }

    public double compareGrowthTimes(int iterationCount) {
        if (updatedTime && iterationCount == prevIteration) {
            updatedTime = false;
            return clockTwo / clockOne;
        }
        calculateLucas(iterationCount);
        compareGrowthTimes(iterationCount);
        return clockTwo / clockOne;
    }
}
