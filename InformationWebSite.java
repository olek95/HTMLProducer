package htmlproducer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Obiekt klasy <code>InformationWebSite</code> reprezentuje informacyjną stronę
 * internetową. Zawiera ona wiele linii losowo wygenerowanego tekstu, umieszczonego 
 * w losowym położeniu i losowym formacie/stylu. 
 * @author AleksanderSklorz
 */
public class InformationWebSite extends WebSite{
    private String[] availableAttributes = {"title", "style"};
    private String[] availableFormattingElements = {"b", "strong", "i", "em", "mark"};
    public InformationWebSite(){
        super();
        Element bodyElement, paragraphElement;
        Text text;
        int pageSize;
        titleElement.appendChild(htmlDocument.createTextNode("Strona informacyjna."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        pageSize = rand.nextInt(100) + 1; 
        for(int i = 0; i < pageSize; i++){
            paragraphElement = htmlDocument.createElement("p");
            Element formattingElement = formatElement(paragraphElement);
            addAttributes(paragraphElement);
            text = htmlDocument.createTextNode(generateText(94, 32, rand.nextInt(50) + 1));
            bodyElement.appendChild(paragraphElement);
            if(formattingElement == null) paragraphElement.appendChild(text);
            else formattingElement.appendChild(text);
        }
    }
    private void addAttributes(Element el){
        int attributesNumber = rand.nextInt(availableAttributes.length + 1);
        int option;
        String attribute;
        if(attributesNumber != 0)
            for(int i = 0; i < attributesNumber; i++){
                attribute = availableAttributes[rand.nextInt(availableAttributes.length)];
                if(el.getAttribute(attribute).equals("")){
                    if(attribute.equals("title")) el.setAttribute(attribute, generateText(94, 32, rand.nextInt(50) + 1));
                    else if(attribute.equals("style")){
                        int range;
                        if(isMarked(el)) range = 2;
                        else range = 3;
                        if((option = rand.nextInt(range)) == 0)
                            el.setAttribute(attribute, "font-size:" + (rand.nextInt(1000) + 1) + "%"); // zakładam że nie moze być więcej niż 1000 procent
                        else if(option == 1){
                            String[] positions = {"left", "center", "right"};
                            el.setAttribute(attribute, "text-align:" + positions[rand.nextInt(positions.length)]);
                        }else 
                            el.setAttribute(attribute, "color:rgb(" + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + ")");
                    }
                }else i--;
            }
    }
    private Element formatElement(Element el){
        Element tempElement = null;
        int elementsNumber = rand.nextInt(availableFormattingElements.length + 1);
        int[] usedIndexes = new int[availableFormattingElements.length];
        if(elementsNumber != 0)
            for(int i = 0; i < elementsNumber; i++){
                int index = rand.nextInt(availableFormattingElements.length);
                if(usedIndexes[index] != 1){
                    tempElement = htmlDocument.createElement(availableFormattingElements[index]);
                    el.appendChild(tempElement);
                    el = tempElement;
                    usedIndexes[index] = 1;
                }
                else i--;
            }
        return tempElement;
    }
    private boolean isMarked(Element el){ // metoda sprawdzająca czy tekst jest oznaczony, ponieważ oznaczony tekst nie może zmienić koloru 
        NodeList list = el.getChildNodes(); 
        int size = list.getLength(); 
        for(int i = 0; i < size; i++){
            Node n = list.item(i);
            if(n.getNodeName().equals("mark") || isMarked((Element)n)) return true;
        }
        return false;
    }
}
