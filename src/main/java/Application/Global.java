package Application;

import java.util.ArrayList;

public class Global {
    static ArrayList<Application.Item> inventory = new ArrayList<Item>();
    static ArrayList<WebScraper> listOfScrapers = new ArrayList<>();

    static {
        TargetWebScraper targetWebScraper = new TargetWebScraper();
        NikeWebScraper nikeWebScraper = new NikeWebScraper();
        listOfScrapers.add(targetWebScraper);
        listOfScrapers.add(nikeWebScraper);
    }
}
