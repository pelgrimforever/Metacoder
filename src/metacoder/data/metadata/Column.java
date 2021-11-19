package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;
import metacoder.data.jdbc.types.DBjavadatatype;
import metacoder.data.jdbc.types.DBsqldatatype;

/**
 * Table column metadata
 * @author Franky Laseure
 */
public class Column {
    
    protected String schema, table, name;
    protected int datatype;
    protected int uniformdatatype;
    protected String typename;
    protected int size, bufferlength, decimaldigits, numberprecisionradix, nullable;
    protected String remarks, columndefinition;
    protected int sqldatatype;
    protected String sqldatetimesub;
    protected int charoctetlength, ordinalposition;
    protected boolean isnullable;
    
    /**
     * constructor
     * read metadata from ResultSet for column
     * @param r Resultset
     * @param connection Database connection
     */
    public Column(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.schema = r.getString(fieldnames.getTableschema());
            this.table = r.getString(fieldnames.getTablename());
            this.name = r.getString(fieldnames.getColumnname());
            this.datatype = r.getInt(fieldnames.getDatatype());
            this.typename = r.getString(fieldnames.getDatatypename());
            this.size = r.getInt(fieldnames.getSize());
            this.bufferlength = r.getInt(fieldnames.getLength());
            this.decimaldigits = r.getInt(fieldnames.getDecimaldigits());
            this.numberprecisionradix = r.getInt(fieldnames.getNumericprecisionradix());
            this.nullable = r.getInt(fieldnames.getNullable());
            this.remarks = r.getString(fieldnames.getTableremarks());
            this.columndefinition = r.getString(fieldnames.getDefaultvalue());
            this.sqldatatype = r.getInt(fieldnames.getSqldatatype());
            this.sqldatetimesub = r.getString(fieldnames.getSqldatetimesub());
            this.charoctetlength = r.getInt(fieldnames.getCharoctetlength());
            this.ordinalposition = r.getInt(fieldnames.getOrdinalposition());
            if(r.getObject(fieldnames.getIsnullable()) instanceof String) {
                this.isnullable = r.getString(fieldnames.getIsnullable()).equals("YES");
            } else {
                this.isnullable = r.getBoolean(fieldnames.getIsnullable());
            }
            if(this.datatype == java.sql.Types.TIMESTAMP && this.typename.equals("DATE")) this.datatype = java.sql.Types.DATE;
            this.uniformdatatype = connection.getUniformSqlType(this.datatype, this.size);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return table schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @return table name
     */
    public String getTable() {
        return table;
    }

    /**
     * @return column name
     */
    public String getName() {
        return name;
    }

    /**
     * @return full column name (schema.table.name)
     */
    public String getFullname() {
        return this.schema + "." + this.table + "." + this.name;
    }

    /**
     * @return data type
     */
    public int getDatatype() {
        return datatype;
    }

    /**
     * @return general useable data types
     */
    public int getUniformdatatype() {
        return uniformdatatype;
    }

    /**
     * @return data type name
     */
    public String getTypename() {
        return typename;
    }

    /**
     * Search Java data type
     * @return DBjavadatatype
     */
    public String getJavatype() {
        return DBjavadatatype.findDBjavadatatype(DBsqldatatype.getType(this.datatype, this.typename)).getJavaclassname();
    }
    
    /**
     * @return column size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return column buffer length
     */
    public int getBufferlength() {
        return bufferlength;
    }

    /**
     * @return decimal digits
     */
    public int getDecimaldigits() {
        return decimaldigits;
    }

    /**
     * @return Precision radix
     */
    public int getNumberprecisionradix() {
        return numberprecisionradix;
    }

    /**
     * @return nullable
     */
    public int getNullable() {
        return nullable;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @return definition
     */
    public String getColumndefinition() {
        return columndefinition;
    }

    /**
     * @return sql data type
     */
    public int getSqldatatype() {
        return sqldatatype;
    }

    public String getSqldatetimesub() {
        return sqldatetimesub;
    }

    public int getCharoctetlength() {
        return charoctetlength;
    }

    /**
     * @return ordinal position
     */
    public int getOrdinalposition() {
        return ordinalposition;
    }

    /**
     * @return is nullable
     */
    public boolean isIsnullable() {
        return isnullable;
    }
    
    
}
