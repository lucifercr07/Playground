package experiments;

import com.google.common.base.Stopwatch;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// Counting number of prime numbers till 100,000,000 = 100 million
// In this we'll divide 100M into 10 parts i.e 10M each
// Total time taken for execution parallel unfair scan using plain threads: 14.27 s total prime numbers: 5761455
// Total time taken for execution parallel unfair scan:: 13.56 s total prime numbers: 5761454
public class ComputingPrimesParallelUnfair {
    private static int MAX_NUM = 100_000_000;
    private static AtomicInteger count = new AtomicInteger(0);
    private static int MAX_CONCURRENCY = 10;

    public static void main(String[] args) {
        try {
            usingPlainThreads();
            // Resetting value for executor service run
            count = new AtomicInteger(0);
            usingExecutorService();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void usingPlainThreads() throws InterruptedException {
        Stopwatch timer = Stopwatch.createStarted();
        // 10M batch size will be for 10 threads
        final int batchSize = MAX_NUM / MAX_CONCURRENCY;
        int start = 3;

        List<Pair<Integer, Integer>> ranges = new ArrayList<>();

        for (int i = 0; i < MAX_CONCURRENCY; i++) {
            final int batchStart = start;
            final int batchEndLength = start + batchSize;

            ranges.add(new Pair<>(batchStart, batchEndLength));
            start = batchEndLength + 1;
        }

        List<Thread> threadList = new ArrayList<>();
        for (var range : ranges) {
            threadList.add(new Thread(() -> checkPrimeForRange(range.getFirst(), range.getSecond())));
        }

        for (var thread : threadList) {
            thread.start();
        }

        for (var thread : threadList) {
            thread.join();
        }

        System.out.println("Total time taken for execution parallel unfair scan using plain threads: "
                + timer.stop() + " total prime numbers: " + count);
    }

    private static void usingExecutorService() {
        ExecutorService service = Executors.newFixedThreadPool(MAX_NUM);
        // 10M batch size will be for 10 threads
        final int batchSize = MAX_NUM / MAX_CONCURRENCY;
        int start = 3;

        Stopwatch timer = Stopwatch.createStarted();
        for (int i = 0; i < MAX_CONCURRENCY; i++) {
            final int batchStart = start;
            final int batchEndLength = start + batchSize;

            service.submit(() -> {
                System.out.println("Submitting for batch start: " + batchStart + " to end num: " + batchEndLength + " thread: " + Thread.currentThread().getId());
                Stopwatch threadTimer = Stopwatch.createStarted();
                checkPrimeForRange(batchStart, batchEndLength);
                System.out.println("Time taken for execution parallel unfair scan: " + threadTimer.stop() + " by thread: " + Thread.currentThread().getId()
                        + " for batch: " + batchStart + " to end: " + batchEndLength);
            });

            start = batchEndLength;
        }

        service.shutdown();
        try {
            service.awaitTermination(5000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("--srb Failed to stop executor service");
            service.shutdownNow();
        }

        System.out.println("Total time taken for execution parallel unfair scan:: " + timer.stop() + " total prime numbers: " + count);
    }

    private static void checkPrimeForRange(final int start, final int end) {
        for (int i = start; i < end; i++) {
            checkPrime(i);
        }
    }

    private static void checkPrime(final int n) {
        if (n <= 1)
            return;
            // Check if number is 2
        else if (n == 2) {
            count.incrementAndGet();
            return;
        }
        // Check if n is a multiple of 2
        else if (n % 2 == 0)
            return;

        // If not, then just check the odds
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return;
        }

        count.incrementAndGet();
    }
}
