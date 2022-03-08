package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

public interface IVista {

	public void setControlador(IControlador controlador);

	public void comenzar() throws OperationNotSupportedException;

	public void salir();

}