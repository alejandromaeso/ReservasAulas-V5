package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.List;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IAulas;

public class Aulas implements IAulas {

	private List<Aula> coleccionAulas;

	public Aulas() {

		coleccionAulas = new ArrayList<Aula>();
	}

	public Aulas(IAulas aulas) {
		setAulas(aulas);
	}

	private void setAulas(IAulas aulas) {
		if (aulas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar aulas nulas.");
		}

		coleccionAulas = aulas.getAulas();
	}

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

	public int getNumAulas() {
		return coleccionAulas.size();

	}

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
	 


