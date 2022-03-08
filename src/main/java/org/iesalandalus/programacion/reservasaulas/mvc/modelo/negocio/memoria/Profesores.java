package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IProfesores;

public class Profesores implements IProfesores {

	private List<Profesor> coleccionProfesores;

	public Profesores() {

		coleccionProfesores = new ArrayList<>();
	}

	public Profesores(IProfesores profesores) {

		setProfesores(profesores);

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
