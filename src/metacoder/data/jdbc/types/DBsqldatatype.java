package metacoder.data.jdbc.types;

/**
 * DBsqldatatype
 * SQL data type and corresponding String name
 * @author Franky Laseure
 */
public enum DBsqldatatype {
    ARRAY(java.sql.Types.ARRAY, "ARRAY"),
    BIGINT(java.sql.Types.BIGINT, "BIGINT"),
    BINARY(java.sql.Types.BINARY, "BINARY"),
    BIT(java.sql.Types.BIT, "BIT"),
    BLOB(java.sql.Types.BLOB, "Object"),
    CHAR(java.sql.Types.CHAR, "CHAR"),
    CLOB(java.sql.Types.CLOB, "CLOB"),
    DATE(java.sql.Types.DATE, "DATE"),
    DECIMAL(java.sql.Types.DECIMAL, "DECIMAL"),
    DISTINCT(java.sql.Types.DISTINCT, "DISTINCT"),
    DOUBLE(java.sql.Types.DOUBLE, "DOUBLE"),
    FLOAT(java.sql.Types.FLOAT, "FLOAT"),
    INTEGER(java.sql.Types.INTEGER, "INTEGER"),
    JAVA_OBJECT(java.sql.Types.JAVA_OBJECT, "JAVA_OBJECT"),
    LONGVARBINARY(java.sql.Types.LONGVARBINARY, "LONGVARBINARY"),
    LONGVARCHAR(java.sql.Types.LONGVARCHAR, "LONGVARCHAR"),
    NULL(java.sql.Types.NULL, "NULL"),
    NUMERIC(java.sql.Types.NUMERIC, "NUMERIC"),
    OTHER(java.sql.Types.OTHER, "OTHER"),
    REAL(java.sql.Types.REAL, "REAL"),
    REF(java.sql.Types.REF, "REF"),
    SMALLINT(java.sql.Types.SMALLINT, "INTEGER"),
    STRUCT(java.sql.Types.STRUCT, "STRUCT"),
    TIME(java.sql.Types.TIME, "TIME"),
    TIMESTAMP(java.sql.Types.TIMESTAMP, "TIMESTAMP"),
    TINYINT(java.sql.Types.TINYINT, "TINYINT"),
    VARBINARY(java.sql.Types.VARBINARY, "VARBINARY"),
    VARCHAR(java.sql.Types.VARCHAR, "VARCHAR"),
    NVARCHAR(java.sql.Types.NVARCHAR, "NVARCHAR"),
    NCHAR(java.sql.Types.NCHAR, "NCHAR"),
    UNKNOWN(-1, "Unknown");
    
    private int type;
    private String name;

    /**
     * @return data type name
     */
    public String getName() {
        return name;
    }

    /**
     * @return jdbc sql type
     */
    public int getType() {
        return type;
    }

    /**
     * constructor
     * @param type jdbc sql type
     * @param name type name
     */
    private DBsqldatatype(int type, String name) {
        this.type = type;
        this.name = name;
    }
    
    /**
     * find corresponding DBsqldatatype by type, with corrections for special cases with typename
     * @param type Metadata Resultset data type
     * @param typename Metadata Resultset data type name
     * @return DBsqldatatype
     */
    public static DBsqldatatype getType(int type, String typename) {
        DBsqldatatype sqldatatype = UNKNOWN;
        if(typename.equals("uuid")) {
            sqldatatype = DBsqldatatype.VARCHAR;
        } else if(typename.equals("timestamp")) {
            sqldatatype = DBsqldatatype.TIMESTAMP;
        } else {
            for(DBsqldatatype sqldt: DBsqldatatype.values()) {
                if(sqldt.getType()==type) {
                    sqldatatype = sqldt;
                    break;
                }
            }
        }
        return sqldatatype;
    }

    /**
     * find corresponding DBsqldatatype by type
     * @param type Metadata Resultset data type
     * @return DBsqldatatype
     */
    public static DBsqldatatype getType(int type) {
        DBsqldatatype sqldatatype = UNKNOWN;
        for(DBsqldatatype sqldt: DBsqldatatype.values()) {
            if(sqldt.getType()==type) {
                sqldatatype = sqldt;
                break;
            }
        }
        return sqldatatype;
    }
}
