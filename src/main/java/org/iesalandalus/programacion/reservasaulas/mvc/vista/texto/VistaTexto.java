package org.iesalandalus.programacion.reservasaulas.mvc.vista.texto;

import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;

public class VistaTexto implements IVista {

	private IControlador controlador;

	public VistaTexto() {
		Opcion.setVista(this);
	}

	@Override
	public void setControlador(IControlador controlador) {

		this.controlador = controlador;
	}

	@Override
	public void comenzar() throws OperationNotSupportedException {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void salir() {

		System.out.println("Se ha terminado el programa.");
		controlador.terminar();
	}

	public void insertarAula() {

		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("El aula se ha insertado correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}

	}

	public void borrarAula() {

		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("El aula se ha borrado correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAula() {
		try {
			Consola.mostrarCabecera("Buscar aula existente: ");
			Aula aulaBuscar = controlador.buscarAula(Consola.leerAula());
			if (aulaBuscar == null) {
				System.out.println("El aula introducida no existe.");
			} else {
				System.out.println("El aula introducida existe.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAulas() {

		Consola.mostrarCabecera("Listado de aulas existentes: ");
		List<String> representar = controlador.representarAulas();

		if (representar.size() == 0) {
			System.out.println("No hay aulas introducidas");
		} else {
			Iterator<String> iterador = representar.iterator();

			while (iterador.hasNext()) {
				System.out.println(iterador.next());
			}
		}
	}

	public void insertarProfesor() {

		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("El profesor se ha insertado correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {

		try {
			controlador.borrarProfesor(Consola.leerProfesor());
			System.out.println("El profesor se ha borrado correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarProfesor() {

		try {
			Consola.mostrarCabecera("Buscar profesor existente: ");

			Profesor profesorBuscar = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesorBuscar == null) {
				System.out.println("El profesor introducido no existe.");
			} else {
				System.out.println("El profesor introducido existe.");
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {

		Consola.mostrarCabecera("Listado de profesores existentes: ");
		List<String> representar = controlador.representarProfesores();

		if (representar.size() == 0) {
			System.out.println("No hay profesores introducidos");
		} else {
			Iterator<String> iterador = representar.iterator();
			while (iterador.hasNext()) {
				System.out.println(iterador.next());
			}
		}
	}

	public void realizarReserva() {

		try {
			Reserva reserva = Consola.leerReserva();
			if (reserva == null) {
				throw new IllegalArgumentException("Error introducido en los datos de la reserva, compruébelos.");
			}
			controlador.realizarReserva(reserva);
			System.out.println("Reserva realizada correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void anularReserva() {

		try {
			controlador.anularReserva(Consola.leerReservaFicticia());
			System.out.println("Reserva anulada correctamente.");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservas() {
		Consola.mostrarCabecera("Listado de reservas existentes: ");
		List<String> representar = controlador.representarReservas();

		if (representar.size() == 0) {
			System.out.println("No hay reservas existentes.");
		} else {
			Iterator<String> iterador = representar.iterator();

			while (iterador.hasNext()) {
				System.out.println(iterador.next());
			}
		}
	}

	public void listarReservasAula() {
		try {

			Consola.mostrarCabecera("Listado de reservas existentes por aula: ");
			Aula aulaBuscar = controlador.buscarAula(Consola.leerAula());
			if (aulaBuscar == null) {
				System.out.println("El aula introducida no existe.");
			} else {
				List<Reserva> listaReservas = controlador.getReservasAula(aulaBuscar);

				if (listaReservas.size() == 0) {
					System.out.println("No hay reservas.");
				} else {
					Iterator<Reserva> iterador = listaReservas.iterator();

					while (iterador.hasNext()) {
						System.out.println(iterador.next().toString());
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarReservaProfesor() {
		try {

			Consola.mostrarCabecera("Listado de reservas existentes por profesor: ");
			Profesor profesorBuscar = controlador.buscarProfesor(Consola.leerProfesor());
			if (profesorBuscar == null) {
				throw new NullPointerException("El profesor introducido no existe.");
			} else {
				List<Reserva> listaReservas = controlador.getReservasProfesor(profesorBuscar);

				if (listaReservas.size() == 0) {
					System.out.println("No hay reservas.");
				} else {
					Iterator<Reserva> iterador = listaReservas.iterator();

					while (iterador.hasNext()) {
						System.out.println(iterador.next().toString());
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void consultarDisponibilidad() {
		try {

			Permanencia permanencia = Consola.leerPermanencia();
			Aula aulaBuscar = controlador.buscarAula(Consola.leerAula());
			if (aulaBuscar == null) {
				System.out.println("El aula introducida no existe.");
			} else {
				if (controlador.consultarDisponibilidad(aulaBuscar, permanencia)) {
					System.out.println("El aula está disponible.");
				} else {
					System.out.println("El aula ya está reservada.");
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
