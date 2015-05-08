package interfazGraficaConcesionario;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import pgn.examenMarzo.concesionarioCoches.Concesionario;
import pgn.examenMarzo.concesionarioCoches.Fichero;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSeparator;

import java.awt.Toolkit;

/**
 * Concesionario de coches con GUI
 * 
 * Sobre la versión anterior del Concesionario de coches con excepciones, crea
 * otra versión añadiéndole el entorno gráfico para la comunicación con el
 * usuario. Nos fijaremos en el Notepad para hacerlo lo más parecido posible.
 * Paquetes. Deberás olvidarte del paquete utiles. Necesitas un paquete sólo
 * para el GUI, donde tendrás la clase Principal.java que abrirá la ventana
 * principal. Recuerda que las excepciones han de estar en el paquete que las
 * crean. La página principal tendrá como título el fichero abierto
 * ("Sin título" en caso de no tener asociado ningún fichero). Será al estilo
 * del Notepad. Una barra de menú (JMenuBar) con los siguientes menús
 * (JMenu):Ficheros, Coches, Ayuda. Tendrá las teclas de acceso rápido asociadas
 * a cada elemento del menú, así como mnemotécnicos. Añade también separadores
 * entre elementos del menú. Será al estilo del Notepad. Un diálogo para
 * trabajar con ficheros (JFileChooser). Ha de usar un filtro para visualizar
 * los ficheros con extensión ".obj" Un diálogo para trabajar con Coches
 * (JDialog). El diálogo contendrá: Una caja de texto (JTextField) debidamente
 * etiquetada(JLabel) para las matrículas, Al perder el foco cambiará el color
 * si la matrícula no es válida. Al editar, la matrícula siempre lo hará en
 * negro. Una lista (JList) para el modelo y otra para la marca. Las listas han
 * de estar sincronizadas. Tres radio buttons con los tres colores (tres
 * JRadioButton y un ButtonGroup Selecciona los tres, menú contextual > set
 * ButtonGroup con un borde etiquetado Color Selecciona los tres, menú
 * contextual > Surround with > javax.swing.JPanel border) y Botones (JButton)
 * para Salir o realizar la acción (crear, eliminar, mostrar...). Los mensajes
 * de error (JOptionPane) han de ser lo más conciso posible. Quizás deban
 * basarse en el atributo message de las excepciones. El menú ayuda dispondrá de
 * un Ver ayuda y Acerca de...
 * 
 * @author Raul Moreno Povedano
 *
 */
public class Principal {
	private Alta dialogoAlta;
	private Baja dialogoBaja;
	private JFrame frame;
	private MostrarCoche dialogoMostrarCoche;
	private MostrarConcesionario dialogoMostrarConcesionario;
	private AboutConcesionario dialogoAboutConcesionario;
	private Ayuda dialogoAyuda;
	static MostrarPorColor dialogoMostrarPorColor;
	private File fichero;
	private JFileChooser filechooser;
	/**
	 * Clase est&aacute;tica que crea un concesionario de coches
	 */
	private Concesionario concesionario = new Concesionario();
	private JMenuItem mostrarConcesionario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/rayoMc.png")));
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (comprobarCambios())
					System.exit(0);
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Sin título - Concesionario");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFicheros = new JMenu("Ficheros");
		mnFicheros.setMnemonic('F');
		menuBar.add(mnFicheros);

