package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;
import java.util.Random;

public class Aula {
	
	private final static float PUNTOS_POR_PUESTO = (float) 0.5;
	private final static int MIN_PUESTOS = 10;
	private final static int MAX_PUESTOS = 50;
	private String nombre;
	private int puestos;
	

	public Aula(String nombre, int puestos) {
		setNombre(nombre);
		setPuestos(puestos);
	}

	public Aula(Aula copiaAula) {
		if(copiaAula == null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}
		setNombre(copiaAula.getNombre());
		setPuestos(copiaAula.getPuestos());
	}

	private void setNombre(String nombre) {
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo.");
		}
		if(nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede estar vacío.");
		}
		this.nombre = nombre;
	}
	
	public int getPuestos() {
		return puestos;
	}
	
	private void setPuestos(int puestos) {
		if(puestos < MIN_PUESTOS) {
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		}
		if(puestos > MAX_PUESTOS) {
			throw new IllegalArgumentException("ERROR: El número de puestos no es correcto.");
		}
		this.puestos = puestos;
	}
	
	public String getNombre() {
		return nombre;
	}

	public float getPuntos() {
		
		return PUNTOS_POR_PUESTO * puestos;
	}
	
	public static Aula getAulaFicticia(String nombreAulaFicticia) {
		
		Random generador = new Random();
		
		return new Aula(nombreAulaFicticia, generador.nextInt(MAX_PUESTOS) + MIN_PUESTOS);
	}	


	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", puestos=" + puestos;
	}

}
