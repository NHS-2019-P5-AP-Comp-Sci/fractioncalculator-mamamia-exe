/**
 * @author Mr. Rasmussen
 */
 
package fracCalc;
import java.util.*;
public class FracCalc {

    public static void main(String[] args)
    {
    String quit = ""; 
    while(true) {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	
    	//Checks for input and runs through produce answer function
    	Scanner console = new Scanner(System.in); 
    	String input = console.nextLine(); 
    	String operand2 = produceAnswer(input);
    	System.out.println(operand2); 
    	
    	//Checks to quit loop 
    	System.out.println("Type q to quit: ");
    	quit = console.nextLine(); 
    	if(quit.equals("q")) {
    		System.out.println("Thank you for using Frac Calc"); 
    		break; 
    	}
    	
    } 	
    	
    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    {
        //Set up variables 
      	int addition = input.indexOf(" + ");
    	int subtraction = input.indexOf(" - ");
    	int division = input.indexOf(" / ");
    	int multiplication = input.indexOf(" * ");
    	String operand2 = ""; 
    	String operand1 = ""; 
    	String function = ""; 
    	
    	//Checks for operator and splits string
    	if(addition > 0) {
    		operand2 = input.substring(addition+3);
    		operand1 = input.substring(0, addition);
    		function = "add";
    	}
    	else if(subtraction > 0) {
    		operand2 = input.substring(subtraction+3);
    		operand1 = input.substring(0, subtraction);
    		function = "minus";
    	}
    	else if(division > 0) {
    		operand2 = input.substring(division+3);
    		operand1 = input.substring(0, division);
    		function = "divide";
    	}
    	else if(multiplication > 0) {
    		operand2 = input.substring(multiplication + 3); 
    		operand1 = input.substring(0, multiplication);
    		function = "multiply";
    	}
    	else {
			System.out.println("Invalid input: No Operator"); 
		}
    	
    	//Parses strings into fractions
    	operand1 = parseFrac(operand1);
    	//operand2 = parseFrac(operand2);
    	//Performs function of fractions
    	//String fracAnswer = evaluteAnswer(operand1, operand2, function); 
    	
    	return operand2;
    }
    
    public static String parseFrac(String input) {
    	int multiFrac = input.indexOf("_"); 
    	int regFrac = input.indexOf("/");
    	String numerator = "";
    	String denominator  = ""; 
    	String whole = ""; 
    	String fullNum = "";
    	
    	if(multiFrac > 0) {
    		whole = input.substring(multiFrac - 1, multiFrac);
    		numerator = input.substring(multiFrac + 1, regFrac); 
    		denominator = input.substring(regFrac + 1); 
    	} else if (multiFrac < 0 & regFrac > 0) {
    		whole = "0";
    		numerator = input.substring(0, regFrac);
    		denominator = input.substring(regFrac + 1, input.length()); 
    	} else {
    		whole = input.substring(0, input.length());
    		numerator = "0";
    		denominator = "1";
    	} 
    
    	fullNum = "whole:" + whole + " numerator:" + numerator + " denominator:" + denominator; 
     	return fullNum; 
    }
    
    public static String evaluteAnswer(String op1, String op2, String function) {
    	String finalAnswer = "";
    
    	int denominator1 = (int)op1.charAt(op1.indexOf("denominator:" + 12));
    	int wholeFrac1 = (denominator1*(int)op1.charAt(op1.indexOf("whole:") + 6));
    	if(wholeFrac1 > 0) {
    		int numerator1 = (int)op2.charAt(op1.indexOf("numerator:") + 10)+wholeFrac1;
    	} else {
    		int numerator1 = (int)op2.charAt(op1.indexOf("numerator:") + 10); 
    	}
    	int denominator2 = (int)op2.charAt(op1.indexOf("denominator:" + 12));
    	int wholeFrac2 = (denominator2*(int)op2.charAt(op1.indexOf("whole:") + 6));
    	if(wholeFrac2 > 0) {
    		int numerator2 = (int)op2.charAt(op1.indexOf("numerator:") + 10)+wholeFrac2;
    	} else {
    		int numerator2 = (int)op2.charAt(op1.indexOf("numerator:") + 10); 
    	}

    	int commonDeno = denominator1*denominator2;
    	int numerator1Ans = denominator2*numerator1;
    	int numerator2Ans = denominator1*numerator2;
    	
    	if(function == "add") {
    		finalAnswer = (numerator1Ans+numerator2Ans) + "/" + commonDeno; 
    	} 
    	
    	if(function == "minus") {
    		finalAnswer = (numerator1Ans-numerator2Ans) + "/" + commonDeno;
    	}
    	
    	if(function == "multiply") {
    		finalAnswer = (numerator1*numerator2) + "/" + commonDeno; 
    	}
    	
    	if(function == "divide") {
    		finalAnswer = numerator1Ans + "/" + (denominator1*numerator2);
    	}
    	
    	return finalAnswer; 	
    }
    
    public static String simplifyAnswer(String answer) {
    	
    	return null; 
    }
    

    // TODO: Fill in the space below with any helper methods that you think you will need

}
