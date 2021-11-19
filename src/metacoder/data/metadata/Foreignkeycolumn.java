package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Foreign key column
 * @author Franky Laseure
 */
public class Foreignkeycolumn {
    
    private String pkschema, pktable, pkcolumn;
    private String fkschema, fktable, fkcolumn;
    private String pkname, fkname;
    private int keysequence;

    /**
     * constructor
     * saves Foreign key column metadata
     * @param r Resultset for Foreign key column
     * @param connection Database connection
     */
    public Foreignkeycolumn(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.pkschema = r.getString(fieldnames.getPKcolumnschemaname());
            this.pktable = r.getString(fieldnames.getPKtablename());
            this.pkcolumn = r.getString(fieldnames.getPKcolumnname());
            this.fkschema = r.getString(fieldnames.getFKcolumnschemaname());
            this.fktable = r.getString(fieldnames.getFKtablename());
            this.fkcolumn = r.getString(fieldnames.getFKcolumnname());
            this.pkname = r.getString(fieldnames.getPKname());
            this.fkname = r.getString(fieldnames.getFKname());
            this.keysequence = r.getInt(fieldnames.getFKcolumnsequence()) + fieldnames.getFKcolumnsequenceoffset();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return primary key table schema
     */
    public String getPKschema() {
        return pkschema;
    }

    /**
     * @return primary key table
     */
    public String getPKtable() {
        return pktable;
    }

    /**
     * @return primary key table column
     */
    public String getPKcolumn() {
        return pkcolumn;
    }

    /**
     * @return foreign key table schema
     */
    public String getFKschema() {
        return fkschema;
    }

    /**
     * @return foreign key table
     */
    public String getFKtable() {
        return fktable;
    }

    /**
     * @return foreign key table column
     */
    public String getFKcolumn() {
        return fkcolumn;
    }

    /**
     * @return primary key name
     */
    public String getPKname() {
        return pkname;
    }

    /**
     * @return foreign key name
     */
    public String getFKname() {
        return fkname;
    }

    /**
     * @return sequence number
     */
    public int getKeysequence() {
        return keysequence;
    }

  
  
}