		JMenuItem Nuevo = new JMenuItem("Nuevo");
		Nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comprobarCambios()) {
					frame.setTitle("Sin título - Concesionario");	
					fichero = null;
					concesionario = new Concesionario();
				}

			}
		});
		Nuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		mnFicheros.add(Nuevo);

		JMenuItem Abrir = new JMenuItem("Abrir...");
		Abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarCambios())
					abrir();

			}
		});
		Abrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_MASK));
		mnFicheros.add(Abrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
				InputEvent.CTRL_MASK));
		mnFicheros.add(mntmGuardar);

		JMenuItem GuardarComo = new JMenuItem("Guardar como...");
		GuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComo();

			}

		});
		mnFicheros.add(GuardarComo);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comprobarCambios())
					System.exit(0);
			}
		});

		JSeparator separator = new JSeparator();
		mnFicheros.add(separator);
		mnFicheros.add(mntmSalir);

		JMenu Coches = new JMenu("Coches");
		Coches.setMnemonic('C');
		menuBar.add(Coches);

		JMenuItem alta = new JMenuItem("Alta");
		alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogoAlta = new Alta(concesionario, frame);
				dialogoAlta.setVisible(true);
			}
		});
		alta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.ALT_MASK));
		Coches.add(alta);

		JMenuItem baja = new JMenuItem("Baja");
		baja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(concesionario.isEmpty())
					JOptionPane.showMessageDialog(
							null,
							"No hay coches en el concesionario", "Mostrar",
							JOptionPane.INFORMATION_MESSAGE);
				else{
					dialogoBaja = new Baja(concesionario, frame);
					dialogoBaja.setVisible(true);
				}
				
			}
		});
		baja.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				InputEvent.ALT_MASK));
		Coches.add(baja);

		mostrarConcesionario = new JMenuItem("Mostrar concesionario");
		mostrarConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
		mostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(concesionario.isEmpty())
					JOptionPane.showMessageDialog(
							null,
							"No hay coches en el concesionario", "Mostrar",
							JOptionPane.INFORMATION_MESSAGE);
				else{
					dialogoMostrarConcesionario = new MostrarConcesionario(
							concesionario, frame);
					dialogoMostrarConcesionario.setVisible(true);
				}
			

			}
		});

		JSeparator separator_1 = new JSeparator();
		Coches.add(separator_1);
		Coches.add(mostrarConcesionario);

		JMenuItem mntmBuscarCoche = new JMenuItem("Buscar coche por Matricula");
		mntmBuscarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(concesionario.isEmpty())
					JOptionPane.showMessageDialog(
							null,
							"No hay coches en el concesionario", "Mostrar",
							JOptionPane.INFORMATION_MESSAGE);
				else{
					dialogoMostrarCoche = new MostrarCoche(concesionario, frame);
				dialogoMostrarCoche.setVisible(true);
				}
				
			}
		});

		JSeparator separator_2 = new JSeparator();
		Coches.add(separator_2);
		mntmBuscarCoche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				InputEvent.ALT_MASK));
		Coches.add(mntmBuscarCoche);

		JMenuItem mntmBuscarCochePor = new JMenuItem("Buscar coche por Color");
		mntmBuscarCochePor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(concesionario.isEmpty())
					JOptionPane.showMessageDialog(
							null,
							"No hay coches en el concesionario", "Mostrar",
							JOptionPane.INFORMATION_MESSAGE);
				else{
					dialogoMostrarPorColor = new MostrarPorColor(concesionario,
						frame);
				dialogoMostrarPorColor.setVisible(true);
				}
				
			}
		});
		mntmBuscarCochePor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.ALT_MASK));
		Coches.add(mntmBuscarCochePor);

		JMenuItem mntmContarCochesConcesionario = new JMenuItem(
				"Contar coches concesionario");
		mntmContarCochesConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						null,
						"Total de coches en el concesionario: "
								+ concesionario.size(), "Añadir",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JSeparator separator_3 = new JSeparator();
		Coches.add(separator_3);
		mntmContarCochesConcesionario.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, InputEvent.ALT_MASK));
		Coches.add(mntmContarCochesConcesionario);

		JMenu Ayuda = new JMenu("Ayuda");
		Ayuda.setMnemonic('A');
		menuBar.add(Ayuda);

		JMenuItem mntmAyuda = new JMenuItem("Ayuda...");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogoAyuda = new Ayuda(frame);
				dialogoAyuda.setVisible(true);
			}
		});
		Ayuda.add(mntmAyuda);

		JMenuItem mntmAboutConcesionario = new JMenuItem("About concesionario");
		mntmAboutConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogoAboutConcesionario = new AboutConcesionario(frame);
				dialogoAboutConcesionario.setVisible(true);
			}
		});
		Ayuda.add(mntmAboutConcesionario);
		filechooser = new JFileChooser();
		filechooser.setFileFilter(new FileNameExtensionFilter("archivos .obj",
				"obj"));
	}

	protected void abrir() {
		int entero = filechooser.showOpenDialog(frame);
		if (entero == JFileChooser.APPROVE_OPTION)
			try {
				concesionario = (Concesionario) Fichero.abrir(filechooser.getSelectedFile());
				fichero = filechooser.getSelectedFile();
				frame.setTitle(fichero + "- Concesionario");
			} catch (ClassNotFoundException | IOException e) {
				JOptionPane.showMessageDialog(frame,
						"No se ha podido abrir el archivo", "Abrir",
						JOptionPane.ERROR_MESSAGE);
			}

	}

	protected boolean sobreescribir(File file2) {

		int entero;
		if (file2.exists()) {
			entero = JOptionPane.showConfirmDialog(frame, "Sobreescribir?",
					"Guardar", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			switch (entero) {
			case JOptionPane.YES_OPTION:
				return true;

			default:
				return false;
			}
		}
		return true;

	}
	private boolean almacenar() {
		try {
			Fichero.guardar(filechooser.getSelectedFile(), concesionario);
			fichero = filechooser.getSelectedFile();
			Fichero.annadirExtension(fichero);
			frame.setTitle(fichero + " - Concesionario");
			concesionario.setModificado(false);
			return true;
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido guardar",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private boolean guardarComo() {
		int opcion = filechooser.showSaveDialog(frame);
		if (opcion == JFileChooser.APPROVE_OPTION)
			if (sobreescribir(filechooser.getSelectedFile()))
				return almacenar();

		return false;
	}

	private boolean comprobarCambios() {
		int entero;
		if (concesionario.isModificado()) {
			entero = JOptionPane
					.showConfirmDialog(
							frame,
							"Los cambios guardados se perderán. Desea guardar los cambios?",
							"Cambios", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
			switch (entero) {
			case JOptionPane.YES_OPTION:
				return guardar();

			case JOptionPane.NO_OPTION:
				return true;
			case JOptionPane.CANCEL_OPTION:
				return false;

			}
		}
		return true;

	}

	private boolean guardar() {
		if (fichero != null)
			return almacenar();
		else
			return guardarComo();
	}
	

}
