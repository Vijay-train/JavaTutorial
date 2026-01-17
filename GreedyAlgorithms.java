/**
 * GreedyAlgorithms
 *
 * Greedy algorithms make locally optimal choices at each step, hoping to find
 * a global optimum. They work well for certain problems but not all.
 *
 * Key idea: always pick the best/safest choice right now, without looking back.
 *
 * When greedy works:
 * - Activity Selection: pick activities that don't overlap, earliest end time first.
 * - Huffman Coding: build optimal prefix-free codes by merging smallest-frequency nodes.
 * - Coin Change (min coins): if coin system is "canonical" (like 1,5,10,25), greedy works.
 * - Fractional Knapsack: pick items by best value/weight ratio (you can split items).
 * - Interval Scheduling: maximize non-overlapping intervals by earliest end time.
 * - Gas Station: find if you can complete a loop with given gas and cost at each stop.
 * - Jump Game: check if you can reach the last index by jumping.
 * - Container with Most Water: two pointers, shrink from wider side if no taller wall.
 * - Candy Distribution: ensure each child gets at least 1 candy; more if rated higher than neighbor.
 * - Meeting Rooms: sort by start time, check if any overlap.
 *
 * Problems we'll cover:
 * 1. Activity Selection
 * 2. Coin Change (min coins)
 * 3. Gas Station
 * 4. Jump Game
 * 5. Container with Most Water
 * 6. Candy Distribution
 *
 * Why greedy can fail:
 * - The "best now" choice might block better paths later.
 * - Example: coin change with coins {1,3,4} and amount 6.
 *   Greedy picks 4 first -> needs two 1s = 3 coins total.
 *   Optimal is 3+3 = 2 coins.
 */
public class GreedyAlgorithms {

    public static void main(String[] args) {
        // Demo: Activity Selection
        int[][] activities = {{0, 5}, {1, 3}, {2, 6}, {5, 7}, {8, 9}};
        System.out.println("Activity Selection (start, end): " + java.util.Arrays.deepToString(activities));
        System.out.println("Max non-overlapping: " + activitySelection(activities));

        // Demo: Coin Change (min coins)
        int[] coins = {1, 5, 10, 25};
        int amount = 41;
        System.out.println("\nCoin Change: coins=" + java.util.Arrays.toString(coins) + ", amount=" + amount);
        System.out.println("Min coins (greedy): " + minCoinsGreedy(coins, amount));

        // Demo: Gas Station
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("\nGas Station: gas=" + java.util.Arrays.toString(gas) + ", cost=" + java.util.Arrays.toString(cost));
        System.out.println("Start index: " + gasStation(gas, cost));

        // Demo: Jump Game
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println("\nJump Game:");
        System.out.println("  " + java.util.Arrays.toString(nums1) + " -> " + canJump(nums1));
        System.out.println("  " + java.util.Arrays.toString(nums2) + " -> " + canJump(nums2));

        // Demo: Container with Most Water
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("\nContainer with Most Water: " + java.util.Arrays.toString(height));
        System.out.println("Max area: " + maxArea(height));

        // Demo: Candy Distribution
        int[] ratings = {1, 0, 2};
        System.out.println("\nCandy Distribution: ratings=" + java.util.Arrays.toString(ratings));
        System.out.println("Min candies: " + distributeChocolates(ratings));
    }

    // ----------------------
    // Activity Selection
    // ----------------------

