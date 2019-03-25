package gui;

import javax.swing.*;
import javax.swing.border.*;
import automates.*;
import expression.*;
import tree.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegexFrame extends JFrame implements Runnable{
	
	private static final int THREAD_MAP = 10;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldExp,textFieldExp1,textFieldExp2;
	private JTextArea textAreaTarS;
	private JLabel result,result_equals,lblExp1,lblExp2;
	private JCheckBox checkBoxOK;
	private String formula=null;
	private Automate result_automate;
	private ThompsonVisitor algo_thompson=new ThompsonVisitor();
	private Tree tree;
	private TreeBuilder builder = new TreeBuilder();

	public static void main(String[] args) {
		new RegexFrame();
	}

	public RegexFrame() {
		super("Regex");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 360);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GRAY);
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
		textAreaTarS.setRows(3);
		textAreaTarS.setLineWrap(true);
		JScrollPane sp = new JScrollPane(textAreaTarS);
		sp.setBounds(56, 97, 277, 64);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(sp);
		
		result = new JLabel("");
		result.setVerticalAlignment(SwingConstants.TOP);
		JScrollPane spR = new JScrollPane(result);
		spR.setBounds(35, 175, 317, 44);
		spR.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(spR);
		
		checkBoxOK = new JCheckBox("");
		checkBoxOK.setBounds(340, 37, 126, 23);
		contentPane.add(checkBoxOK);
		
		lblExp1 = new JLabel("Exp1:");
		lblExp1.setBounds(56, 242, 66, 15);
		contentPane.add(lblExp1);
		
		lblExp2 = new JLabel("Exp2:");
		lblExp2.setBounds(56, 271, 66, 15);
		contentPane.add(lblExp2);
		
		textFieldExp1 = new JTextField();
		textFieldExp1.setBounds(98, 240, 124, 19);
		contentPane.add(textFieldExp1);
		textFieldExp1.setColumns(10);
		
		textFieldExp2 = new JTextField();
		textFieldExp2.setBounds(98, 269, 124, 19);
		contentPane.add(textFieldExp2);
		textFieldExp2.setColumns(10);
		
		result_equals = new JLabel("");
		result_equals.setBounds(150, 295, 126, 23);
		contentPane.add(result_equals);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TreeBuilder builder1 = new TreeBuilder();
				builder1.setFormula(textFieldExp1.getText());
				Tree tree1 = builder1.buildTree();
				ThompsonVisitor algo_thompson1=new ThompsonVisitor();
				
				Automate result_automate1=tree1.accept(algo_thompson1);
				result_automate1.synchronization();
				
				if(!result_automate1.testDeterminist())
					result_automate1.determinize();
				
				//result_automate1.minimisation();
				
				TreeBuilder builder2 = new TreeBuilder();
				builder2.setFormula(textFieldExp2.getText());
				Tree tree2 = builder2.buildTree();
				ThompsonVisitor algo_thompson2=new ThompsonVisitor();
				
				Automate result_automate2=tree2.accept(algo_thompson2);
				result_automate2.synchronization();
				
				if(!result_automate2.testDeterminist())
					result_automate2.determinize();
				
				//result_automate2.minimisation();
				
				String equals=result_automate1.equals(result_automate2);

				if(equals.equals("euqals"))
					result_equals.setForeground(Color.GREEN);
				else
					result_equals.setForeground(Color.RED);
				
				result_equals.setText(equals);
				
			}
		});
		btnOk.setBounds(250, 250, 52, 25);
		contentPane.add(btnOk);
		
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
			if(checkBoxOK.isSelected()){
				if(formula==null || !textFieldExp.getText().equals(formula)){
					formula=textFieldExp.getText();
					builder.setFormula(formula);
					tree = builder.buildTree();
					result_automate=tree.accept(algo_thompson);
					
					result_automate.synchronization();
					if(!result_automate.testDeterminist())
						result_automate.determinize();
					//result_automate.minimisation();
				}
				String auto_res=result_automate.wordRecognition(textAreaTarS.getText());
				
				String resutl="("+textAreaTarS.getText()+")-> "+auto_res;
				
				if(auto_res.equals("accepted"))
					result.setForeground(Color.GREEN);
				else
					result.setForeground(Color.RED);
				
				result.setText(resutl);
			}
		}
	}
}
