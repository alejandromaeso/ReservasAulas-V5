package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorListarAulas {

	private ObservableList<Aula> aulas = FXCollections.observableArrayList();
	private ObservableList<Reserva> reservasAulas = FXCollections.observableArrayList();

	private IControlador controladorMVC;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="colNombreAula"
	private TableColumn<Aula, String> colNombreAula; // Value injected by FXMLLoader

	@FXML // fx:id="colPuestosAula"
	private TableColumn<Aula, String> colPuestosAula; // Value injected by FXMLLoader

	@FXML // fx:id="btBorrarAula"
	private Button btBorrarAula; // Value injected by FXMLLoader

	@FXML // fx:id="tblAulas"
	private TableView<Aula> tblAulas; // Value injected by FXMLLoader

	@FXML // fx:id="btInsertarAula"
	private Button btInsertarAula; // Value injected by FXMLLoader

	@FXML // fx:id="lbAulas"
	private Label lbAulas; // Value injected by FXMLLoader

	@FXML // fx:id="fxAulas"
	private Pane fxAulas; // Value injected by FXMLLoader

	@FXML // fx:id="btReservas"
	private Button btReservas; // Value injected by FXMLLoader

	@FXML // fx:id="tbOpciones"
	private ToolBar tbOpciones; // Value injected by FXMLLoader

	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert colNombreAula != null : "fx:id=\"colNombreAula\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert colPuestosAula != null : "fx:id=\"colPuestosAula\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert btBorrarAula != null : "fx:id=\"btBorrarAula\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert tblAulas != null : "fx:id=\"tblAulas\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert btInsertarAula != null : "fx:id=\"btInsertarAula\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert lbAulas != null : "fx:id=\"lbAulas\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert fxAulas != null : "fx:id=\"fxAulas\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert btReservas != null : "fx:id=\"btReservas\" was not injected: check your FXML file 'Aulas.fxml'.";
		assert tbOpciones != null : "fx:id=\"tbOpciones\" was not injected: check your FXML file 'Aulas.fxml'.";

		// Tabla aulas
		colNombreAula.setCellValueFactory(new PropertyValueFactory<Aula, String>("nombre"));
		colPuestosAula.setCellValueFactory(new PropertyValueFactory<Aula, String>("puestos"));
		tblAulas.setItems(aulas);
	}

	public void actualizaAulas() {
		aulas.clear();
		tblAulas.getSelectionModel().clearSelection();
		aulas.setAll(controladorMVC.listarAulas());
	}

	@FXML
	void btInsertarAula(ActionEvent event) {

		try {
			FXMLLoader cargadorInsertarAula = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/InsertarAulas.fxml"));
			Pane raiz = cargadorInsertarAula.load();
			ControladorAnadirAulas cVentanaPrincipal = cargadorInsertarAula.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Insertar Aula");
			ventana.setScene(escena);
			ventana.setResizable(false);
			ventana.show();

			bloquearVentana();
			ventana.setOnHiding(eventoVentana -> {
				desbloquearVentana();
				actualizaAulas();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void btBorrarAula(ActionEvent event) throws OperationNotSupportedException {
		if (Dialogos.mostrarDialogoConfirmacion("Borrar", "¿Estás seguro de que desea borrar el aula seleccionada?",
				null)) {
			Aula aula = tblAulas.getSelectionModel().getSelectedItem();
			controladorMVC.borrarAula(aula);
			actualizaAulas();
		}
	}

	private void bloquearVentana() {
		fxAulas.setDisable(true);
	}

	private void desbloquearVentana() {
		fxAulas.setDisable(false);
	}

}