    /**
     * Activity Selection (maximize non-overlapping activities)
     * 
     *  * Problem: given a list of (start, end) times, pick the maximum number of activities
     *   such that no two overlap. 
     *   Two activities overlap if one starts before the other ends.
     *   An activity (a, b) and (b, c) do NOT overlap (same end/start time is ok).
     * 
     * Input Format:
     *   Each activity is represented as a 2D array: (start_time, end_time)
     *   Example: (0,5) means activity starts at time 0 and ends at time 5.
     *   Times are integers representing clock or schedule units.
     *
     * Example 1: activities=[(0,5),(1,3),(2,6),(5,7),(8,9)] -> max 3 activities
     *   Activity details:
     *     (0,5) occupies times 0 to 5
     *     (1,3) occupies times 1 to 3
     *     (2,6) occupies times 2 to 6
     *     (5,7) occupies times 5 to 7
     *     (8,9) occupies times 8 to 9
     *   Selected non-overlapping: (0,5), (5,7), (8,9) [total 3]
     * 
     * Example 2: activities=[(1,2),(2,3),(3,4),(1,3)] -
     *   Activity details:
     *     (1,2) occupies times 1 to 2
     *     (2,3) occupies times 2 to 3
     *     (3,4) occupies times 3 to 4
     *     (1,3) occupies times 1 to 3
     *   Selected non-overlapping: (1,2), (2,3), (3,4) [total 3]
     *
     * Output: integer count of maximum non-overlapping activities selected.
     *

     * 
     * Why greedy works here:
     *   If you always pick the activity that ends earliest, you leave the most room
     *   for future activities. Any other choice would "block" more future options.
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Approach (class steps):
     *   1) Sort activities by end time (ascending) so the earliest-ending come first.
     *   2) Pick the first activity (earliest end).
     *   3) For each remaining activity:
     *        - If it starts at or after the last picked activity's end, pick it.
     *        - Otherwise, skip it (overlaps).
     *   4) Count total picked.
     *
     * Detailed Trace 
     * 
     * (Example 1: [(0,5),(1,3),(2,6),(5,7),(8,9)]):
     *   Input: [(0,5),(1,3),(2,6),(5,7),(8,9)]
     *   Already sorted by end time: [(1,3), (0,5), (2,6), (5,7), (8,9)]
     *   Pick (1,3), lastEnd=3, count=1
     *   Check (0,5): start=0 < 3 -> overlap, skip
     *   Check (2,6): start=2 < 3 -> overlap, skip
     *   Check (5,7): start=5 >= 3 -> no overlap, pick! lastEnd=7, count=2
     *   Check (8,9): start=8 >= 7 -> no overlap, pick! lastEnd=9, count=3
     *   Result: 3 activities selected.
     *
     * Trace (Example 2: [(1,2),(2,3),(3,4),(1,3)]):
     *   Input: [(1,2),(2,3),(3,4),(1,3)]
     *   Sorted: [(1,2), (2,3), (3,4), (1,3)]
     *   Pick (1,2), lastEnd=2, count=1
     *   Check (2,3): start=2 >= 2 -> ok, pick! lastEnd=3, count=2
     *   Check (3,4): start=3 >= 3 -> ok, pick! lastEnd=4, count=3
     *   Check (1,3): start=1 < 4 -> overlap, skip
     *   Result: 3 activities.
     */
    public static int activitySelection(int[][] activities) {
        if (activities == null || activities.length == 0) return 0; // no activities
        
        // Sort by end time (second column)
        java.util.Arrays.sort(activities, (a, b) -> a[1] - b[1]);
        // [(1,3), (0,5), (2,6), (5,7), (8,9)]
        
        int count = 1; // always pick the first (earliest end)
        int lastEnd = activities[0][1]; // remember when it ends
        
        for (int i = 1; i < activities.length; i++) {
            int start = activities[i][0]; // next activity's start
            if (start >= lastEnd) { // no overlap with last picked
                count++; // pick this one
                lastEnd = activities[i][1]; // update last end time
            } // else skip; overlaps with last picked activity
        }
        return count; // total activities picked
    }

    // ----------------------
    // Coin Change (min coins)
    // ----------------------

