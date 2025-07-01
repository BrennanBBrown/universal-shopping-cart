package Application;

public class NikeWebScraper extends WebScraper{

    public NikeWebScraper(){
        super("https://www.nike.com/","input[type=\"search\"]", "div[data-testid=\"product-card\"]","h1[data-testid=\"product_title\"]","[data-testid='currentPrice-container']","[data-testid='HeroImg']");
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
