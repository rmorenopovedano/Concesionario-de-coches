/**
 * Paquete concesionarioCoches
 */
package pgn.examenMarzo.concesionarioCoches;

import java.util.ArrayList;

/**
 * Clase que contiene modelos de coches predefinidos.
 * @author Ra&uacute;l Moreno Povedano
 *
 */
public enum Modelo {
/**
 * Modelo serie 1
 */
	SERIE1(Marca.BMW),
/**
 * Modelo serie 2
 */
	SERIE2(Marca.BMW),
/**
 * Modelo serie 3
 */
	SERIE3(Marca.BMW),
/**
 * Modelo serie 5
 */
	SERIE5(Marca.BMW),
/**
 * Modelo C&oacute;rdoba
 */
	CORDOBA(Marca.SEAT),
/**
 * Modelo Ibiza
 */
	IBIZA(Marca.SEAT),
/**
 * Modelo Toledo
 */
	TOLEDO(Marca.SEAT);
	/**
	 * Enumeraci&oacute;n que define la marca de un coche
	 */
	private Marca marca;
	/**
	 * Constructor del modelo de coche
	 * @param marca que corresponde al modelo de coche
	 */

	private Modelo(Marca marca) {
		this.marca = marca;
	}
	/**
	 * M&eacute;todo get del campo Marca
	 * @return marca del coche
	 */

	public Marca getMarca() {
		return marca;
	}
	/**
	 * M&eacute;todo toString
	 */
	public String toString() {
		return name();

	}

	// Para el menú-------------------------------------------------
	/**
	 * M&eacute;todo est&aacute;tico que genera las opciones de un Men&uacute;.
	 * @return Array de cadenas con las opciones del men&uacute;.
	 */
	private static final Modelo[] VALUES = Modelo.values();

	static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[VALUES.length + 1];
		int i = 0;
		for (Modelo modelo : VALUES) {
			opcionesMenu[i++] = modelo.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}
	/**
	 * M&eacute;todo est&aacute;tico que obtiene los valores de la enumeraci&oacute;n modelo
	 * @return valor del modelo.
	 */

	public static Modelo[] getValues() {
		return VALUES;
	}
	
	public static ArrayList<Modelo> getModelosMarca(Marca marca) {
		ArrayList<Modelo> arraylist=new ArrayList<Modelo>();
		for (Modelo modelo : Modelo.values()) {
			if(marca==modelo.getMarca())
				arraylist.add(modelo);
		}
		return arraylist;
	}
	// -------------------------------------------------

}
