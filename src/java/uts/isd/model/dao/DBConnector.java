package uts.isd.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector extends DB {
    public DBConnector() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        conn = DriverManager.getConnection(URL + db, dbuser, dbpass);
    }

    public Connection openConnection() {
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
