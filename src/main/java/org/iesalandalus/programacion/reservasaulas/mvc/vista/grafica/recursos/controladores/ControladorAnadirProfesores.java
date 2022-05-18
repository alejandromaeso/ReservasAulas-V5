package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorAnadirProfesores {
	
	private ObservableList<Profesor> profesores = FXCollections.observableArrayList();

	private IControlador controladorMVC;	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="paneInsertarProf"
    private Pane paneInsertarProf; // Value injected by FXMLLoader

    @FXML // fx:id="tfInsertarNombreProf"
    private TextField tfInsertarNombreProf; // Value injected by FXMLLoader

    @FXML // fx:id="lbTituloInsertarProf"
    private Label lbTituloInsertarProf; // Value injected by FXMLLoader

    @FXML // fx:id="tfInsertarTelefonoProf"
    private TextField tfInsertarTelefonoProf; // Value injected by FXMLLoader

    @FXML // fx:id="tfInsertarCorreoProf"
    private TextField tfInsertarCorreoProf; // Value injected by FXMLLoader

    @FXML // fx:id="lbInsertarNombreProf"
    private Label lbInsertarNombreProf; // Value injected by FXMLLoader

    @FXML // fx:id="btInsertar"
    private Button btInsertar; // Value injected by FXMLLoader

    @FXML // fx:id="lbCorreoProf"
    private Label lbCorreoProf; // Value injected by FXMLLoader

    @FXML // fx:id="lbTelfProf"
    private Label lbTelfProf; // Value injected by FXMLLoader

    @FXML // fx:id="btCancelar"
    private Button btCancelar; // Value injected by FXMLLoader
    
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

    @FXML
    void insertarProf(ActionEvent event) throws OperationNotSupportedException {
    	try {
        	if(tfInsertarTelefonoProf.getText().isBlank()) {
        		controladorMVC.insertarProfesor(new Profesor(tfInsertarNombreProf.getText(), tfInsertarCorreoProf.getText()));
        	} else {
        		controladorMVC.insertarProfesor(new Profesor(tfInsertarNombreProf.getText(), tfInsertarCorreoProf.getText(), tfInsertarTelefonoProf.getText()));
        	}
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();	
    	} catch (Exception e) {
			Dialogos.mostrarDialogoAdvertencia("ERROR", "Por favor, introduce un profesor válido.");
		}         	
    }

    @FXML
    void btCancelar(ActionEvent event) {
		if (Dialogos.mostrarDialogoConfirmacion("Cancelar", "¿Estás seguro de que desea cancelar la inserción del Profesorado?", null)) {
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert paneInsertarProf != null : "fx:id=\"paneInsertarProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert tfInsertarNombreProf != null : "fx:id=\"tfInsertarNombreProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert lbTituloInsertarProf != null : "fx:id=\"lbTituloInsertarProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert tfInsertarTelefonoProf != null : "fx:id=\"tfInsertarTelefonoProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert tfInsertarCorreoProf != null : "fx:id=\"tfInsertarCorreoProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert lbInsertarNombreProf != null : "fx:id=\"lbInsertarNombreProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert btInsertar != null : "fx:id=\"btInsertar\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert lbCorreoProf != null : "fx:id=\"lbCorreoProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert lbTelfProf != null : "fx:id=\"lbTelfProf\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
        assert btCancelar != null : "fx:id=\"btCancelar\" was not injected: check your FXML file 'InsertarProfesores.fxml'.";
    }
    
	public void insertarProfesor() {
		profesores.clear();
		tfInsertarNombreProf.getSelection();
		tfInsertarCorreoProf.getSelection();
		tfInsertarTelefonoProf.getSelection();
		//reservas.setAll(controladorMVC.listarReservas());
	}

}