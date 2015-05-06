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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.CocheNoExisteException;
import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;
import pgn.examenMarzo.utiles.Teclado;

import javax.swing.JTextArea;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Component;

public class MostrarCoche extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static Concesionario concesionario;
	private JButton mostrar;
	private JTextField campoMatricula;
	private JTextArea area;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param frame
	 * @param concesionario2
	 */
	public MostrarCoche(Concesionario concesionario2, JFrame frame) {
		super(frame);
		concesionario = concesionario2;
		setTitle("Mostrar Coche por Matr\u00EDcula");
		setModal(true);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setToolTipText("");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Matr\u00EDcula:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblNewLabel.setBounds(105, 37, 102, 33);
			contentPanel.add(lblNewLabel);
		}

		JList list = new JList();
		list.setBounds(84, 119, 1, 1);
		contentPanel.add(list);

		campoMatricula = new JTextField();
		campoMatricula.setBounds(180, 44, 102, 20);
		contentPanel.add(campoMatricula);
		campoMatricula.setColumns(10);
		{
			area = new JTextArea();
			area.setBounds(84, 83, 276, 115);
			contentPanel.add(area);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				mostrar = new JButton("MOSTRAR");
				mostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Coche coche;
						try {
							coche = concesionario.get(campoMatricula.getText());
							area.setText(coche.toString());

						} catch (MatriculaNoValidaException
								| CocheNoExisteException e1) {
							JOptionPane.showMessageDialog(null,
									e1.getMessage(), "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				});
				mostrar.setActionCommand("OK");
				buttonPane.add(mostrar);
				getRootPane().setDefaultButton(mostrar);
			}
			{
				JButton cancelar = new JButton("VOLVER");
				cancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				cancelar.setActionCommand("Cancel");
				buttonPane.add(cancelar);
			}
		}
	}

	Color getColores() {
		ArrayList<Color> color = new ArrayList<Color>();
		for (Color color2 : color) {
			return color2;
		}
		return null;

	}
}
