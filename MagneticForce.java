
/**
 * 1552. Magnetic Force Between Two Balls
 * Medium
 * Topics: Binary Search, Sorting, Greedy
 * 
 * In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls 
 * if they are put in his new invented basket. Rick has n empty baskets, the ith basket is at position[i]. 
 * Morty has m balls and needs to distribute the balls into the baskets such that the minimum magnetic 
 * force between any two balls is maximum.
 * 
 * Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
 * 
 * Given the integer array position and the integer m. Return the required force.
 * 
 * Example 1:
 * 
 * Input: position = [1,2,3,4,7], m = 3
 * Output: 3
 * Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
 * 
 * Example 2:
 * 
 * Input: position = [5,4,3,2,1,1000000000], m = 2
 * Output: 999999999
 * Explanation: We can use baskets 1 and 1000000000.
 * 
 * Constraints:
 * 
 * n == position.length
 * 2 <= n <= 105
 * 1 <= position[i] <= 109
 * All integers in position are distinct.
 * 2 <= m <= position.length
 */
import java.util.Arrays;
 public class MagneticForce {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        
        int start = 1;
        int end = (position[position.length - 1] - position[0]) / (m - 1);
        while (start <= end) {
            int mid = (end + start) / 2;
            int req = dist(position, mid);
            if (req >= m) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    public int dist(int[] nums, int mid) {
        int count = 1;
        int last = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - last >= mid) {
                count++;
                last = nums[i];
            }
        }
        return count;
    }
    public static void main(String[] args) {
        MagneticForce solution = new MagneticForce();
        
        // Example 1
        int[] position1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println(solution.maxDistance(position1, m1));  // Output: 3
        
        // Example 2
        int[] position2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println(solution.maxDistance(position2, m2));  // Output: 999999999
    }
}

