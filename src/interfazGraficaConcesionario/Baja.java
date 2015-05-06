package interfazGraficaConcesionario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;

public class Baja extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField campoMatricula;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Concesionario concesionario;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Baja(Concesionario concesionario2, JFrame frame) {
		super(frame);
		concesionario = concesionario2;
		setTitle("Baja");
		setModal(true);
		setBounds(100, 100, 368, 184);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Matr\u00EDcula:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(84, 40, 102, 33);
			contentPanel.add(lblNewLabel);
		}

		campoMatricula = new JTextField();
		campoMatricula.setBounds(169, 47, 120, 20);
		contentPanel.add(campoMatricula);
		campoMatricula.setColumns(10);

		JList list = new JList();
		list.setBounds(84, 119, 1, 1);
		contentPanel.add(list);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton eliminarButton = new JButton("ELIMINAR");
				eliminarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {

							if (concesionario.eliminar(campoMatricula.getText())) {
								JOptionPane.showMessageDialog(null,
										"Coche eliminado con éxito",
										"Eliminar coche",
										JOptionPane.INFORMATION_MESSAGE);
								concesionario.setModificado(true);
							}

							else
								JOptionPane.showMessageDialog(null,
										"Coche no existe", "Error",
										JOptionPane.ERROR_MESSAGE);
						} catch (MatriculaNoValidaException e) {
							JOptionPane.showMessageDialog(getParent(),
									e.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);

						}
					}
				});
				eliminarButton.setActionCommand("OK");
				buttonPane.add(eliminarButton);
				getRootPane().setDefaultButton(eliminarButton);
			}
			{
				JButton CANCELAR = new JButton("CANCELAR");
				CANCELAR.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				CANCELAR.setActionCommand("Cancel");
				buttonPane.add(CANCELAR);
			}
		}
	}
}
