package interfazGraficaConcesionario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class AboutConcesionario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public AboutConcesionario(JFrame frame) {
		super(frame);
		setResizable(false);
		setTitle("Sobre el concesionario");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "About concesionario", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextPane txtpnAuthorRalMoreno = new JTextPane();
			txtpnAuthorRalMoreno.setBackground(new Color(240,240,240));
			txtpnAuthorRalMoreno.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtpnAuthorRalMoreno.setBounds(117, 53, 228, 104);
			txtpnAuthorRalMoreno.setText("Author: Ra\u00FAl Moreno Povedano\r\nVersion: 1.0\r\nCurso: 1\u00BA DAW\r\nAsignatura: Programaci\u00F3n\r\nA\u00F1o: 2015");
			contentPanel.add(txtpnAuthorRalMoreno);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton volverButton = new JButton("VOLVER");
				volverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				volverButton.setActionCommand("Cancel");
				buttonPane.add(volverButton);
			}
		}
	}

}
