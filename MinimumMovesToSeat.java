/*
2037. Minimum Number of Moves to Seat Everyone
Solved
Easy
Topics
Companies
Hint
There are n seats and n students in a room. You are given an array seats of length n, where seats[i] is the position of the ith seat. You are also given the array students of length n, where students[j] is the position of the jth student.

You may perform the following move any number of times:

Increase or decrease the position of the ith student by 1 (i.e., moving the ith student from position x to x + 1 or x - 1)
Return the minimum number of moves required to move each student to a seat such that no two students are in the same seat.

Note that there may be multiple seats or students in the same position at the beginning.

Example 1:

Input: seats = [3,1,5], students = [2,7,4]
Output: 4
Explanation: The students are moved as follows:
- The first student is moved from position 2 to position 1 using 1 move.
- The second student is moved from position 7 to position 5 using 2 moves.
- The third student is moved from position 4 to position 3 using 1 move.
In total, 1 + 2 + 1 = 4 moves were used.
Example 2:

Input: seats = [4,1,5,9], students = [1,3,2,6]
Output: 7
Explanation: The students are moved as follows:
- The first student is not moved.
- The second student is moved from position 3 to position 4 using 1 move.
- The third student is moved from position 2 to position 5 using 3 moves.
- The fourth student is moved from position 6 to position 9 using 3 moves.
In total, 0 + 1 + 3 + 3 = 7 moves were used.
Example 3:

Input: seats = [2,2,6,6], students = [1,3,2,6]
Output: 4
Explanation: Note that there are two seats at position 2 and two seats at position 6.
The students are moved as follows:
- The first student is moved from position 1 to position 2 using 1 move.
- The second student is moved from position 3 to position 6 using 3 moves.
- The third student is not moved.
- The fourth student is not moved.
In total, 1 + 3 + 0 + 0 = 4 moves were used.

Constraints:

n == seats.length == students.length
1 <= n <= 100
1 <= seats[i], students[j] <= 100
*/

public class MinimumMovesToSeat {

    // Helper method to find the maximum value in an array
    public int countSize(int arr[]) {
        int max = 0;         
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    // Main method to calculate the minimum number of moves to seat everyone
    public int minMovesToSeat(int[] seats, int[] students) {
        // Find the maximum values in seats and students arrays
        int seatsCount = countSize(seats);
        int studentCount = countSize(students);

        // Create count arrays for seats and students
        int countSeat[] = new int[seatsCount + 1];
        int countStu[] = new int[studentCount + 1];

        int count = students.length;
            
        // Fill count arrays
        for (int i = 0; i < seats.length; i++) {
            countSeat[seats[i]]++;
            countStu[students[i]]++;
        }

        int moves = 0;
        int i = 0;
        int j = 0;

        // Calculate the minimum moves
        while (count > 0) {
            // Find the next available seat and student
            while (countSeat[i] == 0)
                i++;
            while (countStu[j] == 0)
                j++;

            // Decrement the count of the current seat and student
            countSeat[i]--;
            countStu[j]--;

            // Calculate the moves and decrement the total count
            moves += Math.abs(i - j);
            count--;
        }
        return moves;            
    }

    public static void main(String[] args) {
        MinimumMovesToSeat solution = new MinimumMovesToSeat();

        // Example 1
        int[] seats1 = {3, 1, 5};
        int[] students1 = {2, 7, 4};
        System.out.println("Example 1 Output: " + solution.minMovesToSeat(seats1, students1)); // Output: 4

        // Example 2
        int[] seats2 = {4, 1, 5, 9};
        int[] students2 = {1, 3, 2, 6};
        System.out.println("Example 2 Output: " + solution.minMovesToSeat(seats2, students2)); // Output: 7

        // Example 3
        int[] seats3 = {2, 2, 6, 6};
        int[] students3 = {1, 3, 2, 6};
        System.out.println("Example 3 Output: " + solution.minMovesToSeat(seats3, students3)); // Output: 4
    }
}
