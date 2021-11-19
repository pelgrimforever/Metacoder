package metacoder.data.metadata;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.types.DBtypeversion;

/**
 * Database Metadata
 * @author Franky Laseure
 */
public class Database {

    //Database Metadata
    private String driver, driverversion;
    private String productname, productversion;
    private String driverdatabaseurl;

    private ArrayList<Table> tables = new ArrayList<>();

    private DBtypeversion dbtypeversion;

    /**
     * Constructor
     * stores database metadata
     * load table metadata
     * @param connection: database connection
     */
    public Database(IDBconnection connection) {
        DatabaseMetaData dbmetadata = connection.getMetaData();
        try {
            this.driver = dbmetadata.getDriverName();
            this.driverversion = dbmetadata.getDriverVersion();
            this.productname = dbmetadata.getDatabaseProductName();
            this.productversion = dbmetadata.getDatabaseProductVersion();
            this.driverdatabaseurl = dbmetadata.getURL();
        }
        catch(SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        this.dbtypeversion = DBtypeversion.getType(this.productname, this.productversion);
        this.loadtables(connection);
    }

    /**
     * load tables metadata
     * @param connection 
     */
    private void loadtables(IDBconnection connection) {
        ResultSet jdbctables = connection.getTableMetaData();
        try {
            if(jdbctables!=null) {
                while(jdbctables.next()) {
                    tables.add(new Table(jdbctables, connection));
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return jdbc driver
     */
    public String getDriver() {
        return this.driver;
    }

    /**
     * @return jdbc driver version
     */
    public String getDriverVersion() {
        return this.driverversion;
    }

    /**
     * @return database product name
     */
    public String getProductName() {
        return this.productname;
    }

    /**
     * @return database product version
     */
    public String getProductVersion() {
        return this.productversion;
    }

    /**
     * @return connection url
     */
    public String getDriverdatabaseURL() {
        return this.driverdatabaseurl;
    }
    
    /**
     * @return table count
     */
    public int getTablecount() {
        return tables.size();
    }
    
    /**
     * @return table list
     */
    public ArrayList<Table> getTables() {
        return tables;
    }
    
}
