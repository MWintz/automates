package tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import expression.NodeFactory;
import expression.Operation;
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
	
	public TreeBuilder() {
		formula=null;
	}
	
	public Tree buildTree() {
		Stack<Tree> stack_tree=new Stack<Tree>();
		Stack<String> stack_str=new Stack<String>();
		int index=0;
		boolean erreur=false;
		Tree alpha,left,right;
		String oper;
		while (index<formula.length() && !erreur){
			// The current char in the formula to process
			String currentChar = formula.substring(index, index + 1);
			
			if(!isParenthesisO(currentChar) && !isSeparator(currentChar)) {
				if(isAlphabet(currentChar)) {
					alpha=NodeFactory.creatAlphabet(currentChar.charAt(0));
					stack_tree.push(alpha);
				}
				else {
					if(isOperationBinary(currentChar))
						stack_str.push(currentChar);
					else {
						if(isParenthesusF(currentChar)) {
							oper=stack_str.pop();
							if(isOperationBinary(oper)){
								right=stack_tree.pop();
								left=stack_tree.pop();
								Operation node=NodeFactory.creatOperation(oper.charAt(0), left, right);
								stack_tree.push(node);
							}
							else
								erreur=true;
						}
						else if(isOperationUnary(currentChar)) {
								left=stack_tree.pop();
								Operation node=NodeFactory.creatOperation(currentChar.charAt(0), left, null);
								stack_tree.push(node);
								if(index<formula.length()-1) {
									String next = formula.substring(index+1, index+2);
										if(isParenthesusF(next) && next!=null)
											index++;
								}
						}
						else
							erreur=true;
					}
				}
			}
				
			index++;
		}
		// Return the root of the tree (the root IS the tree).
		return stack_tree.pop();
	}
	
	private boolean isSeparator(String value) {
		return value.equals(" ") || value.equals(",");
	}
	
	private boolean isParenthesisO(String value) {
		return value.equals("(");
	}
	
	private boolean isParenthesusF(String value) {
		return value.equals(")");
	}
	
	private boolean isOperationBinary(String value) {
		return value.equals("+") || value.equals(".");
	}
	
	private boolean isOperationUnary(String value) {
		return value.equals("*");
	}
	
	private boolean isAlphabet(String value) {
		return !isOperationUnary(value) && !isOperationBinary(value) && !isParenthesisO(value) && !isParenthesusF(value) && !isSeparator(value);
	}
	
	public void setFormula(String formula) {
		this.formula=formula;
	}
}
