/**
 * 55. Jump Game
 * Solved
 * Medium
 * Topics
 * Companies
 * 
 * You are given an integer array nums. You are initially positioned at the array's first index, 
 * and each element in the array represents your maximum jump length at that position.
 * 
 * Return true if you can reach the last index, or false otherwise.
 * 
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * 
 * Example 2:
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, 
 * which makes it impossible to reach the last index.
 * 
 * Constraints:
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 105
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int destination = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if ((nums[i] + i) >= destination) {
                destination = i;
            }
        }
        return destination == 0;
    }

    public static void main(String[] args) {
        JumpGame solution = new JumpGame();

        // Test cases
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(solution.canJump(nums1)); // Output: true

        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(solution.canJump(nums2)); // Output: false

        int[] nums3 = {0};
        System.out.println(solution.canJump(nums3)); // Output: true

        int[] nums4 = {2, 0, 0};
        System.out.println(solution.canJump(nums4)); // Output: true

        int[] nums5 = {1, 2, 3, 4, 5};
        System.out.println(solution.canJump(nums5)); // Output: true

        int[] nums6 = {2, 5, 0, 0};
        System.out.println(solution.canJump(nums6)); // Output: true

        int[] nums7 = {1, 0, 1, 0};
        System.out.println(solution.canJump(nums7)); // Output: false

        int[] nums8 = {1, 0};
        System.out.println(solution.canJump(nums8)); // Output: true
    }
}

