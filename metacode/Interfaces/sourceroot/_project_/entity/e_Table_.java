/*
 * e:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.entity;

import :project:.:project:Databaseproperties;
import data.interfaces.db.AbstractEntity;
import data.interfaces.db.Entity;
import data.interfaces.db.Filedata;
import data.gis.shape.*;
import data.json.piJson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Iterator;

import :project:.entity.pk.*;
import :project:.interfaces.logicentity.I:Table:;
import :project:.interfaces.entity.pk.*;
import db.Entityvalues;
import db.SQLparameters;

/**
 * Entity class :Table:
 * 
 * Attributes: primary key, foreign keys en fields
 * Attributes: Database independent SQL-construction strings
 * Conversion methods for java.sql.ResultSet ==> :Table: Entity
 * 
 * @author Franky Laseure
 */
public class e:Table: extends AbstractEntity implements :project:Databaseproperties, Entity {

    protected :Table:PK :table:PK;
:repeatforeignkeys:
:notpk:
    private :Pktable:PK :uniquename:PK;
:notpk:
:repeatforeignkeys:
:repeatfields:
    private :columntype: :columnjavaname:;
:repeatfields:
	  
    public static final String table = ":table_o:";
	  
    public String getFieldname(short fieldconstant) {
        return I:Table:.fieldnames[fieldconstant-1];
    }
        
    public byte getFieldtype(short fieldconstant) {
        return I:Table:.fieldtypes[fieldconstant-1];
    }
        
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
     * @return table name for :Table:
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
     * Constructor
     * build an empty :Table: entity with initialized field values
     */
    public e:Table:(:repeatpkfields::columntype: :columnjavaname::,::repeatpkfields:) {
        this.:table:PK = new :Table:PK(:repeatpkfields::columnjavaname::,::repeatpkfields:);
    }
  
    /**
     * Constructor
     * build an empty :Table: entity with initialized Primarykey parameter
     * @param :table:PK: :Table: Primarykey
     */
    public e:Table:(:Table:PK :table:PK) {
        this.:table:PK = :table:PK;
    }

    /**
     * @return is :Table: Empty ?
     */
    public boolean isEmpty() {
        return this.:table:PK == null;
    }

    /**
     * 
     * @return primarykey fields (fieldname, value) as a SQLparameters object
     */
    @Override
    public SQLparameters getSQLprimarykey() {
        return this.:table:PK.getSQLprimarykey();	  
    }
  
    /**
     * 
     * @return primarykey fields (fieldreference, value) as Entityvalues
     */
    @Override
    public Entityvalues getPrimarykeyvalues() {
        return this.:table:PK.getPrimarykeyvalues();	  
    }
  
    /**
     * 
     * @return all fields (fieldname, value)
     */
    @Override
    public Entityvalues getAll() {
:repeatforeignkeys:
:notpk:
:repeatforeignkeyfields:
        updates.put(I:Table:.:COLUMN:, this.:uniquename:PK.get:Primarycolumn:());
:repeatforeignkeyfields:
:notpk:
:repeatforeignkeys:
:repeatfields:
        updates.put(I:Table:.:COLUMN:, :columnjavaname:);
:repeatfields:
        return getAllFields();
    }
	
    /**
     * @return :Table:PK
     */
    @Override
    public Object getKey() {
        return this.getPrimaryKey();
    }
  
    /**
     * @return :Table:PK
     */
    @Override
    public :Table:PK getPrimaryKey() {
        return this.:table:PK;
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
    public void init:Column:(:columntype: :columnjavaname:) {
        this.:columnjavaname: = :columnjavaname:;
    }

    /**
     * set :column: value
     * @param :columnjavaname:: new value
     */
    public void set:Column:(:columntype: :columnjavaname:) {
:iffieldtype:
:java.lang.String:
	if(:columnjavaname:==null && :columnjavaname:!=this.:columnjavaname: || :columnjavaname:!=null && !:columnjavaname:.equals(this.:columnjavaname:)) {
            updates.put(I:Table:.:COLUMN:, :columnjavaname:);
        }
:java.lang.String:
:java.sql.Date:
	if(:columnjavaname:==null && :columnjavaname:!=this.:columnjavaname: || :columnjavaname:!=null && !:columnjavaname:.equals(this.:columnjavaname:)) {
            updates.put(I:Table:.:COLUMN:, :columnjavaname:);
        }
:java.sql.Date:
:java.sql.Time:
	if(:columnjavaname:==null && :columnjavaname:!=this.:columnjavaname: || :columnjavaname:!=null && !:columnjavaname:.equals(this.:columnjavaname:)) {
            updates.put(I:Table:.:COLUMN:, :columnjavaname:);
        }
:java.sql.Time:
:customfieldtype:
	if(:columnjavaname:==null && :columnjavaname:!=this.:columnjavaname: || :columnjavaname:!=null && !:columnjavaname:.equals(this.:columnjavaname:)) {
            updates.put(I:Table:.:COLUMN:, :columnjavaname:);
        }
:customfieldtype:
:other:
        updates.put(I:Table:.:COLUMN:, :columnjavaname:);
:other:
:iffieldtype:
        this.:columnjavaname: = :columnjavaname:;
    }

:repeatfields:
:repeatforeignkeys:
:notpk:
    /**
     * 
     * @return foreign key :pktablejavanamePK:, instance of :Pktable:PK
     */
    public :Pktable:PK get:Uniquename:PK() {
        return this.:uniquename:PK;
    }

    /**
     * set foreign key :pktable:
     * @param :pktable:PK: instance of :Pktable:PK
     */
    public void init:Uniquename:PK(I:Pktable:PK :uniquename:PK) {
        this.:uniquename:PK = (:Pktable:PK):uniquename:PK;
    }

    /**
     * set foreign key :pktable:
     * @param :pktable:PK: instance of :Pktable:PK
     */
    public void set:Uniquename:PK(I:Pktable:PK :uniquename:PK) {
	if(:uniquename:PK==null && :uniquename:PK!=this.:uniquename:PK || :uniquename:PK!=null) {
            if(:uniquename:PK==null) {
:repeatforeignkeyfields:
                updates.put(I:Table:.:COLUMN:, null);
:repeatforeignkeyfields:
            } else {
:repeatforeignkeyfields:
                updates.put(I:Table:.:COLUMN:, :uniquename:PK.get:Primarycolumn:());
:repeatforeignkeyfields:
            }
        }
        this.:uniquename:PK = (:Pktable:PK):uniquename:PK;
    }

:notpk:
:repeatforeignkeys:
    /**
     * 
     * @return Primarykey string value
     */
    @Override
    public String toString() {
        return this.getPrimaryKey().getKeystring();
    }
}
