package metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long runs = 0;

    public void recordComparison() {
        comparisons++;
    }

    public void recordArrayAccess() {
        arrayAccesses++;
    }

    public void incrementRuns() {
        runs++;
    }

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        runs = 0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getRuns() {
        return runs;
    }


    public void exportToCsv(String filePath, int n, long timeNs) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(String.format("%d,%d,%d,%d,%d%n",
                    n,
                    timeNs,
                    comparisons,
                    arrayAccesses,
                    runs
            ));
        } catch (IOException e) {
            throw new RuntimeException("Error in export " + e.getMessage(), e);
        }
    }
}

