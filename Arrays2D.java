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
        5) Count how many even and odd numbers are present in a 2D integer array.


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

    public static void main(String[] args) {

         int[][] matrix = { {1, 2, 3}, {4, 500, 6}, {7, 8, 9},{10,11,2} };
        PrintArray(matrix);
         PrintSumOfRow(matrix,1);



        
}
}
