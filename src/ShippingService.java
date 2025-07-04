import java.util.ArrayList;

public class ShippingService {
    ArrayList<ShippedProduct> productsToBeShipped;

    public ShippingService() {
        productsToBeShipped = new ArrayList<>();
    }

    void serve(ArrayList<ShippedProduct> products) {
        productsToBeShipped = products;
    }
}
