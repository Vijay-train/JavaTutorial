import java.util.Arrays;

public class Sorting {


    // QUICK SORT < Start>

    /**
     * The main recursive Quicksort function.
     * @param arr The array to be sorted.
     * @param low The starting index of the subarray.
     * @param high The ending index of the subarray.
     */
    public static void quickSort(int[] arr, int low, int high) {
        // Print the current state and the subarray being processed
        System.out.println("\n-> Calling quickSort on subarray from index " + low + " to " + high);
        printSubArray(arr, low, high);

        // The base case for the recursion: if the subarray has 0 or 1 elements, it's already sorted.
        if (low >= high) {
            System.out.println("   Base Case Met: low (" + low + ") is >= high (" + high + "). Returning.");
            return;
        }

        // Partition the array and get the index of the pivot element
        int pivotIndex = partition(arr, low, high);
        
        System.out.println("   Partition complete. Pivot '" + arr[pivotIndex] + "' is now at its final sorted position: index " + pivotIndex);

        // Recursively call quickSort on the two subarrays created by the partition
        System.out.println("   Now, recursively sorting the left part (before the pivot)...");
        quickSort(arr, low, pivotIndex - 1);
        
        System.out.println("   ... and recursively sorting the right part (after the pivot)...");
        quickSort(arr, pivotIndex + 1, high);
    }

    /**
     * This function takes the last element as a pivot, places the pivot element at its
     * correct position in the sorted array, and places all smaller elements to the left
     * of the pivot and all greater elements to the right.
     * @param arr The array to be partitioned.
     * @param low The starting index.
     * @param high The ending index.
     * @return The index where the pivot element is now located.
     */
    public static int partition(int[] arr, int low, int high) {
        // Choose the rightmost element as the pivot
        int pivotValue = arr[high];
        System.out.println("   [Partition] Starting partition process for subarray from " + low + " to " + high);
        System.out.println("   [Partition] Pivot chosen is arr[" + high + "] = " + pivotValue);
        
        // 'i' is the index of the smaller element. It indicates the boundary
        // of the elements that are smaller than the pivot.
        int i = (low - 1); 

        // Iterate through the array from 'low' to 'high - 1'
        for (int j = low; j < high; j++) {
            System.out.println("   [Partition]   - Comparing arr[" + j + "] (" + arr[j] + ") with pivot (" + pivotValue + ")");
            // If the current element is smaller than the pivot
            if (arr[j] < pivotValue) {
                // Increment the index of the smaller element boundary
                i++;
                System.out.println("   [Partition]     -> " + arr[j] + " < " + pivotValue + ". Swapping arr[" + i + "] (" + arr[i] + ") with arr[" + j + "] (" + arr[j] + ")");
                
                // Swap arr[i] and arr[j]
                swap(arr, i, j);
                
                System.out.print("   [Partition]       Array state after swap: ");
                printSubArray(arr, low, high);
            } else {
                 System.out.println("   [Partition]     -> " + arr[j] + " >= " + pivotValue + ". No swap needed.");
            }
        }

        // After the loop, all elements smaller than the pivot are to the left of 'i+1'.
        // Now, we place the pivot element in its correct sorted position by swapping
        // it with the element at 'i+1'.
        System.out.println("   [Partition] Loop finished. Placing pivot in its correct spot.");
        System.out.println("   [Partition] Swapping pivot arr[" + high + "] (" + pivotValue + ") with arr[" + (i + 1) + "] (" + arr[i + 1] + ")");
        
        swap(arr, i + 1, high);
        
        System.out.print("   [Partition] Final state of subarray for this partition: ");
        printSubArray(arr, low, high);

        // Return the index of the pivot
        return (i + 1);
    }

