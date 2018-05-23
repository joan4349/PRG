import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class StackIntLinked extends JFrame{
	
	protected int posX = (int)MouseInfo.getPointerInfo().getLocation().getX();
    protected int posY = (int)MouseInfo.getPointerInfo().getLocation().getY();
    
	public StackIntLinked() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(0,0,1000,800);
		setResizable(false);
		setBackground(Color.BLACK);
		add(new JLabel("empecemos"));
		setTitle("Vlady paint");
		panel pan = new panel(colores.color, colores.pincel);
		hilo h = new hilo();
        //h.start();
        Raton r = new Raton();
        addMouseListener(r);
		add(pan);
		setVisible(true);
		
	}
	
	public static void main(String[] arg) 
	{
		StackIntLinked ventana = new StackIntLinked();
		opc o= new opc();
		
	}
	
		private class panel extends JPanel{
			
			protected Toolkit t = Toolkit.getDefaultToolkit();
			int posX = (int)MouseInfo.getPointerInfo().getLocation().getX();
	        int posY = (int)MouseInfo.getPointerInfo().getLocation().getY();
	        Color c;
			int diaPincel;
			public void paintComponent(Graphics g1) {
				Graphics2D g = (Graphics2D)g1;
				g.setColor(c);
		        g.fillOval(posX-20, posY-40, diaPincel, diaPincel);
			}
			public panel(Color col, int pincel) {
				c = col;
				diaPincel = pincel;
				setLayout(null);
				setBounds(0,0,1000,800);
				setResizable(false);
				setBackground(Color.BLACK);
				setOpaque(true);
				setVisible(true);
			}
			
		}
		private class hilo extends Thread{
			public hilo() {}
			public void run() {
				try{
					while(true) {
						//Thread.sleep(1);
						panel pan = new panel(colores.color, colores.pincel);
						add(pan);
					    //repaint();
						}
				}catch(Exception e) {}
			}
		}
		
		private class Raton extends MouseAdapter{
			hilo h;
			public void mousePressed(MouseEvent e) {
				h= new hilo();
				h.start();
			}
			
			public void mouseReleased(MouseEvent e) {
				h.stop();
			}
			
        }
}
class colores{
	protected static Color verde = new Color(22,164,35);
    public static Color color = verde;
    public static Color rojo = new Color(223,38,38);
    protected static Color azull = new Color(22,55,100);
    protected static int pincel = 0;
    protected static int pincel10 = 10;
    protected static int pincel30 = 30;
    protected static int pincel40 = 40;
}
class opc extends JFrame{
	
	protected JButton azul,verd,rojo,pincel10,pince30,pincel40;
	
	public opc() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(1000,0,250,800);
		setResizable(false);
		//setBackground(Color.BLACK);
		ve v = new ve();
		add(v);
		setTitle("OPC");
		setVisible(true);
	}
	protected class ve extends JPanel{
	    
		public ve() {
			setLayout(null);
			setBounds(0,0,250,800);
			setResizable(false);
			setBackground(Color.BLACK);
			setTitle("paint");
			azul = new JButton("BLUE");
			azul.setBounds(10,10,225,50);
			azul.addActionListener(new Botones2());
			azul.setBackground(colores.azull);
			azul.setForeground(Color.white);
			azul.setVisible(true);
			add(azul);
			
			verd = new JButton("GREEN");
			verd.setBounds(10,70,225,50);
			verd.addActionListener(new Botones2());
			verd.setBackground(colores.verde);
			verd.setForeground(Color.white);
			verd.setVisible(true);
			add(verd);
			
			rojo = new JButton("RED");
			rojo.setBounds(10,130,225,50);
			rojo.addActionListener(new Botones2());
			rojo.setBackground(colores.rojo);
			rojo.setForeground(Color.white);
			rojo.setVisible(true);
			add(rojo);
			
			pincel10 = new JButton("Pincel 10");
			pincel10.setBounds(10,800-220,225,50);
			pincel10.addActionListener(new Botones2());
			pincel10.setBackground(Color.PINK);
			pincel10.setForeground(Color.white);
			pincel10.setVisible(true);
			add(pincel10);
			
			pince30 = new JButton("Pincel 30");
			pince30.setBounds(10,800-160,225,50);
			pince30.addActionListener(new Botones2());
			pince30.setBackground(Color.PINK);
			pince30.setForeground(Color.white);
			pince30.setVisible(true);
			add(pince30);
			
			pincel40 = new JButton("Pincel 40");
			pincel40.setBounds(10,800-100,225,50);
			pincel40.addActionListener(new Botones2());
			pincel40.setBackground(Color.PINK);
			pincel40.setForeground(Color.white);
			pincel40.setVisible(true);
			add(pincel40);

			setVisible(true);
		}
		private class Botones2 extends AbstractAction{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(azul)) {
					colores.color = colores.azull;
					System.out.println("Azul");
				}else if(e.getSource().equals(verd)) {
					colores.color = colores.verde;
					System.out.println("Verde");
				}else if(e.getSource().equals(rojo)) {
					colores.color = colores.rojo;
					System.out.println("Rojo");
				}if(e.getSource().equals(pincel10)) {
					System.out.println("Pincel 10");
					colores.pincel = colores.pincel10;
				}else if(e.getSource().equals(pince30)) {
					System.out.println("Pincel 20");
					colores.pincel = colores.pincel30;
				}else if(e.getSource().equals(pincel40)) {
					System.out.println("Pincel 40");
					colores.pincel = colores.pincel40;
				}
			}
		}
	}
}
