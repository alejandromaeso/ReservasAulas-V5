package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
	
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;
	
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia){
		if(profesor == null) {
			throw new NullPointerException("ERROR: La reserva debe estar a nombre de un profesor.");
		}
		if(aula == null) {
			throw new NullPointerException("ERROR: La reserva debe ser para un aula concreta.");
		}
		if(permanencia == null) {
			throw new NullPointerException("ERROR: La reserva se debe hacer para una permanencia concreta.");
		}
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}

	public Reserva(Reserva copiaReserva){
		if(copiaReserva == null) {
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
		this.permanencia = new Permanencia(permanencia);
	}

	public Permanencia getPermanencia() {
		return new Permanencia(permanencia);
	}
	
	public static Reserva getReservaFicticia(Aula aulaFicticia, Permanencia permanenciaFicticia) {
		
		return new Reserva(Profesor.getProfesorFicticio("a@a.es"), aulaFicticia, permanenciaFicticia);
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

	@Override
	public String toString() {
		return "Profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia;
	}
	
	

}
