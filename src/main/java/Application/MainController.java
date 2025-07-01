package Application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
    @GetMapping("/")
    public String searchItem (Model model) {
        return "searchItemPage";
    }

    @GetMapping("/search")
    public String handleSearch (@RequestParam String query,@RequestParam int nums[], Model model){
        ArrayList<Thread> webThreads = new ArrayList<>();
        for(int i = 0; i < Global.listOfScrapers.size(); i++){
            for(int num: nums){
                if(i == num){
                    Global.listOfScrapers.get(i).setTrackItem(query);
                    Global.listOfScrapers.get(i).setNum(2);
                    Thread thread = new Thread(Global.listOfScrapers.get(i));
                    webThreads.add(thread);
                    thread.start();
                }
            }
        }

        try {
            for(Thread threads: webThreads){
                threads.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted: " + e.getMessage());
        }

        for (Item item : Global.inventory){
            System.out.println(item.toString());
        }

        model.addAttribute("items", Global.inventory);

        return "searchResults";
    }
}
