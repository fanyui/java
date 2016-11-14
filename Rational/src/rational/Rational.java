/*
The rational Class implementation to carry out operations on rational number such as add multiply divide subtract
simplifying the resulting answer to it lowest term for carying out calculations on this rational number with a degree of accuracy
and precision
 *
 * @author harisu
 */
public class Rational extends Number implements Comparable<Rational> {

    /**
     * @param args the command line arguments
     */
   private long numerator = 0;
   private long denomenator =1;
   //default no argument constructor
   public Rational(){
       this(0,1);
   }
   //constructor with numerator an denomenator
   public  Rational(long numerator,long denomenator){
       long gcd = gcd(numerator,denomenator);
       this.numerator = ((denomenator>0)?1:-1) *numerator/gcd;
       this.denomenator = Math.abs(denomenator)/gcd;
   }
   //method to find the gcd of the numerator and denomenator for simplifying the fraction
   private static long gcd(long n, long d){
       long n1 = Math.abs(n);
       long n2 = Math.abs(d);
       int gcd = 1;
       for  (int k=1; k<= n1 && k<= n2; k++){
           if(n1%k ==0 && n2%k ==0)
               gcd = k;
       }
       return gcd;
   }
   //return a numerator
   public long getNumerator(){
       return this.numerator;
   }
   //return denomenator
   public long getDenomenator(){
       return this.denomenator;
   }
   //add a rational number to this rational number
   public Rational add(Rational secondRational){
       long n = numerator* secondRational.getDenomenator() + denomenator *secondRational.getNumerator();
       long d = denomenator * secondRational.getDenomenator();
       return new Rational(n,d);
   }
   //subtract rational number from this rational number
   public Rational subtract(Rational secondRational){
       long n = this.numerator *secondRational.getDenomenator() - this.denomenator *secondRational.getNumerator();
       long d = this.denomenator *secondRational.getDenomenator();
       return new Rational(n,d);
}
   
   //multiply rational number to this rational number
   public Rational multiply(Rational secondRational){
       long n = this.numerator*secondRational.getNumerator();
       long d = this.denomenator* secondRational.getDenomenator();
       return new Rational(n,d);
   }
   //divide a rational number by  this rational number
   public Rational divide(Rational secondRational){
       long n = this.numerator * secondRational.getDenomenator();
       long d = this.denomenator * secondRational.getNumerator();
       return new Rational(n,d);
   }
   
   @Override
   public String toString(){
       if(this.denomenator == 1)
           return numerator +"";
       else 
           return numerator +"/"+ denomenator;
   }
   @Override
   public boolean equals(Object other){
       if ((this.subtract((Rational)(other))).getNumerator()== 0)
        return true;
       else 
       return false;
   }
   @Override //implement the abstract int value method of number
   public int intValue(){
       return (int)doubleValue();
   }
   @Override //implement the floatValue mthod of number  class
   public float floatValue(){
       return (float)doubleValue();
   }
   @Override //implement the double value of number class
   public double doubleValue(){
       return this.numerator*1.0/this.denomenator;
   }
   @Override //implement the abstract long value mehtod in number
   public long longValue(){
       return (long)doubleValue();
   }
   @Override //implemthe the compareTo method in comparable
   public int compareTo(Rational o){
       if (this.subtract(o).getNumerator()>0)
           return 1;
       else if(this.subtract(o).getNumerator()<0)
           return -1;
       else
           return 0;
   }
   
}

