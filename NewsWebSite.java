package htmlproducer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class NewsWebSite extends WebSite{
    private String[] availableAttributes = {"title", "style"};
    private String[] availableFormattingElements = {"b", "strong", "i", "em", "mark"};
    public NewsWebSite(){
        super(); 
        Element bodyElement, headingElement, paragraphElement;
        Text text;
        int pageSize;
        titleElement.appendChild(htmlDocument.createTextNode("Aktualności."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        pageSize = rand.nextInt(100) + 1; 
        String tag;
        for(int i = 0; i < pageSize; i++){
            headingElement = htmlDocument.createElement("h1");
            headingElement.setAttribute("style", "text-align:center");
            headingElement.appendChild(htmlDocument.createTextNode(generateText(26, 97, rand.nextInt(25) + 1)));
            bodyElement.appendChild(headingElement);
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
        String attribute;
        if(attributesNumber != 0)
            for(int i = 0; i < attributesNumber; i++){
                attribute = availableAttributes[rand.nextInt(availableAttributes.length)];
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

