package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import turing.TuringMachin;

public class TuringGUI extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private static int THREAD_MAP = 2000;
	private boolean stop = false;
	private JTextField bits;
	private JLabel operation;
	private JComboBox<String> comboBox;
	private JSlider slider_speed;
	private TuringPanel tpanel = new TuringPanel();
	private TuringMachin turing = new TuringMachin("add1");
	
	public static void main(String[] args) {
		new TuringGUI();
	}

	public TuringGUI() {
		setTitle("Turing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 330);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		tpanel.setBounds(31, 227, 488, 33);;
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("reverse bits 1-0");
		comboBox.addItem("add 1");
		comboBox.addItem("substract 1");
		comboBox.addItem("multiplication by 2");
		comboBox.setBounds(170, 40, 192, 24);
		getContentPane().add(comboBox);
		
		slider_speed = new JSlider();
		slider_speed.setBounds(128, 171, 258, 44);
        slider_speed.setMinorTickSpacing(2);
        slider_speed.setMajorTickSpacing(10);
        slider_speed.setPaintTicks(true);
        slider_speed.setPaintLabels(true);
        
        event e = new event();
        slider_speed.addChangeListener(e);
        
		getContentPane().add(slider_speed);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String stex_combo = comboBox.getSelectedItem().toString();
				operation.setText(stex_combo);
				switch (stex_combo) {
					case "reverse bits 1-0":
						turing = new TuringMachin("reverse0_1");
						break;
					case"add 1":
						turing = new TuringMachin("add1");
						break;
					case "substract 1":
						turing = new TuringMachin("sub1");
						break;
					case"multiplication by 2":
						turing = new TuringMachin("mult2");
						break;
				}
				if(bits.getText() != null) {
					turing.fillTable(bits.getText());
					tpanel.setRed_index((TuringMachin.TABLE_SIZE / 2) - 1);
					updateGUI();
				}
				THREAD_MAP = 2000;
			}
		});
		btnOk.setBounds(63, 167, 53, 25);
		getContentPane().add(btnOk);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				launchGUI();
			}
		});
		btnStart.setBounds(398, 167, 77, 25);
		getContentPane().add(btnStart);
		
		bits = new JTextField();
		bits.setBounds(210, 140, 120, 19);
		getContentPane().add(bits);
		bits.setColumns(10);
		
		operation = new JLabel("");
		operation.setHorizontalAlignment(SwingConstants.CENTER);
		operation.setBounds(170, 13, 192, 15);
		getContentPane().add(operation);
		
		getContentPane().add(tpanel);
		
		setVisible(true);
		
	}
	
	private void launchGUI(){
		stop = false;
		Thread chronoThread = new Thread(this);
		chronoThread.start();
	}
	
	public void updateGUI() {
		tpanel.setTable(turing.getTable());
		tpanel.setRed_index(turing.getIndex());
		tpanel.updateUI();
		tpanel.repaint();
	}

	@Override
	public void run() {
		while(!stop) {	
			updateGUI();
			stop = turing.turing_proces();
			try {
				Thread.sleep(THREAD_MAP);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public class event implements ChangeListener{
		public void stateChanged(ChangeEvent arg0) {
			int value = slider_speed.getValue();
			if(value != 0)
				if(THREAD_MAP - (value / 2) > 0)
					THREAD_MAP = THREAD_MAP - (value / 2);
		}
		
	}
}
