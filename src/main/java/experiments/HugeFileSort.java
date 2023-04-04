package experiments;

import arrays.sort.MergeSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class HugeFileSort {
    public static void main(String... ignored) throws IOException {
        // final String rawFileContents = new String(Files.readAllBytes(Paths.get("/Users/shubhampr/Library/CloudStorage/OneDrive-VMware,Inc/Documents/Projects/PracticeProblems/AvgNumbers.txt")));
        int[] arr = {1, 3, 5, 7, 6, 4};
        MergeSort.sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
