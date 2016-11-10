package htmlproducer;

/**
 * Klasa <code>WebSiteFactory</code> reprezentuje fabrykę tworzącą różnego typu 
 * strony internetowe. Móżliwe typy to galeria zdjeć, strona informacyjna, strona 
 * kontaktowa i aktualności. 
 * @author AleksanderSklorz
 */
public class WebSiteFactory {
    /**
     * Tworzy stronę internetową danego typu. Jeśli taki typ nie istnieje, zwraca null. 
     * @param type typ strony internetowej (PHOTOS, INFORMATION, CONTACT, NEWS)
     * @return dany typ strony internetowej lub null, jeśli taki typ nie istnieje. 
     */
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
