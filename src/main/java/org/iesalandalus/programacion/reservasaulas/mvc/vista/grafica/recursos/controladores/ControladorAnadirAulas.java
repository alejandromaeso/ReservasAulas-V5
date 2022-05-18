package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
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

public class ControladorAnadirAulas {

	private IControlador controladorMVC;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfNombreAula"
    private TextField tfNombreAula; // Value injected by FXMLLoader

    @FXML // fx:id="lbPuestosAula"
    private Label lbPuestosAula; // Value injected by FXMLLoader

    @FXML // fx:id="btInsertarAula"
    private Button btInsertarAula; // Value injected by FXMLLoader

    @FXML // fx:id="paneAulas"
    private Pane paneAulas; // Value injected by FXMLLoader

    @FXML // fx:id="tfPuestosAula"
    private TextField tfPuestosAula; // Value injected by FXMLLoader

    @FXML // fx:id="lbInsertarAulas"
    private Label lbInsertarAulas; // Value injected by FXMLLoader

    @FXML // fx:id="lbNombreAula"
    private Label lbNombreAula; // Value injected by FXMLLoader

    @FXML // fx:id="btCancelar"
    private Button btCancelar; // Value injected by FXMLLoader
    
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}

    @FXML
    void btInsertarAula(ActionEvent event) throws NumberFormatException, OperationNotSupportedException {
    	try {
        	controladorMVC.insertarAula(new Aula(tfNombreAula.getText(), Integer.parseInt(tfPuestosAula.getText())));
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();	
    	} catch (Exception e) {
    		Dialogos.mostrarDialogoAdvertencia("ERROR", "Por favor, inserte un aula válida");
		}
    }

    @FXML
    void btCancelar(ActionEvent event) {	
		if (Dialogos.mostrarDialogoConfirmacion("Cancelar", "¿Estás seguro de que desea cancelar la inserción del Aula?", null)) {
			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfNombreAula != null : "fx:id=\"tfNombreAula\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert lbPuestosAula != null : "fx:id=\"lbPuestosAula\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert btInsertarAula != null : "fx:id=\"btInsertarAula\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert paneAulas != null : "fx:id=\"paneAulas\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert tfPuestosAula != null : "fx:id=\"tfPuestosAula\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert lbInsertarAulas != null : "fx:id=\"lbInsertarAulas\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert lbNombreAula != null : "fx:id=\"lbNombreAula\" was not injected: check your FXML file 'InsertarAulas.fxml'.";
        assert btCancelar != null : "fx:id=\"btCancelar\" was not injected: check your FXML file 'InsertarAulas.fxml'.";

    }

}
