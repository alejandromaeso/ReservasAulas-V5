package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.List;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {

	private static final String NOMBRE_FICHERO_AULAS = "datos/aulas.txt";
	private List<Aula> coleccionAulas;

	public Aulas() {

		coleccionAulas = new ArrayList<Aula>();
	}

	public Aulas(IAulas aulas) {
		setAulas(aulas);
	}

	@Override
	public void comenzar() {
		leer();
	}

	private void leer() {
		File fichero = new File(NOMBRE_FICHERO_AULAS);

		try {
			ObjectInputStream objetoLectura = new ObjectInputStream(new FileInputStream(fichero));
			Aula aula = null;
			do {
				aula = (Aula) objetoLectura.readObject();
				insertar(aula);
			} while (aula != null);
			
			objetoLectura.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: No se pudo encontrar la clase a leer.");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se encuentra el fichero de lectura.");
		} catch (EOFException e) {
			System.out.println("El fichero se ha leído correctamente.");
		} catch (IOException e) {
			System.out.println("ERROR: Ha ocurrido un error inesperado en la E/S.");
		} catch (OperationNotSupportedException e) {
			System.out.println("ERROR: Operación no soportada.");
		}

	}

	@Override
	public void terminar() {
		escribir();
	}

	private void escribir() {
		File fichero = new File(NOMBRE_FICHERO_AULAS);

		try {
			ObjectOutputStream objetoEscritura = new ObjectOutputStream(new FileOutputStream(fichero));

			for (Aula aula : coleccionAulas) {
				objetoEscritura.writeObject(aula);

			}
			
			objetoEscritura.close();

			System.out.println("Fichero escrito correctamente.");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero.");
		} catch (IOException e) {
			System.out.println("ERROR: Ha ocurrido un error inesperado en la E/S.");
		}

	}

	private void setAulas(IAulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}

		coleccionAulas = aulas.getAulas();
	}

	@Override
	public List<Aula> getAulas() {

		return copiaProfundaAulas(coleccionAulas);
	}

	private List<Aula> copiaProfundaAulas(List<Aula> listaAulas) {
		List<Aula> copiaAulas = new ArrayList<Aula>();
		Iterator<Aula> iterador = listaAulas.iterator();
		while (iterador.hasNext()) {
			copiaAulas.add(new Aula(iterador.next()));
		}
		Collections.sort(copiaAulas);
		return copiaAulas;
	}

	@Override
	public int getNumAulas() {
		return coleccionAulas.size();

	}

	@Override
	public void insertar(Aula insertarAula) throws OperationNotSupportedException {
		if (insertarAula == null) {
			throw new NullPointerException("ERROR: No se puede insertar un aula nula.");
		}

		if (!coleccionAulas.contains(insertarAula)) {
			coleccionAulas.add(insertarAula);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un aula con ese nombre.");
		}

	}

	@Override
	public Aula buscar(Aula buscarAula) {
		if (buscarAula == null) {
			throw new NullPointerException("ERROR: No se puede buscar un aula nula.");
		}

		Iterator<Aula> iterador = coleccionAulas.iterator();
		while (iterador.hasNext()) {
			Aula aulaBuscada = iterador.next();
			if (buscarAula.equals(aulaBuscada)) {
				return new Aula(aulaBuscada);
			}
		}
		return null;
	}

	@Override
	public void borrar(Aula borrarAula) throws OperationNotSupportedException {
		if (borrarAula == null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula.");
		}

		if (coleccionAulas.contains(borrarAula)) {
			coleccionAulas.remove(borrarAula);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún aula con ese nombre.");
		}

	}

	@Override
	public List<String> representar() {
		List<String> representacion = new ArrayList<String>();
		Iterator<Aula> iterador = coleccionAulas.iterator();
		while (iterador.hasNext()) {
			Aula aulaRepresentada = iterador.next();
			representacion.add(aulaRepresentada.toString());
		}

		return representacion;
	}
}
