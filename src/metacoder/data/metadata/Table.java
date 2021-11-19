package metacoder.data.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.jdbc.IFieldnames;

/**
 * Table and table properties metadata
 * @author Franky Laseure
 */
public class Table {
    
    private String name, schema, type, remarks;
    private boolean systemtable = false;
    private boolean attributesloaded = false;
    private IDBconnection connection;
    private Primarykey primarykey = null;
    private ArrayList<Foreignkey> foreignkeys = new ArrayList<>();
    private ArrayList<Column> columns = new ArrayList<>();
    private ArrayList<Index> indexes = new ArrayList<>();

    /**
     * constructor
     * saves table metadata
     * @param r ResultSet for table
     * @param connection Database connection
     * @throws SQLException from reading ResultSet
     */
    public Table(ResultSet r, IDBconnection connection) throws SQLException {
        IFieldnames fieldnames = connection.getFieldnames();
        this.schema = r.getString(fieldnames.getTableschema());
        this.name = r.getString(fieldnames.getTablename());
        this.type = r.getString(fieldnames.getTabletype());
        this.remarks = r.getString(fieldnames.getTableremarks());
        this.systemtable = connection.isSystemtable(this.schema);
        this.connection = connection;
    }
    
    /**
     * load 
     * - primary key
     * - foreign keys
     * - table columns
     * - indexes
     */
    private void loadAttributes() {
        if(!attributesloaded) {
            attributesloaded = true;
            loadPrimarykey(connection);
            loadForeignkeys(connection);
            loadColumns(connection);
            loadIndexes(connection);
        }
    }

    /**    
     * load primary key metadata
     * @param connection Database connection
     */
    private void loadPrimarykey(IDBconnection connection) {
        ResultSet jdbcpk = connection.getPrimarykeyMetaData(this.schema, this.name);
        try {
            if(jdbcpk!=null) {
                if(jdbcpk.next()) {
                    primarykey = new Primarykey(jdbcpk, connection);
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * load foreign keys metadata
     * @param connection Database connection
     */
    private void loadForeignkeys(IDBconnection connection) {
        ResultSet jdbcfk = connection.getForeignkeyMetaData(this.schema, this.name);
        try {
            if(jdbcfk!=null) {
                while(jdbcfk.next()) {
                    foreignkeys.add(new Foreignkey(jdbcfk, connection));
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }    
    
    /**
     * load columns
     * @param connection Database connection
     */
    private void loadColumns(IDBconnection connection) {
        ResultSet jdbcpk = connection.getColumnMetaData(this.schema, this.name);
        try {
            if(jdbcpk!=null) {
                while(jdbcpk.next()) {
                    columns.add(new Column(jdbcpk, connection));
                }
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }    

    /**
     * load Indexes
     * @param connection Database connection
     */
    private void loadIndexes(IDBconnection connection) {
        ResultSet jdbcindex = connection.getIndexMetaData(this.schema, this.name);
        try {
            if(jdbcindex!=null && jdbcindex.next()) {
                do {
                    indexes.add(new Index(jdbcindex, connection));
                } while(!jdbcindex.isAfterLast());
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }    

    /**
     * @return table
     */
    public String getName() {
        return name;
    }

    /**
     * @return table schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @return type (table / view)
     */
    public String getType() {
        return type;
    }

    /**
     * @return is table
     */
    public boolean isTable() {
        return type.equals(IDBconnection.TYPE_TABLE);
    }

    /**
     * @return is view
     */
    public boolean isView() {
        return type.equals(IDBconnection.TYPE_VIEW);
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @return is system table
     */
    public boolean isSystemtable() {
        return systemtable;
    }

    /**
     * @return Primarykey
     */
    public Primarykey getPrimarykey() {
        loadAttributes();
        return primarykey;
    }

    /**
     * @return list of Foreignkey
     */
    public ArrayList<Foreignkey> getForeignkeys() {
        loadAttributes();
        return foreignkeys;
    }

    /**
     * @return list of Column
     */
    public ArrayList<Column> getColumns() {
        loadAttributes();
        return columns;
    }

    /**
     * @return list of Index
     */
    public ArrayList<Index> getIndexes() {
        loadAttributes();
        return indexes;
    }

}
