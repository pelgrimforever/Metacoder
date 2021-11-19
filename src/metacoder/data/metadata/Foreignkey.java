package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Foreign key metadata
 * @author Franky Laseure
 */
public class Foreignkey {
    
    private String pkschema, pktable, fkschema, fktable, pkname, fkname, name;
    private ArrayList<Foreignkeycolumn> columns = new ArrayList<>();
    
    /**
     * constructor
     * stores Foreignkey metadata
     * @param r Resultset for foreignkeys
     * @param connection Database connection
     */
    public Foreignkey(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.pkschema = r.getString(fieldnames.getPKschema());
            this.pktable = r.getString(fieldnames.getPKtablename());
            this.fkschema = r.getString(fieldnames.getFKschema());
            this.fktable = r.getString(fieldnames.getFKtablename());
            this.pkname = r.getString(fieldnames.getPKname());
            this.fkname = r.getString(fieldnames.getFKname());
            if(!fieldnames.getForeignkeyname().equals(""))
                this.name = r.getString(fieldnames.getForeignkeyname());
            this.loadColumns(connection);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * load foreign key columns metadata
     * @param connection Database connection
     */
    private void loadColumns(IDBconnection connection) {
        //this jdbc connection command gives all foreign key columns between pktable and fktable,
        //without differenciating between different foreign keys
        //Foreign keys not matching the current Foreignkey are filetered out
        ResultSet jdbcfkc = connection.getForeignkeycolumnsMetaData(this.pkschema, this.pktable, this.fkschema, this.fktable);
        try {
            if(jdbcfkc!=null) {
                Foreignkeycolumn fkc;
                while(jdbcfkc.next()) {
                    fkc = new Foreignkeycolumn(jdbcfkc, connection);
                    //filter only same Foreignkey, if foreign key name was available in resultset jdbcfkc
                    if(fkc.getFKname()==null || fkc.getFKname().equals(this.fkname)) {
                        columns.add(fkc);
                    }
                }
            }
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
     * @return primary key table name
     */
    public String getPKname() {
        return pkname;
    }

    /**
     * @return foreign key table name
     */
    public String getFKname() {
        return fkname;
    }

    /**
     * @return foreign key name
     */
    public String getName() {
        return name;
    }

    /**
     * @return foreign key columns
     */
    public ArrayList<Foreignkeycolumn> getColumns() {
        return columns;
    }
    
    
}
