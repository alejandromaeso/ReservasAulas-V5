package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorAnadirReservas {
	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private IControlador controladorMVC;
	private ControladorListarReservas controladorListar;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ToggleGroup permanencia;

	@FXML
	private Button btInsertarReserva;

	@FXML
	private Button btCargarAulas;

	@FXML
	private RadioButton rdHora;

	@FXML
	private DatePicker dpDia;

	@FXML
	private Pane pnInsertarReservas;

	@FXML
	private ComboBox<Aula> cbAulas;

	@FXML
	private Button btCancelarReserva;

	@FXML
	private Label lbAulaAReservas;

	@FXML
	private TextField tfCorreoProf;

	@FXML
	private Label tituloInsertarReservas;

	@FXML
	private Label lbCorreoProf;

	@FXML
	private Label lbNombreProf;

	@FXML
	private RadioButton rdTramo;

	@FXML
	private Label lbPermanencia;

	@FXML
	private ComboBox<Tramo> cbTramo;

	@FXML
	private Slider slHora;

	@FXML
	private TextField tfNombreProf;

	@FXML
	void seleccionDia(ActionEvent event) {

		LocalDate diaSeleccionado = dpDia.getValue();
		String diaFormateado = diaSeleccionado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		// System.out.println(diaFormateado);

	}

	@FXML
	void loadAulas(ActionEvent event) {
		aulas.setAll(controladorMVC.listarAulas());
		cbAulas.setItems(aulas);
		btCargarAulas.setDisable(true);

	}

	@FXML
	void tramoSeleccionado(ActionEvent event) {

		cbTramo.setOpacity(1);
		cbTramo.setDisable(false);
		slHora.setOpacity(0);
		slHora.setDisable(true);
	}

	@FXML
	void horaSeleccionada(ActionEvent event) {
		cbTramo.setOpacity(0);
		cbTramo.setDisable(true);
		slHora.setOpacity(1);
		slHora.setDisable(false);
	}

	@FXML
	void initialize() {
		assert permanencia != null
				: "fx:id=\"permanencia\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert btInsertarReserva != null
				: "fx:id=\"btInsertarReserva\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert rdHora != null : "fx:id=\"rdHora\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert dpDia != null : "fx:id=\"dpDia\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert pnInsertarReservas != null
				: "fx:id=\"pnInsertarReservas\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert cbAulas != null : "fx:id=\"cbAulas\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert btCancelarReserva != null
				: "fx:id=\"btCancelarReserva\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert lbAulaAReservas != null
				: "fx:id=\"lbAulaAReservas\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert tfCorreoProf != null
				: "fx:id=\"tfCorreoProf\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert tituloInsertarReservas != null
				: "fx:id=\"tituloInsertarReservas\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert lbCorreoProf != null
				: "fx:id=\"lbCorreoProf\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert lbNombreProf != null
				: "fx:id=\"lbNombreProf\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert rdTramo != null : "fx:id=\"rdTramo\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert lbPermanencia != null
				: "fx:id=\"lbPermanencia\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert cbTramo != null : "fx:id=\"cbTramo\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert slHora != null : "fx:id=\"slHora\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert tfNombreProf != null
				: "fx:id=\"tfNombreProf\" was not injected: check your FXML file 'InsertarReservas.fxml'.";
		assert btCargarAulas != null
				: "fx:id=\"btCargarAulas\" was not injected: check your FXML file 'InsertarReservas.fxml'.";

		cbTramo.setItems(FXCollections.observableArrayList(Tramo.values()));

	}

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	public void setControladorListarReservas(ControladorListarReservas controladorMVC) {
		this.controladorListar = controladorMVC;
	}

	@FXML
	void insertarReserva(ActionEvent event) {

		try {

			System.out.println(slHora.getValue());

			Profesor profesor = controladorMVC
					.buscarProfesor(new Profesor(tfNombreProf.getText(), tfCorreoProf.getText()));
			Aula aula = cbAulas.getValue();

			Permanencia permanenciaSeleccionada;

			if (rdHora.isSelected()) {
				permanenciaSeleccionada = new PermanenciaPorHora(dpDia.getValue(),
						LocalTime.of((int) slHora.getValue(), 0));
			} else {
				permanenciaSeleccionada = new PermanenciaPorTramo(dpDia.getValue(), cbTramo.getValue());
			}

			Reserva reservaSeleccionada = new Reserva(profesor, aula, permanenciaSeleccionada);

			controladorMVC.realizarReserva(reservaSeleccionada);

			controladorListar.actualizaReservas();

			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Dialogos.mostrarDialogoAdvertencia("ERROR", "Por favor, inserte una reserva válida");
		}

	}

	@FXML
	void cancelarReserva(ActionEvent event) {

		if (Dialogos.mostrarDialogoConfirmacion("Cancelar",
				"¿Estás seguro de que desea cancelar la inserción de la Reserva?", null)) {
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
	}

}