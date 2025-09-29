package OnlineStore;


public class Buyer extends User implements Comparable<Buyer>{
    private Address address;
    private ProductList cart;
    private Order[] orderHistory;
    private int cartCounter, orderHistoryCounter;


    public Buyer(String userNameBuyer, String password, Address address) {
        super(userNameBuyer, password);
        this.cart = new ProductList();
        this.orderHistory = new Order[1];
        this.address = new Address(address);
    }

    public ProductList getCart() {
        return cart;
    }

    public Order[] getOrderHistory() {
        return orderHistory;
    }

    public int getCartCounter() {
        return cartCounter;
    }

    public int getOrderHistoryCounter() {
        return orderHistoryCounter;
    }

    public void addProductToCart(Product product) {
        cart.addProduct(product);
        cartCounter++;
    }

    public void addOrderToHistory(Order order) {
        if (orderHistoryCounter >= orderHistory.length) {
            Order[] newOrderHistory = new Order[orderHistory.length * 2];
            for (int i = 0; i < orderHistoryCounter; i++) {
                newOrderHistory[i] = orderHistory[i];
            }
            orderHistory = newOrderHistory;
        }
        orderHistory[orderHistoryCounter++] = order;
    }

    public void clearCart() {
        cart = new ProductList();
        cartCounter = 0;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Buyer: ").append(getUserName()).append("\n");
        sb.append("----------------------------------------\n");
        sb.append(address).append("\n");
        sb.append("----------------------------------------\n");
        sb.append("Cart Contents:\n");
        for (int i = 0; i < cartCounter; i++) {
            if (cart.getProductsList()[i] != null) {
                sb.append(cart.getProductsList()[i]).append("\n");
            }
        }
        sb.append("----------------------------------------\n");
        sb.append("Order History:\n");
        for (int i = 0; i < orderHistoryCounter; i++) {
            if (orderHistory[i] != null) {
                sb.append(orderHistory[i]).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Buyer o) {
        return getUserName().compareToIgnoreCase(o.getUserName());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;

        return this.getUserName().equals(otherBuyer.getUserName());
    }
}
