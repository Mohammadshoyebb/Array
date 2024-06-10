import java.util.Arrays;

/**
 * 1051. Height Checker
 * Easy
 * 
 * A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
 * 
 * You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).
 * 
 * Return the number of indices where heights[i] != expected[i].
 * 
 * Example 1:
 * Input: heights = [1,1,4,2,1,3]
 * Output: 3
 * Explanation: 
 * heights:  [1,1,4,2,1,3]
 * expected: [1,1,1,2,3,4]
 * Indices 2, 4, and 5 do not match.
 * 
 * Example 2:
 * Input: heights = [5,1,2,3,4]
 * Output: 5
 * Explanation:
 * heights:  [5,1,2,3,4]
 * expected: [1,2,3,4,5]
 * All indices do not match.
 * 
 * Example 3:
 * Input: heights = [1,2,3,4,5]
 * Output: 0
 * Explanation:
 * heights:  [1,2,3,4,5]
 * expected: [1,2,3,4,5]
 * All indices match.
 * 
 * Constraints:
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 */

public class HeightChecker {

    /**
     * This method returns the number of indices where the heights[i] != expected[i].
     * This method uses sorting to find the expected order.
     * 
     * @param heights the current order of students by height
     * @return the number of indices where heights[i] does not match the expected order
     */
    public int heightCheckerSorting(int[] heights) {
        // Copy the original heights array to get the expected order
        int[] expected = Arrays.copyOf(heights, heights.length);
        // Sort the expected array
        Arrays.sort(expected);
        
        int mismatchCount = 0;
        // Compare the original heights with the sorted expected heights
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                mismatchCount++;
            }
        }
        
        return mismatchCount;
    }

    /**
     * This method returns the number of indices where the heights[i] != expected[i].
     * This method uses counting sort to find the expected order more efficiently.
     * 
     * @param heights the current order of students by height
     * @return the number of indices where heights[i] does not match the expected order
     */
    public int heightCheckerCountingSort(int[] heights) {
        int[] heightCounts = new int[101]; // We need 101 because height is from 1 to 100

        // Count the occurrences of each height
        for (int height : heights) {
            heightCounts[height]++;
        }

        int mismatchCount = 0;
        int currentHeight = 0;

        // Iterate through the original heights array to compare with the expected order
        for (int height : heights) {
            // Find the next height that should appear in the sorted order
            while (heightCounts[currentHeight] == 0) {
                currentHeight++;
            }
            // If the current height in the sorted order does not match the height in original array
            if (height != currentHeight) {
                mismatchCount++;
            }
            // Decrement the count for the current height
            heightCounts[currentHeight]--;
        }

        return mismatchCount;
    }

    public static void main(String[] args) {
        HeightChecker solution = new HeightChecker();

        int[] heights1 = {1, 1, 4, 2, 1, 3};
        System.out.println("Sorting Method: " + solution.heightCheckerSorting(heights1)); // Output: 3
        System.out.println("Counting Sort Method: " + solution.heightCheckerCountingSort(heights1)); // Output: 3

        int[] heights2 = {5, 1, 2, 3, 4};
        System.out.println("Sorting Method: " + solution.heightCheckerSorting(heights2)); // Output: 5
        System.out.println("Counting Sort Method: " + solution.heightCheckerCountingSort(heights2)); // Output: 5

        int[] heights3 = {1, 2, 3, 4, 5};
        System.out.println("Sorting Method: " + solution.heightCheckerSorting(heights3)); // Output: 0
        System.out.println("Counting Sort Method: " + solution.heightCheckerCountingSort(heights3)); // Output: 0
    }
}
