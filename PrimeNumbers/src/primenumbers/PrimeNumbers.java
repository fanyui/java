/*
 * A program to display the prime numbers from 1 to n where n is given by the user
 * with 10 of them per line running time of n*sqrt(n) slow
 */
package primenumbers;
import java.util.Scanner;
/**
 *
 * @author harisu
 */
public class PrimeNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        System.out.println("prime numbers from 1 to n: enter limit ");
        int n = input.nextInt();
        final int NUMBER_PER_LINE = 10;//display ten prime numbers in one line
        int count =0; //count the number of prime numbers
        int number = 2 ;//number to be tested for primeness
        
        System.out.println("The prme numbers are: ");
        //loop to repeatedly find prm numbers
        while(number<= n){
            //assume number is prime
            boolean isPrime = true;
            //test if number is prime
            for(int divisor = 2;divisor <= (int)(Math.sqrt(number));divisor++){
                if(number % divisor ==0){ //if true then the number is not prime
                    isPrime = false;
                break;
            }
            
        }
        //print the prime number oand  increase the count
        if(isPrime){
            count++;
            if(count % NUMBER_PER_LINE ==0)
                //print and move to a newline
                System.out.println(" "+number);
            else
                System.out.printf("%7d",number);
        }
        number++;
    }
System.out.println("\n"+count +" primes less than or equal to " +n);    
}
}