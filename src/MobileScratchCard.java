public class MobileScratchCard extends Product {
    private String hiddenNumber;
    private boolean hidden = true;

    public MobileScratchCard(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public MobileScratchCard(String name, double price, int quantity, String hiddenNumber) {
        this(name, price, quantity);
        this.hiddenNumber = hiddenNumber;
    }

    public void ScratchCard() {
        this.hidden = false;
    }

    String getHiddenNumber() {
        if (!hidden) {
            return hiddenNumber;
        }

        return "Card is not scratched";
    }
}

