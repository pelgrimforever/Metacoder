/*
 * Database XML tag names
 */
package metacoder.data.xml;

/**
 * Database XML tag names
 * used in Xmlbuilder and Xmlreader to write and read database xml files
 * @author Franky Laseure
 */
public interface Ixmltags {

    //constants
    public static final String PATH = "databases";
    public static final String NAMEVALUE = "namevalue";
    //db
    public static final String DATABASE = "Database";
    public static final String DB = "DB";
    public static final String XMLFILE = "xmlfile";
    public static final String TOOL = "dbtool";
    public static final String IP = "ip";
    public static final String PORT = "port";
    public static final String PORTSEPARATOR = "portseparator";
    public static final String DATABASENAME = "databasename";
    public static final String USER = "user";
    public static final String URL = "url";
    public static final String ODBCDRIVER = "ODBCdriver";
    public static final String ODBCDRIVERVERSION = "ODBCdriverversion";
    public static final String PRODUCTNAME = "Productname";
    public static final String PRODUCTVERSION = "Productversion";
    public static final String TABLECOUNT = "Tablecount";
    public static final String TABLES = "TABLES";

    //table
    public static final String NAME = "name";
    public static final String TABLETYPE = "tabletype";
    public static final String TABLESCHEMA = "tableschema";
    public static final String TABLEREMARKS = "tableremarks";
    public static final String COLUMNS = "COLUMNS";
    public static final String COLUMN = "COLUMN";
    public static final String PRIMARYKEY = "PRIMARYKEY";
    public static final String FOREIGNKEYS = "FOREIGNKEYS";
    public static final String FOREIGNKEY = "FOREIGNKEY";
    public static final String PRIMARYKEYSCHEMA = "primarykeyschema";
    public static final String PRIMARYKEYTABLE = "primarykeytable";
    public static final String FOREIGNKEYSCHEMA = "foreignkeyschema";
    public static final String FOREIGNKEYTABLE = "foreignkeytable";
    public static final String PKNAME = "pkname";
    public static final String FKNAME = "fkname";
    public static final String FKJAVANAME = "fkjavaname";
    public static final String INDEXES = "INDEXES";
    public static final String INDEX = "INDEX";
    public static final String PARTOFPRIMARYKEY = "partofprimarykey";

    //columns
    public static final String COLUMNNAME = "columnname";
    public static final String DATATYPE = "datatype";
    public static final String UNIFORMDATATYPE = "datatype";
    public static final String SIZE = "size";
    public static final String DECIMALDIGITS = "decimaldigits";
    public static final String NUMERICPRECISIONRADIX = "numericprecisionradix";
    public static final String NULLABLE = "nullable";
    public static final String DEFAULTVALUE = "defautlvalue";
    public static final String SEQUENCE = "sequence";
    public static final String PTABLE = "ftable";
    public static final String PCOLUMNNAME = "pcolumnname";
    public static final String FTABLE = "ptable";
    public static final String FCOLUMNNAME = "fcolumnname";
    public static final String INDEXNAME = "indexname";
    public static final String UNIQUE = "unique";
    public static final String FULLNAME = "fullname";
    public static final String JAVATYPE = "javatype";
    public static final String REMARKS = "remarks";
    public static final String SCHEMA = "schema";
    public static final String SQLDATATYPE = "sqldatatype";
    public static final String SQLDATETIMESUB = "sqldatetimesub";
    public static final String DATATYPENAME = "datatypename";
    public static final String UNIFORMSQLTYPE = "uniformsqltype";
    public static final String FILEONDISK = "fileondisk";
    
}
