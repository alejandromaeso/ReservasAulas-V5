package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

/**
 * Sample Skeleton for 'Profesores.fxml' Controller Class
 */

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControladorListarProfesores {

	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservasProfesor = FXCollections.observableArrayList();

	private IControlador controladorMVC;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btSalir"
	private Button btSalir; // Value injected by FXMLLoader

	@FXML // fx:id="fxProfesores"
	private Pane fxProfesores; // Value injected by FXMLLoader

	@FXML // fx:id="colTelefonoProf"
	private TableColumn<Profesor, String> colTelefonoProf; // Value injected by FXMLLoader

	@FXML // fx:id="btProfesores"
	private Button btProfesores; // Value injected by FXMLLoader

	@FXML // fx:id="lbProfesores"
	private Label lbProfesores; // Value injected by FXMLLoader

	@FXML // fx:id="tblReservasProfesor"
	private TableView<Reserva> tblReservasProfesor; // Value injected by FXMLLoader

	@FXML // fx:id="colTramoReservaProf"
	private TableColumn<Reserva, String> colPermanencia; // Value injected by FXMLLoader

	@FXML // fx:id="btAulas"
	private Button btAulas; // Value injected by FXMLLoader

	@FXML // fx:id="tbClases"
	private ToolBar tbClases; // Value injected by FXMLLoader

	@FXML // fx:id="btAyuda"
	private Button btAyuda; // Value injected by FXMLLoader

	@FXML // fx:id="colCorreoProf"
	private TableColumn<Profesor, String> colCorreoProf; // Value injected by FXMLLoader

	@FXML // fx:id="colPuntosReservaProf"
	private TableColumn<Reserva, String> colPuntosReservaProf; // Value injected by FXMLLoader

	@FXML // fx:id="colNombreProf"
	private TableColumn<Profesor, String> colNombreProf; // Value injected by FXMLLoader

	@FXML // fx:id="lbReservasProfesor"
	private Label lbReservasProfesor; // Value injected by FXMLLoader

	@FXML // fx:id="btReservas"
	private Button btReservas; // Value injected by FXMLLoader

	@FXML // fx:id="colAulaReservaProf"
	private TableColumn<Reserva, String> colAulaReservaProf; // Value injected by FXMLLoader

	@FXML // fx:id="tbOpciones"
	private ToolBar tbOpciones; // Value injected by FXMLLoader

	@FXML // fx:id="tblProfesores"
	private TableView<Profesor> tblProfesores; // Value injected by FXMLLoader

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btSalir != null : "fx:id=\"btSalir\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert fxProfesores != null
				: "fx:id=\"fxProfesores\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colTelefonoProf != null
				: "fx:id=\"colTelefonoProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert btProfesores != null
				: "fx:id=\"btProfesores\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert lbProfesores != null
				: "fx:id=\"lbProfesores\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert tblReservasProfesor != null
				: "fx:id=\"tblReservasProfesor\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colPermanencia != null
				: "fx:id=\"colTramoReservaProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert btAulas != null : "fx:id=\"btAulas\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert tbClases != null : "fx:id=\"tbClases\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert btAyuda != null : "fx:id=\"btAyuda\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colCorreoProf != null
				: "fx:id=\"colCorreoProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colPuntosReservaProf != null
				: "fx:id=\"colPuntosReservaProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colNombreProf != null
				: "fx:id=\"colNombreProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert lbReservasProfesor != null
				: "fx:id=\"lbReservasProfesor\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert btReservas != null : "fx:id=\"btReservas\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert colAulaReservaProf != null
				: "fx:id=\"colAulaReservaProf\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert tbOpciones != null : "fx:id=\"tbOpciones\" was not injected: check your FXML file 'Profesores.fxml'.";
		assert tblProfesores != null
				: "fx:id=\"tblProfesores\" was not injected: check your FXML file 'Profesores.fxml'.";

		// Tabla profesores
		colNombreProf.setCellValueFactory(new PropertyValueFactory<Profesor, String>("nombre"));
		colCorreoProf.setCellValueFactory(new PropertyValueFactory<Profesor, String>("correo"));
		colTelefonoProf.setCellValueFactory(new PropertyValueFactory<Profesor, String>("telefono"));
		tblProfesores.setItems(profesores);
		tblProfesores.getSelectionModel().selectedItemProperty()
				.addListener((ob, ov, nv) -> mostrarReservasProfesor((Profesor) nv));

		colAulaReservaProf
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAula().getNombre()));
		colPermanencia.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPermanencia().toString()));
		colPuntosReservaProf
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPuntos() + ""));
		tblReservasProfesor.setItems(reservasProfesor);
	}

	private void mostrarReservasProfesor(Profesor profesor) {
		try {
			if (profesor != null) {
				reservasProfesor.setAll(controladorMVC.getReservasProfesor(profesor));

			}
		} catch (IllegalArgumentException e) {
			reservasProfesor.setAll(FXCollections.observableArrayList());
		}
	}

	public void actualizaProfesores() {
		profesores.clear();
		tblProfesores.getSelectionModel().clearSelection();
		profesores.setAll(controladorMVC.listarProfesores());
	}

	@FXML
	private void clickAulas(ActionEvent event) {

		try {
			FXMLLoader cargadorVentanaAulas = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/Aulas.fxml"));
			Pane raiz = cargadorVentanaAulas.load();
			ControladorListarAulas cVentanaPrincipal = cargadorVentanaAulas.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);
			cVentanaPrincipal.actualizaAulas();
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Listado de Aulas");
			ventana.setScene(escena);
			ventana.setResizable(false);
			ventana.show();

			bloquearVentana();
			ventana.setOnHiding(eventoVentana -> desbloquearVentana());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void clickReservas(ActionEvent event) {
		try {
			FXMLLoader cargadorVentanaReservas = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/Reservas.fxml"));
			Pane raiz = cargadorVentanaReservas.load();
			ControladorListarReservas cVentanaPrincipal = cargadorVentanaReservas.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);
			cVentanaPrincipal.actualizaReservas();
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Listado de Reservas");
			ventana.setScene(escena);
			ventana.setResizable(false);
			ventana.show();
			bloquearVentana();
			ventana.setOnHiding(eventoVentana -> desbloquearVentana());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void bloquearVentana() {
		fxProfesores.setDisable(true);
	}

	private void desbloquearVentana() {
		fxProfesores.setDisable(false);
	}

	@FXML
	private void clickAcercaDe(ActionEvent event) {

		try {
			FXMLLoader cargadorAcercaDe = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/AcercaDe.fxml"));
			Pane raiz = cargadorAcercaDe.load();
			ControladorAcercaDe cVentanaPrincipal = cargadorAcercaDe.getController();
			
			/*Image image = new Image("../recursos/imagenes/adios.jpg");
			ImageView imageView = new ImageView();
			imageView.setImage(image);*/
			

			cVentanaPrincipal.setControladorMVC(controladorMVC);
			
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Acerca De");
			ventana.setScene(escena);
			ventana.setResizable(false);
			ventana.show();

			bloquearVentana();
			ventana.setOnHiding(eventoVentana -> desbloquearVentana());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void btInsertarProfesor(ActionEvent event) {

		try {
			FXMLLoader cargadorInsertarProfesores = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/InsertarProfesores.fxml"));
			Pane raiz = cargadorInsertarProfesores.load();
			ControladorAnadirProfesores cVentanaPrincipal = cargadorInsertarProfesores.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);
			// cVentanaPrincipal.actualizaAulas();
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Insertar Profesores");
			ventana.setScene(escena);
			ventana.setResizable(false);
			ventana.show();

			bloquearVentana();
			ventana.setOnHiding(eventoVentana -> {
				desbloquearVentana();
				actualizaProfesores();
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
    @FXML
    void borrarProfesor(ActionEvent event) throws OperationNotSupportedException {
    	
		Profesor profesor = tblProfesores.getSelectionModel().getSelectedItem();
		controladorMVC.borrarProfesor(profesor);
		actualizaProfesores();

    }

	@FXML
	void clickSalir(ActionEvent event) {

		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que desea salir de la aplicación?", null)) {
			controladorMVC.terminar();
			System.exit(0);
		}
	}
}
