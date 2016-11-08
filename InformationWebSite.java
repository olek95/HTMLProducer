package htmlproducer;

import java.util.Random;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class InformationWebSite extends WebSite{
    private String[] availableTags = {"h", "p"};
    private String[] availableAttributes = {"title", "color", "font-size"};
    private String[] availableFormattingElements = {"b", "strong", "i", "em", "mark"};
    public InformationWebSite(){
        super();
        Element bodyElement, tagElement;
        Text text;
        int pageSize;
        Random rand = new Random();
        titleElement.appendChild(htmlDocument.createTextNode("Strona informacyjna."));
        bodyElement = htmlDocument.createElement("body");
        rootElement.appendChild(bodyElement);
        pageSize = rand.nextInt(100) + 1; 
        String tag;
        for(int i = 0; i < pageSize; i++){
            tagElement = getTag();
            addAttributes(tagElement);
            //Element formattingElement = formatElement(tagElement);
            text = generateText();
            bodyElement.appendChild(tagElement);
            //if(formattingElement != null) tagElement.appendChild(text);
            //else formattingElement.appendChild(text);
            tagElement.appendChild(text);
        }
    }
    private Element getTag(){
        Random rand = new Random();
        String tag = availableTags[rand.nextInt(availableTags.length)];
        if(tag.equals("h")) tag += rand.nextInt(6) + 1;
        return htmlDocument.createElement(tag);
    }
    private Text generateText(){
        Random rand = new Random();
        String text = "";
        int textSize = rand.nextInt(50) + 1; 
        for(int i = 0; i < textSize; i++)
            text += (char)(rand.nextInt(94) + 32);
        return htmlDocument.createTextNode(text);
    }
    private void addAttributes(Element el){
        Random rand = new Random();
        int attributesNumber = rand.nextInt(availableAttributes.length + 1);
        String attribute;
        if(attributesNumber != 0)
            for(int i = 0; i < attributesNumber; i++){
                attribute = availableAttributes[rand.nextInt(availableAttributes.length)];
                if(el.getAttribute(attribute).equals("")){
                    if(attribute.equals("title")) el.setAttribute(attribute, generateText().getTextContent());
                    else if(attribute.equals("color")) el.setAttribute("style", attribute + ":rgb(" + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + "," + (rand.nextInt(255)+1) + ")");
                }else i--;
            }
    }
    private Element formatElement(Element el){
        Random rand = new Random(); 
        Element tempElement = null;
        int elementsNumber = rand.nextInt(availableFormattingElements.length + 1);
        int[] usedIndexes = new int[availableFormattingElements.length];
        System.out.println(usedIndexes[0]);
        if(elementsNumber != 0)
            for(int i = 0; i < elementsNumber; i++){
                int index = rand.nextInt(availableFormattingElements.length);
                if(usedIndexes[index] != 1){
                    tempElement = htmlDocument.createElement(availableFormattingElements[index]);
                    el.appendChild(tempElement);
                }
                else i--;
            }
        return tempElement;
    }
}
