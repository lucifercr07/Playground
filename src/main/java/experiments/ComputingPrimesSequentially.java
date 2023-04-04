package experiments;

import com.google.common.base.Stopwatch;

// Counting number of prime numbers till 100,000,000 = 100 million
// OUTPUT: Time take for execution sequential scan: 4.266 min total prime numbers: 5761455
public class ComputingPrimesSequentially {
    private static int MAX_NUM = 100_000_000;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Stopwatch timer = Stopwatch.createStarted();
        for (int i = 0; i < MAX_NUM; i++) {
            checkPrime(i);

            if (i % 500000 == 0)
                System.out.println("I am running at: " + i);
        }

        System.out.println("Time take for execution sequential scan: " + timer.stop() + " total prime numbers: " + count);
    }

    private static void checkPrime(final int n) {
        if (n <= 1)
            return;
        // Check if number is 2
        else if (n == 2) {
            count++;
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

        count++;
    }
}
