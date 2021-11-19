package metacoder.data.jdbc.types;

/**
 * Enumeration of jdbc data types and corresponding java data types
 * @author Franky Laseure
 */
public enum DBjavadatatype {
    ARRAY(DBsqldatatype.ARRAY, java.sql.Array.class.getName()),
    BIGINT(DBsqldatatype.BIGINT, long.class.getName()),
    BINARY(DBsqldatatype.BINARY, Object.class.getName()),
    //in SQL Server, boolean does not exist -> byte
    //BIT(DBsqldatatype.BIT, byte.class.getName()),
    BIT(DBsqldatatype.BIT, boolean.class.getName()),
    BLOB(DBsqldatatype.BLOB, Object.class.getName()),
    CHAR(DBsqldatatype.CHAR, String.class.getName()),
    CLOB(DBsqldatatype.CLOB, Object.class.getName()),
    DATE(DBsqldatatype.DATE, java.sql.Date.class.getName()),
    DECIMAL(DBsqldatatype.DECIMAL, float.class.getName()),
    DISTINCT(DBsqldatatype.DISTINCT, String.class.getName()),
    DOUBLE(DBsqldatatype.DOUBLE, double.class.getName()),
    FLOAT(DBsqldatatype.FLOAT, float.class.getName()),
    INTEGER(DBsqldatatype.INTEGER, int.class.getName()),
    JAVA_OBJECT(DBsqldatatype.JAVA_OBJECT, Object.class.getName()),
    LONGVARBINARY(DBsqldatatype.LONGVARBINARY, Object.class.getName()),
    LONGVARCHAR(DBsqldatatype.LONGVARCHAR, String.class.getName()),
    NULL(DBsqldatatype.NULL, String.class.getName()),
    NUMERIC(DBsqldatatype.NUMERIC, float.class.getName()),
    OTHER(DBsqldatatype.OTHER, Object.class.getName()),
    REAL(DBsqldatatype.REAL, float.class.getName()),
    REF(DBsqldatatype.REF, String.class.getName()),
    SMALLINT(DBsqldatatype.SMALLINT, int.class.getName()),
    STRUCT(DBsqldatatype.STRUCT, Object.class.getName()),
    TIME(DBsqldatatype.TIME, java.sql.Time.class.getName()),
    TIMESTAMP(DBsqldatatype.TIMESTAMP, java.sql.Timestamp.class.getName()),
    TINYINT(DBsqldatatype.TINYINT, byte.class.getName()),
    VARBINARY(DBsqldatatype.VARBINARY, Object.class.getName()),
    VARCHAR(DBsqldatatype.VARCHAR, String.class.getName()),
    NVARCHAR(DBsqldatatype.NVARCHAR, String.class.getName()),
    NCHAR(DBsqldatatype.NCHAR, String.class.getName()),
    UNKNOWN(DBsqldatatype.UNKNOWN, Object.class.getName());
    
    private DBsqldatatype dbsqldatatype;
    private String javaclassname;
    
    /**
     * @param dbsqldatatype jdbc data type
     * @param javaclassname java data type
     */
    DBjavadatatype(DBsqldatatype dbsqldatatype, String javaclassname) {
        this.dbsqldatatype = dbsqldatatype;
        this.javaclassname = javaclassname;
    }

    /**
     * @return jdbc data type
     */
    public DBsqldatatype getDbsqldatatype() {
        return dbsqldatatype;
    }

    /**
     * @return java data type
     */
    public String getJavaclassname() {
        return javaclassname;
    }
    
    /**
     * search DBjavadatatype by sql data type
     * @param dbsqldatatype sql data type
     * @return DBjavadatatype
     */
    public static DBjavadatatype findDBjavadatatype(DBsqldatatype dbsqldatatype) {
        DBjavadatatype javadatatype = UNKNOWN;
        for(DBjavadatatype jdt: DBjavadatatype.values()) {
            if(jdt.getDbsqldatatype().equals(dbsqldatatype)) {
                javadatatype = jdt;
                break;
            }
        }
        return javadatatype;
    }
}
