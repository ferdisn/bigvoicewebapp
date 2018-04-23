package id.ferdi.training.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {

            /*
             * Untuk koneksi dengan semua kontainer
             *
             */

            try {
                String url = "jdbc:postgresql://localhost/training";
                String driver = "org.postgresql.Driver";
                String user = "training";
                String password = "training";

                Class.forName(driver);
                connection = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            /*
             * Untuk koneksi dengan kontainer EE
             *
             */
            /*
            try {
                DataSource myDataSource;
                //Class.forName("org.postgresql.Driver");
                //supaya Tomcat dgn setting JNDI pribadi dan WildFly bisa berjalan bersamaan.
                String jndi_name = "java:/comp/env/jdbc/PostgresDS";
                myDataSource = (DataSource) new InitialContext().lookup(jndi_name);
                connection = myDataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
            */

            return connection;
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
