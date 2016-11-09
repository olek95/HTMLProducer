package htmlproducer;

import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import org.w3c.dom.Element;

public class PhotoGalleryWebSite extends WebSite{
    private String[] availableAttributes = {"title", "width", "height"};
    public PhotoGalleryWebSite(){
        super(); 
        Element bodyElement;
        titleElement.appendChild(htmlDocument.createTextNode("Galeria zdjęć."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG (*.jpg;*.jpeg;*.jpe;*.jfif)", "*.jpg", "*.jpeg", "*.jpe", "*.jfif"),
                new FileChooser.ExtensionFilter("PNG (*.png)", "*.png"), new FileChooser.ExtensionFilter("GIF (*.gif)", "*.gif"));
        fileChooser.setTitle("Wybór obrazów.");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        for(File f : selectedFiles){
            bodyElement.appendChild(generatePhoto(f.getPath()));
        }
    }
    private Element generatePhoto(String path){
        Element imgElement = htmlDocument.createElement("img");
        imgElement.setAttribute("src", path);
        addAttributes(imgElement);
        return imgElement;
    }
    private void addAttributes(Element el){
        int attributesNumber = rand.nextInt(availableAttributes.length + 1);
        String attribute; 
        for(int i = 0; i < attributesNumber; i++){
            attribute = availableAttributes[rand.nextInt(availableAttributes.length)];
            if(el.getAttribute(attribute).equals("")){
                if(attribute.equals("title")) el.setAttribute(attribute, generateText(94, 32, rand.nextInt(50) + 1));
                else el.setAttribute(attribute, (rand.nextInt(500) + 1) + "");
            }
        }
    }
}
