package test;

import turing.TuringMachin;

public class TestTuring {
	public static void main(String[] args) {
		TuringMachin turing = new TuringMachin("mult2");
		
		String binary_value = "1000";
		String result = turing.turing(binary_value);
		
		System.out.println("\n\ninput: "+binary_value+" ===> result: "+result);
	}
}
