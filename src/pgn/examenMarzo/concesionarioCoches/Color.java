/**
 * Paquete concesionarioCoches
 */
package pgn.examenMarzo.concesionarioCoches;

//import pgn.examenMarzo.utiles.Menu;

/**
 * Enumeración que contiene los colores disponibles
 * @author Raul Moreno Povedano
 * 
 */
public enum Color {
	/**
	 * Color plata
	 */
	PLATA, 
	/**
	 * Color rojo
	 */
	ROJO, 
	/**
	 * Color azul
	 */
	AZUL;
/**
 * Array de los valores de la enumeraci&oacute;n color
 */
	private static final Color[] VALUES = Color.values();
/**
 * M&eacute;todo est&aacute;tico que genera las opciones de un Men&uacute;.
 * @return Array de cadenas con las opciones del men&uacute;.
 */
	public static String[] generarOpcionesMenu() {
		String[] opcionesMenu = new String[getValues().length + 1];
		int i = 0;
		for (Color color : getValues()) {
			opcionesMenu[i++] = color.name();
		}
		opcionesMenu[i] = "Salir";
		return opcionesMenu;
	}
	/**
	 * M&eacute;todo est&aacute;tico que obtiene los valores de la enumeraci&oacute;n color
	 * @return valor del color.
	 */

	public static Color[] getValues() {
		return VALUES;
	}

}
