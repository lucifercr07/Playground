package experiments;

// In ComputingPrimesParallelUnfair if we look closely at the O/P
// The threads which are executing the higher range are taking more time while the
// ones with lower range are able to complete early and exit. Hence, this is not fair
// for other threads which are doing the heavy lifting. We should ideally optimise the threads
// to be used equivalently/fairly to complete the tasks. Thus, we need that each thread keeps on
// picking the numbers till 100M and finding whether it's a prime or not, accordingly
public class ComputingPrimesParallelFair {
    public static void main(String[] args) {

    }
}
