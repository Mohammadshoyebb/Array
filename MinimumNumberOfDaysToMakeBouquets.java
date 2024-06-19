/**
 * 1482. Minimum Number of Days to Make m Bouquets
 * 
 * You are given an integer array bloomDay, an integer m and an integer k.
 * 
 * You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 * 
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.
 * 
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden. 
 * If it is impossible to make m bouquets return -1.
 * 
 * Example 1:
 * 
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 * 
 * Example 2:
 * 
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 * 
 * Example 3:
 * 
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 * 
 * Constraints:
 * 
 * bloomDay.length == n
 * 1 <= n <= 105
 * 1 <= bloomDay[i] <= 109
 * 1 <= m <= 106
 * 1 <= k <= n
 */

public class MinimumNumberOfDaysToMakeBouquets{

    // First Method: Using Binary Search
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;

        // If the total number of flowers is less than m * k, it's impossible to make the required bouquets
        if (n < m * k) {
            return -1;
        }

        // Binary search for the minimum number of days
        int left = 1; // Minimum possible day
        int right = (int) 1e9; // Maximum possible day

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canMakeBouquets(bloomDay, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Check if the left boundary can indeed make the required bouquets
        return canMakeBouquets(bloomDay, m, k, left) ? left : -1;
    }

    // Helper function to check if we can make the required number of bouquets by a given day
    private boolean canMakeBouquets(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0;
        int flowers = 0;

        for (int bloom : bloomDay) {
            if (bloom <= day) {
                flowers++;
                if (flowers == k) {
                    bouquets++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
            if (bouquets >= m) {
                return true;
            }
        }
        return false;
    }

    // Second Method: Using Count Sort Approach (frequency array)
    int[] arr;
    int m, k;
    public int minDaysUsingCountSort(int[] arr, int m, int k) {
        if (m * k < 0 || m * k > arr.length)
            return -1;

        this.m = m;
        this.k = k;
        this.arr = arr;

        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            max = Math.max(max, arr[i]);

        int st = 1, en = max, md;

        while (st <= en) {
            md = (st + en) / 2;
            if (count(md)) {
                en = md - 1;
            } else {
                st = md + 1;
            }
        }
        return st;
    }

    boolean count(int val) {
        int temp = 0, temp2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= val) {
                temp++;
                if (temp == k) {
                    temp = 0;
                    temp2++;
                    if (temp2 == m)
                        return true;
                }
            } else {
                if ((arr.length - i) / k < m - temp2)
                    return false;
                temp = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MinimumNumberOfDaysToMakeBouquets solution = new MinimumNumberOfDaysToMakeBouquets();

        // Example 1
        int[] bloomDay1 = {1, 10, 3, 10, 2};
        int m1 = 3;
        int k1 = 1;
        System.out.println("Example 1 Output: " + solution.minDays(bloomDay1, m1, k1)); // Output: 3

        // Example 2
        int[] bloomDay2 = {1, 10, 3, 10, 2};
        int m2 = 3;
        int k2 = 2;
        System.out.println("Example 2 Output: " + solution.minDays(bloomDay2, m2, k2)); // Output: -1

        // Example 3
        int[] bloomDay3 = {7, 7, 7, 7, 12, 7, 7};
        int m3 = 2;
        int k3 = 3;
        System.out.println("Example 3 Output: " + solution.minDays(bloomDay3, m3, k3)); // Output: 12

        // Additional test case
        int[] bloomDay4 = {70545, 40667, 26392, 42712, 39599, 8012, 27194, 71384, 58079, 2123, 66655, 48459, 92802, 16345, 43374, 15924, 5480, 48766, 38512, 44416, 50530, 14405, 42803, 4953, 44480, 31455, 12440, 72556, 3593, 74130, 59278, 72043, 9508, 66855, 74237, 46991, 53829, 61978, 8137, 47408, 18152, 3439, 20331, 28085, 43180, 36650, 6053, 62782, 91043, 32640, 62145, 79424, 32256, 89353, 96289, 85104, 91235, 80088, 96025, 59996, 99511, 24387, 36850, 21709, 21253, 45745, 46148, 80258, 9365, 27085, 11183, 38053, 44747, 24038, 91223, 32454, 58318, 77940, 20208, 98572, 842, 31307, 90663, 46331, 8786, 32234, 24599, 30552, 78551, 67424, 19443, 45458, 49450, 37665, 79859, 70847, 30777, 96178, 96183, 3153, 98172, 11893, 35919, 10268, 21934, 2335, 50721, 26460, 73480, 27509, 19905, 83061, 64141, 28707, 73406, 30908, 80395, 26159, 41057, 91277, 5606, 5661, 88593, 16020, 69632, 8323, 15448, 15620, 70886, 54549, 73260, 7253, 60911, 91137, 20479, 12094, 8855, 1836, 28300, 41499, 91819, 73008, 74394, 5665, 52219, 15620, 54739, 52384, 58029, 92968, 17981, 62083, 91981, 96508, 19249, 60972, 39521, 13830, 15488, 6608, 13736, 51362, 27864, 52448, 51494, 79885, 95293, 3884, 70578, 22710, 744};
        int m4 = 89945;
        int k4 = 32127;
        System.out.println("Additional Test Case Output: " + solution.minDays(bloomDay4, m4, k4)); // Expected: -1
    }
}
