/**
 * Paquete concesionarioCoches
 */
package pgn.examenMarzo.concesionarioCoches;

/**
 * Clase ArrayList
 */
import java.util.ArrayList;

/*
 * No pueden existir dos coches con la misma matr�cula en el almac�n del concesinario
 * no puede a�adirse un coche al concecionario con alguno de sus atributos inv�lidos. Han de conocerse todas sus caracter�sticas 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Clase concesionario donde van a gestionarse las acciones sobre los coches
 * 
 * @author Ra&uacute;l Moreno Povedano
 * 
 */
public class Concesionario {
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

	void annadir(String matricula, Color color, Modelo modelo) throws MatriculaNoValidaException, ColorNoValidoException, ModeloNoValidoException, CocheYaExisteException
			 {
	//	Coche coche = Coche.instanciarCoche(matricula, color, modelo);
		Coche coche=new Coche(matricula, color, modelo);

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
//		return almacen.remove(Coche.instanciarCoche(matricula));
		Coche coche=new Coche(matricula);
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

}
