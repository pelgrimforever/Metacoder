/*
 * subclass for jdbc.DBconnection
 * SQL Server depended code
 */
package metacoder.data.jdbc_sqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IFieldnames;
import metacoder.data.jdbc.types.DBsqldatatype;

/**
 * mysql DBconnection
 * @author Franky Laseure
 */
public class DBconnection extends metacoder.data.jdbc.DBconnection {

    public static final String DBTOOL = "sqlserver";
    public IFieldnames fieldnames = new Fieldnames();
    
    /**
     * Reuse the existing DBconnection if that is active and all parameters are the same
     * If not, initiate DBconnection
     * @param drivername: database driver name
     * @param databaseURL: database url string
     * @param username: database username
     * @param password: database password
     */
    public DBconnection (String drivername, String databaseURL, String username, String password) {
        super(drivername, databaseURL, username, password);
    }

    /**
     * get database metadata
     * @return DatabaseMetaData
     */
    @Override
    public ResultSet getTableMetaData() {
        ResultSet tables = null;
        try {
            if(!connection.isClosed()) {
                tables = connection.getMetaData().getTables(null, "dbo", null, null);
            }
        }
        catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return tables;
    }

    @Override
    public IFieldnames getFieldnames() {
        return fieldnames;
    }

    /**
     * equalize all database dependent sql data types to general useable data types
     * @param datatype: field data type
     * @param size: field size
     * @return SQL Type
     */
    @Override
    public int getUniformSqlType(int datatype, int size) {
        switch(datatype) {
            case -1: //unknown
                datatype = DBsqldatatype.VARCHAR.getType();
                break;
            case 1111:
                datatype = DBsqldatatype.VARCHAR.getType();
                break;
            default:
        }
        return datatype;
    }
}
