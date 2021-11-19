/*
 * subclass for jdbc.DBconnection
 * Oracle depended code
 */
package metacoder.data.jdbc_oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IFieldnames;
import metacoder.data.jdbc.types.DBsqldatatype;

/**
 * Oracle DBconnection
 * @author Franky Laseure
 */
public class DBconnection extends metacoder.data.jdbc.DBconnection {

    public static final String DBTOOL = "oracleXE";
    private IFieldnames fieldnames = new Fieldnames();
    
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
                tables = connection.getMetaData().getTables(null, "%", "%", tabletypes);
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
     * check if table is a database system table
     * @param schema: table schema
     * @return true/false
     */
    @Override
    public boolean isSystemtable(String schema) {
        return schema.equals("PUBLIC")
            || schema.startsWith("FLOWS_")
            || schema.equals("CTXSYS")
            || schema.equals("MDSYS")
            || schema.equals("PMADB")
            || schema.equals("SYS")
            || schema.equals("SYSTEM")
            || schema.equals("XDB");
    }

    /**
     * equalize all database dependent sql data types to general useable data types
     * @param datatype: field data type
     * @param size: field size
     * @return SQL type
     */
    @Override
    public int getUniformSqlType(int datatype, int size) {
        switch(datatype) {
            case -1: //unknown
                datatype = DBsqldatatype.VARCHAR.getType();
                break;
            default:
        }
        return datatype;
    }
}
