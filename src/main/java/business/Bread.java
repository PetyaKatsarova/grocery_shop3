package business;

public class Bread extends Item {
    private int age;

    public Bread(String name, double price, int age) {
        super(name, price);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

//    TODO: divisable /3, /2 etc....
    @Override
    public double getTotalPrice() {
        if (age <= 1) {
            return price;
        } else if (age == 3) {
            return price / 2;
        } else if (age == 6) {
            return price / 3;
        } else {
            throw new IllegalArgumentException("Bread older than 6 days cannot be added.");
        }
    }
}

