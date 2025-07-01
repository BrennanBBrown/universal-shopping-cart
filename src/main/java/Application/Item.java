package Application;

public class Item {
    private String name;
    private String price;
    private String url;
    private String image;

    public Item(String name, String price, String url, String image) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.image = image;
    }
    @Override
    public String toString() {
        String returnI = "Name: " + name + "\nPrice: " + price + "\nURL: " + url + "\nImage: " + image;
        return returnI;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}

