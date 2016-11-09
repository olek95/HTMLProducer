package htmlproducer;

import java.io.ByteArrayOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Klasa <code>WebSite</code> reprezentuje stronę internetową w sensie ogólnym. 
 * Zawiera pola i metody związane z tworzeniem struktury kodu HTML oraz wspomagające 
 * pisanie kodu klas podrzędnych. 
 * @author AleksanderSklorz
 */
public abstract class WebSite {
    protected Document htmlDocument;
    protected Element rootElement;
    protected Element titleElement;
    protected Random rand = new Random(); // dodane w celu optymalizacji, gdyż obiekt ten jest często wykorzystywany i aby nie był ciągle na nowo tworzony
    public WebSite(){
        try{
            htmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            rootElement = htmlDocument.createElement("html");
            htmlDocument.appendChild(rootElement);
            Element headElement = htmlDocument.createElement("head");
            rootElement.appendChild(headElement);
            titleElement = htmlDocument.createElement("title");
            headElement.appendChild(titleElement);
        }catch(ParserConfigurationException e){
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public String getHtml() throws TransformerException{
        Transformer htmlTransformer = TransformerFactory.newInstance().newTransformer(); 
        htmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        htmlTransformer.setOutputProperty(OutputKeys.METHOD, "html");
        htmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        htmlTransformer.transform(new DOMSource(htmlDocument), new StreamResult(baos));
        return baos.toString();
    }
    protected String generateText(int numberCharacters, int rangeStart, int textSize){
        String text = "";
        for(int i = 0; i < textSize; i++)
            text += (char)(rand.nextInt(numberCharacters) + rangeStart);
        return text;
    }
}

