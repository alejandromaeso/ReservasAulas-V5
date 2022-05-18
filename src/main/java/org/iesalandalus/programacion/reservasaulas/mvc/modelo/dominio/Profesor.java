package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Profesor implements Comparable<Profesor>, Serializable {

	private static final long serialVersionUID = -6402178995738751035L;
	
	private final String ER_TELEFONO = "^(6|9){1}(1|2|3|4|5|6|7|8|9|0){8}$";
	private final String ER_CORREO = "^.{1,}@.{1,}\\.[0-9a-zA-Z]{1,}$";

	private String nombre;
	private String correo;
	private String telefono;

	public Profesor(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
	}

	public Profesor(String nombre, String correo, String telefono) {
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	}

	public Profesor(Profesor copiaProfesor) {
		if (copiaProfesor == null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}
		setNombre(copiaProfesor.getNombre());
		setCorreo(copiaProfesor.getCorreo());
		setTelefono(copiaProfesor.getTelefono());
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.isBlank()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		} else {
			this.nombre = formateaNombre(nombre);
		}	
	}

	//Clase formatea nombre
	
	private String formateaNombre(String nombre) {
		nombre=nombre.replaceAll("\\s{2,}"," ").trim();
		String[] palabras = nombre.split(" ");
		String nuevoNombre = "";
		
		for(int i=0; i<= palabras.length-1; i++) {
			palabras[i] = palabras[i].substring(0,1).toUpperCase() + palabras[i].substring(1).toLowerCase();
			nuevoNombre = nuevoNombre + palabras[i] + " ";
		}
		nombre = nuevoNombre.trim();
		return nombre;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}
		if (correo.isBlank() || !correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}

		this.correo = correo;
	}
	
	public void setTelefono(String telefono) {
		if (telefono != null) {
			if (telefono.isBlank() || telefono.length() != 9 || !telefono.matches(ER_TELEFONO)) {
				throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
			}

			this.telefono = telefono;
		}
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public static Profesor getProfesorFicticio(String correoFicticio) {
		
		return new Profesor("Alejandrico", correoFicticio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		if (telefono == null) {
			return "nombre=" + formateaNombre(nombre) + ", correo=" + correo;
		}
		return "nombre=" + formateaNombre(nombre) + ", correo=" + correo + ", teléfono=" + telefono;
	}

	@Override
	public int compareTo(Profesor o) {
		return getCorreo().compareTo(o.getCorreo());
	}

}
