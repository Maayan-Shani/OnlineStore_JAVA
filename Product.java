package OnlineStore;

public class Product {
    private static int counter;
    private String productName;
    private double productPrice;
    private int serialNumber;

    public enum eCategories {Kids, Electricity, Office, Cloths};

    private eCategories category;

    public Product(String productName, double productPrice, eCategories category) {
        this.productName = productName;
        this.productPrice = productPrice;
        serialNumber = ++counter;
        this.category = category;
    }

    public Product(Product other) {
        this.productName = other.productName;
        this.productPrice = other.productPrice;
        serialNumber = other.serialNumber; // לבדוק
        this.category = other.category;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getProductName() {
        return productName;
    }

    public boolean setProductName(String productName) {
        this.productName = productName;
        return true;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public eCategories getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "[#" + serialNumber + "] "  + productName + ": " + productPrice;
    }
}
