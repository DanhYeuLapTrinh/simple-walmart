package danh.orderDetail;

import danh.order.OrderDTO;
import danh.util.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class OrderDetailDAO {
    public boolean createOrderDetail(OrderDetailDTO newOrderDetail) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO tblOrderDetail "
                        + "(sku, pName, orderID, price, quantity) "
                        + "VALUES ( ?, ?, ? , ? , ? ) ";
                //3. Create statement
                stm = con.prepareStatement(sql);
                //3.1 Set value
                stm.setString(1, newOrderDetail.getSku());
                stm.setString(2, newOrderDetail.getpName());
                stm.setInt(3, newOrderDetail.getOrderID());
                stm.setFloat(4, newOrderDetail.getPrice());
                stm.setInt(5, newOrderDetail.getQuantity());
                //4. Execute query
                int effectRows = stm.executeUpdate();
                //5. Process
                if (effectRows > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    private List<OrderDetailDTO> odList;

    public List<OrderDetailDTO> getOdList() {
        return odList;
    }   
    
    public void printOrder(int orderID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "SELECT pName, price, quantity "
                        + "FROM tblOrderDetail "
                        + "WHERE orderID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                rs = stm.executeQuery();
                while(rs.next()) {
                    String pName = rs.getString("pName");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    OrderDetailDTO dto = new OrderDetailDTO();
                    dto.setpName(pName);
                    dto.setPrice(price);
                    dto.setQuantity(quantity);
                    if(this.odList == null) {
                        this.odList = new ArrayList<>();
                    }
                    this.odList.add(dto);
                }
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
    }
}
