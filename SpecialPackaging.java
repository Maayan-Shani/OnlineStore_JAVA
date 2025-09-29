package OnlineStore;

public class SpecialPackaging extends Product{
    protected double priceOfSpecialPackage;

    public SpecialPackaging(String productName, double productPrice, eCategories category, double priceOfSpecialPackage) {
        super(productName, productPrice, category);
        this.priceOfSpecialPackage = priceOfSpecialPackage;
    }

    public SpecialPackaging(SpecialPackaging other) {
        super(other);
        this.priceOfSpecialPackage = other.priceOfSpecialPackage;
    }

    public double getPriceOfSpecialPackage() {
        return priceOfSpecialPackage;
    }

    public double getTotalPrice() {
        return getProductPrice() + getPriceOfSpecialPackage();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof SpecialPackaging)) {
            return false;
        }

        SpecialPackaging otherProduct = (SpecialPackaging) other;

        return super.equals(otherProduct) && getProductName().equals(otherProduct.getProductName());
    }

    @Override
    public String toString() {
        return super.toString() + " + " + priceOfSpecialPackage + " --> total price with special package =  " + getTotalPrice();
    }
}
