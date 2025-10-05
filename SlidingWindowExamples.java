import java.util.*;


public class SlidingWindowExamples {

    // ------------------------------------------------------------
    // 1) Minimum Sum of Subarray of Size k (Fixed Window)
    // ------------------------------------------------------------
    /**
     * Returns the minimum sum among all contiguous subarrays of size k.
     * Time: O(n), Space: O(1)
     */
    public static int minSumSubarraySizeK(int[] nums, int k) {
        if (k <= 0 || k > nums.length) return 0;

        int windowSum = 0, minSum = Integer.MAX_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
            windowSum += nums[i];
            if (i >= k) windowSum -= nums[i - k];
            if (i >= k - 1) minSum = Math.min(minSum, windowSum);
        }
        return minSum;
    }

    // ------------------------------------------------------------
    // 2) Count Subarrays with Sum Exactly k (Variable, Non-negative nums)
    // ------------------------------------------------------------
    /**
     * Counts the number of subarrays whose sum equals k.
     * NOTE: This two-pointer method assumes all nums are non-negative.
     * Time: O(n), Space: O(1)
     *
     * Trace idea:
     * - Expand right and add to sum.
     * - While sum > k, shrink from left.
     * - If sum == k after shrinking, count++ (for the current window ending at r).
     */
    public static int countSubarraysSumK(int[] nums, int k) {
        int l = 0, sum = 0, count = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (l <= r && sum > k) sum -= nums[l++];
            if (sum == k) count++;
        }
        return count;
    }

    // ------------------------------------------------------------
    // 3) Number of Subarrays with Product < k (Variable, Positive nums)
    // ------------------------------------------------------------
    /**
     * Returns the number of contiguous subarrays where product < k.
     * Requires: all nums > 0; if k <= 1, answer is 0.
     * Time: O(n), Space: O(1)
     *
     * Trace idea:
     * - Multiply in nums[r].
     * - While product >= k, divide out nums[l] and advance l.
     * - All subarrays ending at r and starting from [l..r] are valid: add (r - l + 1).
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        long prod = 1;
        int l = 0, count = 0;
        for (int r = 0; r < nums.length; r++) {
            prod *= nums[r];
            while (l <= r && prod >= k) prod /= nums[l++];
            count += (r - l + 1);
        }
        return count;
    }

    // ------------------------------------------------------------
    // 4) Smallest Subarray with Sum >= S (Variable, Positive nums)
    // ------------------------------------------------------------
    /**
     * Returns the minimum length of a subarray with sum >= target. If none, returns 0.
     * Requires: non-negative nums for correctness with this two-pointer approach.
     * Time: O(n), Space: O(1)
     *
     * Trace idea:
     * - Expand sum until >= target.
     * - Then shrink from left to minimize length while keeping sum >= target.
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, sum = 0, minLen = Integer.MAX_VALUE;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                minLen = Math.min(minLen, r - l + 1);
                sum -= nums[l++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // ------------------------------------------------------------
    // 5) Longest Substring Without Repeating Characters (Variable)
    // ------------------------------------------------------------
    /**
     * Returns the length of the longest substring with all unique characters.
     * Time: O(n) with constant alphabet (ASCII 256), Space: O(1)
     *
     * Trace idea:
     * - Add s[r]; if it creates a duplicate, move l forward, decrementing counts, until valid again.
     * - Track max window length.
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256]; // ASCII
        int l = 0, maxLen = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            freq[c]++;
            while (freq[c] > 1) {
                freq[s.charAt(l)]--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }

    // ------------------------------------------------------------
    // 6) Fruit Into Baskets (At Most 2 Distinct Types) (Variable)
    // ------------------------------------------------------------
    /**
     * Returns the length of the longest subarray with at most 2 distinct integers.
     * Time: O(n), Space: O(1) to O(k) for the hashmap (k <= number of distinct).
     *
     * Trace idea:
     * - Add fruits[r] to map; if distinct types > 2, shrink from left until size==2 again.
     * - Track max length.
     */
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> count = new HashMap<>();
        int l = 0, maxLen = 0;
        for (int r = 0; r < fruits.length; r++) {
            count.put(fruits[r], count.getOrDefault(fruits[r], 0) + 1);
            while (count.size() > 2) {
                int f = fruits[l];
                count.put(f, count.get(f) - 1);
                if (count.get(f) == 0) count.remove(f);
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }


    // ------------------------------------------------------------
    // Driver with TWO examples per problem
    // ------------------------------------------------------------
    public static void main(String[] args) {
        // 1) Minimum Sum of Subarray of Size k
        System.out.println("1) Min Sum Subarray Size k");
        System.out.println(minSumSubarraySizeK(new int[]{2, 3, 4, 2, 1, 5}, 3)); // 7
        System.out.println(minSumSubarraySizeK(new int[]{5, 1, 3, 2, 6, 1}, 2)); // 4
        System.out.println();

        // 2) Count Subarrays with Sum Exactly k (non-negative nums)
        System.out.println("2) Count Subarrays Sum == k (non-negative nums)");
        System.out.println(countSubarraysSumK(new int[]{1, 2, 1, 2, 1}, 3)); // 4
        System.out.println(countSubarraysSumK(new int[]{2, 1, 1, 1, 2}, 3)); // 3
        System.out.println();

        // 3) Number of Subarrays with Product < k (positive nums)
        System.out.println("3) Count Subarrays Product < k (positive nums)");
        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8
        System.out.println(numSubarrayProductLessThanK(new int[]{1, 2, 3}, 5));       // 4
        System.out.println();

        // 4) Smallest Subarray with Sum >= S (non-negative nums)
        System.out.println("4) Min Length Subarray Sum >= S");
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));  // 2
        System.out.println(minSubArrayLen(7, new int[]{1, 1, 1, 1, 7}));     // 1
        System.out.println();

        // 5) Longest Substring Without Repeating Characters
        System.out.println("5) Longest Substring Without Repeats");
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println();

        // 6) Fruit Into Baskets (At Most 2 Distinct Types)
        System.out.println("6) Fruit Into Baskets (<= 2 types)");
        System.out.println(totalFruit(new int[]{1, 2, 1, 2, 3, 2, 2})); // 4
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));          // 3
        System.out.println();

 
    }
}