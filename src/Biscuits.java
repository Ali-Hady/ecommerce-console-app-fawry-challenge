import java.time.LocalDate;

public class Biscuits extends Product implements Expires, Shipping {
    private LocalDate expirationDate;
    private double weight;

    public Biscuits(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public Biscuits(String name, double price, int quantity, double weight) {
        this(name, price, quantity);
        this.expirationDate = LocalDate.now();
        setExpirationDateByDays(10);
        this.weight = weight;
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }
}
