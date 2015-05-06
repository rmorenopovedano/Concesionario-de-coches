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

public class Fichero {
	
	public static void guardar(File file,
			Concesionario concesionario2) throws IOException {

//		File file = new File(nombreFichero2);
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file)))) {
			out.writeObject(concesionario2);
		}

	}
	public static Concesionario abrir(File file) throws FileNotFoundException, IOException, ClassNotFoundException{
//		File file = new File(nombreFichero2);
		try (ObjectInputStream in = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			return (Concesionario)in.readObject();
		}

	}
}
