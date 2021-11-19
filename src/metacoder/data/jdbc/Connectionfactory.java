/*
 * Database Connection Factory
 */
package metacoder.data.jdbc;

/**
 * Database connection factory
 * create IDBconnection with database dependent DBconnection
 * @author Franky Laseure
 */
public class Connectionfactory {
    
    /**
     * return a connection object for the specified dbtool
     * @param dbtool: database tool
     * @param drivername: database driver name
     * @param databaseURL: database connection string
     * @param username: database user name
     * @param password: database password
     * @return Database Connection
     */
    public static IDBconnection getDBconnectionObject(String dbtool, String drivername, String databaseURL, String username, String password) {
        IDBconnection dbconnection = null;
        switch(dbtool) {
            case metacoder.data.jdbc_mysql.DBconnection.DBTOOL:
                dbconnection = new metacoder.data.jdbc_mysql.DBconnection(drivername, databaseURL, username, password);
                break;
            case metacoder.data.jdbc_postgresql.DBconnection.DBTOOL:
                dbconnection = new metacoder.data.jdbc_postgresql.DBconnection(drivername, databaseURL, username, password);
                break;
            case metacoder.data.jdbc_oracle.DBconnection.DBTOOL:
                dbconnection = new metacoder.data.jdbc_oracle.DBconnection(drivername, databaseURL, username, password);
                break;
            case metacoder.data.jdbc_sqlserver.DBconnection.DBTOOL:
                dbconnection = new metacoder.data.jdbc_sqlserver.DBconnection(drivername, databaseURL, username, password);
                break;
        }
        return dbconnection;
    }
}
