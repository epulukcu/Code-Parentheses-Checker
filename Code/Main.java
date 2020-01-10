import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * @author Elif Pulukçu 
 * November 16th, 2019
 * Program Explanation: A class to read a Java source code file as an input and if there is no file with the given name&
 * in the given path the program throws an exception. Otherwise, keeps each character in a char array.
 * Then, traverse the array and push opening parentheses into a stack.
 * After each push operation, display the stack content to the screen with a for-each loop. 
 * When a closing (“)” or “}”) parenthesis is encountered, the program pops a parenthesis from the top of the stack and compare
 * them if they match Boolean variable keeps true. In the end, the stack should be empty.
 * If the stack is empty and Boolean doNotMatchFlag variable’s logical value is false program prints “Parentheses are correct.”
 * Or else, parentheses are not correctly written.
 */
public class Main {
	/**
	 * 
	 * @param expression
	 * In parenthesisChecker method, a char array was created to list each character of the Java source code that given by user.
	 * If program encounters an opening parenthesis while navigating the array and adds parenthesis to the stack and prints the contents of the stack structure
	 * to the screen after each addition. If the program encounters a closed parenthesis, it takes the element at the top of the stack and compares them. 
	 * This way program examines the entire file. If the parentheses used in the code are properly closed, the stack must be completely emptied.
	 */

	private static void parenthesisChecker(String expression) {

		char[] input = expression.toCharArray(); //Each character of the Java source code file becomes an element of the input array
		myStack<Character> stack = new myStack<>();  
		boolean doNotMatchFlag = false; 

		for (int i = 0; i < input.length; i++) { //Moving over on the input array

			if (input[i] == '{' || input[i] == '(') { 
				stack.push(input[i]); //When encounter "{" or "(" add them to the stack
				for (Character item : stack) { //Print the stack content at each push operation with a for-each loop
					System.out.print(item + " "); //To print the contents of Stack side by side, I used print instead of println
				}
				System.out.println(" "); //To leave a line empty after printing the contents of the stack
			}

			if (input[i] == '}' || input[i] == ')') { 
				if (stack.isEmpty()) {
					System.out.println(" ");
					System.out.println("Parantheses do not match."); //There is an error if stack is empty and a closing parentheses "}" or ")" came
					doNotMatchFlag = true; 
				} else {  //If the stack is not empty...
					char ch1 = stack.pop(); //The closing parenthesis takes the last element in the stack with pop operation to check if it matches
					char ch2 = input[i]; 
					boolean flag = false;

					if (ch1 == '(' && ch2 == ')') //If the element we are currently on (ith index of the array) matches the element from the top of the stack, flag variable comes true
						flag = true;
					else if (ch1 == '{' && ch2 == '}')
						flag = true;
					else 
						flag = false;

					if (!flag) { //If the flag is false that means parentheses do not match
						System.out.println(" ");
						System.out.println("Parantheses do not match.");
						doNotMatchFlag = true; 
					}
				}
			}

		}

		if (stack.isEmpty() && doNotMatchFlag != true) 
			System.out.println("Parentheses are correct."); 
		else if (doNotMatchFlag != true) { 
			System.out.println("Parantheses do not match.");
		}

	}


	public static void main(String[] args) throws UnsupportedEncodingException, IOException {

		try {
			Scanner input = new Scanner(System.in); // Use Scanner class to take an input from the user. So, import the class
			System.out.println("Enter a file path and name: (Such as C:\\\\SampleCode1.java) "); //Get path information of the file that will review
			String path = input.next(); 
			String content = new String(Files.readAllBytes(Paths.get(path))); //Receive all bytes of the file with the help of Files and Path libraries
			//Assign the contents of the file as a variable named "content"
			parenthesisChecker(content); //Sends file contents to parentheses control method
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
	}

}
