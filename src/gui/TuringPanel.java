package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import turing.TuringMachin;

public class TuringPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	Font fonte = new Font("2.TimesRoman ",Font.BOLD,12);
	private static final int W_H_B = 25;
	private char[] table = new char[TuringMachin.TABLE_SIZE];
	private int red_index = TuringMachin.TABLE_SIZE / 2;

	public TuringPanel() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		g.setFont(fonte);
		super.paintComponent(g);
		paintTable(g);
	}
	
	public void paintTable(Graphics g) {
		int k = 0;
		
		for(int i = red_index - 8 ; i < red_index + 10; i++) {
			if(i == red_index) {
				g.setColor(Color.RED);
				g.fillRect(k * (W_H_B + 5), 0, W_H_B, W_H_B);
				g.setColor(Color.BLACK);
				g.drawString(""+table[i], k * (W_H_B + 6), 16);
			}
			else {
				g.drawRect(k * (W_H_B + 5), 0, W_H_B, W_H_B);
				g.drawString(""+table[i], k * (W_H_B + 6), 16);
			}
			k++;
		}
	}
	
	public char[] getTable() {
		return table;
	}
	
	public void setTable(char[] table) {
		this.table = table;
	}
	
	public int getRed_index() {
		return red_index;
	}
	
	public void setRed_index(int red_index) {
		this.red_index = red_index;
	}
}
