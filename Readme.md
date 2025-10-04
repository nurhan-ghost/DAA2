
# Kadane Maximum Subarray Project

**Author:** Turganbek Nurhan  
**Partner:** Nygman Dias  
**Group:** SE-2435  

## Overview

This project solves the *Maximum Subarray Problem* using Kadane's Algorithm.  
The algorithm finds the contiguous subarray with the largest sum and returns its start and end indices.  
It includes benchmarking tools for arrays of various sizes and distributions, and unit tests to ensure correctness.  

## Features

- **Linear Time:** O(n) performance for any input size  
- **Constant Space:** uses only a fixed amount of extra memory  
- **Position Tracking:** reports the subarray producing the maximum sum  
- **Benchmarking:** results saved in `benchmark_results.csv`  
- **Unit Tested:** ensures correctness for different input cases  

## How to Run

1. **Clone the repository:**
```bash
git clone https://github.com/nurhan-ghost/DAA2.git
````

2. **Compile and Run Benchmark:**

```bash
cd DAA2/src/main/java/Cli
javac BenchmarkRunner.java
java BenchmarkRunner
```

3. **Run Unit Tests:**

```bash
mvn test
```

(Unit tests are located in `src/test/java/algorithms/KadaneAlgorithmTest.java`)

## Project Structure

```
Kadane-Algorithm/
├── src/
│   ├── main/java/
│   │   ├── algorithms/ KadaneAlgorithm.java, KadaneOptimized.java
│   │   ├── metrics/ PerformanceTracker.java
│   │   └── cli/ BenchmarkRunner.java
│   ├── test/java/ algorithms/ KadaneAlgorithmTest.java
├── benchmark_results.csv
├── README.md
└── pom.xml
```

## Benchmark & Results

The benchmark evaluates the algorithm on arrays of different sizes and distributions: random, sorted, reversed, nearly sorted, and all negative.

**CSV format (`benchmark_results.csv`):**

```
run,maxSum,start,end,timeMs
1,45716,36,2672,3489
2,108474,3729,9942,1432
3,50183,1347,7182,1399
4,49846,2876,5044,1376
5,101123,360,9999,1376
```

## Notes

* The optimized version improves computation time for large arrays.
* `BenchmarkRunner` records execution time in **milliseconds**.
* All performance metrics are tracked via `PerformanceTracker`.

