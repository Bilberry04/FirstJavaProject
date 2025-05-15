package DrinksMachine;

public class MachineDrinks {
    int drinkID;
    String drinkName;
    double drinkPrice;
    int drinkQuantity;

    public MachineDrinks(int drinkID, String drinkName, double drinkPrice, int drinkQuantity) {
        this.drinkID = drinkID;
        this.drinkName = drinkName;
        this.drinkPrice = drinkPrice;
        this.drinkQuantity = drinkQuantity;
    }

    public void display() {
        System.out.printf("[%d] %s - %.2f$ (avaiable: %d)%n", drinkID, drinkName, drinkPrice, drinkQuantity);
    }

    public int getdrinkId() {
        return drinkID;
    }

    public int getdrinkQuantity() {
        return drinkQuantity;
    }

    public void setdrinkQuantity(int drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }

    public String getdrinkName() {
        return drinkName;
    }

    public double getdrinkPrice() {
        return drinkPrice;
    }
}
