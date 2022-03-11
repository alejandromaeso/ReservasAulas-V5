package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.ficheros;

import java.util.List;
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
import java.util.Iterator;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;

public class Profesores implements IProfesores {
	
	private static final String NOMBRE_FICHERO_PROFESORES = "datos/profesores.txt";
	private List<Profesor> coleccionProfesores;

	public Profesores() {

		coleccionProfesores = new ArrayList<>();
	}

	public Profesores(IProfesores profesores) {

		setProfesores(profesores);

	}
	
	@Override
	public void comenzar() {

		leer();
	}

	private void leer() {
		File fichero = new File(NOMBRE_FICHERO_PROFESORES);

		try {
			ObjectInputStream objetoLectura = new ObjectInputStream(new FileInputStream(fichero));
			Profesor profesor = null;
			do {
				profesor = (Profesor) objetoLectura.readObject();
				insertar(profesor);
			} while (profesor != null);
			
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
		File fichero = new File(NOMBRE_FICHERO_PROFESORES);

		try {
			ObjectOutputStream objetoEscritura = new ObjectOutputStream(new FileOutputStream(fichero));

			for (Profesor profesor : coleccionProfesores) {
				objetoEscritura.writeObject(profesor);

			}
			
			objetoEscritura.close();

			System.out.println("Fichero escrito correctamente.");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se ha encontrado el fichero.");
		} catch (IOException e) {
			System.out.println("ERROR: Ha ocurrido un error inesperado en la E/S.");
		}
	}

	private void setProfesores(IProfesores profesores) {

		if (profesores == null) {
			throw new NullPointerException("ERROR: No se pueden copiar profesores nulos.");
		}

		coleccionProfesores = profesores.getProfesores();
	}

	@Override
	public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}

	@Override
	public int getNumProfesores() {

		return coleccionProfesores.size();
	}

	private List<Profesor> copiaProfundaProfesores(List<Profesor> profesores) {
		List<Profesor> copiaProfesores = new ArrayList<Profesor>();
		Iterator<Profesor> iterador = profesores.iterator();

		while (iterador.hasNext()) {
			copiaProfesores.add(new Profesor(iterador.next()));
		}
		Collections.sort(copiaProfesores);
		return copiaProfesores;
	}

	@Override
	public void insertar(Profesor insertarProfesor) throws OperationNotSupportedException {

		if (insertarProfesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (!coleccionProfesores.contains(insertarProfesor)) {
			coleccionProfesores.add(insertarProfesor);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese correo.");
		}

	}

	@Override
	public Profesor buscar(Profesor buscarProfesor) {
		if (buscarProfesor == null) {
			throw new NullPointerException("ERROR: No se puede buscar un profesor nulo.");
		}

		Iterator<Profesor> iterador = coleccionProfesores.iterator();

		while (iterador.hasNext()) {
			Profesor profesorBuscado = iterador.next();
			if (buscarProfesor.equals(profesorBuscado)) {
				return new Profesor(profesorBuscado);
			}
		}
		return null;

	}

	@Override
	public void borrar(Profesor borrarProfesor) throws OperationNotSupportedException {
		if (borrarProfesor == null) {
			throw new NullPointerException("ERROR: No se puede borrar un profesor nulo.");
		}

		if (coleccionProfesores.contains(borrarProfesor)) {
			coleccionProfesores.remove(borrarProfesor);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese correo.");
		}

	}

	@Override
	public List<String> representar() {
		List<String> representacion = new ArrayList<String>();
		Iterator<Profesor> iterador = coleccionProfesores.iterator();
		while (iterador.hasNext()) {
			Profesor profesorRepresentado = iterador.next();
			representacion.add(profesorRepresentado.toString());
		}
		return representacion;
	}

}
