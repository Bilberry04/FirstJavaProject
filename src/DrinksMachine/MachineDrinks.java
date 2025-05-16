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

    public int getdrinkID() {
        return drinkID;
    }

    public int getdrinkQuantity() {
        return drinkQuantity;
    }

    public void setdrinkQuantity( int drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }

    public String getdrinkName() {
        return drinkName;
    }

    public void setName(String newProductName) {this.drinkName = newProductName; }

    public double getdrinkPrice() { return drinkPrice; }

    public void setPrice(double newPrice) { this.drinkPrice = newPrice; }

    public String toFileString() { return drinkID + ";" + drinkName + ";" + drinkPrice + ";" + drinkQuantity; }
}
