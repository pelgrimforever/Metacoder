package metacoder.data.jdbc;

import java.sql.*;
import java.util.Properties;
import metacoder.data.jdbc.types.DBtype;

/**
 * DBconnection: Database Connection
 * Abstract database connection class
 * @author Franky Laseure
 */
public abstract class DBconnection implements IDBconnection {

    protected DBtype dbtype;
    //private String drivername = "org.postgresql.Driver";
    //private String databaseURL = "jdbc:postgresql://81.165.234.105:5432/";
    //private String drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //private String databaseURL = "jdbc:sqlserver://10.32.18.33;databaseName=MII02;";
    private String drivername = "";
    private String databaseURL = "";
    private String user = "";
    private String password = "";
    protected Connection connection;

    /**
     * Reuse the existing connection if that is active and all parameters are the same
     * If not, initiate connection
     * @param drivername: database driver name
     * @param databaseURL: database url string
     * @param username: database username
     * @param password: database password
     */
    public DBconnection(String drivername, String databaseURL, String username, String password) {
        if(!(this.drivername.equals(drivername) && this.databaseURL.equals(databaseURL) && this.user.equals(username) && this.password.equals(password)
                && !isconnected())) {
            connection = findConnection(drivername, databaseURL, username, password);
        }
    }

    /**
     * Ask a DBconnection to the sql DriverManager
     * @param drivername: database driver name
     * @param databaseURL: database url string
     * @param username: database username
     * @param password: database password
     * @return 
     */
    private Connection findConnection(String drivername, String databaseURL, String username, String password) {
        Connection con = null;
        try {
            this.drivername = drivername;
            this.databaseURL = databaseURL;
            this.user = username;
            this.password = password;
            Class.forName(drivername);
            Properties prop = new Properties();
            prop.setProperty("user", username);
            prop.setProperty("password", password);
            con = DriverManager.getConnection(databaseURL, prop);
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        catch(Throwable t) {
            System.out.println(t.getMessage());
        }
        return con;
    }

    /**
     * is a DBconnection active ?
     * @return true/false
     */
    @Override
    public boolean isconnected() {
        try {
            return !connection.isClosed();
        }
        catch(SQLException | NullPointerException e) {
            return false;
        }
    }

    /**
     * get database metadata
     * @return DatabaseMetaData
     */
    public DatabaseMetaData getMetaData() {
        DatabaseMetaData DBmeta = null;
        try {
          if(!connection.isClosed()) {
            DBmeta = connection.getMetaData();
          }
        }
        catch(SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return DBmeta;
    }
    
    @Override
    public abstract IFieldnames getFieldnames();
    
    /**
     * query primarykey metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with primary key
     */
    @Override
    public ResultSet getPrimarykeyMetaData(String schema, String tablename) {
        ResultSet primarykeys = null;
        try {
            if(!connection.isClosed()) {
                primarykeys = connection.getMetaData().getPrimaryKeys(null, schema, tablename);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return primarykeys;
    }

    /**
     * query foreignkeys metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with foreign keys
     */
    @Override
    public ResultSet getForeignkeyMetaData(String schema, String tablename) {
        ResultSet foreignkeys = null;
        try {
            if(!connection.isClosed()) {
                foreignkeys = connection.getMetaData().getImportedKeys(null, schema, tablename);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return foreignkeys;
    }

    /**
     * query foreignkey columns metadata
     * @param PKschema: primary key table schema
     * @param PKtablename: primary key table name
     * @param FKschema: foreign key table schema
     * @param FKtablename: foreign key table name
     * @return ResultSet with foreign key columns
     */
    @Override
    public ResultSet getForeignkeycolumnsMetaData(String PKschema, String PKtablename, String FKschema, String FKtablename) {
        ResultSet foreignkeys = null;
        try {
            if(!connection.isClosed()) {
                foreignkeys = connection.getMetaData().getCrossReference(null, PKschema, PKtablename, null, FKschema, FKtablename);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return foreignkeys;
    }

    @Override
    public ResultSet getColumnMetaData(String schema, String tablename) {
        ResultSet columns = null;
        try {
            if(!connection.isClosed()) {
                columns = connection.getMetaData().getColumns(null, schema, tablename, null);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return columns;
    }
    
    /**
     * query index columns metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with indexes
     */
    public ResultSet getIndexMetaData(String schema, String tablename) {
        ResultSet indexes = null;
        try {
            if(!connection.isClosed()) {
                indexes = connection.getMetaData().getIndexInfo(null, schema, tablename, false, true);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return indexes;        
    }

    /**
     * check if table is a database system table
     * @param schema: table schema
     * @return true/false
     */
    @Override
    public boolean isSystemtable(String schema) {
        return schema.equals("information_schema");
    }

    /**
     * equalize all database dependent sql data types to general useable data types
     * @param datatype: field data type
     * @param size: field size
     * @return SQL type
     */
    @Override
    public int getUniformSqlType(int datatype, int size) {
        return datatype;
    }
    
}
