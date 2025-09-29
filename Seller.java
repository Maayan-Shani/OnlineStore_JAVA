package OnlineStore;

public class Seller extends User implements Comparable<Seller> {
    private ProductList productsSeller;
    private int countProductsSeller;

    public Seller(String userNameSeller, String password) {
        super(userNameSeller, password);
        this.productsSeller = new ProductList();
        this.countProductsSeller = 0;
    }

    public ProductList getProductsSeller() {
        return productsSeller;
    }

    public void addProductToSeller(Product product) {
        productsSeller.addProduct(product);
        countProductsSeller++;
    }

    public int getCountProductsSeller() {
        return countProductsSeller;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Seller: ").append(getUserName()).append("\n");
        sb.append("Products:\n");
        sb.append(productsSeller.toString());
        return sb.toString();
    }

    @Override
    public int compareTo(Seller s) {
        if ( countProductsSeller < s.countProductsSeller) {
            return -1;
        }else if ( countProductsSeller > s.countProductsSeller) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;

        return this.getUserName().equals(otherSeller.getUserName());
    }

}
