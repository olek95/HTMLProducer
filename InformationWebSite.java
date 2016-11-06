package htmlproducer;

import java.util.Random;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class InformationWebSite extends WebSite{
    private String[] availableTags = {"h", "p"};
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
            text = generateText();
            bodyElement.appendChild(tagElement);
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
}
