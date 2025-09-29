package OnlineStore;

import java.util.Arrays;
import java.util.Date;

public class SystemManager {

    private Seller[] sellers;
    private int countSeller;

    private Buyer[] buyers;
    private int countBuyer;

    public SystemManager() {
        this.buyers = new Buyer[2];
        this.sellers = new Seller[2];
    }

    public Boolean ifThereIsSeller() {
        return countSeller != 0;
    }

    public Boolean ifThereIsBuyer() {
        return countBuyer != 0;
    }

    public Boolean ifThereIsHistoryOrders(String userName){
        Buyer buyer = findBuyer(userName);
        return buyer.getOrderHistoryCounter() != 0;
    }

    public Boolean ifThereIsProductsSeller(String userName){
        Seller seller = findSeller(userName);
        return seller.getCountProductsSeller() != 0;
    }

    public Boolean ifThereIsProductsInCart(String buyerUsername) {
        Buyer buyer = findBuyer(buyerUsername);
        return buyer.getCartCounter() != 0;
    }

    public String chooseSellerByNumber(int sellerNumber) throws IllegalArgumentException{
        Seller[] sellers = getSellers();
        if (sellerNumber > 0 && sellerNumber <= this.countSeller) {
            return sellers[sellerNumber - 1].getUserName();
        }else{
            throw new IllegalArgumentException("Invalid seller number: " + sellerNumber);
        }
    }

    public String chooseBuyerByNumber(int buyerNumber) throws IllegalArgumentException{
        Buyer[] buyers = getBuyers();
        if (buyerNumber > 0 && buyerNumber <= this.countBuyer) {
            return buyers[buyerNumber - 1].getUserName();
        }
        throw new IllegalArgumentException("Invalid buyer number: " + buyerNumber);
    }

    public String chooseProductByNumber(int productNumber,String sellerUsername) {
        Seller seller = findSeller(sellerUsername);
        if (productNumber > 0 && productNumber <= seller.getCountProductsSeller()) {
            return seller.getProductsSeller().getProductsList()[productNumber - 1].getProductName();
        }
        throw new IllegalArgumentException("Invalid buyer number: " + productNumber);
    }

    public void CheckPositiveNumbers (int number) throws IllegalArgumentException{
        if(number <= 0){
            throw new IllegalArgumentException("The building number must be positive : " + number);
        }
    }

    public void CheckPrice (double price) throws IllegalArgumentException{
        if(price <= 0){
            throw new IllegalArgumentException("The price must be positive: " + price);
        }
    }

    public void creatCart(int OrderNumber, String UserNameBuyer) throws IllegalArgumentException{
        Buyer buyer = findBuyer(UserNameBuyer);
        if (buyer != null) {
            if (OrderNumber > 0 && OrderNumber <= buyer.getOrderHistoryCounter()) {
                buyer.clearCart();
                Product[] newCart = buyer.getOrderHistory()[OrderNumber - 1].getProducts();
                for (int i = 0; i < newCart.length; i++) {
                    buyer.addProductToCart(newCart[i]);
                }
            }else{
                throw new IllegalArgumentException("Invalid Order number: " + OrderNumber);
            }
        }
    }

    public Seller[] getSellers() {
        Seller[] activeSellers = new Seller[countSeller];
        for (int i = 0; i < countSeller; i++) {
            activeSellers[i] = sellers[i];
        }
        Arrays.sort(activeSellers);
        return activeSellers;
    }

    public String showSellers() {
        Seller[] sellers = getSellers();
        String SellersNames = "";
        for (int i = 0; i < countSeller; i++) {
            SellersNames += (i + 1)  + ") "+ sellers[i].getUserName() + "\n";
        }
        return SellersNames;
    }

    public String showBuyers() {
        Buyer[] buyers = getBuyers();
        String BuyersNames = "";
        for (int i = 0; i < countBuyer; i++) {
            BuyersNames += (i + 1)  + ") "+ buyers[i].getUserName() + "\n";
        }
        return BuyersNames;
    }

    public String showProductsSeller(String userNameSeller) {
        Seller seller = findSeller(userNameSeller);
        String SellerProductsNames = "";
        if (seller != null) {
            for (int i = 0; i < seller.getCountProductsSeller(); i++) {
                SellerProductsNames += (i + 1) + ") " + seller.getProductsSeller().getProductsList()[i].toString() + "\n";
            }
        }
        return SellerProductsNames;
    }

    public Buyer[] getBuyers() {
        Buyer[] activeBuyers = new Buyer[countBuyer];
        for (int i = 0; i < countBuyer; i++) {
            activeBuyers[i] = buyers[i];
        }
        Arrays.sort(activeBuyers);
        return activeBuyers;
    }

    public Seller findSeller(String userName) {
        for (int i = 0; i < this.countSeller; i++) {
            if (this.sellers[i].getUserName().equals(userName)) {
                return this.sellers[i];
            }
        }
        return null;
    }

