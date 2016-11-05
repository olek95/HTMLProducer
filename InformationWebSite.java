package htmlproducer;

public class InformationWebSite extends WebSite{
    public InformationWebSite(){
        super();
        titleElement.appendChild(htmlDocument.createTextNode("Strona informacyjna."));
    }
}
