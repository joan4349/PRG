import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Delete extends JPanel{

	/**BUTTONS**/
	private JButton back, delete;
	/**TEXT FIELD**/
	protected JTextField toDelete;
	
	public Delete() {
		setLayout(null);
		setBounds(0,0,1200,800);
		setBackground(Color.WHITE);
		setVisible(true);
		
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
		delete = new JButton("ELIMINAR");
		delete.setBounds(800, Interfaz.positionYButtons, 300, 50);
		delete.setForeground(Interfaz.blueColor);
		delete.setFont(new Font("Calibri",Font.ITALIC, 20));
		delete.addActionListener(new Raton());
		delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		delete.setBackground(Color.white);
		delete.setVisible(true);
		add(delete);
		
		/**ADDING TEXT FIELD**/
		toDelete = new JTextField();
		toDelete.setBounds(250, 350, 700, 80);
		toDelete.setFont(new Font("Calibri",Font.ITALIC, 70));
		toDelete.addKeyListener(new Raton());
		toDelete.setVisible(true);
		add(toDelete);
	}
	
	/**PAINT METHOD**/
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D)g2;
		
		g.setColor(Interfaz.blueColor);
		g.setFont(new Font("Calibri",Font.BOLD,80));
		g.drawString("Traductor  ESPAÑOL - ENGLISH", 95, 100);
		g.setFont(new Font("Calibri",Font.BOLD,50));
		g.drawString("Escriba la palabra que desea eliminar", 230, 300);
		g.setFont(new Font("SWGothe",Font.ITALIC,30));
		g.drawString("Grupo ALFA", 20, 750);
	}
	/**MOUSE AND KEYS**/
	private class Raton extends KeyAdapter implements ActionListener{
		/**MOUSE LISTENERS**/
		public void actionPerformed(ActionEvent e) {
			 if(e.getSource().equals(back)) {
				Interfaz.deletePanel.setVisible(false);
				Interfaz.addPanel.setVisible(false);
				Interfaz.main.setVisible(true);
			}else if(e.getSource().equals(delete)) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que \n desea eliminar '" +toDelete.getText()+ " '?",
						"Atención!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, Interfaz.omg);
				if(resp ==0) {
					Interfaz.dic.remove(toDelete.getText());//if yes, so we remove it
				}else {System.out.print("La palabra " + toDelete.getText() + " no existe.");}
			}
		}
		/**KEY LISTENERS**/
		public void keyPressed(KeyEvent e) {
			//detecta el enter
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que \n desea eliminar '" +toDelete.getText()+ " '?",
						"Atención!", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, Interfaz.omg);
				if(resp ==0 ) {//&& Interfaz.dic.wordExists(toDelete.getText())
					Interfaz.dic.remove(toDelete.getText());//if yes, so we remove it
				}else {System.out.print("La palabra " + toDelete.getText() + " no existe.");}
			}
		}
	}
}
