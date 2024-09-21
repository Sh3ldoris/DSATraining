package excercise.arrays_hashing;

import java.util.Arrays;

class SolutionExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;

        // Multiply the prefixes
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Multiply the suffixes
        int postFix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] = result[i] * postFix;
            postFix *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        SolutionExceptSelf solution = new SolutionExceptSelf();
        int[] nums = {1, 2, 3, 4};
        int[] result = solution.productExceptSelf(nums);
        System.out.println(Arrays.toString(result)); // Output: [24, 12, 8, 6]
    }
}
