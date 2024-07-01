/*
 * 2190. Most Frequent Number Following Key In an Array
Solved
Easy
Topics
Companies
Hint
You are given a 0-indexed integer array nums. You are also given an integer key, which is present in nums.

For every unique integer target in nums, count the number of times target immediately follows an occurrence of key in nums. In other words, count the number of indices i such that:

0 <= i <= nums.length - 2,
nums[i] == key and,
nums[i + 1] == target.
Return the target with the maximum count. The test cases will be generated such that the target with maximum count is unique.

 

Example 1:

Input: nums = [1,100,200,1,100], key = 1
Output: 100
Explanation: For target = 100, there are 2 occurrences at indices 1 and 4 which follow an occurrence of key.
No other integers follow an occurrence of key, so we return 100.
Example 2:

Input: nums = [2,2,2,2,3], key = 2
Output: 2
Explanation: For target = 2, there are 3 occurrences at indices 1, 2, and 3 which follow an occurrence of key.
For target = 3, there is only one occurrence at index 4 which follows an occurrence of key.
target = 2 has the maximum number of occurrences following an occurrence of key, so we return 2.
 

Constraints:

2 <= nums.length <= 1000
1 <= nums[i] <= 1000
The test cases will be generated such that the answer is unique.
 */



import java.util.Arrays;

public class Problem2190_MostFrequentNumberFollowingKey {

    public static int mostFrequentApproach1(int[] nums, int key) {
        int[] arr = new int[1001];
        int max = Integer.MIN_VALUE;
        int ans = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                arr[nums[i + 1]]++;
                if (max < arr[nums[i + 1]]) {
                    max = arr[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        
        return ans;
    }
    
    public static int mostFrequentApproach2(int[] nums, int key) {
        int[] arr = new int[1001];
        int max = Integer.MIN_VALUE;
        int ans = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == key) {
                arr[nums[i + 1]]++;
                if (max < arr[nums[i + 1]]) {
                    max = arr[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 100, 200, 1, 100};
        int key1 = 1;
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", key = " + key1);
        System.out.println("Output (Approach 1): " + mostFrequentApproach1(nums1, key1));
        System.out.println("Output (Approach 2): " + mostFrequentApproach2(nums1, key1));
        
        int[] nums2 = {2, 2, 2, 2, 3};
        int key2 = 2;
        System.out.println("\nExample 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", key = " + key2);
        System.out.println("Output (Approach 1): " + mostFrequentApproach1(nums2, key2));
        System.out.println("Output (Approach 2): " + mostFrequentApproach2(nums2, key2));
    }
}

