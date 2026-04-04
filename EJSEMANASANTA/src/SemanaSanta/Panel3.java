package SemanaSanta;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Panel3 extends JPanel {

	private static final long serialVersionUID = 1L;
	private int vidasRestantes=0;
	JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public Panel3() {
		setLayout(new BorderLayout(0, 0));
		lblNewLabel = new JLabel("Vidas Restantes:"+vidasRestantes);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.CENTER);
	}
	
	public void actualizarPanel() {
		lblNewLabel.setText("Vidas Restantes: "+vidasRestantes);
	}
	
	//Getters y Setters
	public int getVidasRestantes() {
		return vidasRestantes;
	}
	public void setVidasRestantes(int vidasRestantes) {
		this.vidasRestantes = vidasRestantes;
	}
}