    /**
     * A utility function to swap two elements in an array.
     * @param arr The array.
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * A helper function to print a specific portion of an array.
     * @param arr The array.
     * @param low The starting index of the subarray to print.
     * @param high The ending index of the subarray to print.
     */
    public static void printSubArray(int[] arr, int low, int high) {
        if (low > high) {
            System.out.println("[] (Empty Subarray)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int k = low; k <= high; k++) {
            sb.append(arr[k]);
            if (k < high) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    // QUICK SORT < End>

    /* Merge sort Start */
    /**
     * The main recursive Merge Sort function. It divides the array into two halves,
     * calls itself for the two halves, and then merges the two sorted halves.
     * @param arr The array to be sorted.
     * @param left The starting index of the subarray.
     * @param right The ending index of the subarray.
     */
    public static void mergeSort(int[] arr, int left, int right) {
        System.out.println("\n-> Calling mergeSort on subarray from index " + left + " to " + right);
        printSubArray(arr, left, right);

        if (left < right) {
            // Find the middle point to divide the array into two halves
            int middle = left + (right - left) / 2;
            
            System.out.println("   Splitting into two halves: [" + left + "..." + middle + "] and [" + (middle + 1) + "..." + right + "]");
            
            // Recursively sort the first half
            mergeSort(arr, left, middle);
            
            // Recursively sort the second half
            mergeSort(arr, middle + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, middle, right);
        } else {
            System.out.println("   Base Case Met: left (" + left + ") is not less than right (" + right + "). This subarray is considered sorted.");
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     * First subarray is arr[left...middle]
     * Second subarray is arr[middle+1...right]
     * @param arr The main array.
     * @param left The starting index of the first subarray.
     * @param middle The ending index of the first subarray.
     * @param right The ending index of the second subarray.
     */
    public static void merge(int[] arr, int left, int middle, int right) {
        System.out.println("   [Merge] Starting merge for subarrays from " + left + " to " + middle + " and " + (middle + 1) + " to " + right);

        // Find sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary arrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            rightArray[j] = arr[middle + 1 + j];
            
        System.out.println("   [Merge]   - Left Temp Array: " + Arrays.toString(leftArray));
        System.out.println("   [Merge]   - Right Temp Array: " + Arrays.toString(rightArray));

        // Merge the temporary arrays back into the original array

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = left;
        while (i < n1 && j < n2) {
            System.out.println("   [Merge]   - Comparing Left[" + i + "](" + leftArray[i] + ") and Right[" + j + "](" + rightArray[j] + ")");
            if (leftArray[i] <= rightArray[j]) {
                System.out.println("   [Merge]     -> Taking " + leftArray[i] + " from Left array. Placing it at index " + k);
                arr[k] = leftArray[i];
                i++;
            } else {
                System.out.println("   [Merge]     -> Taking " + rightArray[j] + " from Right array. Placing it at index " + k);
                arr[k] = rightArray[j];
                j++;
            }
            k++;
            System.out.print("   [Merge]       Array state: ");
            printSubArray(arr, left, right);
        }

        // Copy remaining elements of leftArray[] if any
        while (i < n1) {
            System.out.println("   [Merge]   - Copying remaining element from Left array: " + leftArray[i] + " to index " + k);
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[] if any
        while (j < n2) {
            System.out.println("   [Merge]   - Copying remaining element from Right array: " + rightArray[j] + " to index " + k);
            arr[k] = rightArray[j];
            j++;
            k++;
        }
        
        System.out.print("   [Merge] Merge complete. Final state for this scope: ");
        printSubArray(arr, left, right);
    }
    
    /* Merge sort End */

    // { 3, 7, 2, 1 };
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            System.out.printf("i=%d | key=%d | start: %s%n", i, key, Arrays.toString(array));
            // Compare step-by-step; shift only when needed
            while (j >= 0) {
                System.out.printf("  compare: array[%d]=%d > key=%d ? ", j, array[j], key);
                if (array[j] > key) {
                    System.out.println("YES -> shift to index " + (j + 1));
                    array[j + 1] = array[j];
                    j--;
                    System.out.printf("    array now: %s%n", Arrays.toString(array));
                } else {
                    System.out.println("NO -> stop shifting");
                    break;
                }
            }
            System.out.printf("  place key=%d at index %d%n", key, j + 1);
            array[j + 1] = key;
            System.out.printf("  after i=%d: %s%n%n", i, Arrays.toString(array));
        }
    }


    // [3,7,2,1]
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            System.out.printf("Pass %d: i=%d, start = %s%n", i + 1, i, Arrays.toString(arr));
            for (int j = i + 1; j < n; j++) {
                System.out.printf("  Compare arr[%d]=%d with current min arr[%d]=%d",
                        j, arr[j], min_idx, arr[min_idx]);
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                    System.out.printf(" -> NEW MIN at j=%d (value=%d)%n", j, arr[j]);
                } else {
                    System.out.println(" -> keep min");
                }
            }

            if (min_idx != i) {
                System.out.printf("  Swap: arr[%d]=%d <-> arr[%d]=%d%n",
                        i, arr[i], min_idx, arr[min_idx]);
                int temp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = temp;
            } else {
                System.out.println("  No swap (i already holds the minimum)");
            }

            System.out.printf("  After pass %d: %s%n%n", i + 1, Arrays.toString(arr));
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // outerloop
        for (int i = 0; i < n - 1; i++) {
            // inner loop
            for (int j = 0; j < n - i - 1; j++) {
                // swap adjacent elements
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubble sort" + Arrays.toString(arr));

    }

    public static void main(String[] args) {

        int arr[] = { 9,3, 7, 2, 1 };
     //   System.out.println("Before sort" + Arrays.toString(arr));

       // selectionSortVerbose(arr);
       // insertionSortVerbose(arr);

    //    bubbleSort(arr);
    //    selectionSort(arr);
    //    insertionSort(arr);
    mergeSort(arr, 0, arr.length - 1);
    //    quickSort(arr, 0, arr.length - 1);
    //    System.out.println("Merge sort" + Arrays.toString(arr));
        System.out.println("======================================================");
    //    System.out.println("Final Sorted Array: " + Arrays.toString(arr));
    }
}
