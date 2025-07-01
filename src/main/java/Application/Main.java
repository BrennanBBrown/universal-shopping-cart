package Application;

public class Main {
    public static void main(String args[]){
        TargetWebScraper targetWebScraper = new TargetWebScraper();
        NikeWebScraper nikeWebScraper = new NikeWebScraper();
        targetWebScraper.setTrackItem("pants");
        targetWebScraper.setNum(2);
        nikeWebScraper.setTrackItem("pants");
        nikeWebScraper.setNum(2);

        Thread tThread = new Thread(targetWebScraper);
        Thread nThread = new Thread(nikeWebScraper);

        nThread.start();
        tThread.start();

        try {
            tThread.join(); // Wait for the targetThread to finish
            nThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }

        for (Item item : Global.inventory){
            System.out.println(item.toString());
        }
    }
}
