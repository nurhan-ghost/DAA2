package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import metrics.PerformanceTracker;

public class KadaneAlgorithmTest {

    @Test
    public void testEmptyArray() {
        int[] a = new int[0];
        KadaneOptimized.Result r = KadaneOptimized.maxSubarray(a, null);

        System.out.println("testEmptyArray -> " + r);

        assertEquals(0, r.maxSum);
        assertEquals(-1, r.start);
        assertEquals(-1, r.end);
    }

    @Test
    public void testSingleElement() {
        int[] a = {5};
        KadaneOptimized.Result r = KadaneOptimized.maxSubarray(a, null);

        System.out.println("testSingleElement -> " + r);

        assertEquals(5, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(0, r.end);
    }

    @Test
    public void testAllNegative() {
        int[] a = {-3, -1, -7, -2};
        KadaneOptimized.Result r = KadaneOptimized.maxSubarray(a, null);

        System.out.println("testAllNegative -> " + r);

        assertEquals(-1, r.maxSum);
        assertEquals(1, r.start);
        assertEquals(1, r.end);
    }

    @Test
    public void testMixed() {
        int[] a = { -1, 2, -6, 4, 1, 3, 2, -8, 4 };
        KadaneOptimized.Result r = KadaneOptimized.maxSubarray(a, null);

        System.out.println("testMixed -> " + r);

        assertEquals(10, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(6, r.end);
    }

    @Test
    public void testMetricsAreRecorded() {
        int[] a = {1, 2, 3};
        PerformanceTracker t = new PerformanceTracker();
        KadaneOptimized.Result r = KadaneOptimized.maxSubarray(a, t);


        System.out.println("testMetricsAreRecorded -> " + r);
        System.out.println("Comparisons: " + t.getComparisons());
        System.out.println("Array accesses: " + t.getArrayAccesses());

        assertTrue(t.getArrayAccesses() > 0);
        assertTrue(t.getComparisons() > 0);
    }
}


