package htmlproducer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.xml.transform.TransformerException;

/**
 * Klasa <code>HTMLProducerFXMLController</code> reprezentuje działanie okna 
 * generującego kod HTML. Zawiera przełączniki zmieniające typ strony internetowej,
 * obszar wyświetlający kod, a także możliwość tworzenia/zmiany kodu HTML wraz z 
 * możliwością zapisu i odczytu pliku. Ostrzega przed zamknięciem okna lub wczytaniu 
 * nowego kodu z pliku, jeśli aktualny kod nie został zapisany. 
 * @author AleksanderSklorz
 */
public class HTMLProducerFXMLController implements Initializable {
    private static boolean saved = true;
    @FXML
    private Button generateButton;
    @FXML
    private MenuItem saveMenuItem, openMenuItem, aboutMenuItem, exitMenuItem;
    @FXML
    private TextArea htmlTextArea;
    @FXML
    private RadioButton photoGalleryRadioButton, informationRadioButton, contactRadioButton, newsRadioButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       generateButton.setOnAction(event -> {
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
       saveMenuItem.setOnAction(event -> {
           FileChooser fileChooser = new FileChooser(); 
           fileChooser.setTitle("Zapis plików HTML");
           fileChooser.getExtensionFilters().add(new ExtensionFilter("Pliki HTML", "*.html", "*.htm"));
           File selectedFile = fileChooser.showSaveDialog(null);
           if(selectedFile != null) writeToFile(selectedFile.getPath(), htmlTextArea.getText());
       });
       openMenuItem.setOnAction(event -> {
            if((!saved && showWarning()) || saved){
                FileChooser fileChooser = new FileChooser(); 
                fileChooser.setTitle("Odczyt plików HTML");
                fileChooser.getExtensionFilters().add(new ExtensionFilter("Pliki HTML", "*.html", "*.htm"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if(selectedFile != null) openFile(selectedFile.getPath());
            }
       });
       htmlTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
           saved = false;
       });
       aboutMenuItem.setOnAction(event -> {
           Alert aboutAlert = new Alert(AlertType.INFORMATION);
           aboutAlert.setTitle("O programie");
           aboutAlert.setHeaderText("Jest to program generujący losowy kod HTML dla strony\n"
                   + "internetowej danego typu. Na początku należy wybrać typ strony, a następnie\n"
                   + "ją wygenerować przyciskiem Generuj stronę. Jeśli wygenerowana strona\n"
                   + "się nie podoba, można wygenerować nową lub zmienić jej zawartość w obszarze tekstowym.\n"
                   + "W przypadku Galerii zdjęć, należy wybrać dodatkowo jeden albo kilka obrazów, które mają zostać\n"
                   + "umieszczone. Kod można zapisać i odczytać w menu File.");
           aboutAlert.showAndWait();
       });
       exitMenuItem.setOnAction(event -> {
           if((!saved && showWarning()) || saved) System.exit(0);
       });
    }    
    /**
     * Ostrzega przed zamknięciem okna lub przed wczytaniem nowego kodu z pliku, 
     * w przypadku gdy aktualny kod nie został zapisany. 
     * @return true jeśli potwierdzono zamknięcie okna, false w przeciwnym przypadku. 
     */
    public static boolean showWarning(){
        Alert aboutAlert = new Alert(AlertType.WARNING, "Nie zapisałeś aktualnego kodu! Czy jesteś pewny decyzji?", ButtonType.YES, ButtonType.NO);
        aboutAlert.setTitle("Ostrzeżenie");
        Optional<ButtonType> result = aboutAlert.showAndWait(); 
        return result.isPresent() && result.get().equals(ButtonType.YES);
    }
    /**
     * Zwraca informację czy aktualny kod jest zapisany. 
     * @return true jeśli kod jest zapisany, false w przeciwnym przypadku. 
     */
    public static boolean getSaved(){
        return saved;
    }
    private void writeToFile(String path, String html){
        try{
            Files.write(Paths.get(path), html.getBytes());
            saved = true;
        }catch(IOException e){
            Logger.getLogger(HTMLProducerFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void openFile(String path){
        try{
            htmlTextArea.setText(new String(Files.readAllBytes(Paths.get(path))));
            saved = true;
        }catch(IOException e){
            Logger.getLogger(HTMLProducerFXMLController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
