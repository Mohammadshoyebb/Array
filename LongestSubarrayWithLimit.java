import java.util.Deque;
import java.util.LinkedList;

/**
 * 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 * Medium
 *
 * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray
 * such that the absolute difference between any two elements of this subarray is less than or equal to limit.
 *
 * Example 1:
 * Input: nums = [8,2,4,7], limit = 4
 * Output: 2 
 * Explanation: All subarrays are:
 * [8] with maximum absolute diff |8-8| = 0 <= 4.
 * [8,2] with maximum absolute diff |8-2| = 6 > 4. 
 * [8,2,4] with maximum absolute diff |8-2| = 6 > 4.
 * [8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
 * [2] with maximum absolute diff |2-2| = 0 <= 4.
 * [2,4] with maximum absolute diff |2-4| = 2 <= 4.
 * [2,4,7] with maximum absolute diff |2-7| = 5 > 4.
 * [4] with maximum absolute diff |4-4| = 0 <= 4.
 * [4,7] with maximum absolute diff |4-7| = 3 <= 4.
 * [7] with maximum absolute diff |7-7| = 0 <= 4. 
 * Therefore, the size of the longest subarray is 2.
 *
 * Example 2:
 * Input: nums = [10,1,2,4,7,2], limit = 5
 * Output: 4 
 * Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
 *
 * Example 3:
 * Input: nums = [4,2,2,2,4,4,2,2], limit = 0
 * Output: 3
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 0 <= limit <= 109
 */

public class LongestSubarrayWithLimit {

    // Sliding window with deques
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain decreasing order in maxDeque
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(nums[right]);

            // Maintain increasing order in minDeque
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(nums[right]);

            // Ensure the absolute difference within the limit
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    // Static deques approach
    static int[] maxDeque;
    static int[] minDeque;
    static int maxHead;
    static int maxTail;
    static int minHead;
    static int minTail;

    // Maintain max val of current sliding window in head of deque
    // Maintain min val of current sliding window in head of deque
    public int longestSubarrayWithStaticDeques(int[] nums, int limit) {
        int n = nums.length;
        maxDeque = new int[n];
        minDeque = new int[n];

        // queue range [h,t)
        maxHead = 0;
        maxTail = 0;
        minHead = 0;
        minTail = 0;

        int res = 0;
        int r = 0;
        for (int l = 0; l < n; l++) {
            // window is [l,r), check if add r to window still meet condition
            while (r < n && withinLimit(nums, nums[r], limit)) {
                addToLast(nums, r);
                r++;
            }
            // [l,r) is max len of a valid window using l as start 
            res = Math.max(res, r - l);

            removeExpired(l);
        }

        return res;
    }

    public boolean withinLimit(int[] nums, int num, int limit) {
        int max = maxHead < maxTail ? Math.max(nums[maxDeque[maxHead]], num) : num;
        int min = minHead < minTail ? Math.min(nums[minDeque[minHead]], num) : num;

        return max - min <= limit;
    }

    public void addToLast(int[] nums, int r) {
        // add to max deque 
        while (maxHead < maxTail && nums[maxDeque[maxTail - 1]] <= nums[r]) {
            maxTail--;
        }
        maxDeque[maxTail++] = r;

        // add to min deque
        while (minHead < minTail && nums[minDeque[minTail - 1]] >= nums[r]) {
            minTail--;
        }
        minDeque[minTail++] = r;
    }

    public void removeExpired(int l) {
        if (maxDeque[maxHead] == l) {
            maxHead++;
        }

        if (minDeque[minHead] == l) {
            minHead++;
        }
    }

    public static void main(String[] args) {
        LongestSubarrayWithLimit solution = new LongestSubarrayWithLimit();

        // Example 1
        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println(solution.longestSubarray(nums1, limit1)); // Output: 2

        // Example 2
        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println(solution.longestSubarray(nums2, limit2)); // Output: 4

        // Example 3
        int[] nums3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println(solution.longestSubarray(nums3, limit3)); // Output: 3

        // Using the static deques approach
        System.out.println(solution.longestSubarrayWithStaticDeques(nums1, limit1)); // Output: 2
        System.out.println(solution.longestSubarrayWithStaticDeques(nums2, limit2)); // Output: 4
        System.out.println(solution.longestSubarrayWithStaticDeques(nums3, limit3)); // Output: 3
    }
}
