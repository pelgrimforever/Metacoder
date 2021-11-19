package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Primary key column metadata
 * @author Franky Laseure
 */
public class Primarykeycolumn {
    
    private String schema, table, name, primarykey;
    private int keysequence;

    /**
     * constructor
     * stores primary key column metadata
     * @param r ResultSet for primary key column
     * @param connection Database connection
     */
    public Primarykeycolumn(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.schema = r.getString(fieldnames.getTableschema());
            this.table = r.getString(fieldnames.getTablename());
            this.primarykey = r.getString(fieldnames.getPrimarykey());
            this.name= r.getString(fieldnames.getColumnname());
            this.keysequence = r.getInt(fieldnames.getPKcolumnsequence());
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
     * @return table
     */
    public String getTable() {
        return table;
    }

    /**
     * @return primary key column name
     */
    public String getName() {
        return name;
    }

    /**
     * @return primary key name
     */
    public String getPrimarykey() {
        return primarykey;
    }

    /**
     * @return sequence number
     */
    public int getKeysequence() {
        return keysequence;
    }
  
  
}
