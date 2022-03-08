package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;

public interface IVista {

	public void setControlador(Controlador controlador);

	public void comenzar() throws OperationNotSupportedException;

	public void salir();

}