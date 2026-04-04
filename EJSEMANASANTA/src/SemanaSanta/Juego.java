package SemanaSanta;

import java.awt.EventQueue;

public class Juego {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana2 ventana = new Ventana2();
					PanelDeControl panel = new PanelDeControl(ventana);

					ventana.setPanelDeControl(panel);
					panel.setVentana2(ventana);

					ventana.setVisible(true);
					panel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
