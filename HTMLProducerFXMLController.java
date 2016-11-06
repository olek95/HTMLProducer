package htmlproducer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.xml.transform.TransformerException;

public class HTMLProducerFXMLController implements Initializable {
    
    @FXML
    private Button generateButton;
    @FXML
    private MenuItem saveMenuItem, openMenuItem;
    @FXML
    private TextArea htmlTextArea;
    @FXML
    private RadioButton photoGalleryRadioButton, informationRadioButton, contactRadioButton, newsRadioButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       generateButton.setOnAction((event) -> {
            WebSite site;
            if(photoGalleryRadioButton.isSelected()) site = WebSiteFactory.createWebSite(WebSiteType.PHOTOS);
            else if(informationRadioButton.isSelected()) site = WebSiteFactory.createWebSite(WebSiteType.INFORMATION);
            else if(contactRadioButton.isSelected()) site = WebSiteFactory.createWebSite(WebSiteType.CONTACT);
            else site = WebSiteFactory.createWebSite(WebSiteType.NEWS);
            try{
                htmlTextArea.setText(site.getHtml());
            }catch(TransformerException e){
                Logger.getLogger(HTMLProducerFXMLController.class.getName()).log(Level.SEVERE, null, e);
            }
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

