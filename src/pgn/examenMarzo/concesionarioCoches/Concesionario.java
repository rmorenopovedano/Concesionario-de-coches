/**
 * Paquete concesionarioCoches
 * Hay que hacerla serializable. Luego crear 2 clases static, un flujo de entrada y uno de salida. Fichero.escribir, fichero.leer
 * Crear un main que escribas el concesionario en el sistema de ficheros y lo recuperas. Cuando tengamos esto hecho ense�arlo y luego ver qu� pasa.
 */
package pgn.examenMarzo.concesionarioCoches;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Clase ArrayList
 */
import java.util.ArrayList;

import pgn.examenMarzo.utiles.Teclado;

/*
 * No pueden existir dos coches con la misma matr�cula en el almac�n del concesinario
 * no puede a�adirse un coche al concecionario con alguno de sus atributos inv�lidos. Han de conocerse todas sus caracter�sticas 
 * Ninguno de los valores puede ser por defecto
 */
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
public class Concesionario implements Serializable {
	/**
	 * ArrayList que guarda los coches de un concesionario
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	/**
	 * Atributo final que define el nombre del concesionario
	 */
	private final String nombre = "IES Gran Capit�n";

	/**
	 * M&eacute;todo que a&ntilde;ade un coche al ArrayList
	 * 
	 * @param matricula
	 *            del coche
	 * @param color
	 *            del coche
	 * @param modelo
	 *            del coche
	 * @return false si queremos a&ntilde;adir un coche vac&iacute;o o si el
	 *         coche ya est&aacute; repetido en el concesionario, o sino
	 *         devuelve el coche a&ntilde;adido al ArrayList.
	 * @throws MatriculaNoValidaException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 * @throws CocheYaExisteException
	 */

	void annadir(String matricula, Color color, Modelo modelo)
			throws CocheYaExisteException, MatriculaNoValidaException,
			ColorNoValidoException, ModeloNoValidoException {
		// Coche coche = Coche.instanciarCoche(matricula, color, modelo);
		Coche coche = new Coche(matricula, color, modelo);

		if (coche == null || almacen.contains(coche))
			throw new CocheYaExisteException("El coche ya existe");
		almacen.add(coche);
	}

	/**
	 * M&eacute;todo que elimina un coche del concesionario
	 * 
	 * @param matr
	 *            &iacute;cula del coche
	 * @return el coche eliminado si es un coche v&aacute;lido, o false no
	 *         est&aacute; instanciado correctamente.
	 * @throws MatriculaNoValidaException
	 */

	boolean eliminar(String matricula) throws MatriculaNoValidaException {
		// return almacen.remove(Coche.instanciarCoche(matricula));
		Coche coche = new Coche(matricula);
		return almacen.remove(coche);
	}

	/**
	 * M&eacute;todo que nos dice el tama&ntilde;o del ArrayList.
	 * 
	 * @return el n&uacute;mero de elementos del ArrayList.
	 */

	int size() {
		return almacen.size();
	}

	/**
	 * M&eacute;todo que obtiene un coche seg&uacute;n la matr&iacute;cula
	 * 
	 * @param matr
	 *            &iacute;cula del coche que buscamos
	 * 
	 * @return Coche contenido en el almac&eacute;n. null si no existe
	 * @throws MatriculaNoValidaException
	 *             Si la matr�cula no es v�lida
	 * @throws CocheNoExisteException
	 *             Si el coche no est� en el concesionario.
	 */
	Coche get(String matricula) throws MatriculaNoValidaException,
			CocheNoExisteException {
		// Coche coche = Coche.instanciarCoche(matricula);
		Coche coche = new Coche(matricula);
		int index = almacen.indexOf(coche);
		if (index != -1) {
			return almacen.get(index);
		} else
			throw new CocheNoExisteException(
					"El coche no est� en el concesionario.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	/**
	 * M&eacute;todo que obtiene de una lista los coches de un mismo color.
	 * 
	 * @param color
	 *            que queremos buscar en la lista
	 * @return ArrayList de coches de un mismo color.
	 */

	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if (coche.getColor() == color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

	public void guardar(String nombreArchivo) throws IOException {

		File file = new File(nombreArchivo);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(almacen);
		}

	}

	public void abrir() throws ClassNotFoundException, IOException {
		String nombreArchivo = Teclado.leerCadena("Qu� archivo quieres abrir?");
		File file = new File(nombreArchivo);
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			almacen = (ArrayList<Coche>) in.readObject();
		}

	}

	public void guardarComo() throws FileNotFoundException, IOException,
			FicheroExisteException {

		String nombreArchivo = Teclado
				.leerCadena("Guarda el archivo con el siguiente nombre: ");
		File file = new File(nombreArchivo);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			if (file.exists())
				throw new FicheroExisteException("El fichero ya existe");
			else
				out.writeObject(almacen);
		}

	}

	public void nuevo() throws FileNotFoundException, IOException {
		String nombreArchivo = Teclado.leerCadena("Nombre del archivo: ");
		File file = new File(nombreArchivo);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(almacen);
		}
	}
}
