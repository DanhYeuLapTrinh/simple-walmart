package danh.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {

    //DTO chứa đồ cho Cart
    private Map<String, Integer> item;

    public Map<String, Integer> getItem() {
        return item;
    }

    // viết thêm 1 method add có 2 parameter là sku và số lượng nếu trùng thì tăng số lượng lên
    public void addItem(String sku) {
        if (sku == null) {
            return;
        }
        if (sku.trim().isEmpty()) {
            return;
        }
        //1. Check xem cart có tồn tại chưa?
        if (this.item == null) {
            this.item = new HashMap<>();
        }
        //2. Check xem item có tồn tại chưa? có thì tăng số lượng
        int quantity = 1;
        if (this.item.containsKey(sku)) {
            quantity = this.item.get(sku) + 1;
        }
//        3. Update item
        this.item.put(sku, quantity);
    }

    public boolean addItemWithQuantity(String sku, int inQuantity) {
        if (sku == null) {
            return false;
        }
        if (sku.trim().isEmpty()) {
            return false;
        }
        if (this.item == null) {
            this.item = new HashMap<>();
        }
        int quantity = inQuantity;
        if (this.item.containsKey(sku)) {
            quantity = this.item.get(sku) + inQuantity;
        }
        this.item.put(sku, quantity);
        return true;
    }


    public void removeItem(String sku) {
        //1. Check xem item có tồn tại chưa?
        if (this.item == null) {
            return;
        }
        if (this.item.containsKey(sku)) {
            this.item.remove(sku);
            if (this.item.isEmpty()) { // nếu xóa xong mà map ko còn phần tử nào thì hủy map luôn
                this.item = null;
            }
        }
    }
    
    public void removeItemWithQuantity(String sku, int quantity) {
        //1. Check xem item có tồn tại chưa?
        if (this.item == null) {
            return;
        }
        if(this.item.containsKey(sku)) {
            int oldQuantity = this.item.get(sku);
            this.item.remove(sku);
            this.item.put(sku, oldQuantity - quantity);
            if (this.item.isEmpty()) {
                this.item = null;
            }
        }
    }
}
