package algorithms;

import metrics.PerformanceTracker;


public class KadaneAlgorithm {


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


    public static Result maxSubarray(int[] arr, PerformanceTracker tracker) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int n = arr.length;

        if (n == 0) {
            return new Result(0, -1, -1);
        }

        long maxEndingHere = arr[0];
        long maxSoFar = arr[0];
        int start = 0, end = 0, s = 0;

        if (tracker != null) {
            tracker.recordArrayAccess();
        }

        for (int i = 1; i < n; i++) {
            if (tracker != null) tracker.recordArrayAccess();

            long val = arr[i];


            if (val > maxEndingHere + val) {
                maxEndingHere = val;
                s = i;
            } else {
                maxEndingHere = maxEndingHere + val;
            }

            if (tracker != null) tracker.recordComparison();


            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = s;
                end = i;
            }
        }

        return new Result(maxSoFar, start, end);
    }
}