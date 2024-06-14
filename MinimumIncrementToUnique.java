/*
945. Minimum Increment to Make Array Unique
Solved
Medium
Topics
Companies
You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and increment nums[i] by 1.

Return the minimum number of moves to make every value in nums unique.

The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: nums = [1,2,2]
Output: 1
Explanation: After 1 move, the array could be [1, 2, 3].

Example 2:

Input: nums = [3,2,1,2,1,7]
Output: 6
Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.

Constraints:

1 <= nums.length <= 105
0 <= nums[i] <= 105
*/

public class MinimumIncrementToUnique {

    // Helper method to find the maximum element in an array
    public int maxElement(int[] arr) {
        int max = 0;
        for (int nums : arr) {
            max = Math.max(max, nums);
        }
        return max;
    }

    // Main method to calculate the minimum number of increments to make array elements unique
    public int minIncrementForUnique(int[] nums) {
        // Find the maximum element in the nums array
        int maxEle = maxElement(nums);

        // Create a count array to store frequencies of elements in nums
        int countArr[] = new int[maxEle + nums.length];

        // Fill the count array with frequencies
        for (int elements : nums) {
            countArr[elements]++;
        }

        int result = 0;
        // Iterate through the count array to calculate the minimum increments
        for (int i = 0; i < countArr.length; i++) {
            if (countArr[i] <= 1) {
                continue;
            }

            // Calculate the number of extra elements that need to be incremented
            int extra = countArr[i] - 1;

            // Increment the count of the next element
            countArr[i + 1] += extra;

            // Increment the result by the number of extra elements
            result += extra;

            // Set the current element count to 1 as it is now unique
            countArr[i] = 1;
        }
        return result;
    }

    public static void main(String[] args) {
        MinimumIncrementToUnique solution = new MinimumIncrementToUnique();

        // Example 1
        int[] nums1 = {1, 2, 2};
        System.out.println("Example 1 Output: " + solution.minIncrementForUnique(nums1)); // Output: 1

        // Example 2
        int[] nums2 = {3, 2, 1, 2, 1, 7};
        System.out.println("Example 2 Output: " + solution.minIncrementForUnique(nums2)); // Output: 6

        // Additional Example 3
        int[] nums3 = {2, 2, 2, 2, 2};
        System.out.println("Example 3 Output: " + solution.minIncrementForUnique(nums3)); // Output: 10

        // Additional Example 4
        int[] nums4 = {0, 0, 0, 0, 0};
        System.out.println("Example 4 Output: " + solution.minIncrementForUnique(nums4)); // Output: 10

        // Additional Example 5
        int[] nums5 = {100000, 100000, 100000};
        System.out.println("Example 5 Output: " + solution.minIncrementForUnique(nums5)); // Output: 3

        // Additional Example 6
        int[] nums6 = {1, 1, 1, 1, 1, 1};
        System.out.println("Example 6 Output: " + solution.minIncrementForUnique(nums6)); // Output: 15

        // Additional Example 7
        int[] nums7 = {10, 10, 10, 10, 10};
        System.out.println("Example 7 Output: " + solution.minIncrementForUnique(nums7)); // Output: 10
    }
}
