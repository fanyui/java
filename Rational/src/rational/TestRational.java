/*
 Test class for the rational class and it methods 
 */
public class TestRational {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
       Rational r1 = new Rational(4,2);
       Rational r2 = new Rational(2,3);
       
        System.out.println(r1+" + "+r2+"= "+r1.add(r2));
        System.out.println(r1+" - "+r2+"= "+r1.subtract(r2));
        System.out.println(r1+" * "+r2+"= "+r1.multiply(r2));
        System.out.println(r1+"/ "+r2+"= "+r1.divide(r2));
        System.out.println(r2+ " is " +r2.doubleValue());
        System.out.println("r1 toString is "+r1.toString());
        System.out.println("r2 toSting is "+r2.toString());
    }
    
}
