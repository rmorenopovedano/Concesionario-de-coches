/**
 * Paquete concesionarioCoches.
 */
package pgn.examenMarzo.concesionarioCoches;

import java.io.Serializable;
/**
 * Clase Pattern.
 */
import java.util.regex.Pattern;

/**
 * Clase coche que define los m&eacute;todos y atributos de un objeto coche.
 * 
 * @author Ra&uacute;l Moreno Povedano
 *
 */
public class Coche implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * String que define la matr&iacute;cula de un coche
	 */
	private String matricula;
	/**
	 * Enumeración que define el color de un coche.
	 */
	private Color color;
	/**
	 * Enumeraci&oacute;n que define el modelo de un coche.
	 */
	private Modelo modelo;
	/**
	 * Atributo final de la clase Pattern que define una expresi&oacute;n
	 * regular para que una matr&iacute;cula sea v&aacute;lida.
	 */
	static final private Pattern patternMatricula = Pattern
			.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	/**
	 * Constructor de la clase Coche para generar un coche con 3
	 * par&aacute;metros.
	 * 
	 * @param matr
	 *            &iacute;cula que le pasamos al constructor
	 * @param color
	 *            que le pasamos al constructor
	 * @param modelo
	 *            que le pasamos al constructor
	 * @throws MatriculaNoValidaException
	 * @throws ColorNoValidoException
	 * @throws ModeloNoValidoException
	 */

	Coche(String matricula, Color color, Modelo modelo)
			throws MatriculaNoValidaException, ColorNoValidoException,
			ModeloNoValidoException {
		super();
		setMatricula(matricula);
		setColor(color);
		setModelo(modelo);
	}

	/**
	 * Constructor de la clase Coche para generar un coche con un solo
	 * par&aacute;metro
	 * 
	 * @param matr
	 *            &iacute;cula que le pasamos al constructor
	 * @throws MatriculaNoValidaException
	 */

	public Coche(String matricula) throws MatriculaNoValidaException {
		setMatricula(matricula);
	}

	/**
	 * M&eacute;todo que instancia un coche v&aacute;lido
	 * 
	 * @param matr
	 *            &iacute;cula que queremos validar
	 * @param color
	 *            que queremos instanciar
	 * @param modelo
	 *            que queremos instanciar
	 * @return un Coche nuevo en caso de ser un coche v&aacute;lido, o null en
	 *         caso de no poder validarse.
	 */

	// static Coche instanciarCoche(String matricula, Color color, Modelo
	// modelo) {
	// if (esValida(matricula) && color != null && modelo != null)
	// return new Coche(matricula, color, modelo);
	// return null;
	// }
	// /**
	// * Método que instancia un coche v&aacute;lido.
	// * @param matr&iacute;cula que queremos validar.
	// * @return un Coche nuevo en caso de ser un coche v&aacute;lido, o null en
	// caso de no poder validarse.
	// */
	//
	// static Coche instanciarCoche(String matricula) {
	// if (esValida(matricula))
	// return new Coche(matricula);
	// return null;
	// }
	/**
	 * Método que valida la m&aacute;tricula de un coche.
	 * 
	 * @param matr
	 *            &iacute;cula que queremos validar
	 * @return Patrón validado si es true, o false si no es v&aacute;lido el
	 *         patr&oacute;n.
	 */

	public static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}

	public Modelo getModelo() {
		return modelo;
	}

	/**
	 * M&eacute;todo set del campo matr&iacute;cula
	 * 
	 * @param matr
	 *            &iacute;cula del coche.
	 * @throws MatriculaNoValidaException
	 */
	private void setMatricula(String matricula)
			throws MatriculaNoValidaException {
		if (esValida(matricula))
			this.matricula = matricula;
		else
			throw new MatriculaNoValidaException("Matrícula no válida");
	}

	/**
	 * M&eacute;todo que obtiene el color de un coche.
	 * 
	 * @return el color del coche.
	 */

	public Color getColor() {
		return color;
	}

	public String getMatricula() {
		return matricula;
	}

	/**
	 * M&eacute;todo set del campo color
	 * 
	 * @param color
	 *            del coche.
	 * @throws ColorNoValidoException
	 */

	private void setColor(Color color) throws ColorNoValidoException {
		if (color != null)
			this.color = color;
		else
			throw new ColorNoValidoException("Color no válido");
	}

	/**
	 * M&eacute;todo set del campo modelo
	 * 
	 * @param modelo
	 *            del coche.
	 * @throws ModeloNoValidoException
	 */

	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo != null)
			this.modelo = modelo;
		else
			throw new ModeloNoValidoException("Modelo no válido");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nMatrícula: " + matricula + "\nColor: " + color + "\nModelo: "
				+ modelo + "\nMarca: " + modelo.getMarca();
	}

}
