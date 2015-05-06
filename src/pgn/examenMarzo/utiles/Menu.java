package pgn.examenMarzo.utiles;



/**
 * Clase utilizada para la gesti&oacute;n de un men&uacute;. Se dedica a:
 * 
 * <li>Mostrar las opciones del men&uacute;
 * 
 * <li>Recoger y devolver las opciones de un men&uacute;
 * 
 * @author Ra&uacute;l Moreno Povedano
 * 
 */
public class Menu {
	String titulo = null;
	String opciones[] = null;
	int numOpciones = 2;

	/**
	 * 
	 * @param titulo
	 *          T&iacute;tulo del men&uacute;
	 * @param opciones
	 *            opciones del men&uacute;
	 */
	public Menu(String titulo, String[] opciones) {
		this.titulo = titulo;
		this.opciones = opciones;
		this.numOpciones = this.opciones.length;
	}

	/**
	 * Gestiona el men&uacute;. Consiste en mostrar las opcines y recoger la opci&oacute;n
	 * seleccionada por el usuario
	 * 
	 * @return opci&oacute;n v&aacute;lida del men&uacute;
	 */
	public int gestionar() {
		mostrar();
		return recogerOpcion();
	}

	/**
	 * Muestra las opciones del men&uacute;
	 */
	private void mostrar() {
		int i = 1;
		System.out.println("**" + titulo);
		for (String elemento : opciones)
			System.out.println("(" + (i++) + ") " + elemento);
	}

	/**
	 * Recoge la opci&oacute;n v&aacute;lida del men&uacute;
	 * 
	 * @return opci&oacute;n v&aacute;lida 
	 */
	private int recogerOpcion() {
		int opcion;
		do {
			opcion = Teclado.leerEntero();
		} while (opcion < 1 || opcion > numOpciones);
		return opcion;
	}

}
