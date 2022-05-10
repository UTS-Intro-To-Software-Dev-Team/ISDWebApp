package uts.isd.model.dao;
import java.sql.Connection;

public abstract class DB {
    protected String URL = "jdbc:derby://localhost:1527/";
    protected String db = "customerdb";
    protected String dbuser = "isduser";
    protected String dbpass = "TnePasswordIsIncorrect";
    protected String driver = "org.apache.derby.jdbc.ClientDriver";
    protected Connection conn;
}
