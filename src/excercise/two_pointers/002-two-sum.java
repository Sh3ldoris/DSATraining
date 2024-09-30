package excercise.two_pointers;

import java.util.Arrays;

class SolutionTwoSumSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        Arrays.sort(numbers);

        int j;
        int i = 0;
        while (i < numbers.length) {
            int diff = target - numbers[i];
            if (diff < numbers[i]) {
                j = i - 1;
                while (j >= 0 && numbers[j] >= diff) {
                    if (numbers[j] == diff) {
                        return new int[] { ++j, ++i};
                    } else {
                        j--;
                    }
                }
            } else {
                j = i + 1;
                while (j <= numbers.length - 1 && numbers[j] <= diff) {
                    if (numbers[j] == diff) {
                        return new int[] { ++i, ++j};
                    } else {
                        j++;
                    }
                }
            }
            i++;
        }

        return new int[2];
    }

    public int[] twoSumBetter(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;

        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[] { ++i, ++j};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[2];
    }

    public static void main(String[] args) {
        SolutionTwoSumSortedArray solution = new SolutionTwoSumSortedArray();
        int[] numbers = {2, 3, 1};
        int target = 4;
        int[] result = solution.twoSumBetter(numbers, target);
        System.out.println("Index 1: " + result[0]);
        System.out.println("Index 2: " + result[1]);
    }
}
