import java.util.Scanner;
import java.lang.System;

/*
1) Given an array of integers, print all its elements.
2) Calculate the sum of all elements in an integer array.
3) Find the largest element in an integer array.
4) Find the smallest element in an integer array.
5) Check if a specific number exists in an array. If it does, tell its index.
6) Count how many times a specific number appears in an array.
7) Write a program that takes an array of temperatures (e.g., double[] temperatures = {25.5, 28.1, 30.0, 27.8, 29.5};) and counts how many days the temperature was above 29.0.

8) Reverse the order of elements in an array.
9) Determine if an array of numbers is sorted in ascending order.
12)Find the second largest element in an array.

13)Given an array of integers and a target sum, 
find if there exists a pair of elements in the array that add up to the target sum.

Homework
* 12)find if there exists a pair of elements in the array that add up to the target sum.
* 10)Given an array with duplicate elements, create a new array with only unique elements.
* 11)Write a program to rotate an array to the left by one position.
* 15)Count the frequency of each element in an array.
* 16)Given an array of integers, count the number of even and odd numbers in it.
* 14)Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing.
* 
*/


public class Arrays {


   //1) Given an array of integers, print all its elements.
   public static void PrintArray(int[] arr){
      System.out.println("[PrintArray] Printing all elements in the Array ");

      for(int i = 0; i < arr.length; i++){
         System.out.println((arr[i]));
      }
   }

   // 2) Calculate the sum of all elements in an integer array.
   public static void PrintSum(int[] arr){
      int currSum = 0;

      for(int i = 0; i < arr.length; i++){
         currSum = currSum + arr[i];
      }

      System.out.println("[PrintSum]Sum of the array = " + currSum);

   }


   //  3) Find the largest element in an integer array.
   public static void PrintLargest(int[] arr){
     int currMax = 0;
  
       for(int i = 0; i < arr.length; i++){
            if (currMax < arr[i]){
               currMax = arr[i];
            }
         }
      
      System.out.println("[PrintLargest], the largest number in the arr is " + currMax);

   }

   //5) Check if a specific number exists in an array. If it does, tell its index.
   public static void FindNumber(int numberToCheck, int[] arr){
      int index = -1;

      for(int i = 0; i < arr.length; i++){
         if (numberToCheck == arr[i]){ // found it
            index = i;
            break;
         }
      }

      if (index != -1)
         System.out.println(("[FindNumber] Found the number at index " + index));
      else
         System.out.println(("[FindNumber] Number not found "));

   }

   // 6) Count how many times a specific number appears in an array.
   public static void CountOccurence(int numberToCheck, int[] arr){
      int occurenceCount = 0;

      for(int i = 0; i < arr.length; i++){
         if (numberToCheck == arr[i]){ // found it
            occurenceCount++;
         }
      }

      System.out.println(("[CountOccurence] # of times the number occured is  " + occurenceCount));     

   }

   //7) Write a program that takes an array of temperatures and 
   // counts how many days the temperature was above 29
   public static void CheckTemp(int temp, int[] arr){
      int occurenceCount = 0;

      for(int i = 0; i < arr.length; i++){
         if (arr[i] >= temp){ // found it
            occurenceCount++;
         }
      }

      System.out.println(("[CountOccurence] # of times the number occured is  " + occurenceCount));     

   }

   //8) Reverse the order of elements in an array.
   public static void ReverseArray(int[] arr){
      // create a new array, and store the value in reverse order of arr
      // print the reversed array
      /*
       * 10 .. 10
       * Arr[0]->10, Arr[1] 20 .. 20,10
       * temp = Arr[0] // 10   
       * Arr[0] = Arr[1] // 20
       * Arr[1] = temp
       * 
       * 10,20,30,40 --> 40,30,20,10
       * 
       * inplace reversal
       * 
       */

       //   int[] testArr = {10,20,30,40,50};
       int[] reversedArray = new int[arr.length];

       for (int i = 0; i < arr.length; i++){
         reversedArray[i] = arr[arr.length-1-i]; //arr[4]
       }

       System.out.println("Printing the Reversed Array");
       for (int i = 0 ; i < reversedArray.length; i++){
         System.out.println((reversedArray[i]));
       }
   }

   //9) Determine if an array of numbers is sorted in ascending order.
   // 1,4,5,6,7,8 .."Array is in sorted ascending order"
   // 1,4,3,5,6 .."Arrays is NOT in sorted ascending order"
   public static void IsSorted(int[] arr){
 
      boolean isSorted = true;
       for (int i = 0; i < arr.length - 1; i++){
         if (arr[i] > arr[i+1]){
            isSorted = false;
            break;
         }
       }

       if (isSorted == true){
         System.out.println("Array is in sorted ascending order");
       }
       else{
         System.out.println("Array is NOT in sorted ascending order");
       }


   }

   // 12)Find the second largest element in an array.
   // for eg - {10, 5, 20, 8, 25, 15}; , this should print 20
   public static void PrintSecondLargest(int[] arr){
      int largest = 0;
      int secondLargest = 0;

      for (int i = 0 ; i < arr.length ; i++){
         if (arr[i] > largest ){// this means current element is the largest
            secondLargest = largest;
            largest = arr[i];
         } 
         else if (arr[i] > secondLargest && arr[i] != largest){
            secondLargest = arr[i];
         }
      }

      System.out.println("The second largest element in the array is " + secondLargest);
   }
   
// 13)Given an array of integers and a target sum, 
// find if there exists a pair of elements in the array that add up to the target sum.

// {10,5,25,8,25,15} , targetSum = 20 .. Yes or no.. Yes = if there is pair that sums to the target sum
// pair = any pair of 2 elements in the array

   public static void FindPairWithSum(int[] arr, int targetSum)
   {
      int isPair = 0;
      for(int i = 0; i < arr.length - 1; i++){
         if(arr[i] + arr[i+1] == targetSum){
               System.out.println("Yes! Pair exists");
               isPair = 1;
               break;
         }
      }
      if(isPair == 0){
         System.out.println("NO Pair");
      }
   }

    public static void testFun(int[] arr)
    {
       int target=30;
       boolean targetFound = false;
       
       for(int i=0;i<=arr.length-1;i++){
         
        int sum = arr[i] + arr[i+1];    
        
        if(target==sum){
            targetFound = true;
            break;
           }
           
      }

      if (targetFound == true)
         System.out.println("Target found.....");
      else
          System.out.println("Target NOT found");
   }




     public static void main(String[] args) {
       int[] testArr = {10,5,25,8,25,15};
   
       /* 
       PrintArray(testArr);
       PrintSum(testArr);
       PrintLargest(testArr);
       FindNumber(15,testArr);
       CountOccurence(130,testArr);
       ReverseArray(testArr);
       IsSorted(testArr);
      PrintSecondLargest(testArr);
       */

      
    //  FindPairWithSum(testArr,133);

     // testFun(testArr);

      int[] scores = new int[5];
       for(int i = 0; i < scores.length; i++){
         System.out.println((scores[i]));
      }

      int[] scores1 = {0,0,0,0,0};
      System.out.println ("Printing scores1");
      for(int i = 0; i < scores1.length; i++){
         System.out.println((scores1[i]));
      }

   
       

     }

}