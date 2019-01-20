package tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import expression.Tree;

/**
 * This class builds the binary tree based on an input text file.
 * 
 * The input file contains the formula.
 * 
 * @author ishakayad96@outlook.fr
 */
public class TreeBuilder {
	private String formula;
	
	public TreeBuilder(String fileName) {
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null)
				if (line.startsWith("F"))
					// Save the formula.
					formula = line.substring(2);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Tree buildTree() {
		Stack<Tree> stack = new Stack<Tree>();
		String AlphabetBuffer = "";
		
		for (int index = 0; index < formula.length(); index++) {
			// The current char in the formula to process
			String currentChar = formula.substring(index, index + 1);
			System.out.println(currentChar);
		}
		// Return the root of the tree (the root IS the tree).
		return stack.pop();
	}
	
	private boolean isParenthesis(String value) {
		return value.equals("(") || value.equals(")");
	}
	
	private boolean isOperation(String value) {
		return value.equals("*") || value.equals("+") || value.equals(".");
	}
	
	private boolean isAlphabet(String value) {
		return !isOperation(value) && !isParenthesis(value);
	}
}
