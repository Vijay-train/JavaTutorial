/*
    Find a number in an unsorted array.

    Count the occurrences of a number in an unsorted array.
 
    Question: Write a Java program to perform a binary search on a sorted array of double values.
      The program should return the index of the element if found, or -1 if not found.
      Example Input: [1.1, 2.2, 3.3, 4.4, 5.5] and key = 3.3
      Expected Output: 2
    
    Find a number in a sorted array

    Search for a number in a 2D sorted matrix

    Find the first and last occurrence of a number in a sorted array.

    Find the missing number in a sorted array of distinct numbers from 1 to N.

    Find a triplet in a sorted array that sums to a given value.

    Search a key in a sorted infinite array.

    Find the floor of an element in a sorted array.

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


    public static void main(String[] args) {
        System.out.println(("Searching..."));
        int[] num = {10};
        int keyToFind = 100;

        binarySearch(num, keyToFind);

 
    }
    
}
