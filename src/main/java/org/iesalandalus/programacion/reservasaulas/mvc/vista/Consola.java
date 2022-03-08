package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private final static DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/M/yyyy");

	public Consola() {

	}

	public static void mostrarMenu() {

		mostrarCabecera("Gestión de reservas de aulas");

		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String cabecera) {

		System.out.printf("%n%s%n", cabecera);
		String cadena = "%0" + cabecera.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace('0', '-'));

	}

	public static int elegirOpcion() {

		int opcionElegida;
		do {
			System.out.println("Elija una opción: ");
			opcionElegida = Entrada.entero();
		} while (opcionElegida < 0 || opcionElegida > Opcion.values().length);
		return opcionElegida;
	}

	public static Aula leerAula() {

		System.out.println("Por favor, introduzca el nombre del aula: ");
		String nombre = leerNombreAula();
		System.out.println("Por favor, introduce el número de puestos del aula: ");
		int puestos = Entrada.entero();
		return new Aula(nombre , puestos);
	}
	
	public static int leerNumeroPuestos() {
		
		System.out.println("Por favor, introduzca el número de puestos del aula: ");
		int puestos = Entrada.entero();
		
		return puestos;
	}
	
	public static Aula leerAulaFicticia() {
		
		System.out.println("Por favor. introduzca el nombre del aula ficticia: ");
		String nombreAulaFicticia = Entrada.cadena();
		
		return Aula.getAulaFicticia(nombreAulaFicticia);
	}

	public static String leerNombreAula() {

		String nombre = Entrada.cadena();
		return nombre;
	}

	public static Profesor leerProfesor() {

		String nombre = null;
		String correo = null;
		String telefono = null;
		System.out.println("Por favor, introduzca el nombre del profesor: ");
		nombre = leerNombreProfesor();
		System.out.println("Por favor, introduzca el correo del profesor: ");
		correo = leerNombreProfesor();
		System.out.println("Por favor, introduzca el telefono del profesor: ");
		telefono = leerNombreProfesor();

		Profesor profesor;

		if (telefono == null || telefono.isBlank()) {
			profesor = new Profesor(nombre, correo);
		} else {
			profesor = new Profesor(nombre, correo, telefono);
		}
		return profesor;
	}

	public static String leerNombreProfesor() {
		String nombre = Entrada.cadena();
		return nombre;
	}
	
	public static Profesor leerProfesorFicticio() {
		System.out.println("Por favor, introduzca el correo del profesor ficticio: ");
		return Profesor.getProfesorFicticio(Entrada.cadena());
	}

	public static Tramo leerTramo() {

		System.out.println("Por favor, introduzca el tramo (maNana o tarde):");
		String tramoIntroducido = Entrada.cadena();

		return Tramo.valueOf(tramoIntroducido.toUpperCase());
	}

	public static LocalDate leerDia() {

		try {
			System.out.println("Por favor, intrdozuca una fecha: ");
			String fechaIntroducida = Entrada.cadena();
			return LocalDate.parse(fechaIntroducida, FORMATO_DIA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de fecha incorrecto: dd/MM/yyyy");
		}
	}
	
	public static int elegirPermanencia() {
		int seleccion;
		do {
			System.out.println("Por favor, elija una permanencia:");
			System.out.println("Para seleccionar permamencia por Tramo: 1");
			System.out.println("Para seleccionar permamencia por Hora: 2");
			seleccion = Entrada.entero();
		} while (seleccion < 1 || seleccion >2);
		
		return seleccion;
	}
	
	public static Permanencia leerPermanencia() {
		
		int seleccionPermanencia = Consola.elegirPermanencia();
		LocalDate dia = leerDia();
		Permanencia permanencia = null;
		if (seleccionPermanencia == 1) {
			Tramo tramo = leerTramo();
			permanencia = new PermanenciaPorTramo(dia, tramo);
		} else if (seleccionPermanencia == 2) {
			LocalTime hora = leerHora();
			permanencia = new PermanenciaPorHora(dia, hora);
		}
		return permanencia;
	}
	
	private static LocalTime leerHora() {
		
		LocalTime hora = null;
		String formato = "HH:mm";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formato);
		System.out.printf("Introduce la hora (%s): ", formato);
		String horaStr = Entrada.cadena();
		try {
			hora = LocalTime.parse(horaStr, dtf);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la hora no es correcto.");
		}
		return hora;
	}
	
	public static Reserva leerReserva() {
		
		Profesor profesor = leerProfesorFicticio();
		Aula aula = leerAulaFicticia();
		Permanencia permanencia = leerPermanencia();
		
		return new Reserva(profesor, aula, permanencia);
	}
	
	public static Reserva leerReservaFicticia() {
		
		return Reserva.getReservaFicticia(leerAulaFicticia(), leerPermanencia());
	}
}
