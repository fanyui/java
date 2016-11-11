/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomplexnum;
import java.util.Scanner;
/**
 *
 * @author harisu
 */
public class TestComplexNum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.println("input First complex number");
//       float r1 = input.nextFloat();
//       float im1 = input.nextFloat();
//        System.out.println(" input second complex number");
//        float r2 = input.nextFloat();
//        float im2 = input.nextFloat();
        Complex num1 = new Complex(3.5,5.5);
        Complex num2 = new Complex(-3.5,1);
        System.out.println(num1+" + "+num2+" = "+num1.add(num2));
        System.out.println(num1+" - "+ num2+" = "+num1.subtract(num2));
        System.out.println(num1+" * "+ num2+" = "+num1.multiply(num2));
        System.out.println("|"+num1+"| = "+num1.abs());
        System.out.println("|"+num2+"| = "+num2.abs());
    }
    
}