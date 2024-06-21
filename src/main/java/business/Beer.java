package business;

public class Beer extends Item {
    public enum BeerType {
        BELGIUM, DUTCH, GERMAN
    }

    private BeerType type;
    private int quantity;

    public Beer(String name, double price, BeerType type, int quantity) {
        super(name, price);
        this.type = type;
        this.quantity = quantity;
    }

    public BeerType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getTotalPrice() {
        double discount = 0.0;
        if (quantity >= 6) {
            switch (type) {
                case BELGIUM:
                    discount = 3.0;
                    break;
                case DUTCH:
                    discount = 2.0;
                    break;
                case GERMAN:
                    discount = 4.0;
                    break;
            }
            return price * quantity - discount;
        }
        return price * quantity;
    }
}
