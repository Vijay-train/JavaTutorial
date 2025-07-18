public class Arrays2D {
     // Homework
     /*
      * 1) Check if a specific number exists in a 2D array. 
           If it does, print its row and column index.
        2) Given a matrix, create its transpose. 
           (Rows become columns, columns become rows).
        3) Add two matrices (2D arrays) of the same dimensions.
        4) Given a square matrix (number of rows equals number of columns), 
        calculate the sum of its main diagonal elements.
        



     
// IN CLASS
        Problem: Count how many even and odd numbers are present in a 2D integer array.

        Problem: Add two matrices (2D arrays) of the same dimensions.
       
      




        Problem: Given a square matrix (number of rows equals number of columns), 
        calculate the sum of its main diagonal elements.

         
       
        

        Problem: Given a 2D array, 
        check if any entire row consists only of zeros, 
        or if any entire column consists only of zeros.

        Problem: Given a 2D array, 
        calculate and print the sum of elements for each row, 
        and then the sum of elements for each column.
      */
    
    public static void PrintArray(int[][] arr){
        // outerloop that is traversing row by row
        for (int i = 0 ; i < arr.length; i++){
            // inner loop, that will traver column in this row
            for (int j = 0; j < arr[i].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
            
        }

    }

    // Print the sum of all elements in a 2d array
    public static void PrintSum(int[][] arr){
        int sum = 0;
        // outerloop that is traversing row by row
        for (int i = 0 ; i < arr.length; i++){
            // inner loop, that will traver column in this row
            for (int j = 0; j < arr[i].length; j++){
                sum = sum + arr[i][j];
            }        
        }
        System.out.println("The total sum is " + sum);
    }

    // Print the Largest element in a 2d array
     public static void PrintLargestNumber(int[][] arr){
        int largestNumber = arr[0][0];
        // outerloop that is traversing row by row
        for (int i = 0 ; i < arr.length; i++){
            // inner loop, that will traver column in this row
            for (int j = 0; j < arr[i].length; j++){
                if (arr[i][j] > largestNumber){
                    largestNumber = arr[i][j];
                }
            }        
        }
        System.out.println("The Largest number  is " + largestNumber);
    }

    //  Print the sum of all elements in a particular row
    public static void PrintSumOfRow(int[][] arr , int rowNumber){
        int sum = 0;
        for (int j = 0; j < arr[rowNumber].length; j++) {
            sum += arr[rowNumber][j];
        }
       System.out.println("The Largest sum in this row   is " + sum);
    }

    
    // Problem: Count how many even and odd numbers are present in a 2D integer array.
    // // "the total number of even numbers = 12, odd numbes = 13"
    public static void CountEvenOdd(int[][] arr){
    // even % will give 0 , odd will not 0

        int evenCount = 0;
        int oddCount = 0;

        // outerloop that is traversing row by row
        for (int i = 0 ; i < arr.length; i++){
            // inner loop, that will traver column in this row
            for (int j = 0; j < arr[i].length; j++){
                if (arr[i][j] % 2  == 0){// even
                    evenCount++;
                }
                else{
                    oddCount++;
                }
            }
        }

        System.out.println("Even count = " + evenCount);
        System.out.println("Odd count = " + oddCount);
    }

    // Problem: Add two matrices (2D arrays) of the same dimensions.
    public static void AddArrays(int[][] arr1, int[][] arr2)
    {
      int[][] sumArray = new int[arr1.length][arr1[0].length];

       for (int i = 0 ; i < arr1.length; i++){
            // inner loop, that will traver column in this row
            for (int j = 0; j < arr1[i].length; j++){
                sumArray[i][j] = arr1[i][j] + arr2[i][j];
            }
        }

        PrintArray(sumArray);

    }

    public static void DiagonalSum(int[][] arr){
        int sum = 0;


        for (int i = 0 ; i < arr.length; i++){
            sum = sum + arr[i][i];
        }

        System.out.println("THe sum of the main diagonal = " + sum);
    }

    public static void main(String[] args) {

        int[][] matrix1 = { {1, 2, 3}, {4, 500, 6}, {7, 8, 9} };
        DiagonalSum(matrix1);



        
}
}
