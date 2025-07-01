package Application;

public class TargetWebScraper extends WebScraper {
    public TargetWebScraper() {
        super(("https://www.target.com/"), ("#search"), ("div[cardcolumncount=\"0\"] h3[data-test=\"@web/ProductCard/ProductCardImage\"]"), ("h1#pdp-product-title-id"), ("span[class=\"sc-44e8b7a0-1 LjEZN\"]"), ("img[alt*='1 of']"));
    }

    public void scrape(){
        startScraping();
        for (int i = 0; i < num; i++) {
            findItem(i);
            getName();
            getUrl();
            getPrice();
            getImage();
            addToInv();
            repeat();
        }
        close();
    }

}