    /**
    /**
     * Coin Change: min coins to make amount (greedy approach)
     * 
     *   * Problem: given coin denominations and a target amount, find the minimum number
     *   of coins needed. NOTE: this greedy approach only works for "canonical" coin
     *   systems (like real-world currencies). For arbitrary coin sets, use dynamic
     *   programming instead.
     * 
     * Input Format:
     *   coins: array of available coin denominations (positive integers)
     *   amount: target sum to make (positive integer)
     *
     * Output: integer count of minimum coins needed to make the amount.
     * 
     * Example 1 (): coins=[1,5,10,25], amount=41
     *   Input explanation: use coins of value 1, 5, 10, or 25 paise to make 41 paise
     *   Greedy result: 1*25 + 1*10 + 1*5 + 1*1 = 4 coins total [25,10,5,1]
     *   Output: 4
     *
     * Example 2 (non-canonical): coins=[1,3,4], amount=6
     *   Input explanation: use coins of value 1, 3, or 4 units to make 6 units
     *   Greedy result: 1*4 + 2*1 = 3 coins [4,1,1]
     *   Optimal result: 2*3 = 2 coins [3,3]
     *   Output (greedy): 3 (WRONG!)\n     *   Output (optimal): 2
     *

     *
     * Why greedy sometimes works:
     *   For US coins {1,5,10,25}, always using the largest coin possible first
     *   happens to be optimal. But this is NOT guaranteed for all coin systems.
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Approach (greedy, for canonical systems only):
     *   1) Sort coins in descending order (largest first).
     *   2) For each coin, calculate how many of that coin fit into the remaining amount.
     *   3) Use that many coins and reduce the amount.
     *   4) Move to the next smaller coin and repeat.
     *
     * Detailed Trace (Example 1: amount=41, coins=[1,5,10,25]):
     *   Sorted (desc): [25, 10, 5, 1]
     *   Coin 25: 41 / 25 = 1 (use 1), remain = 41 - 25 = 16, count=1
     *   Coin 10: 16 / 10 = 1 (use 1), remain = 16 - 10 = 6, count=2
     *   Coin 5:  6 / 5 = 1 (use 1), remain = 6 - 5 = 1, count=3
     *   Coin 1:  1 / 1 = 1 (use 1), remain = 1 - 1 = 0, count=4
     *   Result: 4 coins total [25,10,5,1]
     *
     * Trace (Example 2 showing failure: amount=6, coins=[1,3,4]):
     *   Sorted (desc): [4, 3, 1]
     *   Coin 4: 6 / 4 = 1 (use 1), remain = 6 - 4 = 2, count=1
     *   Coin 3: 2 / 3 = 0 (use 0), remain = 2, count=1
     *   Coin 1: 2 / 1 = 2 (use 2), remain = 0, count=3
     *   Greedy result: 3 coins [4,1,1]
     *   But optimal is 2 coins [3,3]. Greedy chose wrong!
     */
    public static int minCoinsGreedy(int[] coins, int amount) {
        if (amount <= 0) return 0; // no coins needed
        
        // Sort coins in descending order
        java.util.Arrays.sort(coins);
        // [25, 10, 5, 1] NOT THIS
        // [1,5,10,25]
        int n = coins.length;
        
        int count = 0; // number of coins used
        for (int i = n - 1; i >= 0; i--) { // start from largest coin
            int coin = coins[i];
            int use = amount / coin; // how many of this coin fit
            count += use; // add them to total
            amount -= use * coin; // reduce remaining amount
        }
        return count; // total coins used
    }

    // ----------------------
    // (Homework) Petrol Station
    // ----------------------

