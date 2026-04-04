package SemanaSanta;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent.KeyBinding;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Ventana2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int huevos[]=new int[16];
	private int huevosTotales=0;
	private PanelDeControl panelDeControl;
	private boolean jugando=false;
	private boolean espacioPulsado=false;
	
	/**
	 * Create the frame.
	 */
	public Ventana2() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 4));
		
		
		JPopupMenu popup = new JPopupMenu();
		JMenuItem ponerBandera = new JMenuItem("Poner Bandera");
		ponerBandera.addActionListener(e -> {
			JButton boton = (JButton) popup.getInvoker();
			if(!boton.getText().equals("Bandera")) {
				boton.setText("Bandera");
				boton.setBackground(Color.YELLOW);
			}else {
				boton.setText(boton.getClientProperty("index").toString());
				boton.setBackground(null);
			}
		});
		popup.add(ponerBandera);
	
		
		for(int i=0;i<16;i++) {
			JButton boton = new JButton((i+1)+"");
			boton.putClientProperty("index", i+1);
			boton.setFocusable(false);
			boton.setEnabled(false);
			contentPane.add(boton);
			boton.addActionListener(e -> {	
				if(huevos[Integer.parseInt(boton.getText())-1]==1) {
					boton.setText("Huevo");
					boton.setBackground(Color.GREEN);
					boton.setEnabled(false);
					panelDeControl.setHuevosEncontrados(panelDeControl.getHuevosEncontrados()+1);
					panelDeControl.actualizarPanel();
					if(panelDeControl.getHuevosEncontrados()>=huevosTotales) {
						hasGanado();
						reiniciarJuego();
					}
				}else if(huevos[Integer.parseInt(boton.getText())-1]==2) {
					boton.setText("Trampa");
					boton.setBackground(Color.RED);
					boton.setEnabled(false);
					panelDeControl.setVidasRestantes(panelDeControl.getVidasRestantes()-1);
					panelDeControl.actualizarPanel();
					if(panelDeControl.getVidasRestantes()<=0) {
						jugando=false;
						mostrarContenido();
						int opcion = JOptionPane.showConfirmDialog(this,"¿Quieres reiniciar?","Confirmación",JOptionPane.YES_NO_OPTION);
						if(opcion==JOptionPane.YES_OPTION) {
							reiniciarJuego();
						}
			
					}
				}else {
					boton.setText("");
					boton.setBackground(Color.LIGHT_GRAY);
					boton.setEnabled(false);
				}
			});
			
			boton.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				      if (e.isPopupTrigger() && boton.isEnabled()) {
				            popup.show(boton, e.getX(), e.getY());
				      }
				    
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					 if (e.isPopupTrigger() && boton.isEnabled()) {
				            popup.show(boton, e.getX(), e.getY());
				     }
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			
			addKeyListener(new KeyListener() {
				

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					    espacioPulsado = false;
					}
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if (e.getKeyCode() == KeyEvent.VK_SPACE && !espacioPulsado) {
					    espacioPulsado = true;
					    radar();
					}
				}
			});
		
			
		}
	}
	
	
	//Metodos
	public void activarBotones() {
		for(int i=0;i<16;i++) {
			JButton boton = (JButton) contentPane.getComponent(i);
			boton.setEnabled(true);
		}
	}
	
	
	public void desactivarBotones() {
		for(int i=0;i<16;i++) {
			JButton boton = (JButton) contentPane.getComponent(i);
			boton.setEnabled(false);
		}
	}
	
	
	public void randomizarHuevos(int huevosEscondidos) {
		Arrays.fill(huevos, 0);
		System.out.println("huevos escondidos: "+huevosEscondidos);
		huevosTotales=huevosEscondidos;
		for(int i=0;i<huevosEscondidos;i++) {
			int posicion=(int)(Math.random()*16);
			if(huevos[posicion]==0) {
				huevos[posicion]=1;
			}else {
				i--;
			}
		}
		int trampas= (huevosEscondidos/2)+1;
		for(int i=0;i<trampas;i++) {
			int posicion=(int)(Math.random()*16);
			if(huevos[posicion]==0) {
				huevos[posicion]=2;
			}else {
			 i--;
			}
			
		}
	}
	
	
	
	public void imprimirHuevos() {
		System.out.println(Arrays.toString(huevos));
	}
	
	
	public void radar() {
		ArrayList<Integer> posicionesHuevos = new ArrayList<>();
		if(panelDeControl.getVidasRestantes()>1 ) {
			for (int i = 0; i < huevos.length; i++) {
				JButton boton = (JButton) contentPane.getComponent(i);
				if (huevos[i] == 1 && boton.isEnabled()) {
				    posicionesHuevos.add(i);
				}
			}
			System.out.println("Posiciones de los huevos: " + posicionesHuevos);
			int randomIndex = (int)(Math.random() * posicionesHuevos.size());
			int posicion = posicionesHuevos.get(randomIndex)+1;
			System.out.println(posicion);
			
			if(Math.random()<0.5) {//Busca en filas
				int fila=(posicion-1)/4+1;
				JOptionPane.showMessageDialog(this, "El radar ha detectado un huevo en la fila "+(fila));
				
			}else {//Busca en columnas
				int columna=(posicion)%4;
				if (columna==0) {
					columna=4;
				}
				JOptionPane.showMessageDialog(this, "El radar ha detectado un huevo en la columna "+(columna));
			}
			panelDeControl.setVidasRestantes(panelDeControl.getVidasRestantes()-1);
			panelDeControl.actualizarPanel();
		}else {
			JOptionPane.showMessageDialog(this, "No puedes usar el radar, te queda 1 vida.");
		}
	}
	
	public void reiniciarJuego() {
		panelDeControl.reiniciarVidas();
		panelDeControl.setHuevosEncontrados(0);
		panelDeControl.actualizarPanel();
		Arrays.fill(huevos, 0);
		for(int i=0;i<16;i++) {
			JButton boton = (JButton) contentPane.getComponent(i);
			int numero = (int) boton.getClientProperty("index");
			boton.setText(numero + "");
			boton.setBackground(null);
		}
		iniciarJuego(huevosTotales);
	}
	
	public void hasGanado() {
		jugando=false;
		desactivarBotones();
		JOptionPane.showMessageDialog(this, "¡Has ganado! Has encontrado todos los huevos.");
	}
	
	public void iniciarJuego(int huevosEscondidos) {
		randomizarHuevos(huevosEscondidos);
		imprimirHuevos();
		activarBotones();
		jugando=true;
	}
	
	public void mostrarContenido() {
		for(int i=0;i<16;i++) {
			JButton boton = (JButton) contentPane.getComponent(i);
			if(huevos[i]==1) {
				boton.setText("Huevo");
				boton.setBackground(Color.GREEN);
			}else if(huevos[i]==2) {
				boton.setText("Trampa");
				boton.setBackground(Color.RED);
			}else {
				boton.setText("");
				boton.setBackground(Color.LIGHT_GRAY);
			}
			boton.setEnabled(false);
		}
	}
	
	
	//Getters y Setters
	public void setPanelDeControl(PanelDeControl panelDeControl) {
	    this.panelDeControl = panelDeControl;
	}
	public boolean isJugando() {
		return jugando;
	}
	public void setJugando(boolean jugando) {
		this.jugando = jugando;
	}
	public int[] getHuevos() {
		return huevos;
	}
	public void setHuevos(int[] huevos) {
		this.huevos = huevos;
	}
}
