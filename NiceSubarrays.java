import java.util.HashMap;
import java.util.Map;

/**
 * 1248. Count Number of Nice Subarrays
 * Medium
 * 
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 * 
 * Return the number of nice sub-arrays.
 * 
 * Example 1:
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * 
 * Example 2:
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * 
 * Example 3:
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 * 
 * Constraints:
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */

public class NiceSubarrays {

    // Prefix Sum Approach
    public int numberOfSubarraysPrefixSum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        for (int num : nums) {
            prefixSum += num % 2;
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // Sliding Window Approach
    public int numberOfSubarraysSlidingWindow(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private int atMostK(int[] nums, int k) {
        int count = 0;
        int start = 0;
        int oddCount = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] % 2 == 1) {
                k--;
            }
            while (k < 0) {
                if (nums[start] % 2 == 1) {
                    k++;
                }
                start++;
            }
            count += end - start + 1;
        }

        return count;
    }

    // Alternative Method
    public int numberOfSubarraysAlternative(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        cnt[0] = 1;
        int ans = 0, t = 0;
        for (int v : nums) {
            t += v & 1;
            if (t - k >= 0) {
                ans += cnt[t - k];
            }
            cnt[t]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        NiceSubarrays solution = new NiceSubarrays();

        // Example 1
        int[] nums1 = {1, 1, 2, 1, 1};
        int k1 = 3;
        System.out.println("Prefix Sum Approach Output: " + solution.numberOfSubarraysPrefixSum(nums1, k1)); // Output: 2
        System.out.println("Sliding Window Approach Output: " + solution.numberOfSubarraysSlidingWindow(nums1, k1)); // Output: 2
        System.out.println("Alternative Method Output: " + solution.numberOfSubarraysAlternative(nums1, k1)); // Output: 2

        // Example 2
        int[] nums2 = {2, 4, 6};
        int k2 = 1;
        System.out.println("Prefix Sum Approach Output: " + solution.numberOfSubarraysPrefixSum(nums2, k2)); // Output: 0
        System.out.println("Sliding Window Approach Output: " + solution.numberOfSubarraysSlidingWindow(nums2, k2)); // Output: 0
        System.out.println("Alternative Method Output: " + solution.numberOfSubarraysAlternative(nums2, k2)); // Output: 0

        // Example 3
        int[] nums3 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k3 = 2;
        System.out.println("Prefix Sum Approach Output: " + solution.numberOfSubarraysPrefixSum(nums3, k3)); // Output: 16
        System.out.println("Sliding Window Approach Output: " + solution.numberOfSubarraysSlidingWindow(nums3, k3)); // Output: 16
        System.out.println("Alternative Method Output: " + solution.numberOfSubarraysAlternative(nums3, k3)); // Output: 16
    }
}
