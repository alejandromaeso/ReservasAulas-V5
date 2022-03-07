package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.memoria;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.IReservas;

public class Reservas implements IReservas {

	private static final float MAX_PUNTOS_PROFESOR_MES = 200;
	private List<Reserva> coleccionReservas;

	public Reservas() {
		coleccionReservas = new ArrayList<>();
	}

	public Reservas(IReservas reservas) {
		setReservas(reservas);
	}

	private void setReservas(IReservas reservas) {

		if (reservas == null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}
		coleccionReservas = reservas.getReservas();
	}

	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> copiaReservas = new ArrayList<Reserva>();
		Iterator<Reserva> iterador = reservas.iterator();

		while (iterador.hasNext()) {
			copiaReservas.add(new Reserva(iterador.next()));
		}
		Collections.sort(copiaReservas);
		return copiaReservas;
	}

	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

	public int getNumReservas() {
		return coleccionReservas.size();
	}

	public void insertar(Reserva reserva) throws OperationNotSupportedException {

		if (reserva == null) {
			throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");
		}

		Reserva reservaExistente = getReservaAulaDia(reserva.getAula(), reserva.getPermanencia().getDia());

		if (reservaExistente != null) {
			if (reservaExistente.getPermanencia() instanceof PermanenciaPorTramo
					&& reserva.getPermanencia() instanceof PermanenciaPorHora) {
				throw new OperationNotSupportedException(
						"ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");
			}
			if (reservaExistente.getPermanencia() instanceof PermanenciaPorHora
					&& reserva.getPermanencia() instanceof PermanenciaPorTramo) {
				throw new OperationNotSupportedException(
						"ERROR: Ya se ha realizado una reserva de otro tipo de permanencia para este día.");
			}
		}

		if (!esMesSiguienteOPosterior(reserva)) {
			throw new OperationNotSupportedException(
					"ERROR: Sólo se pueden hacer reservas para el mes que viene o posteriores.");
		}
		if ((getPuntosGastadosReserva(reserva) + reserva.getPuntos()) > MAX_PUNTOS_PROFESOR_MES) {
			throw new OperationNotSupportedException(
					"ERROR: Esta reserva excede los puntos máximos por mes para dicho profesor.");
		}
		if (coleccionReservas.contains(reserva)) {
			throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
		} else {
			coleccionReservas.add(new Reserva(reserva));
		}

	}

	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		if (reserva == null) {
			throw new NullPointerException("ERROR: La reserva no puede ser nula");
		}
		boolean mesSiguienteOPosterior = false;
		Month mes = reserva.getPermanencia().getDia().getMonth();
		Month mesActual = LocalDate.now().getMonth();
		if (mes.getValue() > mesActual.getValue()) {
			mesSiguienteOPosterior = true;
		}
		return mesSiguienteOPosterior;

	}

	private float getPuntosGastadosReserva(Reserva reserva) {

		List<Reserva> listadoReservasProfesor = getReservasProfesorMes(reserva.getProfesor(),
				reserva.getPermanencia().getDia());

		float sumaPuntosTotales = 0;

		Iterator<Reserva> iterador = listadoReservasProfesor.iterator();

		while (iterador.hasNext()) {
			sumaPuntosTotales = sumaPuntosTotales + iterador.next().getPuntos();
		}

		return sumaPuntosTotales;
	}

	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate fecha) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo");
		} else if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		List<Reserva> reservasMes = new ArrayList<>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			Month mesLista = auxiliar.getPermanencia().getDia().getMonth();
			Month mesFecha = fecha.getMonth();
			if (profesor.equals(auxiliar.getProfesor()) && mesLista.getValue() == mesFecha.getValue()) {
				reservasMes.add(new Reserva(auxiliar));
			}
		}
		return reservasMes;
	}

	public Reserva getReservaAulaDia(Aula aula, LocalDate fecha) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula");
		} else if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		Reserva reservaDia = null;
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (aula.equals(auxiliar.getAula()) && fecha.equals(auxiliar.getPermanencia().getDia())) {
				reservaDia = new Reserva(auxiliar);
			}
		}
		return reservaDia;
	}

	public Reserva buscar(Reserva buscarReserva) {
		if (buscarReserva == null) {
			throw new NullPointerException("ERROR: No se puede buscar una reserva nula.");
		}

		Iterator<Reserva> iterador = coleccionReservas.iterator();

		while (iterador.hasNext()) {
			Reserva reservaBuscada = iterador.next();
			if (buscarReserva.equals(reservaBuscada)) {
				return new Reserva(reservaBuscada);
			}
		}
		return null;

	}

	public void borrar(Reserva borrarReserva) throws OperationNotSupportedException {
		if (borrarReserva == null) {
			throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
		}

		if (esMesSiguienteOPosterior(borrarReserva)) {
			if (coleccionReservas.contains(borrarReserva)) {
				coleccionReservas.remove(borrarReserva);
			} else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna reserva igual.");

			}

		} else {
			throw new OperationNotSupportedException(
					"ERROR: Sólo se pueden anular reservas para el mes que viene o posteriores.");
		}

	}

	public List<String> representar() {
		List<String> representacion = new ArrayList<String>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reservaRepresentada = iterador.next();
			representacion.add(reservaRepresentada.toString());
		}
		return representacion;
	}

	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		List<Reserva> reservasProfesor = new ArrayList<Reserva>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (profesor.equals(reserva.getProfesor())) {
				reservasProfesor.add(new Reserva(reserva));
			}
		}
		Collections.sort(reservasProfesor);
		return reservasProfesor;
	}

	public List<Reserva> getReservasAula(Aula aula) {
		if (aula == null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}
		List<Reserva> reservasAula = new ArrayList<Reserva>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (aula.equals(reserva.getAula())) {
				reservasAula.add(new Reserva(reserva));
			}
		}
		Collections.sort(reservasAula);
		return reservasAula;
	}

	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		if (permanencia == null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}
		List<Reserva> reservasPermanencia = new ArrayList<Reserva>();
		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva reserva = iterador.next();
			if (permanencia.equals(reserva.getPermanencia())) {
				reservasPermanencia.add(new Reserva(reserva));
			}
		}
		Collections.sort(reservasPermanencia);
		return reservasPermanencia;
	}

	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		}

		if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}

		boolean consulta = true;

		Iterator<Reserva> iterador = coleccionReservas.iterator();
		while (iterador.hasNext()) {
			Reserva auxiliar = iterador.next();
			if (permanencia.equals(auxiliar.getPermanencia()) && aula.equals(auxiliar.getAula())) {
				consulta = false;
			}
		}

		return consulta;
	}
}
