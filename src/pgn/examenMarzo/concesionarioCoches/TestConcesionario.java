/**
 * Paquete concesionarioCoches
 */
package pgn.examenMarzo.concesionarioCoches;

/**
 * clase Men&uacute;
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import pgn.examenMarzo.utiles.Menu;
/**
 * Clase Teclado
 */
import pgn.examenMarzo.utiles.Teclado;
/**
 * Enumeracion con el color del coche
 */
import pgn.examenMarzo.concesionarioCoches.Color;
/**
 * Enumeracion con el modelo del coche
 */
import pgn.examenMarzo.concesionarioCoches.Modelo;

/**
 * Crea otra versi�n de Concesionario de coches, ahora almacenar� el
 * concesionario completo en el sistema de ficheros, del que se podr� recuperar
 * en cualquier momento Para ello: A�ade una opci�n Ficheros al men� principal
 * Crea un men� para gestionar los ficheros. Tendr� las opciones t�picas: nuevo,
 * abrir, guardar, guardar como... El concesionario podr� guardarse en un
 * fichero (guardar y guardar como...) El concesionario podr� leerse de un
 * fichero (abrir) Podr� crearse un concesionario nuevo (nuevo) En caso de que
 * se pueda perder informaci�n del concesionario, se le preguntar� al usuario
 * (nuevo, abrir, guardar como...) Se le a�adir� la extensi�n ".obj". Deber�s
 * utilizar la clase File, que es una representaci�n abstracta de los nombres de
 * los ficheros y directorios. Podr�s usar los m�todos: File file = new
 * File(String pathname) file.getPath(); file.exists();
 * 
 * @author Ra&uacute;l Moreno Povedano
 * 
 */
public class TestConcesionario extends Concesionario {
	static Menu menuFicheros = new Menu("**ARCHIVOS**", new String[] {
			"Nuevo...", "Abrir...", "Guardar...", "Guardar como..." });
	/**
	 * Men&uacute; est&aacute;tico que muestra las opciones del men&uacute;
	 */
	static Menu menu = new Menu("Concesionario de coches", new String[] {
			"Alta Coche", "Baja Coche", "Mostrar Coche",
			"Mostrar concesionario", "Contar coches del concesionario",
			"Mostrar coches de un color", "Ficheros", "Salir" });
	/**
	 * Men&uacute; est&aacute;tico para elegir el color del coche
	 */
	private static Menu menuColores = new Menu("Colores de los coches",
			Color.generarOpcionesMenu());
	/**
	 * Men&uacute; est&aacute;tico para elegir el modelo del coche
	 */
	private static Menu menuModelos = new Menu("Modelos de los coches",
			Modelo.generarOpcionesMenu());
	/**
	 * Clase est&aacute;tica que crea un concesionario de coches
	 */
	static Concesionario concesionario = new Concesionario();
	/**
	 * Indica si el concesionario se ha modificado.
	 */
	private static boolean modificado;
	/**
	 * Nombre del fichero abierto.
	 */
	private static String nombreFichero;
	/**
	 * Menu que gestiona la opcion de guardar el fichero.
	 */
	private static Menu menuGuardar = new Menu("***Guardar fichero***",
			new String[] { "Guardar fichero", "No guardar fichero",
					"Crear nuevo sin guardar" });

