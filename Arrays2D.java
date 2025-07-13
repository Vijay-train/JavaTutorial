public class Arrays2D {
     
    
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
        
    }
    public static void main(String[] args) {

         int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9},{10,11,12} };
         PrintArray(matrix);

        
}
}
