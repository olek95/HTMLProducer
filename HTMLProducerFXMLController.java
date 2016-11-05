package htmlproducer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class HTMLProducerFXMLController implements Initializable {
    
    @FXML
    private Button generateButton;
    @FXML
    private MenuItem saveMenuItem, openMenuItem;
    @FXML
    private TextArea htmlTextArea;
    @FXML
    private void handleGenerateButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       generateButton.setOnAction((event) -> {
            WebSite site = WebSiteFactory.createWebSite(WebSiteType.NEWS);
        });
       saveMenuItem.setOnAction((event) -> {
           FileChooser fileChooser = new FileChooser(); 
           fileChooser.setTitle("Zapis plików HTML");
           fileChooser.getExtensionFilters().add(new ExtensionFilter("Pliki HTML", "*.html", "*.htm"));
           File selectedFile = fileChooser.showSaveDialog(null);
           if(selectedFile != null) writeToFile(selectedFile.getPath(), htmlTextArea.getText());
       });
       openMenuItem.setOnAction((event) -> {
           FileChooser fileChooser = new FileChooser(); 
           fileChooser.setTitle("Odczyt plików HTML");
           fileChooser.getExtensionFilters().add(new ExtensionFilter("Pliki HTML", "*.html", "*.htm"));
           File selectedFile = fileChooser.showOpenDialog(null);
           if(selectedFile != null) openFile(selectedFile.getPath());
       });
    }    
    private void writeToFile(String path, String html){
        try{
            Files.write(Paths.get(path), html.getBytes());
        }catch(IOException e){
            Logger.getLogger(HTMLProducerFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void openFile(String path){
        try{
            htmlTextArea.setText(new String(Files.readAllBytes(Paths.get(path))));
        }catch(IOException e){
            Logger.getLogger(HTMLProducerFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

