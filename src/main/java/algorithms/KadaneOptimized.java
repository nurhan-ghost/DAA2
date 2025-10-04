package algorithms;

import metrics.PerformanceTracker;


public class KadaneOptimized {


    public static class Result {
        public final long maxSum;
        public final int start;
        public final int end;

        public Result(long maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("maxSum=%d, start=%d, end=%d", maxSum, start, end);
        }
    }
    public static KadaneOptimized.Result maxSubarrayOptimized(int[] arr, PerformanceTracker tracker) {
        if (arr == null || arr.length == 0) {
            return new KadaneOptimized.Result(0, -1, -1);
        }

        long maxSum = arr[0];
        long currentSum = arr[0];
        int start = 0, bestStart = 0, bestEnd = 0;

        if (tracker != null) tracker.recordArrayAccess();

        for (int i = 1; i < arr.length; i++) {
            if (tracker != null) tracker.recordArrayAccess();

            long val = arr[i];


            if (currentSum < 0) {
                currentSum = val;
                start = i;
            } else {
                currentSum += val;
            }

            if (tracker != null) tracker.recordComparison();


            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = start;
                bestEnd = i;
            }
        }

        return new KadaneOptimized.Result(maxSum, bestStart, bestEnd);
    }
}
