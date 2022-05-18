package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Reserva implements Comparable<Reserva>, Serializable {

	private static final long serialVersionUID = 8190187186806831322L;
	
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}
		if (aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}

		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva copiaReserva) {
		if (copiaReserva == null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		}
		setProfesor(copiaReserva.getProfesor());
		setAula(copiaReserva.getAula());
		setPermanencia(copiaReserva.getPermanencia());
	}

	private void setProfesor(Profesor profesor) {
		this.profesor = new Profesor(profesor);
	}

	public Profesor getProfesor() {
		return new Profesor(profesor);
	}

	private void setAula(Aula aula) {
		this.aula = new Aula(aula);
	}

	public Aula getAula() {
		return new Aula(aula);
	}

	private void setPermanencia(Permanencia permanencia) {

		if (permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		if (permanencia instanceof PermanenciaPorTramo) {
			this.permanencia = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
		} else if (permanencia instanceof PermanenciaPorHora) {
			this.permanencia = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
		}
	}

	public Permanencia getPermanencia() {

		return permanencia;
	}

	public static Reserva getReservaFicticia(Aula aulaFicticia, Permanencia permanenciaFicticia) {

		return new Reserva(Profesor.getProfesorFicticio("a@a.es"), aulaFicticia, permanenciaFicticia);
	}

	public float getPuntos() {
		return permanencia.getPuntos() + aula.getPuntos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(aula, permanencia, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia);
	}

	/*@Override
	public String toString() {
		return String.format("%s, %s, %s, puntos=%.1f", profesor, aula, permanencia, getPuntos());
	}*/
	
	@Override
	public String toString() {
		return profesor +", "+ aula +", "+ permanencia +", ";
	}


	@Override
	public int compareTo(Reserva o) {
		int comparadorAula = getAula().getNombre().compareTo(o.getAula().getNombre());

		if (comparadorAula == 0) {

			int comparadorFecha = getPermanencia().getDia().compareTo(o.getPermanencia().getDia());
			if (comparadorFecha == 0) {
				if (getPermanencia() instanceof PermanenciaPorTramo
						&& o.getPermanencia() instanceof PermanenciaPorTramo) {
					if (((PermanenciaPorTramo) getPermanencia()).getTramo() == Tramo.MANANA
							&& ((PermanenciaPorTramo) o.getPermanencia()).getTramo() == Tramo.TARDE) {
						return -1;
					} else if (((PermanenciaPorTramo) getPermanencia()).getTramo() == Tramo.TARDE
							&& ((PermanenciaPorTramo) o.getPermanencia()).getTramo() == Tramo.MANANA) {
						return 1;
					} else {
						return 0;
					}
				} else {
					return ((PermanenciaPorHora) getPermanencia()).getHora()
							.compareTo(((PermanenciaPorHora) o.getPermanencia()).getHora());
				}
			}
			return comparadorFecha;

		}
		return comparadorAula;
	}
}
