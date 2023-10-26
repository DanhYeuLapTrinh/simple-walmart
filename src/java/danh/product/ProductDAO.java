package danh.product;

import danh.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDAO implements Serializable {

    private List<ProductDTO> proList;

    public List<ProductDTO> getProList() {
        return proList;
    }

    public void searchProductByName(String proname) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT sku, proName, price "
                        + "FROM tblProduct "
                        + "WHERE proName like ?  and proStatus = ? ";
                //3. Create statement
                stm = con.prepareStatement(sql);
                //3.1 Set value
                stm.setString(1, "%" + proname + "%");
                stm.setBoolean(2, true);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //5.1 MAP
                    //5.2 Get data from rs
                    String sku = rs.getString("sku");
                    String proName = rs.getString("proName");
                    float price = rs.getFloat("price");
                    //5.3 set data (using setter)
                    ProductDTO dto = new ProductDTO();
                    dto.setSku(sku);
                    dto.setProName(proName);
                    dto.setPrice(price);
                    //5.4 Add to list to show
                    if (this.proList == null) {
                        this.proList = new ArrayList<>();
                    }
                    this.proList.add(dto);
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
    }
    
    public boolean printAll() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT sku, proName, price "
                        + "FROM tblProduct "
                        + "WHERE proStatus = ? ";
                //3. Create statement
                stm = con.prepareStatement(sql);
                //3.1 Set value
                stm.setBoolean(1, true);
                //4. Execute query
                rs = stm.executeQuery();
                //5. Process
                while (rs.next()) {
                    //5.1 MAP
                    //5.2 Get data from rs
                    String sku = rs.getString("sku");
                    String proName = rs.getString("proName");
                    float price = rs.getFloat("price");
                    //5.3 set data (using setter)
                    ProductDTO dto = new ProductDTO();
                    dto.setSku(sku);
                    dto.setProName(proName);
                    dto.setPrice(price);
                    //5.4 Add to list to show
                    if (this.proList == null) {
                        this.proList = new ArrayList<>();
                    }
                    this.proList.add(dto);
                    if(this.proList.size() != 0) {
                        result = true;
                    }
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
}
