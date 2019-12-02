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
    	System.out.println("Type q to quit or press enter to continue: ");
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
    		function = "a";
    	}
    	else if(subtraction > 0) {
    		operand2 = input.substring(subtraction+3);
    		operand1 = input.substring(0, subtraction);
    		function = "s";
    	}
    	else if(division > 0) {
    		operand2 = input.substring(division+3);
    		operand1 = input.substring(0, division);
    		function = "d";
    	}
    	else if(multiplication > 0) {
    		operand2 = input.substring(multiplication + 3); 
    		operand1 = input.substring(0, multiplication);
    		function = "m";
    	}
    	else {
			System.out.println("Invalid input: No Operator"); 
		}
    	
    	//Parses strings into fractions
    	operand1 = parseFrac(operand1, function);
    	operand2 = parseFrac(operand2, function);
    	//Performs function of fractions
    	String fracAnswer = evaluteAnswer(operand1, operand2, function);  
    	String finalAnswer = simplifyAnswer(fracAnswer);
    	
    	return finalAnswer;
    }
    
    public static String parseFrac(String input, String function) {
    	int multiFrac = input.indexOf("_"); 
    	int regFrac = input.indexOf("/");
    	int functionMark = input.indexOf(function); 
    	String numerator = "";
    	String denominator  = ""; 
    	String whole = ""; 
    	String fullNum = "";
    	
    	if(multiFrac > 0) {
    		whole = input.substring(functionMark+1, multiFrac);
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
    
    	fullNum = "w:" + whole + " n:" + numerator + " d:" + denominator; 
     	return fullNum; 
    }
    
    public static String evaluteAnswer(String op1, String op2, String function) {
    	
    	int whole1 = Integer.valueOf(op1.substring(op1.indexOf("w:")+2,op1.indexOf("n:")-1)); 
    	int whole2 = +Integer.valueOf(op2.substring(op2.indexOf("w:")+2,op2.indexOf("n:")-1));
    	int deno1 = Integer.valueOf(op1.substring(op1.indexOf("d:")+2));
    	int deno2 = Integer.valueOf(op2.substring(op2.indexOf("d:")+2));
    	int num1 = Integer.valueOf(op1.substring(op1.indexOf("n:")+2,op1.indexOf("d:")-1)); 
    	int num2 = Integer.valueOf(op2.substring(op2.indexOf("n:")+2,op2.indexOf("d:")-1)); 
    	
    	if(whole1 != 0) {
    		num1 = num1 + (whole1*deno1); 
    	}
    	if(whole2 != 0) {
    		num2 = num2 + (whole2*deno2); 
    	}
    	
    	int commonDeno = deno1*deno2; 
    	int commonNum1 = deno2*num1;
    	int commonNum2 = deno1*num2; 
    	String finalAnswer = " ";
    	
    	if (function.equals("a")) {
    		finalAnswer = (commonNum1+commonNum2) + "/" + commonDeno;
    	}
    	if (function.equals("s")) {
    		finalAnswer = (commonNum1-commonNum2) + "/" + commonDeno;
    	}
    	if (function.equals("d")) {
    		finalAnswer = (num1*deno2) + "/" + (deno1*num2);
    	}
    	if (function.equals("m")) {
    		finalAnswer = (num1*num2) + "/" + (deno1*deno2);
    	}

    	return finalAnswer; 
    }
    
    public static String simplifyAnswer(String answer) {
    	int num = Integer.valueOf(answer.substring(0,answer.indexOf("/"))); 
    	int deno = Integer.valueOf(answer.substring(answer.indexOf("/")+1));
    	int count = 0; 
    	int tempnum = Integer.valueOf(answer.substring(0,answer.indexOf("/"))); 
    	int tempdeno = Integer.valueOf(answer.substring(answer.indexOf("/")+1));
    	
    	while(true) {
    	tempnum = tempnum - tempdeno; 
    	count++; 
    	if(tempnum < 0) {
    			count --; 
    			break; 
    		}
    	}
    	
    	num = num - (count*deno); 
    	
    	int gcd = 1; 
        for(int i = 1; i <= num && i <= deno; i++)
        {
            if(num%i==0 && deno%i==0)
                gcd = i;
        }
        
        num = num/gcd;
        deno = deno/gcd;  
        
        if(num == 0) {
        	return count + ""; 
        } else {
        	return count + "_" + num + "/" + deno;  
        } 
        
    }
    

    // TODO: Fill in the space below with any helper methods that you think you will need

}
