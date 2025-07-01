package Application;

import com.microsoft.playwright.*;


public abstract class WebScraper implements Runnable{
    String itemName;
    String itemPrice;
    String itemUrl;
    String itemImage;
    String website;
    String searchBarFinder;
    String itemLocatorFinder;
    String itemNameFinder;
    String itemPriceFinder;
    String itemImageFinder;
    String trackItem;
    int num;

    Playwright playwright;
    Browser browser;
    Page page;

    public WebScraper(String website, String searchBarFinder, String itemLocatorFinder, String itemNameFinder, String itemPriceFinder, String itemImageFinder) {
        this.website = website;
        this.searchBarFinder = searchBarFinder;
        this.itemLocatorFinder = itemLocatorFinder;
        this.itemNameFinder = itemNameFinder;
        this.itemPriceFinder = itemPriceFinder;
        this.itemImageFinder = itemImageFinder;
    }

    public void startScraping() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(website);
        //
        String searchclick = searchBarFinder;
        page.click(searchclick);
        page.locator(searchclick).first().type(trackItem, new Locator.TypeOptions().setDelay(100));
        page.locator(searchclick).press("Enter");
        page.waitForLoadState();
        page.waitForSelector(searchclick);
    }

    public void findItem(int i) {
        page.waitForSelector(itemLocatorFinder); // waits for grid to reload
        Locator itemLocator = page.locator(itemLocatorFinder).nth(i);
        itemLocator.scrollIntoViewIfNeeded();
        itemLocator.click();
    }

    public void getName() {
        page.waitForSelector(itemNameFinder);
        Locator itemNameLocator = page.locator(itemNameFinder);
        itemName = itemNameLocator.textContent().trim();
    }

    public void getUrl() {
        itemUrl = page.url();
    }

    public void getPrice() {
        page.waitForSelector(itemPriceFinder);
        Locator itemPriceLocator = page.locator(itemPriceFinder);
        itemPrice = itemPriceLocator.first().textContent().trim();
    }

    public void getImage() {
        Locator itemImageLocator = page.locator(itemImageFinder);
        itemImage = itemImageLocator.getAttribute("src");
    }

    public void addToInv() {
        Item item = new Item(itemName, itemPrice, itemUrl, itemImage);
        Global.inventory.add(item);

    }

    public void repeat() {
        page.goBack();
    }

    public void close() {
        browser.close();
    }

    public void setTrackItem(String trackItem){
        this.trackItem = trackItem;
    }

    public void setNum(int num){
        this.num = num;
    }

    public abstract void scrape();

    public void run(){
        scrape();
    }

}
