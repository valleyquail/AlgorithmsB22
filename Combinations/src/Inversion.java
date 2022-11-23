import java.util.Arrays;

public class Inversion {

    public int easyinversioncount(int[] set) {
        int inversionNum = 0;
        for (int i = 0; i < set.length - 1; i++) {
            for (int j = i + 1; j < set.length; j++) {
                if (set[i] > set[j]) inversionNum++;
            }
        }
        return inversionNum;
    }

    public int fastinversioncount(int[] set) {
        return fastHelper(set, 0, set.length - 1);
    }

    private int fastCountHelper(int[] array, int l, int r) {
        int mid = (l + r) / 2;
        int[] leftArr = Arrays.copyOfRange(array, l, mid + 1);
        int[] rightArr = Arrays.copyOfRange(array, mid + 1, r + 1);
        int i = 0, j = 0;
        int index = 0;
        int swapCount = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                i++;
                index++;
            } else {
                index++;
                j++;
                swapCount += (mid + 1) - (l + i);
            }
        }

        return swapCount;
    }

    private int fastHelper(int[] array, int l, int r) {
        int count = 0;
        if (l < r && array.length > 1) {
            int middle = (l + r) / 2;
            count += fastHelper(array, l, middle);
            count += fastHelper(array, middle + 1, r);
            count += fastCountHelper(array, l, r);
        }
        return count;
    }
}

