package SemanaSanta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class PanelDeControl extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int huevosEncontrados=0;
	private int totalHuevos=0;
	private int vidasRestantes=0;
	private Ventana2 ventana2;
	private Panel1 panel1;
	private Panel2 panel2;
	private Panel3 panel3;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public PanelDeControl(Ventana2 ventana2) {
		setTitle("Panel de Control");
		setSize(300, 400);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Juego");
		JMenuItem menuItem1 = new JMenuItem("Cambiar Nombre");
		JMenuItem menuItem2 = new JMenuItem("Rendirse y Salir");
		
		//Menu
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		
		menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem1.addActionListener(e -> {
			setTitle(JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre del jugador:"));
		});
		
		
		menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuItem2.addActionListener(e -> {
		    int opcion = JOptionPane.showConfirmDialog(this,"¿Estás seguro de que quieres rendirte y salir?","Confirmar Rendición", JOptionPane.YES_NO_OPTION);
		    if (opcion == JOptionPane.YES_OPTION) {
		        dispose();
		        ventana2.dispose();
		    }
		});
	
		//Panel de Control
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		panel1=new Panel1();
		panel2=new Panel2();
		panel3=new Panel3();
		
		
		JButton btnNewButton = new JButton("Generar Tablero");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				totalHuevos=panel1.getHuevosEscondidos();
				panel2.setTotalHuevos(totalHuevos);
				panel2.actualizarPanel();
				vidasRestantes=panel1.getVidasRestantes();
				panel3.setVidasRestantes(vidasRestantes);
				panel3.actualizarPanel();
				/*
				for(int i=0;i<ventana2.getHuevos().length;i++) {
					int[] huevos=ventana2.getHuevos();
					Arrays.fill(huevos, 0);
					ventana2.setHuevos(huevos);
				}
				*/
				if (ventana2.isJugando()==true) {
					ventana2.reiniciarJuego();
				}
				
				if (ventana2 != null) {
			        ventana2.randomizarHuevos(panel1.getHuevosEscondidos());
			        ventana2.activarBotones();
			        ventana2.imprimirHuevos();
			    }
			    
				ventana2.setJugando(true);
			}
		});
		

		contentPane.add(panel1);
		contentPane.add(btnNewButton);
		contentPane.add(panel2);
		contentPane.add(panel3);
	
	}
	public void actualizarPanel() {
		panel2.setHuevosEncontrados(huevosEncontrados);
		panel2.actualizarPanel();
		panel3.setVidasRestantes(vidasRestantes);
		panel3.actualizarPanel();
	}
	public void reiniciarVidas() {
		vidasRestantes=panel1.getVidasRestantes();
		panel3.setVidasRestantes(vidasRestantes);
		actualizarPanel();
	}
	
	//Getters y Setters
	public int getHuevosEncontrados() {
		return huevosEncontrados;
	}
	public void setHuevosEncontrados(int huevosEncontrados) {
		this.huevosEncontrados = huevosEncontrados;
	}
	public int getTotalHuevos() {
		return totalHuevos;
	}
	public void setTotalHuevos(int totalHuevos) {
		this.totalHuevos = totalHuevos;
	}
	public int getVidasRestantes() {
		return vidasRestantes;
	}
	public void setVidasRestantes(int vidasRestantes) {
		this.vidasRestantes = vidasRestantes;
	}
	public Ventana2 getVentana2() {
		return ventana2;
	}
	public void setVentana2(Ventana2 ventana2) {
		this.ventana2 = ventana2;
	}

}
