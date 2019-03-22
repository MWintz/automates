package turing;

import automates.Alphabet;
import automates.Automate;
import automates.State;

public class TuringMachin {
	
	public final static char NULL = '\0';
	public final static int TABLE_SIZE = 500;
	
	private char table[];
	private int nbChar;
	private Automate turing_automate;
	private String type_turing_machin;
	private State s_turing;
	private int index = (TABLE_SIZE / 2) - 1;
	
	public TuringMachin() {
		this.nbChar = 0;
		this.table = new char[TABLE_SIZE];
	}
	
	public TuringMachin(String type_turing_machin) {
		this.type_turing_machin = type_turing_machin;
		this.nbChar = 0;
		this.table = new char[TABLE_SIZE];
		creatTuringMachin(type_turing_machin);
		s_turing = turing_automate.getInitial_Final_State("initial").get(0);
	}
	
	public void creatTuringMachin(String type_turing_machine) {
		switch (type_turing_machine) {
			case "reverse0_1":
				turing_automate = TuringFactory.reverse0_1();
				break;
			case "add1":
				turing_automate = TuringFactory.add1();
				break;
			case "sub1":
				turing_automate = TuringFactory.sub1();
				break;
			case "mult2":
				turing_automate = TuringFactory.mult2();
				break;
		}
	}
	
	public void fillTable(String binary_value) {
		this.nbChar = binary_value.length();
		int middel = TABLE_SIZE / 2;
		for(int i = 0; i < binary_value.length(); i++) {
			table[middel] = binary_value.charAt(i);
			middel++;
		}
	}
	
	public void debug_table(int index, char value) {
		System.out.println("------------------------");
		int max = ((TABLE_SIZE / 2) - 1) + nbChar;
		if(index > max) max = index;
		
		for(int i = (TABLE_SIZE / 2) - 1; i <= max; i++) {
			if((table[i]=='\0') && (i==index))
				System.out.print(" ");
			else if(table[i] != '\0')
				System.out.print(table[i]);
		}
		System.out.println();
		
		for(int i = (TABLE_SIZE / 2) - 1 ; i <= max; i++) {
			if(i == index-1 || index == (TABLE_SIZE / 2) - 1) {
				System.out.println("^");
				break;
			}
			else
				System.out.print(" ");
		}
		
		for(int i = (TABLE_SIZE / 2) - 1 ; i <= max; i++) {
			if(i == index-1 || index == (TABLE_SIZE / 2) - 1) {
				System.out.println(value);
				break;
			}
			else
				System.out.print(" ");
		}

	}
	
	public void turing_eval() {
		index = (TABLE_SIZE / 2) - 1;
		boolean end = false;
		
		while(!end)
			end = turing_proces();
	}
	
	public boolean turing_proces() {
		boolean end = false;
		Alphabet write = null;
		int move = 0;
		for(TuringTransition ttran : s_turing.getTuring_transition()) {
			if(ttran.getLabel().getValue() == table[index]) {
				write = ttran.getWrite();
				debug_table(index, write.getValue());
				move = ttran.getMove();
				s_turing = ttran.getState();
				break;
			}
		}
		
		table[index] = write.getValue();
		if(s_turing.isFinal())
			end = true;
		index = index + move;
		
		return end;
	}
	
	public String turing(String binary_value) {
		String result = "";
		
		fillTable(binary_value);
		turing_eval();
		
		for(int i = 0; i < table.length; i++)
			if(table[i] != '\0')
				result += table[i];
		return result;
	}
	
	public char[] getTable() {
		return table;
	}
	
	public void setTable(char[] table) {
		this.table = table;
	}
	
	public int getNbChar() {
		return nbChar;
	}
	
	public void setNbChar(int nbChar) {
		this.nbChar = nbChar;
	}
	
	public Automate getTuring_automate() {
		return turing_automate;
	}
	
	public void setTuring_automate(Automate turing_automate) {
		this.turing_automate = turing_automate;
	}
	
	public String getType_turing_machin() {
		return type_turing_machin;
	}
	
	public void setType_turing_machin(String type_turing_machin) {
		this.type_turing_machin = type_turing_machin;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}
