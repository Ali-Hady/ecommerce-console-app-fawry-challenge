public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        Customer customer = new Customer("Ali", 1000);

        Cheese cheese = new Cheese("Mozzarella", 20.0, 5, 100);
        Biscuits biscuits = new Biscuits("One More", 10, 3, 50);
        Television tv = new Television("Samsung", 200, 1, 1500);
        Mobile mobile = new Mobile("iPhone", 500, 1, 200);
        MobileScratchCard scratchCard = new MobileScratchCard("Vodafone 50 EGP", 50, 5);

        try {
            cart.add(cheese, 2);
        } catch (OutOfStockException e) {
            System.out.println("Failed to add cheese: " + e.getMessage());
        }

        try {
            cart.add(biscuits, 2);
        } catch (OutOfStockException e) {
            System.out.println("Failed to add biscuits: " + e.getMessage());
        }

        try {
            cart.add(biscuits, 2); // Only 1 left
        } catch (OutOfStockException e) {
            System.out.println("Expected fail: " + e.getMessage());
        }

        try {
            cart.add(mobile, 1);
        } catch (OutOfStockException e) {
            System.out.println("Failed to add mobile: " + e.getMessage());
        }

        try {
            cart.add(scratchCard, 3);
        } catch (OutOfStockException e) {
            System.out.println("Failed to add scratch card: " + e.getMessage());
        }

        try {
            cart.checkout(customer);
        } catch (EmptyCartException | NotEnoughBalanceException e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }

        System.out.println("\n--- After First Checkout ---");
        System.out.println("Remaining balance: " + customer.getBalance());

        try {
            cart.checkout(customer);
        } catch (EmptyCartException | NotEnoughBalanceException e) {
            System.out.println("Expected fail (empty cart): " + e.getMessage());
        }

        try {
            cart.add(tv, 1);
        } catch (OutOfStockException e) {
            System.out.println("Failed to add TV: " + e.getMessage());
        }

        try {
            cart.checkout(customer);
        } catch (EmptyCartException | NotEnoughBalanceException e) {
            System.out.println("Expected fail (low balance): " + e.getMessage());
        }

        try {
            cart.add(cheese, 1);
            cart.checkout(customer);
        } catch (OutOfStockException | EmptyCartException | NotEnoughBalanceException e) {
            System.out.println("Second checkout failed: " + e.getMessage());
        }

        System.out.println("\n--- Final State ---");
        System.out.println("Remaining balance: " + customer.getBalance());
    }
}
