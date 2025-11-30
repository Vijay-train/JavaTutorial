import java.lang.System;
import java.util.ArrayList;


public class MyRecursion {
   
    public static void print_iter(int n){
        for (int i = n ; i >=1 ; i--){
            System.out.println(i);
        }
    }

    public static void print_recur(int n){
        System.out.println("print_recur is called with n=" + n);
        if (n == 0){
            return;
        }

        System.out.println(n);
        print_recur (n-1);

        System.out.println("print_recur last line of code with n=" + n);

    }

    public static int Fact(int n){
        if (n == 0){
            return 1;
        }

        return n * Fact(n-1);
    }

    public static void printArray(int[] arr, int index) {
        if(index == arr.length) {
            return;
        }
        System.out.println(arr[index]);
        printArray(arr, index + 1);
    }

    public static void printArrayRev(int[] arr,int index){
        if(index==arr.length){
            return;
        }
        printArrayRev(arr,index+1);
        
        System.out.println(arr[index]);
    }

   // Find Max Element in an array using recursion
   // for arr = {22,35,1,99,8}
   // the function should return 99
    public static int findMax(int[] arr,int n,int max)
    {
        if(n<0)
        {
            return max;
        }
        if(arr[n]>max)
        {
            max = arr[n];
        }
        return findMax(arr,n-1,max);
    }

    // changing list - Sum of ArrayList
    public static int sum(ArrayList<Integer> list){
        // Base case
        if (list.isEmpty()){
            return 0;
        }

        int currElement = list.removeFirst();
        return currElement + sum(list);
    }


    // without changing the list - Sum of ArrayList
    public static  int sumWithIndex(ArrayList<Integer> arr,int i) {
        if (i == arr.size()) {
            return 0; // base case: no more elements
         }

        return arr.get(i) + sumWithIndex(arr, i + 1);
    }

 

    // Find first occurence of a number in an array using recursion
    // arr = {2,3,9,8,7,9,3}
    // Target = 9
    // index = 2

    // Find last occurence
    // index = 5

    public static void TestArrayList(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");          	// [Alice]
        names.add("Bob");            	// [Alice, Bob]
        
        System.out.println(names.getFirst()); // Alice
        System.out.println(names); // Alice
        
    }

    public static boolean Palindrome(ArrayList<String> list ,int start ,int end){
        if(start>=end){
            return true ;
        }
        if(!list.get(start).equals(list.get(end))){
            System.out.println("Not Palindrome");
            return false;
        }
        return Palindrome(list, start+1, end-1);
    }

    public static void reverse(ArrayList<String> list, int start, int end){
        // base case
        if (start >= end){
            return;
        }

        String temp = list.get(start);
        list.set(start,list.get(end));
        list.set(end,temp);

        reverse(list,start+1,end-1);

    }

    public static int CountOccurences(ArrayList<Integer> list, int target, int index){
        // base case
        if (index >= list.size()){
            return 0;
        }

        // check the element
        int currCount = 0;
        if (list.get(index) == target){
            currCount = 1;
        }
        // call recursion
        int restCount = CountOccurences(list, target, index+1);
        return currCount + restCount;

    }


/* Backtracking problmems */
      public static void generate(int n, String currentString) {
        // Base case: If the string is the desired length, print it.
        if (currentString.length() == n) {
            System.out.println(currentString);
            return;
        }

        // Choice 1: Append '0' and recurse for the next position.
        generate(n, currentString + "0");

        // Choice 2: Append '1' and recurse for the next position.
        generate(n, currentString + "1");
    }

    public static void generatePermutations(String str, int left, int right) {
        if (left == right) {
            System.out.println(str);
            return;
        }
        for (int i = left; i <= right; i++) {
           
            str = swap(str, left, i);  // 1. Choose:
       
            generatePermutations(str, left + 1, right);      // 2. Explore:
        
            str = swap(str, left, i);   // 3. Un-choose (Backtrack!):
        }
    }

    public static String swap(String a, int i, int j) {
        char[] charArray = a.toCharArray();
        char temp = charArray[i]; charArray[i] = charArray[j]; charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void FindPath(int n, int m , int r, int c, String str) {
        if(r == n-1 && c == m-1) {
            System.out.println(str);
            return;
        }
        if( r >= n || c >= m) {
            return;
        }
        FindPath(n,m,r+1,c,str + "D");
        FindPath(n,m,r,c+1,str + "R");
    }

    public static void FindPathInMaze(int n, int m ,int[][] matrix ,int r, int c, String str) {
        if(r == n-1 && c == m-1) {
            System.out.println(str);
            return;
        }
        if( r >= n || c >= m||matrix[r][c]==1) {
            return;
        }
        FindPathInMaze(n,m,matrix,r+1,c,str + "D");
        FindPathInMaze(n,m,matrix,r,c+1,str + "R");
    }



    public static void main(String[] args) {
      
    //   generate(2, "");
    //   generatePermutations("abc", 0,2);
    //FindPath(3, 3, 0, 0, "");
     int[][] matrix={{0,0,1},{0,0,1},{0,0,0}};
    FindPathInMaze(3, 3,matrix, 0, 0, "");

    
   
    }
  


}
    

