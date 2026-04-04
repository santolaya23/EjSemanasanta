package SemanaSanta;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.JLabel;

public class Panel2 extends JPanel {

	private static final long serialVersionUID = 1L;
	private int huevosEncontrados=0;
	private int totalHuevos=0;
	JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public Panel2() {
		setLayout(new BorderLayout(0, 0));
		lblNewLabel = new JLabel("Huevos Encontrados:"+huevosEncontrados+"/"+totalHuevos);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.CENTER);
	}

	public void actualizarPanel() {
		
		lblNewLabel.setText("Huevos Encontrados:"+huevosEncontrados+"/"+totalHuevos);
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
}
