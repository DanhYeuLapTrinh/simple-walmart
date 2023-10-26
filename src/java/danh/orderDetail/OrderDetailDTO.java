package danh.orderDetail;

import java.io.Serializable;

public class OrderDetailDTO implements Serializable {

    private int ID;
    private String sku;
    private int orderID;
    private float price;
    private int quantity;
    private String pName;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int ID, String sku, int orderID, float price, int quantity, String pName) {
        this.ID = ID;
        this.sku = sku;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.pName = pName;
    }

    public OrderDetailDTO(String sku, int orderID, float price, int quantity, String pName) {
        this.sku = sku;
        this.orderID = orderID;
        this.price = price;
        this.quantity = quantity;
        this.pName = pName;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the pName
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName the pName to set
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

}
