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
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import automates.Alphabet;
import automates.Automate;
import automates.State;
import automates.Transition;

public class HtmlExport {
	private String name;
	private File file;
	private HashMap<Integer, Automate> list = new HashMap<Integer, Automate>();
	private Question question;
	
	
	/*Constructors*/
	public HtmlExport(String name) {
		this.name = name;
		new File("/Cours/HTML/").mkdirs();
		this.file = new File("/Cours/XML/"+name+".xml");
	}	
	
	public HtmlExport(String name, Path path) {
		this.name = name;
		new File(path.toString()).mkdirs();
		this.file = new File(path+name+".html");
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	/*Methods*/
	/*Creates the HTML file
	 * to use once the content is defined
	 * Rewrites the file otherwise*/
	public void generateFile() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
		BufferedReader br = new BufferedReader(new FileReader("/Cours/XML/speciment.xml"));
		/*En tete*/
		bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		bw.newLine();
		bw.write("<quiz>");
		bw.newLine();
		bw.write("<question type=\"category\">");
		bw.newLine();
		bw.write("<category>");
		bw.newLine();
		bw.write("<text>$course$/"+question.getQtype()+"</text> <!-- Donne la categorie de la base de questions-->");
		bw.newLine();
		bw.write("</category>");
		bw.newLine();
		bw.write("</question>");
		bw.newLine();
		
		String fullquestion = "";
		while (br.readLine() != null) {
			fullquestion += br.readLine();
		}
		
		/*Corps*/
		/*Pour chaque automate à placer dans le fichier*/
		for(int i = 0; i < this.list.size() ; i++){
			Automate auto = this.list.get(i);
			fullquestion.replaceAll("#NUMBER#",""+i);
			fullquestion.replace("#NAME#",question.getQtype()+" "+i);
			fullquestion.replace("#QUESTION#", question.getQuestion());
			
			/*On donne un titre au nouveau tableau*/
			String tabletowrite = "";
			tabletowrite += "<TABLE BORDER=\"1\">\n ";
			int ns = i+1;
			tabletowrite += "<CAPTION>Automate "+ns+"</CAPTION>\n";
			tabletowrite += "<TR><TH></TH>\n";
			ArrayList<Alphabet> alpha = auto.getAlphabet();
			/*On trie l'alphabet dans l'ordre alphabetique*/
			alpha.sort(new Comparator<Alphabet>(){

				@Override
				public int compare(Alphabet alphabet1, Alphabet alphabet2) {
						String alpha1 = ""+alphabet1.getValue();
						String alpha2 = ""+alphabet2.getValue();
					return alpha1.compareTo(alpha2);
				}
				
			});
			/*On place chaque caractere de l'alphabet dans un titre de colonne*/
			for (int j = 0; j < alpha.size(); j++) {
				
				tabletowrite += "<TH>"+alpha.get(j)+"</TH>\n";
			}
			/*On ferme la premiere ligne*/
			tabletowrite += "</TR>\n";
			/*Pour chaque etat de l'automate*/
			String[][] table = auto.transitionTable();
			for (int j=1; j<auto.getAutomate().size()+1;j++) {
				tabletowrite += "<TR>\n";
				for (int k=0; k<alpha.size()+1; k++) {
					tabletowrite += "<TH>"+table[j][k]+"</TH>\n";
					}
				tabletowrite += "</TR>\n";
				}
/*			TENTATIVE RATEE AVEC UN COMPARATEUR
 			for(int k = 0; k < auto.getAutomate().size(); k++) {

				
				Collection<State> setAuto = auto.getAutomate().values();
				ArrayList<State> arlia = new ArrayList<State>(setAuto);
				
				arlia.sort(new Comparator<State>() {

					@Override
					public int compare(State state0, State state1) {
						return state0.getId_state().compareTo(state1.getId_state());
					}
					
				});
				State stat = arlia.get(k);
				bw.write("<TR><TH>"+stat.getId_state());
				if(stat.isInitial()) {
					bw.write(" init");
				}
				if (stat.isFinal()) {
					bw.write(" final");
				}
				bw.write("</TH>");
				for(int l = 0; l < alpha.size(); l++) {
					HashSet<Transition> trans = stat.getTransition();
					Alphabet testChar = alpha.get(0);
					for (Alphabet al : alpha) {
						bw.newLine();
						bw.write("<TH>");
						for (Transition tra : stat.getTransition()) {
							if (tra.getLabel() == al) {
								bw.write(tra.getState().getId_state()+ " ");
							}
						}
						bw.write("</TH>");
					}
				bw.write("</TR>");
				}
*/			
			tabletowrite += "</TABLE>\n";
			fullquestion.replace("#CONTENT#", tabletowrite);
			fullquestion.replace("#REMINDER#", question.getReminder());
			fullquestion.replace("#ANSWER#", question.getAnswer());
			fullquestion.replace("#FEEDBACK#", question.getFeedback());
			fullquestion.replace("#PENALTY#", ""+question.getPenalty());
			fullquestion.replace("#HIDDEN#", ""+question.getHidden());
		}
		
		
	

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
}
