/*
826. Most Profit Assigning Work
Medium
Topics
Companies

You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:

difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
Every worker can be assigned at most one job, but one job can be completed multiple times.

For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
Return the maximum profit we can achieve after assigning the workers to the jobs.

Example 1:
Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
Output: 100
Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.

Example 2:
Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
Output: 0

Constraints:
n == difficulty.length
n == profit.length
m == worker.length
1 <= n, m <= 10^4
1 <= difficulty[i], profit[i], worker[i] <= 10^5
*/

import java.util.Arrays;

public class MostProfitAssigningWork {

    // First method using sorting and two-pointer technique
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int m = worker.length;

        // Create a jobs array to store job difficulty and profit together
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        // Sort jobs by difficulty
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        // Sort workers by their ability
        Arrays.sort(worker);

        int maxProfit = 0;
        int i = 0;
        int bestProfit = 0;

        // Iterate over each worker to assign the best possible job
        for (int ability : worker) {
            while (i < n && jobs[i][0] <= ability) {
                bestProfit = Math.max(bestProfit, jobs[i][1]);
                i++;
            }
            maxProfit += bestProfit;
        }

        return maxProfit;
    }

    // Second method using Count Sort approach (frequency array)
    public int maxProfitAssignmentCountSort(int[] difficulty, int[] profit, int[] worker) {
        int maxDifficulty = 0;
        for (int d : difficulty) {
            maxDifficulty = Math.max(maxDifficulty, d);
        }
        for (int w : worker) {
            maxDifficulty = Math.max(maxDifficulty, w);
        }

        int[] maxProfitAtDifficulty = new int[maxDifficulty + 1];

        // Fill the max profit for each difficulty
        for (int i = 0; i < difficulty.length; i++) {
            maxProfitAtDifficulty[difficulty[i]] = Math.max(maxProfitAtDifficulty[difficulty[i]], profit[i]);
        }

        // Propagate the maximum profit to all difficulties
        for (int i = 1; i <= maxDifficulty; i++) {
            maxProfitAtDifficulty[i] = Math.max(maxProfitAtDifficulty[i], maxProfitAtDifficulty[i - 1]);
        }

        int totalProfit = 0;

        // Calculate the total profit by assigning the best job for each worker
        for (int w : worker) {
            totalProfit += maxProfitAtDifficulty[w];
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        MostProfitAssigningWork solution = new MostProfitAssigningWork();

        // Example 1
        int[] difficulty1 = {2, 4, 6, 8, 10};
        int[] profit1 = {10, 20, 30, 40, 50};
        int[] worker1 = {4, 5, 6, 7};
        System.out.println("Example 1 Output: " + solution.maxProfitAssignment(difficulty1, profit1, worker1)); // Output: 100
        System.out.println("Example 1 Count Sort Output: " + solution.maxProfitAssignmentCountSort(difficulty1, profit1, worker1)); // Output: 100

        // Example 2
        int[] difficulty2 = {85, 47, 57};
        int[] profit2 = {24, 66, 99};
        int[] worker2 = {40, 25, 25};
        System.out.println("Example 2 Output: " + solution.maxProfitAssignment(difficulty2, profit2, worker2)); // Output: 0
        System.out.println("Example 2 Count Sort Output: " + solution.maxProfitAssignmentCountSort(difficulty2, profit2, worker2)); // Output: 0

        // Additional test cases
        int[] difficulty3 = {1, 2, 3};
        int[] profit3 = {5, 10, 15};
        int[] worker3 = {2, 2, 2};
        System.out.println("Additional Example 3 Output: " + solution.maxProfitAssignment(difficulty3, profit3, worker3)); // Output: 30
        System.out.println("Additional Example 3 Count Sort Output: " + solution.maxProfitAssignmentCountSort(difficulty3, profit3, worker3)); // Output: 30

        int[] difficulty4 = {1, 2, 3, 4, 5};
        int[] profit4 = {5, 10, 15, 20, 25};
        int[] worker4 = {1, 3, 5, 6};
        System.out.println("Additional Example 4 Output: " + solution.maxProfitAssignment(difficulty4, profit4, worker4)); // Output: 60
        System.out.println("Additional Example 4 Count Sort Output: " + solution.maxProfitAssignmentCountSort(difficulty4, profit4, worker4)); // Output: 60
    }
}
