package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.*;

import automates.Automate;
import expression.Tree;
import tree.ThompsonVisitor;
import tree.TreeBuilder;

import java.awt.Color;

public class RegexFrame extends JFrame implements Runnable{
	
	private static final int THREAD_MAP = 1000;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldExp;
	private JTextArea textAreaTarS;
	private JLabel result;
	private JCheckBox checkBoxOK;

	public static void main(String[] args) {
		new RegexFrame();
	}

	public RegexFrame() {
		super("Regex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 262);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regular expression");
		lblNewLabel.setBounds(56, 12, 138, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblTargetString = new JLabel("Target String");
		lblTargetString.setBounds(56, 70, 92, 15);
		contentPane.add(lblTargetString);
		
		textFieldExp = new JTextField();
		textFieldExp.setBounds(56, 39, 277, 19);
		contentPane.add(textFieldExp);
		textFieldExp.setColumns(10);
		
		textAreaTarS = new JTextArea();
		textAreaTarS.setBounds(56, 97, 277, 64);
		contentPane.add(textAreaTarS);
		
		result = new JLabel("");
		result.setVerticalAlignment(SwingConstants.TOP);
		result.setBounds(56, 173, 317, 38);
		contentPane.add(result);
		
		checkBoxOK = new JCheckBox("");
		checkBoxOK.setBounds(340, 37, 126, 23);
		contentPane.add(checkBoxOK);
		setVisible(true);
		
		launchGUI();
	}

	
	private void launchGUI(){		
		Thread chronoThread = new Thread(this);
		chronoThread.start();
	}
	
	@Override
	public void run() {
		while(true) {	
			simulation();
			try {
				Thread.sleep(THREAD_MAP);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void simulation() {
		if(textFieldExp.getText().isEmpty()) {
			result.setForeground(Color.RED);
			result.setText("Enter your Regular expression");
		}
		else {
			if(checkBoxOK.isSelected()) {
				TreeBuilder builder = new TreeBuilder();
				builder.setFormula(textFieldExp.getText());
				Tree tree = builder.buildTree();
				ThompsonVisitor algo_thompson=new ThompsonVisitor();
				Automate result_automate=tree.accept(algo_thompson);
				result_automate.synchronization();
				
				String auto_res=result_automate.wordRecognition(textAreaTarS.getText());
				
				String resutl="the word :('"+textAreaTarS.getText()+"')-> has been "+auto_res;
				
				if(auto_res.equals("accepted"))
					result.setForeground(Color.GREEN);
				else
					result.setForeground(Color.RED);
				
				result.setText(resutl);
			}
		}
	}
}
