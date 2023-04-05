package experiments;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

// In ComputingPrimesParallelUnfair if we look closely at the O/P
// The threads which are executing the higher range are taking more time while the
// ones with lower range are able to complete early and exit. Hence, this is not fair
// for other threads which are doing the heavy lifting. We should ideally optimise the threads
// to be used equivalently/fairly to complete the tasks. Thus, we need that each thread keeps on
// picking the numbers till 100M and finding whether it's a prime or not, accordingly
// Output:
// Thread: 5 completed in: 11.62 s
// Thread: 0 completed in: 11.62 s
// Thread: 4 completed in: 11.62 s
//Thread: 6 completed in: 11.62 s
// Thread: 3 completed in: 11.62 s
// Thread: 1 completed in: 11.62 s
// Thread: 9 completed in: 11.62 s
// Thread: 8 completed in: 11.62 s
// Thread: 7 completed in: 11.62 s
// Thread: 2 completed in: 11.62 s
// Total time taken for execution parallel fair scan using executor service threads: 11.66 s total prime numbers: 5761454

public class ComputingPrimesParallelFair {
    private static int MAX_NUM = 100_000_000;
    private static int MAX_CONCURRENCY = 10;
    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger currentNum = new AtomicInteger(2);

    public static void main(String[] args) {
        usingExecutorService();
    }

    private static void usingExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CONCURRENCY);
        Stopwatch timer = Stopwatch.createStarted();
        for (int i = 0; i < MAX_CONCURRENCY; i++) {
            final String threadName = String.valueOf(i);
            executorService.submit(() -> submitWork(threadName));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(5000L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("--srb Failed to stop executor service");
            executorService.shutdownNow();
        }

        System.out.println("Total time taken for execution parallel fair scan using executor service threads: "
                + timer.stop() + " total prime numbers: " + count);
    }

    private static void submitWork(String threadName) {
        Stopwatch timer = Stopwatch.createStarted();
        while (true) {
            final int num = currentNum.incrementAndGet();
            if (num > MAX_NUM)
                break;

            checkPrime(num);
        }
        System.out.println("Thread: " + threadName + " completed in: " + timer.stop());
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
