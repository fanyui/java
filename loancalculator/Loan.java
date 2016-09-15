/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loancalculator;

/**
 *
 * @author fanyui
 */

public class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;
    //default constructor 
    public Loan(){
        this(2.5,1,1000);
    }
    //construct a loan with specific  annual interest rate
    public Loan(double annualInterestRate,int numberOfYears,double loanAmount){
        this.annualInterestRate = annualInterestRate;
        this.numberOfYears = numberOfYears;
        this.loanAmount = loanAmount;
        loanDate = new java.util.Date();
    }
    //return annual interest Rate
    public double getAnnualInterestRate(){
        return annualInterestRate;
        
    }
    
    //set annual interestRate
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }
    //return number of years
    public int getNumberOfYears(){
        return numberOfYears;
        
    }
    //set number of years
    public void setNumberOfyears(int numberOfyears){
        this.numberOfYears = numberOfyears;
    }
    
    //return loanAmount
    public double getLoanAmount(){
        return loanAmount;
    }
    
    //set loan amount
    public void setLoanAmount(double loanAmount){
        this.loanAmount = loanAmount;
    }
    //find monthly payment
    public double getMonthlyPayment(){
        double monthlyInterestRate = annualInterestRate/1200;
        double monthlyPayment = loanAmount * monthlyInterestRate /(1-(1/Math.pow(1+monthlyInterestRate, numberOfYears * 12)));
        return monthlyPayment;
    }
    
    //find total payment
    public double getTotalPayment(){
        double totalPayment = getMonthlyPayment() * numberOfYears * 12;
        return totalPayment;
    }
    
    //return loan date
    public java.util.Date getLoanDate(){
        return loanDate;
    }
    
    //
    
}
