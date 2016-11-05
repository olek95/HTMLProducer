package htmlproducer;

import java.io.ByteArrayOutputStream;
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
import org.w3c.dom.Text;

public class WebSite {
    private Document htmlDocument;
    public WebSite(){
        try{
            Element rootElement, titleElement;
            Text titleNode;
            htmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            rootElement = htmlDocument.createElement("html");
            titleElement = htmlDocument.createElement("title");
            titleNode = htmlDocument.createTextNode("Jakiś tytuł");
            htmlDocument.appendChild(rootElement);
            rootElement.appendChild(titleElement);
            titleElement.appendChild(titleNode);
        }catch(ParserConfigurationException e){
            Logger.getLogger(WebSite.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public String getWebSiteContent() throws TransformerException{
        Transformer htmlTransformer = TransformerFactory.newInstance().newTransformer(); 
        htmlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
        htmlTransformer.setOutputProperty(OutputKeys.METHOD, "html");
        htmlTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        htmlTransformer.transform(new DOMSource(htmlDocument), new StreamResult(baos));
        return baos.toString();
    }
}
