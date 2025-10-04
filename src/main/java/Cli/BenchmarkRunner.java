package Cli;

import algorithms.KadaneAlgorithm;
import algorithms.KadaneOptimized;
import metrics.PerformanceTracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.stream.IntStream;

public class BenchmarkRunner {
    public static void main(String[] args) throws IOException {

        String outPath = "benchmark_results.csv";

        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10000;
        int trials = args.length > 1 ? Integer.parseInt(args[1]) : 5;
        String dist = args.length > 2 ? args[2] : "random";

        System.out.printf("Benchmark: n=%d, trials=%d, distribution=%s%n", n, trials, dist);

        File f = new File(outPath);
        if (f.exists()) {
            f.delete();
        }

        // Заголовок нового CSV
        Files.writeString(Path.of(outPath), "run,maxSum,start,end,timeMs\n");

        Random rnd = new Random(12345);
        PerformanceTracker tracker = new PerformanceTracker();

        for (int t = 0; t < trials; t++) {
            int[] arr = generateArray(n, dist, rnd);
            tracker.reset();

            long startTime = System.nanoTime();
            tracker.incrementRuns();
            KadaneAlgorithm.Result r = KadaneAlgorithm.maxSubarray(arr, tracker);
            long elapsedNs = System.nanoTime() - startTime;
            double elapsedMs = elapsedNs / 1e6;

            // Добавляем запись в CSV
            String line = String.format("%d,%d,%d,%d,%.3f%n",
                    t + 1, r.maxSum, r.start, r.end, elapsedMs);
            Files.writeString(Path.of(outPath), line, StandardOpenOption.APPEND);

            System.out.printf("Done %d/%d  result: maxSum=%d, start=%d, end=%d  time(ms)=%.3f%n",
                    t + 1, trials, r.maxSum, r.start, r.end, elapsedMs);
        }
    }

    private static int[] generateArray(int n, String dist, Random rnd) {
        switch (dist.toLowerCase()) {
            case "sorted":
                return IntStream.rangeClosed(1, n).toArray();
            case "reversed":
                return IntStream.iterate(n, i -> i - 1).limit(n).toArray();
            case "nearly-sorted":
                int[] a = IntStream.rangeClosed(1, n).toArray();
                for (int i = 0; i < Math.max(1, n / 100); i++) {
                    int x = rnd.nextInt(n);
                    int y = rnd.nextInt(n);
                    int tmp = a[x];
                    a[x] = a[y];
                    a[y] = tmp;
                }
                return a;
            case "all-negative":
                int[] neg = new int[n];
                for (int i = 0; i < n; i++) neg[i] = -rnd.nextInt(1000) - 1;
                return neg;
            case "random":
            default:
                int[] r = new int[n];
                for (int i = 0; i < n; i++) r[i] = rnd.nextInt(2001) - 1000; // [-1000,1000]
                return r;
        }
    }
}
