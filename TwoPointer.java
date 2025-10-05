import java.util.ArrayList;
import java.util.Arrays;

public class TwoPointer {

    public static void twoSum(int[] num,int target){
        int left = 0;
        int right = num.length - 1;

        while ( left < right){
            int sum = num[left] + num[right];

            if (sum == target){
                // we found it
                System.out.println("target Found at index = " + left + "," +  right);
                return;
            }

            else if (sum < target){
                left++;
            }
            else if (sum > target){
                right--;
            }
        }

        System.out.println("Not found");

    }

    public static void findTriplets(int[] nums) {
        // If the array has fewer than 3 elements, a triplet is impossible.
        if (nums == null || nums.length < 3) {
            return;
        }

        // Sort the array. This is crucial for the two-pointer approach.
        Arrays.sort(nums);

        // Iterate through the array to "fix" the first element of the triplet.
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate fixed elements to avoid duplicate triplets in the output.
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // Initialize two pointers.
            int left = i + 1;
            int right = nums.length - 1;

            // Use the two-pointer approach to find the other two numbers.
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a triplet, so print it.
                    System.out.println("[" + nums[i] + ", " + nums[left] + ", " + nums[right] + "]");

                    // Skip duplicates for the left and right pointers.
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move pointers to the next unique elements.
                    left++;
                    right--;

                } else if (sum < 0) {
                    // Sum is too small, so we need a larger number.
                    left++;
                } else { // sum > 0
                    // Sum is too large, so we need a smaller number.
                    right--;
                }
            }
        }
    }


    public static boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }

            while (left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;

        }

        return true;
    }

   

    public static void printSubarraysSizeK(int[] nums, int k) {
    for (int i = 0; i <= nums.length - k; i++) {
        for (int j = i; j < i + k; j++) {
            System.out.print(nums[j] + " ");
        }
        System.out.println();
    }
}


public static void printMaxSum(int[] nums, int k) {
    int maxSum = 0;
    int windowSum = 0;
    for (int i = 0; i <= nums.length - k; i++) {
        windowSum = 0;
        for (int j = i; j < i + k; j++) {
            System.out.print(nums[j] + " ");
            windowSum = windowSum + nums[j];
        }
        System.out.println("Windowsum = " + windowSum);
        if (maxSum < windowSum){
            maxSum = windowSum;
        }
    }
    System.out.println("MaxSum = " + maxSum);
}



 public static int removeDuplicates(int[] nums){
        int k = 1;
        
        for (int i = 1; i < nums.length ; i++){
            if (nums[i] != nums[i-1]){
                nums[k] = nums[i];
                k++;
            }
        }

        return k;

    }

    public static void printSubArray(int[] nums, int k){
        for (int i = 0; i <= nums.length - k ; i++){
            for (int j = i ; j < i+k ; j++){
                System.out.print(nums[j] + " ");
            }
            System.out.println();
        }
    }

    public static void printMaxSum1(int[] nums, int k){
        int maxSofar = 0;
        int windowSum = 0;

        for (int i = 0; i <= nums.length - k ; i++){
            windowSum = 0;
            for (int j = i ; j < i+k ; j++){
                windowSum = windowSum + nums[j];
            }
            if (maxSofar < windowSum){
                maxSofar = windowSum;
            }
        }
        System.out.println("Max sum is " + maxSofar);
      
    }

     public static void printMaxSum2(int[] nums, int k){
        int maxSofar = 0;
        int windowSum = 0;

        for (int i = 0; i < k ; i++){
            windowSum = windowSum + nums[i];
        }

        maxSofar = windowSum;
        // side the window
        for (int i = k ; i < nums.length ; i++){
            windowSum = windowSum + nums[i];
            windowSum = windowSum - nums[i - k];
            if (windowSum > maxSofar){
                maxSofar = windowSum;
            }

        }

        System.out.println("Max sum is " + maxSofar);

        
        
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256]; // frequency for ASCII chars
        int l = 0, maxLen = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            freq[c]++; // include s[r] in the window

            // If char c is duplicated in the current window, shrink from the left
            while (freq[c] > 1) {
                freq[s.charAt(l)]--; // remove s[l] from the window
                l++;                 // move left pointer right
            }

            // Window [l..r] is valid (all chars unique), update maxLen
            maxLen = Math.max(maxLen, r - l + 1);
        }
        return maxLen;
    }


    public static void main(String[] args) {

        String s = "abcabcbb";
        System.out.println((lengthOfLongestSubstring(s)));


    }
}
