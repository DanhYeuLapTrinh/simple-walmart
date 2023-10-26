package danh.order;

import danh.product.ProductDAO;
import danh.product.ProductDTO;
import danh.util.DBHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class OrderDAO {

    public int createOrder(OrderDTO newOrder) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "INSERT INTO tblOrder "
                        + "(date, total) "
                        + "VALUES (GETDATE() , ? ) ";
                //3. Create statement
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //3.1 Set value
                stm.setFloat(1, newOrder.getTotal());
                //4. Execute query
                int effectRows = stm.executeUpdate();
                //5. Process (lấy mã UID để thêm vào order detail)
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return 0;
    }

    public ProductDTO find(String name) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO result = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT sku, proName, quantity, price, proStatus "
                        + "FROM tblProduct "
                        + "WHERE proName = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String sku = rs.getString("sku");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("proStatus");
                    result = new ProductDTO(sku, name, quantity, price, status);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void updateQuantity(String sku, int inStockQuantity, int buyQuantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        int finalQuantity = 0;
        try {
            con = DBHelper.makeConnection();
            if (inStockQuantity > buyQuantity) {
                finalQuantity = inStockQuantity - buyQuantity;
            }
            String sql = "UPDATE tblProduct "
                    + "SET quantity = ? "
                    + "WHERE sku = ? ";
            stm = con.prepareStatement(sql);
            stm.setInt(1, finalQuantity);
            stm.setString(2, sku);
            stm.executeUpdate();
            if (finalQuantity == 0) {
                String sql2 = "UPDATE tblProduct "
                        + "SET proStatus = ? "
                        + "WHERE sku = ? ";
                stm = con.prepareStatement(sql2);
                stm.setBoolean(1, false);
                stm.setString(2, sku);
                stm.executeUpdate();
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
