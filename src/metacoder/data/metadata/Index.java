package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Index metadata
 * @author Franky Laseure
 */
public class Index {
    
    private String schema, table, name;
    private boolean unique;
    private int sequence;
    private ArrayList<Indexcolumn> columns = new ArrayList<>();

    /**
     * constructor
     * stores index metadata
     * @param r ResultSet for Index
     * @param connection Database connection
     */
    public Index(ResultSet r, IDBconnection connection) {
        try {
            IFieldnames fieldnames = connection.getFieldnames();
            this.schema = r.getString(fieldnames.getTableschema());
            this.table = r.getString(fieldnames.getTablename());
            this.unique = !r.getBoolean(fieldnames.getIndexnonunique());
            this.name = r.getString(fieldnames.getIndexname());
            this.sequence = r.getInt(fieldnames.getOrdinalposition());
            this.loadColumns(r, connection);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * load indexcolumns
     * @param jdbcindexc ResultSet for Index
     * @param connection Database connection
     */
    private void loadColumns(ResultSet jdbcindexc, IDBconnection connection) {
        try {
            if(jdbcindexc!=null) {
                String indexname;
                boolean sameindex = false;
                do {
                    indexname = jdbcindexc.getString(connection.getFieldnames().getIndexname());
                    sameindex = indexname.equals(this.name);
                    if(sameindex) {
                        columns.add(new Indexcolumn(jdbcindexc, connection));
                    }
                } while(sameindex && jdbcindexc.next());
            }
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
     * @return is index unique
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * @return sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * @return index columns
     */
    public ArrayList<Indexcolumn> getColumns() {
        return columns;
    }
    
}
