
/*
330. Patching Array
Hard
Topics
Companies

Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the range [1, n] inclusive can be formed by the sum of some elements in the array.

Return the minimum number of patches required.

Example 1:
Input: nums = [1,3], n = 6
Output: 1
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.

Example 2:
Input: nums = [1,5,10], n = 20
Output: 2
Explanation: The two patches can be [2, 4].

Example 3:
Input: nums = [1,2,2], n = 5
Output: 0

Constraints:
1 <= nums.length <= 1000
1 <= nums[i] <= 10^4
nums is sorted in ascending order.
1 <= n <= 2^31 - 1
*/

public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        long miss = 1; // The smallest number that we can't form
        int added = 0; // Number of patches added
        int i = 0; // Index in nums

        // While the smallest number we can't form is <= n
        while (miss <= n) {
            // If nums[i] is within the current range, extend the range with nums[i]
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                // Patch the array with 'miss' to extend the range
                miss += miss;
                added++;
            }
        }

        return added;
    }

    public static void main(String[] args) {
        PatchingArray patchingArray = new PatchingArray();

        // Example 1
        int[] nums1 = {1, 3};
        int n1 = 6;
        System.out.println("Example 1 Output: " + patchingArray.minPatches(nums1, n1)); // Output: 1

        // Example 2
        int[] nums2 = {1, 5, 10};
        int n2 = 20;
        System.out.println("Example 2 Output: " + patchingArray.minPatches(nums2, n2)); // Output: 2

        // Example 3
        int[] nums3 = {1, 2, 2};
        int n3 = 5;
        System.out.println("Example 3 Output: " + patchingArray.minPatches(nums3, n3)); // Output: 0

        // Additional Example 4
        int[] nums4 = {1, 2, 31, 33};
        int n4 = 2147483647;
        System.out.println("Example 4 Output: " + patchingArray.minPatches(nums4, n4)); // Output: 28
    }
}
