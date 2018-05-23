import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Add extends JPanel{
	
	/**BUTTONS**/
	private JButton back, addWord; 
	/**TEXT FIELDS**/
	protected JTextField spanishWord;
	protected JTextField englishWord;
	/**POSITION VARS**/
	private int posXTF = 250;//position in X of textField
	
	public Add() {
		setLayout(null);
		setBounds(0,0,1200,800);
		setBackground(Color.WHITE);
		
		/**ADDING BUTTONS**/
		//back
		back = new JButton("VOLVER");
		back.setBounds(100, Interfaz.positionYButtons, 300, 50);
		back.setForeground(Interfaz.blueColor);
		back.setFont(new Font("Calibri",Font.ITALIC, 20));
		back.addActionListener(new Raton());
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBackground(Color.white);
		back.setVisible(true);
		add(back);
		
		//addWord
		addWord = new JButton("AÑADIR");
		addWord.setBounds(800, Interfaz.positionYButtons, 300, 50);
		addWord.setForeground(Interfaz.blueColor);
		addWord.setFont(new Font("Calibri",Font.ITALIC, 20));
		addWord.addActionListener(null);
		addWord.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addWord.setBackground(Color.white);
		addWord.setVisible(true);
		add(addWord);
		/**ADDING TEXT FIELDS**/
		//spanish word
		spanishWord = new JTextField();
		spanishWord.setBounds(posXTF, 250, 700, 80);
		spanishWord.setFont(new Font("Calibri",Font.ITALIC, 70));
		spanishWord.addKeyListener(new Raton());
		spanishWord.setVisible(true);
		add(spanishWord);
		
		//english word
		englishWord = new JTextField();
		englishWord.setBounds(posXTF, 450, 700, 80);
		englishWord.setFont(new Font("Calibri",Font.ITALIC, 70));
		englishWord.addKeyListener(new Raton());
		englishWord.setVisible(true);
		add(englishWord);
		
		setVisible(true);
	}
	
	/**PAINT METHOD**/
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D)g2;
		g.setColor(Interfaz.blueColor);
		g.setFont(new Font("Calibri",Font.BOLD,80));
		g.drawString("Traductor  ESPAÑOL - ENGLISH", 95, 100);
		g.setFont(new Font("Calibri",Font.BOLD,50));
		g.drawString("Introduzca la palabra en español", posXTF, 220);
		g.drawString("Introduzca la palabra en inglés", posXTF, 420);
		g.setFont(new Font("SWGothe",Font.ITALIC,30));
		g.drawString("Grupo ALFA", 20, 750);
		//Images of the countries
		g.drawImage(Interfaz.es,50,190,150,150,null);
		g.drawImage(Interfaz.usa,50,390,150,150,null);
	}
	/**MOUSE AND KEYS**/
	private class Raton extends KeyAdapter implements ActionListener{
		/**MOUSE LISTENERS**/
		public void actionPerformed(ActionEvent e) {
			 if(e.getSource().equals(back)) {
				Interfaz.main.setVisible(true);
				Interfaz.deletePanel.setVisible(false);
				Interfaz.addPanel.setVisible(false);
			}else if(e.getSource().equals(addWord)) {
				Interfaz.dic.insert(spanishWord.getText(), englishWord.getText());
			}
		}
		/**KEY LISTENERS**/
		public void keyPressed(KeyEvent e) {
			//detecta el enter
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("primo victoria by enter");
			}
		}
	}
}
