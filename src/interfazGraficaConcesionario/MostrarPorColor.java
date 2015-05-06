package interfazGraficaConcesionario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.CocheYaExisteException;
import pgn.examenMarzo.concesionarioCoches.Color;
import pgn.examenMarzo.concesionarioCoches.ColorNoValidoException;
import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;
import pgn.examenMarzo.concesionarioCoches.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MostrarPorColor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton buttonPlata;
	private MostrarConcesionario dialogoMostrarConcesionario;
	private Concesionario concesionario;
	private JRadioButton buttonRojo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public MostrarPorColor(Concesionario concesionario2, JFrame frame) {
		super(frame);
		concesionario=concesionario2;
		setTitle("Elegir Color");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Colores disponibles",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(149, 63, 121, 97);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				buttonPlata = new JRadioButton("PLATA");
				buttonPlata.setBounds(6, 16, 109, 23);
				panel.add(buttonPlata);
				buttonGroup.add(buttonPlata);
			}
			{
				buttonRojo = new JRadioButton("ROJO");
				buttonRojo.setBounds(6, 42, 109, 23);
				panel.add(buttonRojo);
				buttonGroup.add(buttonRojo);
			}
			{
				JRadioButton buttonAzul = new JRadioButton("AZUL");
				buttonAzul.setBounds(6, 67, 109, 23);
				panel.add(buttonAzul);
				buttonGroup.add(buttonAzul);

			}
			buttonPlata.setSelected(true);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton buttonAceptar = new JButton("ACEPTAR");
				buttonAceptar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {						
						Concesionario concesionarioColor=getConcesionarioColor(getColor());
						if(concesionarioColor.size()==0)
							JOptionPane.showMessageDialog(contentPanel,
									"No hay coches de ese color en el concesionario", "Error",
									JOptionPane.ERROR_MESSAGE);
						else{
						dialogoMostrarConcesionario=new MostrarConcesionario(concesionarioColor, null);
						dialogoMostrarConcesionario.setVisible(true);
						}
					}
				});
				buttonAceptar.setActionCommand("OK");
				buttonPane.add(buttonAceptar);
				getRootPane().setDefaultButton(buttonAceptar);
			}
			{
				JButton btnVolver = new JButton("VOLVER");
				btnVolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				buttonPane.add(btnVolver);
			}
		}
	}
	protected Concesionario getConcesionarioColor(Color color) {
		ArrayList<Coche> arraylist=concesionario.getCochesColor(color);
		 Concesionario concesionarioColor=new Concesionario();
		for (Coche coche : arraylist) {
			try {
				concesionarioColor.annadir(coche.getMatricula(), coche.getColor(), coche.getModelo());
			} catch (CocheYaExisteException | MatriculaNoValidaException
					| ColorNoValidoException | ModeloNoValidoException e) {
				
			}
		}
		return concesionarioColor;
	}
	public pgn.examenMarzo.concesionarioCoches.Color getColor() {
		if (buttonPlata.isSelected())
			return pgn.examenMarzo.concesionarioCoches.Color.PLATA;
		else if (buttonRojo.isSelected())
			return pgn.examenMarzo.concesionarioCoches.Color.ROJO;
		else
			return pgn.examenMarzo.concesionarioCoches.Color.AZUL;
	}

}
