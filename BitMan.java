import java.util.Scanner;
import java.lang.System;



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

     public static void main(String[] args) {
        leftShift(5,1);
        rightShift(5,1);
        //5 0101
        GetBit(5,3);
     }
    

}
