import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem 932: Beautiful Array
 * 
 * An array nums of length n is beautiful if:
 * 1. nums is a permutation of the integers in the range [1, n].
 * 2. For every 0 <= i < j < n, there is no index k with i < k < j where 2 * nums[k] == nums[i] + nums[j].
 * 
 * Given the integer n, return any beautiful array nums of length n. There will be at least one valid answer for the given n.
 * 
 * Example 1:
 * Input: n = 4
 * Output: [2,1,4,3]
 * 
 * Example 2:
 * Input: n = 5
 * Output: [3,1,2,5,4]
 * 
 * Constraints:
 * - 1 <= n <= 1000
 */
public class Problem932_BeautifulArray {

    // Approach 1: Divide and Conquer without Memoization
    public static int[] beautifulArray(int n) {
        List<Integer> result = constructBeautifulArray(n);
        int[] beautifulArray = new int[n];
        for (int i = 0; i < n; i++) {
            beautifulArray[i] = result.get(i);
        }
        return beautifulArray;
    }
    
    private static List<Integer> constructBeautifulArray(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        while (result.size() < n) {
            List<Integer> temp = new ArrayList<>();
            for (int i : result) {
                if (2 * i - 1 <= n) {
                    temp.add(2 * i - 1);
                }
            }
            for (int i : result) {
                if (2 * i <= n) {
                    temp.add(2 * i);
                }
            }
            result = temp;
        }
        return result;
    }

    // Approach 2: Divide and Conquer with Memoization
    public static int[] beautifulArrayMemoized(int N) {
        Map<Integer, int[]> memo = new HashMap<>();
        return constructBeautifulArrayMemoized(N, memo);
    }

    private static int[] constructBeautifulArrayMemoized(int N, Map<Integer, int[]> memo) {
        if (memo.containsKey(N)) {
            return memo.get(N);
        }

        int[] ans = new int[N];
        if (N == 1) {
            ans[0] = 1;
        } else {
            int t = 0;
            for (int x : constructBeautifulArrayMemoized((N + 1) / 2, memo)) {  // odds
                ans[t++] = 2 * x - 1;
            }
            for (int x : constructBeautifulArrayMemoized(N / 2, memo)) {  // evens
                ans[t++] = 2 * x;
            }
        }
        memo.put(N, ans);
        return ans;
    }

    public static void main(String[] args) {
        int n1 = 4;
        System.out.println("Example 1:");
        System.out.println("Input: n = " + n1);
        System.out.println("Output (Approach 1): " + java.util.Arrays.toString(beautifulArray(n1))); // Expected output: [2,1,4,3] or any valid permutation
        System.out.println("Output (Approach 2): " + java.util.Arrays.toString(beautifulArrayMemoized(n1))); // Expected output: [2,1,4,3] or any valid permutation
        
        int n2 = 5;
        System.out.println("\nExample 2:");
        System.out.println("Input: n = " + n2);
        System.out.println("Output (Approach 1): " + java.util.Arrays.toString(beautifulArray(n2))); // Expected output: [3,1,2,5,4] or any valid permutation
        System.out.println("Output (Approach 2): " + java.util.Arrays.toString(beautifulArrayMemoized(n2))); // Expected output: [3,1,2,5,4] or any valid permutation
    }
}

