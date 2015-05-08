package interfazGraficaConcesionario;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ayuda extends JDialog {

	/**
	 * Launch the application.
	 */


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public Ayuda(JFrame frame) {
		super(frame);
		setResizable(false);
		setTitle("Ayuda");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 226);
		getContentPane().add(scrollPane);
		{
			JEditorPane dtrpnHola = new JEditorPane();
			scrollPane.setViewportView(dtrpnHola);
			dtrpnHola.setEditable(false);
			dtrpnHola.setContentType("text/html");
			dtrpnHola.setText("<h2>Men\u00FA</h2>\r\n<ul>\r\n<li>Ficheros<br/>\r\n<ul><li>Nuevo: Crear un fichero nuevo.</li><li>Abrir: Abrir un fichero existente</li><li>Guardar: Guardar el fichero con el que hemos trabajado en nuestro directorio.</li><li>Guardar como: Guardar el fichero con otro nombre distinto.</li></ul>\r\n</li>\r\n<li>Coches<br/>\r\n<ul><li>Alta</li><li>Baja</li><li>Mostrar concesionario</li><li>Buscar coche por matr\u00EDcula</li><li>Buscar coche por color</li><li>Contar coches del concesionario</li></ul>\r\n</li>\r\n<li>Ayuda<br/>\r\n<ul><li>Ayuda</li><li>Sobre el concesionario</li><ul>\r\n</li>\r\n\t</ul>\r\n\t</body>\r\n</html>");
		}
	}
}