    /**
     * Petrol Station: find starting pump to complete the loop
     * 
     * Input Format:
     *   gas: array where gas[i] = liters available at station i
     *   cost: array where cost[i] = liters needed to drive from station i to station (i+1)
     *
     * Output: integer index of starting station, or -1 if impossible.
     * 
     * Example: gas=[1,2,3,4,5], cost=[3,4,5,1,2]
     *   Input explanation:
     *     Station 0: gain 1 liter, spend 3 to reach station 1
     *     Station 1: gain 2 liters, spend 4 to reach station 2
     *     Station 2: gain 3 liters, spend 5 to reach station 3
     *     Station 3: gain 4 liters, spend 1 to reach station 4
     *     Station 4: gain 5 liters, spend 2 to reach station 0 (loop back)
     *   Starting at index 3:
     *   Station 3: gas=4, cost=1 -> 4-1=3 (in tank)
     *   Station 4: gas=5, cost=2 -> 3+5-2=6 (in tank)
     *   Station 0: gas=1, cost=3 -> 6+1-3=4 (in tank)
     *   Station 1: gas=2, cost=4 -> 4+2-4=2 (in tank)
     *   Station 2: gas=3, cost=5 -> 2+3-5=0 (in tank, made it!)
     *   Answer: start at index 3
     *
     * Problem: you have a circular route with N gas stations. At station i:
     *   - You gain gas[i] liters
     *   - You spend cost[i] liters to reach station (i+1)
     *   Find the starting station index where you can complete the entire loop
     *   without ever running out of gas. Return -1 if impossible.
     *
     * Key insight (why greedy works):
     *   If starting at position A, you run out of gas at position B, then starting
     *   at any position between A and B is also hopeless. Why? They all inherit the
     *   deficit from A but start even further behind. So skip directly to A+1.
     *
     * Approach (greedy):
     *   1) Check if total gas >= total cost. If not, no solution exists anywhere.
     *   2) Otherwise, iterate through stations, tracking cumulative surplus (gas-cost).
     *   3) If surplus becomes negative at station i, reset start to i+1 and reset surplus.
     *   4) The final "start" will be the answer.
     *
     * Detailed Trace (gas=[1,2,3,4,5], cost=[3,4,5,1,2]):
     *   total gas = 1+2+3+4+5 = 15
     *   total cost = 3+4+5+1+2 = 15
     *   15 >= 15, so solution exists
     *   
     *   start=0, surplus=0
     *   Station 0: surplus += (1-3) = -2 (negative! reset)
     *   start=1, surplus=0
     *   
     *   Station 1: surplus += (2-4) = -2 (negative! reset)
     *   start=2, surplus=0
     *   
     *   Station 2: surplus += (3-5) = -2 (negative! reset)
     *   start=3, surplus=0
     *   
     *   Station 3: surplus += (4-1) = 3 (positive, ok)
     *   Station 4: surplus += (5-2) = 3+3 = 6 (still positive)
     *   Loop ends, start=3 is the answer.
     */
    public static int gasStation(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) return -1; // no stations
        
        int totalGas = 0; // total gas available
        int totalCost = 0; // total cost to traverse all
        int surplus = 0; // current cumulative surplus
        int start = 0; // candidate starting station
        
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            surplus += gas[i] - cost[i]; // net gas at this station
            
