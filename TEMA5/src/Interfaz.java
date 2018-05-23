import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Interfaz extends JFrame{
	//PANELS
	protected static Add addPanel;
	protected static Delete deletePanel;
	protected static Panel main;
	//POSITION VARS
	protected static Diccionario dic;
	protected static int ancho = 1200, alto = 800, posBuscador = 300, positionYButtons = 600;
	//FROND END
	protected static Image icono, usa, es, alpha;
	protected static ImageIcon lupa,omg;
	protected static Color blueColor = new Color(8,46,147);
	/**TEXT FIELD**/
	protected JTextField searcher;
	
	public static void main(String[] args) {
		Interfaz ventana = new Interfaz();
	}
	
	public Interfaz(){
		setDefaultCloseOperation(Interfaz.EXIT_ON_CLOSE); //esta propiedad se encarga de cerrar la ventana cuando le demos al boton `X`
		setLayout(null);//declaramos una rejilla que nos permitira dibujar objetos por coordenadas
		setResizable(false);//prohibimos la redimension de la ventana
		setBounds(100,100,ancho, alto);
		try {
			icono = ImageIO.read(new File("src/img/alpha.png"));
			usa = ImageIO.read(new File("src/img/USA.png"));
			alpha = ImageIO.read(new File("src/img/alpha.png"));
			es = ImageIO.read(new File("src/img/ES.png"));
			omg = new ImageIcon("src/img/omg.png");
			lupa = new ImageIcon("src/img/lupa.png");
		} catch (IOException e) {
			System.err.println("no tienes imagenes");
		}	
		setIconImage(icono);
		setTitle("ALPHA-TRADUCTOR");//este comando declara el nombre de la ventana. 
		
		/**adding panels**/
		main = new Panel();
		add(main);
		
		addPanel = new Add();
		addPanel.setVisible(false);
		add(addPanel);
		
		deletePanel = new Delete();
		deletePanel.setVisible(false);
		add(deletePanel);
		
		setVisible(true);
	}

	/**subclase de la interfaz **/
	protected class Panel extends JPanel{
		
		/**BUTTONS**/
		protected JButton search, add, delete;
		
		public Panel() {
			setLayout(null);
			setBounds(0,0,1200,800);
			setBackground(Color.WHITE);
			dic = new Diccionario("aa");
			//dic.insertar("hola", "hello");
			
			/**creating text field for search**/
			searcher = new JTextField();
			searcher.setBounds(posBuscador, 350, 600, 80);
			searcher.setFont(new Font("Calibri",Font.ITALIC, 70));
			searcher.addKeyListener(new Raton());
			searcher.setVisible(true);
			add(searcher);
			
			/** STARTING BUTTONS**/
			//lupa
			search = new JButton();
			search.setContentAreaFilled(false);
			search.setBounds(posBuscador +600,350,80,80);
			search.setIcon(lupa);
			search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			search.setBorderPainted(false);
			search.addActionListener(new Raton());
			search.setVisible(true);
			add(search);
			
			//add
			add = new JButton("AÑADIR");
			add.setBounds(100, positionYButtons, 300, 50);
			add.setForeground(blueColor);
			add.setFont(new Font("Calibri",Font.ITALIC, 20));
			add.addActionListener(new Raton());
			add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add.setBackground(Color.white);
			add.setVisible(true);
			add(add);
			//delete
			delete = new JButton("ELIMINAR");
			delete.setBounds(800, positionYButtons, 300, 50);
			delete.setForeground(blueColor);
			delete.setFont(new Font("Calibri",Font.ITALIC, 20));
			delete.addActionListener(new Raton());
			delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			delete.setBackground(Color.white);
			delete.setVisible(true);
			add(delete);
			
			setVisible(true);
		}
		
		/**PAINT METHOD**/
		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			Graphics2D g = (Graphics2D)g2;
			g.setColor(blueColor);
			g.setFont(new Font("Calibri",Font.BOLD,80));
			g.drawString("Traductor  ESPAÑOL - ENGLISH", 95, 100);
			g.setFont(new Font("SWGothe",Font.ITALIC,30));
			g.drawString("Grupo", 20, 750);
			g.drawImage(alpha, 125, 705,50,50,null);
		}
		
		/**MOUSE AND KEYS**/
		protected class Raton extends KeyAdapter implements ActionListener{
			/**MOUSE LISTENERS**/
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(search)) {			
					System.out.println(dic.search(searcher.getText()));
					dic.search(searcher.getText());
				}else if(e.getSource().equals(add)) {
					main.setVisible(false);
					deletePanel.setVisible(false);
					addPanel.setVisible(true);
				}else if(e.getSource().equals(delete)) {
					main.setVisible(false);
					addPanel.setVisible(false);
					deletePanel.setVisible(true);
				}
			}
			/**KEY LISTENERS**/
			public void keyPressed(KeyEvent e) {
				//detecta el enter
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(dic.search(searcher.getText()));
				}
			}
		}
		
		
	}
}
