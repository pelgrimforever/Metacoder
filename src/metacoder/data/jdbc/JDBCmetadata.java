package metacoder.data.jdbc;

import metacoder.data.metadata.Database;

/**
 * Database JDBCmetadata record handling
 * @author Franky Laseure
 */
public class JDBCmetadata {
    
    private String drivername = "";
    private String databaseURL = "";
    private String user = "";
    private String password = "";
    private IDBconnection connection;
    private Database metadatabase;
    private IFieldnames fieldnames;
    
    /**
     * constructor
     * @param dbtool Database tool name
     * @param drivername jdbc driver name
     * @param databaseURL database url
     * @param username database username
     * @param password datase password
     */
    public JDBCmetadata(String dbtool, String drivername, String databaseURL, String username, String password) {
        this.drivername = drivername;
        this.databaseURL = databaseURL;
        this.user = username;
        this.password = password;
        connection = Connectionfactory.getDBconnectionObject(dbtool, drivername, databaseURL, username, password);
        metadatabase = new Database(connection);
    }
    
    /**
     * @return Database metadata
     */
    public Database getMetadatabase() {
        return metadatabase;
    }

    /**
     * @return jdbc driver name
     */
    public String getDrivername() {
        return drivername;
    }

    /**
     * @return database username
     */
    public String getUser() {
        return user;
    }

    /**
     * @return database password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return database connection url
     */
    public String getDatabaseURL() {
        return databaseURL;
    }
    
    
}
