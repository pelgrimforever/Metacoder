/*
 * e:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.view;

import :project:.:project:Databaseproperties;
import data.interfaces.db.EntityView;
import data.interfaces.db.Filedata;
import data.gis.shape.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Iterator;

/**
 * View class :Table:
 * 
 * Attributes: fields
 * Attributes: Database independent SQL-construction strings
 * Conversion methods for java.sql.ResultSet ==> :Table: Entity
 * 
 * @author Franky Laseure
 */
public class e:Table: implements :project:Databaseproperties, EntityView {

    public static final String table = ":table_o:";
:repeatfields:
    private :columntype: :columnjavaname:;
:repeatfields:
	  
    /**
     * @return database tool name
     */
    @Override
    public String getDbtool() {
        return e:Table:.databasetool;
    }
    
    /**
     * @return connection pool name
     */
    @Override
    public String getConnectionpool() {
        return e:Table:.connectionpool;
    }
    
    /**
     * 
     * @return view name for :Table:
     */
    public String getTable() { return table; }

    /**
     * 
     * @return :Table: class name
     */
    public String getClassName() { return this.getClass().getName(); };
	  
    /** 
     * Constructor
     * Creates an empty :Table: entity
     */
    public e:Table:() {
    }

    /**
     * @return is :Table: Empty ?
     */
    public boolean isEmpty() {
        return false;
    }

:repeatfields:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Column:() {
        return this.:columnjavaname:;
    }

    /**
     * set :column: value
     * @param :columnjavaname:: new value
     */
    public void set:Column:(:columntype: :columnjavaname:) {
        this.:columnjavaname: = :columnjavaname:;
    }

:repeatfields:
}
