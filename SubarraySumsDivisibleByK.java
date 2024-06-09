import java.util.HashMap;
import java.util.Map;

/**
 * 974. Subarray Sums Divisible by K
 * Medium
 * 
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * Example 1:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by k = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * 
 * Example 2:
 * Input: nums = [5], k = 9
 * Output: 0
 * 
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -10^4 <= nums[i] <= 10^4
 * 2 <= k <= 10^4
 */

public class SubarraySumsDivisibleByK {

    /**
     * This method returns the number of non-empty subarrays with a sum divisible by k.
     * 
     * @param nums the input array of integers
     * @param k the integer by which we check if the sum is divisible
     * @return the number of subarrays with a sum divisible by k
     */
    public int subarraysDivByK(int[] nums, int k) {
        // Map to store the frequency of each modulo value
        Map<Integer, Integer> modMap = new HashMap<>();
        // Initialize with mod 0 occurring once to handle subarrays starting from the beginning
        modMap.put(0, 1);
        
        int runningSum = 0;
        int result = 0;
        
        for (int num : nums) {
            runningSum += num;
            int mod = runningSum % k;
            
            // Adjust negative mod to be positive
            if (mod < 0) {
                mod += k;
            }
            
            // If the mod value has been seen before, add the count to the result
            result += modMap.getOrDefault(mod, 0);
            
            // Update the frequency of the current mod value in the map
            modMap.put(mod, modMap.getOrDefault(mod, 0) + 1);
        }
        
        return result;
    }

    public static void main(String[] args) {
        SubarraySumsDivisibleByK solution = new SubarraySumsDivisibleByK();
        
        int[] nums1 = {4, 5, 0, -2, -3, 1};
        int k1 = 5;
        System.out.println(solution.subarraysDivByK(nums1, k1)); // Output: 7
        
        int[] nums2 = {5};
        int k2 = 9;
        System.out.println(solution.subarraysDivByK(nums2, k2)); // Output: 0
    }
}
