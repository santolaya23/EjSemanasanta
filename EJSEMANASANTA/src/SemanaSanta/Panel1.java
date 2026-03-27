package SemanaSanta;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Panel1 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Panel1() {
		setLayout(new BorderLayout(0, 0));		
		JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL,5,1,1,11);
		JLabel lbl=new JLabel("Esconder Huevos:"+scrollBar.getValue());
		add(lbl,BorderLayout.NORTH);
		add(scrollBar,BorderLayout.CENTER);
		scrollBar.addAdjustmentListener(new AdjustmentListener() {
			
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				// TODO Auto-generated method stub
				System.out.println(e.getValue());
				lbl.setText("Esconder Huevos: "+e.getValue());
			}
		});
		JPanel subPanel=new JPanel(new FlowLayout());
		JLabel lblNewLabel = new JLabel("Dificultad: ");
		subPanel.add(lblNewLabel);	
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Facil (5 vidas)", "Normal (3 vidas)", "Dificil (1 vida)"}));
		subPanel.add(comboBox);
		add(subPanel,BorderLayout.SOUTH);
		
	}

}
