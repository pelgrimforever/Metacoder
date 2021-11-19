package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Primary key metadata
 * @author Franky Laseure
 */
public class Primarykey {
    
    private String schema, table, name;
    private ArrayList<Primarykeycolumn> columns = new ArrayList<>();
    
    /**
     * constructor
     * stores Primary key metadata
     * @param r ResultSet for primary key
     * @param connection Database connection
     */
    public Primarykey(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.schema = r.getString(fieldnames.getTableschema());
            this.table = r.getString(fieldnames.getTablename());
            this.name= r.getString(fieldnames.getPrimarykey());
            do {
                columns.add(new Primarykeycolumn(r, connection));
            } while(r.next());
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
     * @return primary key name
     */
    public String getName() {
        return name;
    }

    /**
     * @return primary key columns
     */
    public ArrayList<Primarykeycolumn> getColumns() {
        return columns;
    }
    
}
