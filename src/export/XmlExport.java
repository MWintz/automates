package export;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import automates.Alphabet;
import automates.Automate;

public class XmlExport {
	private String name;
	private File file;
	private HashMap<Integer, Automate> list = new HashMap<Integer, Automate>();
	private HashMap<Integer, ArrayList<Alphabet>> alphalist = new HashMap<Integer, ArrayList<Alphabet>>();
	private String question;
	
	
	/*Constructors*/
	public XmlExport(String name, String path) throws IOException {
		this.name = name;
		this.file = new File(path+name+".xml");
		this.file.delete();
		this.file.createNewFile();
	}	
	
	public XmlExport(String name, Path path) throws IOException {
		this.name = name;
		new File(path.toString()).mkdirs();
		this.file = new File(path+name+".xml");
		this.file.delete();
		this.file.createNewFile();
	}

	/*Getters and Setters*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public HashMap<Integer, Automate> getList() {
		return list;
	}

	public void setList(HashMap<Integer, Automate> list) {
		this.list = list;
	}
	
	public void addList(Automate auto) {
		this.list.put(this.list.size()+1,auto);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String string) {
		this.question = string;
	}

	/*Methods*/
	/*Creates the HTML file
	 * to use once the content is defined
	 * Rewrites the file otherwise*/
	public void generateFile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
		BufferedReader br = new BufferedReader(new FileReader("specimen.xml"));
		/*En tete*/
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		bw.write("<quiz>");
		bw.newLine();
		bw.write("<question type=\"category\">");
		bw.newLine();
		bw.write("<category>");
		bw.newLine();
		bw.write("<text>$course$/"+question+"</text> <!-- Donne la categorie de la base de questions-->");
		bw.newLine();
		bw.write("</category>");
		bw.newLine();
		bw.write("</question>");
		bw.newLine();
		
		String fullquestion = "";
		String line = br.readLine();
		while (line != null) {
			fullquestion += line+"\n";
			line = br.readLine();
		}
		String typeFullQuestion = fullquestion;		
		/*Corps*/
		/*Pour chaque automate � placer dans le fichier*/
		for(int i = 0; i < this.list.size() ; i++){
			fullquestion = typeFullQuestion;
			Automate auto = this.list.get(i);
			Question quest;
			switch(this.question) {
			case "deter" : quest = new Deter(auto);
				break;
			//TODO find a way to modify the number of question without modifying the code
			case "recognition" : quest = new Recognition(auto, alphalist.get(i), 3);
				break;
			case "determiniser" : quest = new Determiniser(auto);
				break;
			default : quest = new Deter(auto);
				break;
			}
			fullquestion = fullquestion.replaceAll("#NUMBER#",""+i);
			fullquestion = fullquestion.replace("#NAME#",question+" "+i);
			fullquestion = fullquestion.replace("#QUESTION#", quest.getQuestion());
			
			/*On donne un titre au nouveau tableau*/
			String tabletowrite = "";
			tabletowrite += "<TABLE BORDER=\"1\">\n ";
			int ns = i+1;
			tabletowrite += "<CAPTION>Automate "+ns+"</CAPTION>\n";
//			tabletowrite += "<TR><TH></TH>\n";

			String[][] table = auto.transitionTable();
			for (int j=0; j<auto.getAutomate().size()+1;j++) {
				tabletowrite += "<TR>\n";
				for (int k=0; k<table[j].length; k++) {
					tabletowrite += "<TH>"+table[j][k]+"</TH>\n";
					}
				tabletowrite += "</TR>\n";
				}			
			tabletowrite += "</TABLE>\n";
			fullquestion = fullquestion.replace("#CONTENT#", tabletowrite);
			fullquestion = fullquestion.replace("#REMINDER#", quest.getReminder());
			fullquestion = fullquestion.replace("#ANSWER#", quest.getAnswer());
			fullquestion = fullquestion.replace("#FEEDBACK#", quest.getFeedback());
			fullquestion = fullquestion.replace("#PENALTY#", ""+quest.getPenalty());
			fullquestion = fullquestion.replace("#HIDDEN#", ""+quest.getHidden());
			
			bw.write(fullquestion);
		}
		
		bw.close();
		br.close();
	

	}
	
	public void addAutomate(Automate automate) {
		int size = this.list.size();
		this.list.put(size, automate);
	}
	
	public void open() throws IOException {
		if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop is not supported");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists()) desktop.open(this.file);
	}

	public HashMap<Integer, ArrayList<Alphabet>> getAlphalist() {
		return alphalist;
	}

	public void setAlphalist(HashMap<Integer, ArrayList<Alphabet>> alphalist) {
		this.alphalist = alphalist;
	}
}