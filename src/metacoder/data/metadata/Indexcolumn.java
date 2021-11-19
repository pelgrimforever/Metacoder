package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Index column metadata
 * @author Franky Laseure
 */
public class Indexcolumn {
    
    private String schema, table, name, column;
    private boolean unique;
    private int sequence;
    
    /**
     * constructor
     * stores Index column metadata
     * @param r ResultSet for Index column
     * @param connection Database connection
     */
    public Indexcolumn(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.schema = r.getString(fieldnames.getTableschema());
            this.table = r.getString(fieldnames.getTablename());
            if(r.getString(fieldnames.getIndexnonunique())!=null) {
                this.unique = r.getString(fieldnames.getIndexnonunique()).equals("f");
            } else {
                this.unique = true;
            }
            this.name = r.getString(fieldnames.getIndexname());
            this.sequence = r.getInt(fieldnames.getOrdinalposition());
            this.column = r.getString(fieldnames.getColumnname());
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
     * @return index name
     */
    public String getName() {
        return name;
    }

    /**
     * @return index column
     */
    public String getColumn() {
        return column;
    }

    /**
     * @return is unique index
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * @return sequence number
     */
    public int getSequence() {
        return sequence;
    }
    
    
}
