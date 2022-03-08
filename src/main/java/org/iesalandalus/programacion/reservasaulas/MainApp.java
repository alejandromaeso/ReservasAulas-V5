package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {
		
		IFuenteDatos factoria = FactoriaFuenteDatos.MEMORIA.crear();
		
		IModelo modelo = new Modelo(factoria);
		IVista vista = new Vista();
		IControlador controlador = new Controlador(modelo, vista);
		
		controlador.comenzar();
		
	}

}
