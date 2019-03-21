package test;

import automates.Automate;
import automates.AutomateFactory;

public class TestCreatAutomateByFile {
	public static void main(String[] args) {
		Automate automate = AutomateFactory.creatAutomateByFile("automate1.txt");
		System.out.println(automate.transitionTableString());
	}
}
