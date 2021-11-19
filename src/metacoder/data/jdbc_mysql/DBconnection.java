/*
 * subclass for jdbc.DBconnection
 * mysql depended code
 */
package metacoder.data.jdbc_mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IFieldnames;
import metacoder.data.jdbc.types.DBsqldatatype;

/**
 * mysql DBconnection
 * @author Franky Laseure
 */
public class DBconnection extends metacoder.data.jdbc.DBconnection {

    public static final String DBTOOL = "mysql";
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
                tables = connection.getMetaData().getTables(connection.getCatalog(), null, null, tabletypes);
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
                switch(size) {
                    case 16777215:                              //MEDIUMTEXT
                    case 65535:                                 //TEXT
                    case 255:                                   //TYNITEXT
                    case -1:                                    //LONGTEXT
                    datatype = DBsqldatatype.VARCHAR.getType();
                    break;
                }
            default:
        }
        return datatype;
    }
}
