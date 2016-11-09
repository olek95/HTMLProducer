package htmlproducer;

import static htmlproducer.WebSite.htmlDocument;
import java.util.Random;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ContactWebSite extends WebSite{
    public ContactWebSite(){
        super();
        Element bodyElement;
        titleElement.appendChild(htmlDocument.createTextNode("Galeria zdjęć."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        createField("Imię: ", bodyElement);
        createField("Nazwisko: ", bodyElement);
        createField("Miasto: ", bodyElement);
        createField("Kod pocztowy: ", bodyElement);
        createField("Nr telefonu: ", bodyElement);
    }
    private void createField(String name, Element bodyElement){
        Random rand = new Random(); 
        Element paragraphElement, boldElement;
        paragraphElement = htmlDocument.createElement("p");
        bodyElement.appendChild(paragraphElement);
        boldElement = htmlDocument.createElement("b");
        paragraphElement.appendChild(boldElement);
        boldElement.appendChild(htmlDocument.createTextNode(name));
        if(name.equals("Imię: ") || name.equals("Nazwisko: ") || name.equals("Miasto: "))
            paragraphElement.appendChild(generateText(26, 97, rand.nextInt(20) + 1));
        else if(name.equals("Kod pocztowy: "))
    }
    private Text generateText(int numberCharacters, int rangeStart, int textSize){
        Random rand = new Random();
        String text = "";
        for(int i = 0; i < textSize; i++)
            text += (char)(rand.nextInt(numberCharacters) + rangeStart);
        return htmlDocument.createTextNode(text);
    }
}