    public Buyer findBuyer(String userName) {
        for (int i = 0; i < this.countBuyer; i++) {
            if (this.buyers[i].getUserName().equals(userName)) {
                return this.buyers[i];
            }
        }
        return null;
    }

    public Boolean ifNameIsExistInSellers(String userName){
        for (int i = 0; i < this.countSeller; i++) {
            if (this.sellers[i].getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean ifNameIsExistInBuyers(String userName) {
        for (int i = 0; i < this.countBuyer; i++) {
            if (this.buyers[i].getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean checkProduct(Seller seller, String productName, double productPrice) {
        for (int i = 0; i < seller.getCountProductsSeller(); i++) {
            if (seller.getProductsSeller().getProductsList()[i].getProductName().equals(productName)) {
                if(seller.getProductsSeller().getProductsList()[i].getProductPrice() == productPrice){
                    return false;
                }
            }
        }
        return true;
    }

    private Seller[] extendArrSeller() {
        Seller[] newArr = new Seller[this.sellers.length * 2];
        for (int i = 0; i < this.countSeller; i++) {
            newArr[i] = this.sellers[i];
        }
        return newArr;
    }

    private Buyer[] extendArrBuyer() {
        Buyer[] newArr = new Buyer[this.buyers.length * 2];
        for (int i = 0; i < this.countBuyer; i++) {
            newArr[i] = this.buyers[i];
        }
        return newArr;
    }

    public void addSeller(String userNameSeller, String password) {
        Seller seller = new Seller(userNameSeller, password);
        if (!(countSeller < sellers.length)) {
            this.sellers = extendArrSeller();
        }
        sellers[countSeller++] = seller;
    }

    public void addBuyer(String userNameBuyer, String password, Address address) {
        Buyer buyer = new Buyer(userNameBuyer ,password, address);
        if (!(countBuyer < buyers.length)) {
            buyers = extendArrBuyer();
        }
        buyers[countBuyer++] = buyer;
    }

    public boolean addProductToSeller(String userNameSeller, String productName, double productPrice, Product.eCategories category, double priceSpecialPackaging, boolean specialPackaging) {
        Seller seller = findSeller(userNameSeller);
        if (seller != null) {
            if(checkProduct(seller, productName, productPrice)){
                if(specialPackaging){
                    SpecialPackaging productSeller = new SpecialPackaging(productName, productPrice, category, priceSpecialPackaging);
                    seller.addProductToSeller(productSeller);
                }else{
                    Product productSeller = new Product(productName, productPrice,category);
                    seller.addProductToSeller(productSeller);
                }
                return true;
            }
        }
        return false;
    }

    public boolean addProductToBuyer(String buyerUserName, String sellerUserName, String productName) {
        Buyer buyer = findBuyer(buyerUserName);
        if (buyer != null) {
            Seller seller = findSeller(sellerUserName);
            if (seller != null) {
                for (int i = 0; i < seller.getCountProductsSeller(); i++) {
                    Product product = seller.getProductsSeller().getProductsList()[i];
                    if(product.getProductName().equals(productName)) {
                        if(product instanceof SpecialPackaging){
                            SpecialPackaging productToAdd = new SpecialPackaging((SpecialPackaging)product);
                            buyer.addProductToCart(productToAdd);
                        }else{
                            Product productToAdd = new Product(product);
                            buyer.addProductToCart(productToAdd);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public double paymentForBuyer(String nameBuyer) {
        Buyer buyer = findBuyer(nameBuyer);
        double totalPrice = 0;
        if (buyer != null) {
            ProductList cart = buyer.getCart();
            Product[] purchasedProducts = new Product[buyer.getCartCounter()];

            for (int i = 0; i < cart.getProductsListCounter(); i++) {
                Product product = cart.getProductsList()[i];
                if (product instanceof SpecialPackaging) {
                    totalPrice += ((SpecialPackaging) product).getTotalPrice();
                } else {
                    totalPrice += product.getProductPrice();
                }
                purchasedProducts[i] = product;
            }
            Date orderDate = new Date();
            Order newOrder = new Order(purchasedProducts, totalPrice, orderDate);
            buyer.addOrderToHistory(newOrder);
            buyer.clearCart();
        }
        return totalPrice;
    }

    public String getProductsByCategory(Product.eCategories category){
        String productsByCategory = "";

        for (int i = 0; i < countSeller; i++) {
            Product[] productsSeller = sellers[i].getProductsSeller().getProductsList();
            for (int j = 0; j < sellers[i].getCountProductsSeller(); j++) {
                if(productsSeller[j].getCategory() == category){
                    productsByCategory += productsSeller[j].toString() + "\n";
                }
            }
        }
        return productsByCategory;
    }

    public String ShowOrderHistory(String buyerUsername){
        String Orders = "";
        Buyer buyer = findBuyer(buyerUsername);
        if (buyer != null){
            for (int i = 0; i < buyer.getOrderHistoryCounter(); i++) {
                Orders += (i + 1)  + ") "+ buyer.getOrderHistory()[i].toString() + "\n";
            }
        }
        return Orders;
    }
}