package danh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBHelper {
    public static Connection makeConnection() throws /*ClassNotFoundException*/ NamingException, SQLException {
//HARD CODE
//        //nên sd throws thay vì trycatch
//        //0.5 bỏ driver vào thư mục Libraries trong netbean (Add JAR/Folder)
//        //1. Load driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create connection string
//        String url = "jdbc:sqlserver://" 
//                + "localhost:1433;" 
//                + "databaseName=Login_Servlet;"
//                + "instanceName=DANHNGUYEN";
//                    //protocol                 ip+port                1 databaseName                   instanceName
//        //3. Open connection
//        Connection con = DriverManager.getConnection(url, "sa", "12341234");
//        //4. Return connection to caller
//        return con;
        
        //1. get current context
        Context currentContext = new InitialContext();
        //2. get web app context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. look up datasource from web app context
        DataSource ds = (DataSource)tomcatContext.lookup("DS_DANH");
        //4. get connection
        Connection con = ds.getConnection();
        return con;
    }
}
