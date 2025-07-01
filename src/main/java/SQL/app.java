package SQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class app implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    public static void main(String[] args) {
        SpringApplication.run(app.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Item item = new Item("Cat", "30", "http://example.com", "image.jpg");
        System.out.println("Item before save:\n" + item.toString());

        Item savedItem = itemRepository.save(item);

        System.out.println("Saved Item ID: " + savedItem.getId());

        Optional<Item> foundItem = itemRepository.findById(savedItem.getId());
        foundItem.ifPresentOrElse(
                i -> System.out.println("Found item from DB:\n" + i.toString()),
                () -> System.out.println("Item not found!")
        );
    }
}