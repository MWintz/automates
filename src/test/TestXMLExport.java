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
		XmlExport exp = new XmlExport("recognition", "/Cours/XML/");
		XmlExport exp2 = new XmlExport("deter", "/Cours/XML/");
		XmlExport exp3 = new XmlExport("determinist", "/Cours/XML/");

		HashMap<Integer, Automate> liste = new HashMap<Integer, Automate>();
		HashMap<Integer, ArrayList<Alphabet>> alphalist = new HashMap<Integer, ArrayList<Alphabet>>();
		for (int i=0; i<10;i++) {
			Automate auto = AutomateFactory.randomADeterminist(5, 3, "default");	
			liste.put(i,auto);
			alphalist.put(i, auto.getAlphabet());
		}
		
		exp.setList(liste);
		exp.setAlphalist(alphalist);
		exp.setQuestion("recognition");
		exp2.setList(liste);
		exp2.setAlphalist(alphalist);
		exp2.setQuestion("deter");
		exp3.setList(liste);
		exp3.setAlphalist(alphalist);
		exp3.setQuestion("determiniser");
		exp.generateFile();
		exp2.generateFile();
		exp3.generateFile();

	}

	public ArrayList<Alphabet> getAlpha() {
		return alpha;
	}

	public static void setAlpha(ArrayList<Alphabet> arrayList) {
		alpha = arrayList;
	}	
}