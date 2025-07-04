public class Customer {
    private static int numberOfCustomers;
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        numberOfCustomers += 1;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }

    public void decreaseBalance(double amount) throws NotEnoughBalanceException {
        if (balance < amount) {
            throw new NotEnoughBalanceException("Not enough balance");
        }
        balance -= amount;
    }
}
