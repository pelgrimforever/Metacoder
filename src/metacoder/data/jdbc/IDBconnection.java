package metacoder.data.jdbc;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

/**
 * Database connection Interface
 * @author Franky Laseure
 */
public interface IDBconnection {
    
    public static final String TYPE_TABLE = "TABLE";
    public static final String TYPE_VIEW = "VIEW";
    public static final String[] tabletypes = {TYPE_TABLE, TYPE_VIEW};

    /**
     * is a DBconnection active ?
     * @return true/false
     */
    public boolean isconnected();
    
    /**
     * get database metadata
     * @return DatabaseMetaData
     */
    public DatabaseMetaData getMetaData();

    /**
     * get metadata resultset fieldnames
     * @return IFieldnames, Fieldnames instance from specified database tool
     */
    public IFieldnames getFieldnames();
    
    /**
     * query table metadata
     * @return ResultSet with database dependend metadata
     */
    public ResultSet getTableMetaData();

    /**
     * query primarykey columns metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with primary key
     */
    public ResultSet getPrimarykeyMetaData(String schema, String tablename);
    
    /**
     * query foreignkeys metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with foreign keys
     */
    public ResultSet getForeignkeyMetaData(String schema, String tablename);
    
    /**
     * query foreignkey columns metadata
     * WARNING: this gives all foreign key columns with these parameters
     * when a column is used in 2 or more foreign keys, this can cause too much results and must be filtered out
     * @param PKschema: primary key table schema
     * @param PKtablename: primary key table name
     * @param FKschema: foreign key table schema
     * @param FKtablename: foreign key table name
     * @return ResultSet with foreign key columns
     */
    public ResultSet getForeignkeycolumnsMetaData(String PKschema, String PKtablename, String FKschema, String FKtablename);
    
    /**
     * query columns metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return ResultSet with columns
     */
    public ResultSet getColumnMetaData(String schema, String tablename);

    /**
     * query index columns metadata
     * @param schema: schema name
     * @param tablename: table name
     * @return Resultset with Indexes
     */
    public ResultSet getIndexMetaData(String schema, String tablename);
    
    /**
     * check if table is a database system table
     * @param schema: table schema
     * @return true/false
     */
    public boolean isSystemtable(String schema);
    
    /**
     * equalize all database dependent sql data types to general useable data types
     * @param datatype: field data type
     * @param size: field size
     * @return SQL Type
     */
    public int getUniformSqlType(int datatype, int size);    
}
