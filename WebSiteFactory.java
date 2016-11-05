package htmlproducer;

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
