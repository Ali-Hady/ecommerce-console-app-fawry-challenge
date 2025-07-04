import java.util.Objects;

public abstract class Product {
    private final String name;
    private double price;
    private int quantity = 0;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity(int quantity) throws OutOfStockException {
        if (this.quantity < quantity) {
            throw new OutOfStockException("Not Enough Quantity");
        }

        this.quantity -= quantity;
    }

    public void incrementQuantity(int quantity) {
        this.quantity += quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return false;

    }
}