            if (surplus < 0) { // can't continue from current start
                start = i + 1; // try next station as new start
                surplus = 0; // reset surplus
            }
        }
        
        // Check if a solution exists
        if (totalGas < totalCost) return -1; // impossible
        return start; // valid starting station
    }

    // ----------------------
    // (Homework) Jump Game
    // ----------------------

    /**
     * Jump Game: can you reach the last index?
     *
     * 
     *  * Problem: each element nums[i] represents the max number of steps you can jump
     *   from position i. Starting at index 0, determine if you can reach the last index.
     *  
     * Input Format:
     *   nums: array where nums[i] = maximum jump length from index i
     *
     * Output: boolean true if last index is reachable, false otherwise.
     * 
     * Example 1: nums=[2,3,1,1,4]
     *   Input explanation:
     *     Index 0: can jump up to 2 steps (to indices 1 or 2)
     *     Index 1: can jump up to 3 steps (to indices 2, 3, or 4)
     *     Index 2: can jump up to 1 step (to index 3)
     *     Index 3: can jump up to 1 step (to index 4)
     *     Index 4: last index (destination)
     *   Output: true (can reach index 4)
     *
     * Example 2: nums=[3,2,1,0,4]
     *   Start at 0 (jump up to 3 steps)
     *   → jump 3 steps to index 3 (can jump up to 0 steps, stuck)
     *   → cannot go further, last index unreachable
     *   Answer: false
     *

     *
     * Why greedy works:
     *   Tracking the farthest reachable index tells you the "frontier" of all possible
     *   positions. If the current position exceeds the frontier, it's unreachable.
     *
     * Approach (greedy):
     *   1) Initialize farthest = 0 (only index 0 is reachable initially).
     *   2) For each index i from 0 to n-1:
     *        - If i > farthest, return false (can't reach i, so stuck).
     *        - Update farthest = max(farthest, i + nums[i]) (extend frontier).
     *        - If farthest >= n-1, return true (reached the end!).
     *   3) If loop completes without reaching end, return false.
     *
     * Detailed Trace (Example 1: [2,3,1,1,4], n=5):
     *   farthest = 0
     *   i=0: 0 <= 0 (ok), reach = 0+2 = 2, farthest = max(0,2) = 2
     *        2 < 4 (not at end yet)
     *   i=1: 1 <= 2 (ok), reach = 1+3 = 4, farthest = max(2,4) = 4
     *        4 >= 4 (reached end!) return true
     *
     * Trace (Example 2: [3,2,1,0,4], n=5):
     *   farthest = 0
     *   i=0: 0 <= 0 (ok), reach = 0+3 = 3, farthest = max(0,3) = 3
     *        3 < 4 (not at end)
     *   i=1: 1 <= 3 (ok), reach = 1+2 = 3, farthest = max(3,3) = 3
     *        3 < 4 (not at end)
     *   i=2: 2 <= 3 (ok), reach = 2+1 = 3, farthest = max(3,3) = 3
     *        3 < 4 (not at end)
     *   i=3: 3 <= 3 (ok), reach = 3+0 = 3, farthest = max(3,3) = 3
     *        3 < 4 (not at end)
     *   i=4: 4 > 3 (can't reach index 4) return false
     */
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) return true; // already at or past end
        
        int farthest = 0; // farthest index we can currently reach
        
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) return false; // stuck; can't reach index i
            
            int reach = i + nums[i]; // where can we jump to from i?
            farthest = Math.max(farthest, reach); // update farthest
            
            if (farthest >= nums.length - 1) return true; // reached the end
        }
        return false; // shouldn't reach here
    }

    // ----------------------
    // (Homework) Container with Most Water
    // ----------------------

    /**
     * Container with Most Water (maximize area between two vertical lines)
     * 
     * Input Format:
     *   height: array where height[i] = height of vertical bar at index i
     *
     * Output: integer representing maximum water area that can be contained.
     * 
     * Example: nums=[1,8,6,2,5,4,8,3,7]
     *   Input explanation:
     *     Index 0: bar height 1
     *     Index 1: bar height 8
     *     Index 2: bar height 6
     *     ...
     *     Index 8: bar height 7
     *   Best container uses indices 1 and 8:
     *     Width = 8 - 1 = 7
     *     Height (water level) = min(8, 7) = 7
     *     Area = 7 * 7 = 49
     *   Output: 49
     *
     * Problem: given array where each element represents the height of a vertical line,
     *   find two indices i and j such that the container formed by these lines holds
     *   the maximum amount of water. The water area is:
     *   Area = (j - i) * min(height[i], height[j])
     *   where (j-i) is width and min is the water level (bottleneck).
     *
     * Why greedy two-pointer works:
     *   Start with the widest container (maximum width). The area is limited by
     *   the shorter of the two heights. To potentially find a better area, move the
     *   pointer from the shorter side (moving the taller side won't help, it's limited
     *   by the shorter anyway). This shrinks width but might increase height.
     *
     * Approach (greedy, two pointers):
     *   1) Start with left=0, right=n-1 (widest container).
     *   2) Calculate area = (right - left) * min(height[left], height[right]).
     *   3) Track maximum area.
     *   4) Move the pointer with the shorter height (hoping for taller bar that compensates).
     *   5) Repeat until left and right meet.
     *
     * Detailed Trace ([1,8,6,2,5,4,8,3,7]):
     *   left=0 (h=1), right=8 (h=7)
     *     width=8, min(1,7)=1, area=8*1=8, maxArea=8
     *     1 < 7, move left pointer right
     *   
     *   left=1 (h=8), right=8 (h=7)
     *     width=7, min(8,7)=7, area=7*7=49, maxArea=49
     *     8 > 7, move right pointer left
     *   
     *   left=1 (h=8), right=7 (h=3)
     *     width=6, min(8,3)=3, area=6*3=18, maxArea=49 (no improvement)
     *     3 < 8, move right pointer left
     *   
     *   left=1 (h=8), right=6 (h=8)
     *     width=5, min(8,8)=8, area=5*8=40, maxArea=49 (no improvement)
     *     8 = 8, both same, move right pointer left (arbitrary choice)
     *   
     *   (continue shrinking...)
     *   Final result: maxArea = 49
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0; // need at least 2
        
        int maxArea = 0; // track maximum area found
        int left = 0; // left pointer
        int right = height.length - 1; // right pointer
        
        while (left < right) {
            int width = right - left; // distance between pointers
            int h = Math.min(height[left], height[right]); // water level (bottleneck)
            int area = width * h; // area of container
            maxArea = Math.max(maxArea, area); // update max
            
            // Move pointer from shorter side (it's the limit)
            if (height[left] < height[right]) {
                left++; // move left pointer right
            } else {
                right--; // move right pointer left
            }
        }
        return maxArea; // maximum area found
    }

    // ----------------------
    // Chocolate  Distribution
    // ----------------------

    /**
     * Chocolate Distribution: distribute chocolates fairly
     * 
     * * Problem: distribute chocolates to children in a line. 
     * 
     * Input Format:
     *   ratings: array where ratings[i] = rating/score of child i
     *
     * Output: integer representing minimum total chocolates needed to distribute.
     * 
     *  
     *  Constraints:
     *   - Each child must receive at least 1 chocolate.
     *   - If child i has a higher rating than adjacent children, they must get more
     *     chocolates than those adjacent children.
     *   - Minimize total chocolates distributed.
     * 
     * 
     * 
     * Example 1: ratings=[1,0,2]
     *   Input explanation:
     *     Child 0: rating 1
     *     Child 1: rating 0 (lowest rated)
     *     Child 2: rating 2 (highest rated)
     *   Solution: give [2, 1, 2] chocolates respectively
     *   Output: 5 (total chocolates = 2+1+2)
     *
     * Example 2: ratings=[1,2,3]
     *   Child 0 (rating 1): 1 < 2, needs less than child 1
     *   Child 1 (rating 2): 2 > 1 and 2 < 3, needs more than 0 but less than 2
     *   Child 2 (rating 3): 3 > 2, needs more than child 1
     *   
     *   Solution: [1, 2, 3] (total 6)
     *   Each child i gets rating[i] chocolates (happens to work here)
     *

     *
     * Clue - Why two passes work (greedy insight):
     *   A single pass can't guarantee both left and right constraints simultaneously.
     *   Left pass ensures each child beats their left neighbor if rated higher.
     *   Right pass ensures each child beats their right neighbor if rated higher.
     *   Taking the max from both passes satisfies all constraints.
     *
     * Approach (greedy, two passes):
     *   1) Initialize chocolates[i] = 1 for all children (minimum).
     *   2) LEFT PASS (i=1 to n-1):
     *        - If ratings[i] > ratings[i-1], set chocolates[i] = chocolates[i-1] + 1
     *        - This ensures child i beats their left neighbor if needed.
     *   3) RIGHT PASS (i=n-2 down to 0):
     *        - If ratings[i] > ratings[i+1], set chocolates[i] = max(chocolates[i], chocolates[i+1] + 1)
     *        - This ensures child i beats their right neighbor if needed.
     *   4) Sum all chocolates.
     *
     * Detailed Trace (ratings=[1,0,2]):
     *   Initial: chocolates = [1, 1, 1]
     *   
     *   LEFT PASS:
     *   i=1: ratings[1]=0 vs ratings[0]=1 -> 0 < 1, skip
     *   i=2: ratings[2]=2 vs ratings[1]=0 -> 2 > 0, chocolates[2] = 1+1 = 2
     *   After left: chocolates = [1, 1, 2]
     *   
     *   RIGHT PASS:
     *   i=1: ratings[1]=0 vs ratings[2]=2 -> 0 < 2, skip
     *   i=0: ratings[0]=1 vs ratings[1]=0 -> 1 > 0, chocolates[0] = max(1, 1+1) = 2
     *   After right: chocolates = [2, 1, 2]
     *   
     *   Total = 2+1+2 = 5
     */
    public static int distributeChocolates(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0; // no children
        
        int n = ratings.length;
        int[] chocolates = new int[n];
        
        // Initialize all children with 1 chocolate
        for (int i = 0; i < n; i++) chocolates[i] = 1;
        
        // Left pass: if rating increases, give 1 more than left neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) { // higher rated than left
                chocolates[i] = chocolates[i - 1] + 1; // give 1 more
            }
        }
        
        // Right pass: if rating increases (going backward), ensure 1 more than right
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) { // higher rated than right
                chocolates[i] = Math.max(chocolates[i], chocolates[i + 1] + 1); // at least 1 more
            }
        }
        
        // Count total chocolates
        int total = 0;
        for (int i = 0; i < n; i++) total += chocolates[i];
        return total; // total chocolates needed
    }
}
