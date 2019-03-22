package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import automates.Alphabet;
import automates.Automate;
import automates.AutomateFactory;
import export.XmlExport;

public class TestXMLExport {
	static ArrayList<Alphabet> alpha;
	
	public static void main(String[] args) throws IOException {
		
//		XmlExport exp = new XmlExport("test", "/home/ishak/");
		XmlExport exp = new XmlExport("test", "/Cours/XML/");
		HashMap<Integer, Automate> liste = new HashMap<Integer, Automate>();
		HashMap<Integer, ArrayList<Alphabet>> alphalist = new HashMap<Integer, ArrayList<Alphabet>>();
		for (int i=0; i<10;i++) {
			Automate auto = AutomateFactory.randomADeterminist(5, 3, "default");	
			liste.put(i,auto);
			alphalist.put(i, auto.getAlphabet());
			System.out.println(auto.isDeterminist());
		}
		
		exp.setList(liste);
		exp.setAlphalist(alphalist);
		exp.setQuestion("recognition");
		
		exp.generateFile();
	
	}

	public ArrayList<Alphabet> getAlpha() {
		return alpha;
	}

	public static void setAlpha(ArrayList<Alphabet> arrayList) {
		alpha = arrayList;
	}	
}