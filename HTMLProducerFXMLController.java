package htmlproducer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
       saveMenuItem.setOnAction((event) ->{
           FileChooser fileChooser = new FileChooser(); 
           fileChooser.setTitle("Zapis pliku HTML");
           fileChooser.getExtensionFilters().add(new ExtensionFilter("Pliki HTML", "*.html", "*.htm"));
           File selectedFile = fileChooser.showSaveDialog(null);
           if(selectedFile != null) writeToFile(htmlTextArea.getText());
       });
    }    
    
}
