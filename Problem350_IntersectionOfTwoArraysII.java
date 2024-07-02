import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Problem 350: Intersection of Two Arrays II
 * 
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 * 
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * 
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9] (Explanation: [9,4] is also accepted.)
 * 
 * Constraints:
 * - 1 <= nums1.length, nums2.length <= 1000
 * - 0 <= nums1[i], nums2[i] <= 1000
 */
public class Problem350_IntersectionOfTwoArraysII {

    /**
     * Approach 1: Using HashMap to track occurrences of targets following key.
     * 
     * @param nums1 The input array of integers.
     * @param nums2 The input array of integers.
     * @return The array representing the intersection of nums1 and nums2.
     */
    public static int[] intersectWithHashMap(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        
        // Count the occurrences of each element in nums1
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Find the intersection with nums2
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1); // Decrease the count in the map
            }
        }
        
        // Convert the result list to an array
        int[] intersection = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersection[i] = result.get(i);
        }
        
        return intersection;
    }

    /**
     * Approach 2: Using frequency array to track occurrences.
     * 
     * @param nums1 The input array of integers.
     * @param nums2 The input array of integers.
     * @return The array representing the intersection of nums1 and nums2.
     */
    public static int[] intersectWithFrequencyArray(int[] nums1, int[] nums2) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] freqArr = new int[1001];

        // Count the occurrences of each element in nums1
        for (int num : nums1) {
            freqArr[num]++;
        }
        
        // Find the intersection with nums2
        for (int num : nums2) {
            if (freqArr[num] > 0) {
                freqArr[num]--;
                result.add(num);               
            }
        }

        // Convert the result list to an array
        int size = result.size();
        int[] intersection = new int[size];
        for (int i = 0; i < size; i++) {
            intersection[i] = result.get(i);
        }
        
        return intersection;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        System.out.println("Example 1:");
        System.out.println("Input: nums1 = [1,2,2,1], nums2 = [2,2]");
        System.out.println("Output with HashMap: " + java.util.Arrays.toString(intersectWithHashMap(nums1, nums2))); // Expected output: [2, 2]
        System.out.println("Output with Frequency Array: " + java.util.Arrays.toString(intersectWithFrequencyArray(nums1, nums2))); // Expected output: [2, 2]
        
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        System.out.println("\nExample 2:");
        System.out.println("Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]");
        System.out.println("Output with HashMap: " + java.util.Arrays.toString(intersectWithHashMap(nums3, nums4))); // Expected output: [4, 9] or [9, 4]
        System.out.println("Output with Frequency Array: " + java.util.Arrays.toString(intersectWithFrequencyArray(nums3, nums4))); // Expected output: [4, 9] or [9, 4]
    }
}

