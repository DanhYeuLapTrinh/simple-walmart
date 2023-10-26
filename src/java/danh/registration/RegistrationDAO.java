package danh.registration;

import danh.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
//        boolean result = false;
        RegistrationDTO result = null;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT lastName, isAdmin "
                        + "FROM Registration "
                        + "WHERE username = ? "
                        + "AND password = ?";
                //3. Create statement object
                stm = con.prepareStatement(sql); // create cau lenh sql
                //3.1 Set value
                stm.setString(1, username);
                stm.setString(2, password); // set giá trị cho câu lệnh bắt đầu từ 1 từ trái sang
                //4. Execute query
                rs = stm.executeQuery(); // muốn có result thì phải có statement
                //5. Process
                if (rs.next()) {
                    //map: get data from rs and set properties of dto
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
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

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchByLastName(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "SELECT username, password, lastName, isAdmin "
                        + "FROM Registration "
                        + "WHERE lastName like ? ";
                //3. Create statement object
                stm = con.prepareStatement(sql); // create cau lenh sql
                //3.1 Set value
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute query
                rs = stm.executeQuery(); // muốn có result thì phải có statement
                //5. Process
                while (rs.next()) {
                    //5.1 trả ra nhiều dòng nên sd while
                    //5.1.1 map
                    //5.1.1.1 GET data from RS
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastName");
                    boolean role = rs.getBoolean("isAdmin");
                    //5.1.1.2 SET data to properties of DTO (constructor hoặc setter)
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    //5.1.1.3 add to list
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }
                    this.accountList.add(dto);
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

    public boolean deleteAccount(String username) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "DELETE FROM Registration "
                        + "WHERE username = ? ";
                //3. Create statement object
                stm = con.prepareStatement(sql); // create cau lenh sql
                //3.1 Set value
                stm.setString(1, username);
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

    public boolean updateAccount(String username, String password, boolean role) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "UPDATE Registration "
                        + "SET password = ?, "
                        + "isAdmin = ? "
                        + "WHERE username = ? ";
                //3. Create statement object
                stm = con.prepareStatement(sql); // create cau lenh sql
                //3.1 Set value
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
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
    
    public boolean addAccount(RegistrationDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBHelper.makeConnection();
            if(con != null) {
                String sql = "INSERT INTO Registration("
                        + "username, password, lastName, isAdmin"
                        + ") VALUES ("
                        + "? ,? ,? ,?"
                        + ")";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                int effectRows = stm.executeUpdate();
                if(effectRows > 0) {
                    result = true;
                }
            }
        } finally{
            if(stm != null) {
                stm.close();
            }
            if(con != null) {
                con.close();
            }
        }
        return result;
    }
}
