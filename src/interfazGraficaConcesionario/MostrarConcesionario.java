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
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import pgn.examenMarzo.concesionarioCoches.Coche;
import pgn.examenMarzo.concesionarioCoches.CocheYaExisteException;
import pgn.examenMarzo.concesionarioCoches.ColorNoValidoException;
import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.Marca;
import pgn.examenMarzo.concesionarioCoches.MatriculaNoValidaException;
import pgn.examenMarzo.concesionarioCoches.Modelo;
import pgn.examenMarzo.concesionarioCoches.ModeloNoValidoException;

public class MostrarConcesionario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField text_matricula;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton plataButton;
	private JRadioButton rojoButton;
	private JRadioButton azulButton;
	private JList listaModelo;
	private JList listaMarca;
	private Concesionario concesionario;
	private int indiceCoche = -1;
	private JButton buttonSiguiente;
	private JButton buttonAnterior;
	private JButton mostrar;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 * 
	 * @param frame
	 */
	public MostrarConcesionario(Concesionario concesionario2, JFrame frame) {
		super(frame);
		concesionario = concesionario2;
		setTitle("Mostrar Concesionario");
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
			lblNewLabel.setBounds(36, 11, 102, 33);
			contentPanel.add(lblNewLabel);
		}

		text_matricula = new JTextField();
		text_matricula.setEditable(false);
		text_matricula.setEnabled(false);
		text_matricula.setBounds(114, 18, 120, 20);
		contentPanel.add(text_matricula);
		text_matricula.setColumns(10);

		JLabel color = new JLabel("Color: ");
		color.setFont(new Font("Tahoma", Font.BOLD, 12));
		color.setBounds(36, 86, 46, 14);
		contentPanel.add(color);

		JLabel marca = new JLabel("Marca:");
		marca.setFont(new Font("Tahoma", Font.BOLD, 12));
		marca.setBounds(36, 175, 46, 14);
		contentPanel.add(marca);

		JLabel modelo = new JLabel("Modelo:");
		modelo.setFont(new Font("Tahoma", Font.BOLD, 12));
		modelo.setBounds(218, 175, 60, 14);
		contentPanel.add(modelo);
		listaMarca = new JList<Modelo>();
		listaMarca.setEnabled(false);
		listaMarca.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				establecerModelos();
			}
		});
		listaMarca.setModel(new AbstractListModel() {
			Marca[] values = Marca.values();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listaMarca.setBounds(84, 154, 102, 62);
		contentPanel.add(listaMarca);

		listaModelo = new JList();
		listaModelo.setEnabled(false);
		establecerModelos();
		listaModelo.setBounds(275, 120, 102, 96);
		contentPanel.add(listaModelo);

		listaMarca.setSelectedIndex(0);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED,
				null, null), "Color", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		panel.setBounds(78, 49, 121, 96);
		contentPanel.add(panel);
		panel.setLayout(null);

		plataButton = new JRadioButton("PLATA");
		plataButton.setEnabled(false);
		plataButton.setBounds(6, 16, 109, 23);
		panel.add(plataButton);
		buttonGroup.add(plataButton);

		rojoButton = new JRadioButton("ROJO");
		rojoButton.setEnabled(false);
		rojoButton.setBounds(6, 40, 109, 23);
		panel.add(rojoButton);
		buttonGroup.add(rojoButton);

		azulButton = new JRadioButton("AZUL");
		azulButton.setEnabled(false);
		azulButton.setBounds(6, 67, 109, 23);
		panel.add(azulButton);
		buttonGroup.add(azulButton);

		plataButton.setSelected(true);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			{
				JButton CANCELAR = new JButton("CANCELAR");
				CANCELAR.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						if(Principal.dialogoMostrarPorColor!=null)
						Principal.dialogoMostrarPorColor.setVisible(false);
					}
				});

				buttonAnterior = new JButton("<ANTERIOR");
				buttonAnterior.setVisible(false);
				buttonAnterior.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarAnterior();
					}

				});
				buttonPane.add(buttonAnterior);

				buttonSiguiente = new JButton("SIGUIENTE>");
				buttonSiguiente.setVisible(false);
				buttonSiguiente.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mostrarSiguiente();
					}
				});
				actualizar();

				mostrar = new JButton("MOSTRAR");
				mostrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (concesionario.size() == 0)
							JOptionPane.showMessageDialog(contentPanel,
									"El concesionario esta vacio", "Error",
									JOptionPane.ERROR_MESSAGE);
						if (concesionario.get(0) != null) {
							buttonAnterior.setVisible(true);
							buttonSiguiente.setVisible(true);
							mostrar.setVisible(false);

						}
					}
				});
				buttonPane.add(mostrar);
				buttonPane.add(buttonSiguiente);
				CANCELAR.setActionCommand("Cancel");
				buttonPane.add(CANCELAR);
			}
		}
	}

	private Object[] getModelo(JList<Marca> listaMarca2) {
		Marca marca = (Marca) listaMarca2.getSelectedValue();
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m : Modelo.values()) {
			if (m.getMarca() == marca)
				modelos.add(m);
		}
		return modelos.toArray();
	}



	private void establecerModelos() {
		listaModelo.setModel(new AbstractListModel() {
			ArrayList<Modelo> values = Modelo
					.getModelosMarca((Marca) listaMarca.getSelectedValue());

			public int getSize() {
				return values.size();
			}

			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
		listaModelo.setSelectedIndex(0);
	}

	void actualizar() {
		if (concesionario.size() == 0) {
			return;
		}
		indiceCoche = 0;
		mostrarCoche(concesionario.get(indiceCoche));
		comprobarBotones();
	}

	private void comprobarBotones() {
		if (concesionario.get(indiceCoche + 1) == null)
			buttonSiguiente.setEnabled(false);
		else
			buttonSiguiente.setEnabled(true);

		if (concesionario.get(indiceCoche - 1) == null)
			buttonAnterior.setEnabled(false);
		else
			buttonAnterior.setEnabled(true);
	}

	private void mostrarSiguiente() {
		mostrarCoche(concesionario.get(++indiceCoche));
		comprobarBotones();
	}

	private void mostrarAnterior() {
		mostrarCoche(concesionario.get(--indiceCoche));
		comprobarBotones();
	}

	private void mostrarCoche(Coche coche) {
		text_matricula.setText(coche.getMatricula());
		switch (coche.getColor()) {
		case PLATA:
			plataButton.setSelected(true);
			break;
		case ROJO:
			rojoButton.setSelected(true);
			break;
		case AZUL:
			azulButton.setSelected(true);
		}
		listaMarca.setSelectedValue(coche.getModelo().getMarca(), false);
		listaModelo.setSelectedValue(coche.getModelo(), false);

	}

}
