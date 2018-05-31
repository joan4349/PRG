import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.sun.javafx.logging.Logger;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.*;
import javazoom.jl.decoder.JavaLayerException;


public class Interfaz extends JFrame{
	
	//VARS
	protected static boolean voiceON = true;
	private final static String VOICE= "kevin16";
	private String descript = "";
	//PANELS
	protected static Add addPanel;
	protected static Delete deletePanel;
	protected static Panel main;
	//POSITION VARS
	protected static Diccionario dic;
	protected static int ancho = 1200, alto = 800, posBuscador = 300, positionYButtons = 600;
	//FROND END
	protected static Image icono, usa, es, alpha,str;
	protected static ImageIcon lupa,omg,ON,OFF;
	protected static Color blueColor = new Color(8,46,147);
	/**TEXT FIELD**/
	protected JTextField searcher;
	protected JLabel trad, tradDesc, title, information;
	protected String traduccion = ">Type some word!";
	/**MENU**/
	protected JComboBox diccionarios;
	
	public static void main(String[] args) {
		Interfaz ventana = new Interfaz();
	}
	
	public Interfaz(){
		setDefaultCloseOperation(Interfaz.EXIT_ON_CLOSE); //esta propiedad se encarga de cerrar la ventana cuando le demos al boton `X`
		setLayout(null);//declaramos una rejilla que nos permitira dibujar objetos por coordenadas
		setResizable(false);//prohibimos la redimension de la ventana
		setBounds(200,100,ancho, alto);
		try {
			icono = ImageIO.read(new File("src/img/alpha.png"));
			usa = ImageIO.read(new File("src/img/USA.png"));
			alpha = ImageIO.read(new File("src/img/alpha.png"));
			es = ImageIO.read(new File("src/img/ES.png"));
			omg = new ImageIcon("src/img/omg.png");
			lupa = new ImageIcon("src/img/lupa.png");
			ON = new ImageIcon("src/img/ON.png");
			OFF= new ImageIcon("src/img/OFF.png");
			str =  ImageIO.read(new File("src/img/str.png"));
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
	
	/**
	 * speaks te current string
	 * @param string to speak
	 * **/
	protected static void hablar(String s) {
		if(voiceON) {
			//VOICE
			VoiceManager vm = VoiceManager.getInstance();
	        Voice voice = vm.getVoice(VOICE);
	        voice.allocate();
	        try {
	        	voice.speak(s);
	        }catch(Exception o) { System.err.println("No funciono");}
		}
	}

	/**subclase de la interfaz **/
	protected class Panel extends JPanel{
		
		/**BUTTONS**/
		protected JButton search, add, delete,voice;
		/**VOICE**/
		protected boolean activeVoice = true, activeInformation = true;
		
		public Panel() {
			setLayout(null);
			setBounds(0,0,1200,800);
			setBackground(Color.WHITE);
			dic = new Diccionario("src/datos/palabras.txt");
			
			/**creating text field for search**/
			searcher = new JTextField();
			searcher.setBounds(posBuscador, 350, 600, 80);
			searcher.setFont(new Font("Calibri",Font.ITALIC, 70));
			searcher.addKeyListener(new Raton());
			searcher.setVisible(true);
			add(searcher);
			
			/** STARTING BUTTONS**/
			//voice
			voice = new JButton();
			voice.setContentAreaFilled(false);
			voice.setBounds(210,350,80,80);
			voice.setIcon(ON);
			voice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			voice.setBorderPainted(false);
			voice.addActionListener(new Raton());
			voice.setVisible(true);
			add(voice);
			//magnifying glass
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
			
			/**adding combo box*/
			diccionarios = new JComboBox();
			diccionarios.addItem("Crear diccionario vacio.");
			diccionarios.addItem("Crear diccionario lleno.");
			diccionarios.setForeground(blueColor);
			diccionarios.setFont(new Font("Calibri",Font.ITALIC, 20));
			diccionarios.setBounds(450,positionYButtons,300,50);
			diccionarios.addActionListener(new Raton());
			diccionarios.setVisible(true);
			add(diccionarios);
			
			/**labels**/
			trad = new JLabel(traduccion);
			trad.setBounds(posBuscador,450, 1000, 50);
			trad.setForeground(blueColor);
			trad.setFont(new Font("Calibri",Font.BOLD,40));
			trad.setVisible(true);
			add(trad);
			
			tradDesc = new JLabel(descript);
			tradDesc.setBounds(posBuscador,510, 1000, 50);
			tradDesc.setForeground(blueColor);
			tradDesc.setFont(new Font("Calibri",Font.BOLD,30));
			tradDesc.setVisible(true);
			add(tradDesc);
			
			information = new JLabel("Pincha en el titulo para ver todas las palabras del diccionario");
			information.setBounds(posBuscador+40,180, 1000, 50);
			information.setForeground(new Color(163,20,190));
			information.setFont(new Font("Calibri",Font.BOLD,20));
			information.setVisible(true);
			add(information);
			
			//title
			title = new JLabel("Traductor  ESPAÑOL - ENGLISH");
			title.setForeground(blueColor);
			title.setFont(new Font("Calibri",Font.BOLD,80));
			title.setBounds(95, 100, 1500, 90);
			title.addMouseListener(new Raton());
			title.setVisible(true);
			add(title);

		
			
		}
		/**SPLIT TRADUCTION
		 * @param String traduction 
		 * @return traducction splitted
		 * **/
		public String splitTraduction(String trad) {
			try {
				String res[] = trad.split(">");
				traduccion = res[0] + " > " + res[1];
			}catch(Exception e) {traduccion = "Word doesn`t exist.";}
			return traduccion;
		}
		
		/**SPLIT DESCRIPTION
		 * @param String traduction 
		 * @return description splitted
		 * **/
		public String splitDescription(String trad) {
			try {
				String res[] = trad.split(">");
				traduccion = res[2] +".";
			}catch(Exception e) {traduccion = "";}
			return traduccion;
		}
		
		/**PAINT METHOD**/
		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			Graphics2D g = (Graphics2D)g2;
			g.setColor(blueColor);
			g.setFont(new Font("Calibri",Font.BOLD,80));
			g.setFont(new Font("SWGothe",Font.ITALIC,30));
			g.drawString("Grupo", 20, 750);
			g.drawImage(alpha, 125, 705,50,50,null);
		}
		
		/**MOUSE AND KEYS**/
		protected class Raton extends KeyAdapter implements ActionListener ,MouseListener{
			
			private ShowString str;
			/**MOUSE LISTENERS**/
			public void actionPerformed(ActionEvent e) {
				//BUTTONS
				if(e.getSource().equals(search)) {			
					
					System.out.println(dic.search(searcher.getText()));
					dic.search(searcher.getText());
					trad.setText(splitTraduction(dic.search(searcher.getText().trim())));
					tradDesc.setText(splitDescription(dic.search(searcher.getText().trim())));
					main.repaint();
			        if(activeVoice) {hablar(dic.search(searcher.getText()));}
			        
				}else if(e.getSource().equals(add)) {
					main.setVisible(false);
					deletePanel.setVisible(false);
					addPanel.setVisible(true);
				}else if(e.getSource().equals(delete)) {
					main.setVisible(false);
					addPanel.setVisible(false);
					deletePanel.setVisible(true);
				}else if(e.getSource().equals(voice)) {
					if(activeVoice) {
						activeVoice = false;
						voice.setIcon(OFF);
						voice.repaint();
						System.out.println("OFF");
					}else {
						activeVoice = true;
						System.out.println("ON");
						voice.setIcon(ON);
						voice.repaint();
					}
				}else if(diccionarios.getSelectedIndex() == 0) {
					dic = new Diccionario();
					System.out.println("Diccionario vacio");
				}else if(diccionarios.getSelectedIndex() == 1) {
					dic = new Diccionario("src/datos/palabras.txt");
					System.out.println("Diccionario lleno");
				}
			}
			/**KEY LISTENERS**/				
			//detects the enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(dic.search(searcher.getText().trim()));
					trad.setText(splitTraduction(dic.search(searcher.getText().trim())));
					tradDesc.setText(splitDescription(dic.search(searcher.getText().trim())));
					main.repaint();
					if(activeVoice) {hablar(dic.search(searcher.getText()));}
				}
			}
			//MOUSE LISTENERS
			public void mouseClicked(MouseEvent e) {				
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("hooooola");
				if(activeInformation) {
					str = new ShowString();
					str.setVisible(true);
					activeInformation = false;
				}else {
					str.setVisible(false);
					activeInformation = true;
				}
			}
			public void mouseReleased(MouseEvent e) {
				System.out.println("adios");
				//str.setVisible(false);
				main.repaint();
			}
			public void mouseEntered(MouseEvent e) {
				information.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {
				information.setVisible(false);
			}
			
			protected class ShowString extends JFrame{
				public ShowString() {
					setLayout(null);
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					setResizable(false);
					setBounds(300,300,1000,500);
					setTitle("Dictionary.");
					setIconImage(Interfaz.icono);
					PanToString p = new PanToString();
					JScrollPane scrollBar = new JScrollPane(p,
				            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					scrollBar.setSize(980,500);
					main.repaint();
					add(scrollBar);
			        setVisible(true);
				}
				private class PanToString extends JPanel{
					public PanToString() {
						setLayout(null);
						setBounds(0,0,1000,200);
						Scrollbar s = new Scrollbar(1);
						add(s);
						setBackground(Color.white);
						JTextArea text= new JTextArea();
						text.setEditable(false);
						text.setBounds(0,0,400,5000);
						text.setFont(new Font("Consol",Font.ITALIC,20));
						text.setText(dic.toString());
						setPreferredSize(new Dimension(1000,5000));
						text.setVisible(true);
						add(text);
					}
					/**PAINT METHOD**/
					public void paintComponent(Graphics g2) {
						super.paintComponent(g2);
						Graphics2D g = (Graphics2D)g2;
						g.drawImage(Interfaz.str, 400, 0,600,500,null);
					}
				}
			}
		}
	}
}
