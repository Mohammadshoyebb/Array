/**
 * 1550. Three Consecutive Odds
 * Solved
 * Easy
 * 
 * Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.
 * 
 * Example 1:
 * Input: arr = [2,6,4,1]
 * Output: false
 * Explanation: There are no three consecutive odds.
 * 
 * Example 2:
 * Input: arr = [1,2,34,3,4,5,7,23,12]
 * Output: true
 * Explanation: [5,7,23] are three consecutive odds.
 * 
 * Constraints:
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 */

 public class ThreeConsecutiveOdds {
    // Sliding window approach
    public boolean threeConsecutiveOddsSlidingWindow(int[] arr) {
        if (arr.length < 3) return false;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i] % 2 != 0) {
                count++;
            }
            if (count == 3) return true;
        }

        // Solve for the remaining window
        for (int i = 1; i <= arr.length - 3; i++) {
            if (arr[i - 1] % 2 != 0) {
                count--;
            }
            if (arr[i + 2] % 2 != 0) {
                count++;
            }
            if (count == 3) return true;
        }
        return false;
    }

    // Count pointer approach
    public boolean threeConsecutiveOddsCountPointer(int[] arr) {
        if (arr.length < 3) return false;
        int n = arr.length;
        if (n == 4 && (arr[1] % 2 != 0 || arr[2] % 2 != 0)) return false;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                count = 0;
                continue;
            }
            count++;
            if (count >= 3) return true;
        }
        return false;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        ThreeConsecutiveOdds solution = new ThreeConsecutiveOdds();
        
        int[] test1 = {2, 6, 4, 1};
        System.out.println(solution.threeConsecutiveOddsSlidingWindow(test1)); // Output: false
        System.out.println(solution.threeConsecutiveOddsCountPointer(test1)); // Output: false

        int[] test2 = {1, 2, 34, 3, 4, 5, 7, 23, 12};
        System.out.println(solution.threeConsecutiveOddsSlidingWindow(test2)); // Output: true
        System.out.println(solution.threeConsecutiveOddsCountPointer(test2)); // Output: true
    }
}

