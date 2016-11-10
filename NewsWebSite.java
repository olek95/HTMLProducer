package htmlproducer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Obiekt klasy <code>NewsWebSite</code> reprezentuje stronę internetową typu 
 * aktualności. Składa się ona z losowo wygenerowanego tekstu. W odróżnieniu 
 * od klasy InformationWebSite, losowo wygenerowany tekst jest oddzielany przez
 * losowo wygenerowane wyśrodkowane nagłówki, symbolizujące różnego rodzaju wiadomości. 
 * @author AleksanderSklorz
 */
public class NewsWebSite extends WebSite{
    private final static String[] AVAILABLE_ATTRIBUTES = {"title", "style"};
    private final static String[] AVAILABLE_FORMATTING_ELEMENTS = {"b", "strong", "i", "em", "mark"}; 
    public NewsWebSite(){
        super(); 
        Element bodyElement, headingElement, paragraphElement;
        Text text;
        int headingsNumber, paragraphsNumber;
        titleElement.appendChild(htmlDocument.createTextNode("Aktualności."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        headingsNumber = rand.nextInt(20) + 1; 
        String tag;
        for(int i = 0; i < headingsNumber; i++){
            headingElement = htmlDocument.createElement("h1");
            headingElement.setAttribute("style", "text-align:center");
            headingElement.appendChild(htmlDocument.createTextNode(generateText(26, 97, rand.nextInt(25) + 1)));
            bodyElement.appendChild(headingElement);
            paragraphsNumber = rand.nextInt(100) + 1;
            for(int k = 0; k < paragraphsNumber; k++){
                paragraphElement = htmlDocument.createElement("p");
                Element formattingElement = formatElement(paragraphElement);
                addAttributes(paragraphElement);
                text = htmlDocument.createTextNode(generateText(94, 32, rand.nextInt(50) + 1));
                bodyElement.appendChild(paragraphElement);
                if(formattingElement == null) paragraphElement.appendChild(text);
                else formattingElement.appendChild(text);
            }
        }
    }
    private void addAttributes(Element el){
        int attributesNumber = rand.nextInt(AVAILABLE_ATTRIBUTES.length + 1);
        String attribute;
        if(attributesNumber != 0)
            for(int i = 0; i < attributesNumber; i++){
                attribute = AVAILABLE_ATTRIBUTES[rand.nextInt(AVAILABLE_ATTRIBUTES.length)];
                if(el.getAttribute(attribute).equals("")){
                    if(attribute.equals("title")) el.setAttribute(attribute, generateText(94, 32, rand.nextInt(50) + 1));
                    else if(attribute.equals("style") && !isMarked(el)){
                        el.setAttribute(attribute, "color:rgb(" + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + ")");
                    }
                }else i--;
            }
    }
    private Element formatElement(Element el){
        Element tempElement = null;
        int elementsNumber = rand.nextInt(AVAILABLE_FORMATTING_ELEMENTS.length + 1);
        int[] usedIndexes = new int[AVAILABLE_FORMATTING_ELEMENTS.length];
        if(elementsNumber != 0)
            for(int i = 0; i < elementsNumber; i++){
                int index = rand.nextInt(AVAILABLE_FORMATTING_ELEMENTS.length);
                if(usedIndexes[index] != 1){
                    tempElement = htmlDocument.createElement(AVAILABLE_FORMATTING_ELEMENTS[index]);
                    el.appendChild(tempElement);
                    el = tempElement;
                    usedIndexes[index] = 1;
                }
                else i--;
            }
        return tempElement;
    }
    private boolean isMarked(Element el){
        NodeList list = el.getChildNodes(); 
        int size = list.getLength(); 
        for(int i = 0; i < size; i++){
            Node n = list.item(i);
            if(n.getNodeName().equals("mark") || isMarked((Element)n)) return true;
        }
        return false;
    }
}

