/**
 * 904. Fruit Into Baskets
 * Problem:
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * 
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * - You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
 * - Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * - Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * 
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 * 
 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2]. If we had started at the first tree, we would only pick from trees [0,1].
 * 
 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2]. If we had started at the first tree, we would only pick from trees [1,2].
 * 
 * Constraints:
 * - 1 <= fruits.length <= 10^5
 * - 0 <= fruits[i] < fruits.length
 */

 import java.util.HashMap;
 import java.util.Map;
 
 public class FruitIntoBaskets_904 {
 
     /**
      * Solution using two pointers and counting to find the maximum number of fruits
      * that can be collected under the given constraints.
      */
     public int totalFruit(int[] fruits) {
         int max = 0;
         int curMax = 0;
         int prev = -1;
         int prev2 = -1;
         int prevCount = 0;
 
         for (int fruit : fruits) {
             if (fruit == prev || fruit == prev2) {
                 curMax++;
             } else {
                 max = Math.max(max, curMax);
                 curMax = prevCount + 1;
             }
             if (fruit == prev) {
                 prevCount++;
             } else {
                 prevCount = 1;
                 prev2 = prev;
                 prev = fruit;
             }
         }
         return Math.max(max, curMax);
     }
 
     /**
      * Solution using a sliding window and a HashMap to find the maximum number of fruits
      * that can be collected under the given constraints.
      */
     public int totalFruitHashMap(int[] fruits) {
         if (fruits == null || fruits.length == 0) return 0;
 
         Map<Integer, Integer> countMap = new HashMap<>();
         int maxFruits = 0;
         int left = 0;
 
         for (int right = 0; right < fruits.length; right++) {
             countMap.put(fruits[right], countMap.getOrDefault(fruits[right], 0) + 1);
 
             while (countMap.size() > 2) {
                 countMap.put(fruits[left], countMap.get(fruits[left]) - 1);
                 if (countMap.get(fruits[left]) == 0) {
                     countMap.remove(fruits[left]);
                 }
                 left++;
             }
 
             maxFruits = Math.max(maxFruits, right - left + 1);
         }
 
         return maxFruits;
     }
 
     public static void main(String[] args) {
         FruitIntoBaskets_904 solution = new FruitIntoBaskets_904();
 
         int[] fruits1 = {1, 2, 1};
         System.out.println("Maximum number of fruits for test case 1: " + solution.totalFruit(fruits1)); // Output should be 3
 
         int[] fruits2 = {0, 1, 2, 2};
         System.out.println("Maximum number of fruits for test case 2: " + solution.totalFruit(fruits2)); // Output should be 3
 
         int[] fruits3 = {1, 2, 3, 2, 2};
         System.out.println("Maximum number of fruits for test case 3: " + solution.totalFruit(fruits3)); // Output should be 4
     }
 }
 
