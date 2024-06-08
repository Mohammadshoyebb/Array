import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 
 * Example 2:
 * Input: nums = [0]
 * Output: [[],[0]]
 * 
 * Constraints:
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * All the numbers of nums are unique.
 */
public class SubsetsSolution {

    // Method to generate all subsets using bit manipulation
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        
        // Loop over all possible subsets (2^n possibilities)
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> currentSubset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                // Check if the j-th bit in i is set
                if ((i & (1 << j)) != 0) {
                    currentSubset.add(nums[j]);
                }
            }
            // Add the current subset to the list of subsets
            ans.add(currentSubset);
        }
        return ans;
    }
    
    // Method to generate all subsets using recursion and backtracking
    public List<List<Integer>> subsetsRecursion(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void generateSubsets(int index, int[] nums, List<Integer> current, List<List<Integer>> ans) {
        // Add the current subset to the list of subsets
        ans.add(new ArrayList<>(current));
        
        // Iterate through the remaining elements
        for (int i = index; i < nums.length; i++) {
            // Include nums[i] in the current subset
            current.add(nums[i]);
            // Move to the next element
            generateSubsets(i + 1, nums, current, ans);
            // Backtrack: remove nums[i] from the current subset
            current.remove(current.size() - 1);
        }
    }

    // Main method to test the above functions
    public static void main(String[] args) {
        SubsetsSolution solution = new SubsetsSolution();
        
        int[] nums1 = {1, 2, 3};
        System.out.println("Subsets using bit manipulation: " + solution.subsets(nums1));
        System.out.println("Subsets using recursion and backtracking: " + solution.subsetsRecursion(nums1));
        
        int[] nums2 = {0};
        System.out.println("Subsets using bit manipulation: " + solution.subsets(nums2));
        System.out.println("Subsets using recursion and backtracking: " + solution.subsetsRecursion(nums2));
    }
}
