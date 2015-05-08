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

import pgn.examenMarzo.concesionarioCoches.CocheNoExisteException;
import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;

public class Baja extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField campoMatricula;
	private Concesionario concesionario;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Baja(Concesionario concesionario2, JFrame frame) {
		super(frame);
		setResizable(false);
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
		campoMatricula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		campoMatricula.setBounds(169, 47, 120, 20);
		contentPanel.add(campoMatricula);
		campoMatricula.setColumns(10);

		JList<Object> list = new JList<Object>();
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
							if (concesionario.get(campoMatricula.getText()) == null) {
							} else {
								int entero = JOptionPane.showConfirmDialog(
										null,
										"Estás seguro que desea eliminar?",
										"Eliminar",
										JOptionPane.YES_NO_CANCEL_OPTION,
										JOptionPane.QUESTION_MESSAGE);

								switch (entero) {
								case JOptionPane.YES_OPTION:
									concesionario.eliminar(campoMatricula
											.getText());
									JOptionPane.showMessageDialog(null,
											"Coche eliminado con éxito",
											"Eliminar coche",
											JOptionPane.INFORMATION_MESSAGE);
									concesionario.setModificado(true);
									campoMatricula.setText(null);
									return;
									
								}
							}

						} catch (MatriculaNoValidaException e) {
							JOptionPane.showMessageDialog(getParent(),
									e.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);

						} catch (CocheNoExisteException e) {
							JOptionPane.showMessageDialog(null,
									"El coche no existe", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					

					}
				});
				eliminarButton.setActionCommand("OK");
				buttonPane.add(eliminarButton);
				getRootPane().setDefaultButton(eliminarButton);
			}
			{
				JButton cancelarButton = new JButton("CANCELAR");
				cancelarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				cancelarButton.setActionCommand("Cancel");
				buttonPane.add(cancelarButton);
			}
		}
	}
}
