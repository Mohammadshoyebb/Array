/**
 * 523. Continuous Subarray Sum
 * Medium
 * 
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 * 
 * A good subarray is a subarray where:
 * - its length is at least two, and
 * - the sum of the elements of the subarray is a multiple of k.
 * 
 * Note that:
 * - A subarray is a contiguous part of the array.
 * - An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 * 
 * Example 1:
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 * 
 * Example 2:
 * Input: nums = [23,2,6,4,7], k = 6
 * Output: true
 * Explanation: [23, 2, 6, 4, 7] is a continuous subarray of size 5 whose elements sum up to 42.
 * 42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
 * 
 * Example 3:
 * Input: nums = [23,2,6,4,7], k = 13
 * Output: false
 * 
 * Constraints:
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= sum(nums[i]) <= 2^31 - 1
 * 1 <= k <= 2^31 - 1
 */


import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // Map to store the first occurrence of each mod value
        Map<Integer, Integer> modMap = new HashMap<>();
        modMap.put(0, -1); // Initialize with mod 0 at index -1 to handle edge cases

        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            
            if (k != 0) {
                runningSum %= k;
            }
            
            if (modMap.containsKey(runningSum)) {
                if (i - modMap.get(runningSum) > 1) {
                    return true;
                }
            } else {
                modMap.put(runningSum, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();
        
        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        System.out.println(solution.checkSubarraySum(nums1, k1)); // Output: true
        
        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;
        System.out.println(solution.checkSubarraySum(nums2, k2)); // Output: true
        
        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;
        System.out.println(solution.checkSubarraySum(nums3, k3)); // Output: false
    }
}

