//Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita
package gruppe6.eksamensprojekt.data;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {


    private static String usr;
    private static String pwd;
    private static String url;


    private static Connection connection = null;

    /**
     * Returns a connection
     * @return returns the connection to the mySQL database
     */

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        if (connection != null) return connection;


        try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            usr = properties.getProperty("usr");
            pwd = properties.getProperty("pwd");
        } catch (IOException ex) {
            ex.printStackTrace();
        }



        try {
            connection = DriverManager.getConnection(url,usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}




/*
        try (InputStream input = new ClassPathResource("application.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            usr = properties.getProperty("usr");
            pwd = properties.getProperty("pwd");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
*/