
/*
633. Sum of Square Numbers
Medium
Topics
Companies

Given a non-negative integer c, decide whether there're two integers a and b such that a^2 + b^2 = c.

Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5

Example 2:
Input: c = 3
Output: false

Constraints:
0 <= c <= 2^31 - 1
*/

public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        // Initialize two pointers
        long left = 0; // Start pointer
        long right = (long)Math.sqrt(c); // End pointer

        // Two-pointer technique to find two numbers whose squares sum up to c
        while (left <= right) {
            long sum = left * left + right * right;

            if (sum == c) {
                return true; // Found two numbers a and b such that a^2 + b^2 = c
            } else if (sum < c) {
                left++; // Increase the left pointer to increase the sum
            } else {
                right--; // Decrease the right pointer to decrease the sum
            }
        }

        return false; // No such pair found
    }

    public static void main(String[] args) {
        SumOfSquareNumbers solution = new SumOfSquareNumbers();

        // Example 1
        int c1 = 5;
        System.out.println("Example 1 Output: " + solution.judgeSquareSum(c1)); // Output: true

        // Example 2
        int c2 = 3;
        System.out.println("Example 2 Output: " + solution.judgeSquareSum(c2)); // Output: false

        // Additional test cases
        int c3 = 4;
        System.out.println("Additional Example 3 Output: " + solution.judgeSquareSum(c3)); // Output: true (0^2 + 2^2 = 4)

        int c4 = 2;
        System.out.println("Additional Example 4 Output: " + solution.judgeSquareSum(c4)); // Output: true (1^2 + 1^2 = 2)

        int c5 = 1;
        System.out.println("Additional Example 5 Output: " + solution.judgeSquareSum(c5)); // Output: true (0^2 + 1^2 = 1)

        int c6 = 0;
        System.out.println("Additional Example 6 Output: " + solution.judgeSquareSum(c6)); // Output: true (0^2 + 0^2 = 0)
    }
}
