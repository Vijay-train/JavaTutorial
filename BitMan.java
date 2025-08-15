import java.util.Scanner;
import java.lang.System;
/*
****
& - Sets a bit to 1 if *both* corresponding bits are 1.
| - Sets a bit to 1 if *either or both* of the corresponding bits are 1.
^ - Sets a bit to 1 if *both* bits are different
~ - Inverts all the bits (flips 0s to 1s and 1s to 0s)

XOR properties
x^0=x (Identity Property) <<-  if we XOR a number with 0? It stays the same.
x^x=0 (Inverse Property). <<-  if we XOR a number with itself? It cancels out! 

<< 1 Left shift by 1, make the shifted bits 0 on the right
>> 1 right shift by 1, make the shifted buts 0 on the left
****
Tips and tricks - keep this in mind
****
1. Given a number, print Even or Odd? 
- An even number always has its last bit (0 position) as 0.
- An odd number always has its last bit (0 position) as 1.

2. Swap two numbers without a 3rd variale ( 3 times xor)

3. Count the number of bits that are 1 in a number. (The Kernighan's Algorithm)
- There's a simple trick using the expression n = n & (n - 1).
- Each time this operation is performed, it clears the rightmost set bit.
- You can loop and count how many times you perform 
- this operation until the number becomes 0.


4. Check if a number is a power of two**. 2^0 = 1 , 2^1 = 2, 2^2 = 4, 2^3= 8, 2^4= 16
- What's unique about the binary representation of a power of two?
- 1 (0001), 2 (0010), 4 (0100), 8 (1000). 
- Hint: We can use Kernighan's trick! n & (n - 1) will be 0 
- for any power of two, because there's only one set bit to turn off.

5. Given a number n and an index i,
   check if the i-th bit is set (i.e., is 1).

6. *HW Given a number n and an index i, 
   unset the i-th bit (set it to 0).

7. Given a number n and an index i,
   toggle the i-th bit (0 to 1, 1 to 0).

8. Problem: Given an array nums 
   containing n distinct numbers in the range [0, n],
   return the missing number.
   Hint - matching jigsaw puzzles
        If we XOR all the numbers from 0 to n
          and then XOR all the numbers in the array,
          the pairs will cancel out,
          leaving only the missing number.
          n = 3 [3,0,1]
          (3⊕0⊕1⊕2)⊕(3⊕0⊕1)
            Rearrange the terms: (3⊕3)⊕(0⊕0)⊕(1⊕1)⊕2
            All the pairs cancel out, leaving just 2.
          



9. Given a number n that is a power of two, 
   find the position of the only set bit.
   Example: n = 8 (binary 1000), the position is 3 (0-indexed).
   Hint: 
        we can repeatedly right shift the number
        and count the shifts until the number becomes 1.

10. (*HW)Given an array where all elements appear twice except for two,
    find those two elements.
   Hint: This is a step up from finding one unique number. 
         First, XOR all the numbers.
         The result xorSum will be unique1 ^ unique2. 
         Now we need to split the original array into two groups
         such that unique1 and unique2 
         are in different groups.
         We can find a bit that is set in 
         xorSum (because unique1 and unique2 must differ at that bit).
        Let's say that bit is at position k.
        We can then group the numbers based on whether
        their k-th bit is 0 or 1.

11. Reverse bits of a 32-bit unsigned integer
   We need to build a new number by taking the bits from the 
   original number one by one, from right to left, and placing 
   them in the new number from left to right.
   Hint : Use a loop that runs 32 times.
   In each iteration, take the rightmost bit of the original number n 
   using n & 1. Append this bit to the result result 
   by left shifting result and then OR-ing the new bit. 
   Then, right shift n to get the next bit.

12. Given a number n and an index i, clear all bits 
    from the most significant bit (MSB) up to bit i (inclusive).
   Hint: We need to create a mask with 0s from the MSB to i 
   and 1s from i-1 to the right.
   The expression (1 << i) - 1 creates a mask with 
   1s up to i-1. We can then use AND with this mask.

13. Print Binary

13. :Given two integers, check if they have opposite signs.
   Hint: The most significant bit (MSB) in a number's binary
    representation tells us its sign. 0 for positive, 
    1 for negative (in two's complement). 
    If two numbers have opposite signs, 
    their MSBs will be different. 
    The XOR of their MSBs will be 1. 
    So we can use XOR on the two numbers. 
    If the result is negative, it means they had opposite signs.

14 Find the maximum of two numbers without 
   using if/else or a ternary operator.
   Hint: This is a clever trick using the sign bit.
   Let's look at a - b. 
   If a > b, a - b is positive.
   If a < b, a - b is negative.
   We can use the sign bit of a - b 
   to create a mask. >> 31 will give us 0 for positive
   and -1 (all 1s) for negative.

15. Find the sum of two numbers, a and b, without using the + operator.
   Hint: We can use XOR for the sum part and AND 
   for the carry part.
   a ^ b gives the sum without considering carry.
   (a & b) << 1 gives the carry.
   We can repeatedly do this until there is no carry.

16. Given an integer n, find the smallest power of 2 greater 
    than or equal to n.
    Example: n = 10, answer is 16. n = 4, answer is 4.

*/


public class BitMan {

      public static void leftShift(int n, int m){
         int y = n << m;
         System.out.println(y);
      }

      public static void rightShift(int n, int m){
         int y = n >> m;
         System.out.println(y);
      }

      public static void GetBit(int n, int pos){
         /*
          * mask = 1 << pos
          * n & mask
          * if this is 0, then the bit is 0, else 1
          */

          int mask = 1 << pos;
          if ((mask & n) == 0){
            System.out.println("The bit is zero");
          }
          else{
            System.out.println("The bit is one");            
          }
      }

      public static void EvenOrOdd (int num){

         if ((num & 1) == 0){
            System.out.println("The number is Even");
         }
         else{
            System.out.println("The number is odd");
         }
      }

      public static void Swap(int n, int m){
         /* 3 xors */
         n = n ^ m;
         m = n ^ m;
         n = n ^ m;

         System.out.println("After swap n=" + n + " m=" + m);
      }

      public static void printBinary32(int n) {
          for (int i = 31; i >= 0; i--) {
              int bit = (n >> i) & 1;   // get the bit at position i
              System.out.print(bit);     // print 0 or 1
                }
          System.out.println();
         }


      public static void CountBits(int n){
         // n = n & (n-1) until n = 0
        int c=0;
        while(n!=0){
          n=n&(n-1);
         c++;
        }
        System.out.println("No.of 1's in binary number : "+c);
      
      }

      public static void IsPowerOf2(int num){
         int val = num & (num - 1);

         if (val == 0){
            System.out.println("The number is a power of 2");
         }
         else{
            System.out.println(("The number is NOT a power of 2"));
         }
      }

      // 5. Given a number n and an index i,
      //check if the i-th bit is set (i.e., is 1).
      public static void CheckBit(int num, int i){
         int mask = 1 << i;
         if ((num & mask) != 0){
            System.out.println("The bit is set to 1");
         }
         else{
            System.out.println("The bit is set to 0");
         }
      } 


     public static void main(String[] args) {
        //CheckBit(13,2);

        int x = 13;
        printBinary32(2);
     }
    

}
