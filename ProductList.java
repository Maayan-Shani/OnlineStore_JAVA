package OnlineStore;

public class ProductList {
    private Product[] productsList;
    private int productsListCounter;

    public ProductList() {
        this.productsList = new Product[1];
    }

    public Product[] getProductsList() {
        return productsList;
    }

    public int getProductsListCounter() {
        return productsListCounter;
    }

    public void extendArrProducts(){
        Product[] newArr = new Product[this.productsList.length * 2];
        for (int i = 0; i < this.productsListCounter; i++) {
            newArr[i] = this.productsList[i];
        }
        this.productsList = newArr;
    }

    public void addProduct(Product product){
        if (productsListCounter >= productsList.length) {
            extendArrProducts();
        }
        productsList[productsListCounter++] = product;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < productsListCounter; i++) {
            sb.append(productsList[i].toString()).append("\n");
        }
        return sb.toString();
    }
}
