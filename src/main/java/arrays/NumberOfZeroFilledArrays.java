package arrays;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ref: <a href="https://leetcode.com/problems/number-of-zero-filled-subarrays/">...</a>
 */
public class NumberOfZeroFilledArrays {
    public static void main(String[] args) {
        // {0,0,0,2,0,0} => 9 , {2,10,2019} => 0
        List<List<Integer>> inputs = List.of(
                List.of(1,3,0,0,2,0,0,4),
                List.of(0,0,0,2,0,0),
                List.of(2,10,2019)
        );

        for (var input : inputs) {
            System.out.println("Result: " + solution(input.stream().mapToInt(Integer::intValue).toArray()));
        }
    }

    private static int solution(int[] nums) {
        int countConsecutiveZeroes = 0, result = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                // increment count for 0's
                countConsecutiveZeroes++;
            } else {
                // Count number of subarrays can be created from given consecutive count
                result += (countConsecutiveZeroes * (countConsecutiveZeroes + 1)) / 2;
                countConsecutiveZeroes = 0;
            }
        }

        // At end we need to calculate again as it might be that 0's extend till end of array
        if (countConsecutiveZeroes != 0)
            result += (countConsecutiveZeroes * (countConsecutiveZeroes + 1)) / 2;

        return result;
    }
}
