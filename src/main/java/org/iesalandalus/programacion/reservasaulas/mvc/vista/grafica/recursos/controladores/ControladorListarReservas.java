package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.LocalizadorRecursos;

import javafx.beans.property.SimpleStringProperty;
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

public class ControladorListarReservas {
	
	private ObservableList<Reserva> reservas = FXCollections.observableArrayList();

	private IControlador controladorMVC;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btSalir"
    private Button btSalir; // Value injected by FXMLLoader

    @FXML // fx:id="btBorrarReserva"
    private Button btBorrarReserva; // Value injected by FXMLLoader

    @FXML // fx:id="fxReservas"
    private Pane fxReservas; // Value injected by FXMLLoader

    @FXML // fx:id="btInsertarReserva"
    private Button btInsertarReserva; // Value injected by FXMLLoader

    @FXML // fx:id="btProfesores"
    private Button btProfesores; // Value injected by FXMLLoader

    @FXML // fx:id="lbProfesores"
    private Label lbProfesores; // Value injected by FXMLLoader

    @FXML // fx:id="btAulas"
    private Button btAulas; // Value injected by FXMLLoader
    
    @FXML // fx:id="btInsertarProf"
    private Button btInsertarProf; // Value injected by FXMLLoader
    
    @FXML // fx:id="btBorrarProf"
    private Button btBorrarProf; // Value injected by FXMLLoader

    @FXML // fx:id="tbNombreAulaReserva"
    private TableColumn<Reserva, String> tbNombreAulaReserva; // Value injected by FXMLLoader

    @FXML // fx:id="tbClases"
    private ToolBar tbClases; // Value injected by FXMLLoader

    @FXML // fx:id="tbPuntosReserva"
    private TableColumn<Reserva, String> tbPuntosReserva; // Value injected by FXMLLoader

    @FXML // fx:id="tbNombreReserva"
    private TableColumn<Reserva, String> tbNombreReserva; // Value injected by FXMLLoader

    @FXML // fx:id="tbFechaReserva"
    private TableColumn<Reserva, String> tbPermanencia; // Value injected by FXMLLoader

    @FXML // fx:id="tbReservas"
    private TableView<Reserva> tbReservas; // Value injected by FXMLLoader

    @FXML // fx:id="btReservas"
    private Button btReservas; // Value injected by FXMLLoader

    @FXML // fx:id="tbCorreoReserva"
    private TableColumn<Reserva, String> tbCorreoReserva; // Value injected by FXMLLoader

    @FXML // fx:id="tbOpciones"
    private ToolBar tbOpciones; // Value injected by FXMLLoader
    
    public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btSalir != null : "fx:id=\"btSalir\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btBorrarReserva != null : "fx:id=\"btBorrarReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert fxReservas != null : "fx:id=\"fxReservas\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btInsertarReserva != null : "fx:id=\"btInsertarReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btProfesores != null : "fx:id=\"btProfesores\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert lbProfesores != null : "fx:id=\"lbProfesores\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btAulas != null : "fx:id=\"btAulas\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btInsertarProf != null : "fx:id=\"btInsertarProf\" was not injected: check your FXML file 'Profesores.fxml'.";
        assert btBorrarProf != null : "fx:id=\"btBorrarProf\" was not injected: check your FXML file 'Profesores.fxml'.";
        assert tbNombreAulaReserva != null : "fx:id=\"tbNombreAulaReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbClases != null : "fx:id=\"tbClases\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbPuntosReserva != null : "fx:id=\"tbPuntosReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbNombreReserva != null : "fx:id=\"tbNombreReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbPermanencia != null : "fx:id=\"tbPermanencia\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbReservas != null : "fx:id=\"tbReservas\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert btReservas != null : "fx:id=\"btReservas\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbCorreoReserva != null : "fx:id=\"tbCorreoReserva\" was not injected: check your FXML file 'Reservas.fxml'.";
        assert tbOpciones != null : "fx:id=\"tbOpciones\" was not injected: check your FXML file 'Reservas.fxml'.";
      
     // Tabla Reservas
        tbNombreReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesor().getNombre()));
        tbCorreoReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProfesor().getCorreo()));
        tbNombreAulaReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAula().getNombre()));
        tbPermanencia.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPermanencia().toString()));
        tbPuntosReserva.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPuntos()+""));
        tbReservas.setItems(reservas);
     		
    }

	public void actualizaReservas() {
		reservas.clear();
		tbReservas.getSelectionModel().clearSelection();
		reservas.setAll(controladorMVC.listarReservas());
	}
	
    @FXML
    void btInsertarReserva(ActionEvent event) {
    	
		try {
			FXMLLoader cargadorInsertarReserva = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/InsertarReservas.fxml"));
			Pane raiz = cargadorInsertarReserva.load();
			ControladorAnadirReservas cVentanaPrincipal = cargadorInsertarReserva.getController();
			cVentanaPrincipal.setControladorMVC(controladorMVC);
			cVentanaPrincipal.setControladorListarReservas(this);
			Stage ventana = new Stage();

			Scene escena = new Scene(raiz);
			ventana.setTitle("Insertar Reserva");
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
    void btBorrarReserva(ActionEvent event) throws OperationNotSupportedException {
    	
		Reserva reserva = tbReservas.getSelectionModel().getSelectedItem();
		controladorMVC.anularReserva(reserva);
		actualizaReservas();

    }
    
	private void bloquearVentana() {
		fxReservas.setDisable(true);
	}

	private void desbloquearVentana() {
		fxReservas.setDisable(false);
	}
	
	
}
