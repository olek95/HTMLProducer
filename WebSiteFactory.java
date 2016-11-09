package htmlproducer;

/**
 * Klasa <code>WebSiteFactory</code> reprezentuje fabrykę tworzącą różnego typu 
 * strony internetowe. Móżliwe typy to galeria zdjeć, strona informacyjna, strona 
 * kontaktowa i aktualności. 
 * @author AleksanderSklorz
 */
public class WebSiteFactory {
    public static WebSite createWebSite(WebSiteType type){
        if(WebSiteType.PHOTOS.equals(type)){
            return new PhotoGalleryWebSite(); 
        }
        if(WebSiteType.INFORMATION.equals(type)){
            return new InformationWebSite();
        }
        if(WebSiteType.CONTACT.equals(type)){
            return new ContactWebSite();
        }
        if(WebSiteType.NEWS.equals(type)){
            return new NewsWebSite();
        }
        return null;
    }
}
