/*
program to carry out operations on complex numbers such as addition
subtraction, multiplication division and absolute values of the complex number
 */
package testcomplexnum;

/**
 *
 * @author harisu
 */
public class Complex extends Object {
    private double real;
    private double imaginary ;
    //default constructor with zero real and zero im part
    public Complex(){
        this.real= 0;
        this.imaginary = 0;
    }
    //one argument constructor with only real part
    public Complex(double re){
        this.real = re;
        this.imaginary = 0;
    }
    
    //two argument constructor with real and imaginary parts
    public Complex(double re,double im){
        this.real= re;
        this.imaginary = im;
    }
    //add this complex number to another
    public Complex add(Complex num){
        double re = this.real + num.real;
        double im = this.imaginary + num.imaginary;
        
        return new Complex(re,im);
    }
    //subtract a complex num from this
    public Complex subtract(Complex num){
        double re = this.real - num.real;
        double im = this.imaginary - num.imaginary;
        return new Complex(re,im);
    }
    //multiply this complex number by another
    public Complex multiply(Complex num){
        double re = this.real*num.real -this.imaginary*num.imaginary;
        double im = this.real*num.imaginary + this.imaginary* num.real;
        return new Complex(re,im);
    }
    //divide this complex number with another one
    public Complex divide(Complex num){
        double re = (this.real*num.real + this.imaginary * num.imaginary)/(double)Math.pow(num.real, 2)+(double)Math.pow(num.imaginary, 2);
        double im = ((this.imaginary * num.real)-(this.real*num.imaginary)) / (double)Math.pow(num.imaginary, 2)+(double)Math.pow(num.real, 2);
        return new Complex(re,im);
    }
    //returns the absolute value of the Complex number
    public double abs(){
        return (double)Math.sqrt((Math.pow(this.real, 2)+Math.pow(this.imaginary, 2)));
    }
    
    @Override
         public String toString(){
           //  String.format("%d ", args)
                     if(this.imaginary==0)
                         return String.format("%f", this.real);
                     else 
                         return String.format("%f + %fi", this.real,this.imaginary);
         }
    //retrun the real part of the complex number
    public double getRealPart(){
        return this.real;
    }
    //return the imaginary part
    public double getImaginaryPart(){
        return this.imaginary;
    }
}
