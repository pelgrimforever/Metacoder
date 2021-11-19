package metacoder.data.jdbc;

/**
 * Standard JDBC metadata fieldnames
 * These can optionally be overridden in the Project"database" projects
 * if a database deviates
 * @author Franky Laseure
 */
public class Fieldnames implements IFieldnames {
    
    @Override
    public String getTableschema() {
        return SCHEMA;
    }
    
    @Override
    public String getTabletype() {
        return TYPE;
    }
    
    @Override
    public String getTableremarks() {
        return REMARKS;
    }
    
    @Override
    public String getTablename() {
        return TABLE_NAME;
    }
    
    @Override
    public String getPrimarykey() {
        return PRIMARYKEY;
    }

    @Override
    public String getPKcolumnsequence() {
        return PKCOLUMNSEQUENCE;
    }

    @Override
    public String getColumnname() {
        return COLUMN_NAME;
    }

    @Override
    public String getDatatype() {
        return DATATYPE;
    }

    @Override
    public String getDatatypename() {
        return DATATYPENAME;
    }

    @Override
    public String getSize() {
        return SIZE;
    }

    @Override
    public String getLength() {
        return LENGTH;
    }

    @Override
    public String getDecimaldigits() {
        return DECIMALDIGITS;
    }

    @Override
    public String getNumericprecisionradix() {
        return NUMERICPRECISIONRADIX;
    }

    @Override
    public String getNullable() {
        return NULLABLE;
    }

    @Override
    public String getDefaultvalue() {
        return DEFAULTVALUE;
    }

    @Override
    public String getSqldatatype() {
        return SQL_DATA_TYPE;
    }

    @Override
    public String getSqldatetimesub() {
        return SQL_DATETIME_SUB;
    }

    @Override
    public String getCharoctetlength() {
        return CHAR_OCTET_LENGTH;
    }

    @Override
    public String getOrdinalposition() {
        return ORDINALPOSITION;
    }

    @Override
    public String getIsnullable() {
        return ISNULLABLE;
    }
    
    public String getPKschema() {
        return PKSCHEMA;
    }

    @Override
    public String getPKtablename() {
        return PKTABLE;
    }

    @Override
    public String getFKschema() {
        return FKSCHEMA;
    }

    @Override
    public String getFKtablename() {
        return FKTABLE;
    }

    @Override
    public String getFKupdaterule() {
        return UPDATERULE;
    }

    @Override
    public String getFKdeleterule() {
        return DELETERULE;
    }

    @Override
    public String getForeignkeyname() {
        return FOREIGNKEYNAME;
    }

    @Override
    public String getPKname() {
        return PK_NAME;
    }

    @Override
    public String getPKcolumnschemaname() {
        return PKSCHEMA;
    }

    @Override
    public String getPKcolumnname() {
        return PKCOLUMN;
    }

    @Override
    public String getFKname() {
        return FK_NAME;
    }

    @Override
    public String getFKcolumnschemaname() {
        return FKSCHEMA;
    }

    @Override
    public String getFKcolumnname() {
        return FKCOLUMN;
    }

    @Override
    public String getFKcolumnsequence() {
        return FKCOLUMNSEQUENCE;
    }
    
    @Override
    public String getIndexname() {
        return INDEX_NAME;
    }

    @Override
    public String getIndexnonunique() {
        return NON_UNIQUE;
    }

    @Override
    public int getFKcolumnsequenceoffset() {
        return FKCOLUMNSEQUENCEOFFSET;
    }
}
