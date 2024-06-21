

    /**
     * 1052. Grumpy Bookstore Owner
     * Medium
     * 
     * There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store.
     * You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute 
     * and all those customers leave after the end of that minute.
     * 
     * On some minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute,
     * and is 0 otherwise.
     * 
     * When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
     * 
     * The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.
     * 
     * Return the maximum number of customers that can be satisfied throughout the day.
     * 
     * Example 1:
     * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
     * Output: 16
     * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes. 
     * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     * 
     * Example 2:
     * Input: customers = [1], grumpy = [0], minutes = 1
     * Output: 1
     * 
     * Constraints:
     * n == customers.length == grumpy.length
     * 1 <= minutes <= n <= 2 * 10^4
     * 0 <= customers[i] <= 1000
     * grumpy[i] is either 0 or 1.
     */
public class GrumpyBookstoreOwner {
    
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length;
        int satisfiedCustomers = 0;
        
        // Calculate initially satisfied customers
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                satisfiedCustomers += customers[i];
            }
        }
        
        // Calculate the number of customers that can be converted to satisfied customers
        int additionalCustomers = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                additionalCustomers += customers[i];
            }
        }
        
        int maxAdditionalCustomers = additionalCustomers;
        
        // Sliding window to find the maximum additional customers that can be satisfied
        for (int i = minutes; i < n; i++) {
            if (grumpy[i] == 1) {
                additionalCustomers += customers[i];
            }
            if (grumpy[i - minutes] == 1) {
                additionalCustomers -= customers[i - minutes];
            }
            maxAdditionalCustomers = Math.max(maxAdditionalCustomers, additionalCustomers);
        }
        
        return satisfiedCustomers + maxAdditionalCustomers;
    }

    public static void main(String[] args) {
        GrumpyBookstoreOwner solution = new GrumpyBookstoreOwner();
        
        // Example 1
        int[] customers1 = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy1 = {0, 1, 0, 1, 0, 1, 0, 1};
        int minutes1 = 3;
        System.out.println("Example 1 Output: " + solution.maxSatisfied(customers1, grumpy1, minutes1));  // Output: 16
        
        // Example 2
        int[] customers2 = {1};
        int[] grumpy2 = {0};
        int minutes2 = 1;
        System.out.println("Example 2 Output: " + solution.maxSatisfied(customers2, grumpy2, minutes2));  // Output: 1
    }
}
