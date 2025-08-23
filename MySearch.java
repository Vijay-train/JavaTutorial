/*
    Find a number in an unsorted array.

    Count the occurrences of a number in an unsorted array.
    {4,2,7,1,9,7,5,7}
    Key = 7
    Result = 3
 
    Question: Write a Java program to perform a binary search on a sorted array of int values.
      The program should return the index of the element if found, or -1 if not found.
      Example Input: [1, 2, 3, 4, 5] and key = 3
      Expected Output: 2
    
    Search for a number in a 2D sorted matrix
    
    Find the first and last occurrence of a number in a sorted array.

    Find the missing number in a sorted array of distinct numbers from 1 to N.

    Find the floor of an element in a sorted array.
    
    Search a key in a sorted infinite array.
    
    Find a triplet in a sorted array that sums to a given value.

    

   

    Find the ceiling of an element in a sorted array.

    Find a Fixed Point (Value equal to index) in a given array.

    Find the median of two sorted arrays of different sizes.

    *Find the peak element in an array.

    *Find the number of times a sorted array is rotated.

    *Search for a number in an array where adjacent elements differ by at most 1.

    *Find a pair in a rotated sorted array with a given sum.

    *Find the position of an element in a sorted array of infinite numbers.

    *Find the first repeating element in an array of integers.

    *Find the repeating and the missing number.
    
    *Search in a sorted array of unknown size.
 */



public class MySearch {


    public static void linearSearch(int[] arr, int key) {
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                System.out.println("item found at index = " + i);
                found = true;
                break;
            }
        }     

        if (found == false){
            System.out.println("Item not found");
        }
    }

    public static void binarySearch(int[]arr, int key){
        int low = 0;
        int high = arr.length - 1;
        boolean found = false;

        while (low <=high){
            int mid = (low + high)/2;

            if (arr[mid] == key){
                System.out.println("item found at index = " + mid);
                found = true;
                break;
            }

            else if (arr[mid] < key){
                low = mid + 1;
            }

            else if (arr[mid] > key){
                high = mid - 1;
            }
        }

        if (found == false){
            System.out.println("Item not found");
        }
    }

    public static void SearchMatrix(int[][] matrix, int key){
        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0;
        int high = (m*n)-1;

        while (low <= high){
            int mid = (low + high)/2;
            int row = mid/n;
            int col = mid%n;

            if (matrix[row][col] == key){
                System.out.println("Element found at " + row + "x" + col);
                return;
            } else if (matrix[row][col] < key){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Item not found");
    }

    public static int[] FindFirstAndLast(int[] arr, int target){
        int[] result = {-1,-1};

        // 1. Find the first occurence
        int low = 0, high = arr.length - 1;
        while(low <= high){
            int mid = (low + high)/2;

            if (arr[mid] == target){ // we found the target
                result[0] = mid;
                high = mid - 1;
            }
            else if (arr[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }

        // 2. Find the last occurence
        low = 0;
        high = arr.length - 1;
        while (low <= high){
            int mid = (low + high)/2;

            if (arr[mid] == target){
                result[1] = mid;
                low = mid + 1;
            }
            else if (arr[mid] < target ){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }

        }

        return result;
    }

    public static void FindMissingNo(int[] arr) {  // find missing no between 1 to N.
        int low = 0, high = arr.length - 1, missing = -1;
        while(low <= high) {
            int mid = (low + high)/2;
            if(arr[mid] == mid + 1) {
                low = mid + 1;
            }
            else {
                missing = arr[mid] - 1;
                high = mid - 1;
            }
        }
        System.out.println("Missing Number = " + missing);
    }

    public static void checkElement(int[] nums, int target){
        int low = 0;
        int high = 1;
        while(nums[high] < target){
            low = high;
            high = high * 2;
        }
        // at this point we have our range

        boolean found = false;
        while(low <= high){
            int mid = (low + high)/2;
            if(nums[mid]==target){
                found = true;
                break;
            }
            else if(nums[mid] < target){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        if(found){
            System.out.println("Element found!");
        }
        else{
            System.out.println("Element not found!");
        }
    }


    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5};
        FindMissingNo(arr);

 
    }
    
}
