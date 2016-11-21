/* An uptimized version of the  program to display the prime numbers from 1 to n where n is given by the user
 * with 10 of them per line
 * runs with a running time of O((n *sqrt(n))/logn)
 */
package uptimizedprimenumbers;

import java.util.Scanner;

/**
 *
 * @author harisu
 */
public class UptimizedPrimeNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
 
        Scanner input = new Scanner (System.in);
        System.out.println("prime numbers from 1 to n: enter limit ");
        int n = input.nextInt();
        //list to hold prime numbers
        java.util.List<Integer> list = new java.util.ArrayList<>();
        final int NUMBER_PER_LINE = 10;//display ten prime numbers in one line
        int count =0; //count the number of prime numbers
        int number = 2 ;//number to be tested for primeness
        int squareRoot = 1; //check whether number is <= squareroot
        
        System.out.println("The prme numbers are: ");
        //loop to repeatedly find prm numbers
        while(number<= n){
            //assume number is prime
            boolean isPrime = true;
            
            if(squareRoot *squareRoot <number) squareRoot++;
            //test if number is prime
            for(int k = 0;k <list.size() && list.get(k)<=squareRoot;k++){
                
                if(number % list.get(k) ==0){ //if true then the number is not prime
                    isPrime = false;
                break;
            }
            
        }
        //print the prime number oand  increase the count
        if(isPrime){
            count++;
            list.add(number);
            if(count % NUMBER_PER_LINE ==0)
                //print and move to a newline
                System.out.println(number);
            else
                System.out.printf(number+ " ");
        }
        number++;
    }
System.out.println("\n"+count +" primes less than or equal to " +n);    
}
}
    
    

