package htmlproducer;

import org.w3c.dom.Element;

/**
 * Obiekt klasy <code>ContactWebSite</code> reprezentuje kontaktową stronę 
 * internetową. Zawiera ona takie dane kontaktowe jak: imię, nazwisko, miasto, 
 * ulica, kod pocztowy i numer telefonu. Wszystkie te dane są wygenerowane losowo, 
 * przestrzegając jednocześnie odpowiedniego dla nich formatu np. nr telefonu 9 cyfrowy. 
 * @author AleksanderSklorz
 */
public class ContactWebSite extends WebSite{
    public ContactWebSite(){
        super();
        Element bodyElement;
        titleElement.appendChild(htmlDocument.createTextNode("Strona kontaktowa."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        createField("Imię: ", bodyElement);
        createField("Nazwisko: ", bodyElement);
        createField("Miasto: ", bodyElement);
        createField("Ulica: ", bodyElement);
        createField("Kod pocztowy: ", bodyElement);
        createField("Nr telefonu: ", bodyElement);
    }
    private void createField(String name, Element bodyElement){
        Element paragraphElement, boldElement;
        paragraphElement = htmlDocument.createElement("p");
        bodyElement.appendChild(paragraphElement);
        boldElement = htmlDocument.createElement("b");
        paragraphElement.appendChild(boldElement);
        boldElement.appendChild(htmlDocument.createTextNode(name));
        if(name.equals("Imię: ") || name.equals("Nazwisko: ") || name.equals("Miasto: "))
            paragraphElement.appendChild(htmlDocument.createTextNode(generateText(26, 97, rand.nextInt(20) + 1)));
        else{
            if(name.equals("Ulica: ")){
                paragraphElement.appendChild(htmlDocument
                        .createTextNode(generateText(26, 97, rand.nextInt(20) + 1) + " " + generateText(10, 48, rand.nextInt(2) + 1) + "/" + generateText(10, 48, rand.nextInt(2) + 1)));
            }else{ 
                if(name.equals("Kod pocztowy: ")){
                    paragraphElement.appendChild(htmlDocument.createTextNode(generateText(10, 48, 2) + "-" + generateText(10, 48, 3)));
                }else paragraphElement.appendChild(htmlDocument.createTextNode(generateText(10, 48, 9)));
            }
        }
    }
}