	/**
	 * M&eacute;todo main
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		do {
			switch (menu.gestionar()) {
			case 1:// "A�adir Coche
				annadirCoche();
				break;
			case 2:// Eliminar Coche
				eliminarCoche();
				break;
			case 3:// Obtener Coche
				getCoche();
				break;
			case 4:// Mostrar lista
				System.out.println(concesionario);
				break;
			case 5:// Contar coches
				System.out.println("N�mero de coches en el concesionario: "
						+ concesionario.size());
				break;
			case 6:// Mostrar coches de un color
				System.out.println(concesionario.getCochesColor(pedirColor()));
				break;
			case 7:// Menu ficheros
				try {
					realizarOpcion(menuFicheros.gestionar());
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:// Salir
				Menu menu = new Menu("Desea guardar el archivo antes de salir?",
						new String[] { "S�", "No" });
				switch (menu.gestionar()) {
				case 1:
					guardar();
					break;
				case 2:
					break;
				}

				System.out.println("Aaaaaaaaaaaaaaaaaaaaadios");
				return;
			}
		} while (true);
	}

	private static void realizarOpcion(int opcion)
			throws FileNotFoundException, IOException {
		switch (opcion) {
		case 1:// nuevo archivo
			nuevo();

			break;
		case 2: // abrir archivo
			try {
				concesionario = (Concesionario) Fichero.abrir(Teclado
						.leerCadena("Qu� fichero quieres abrir?: "));
			} catch (ClassNotFoundException e1) {
				System.out.println(e1.getMessage());
			} catch (IOException e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case 3: // guardar archivo
			guardar();
			break;
		case 4: // guardar como...
			guardarComo();
			break;
		}
		// char caracter;
		// {

		// do {
		// caracter = Teclado
		// .leerCaracter("Desea sobreescribir?(s/n)");
		// } while (caracter != 's' && caracter != 'n');
		// if (caracter == 's')
		// try {
		// Fichero.guardar(Teclado.leerCadena(
		// "Con qu� nombre guardas tu archivo?: "),
		// concesionario);
		// } catch (IOException e1) {
		// System.out.println(e1.getMessage());
		// }
		// else {
		// String fichero = Teclado
		// .leerCadena("nombre del archivo: ");
		// try (ObjectOutputStream out = new ObjectOutputStream(
		// new FileOutputStream(fichero))) {
		// out.writeObject(concesionario);
		//
		// }
		//
		// }
		// }
		// }
		// }
	}

	private static boolean existe(String nombreFichero) {
		File file = new File(nombreFichero);
		return file.exists();
	}

	private static void nuevo() {

		guardarSiModificado();
		concesionario = new Concesionario();
	}

	private static void guardarSiModificado() {
		if (getModificado()) {
			System.out
					.println("Desea guardar los cambios realizados al concesionario?: "
							+ getNombreFichero());
			switch (menuGuardar.gestionar()) {
			case 1: // case ;//s� deseo guardar
				if (getNombreFichero() == null) {
					guardar();
					System.out.println("Guardado correctamente.");
				}
				break;
			case 2:// case ;//no deseo guardar
				break;
			default:// creo el nuevo sin guardar el anterior
				return;

			}

		}
		concesionario = new Concesionario();

	}

	private static boolean getModificado() {

		return modificado;
	}

	private static String getNombreFichero() {
		return nombreFichero;
	}

	/**
	 * M&eacute;todo que obtiene un coche del concesionario
	 */
	private static void getCoche() {
		Coche coche;
		try {
			coche = concesionario.get(Teclado
					.leerCadena("Introduce la matr�cula"));
			System.out.println(coche);
		} catch (MatriculaNoValidaException | CocheNoExisteException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * M&eacute;todo que elimina un coche del concesionario
	 */
	private static void eliminarCoche() {
		try {
			if (concesionario.eliminar(Teclado
					.leerCadena("Introduce la matr�cula"))) {
				System.out.println("Coche eliminado");
				setModificado(true);
			}

			else
				System.out.println("No se ha podido eliminar");
		} catch (MatriculaNoValidaException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Metodo que a&ntilde;ade un coche al concesionario
	 * 
	 * @throws CocheYaExisteException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 * @throws MatriculaNoValidaException
	 */
	private static void annadirCoche() {

		try {
			concesionario.annadir(Teclado.leerCadena("Introduce la matr�cula"),
					pedirColor(), pedirModelo());
			System.out.println("Coche a�adido con �xito");
			setModificado(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	private static void setModificado(boolean b) {
		modificado = b;

	}

	/**
	 * M&eacute;todo para pedir el modelo del coche que buscamos
	 * 
	 * @return null si no existe ese modelo en el concesionario, o un array de
	 *         Modelos que hayamos encontrado.
	 */

	private static Modelo pedirModelo() {
		int opcion = menuModelos.gestionar();
		Modelo[] arrModelos = Modelo.getValues();
		if (opcion == arrModelos.length + 1)
			return null;
		return arrModelos[opcion - 1];
	}

	/**
	 * M&eacute;todo para pedir el color del coche que buscamos
	 * 
	 * @return null si no existe un coche de ese color en el concesionario, o un
	 *         array de Colores que hayamos encontrado.
	 */

	private static Color pedirColor() {
		int opcion = menuColores.gestionar();
		Color[] arrColores = Color.getValues();
		if (opcion == arrColores.length + 1)
			return null;
		return arrColores[opcion - 1];
	}

	static void guardar() {
		if (nombreFichero == null)
			guardarComo();
		else
			almacenar();
	}

	private static void guardarComo() {
		nombreFichero = Teclado.leerCadena("Dime el nombre del fichero: ");
		if (sobreescribir())
			almacenar();
		else
			System.out.println("No se ha guardado el archivo");

	}

	private static void almacenar() {
		try {
			Fichero.guardar(nombreFichero, concesionario);
			setModificado(false);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static boolean sobreescribir() {
		if (existe(nombreFichero)) {
			Menu menu = new Menu("Desea sobreescribir?", new String[] { "S�",
					"No" });
			switch (menu.gestionar()) {
			case 1:

				return true;
			case 2:

				return false;
			}
		}
		return true;
	}
}
