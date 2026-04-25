public class Order {

    private String orderId;
    private String customerName;
    private String product;
    private int quantity;
    private String status;

    public Order(String orderId, String customerName, String product, int quantity, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}