package metacoder.data.jdbc;

/**
 * Database Metadata field names constants
 * @author Franky Laseure
 */
public interface IFieldnames {
    
    //Table
    public static final String SCHEMA = "TABLE_SCHEM";
    public static final String TYPE = "TABLE_TYPE";
    public static final String REMARKS = "REMARKS";
    public static final String TABLE_NAME = "TABLE_NAME";
    
    //Primary key
    public static final String PRIMARYKEY = "PK_NAME";
    
    //Foreign key
    public static final String PKSCHEMA = "PKTABLE_SCHEM";
    public static final String PKTABLE = "PKTABLE_NAME";
    public static final String FKSCHEMA = "FKTABLE_SCHEM";
    public static final String FKTABLE = "FKTABLE_NAME";
    public static final String UPDATERULE = "UPDATE_RULE";
    public static final String DELETERULE = "DELETE_RULE";
    public static final String FOREIGNKEYNAME = "";
    public static final String PK_NAME = "PK_NAME";
    public static final String FK_NAME = "FK_NAME";
    
    //foreign key column
    public static final String PKCOLUMN = "PKCOLUMN_NAME";
    public static final String PKCOLUMNSEQUENCE = "KEY_SEQ";
    public static final String FKCOLUMN = "FKCOLUMN_NAME";
    public static final String FKCOLUMNSEQUENCE = "KEY_SEQ";
    
    //Table column
    public static final String COLUMN_NAME = "COLUMN_NAME";
    public static final String DATATYPE = "DATA_TYPE";
    public static final String DATATYPENAME = "TYPE_NAME";
    public static final String SIZE = "COLUMN_SIZE";
    public static final String LENGTH = "BUFFER_LENGTH";
    public static final String DECIMALDIGITS = "DECIMAL_DIGITS";
    public static final String NUMERICPRECISIONRADIX = "NUM_PREC_RADIX";
    public static final String NULLABLE = "NULLABLE";
    public static final String DEFAULTVALUE = "COLUMN_DEF";
    public static final String SQL_DATA_TYPE = "SQL_DATA_TYPE";
    public static final String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
    public static final String CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH";
    public static final String ORDINALPOSITION = "ORDINAL_POSITION";
    public static final String ISNULLABLE = "IS_NULLABLE";

    //Table index
    public static final String NON_UNIQUE = "NON_UNIQUE";
    public static final String INDEX_NAME = "INDEX_NAME";
    
    public static final byte FKCOLUMNSEQUENCEOFFSET = 0;
    
    //Table
    public String getTableschema();
    public String getTabletype();
    public String getTableremarks();
    public String getTablename();
    
    //Primary key
    public String getPrimarykey();
    public String getPKcolumnsequence();
    
    //Table column
    public String getColumnname();
    public String getDatatype();
    public String getDatatypename();
    public String getSize();
    public String getLength();
    public String getDecimaldigits();
    public String getNumericprecisionradix();
    public String getNullable();
    public String getDefaultvalue();
    public String getSqldatatype();
    public String getSqldatetimesub();
    public String getCharoctetlength();
    public String getOrdinalposition();
    public String getIsnullable();

    public String getPKschema();
    public String getPKtablename();
    public String getFKschema();
    public String getFKtablename();
    public String getFKupdaterule();
    public String getFKdeleterule();
    public String getForeignkeyname();
    public String getPKname();
    public String getFKname();

    public String getPKcolumnschemaname();
    public String getPKcolumnname();
    public String getFKcolumnschemaname();
    public String getFKcolumnname();
    public String getFKcolumnsequence();

    public String getIndexname();
    public String getIndexnonunique();
    
    public int getFKcolumnsequenceoffset();
}
