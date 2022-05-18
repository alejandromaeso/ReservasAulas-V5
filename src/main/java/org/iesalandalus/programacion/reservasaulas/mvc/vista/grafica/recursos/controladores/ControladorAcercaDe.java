package org.iesalandalus.programacion.reservasaulas.mvc.vista.grafica.recursos.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControladorAcercaDe {
	
	private IControlador controladorMVC;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btOk"
    private Button btOk; // Value injected by FXMLLoader
    
    @FXML // fx:id="imgagenAcercaDe"
    private ImageView imgagenAcercaDe; // Value injected by FXMLLoader

    @FXML // fx:id="paneAcercaDe"
    private Pane paneAcercaDe; // Value injected by FXMLLoader

    @FXML // fx:id="lbAcercaDe"
    private Label lbAcercaDe; // Value injected by FXMLLoader

    @FXML // fx:id="lbAlejandro"
    private Label lbAlejandro; // Value injected by FXMLLoader

    @FXML // fx:id="tfHechaPor"
    private TextField tfHechaPor; // Value injected by FXMLLoader
    
	public void setControladorMVC(IControlador controladorMVC) {
		this.controladorMVC = controladorMVC;
	}
	
    @FXML
    void btClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btOk != null : "fx:id=\"btOk\" was not injected: check your FXML file 'AcercaDe.fxml'.";
        assert paneAcercaDe != null : "fx:id=\"paneAcercaDe\" was not injected: check your FXML file 'AcercaDe.fxml'.";
        assert lbAcercaDe != null : "fx:id=\"lbAcercaDe\" was not injected: check your FXML file 'AcercaDe.fxml'.";
        assert lbAlejandro != null : "fx:id=\"lbAlejandro\" was not injected: check your FXML file 'AcercaDe.fxml'.";
        assert tfHechaPor != null : "fx:id=\"tfHechaPor\" was not injected: check your FXML file 'AcercaDe.fxml'.";
    }
  
}