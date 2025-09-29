package OnlineStore;

import java.util.Date;

public class Order {
    private Product[] products;
    private double totalPrice;
    private Date orderDate;

    public Order(Product[] products, double totalPrice, Date orderDate) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Product[] getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public boolean setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("    Order Date: ").append(orderDate).append("\n");
        sb.append("    Total Price: ").append(totalPrice).append("\n");
        sb.append("    Products:\n");
        for (Product product : products) {
            if (product != null) {
                sb.append(product).append("\n");
            }
        }
        return sb.toString();
    }
}
