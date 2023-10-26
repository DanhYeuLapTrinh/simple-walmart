package danh.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextServletListener implements ServletContextListener {

//đc kích hoạt khi ứng dụng đc deploy
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("The application is being deployed...");
        //1. Lấy context scope
        ServletContext context = sce.getServletContext();
        //2. Lấy url của siteMap
        String siteMapsPath = context.getInitParameter("SITEMAPS_FILE_PATH");
        //3. Load siteMap vào trong attribute của context scope
        Properties siteMaps = null;
        InputStream is = null;

        try {
            siteMaps = new Properties();
            is = context.getResourceAsStream(siteMapsPath);
            siteMaps.load(is);
            context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            context.log("ContextServletListener _ IO | " + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    context.log("ContextServletListener _ IO | " + e.getMessage()); // nếu ko .log đc thì context.log
                }
            }
        }
    }

//đc kích hoạt khi ứng dụng đc destroy
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("The application is stopping...");
    }
}
