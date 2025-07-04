public class Mobile extends Product implements Shipping {
    private double weight;

    public Mobile(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public Mobile(String name, double price, int quantity, double weight) {
        this(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
