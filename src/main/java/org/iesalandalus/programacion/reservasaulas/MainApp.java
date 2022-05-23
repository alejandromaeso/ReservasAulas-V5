package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IFuenteDatos;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.IModelo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.IVista;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.texto.VistaTexto;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {
		IModelo modelo = new Modelo(procesarArgumentosModelo(args));
		IVista vista = procesarArgumentosVista(args);
		IControlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
	}

	private static IFuenteDatos procesarArgumentosModelo(String[] args) {
		IFuenteDatos fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
		;
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-fmemoria")) {
				fuenteDatos = FactoriaFuenteDatos.MEMORIA.crear();
			} else if (argumento.equalsIgnoreCase("-fficheros")) {
				fuenteDatos = FactoriaFuenteDatos.FICHEROS.crear();
			} else if (argumento.equalsIgnoreCase("-fmongodb")) {
				fuenteDatos = FactoriaFuenteDatos.MONGODB.crear();
			}
		}
		return fuenteDatos;
	}

	private static IVista procesarArgumentosVista(String[] args) {
		IVista vista = FactoriaVista.GRAFICA.crear();
		for (String argumento : args) {
			if (argumento.equalsIgnoreCase("-vgrafica")) {
				vista = FactoriaVista.GRAFICA.crear();
			} else if (argumento.equalsIgnoreCase("-vtexto")) {
				vista = FactoriaVista.TEXTO.crear();
			}
		}
		return vista;
	}
}
