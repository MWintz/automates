package export;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import automates.Automate;
import automates.AutomateFactory;

public class TexExport {
	
	private String name;
	private File file;
	
	public TexExport(String name, String path) throws IOException {
		this.name = name;
		this.file = new File(path+name+".tex");
		this.file.delete();
		this.file.createNewFile();
	}
	
	public String automateFormatTex(Automate automate) {
		String tex = "\\begin{tikzpicture}[shorten >=1pt, node distance=3cm, on grid, line width=1pt, auto]\n";
			tex += TexFactory.positionedNodes(automate);
		tex+="\\end{tikzpicture}\n";
		return tex;
	}
	
	public void generateFile() {
		File file = new File("specimen.tex");
		Automate automate;
		
		try {
			BufferedReader read = new BufferedReader(new FileReader(file));
			BufferedWriter write = new BufferedWriter(new FileWriter(this.file));
			String line;
			while ((line = read.readLine()) != null) {
				if(line.contains("|-")) {
					line = line.replace("|-", "");
					line = line.replace("  ", "");
					String data[] = line.split(":");
					automate = AutomateFactory.randomAutomate(Integer.parseInt(data[1]), 
							Integer.parseInt(data[2]), data[3], data[0]);
					System.out.println(automate.transitionTableString());
					String automateTex = automateFormatTex(automate);
					write.write(automateTex);
				}
				else
					write.write(line+"\n");
			}
			read.close();
			write.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
 