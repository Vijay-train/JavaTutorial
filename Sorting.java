import java.util.Arrays;

public class Sorting {

    /* Merge sort Start */
    /**
     * The main function that sorts the array arr[l..r] using the merge() helper
     * function.
     * This is the recursive "divide" part of the algorithm.
     *
     * @param arr The array to be sorted.
     * @param l   The starting index of the subarray.
     * @param r   The ending index of the subarray.
     */

    public static void Mergesort(int arr[], int l, int r,int depth) {
        System.out.println(
                "<< Recursion Depth = " + depth + ">> " + "ENTER sort: l=" + l + ", r=" + r + " -> " + Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)) );
        if (l < r) {
            int m = l + (r - l) / 2;
             System.out.println(" <Depth=" + depth + ">" +  "...Calling First MergeSort ");
            Mergesort(arr, l, m,depth+1);
            System.out.println(" <Depth=" + depth + ">" +  "...Calling Second MergeSort  ");
             Mergesort(arr, m + 1, r,depth+1);
             System.out.println(" <Depth=" + depth + ">" +  "...Calling Merge ");
         
            merge(arr, l, m, r);
        }
        System.out.println(
                "<< Recursion Depth = " + depth + ">> " + "RETURN sort: l=" + l + ", r=" + r + " -> " + Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));
    }

    /**
     * Merges two sorted subarrays of arr[].
     * First subarray is arr[l..m]
     * Second subarray is arr[m+1..r]
     * This is the "conquer" or "combine" part of the algorithm.
     *
     * @param arr The main array containing the subarrays.
     * @param l   The starting index of the first subarray.
     * @param m   The ending index of the first subarray.
     * @param r   The ending index of the second subarray.
     */
    public static void merge(int arr[], int l, int m, int r) {
        System.out.println("MERGE: l=" + l + ", m=" + m + ", r=" + r);
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        System.out.println("  Left: " + Arrays.toString(L) + " | Right: " + Arrays.toString(R));
        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                System.out.println("    Take L[" + i + "]=" + L[i] + " -> " +
                        Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));
                i++;
            } else {
                arr[k] = R[j];
                System.out.println("    Take R[" + j + "]=" + R[j] + " -> " +
                        Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            System.out.println("    Copy L[" + i + "]=" + L[i] + " -> " +
                    Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            System.out.println("    Copy R[" + j + "]=" + R[j] + " -> " +
                    Arrays.toString(Arrays.copyOfRange(arr, l, r + 1)));
            j++;
            k++;
        }
    }

    /* Merge sort End */


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

        int arr[] = { 3, 7, 2, 1 };
     //   System.out.println("Before sort" + Arrays.toString(arr));

       // selectionSortVerbose(arr);
       // insertionSortVerbose(arr);

    //    bubbleSort(arr);
        selectionSort(arr);
    //    insertionSort(arr);
    //    Mergesort(arr, 0, arr.length - 1,0);
    //    System.out.println("Merge sort" + Arrays.toString(arr));

    }
}
