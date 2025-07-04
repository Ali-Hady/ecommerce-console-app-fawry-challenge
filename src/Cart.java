import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products;

    public Cart() {
        this.products = new HashMap<>();
    }

    public void add(Product product, int quantity) throws OutOfStockException {
        if (quantity == 0) {
            return;
        }

        if (product.getQuantity() == 0) {
            String message = "Product " + product.getName() + " is out of stock";
            throw new OutOfStockException(message);
        }
        else if (product.getQuantity() < quantity) {
            String message = "Product " + product.getName() + " has only " + product.getQuantity() + " left";
            throw new OutOfStockException(message);
        }
        else {
            if (this.products.containsKey(product)) {
                int oldQuantity = products.get(product);
                int newQuantity = oldQuantity + quantity;
                products.put(product, newQuantity);
            }
            else {
                this.products.put(product, quantity);
            }
        }
    }

    public void emptyCart() {
        this.products.clear();
    }

    public void printCart() {
        if (this.products.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(quantity + "x " + product.getName());
        }
    }

    private void modify() {
        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            try {
                product.decrementQuantity(quantity);
            }
            catch (OutOfStockException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void service() {
        ShippingService shippingService = new ShippingService();
        ArrayList<ShippedProduct> productsToBeShipped = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (!(product instanceof  Shipping)) {
                continue;
            }

            ShippedProduct shippedProduct = new ShippedProduct(product.getName(), product.getPrice(), quantity);
            productsToBeShipped.add(shippedProduct);
        }

        shippingService.serve(productsToBeShipped);
    }

    public void checkout(Customer customer) throws NotEnoughBalanceException, EmptyCartException {
        if (products.isEmpty()) {
            throw new EmptyCartException("No products added to cart");
        }

        String shipmentNotice = "";
        String checkoutReceipt = "";
        double balance = customer.getBalance();
        double accumulatedPrice = 0, totalWeight = 0;

        for (Map.Entry<Product, Integer> entry : this.products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            if (product instanceof Shipping) {
                double weight = ((Shipping) product).getWeight();
                String weightDisplay = weight >= 1000.0
                        ? String.format("%.2fkg", weight / 1000.0)
                        : String.format("%.0fg", weight);

                shipmentNotice += quantity + "x " + product.getName() + "\t" + weightDisplay + "\n";
                totalWeight += weight * quantity;
            }

            double totalPrice = product.getPrice() * quantity;
            checkoutReceipt += quantity + "x " + product.getName() + "\t" + totalPrice + "\n";
            accumulatedPrice += totalPrice;
        }

        double shippingFee = 0;
        if (totalWeight <= 500.0) {
            shippingFee = 15.0;
        }
        else if (totalWeight <= 1000.0) {
            shippingFee = 30.0;
        }
        else {
            shippingFee = 50.0;
        }

        double finalPrice = accumulatedPrice + shippingFee;

        if (finalPrice > balance) {
            throw new NotEnoughBalanceException("Not enough balance");
        }

        if (!shipmentNotice.equals("")) {
            System.out.println("** Shipment notice **");
            System.out.println(shipmentNotice);
            String weightDisplay = totalWeight >= 1000.0
                    ? String.format("%.2fkg", totalWeight / 1000.0)
                    : String.format("%.0fg", totalWeight);
            System.out.println("Total Package Weight " + weightDisplay + '\n');
        }

        customer.decreaseBalance(finalPrice);
        System.out.println("** Checkout receipt **");
        System.out.println(checkoutReceipt);
        System.out.println("--------------------------------------------------------------");
        System.out.println("Subtotal" + "\t" + accumulatedPrice);
        System.out.println("Shipping" + "\t" + shippingFee);
        System.out.println("Amount" + "\t" + finalPrice);
        System.out.println("--------------------------------------------------------------");
        System.out.println(customer.getBalance() + "\n\n");

        modify(); // to decrement the quantities bought
        service();
        products.clear(); // to clear the cart
    }
}
