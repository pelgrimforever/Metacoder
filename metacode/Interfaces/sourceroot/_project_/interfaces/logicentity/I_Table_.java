/*
 * I:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.logicentity;

import data.gis.shape.*;
import data.interfaces.Datatypes;
import data.interfaces.db.LogicEntity;
import data.interfaces.db.Filedata;
import data.json.piJson;
import :project:.interfaces.entity.pk.*;
import :project:.entity.pk.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Entity Interface I:Table:
 *
 * extended attributes and methods possibility:
 * table level programming is done here
 *
 * @author Franky Laseure
 */
public interface I:Table: extends LogicEntity {

    public I:Table:PK getPrimaryKey();
	
:repeatallfields:
    public static short :COLUMN: = :columnposition:;
:repeatallfields:

:repeatallfields:
    public static final int SIZE_:COLUMN: = :columnsize:;
:repeatallfields:
    public static final String[] fieldnames = { :repeatallfields:":column_o:":,::repeatallfields: };
    public static final byte[] fieldtypes = { :repeatallfields:Datatypes.type_:iffieldtype:
:java.lang.String:String:java.lang.String:
:java.sql.Time:Time:java.sql.Time:
:java.sql.Timestamp:Timestamp:java.sql.Timestamp:
:java.sql.Date:Date:java.sql.Date:
:java.lang.Object:Object:java.lang.Object:
:other::columntypesql::other:
:iffieldtype::,::repeatallfields: };

:repeatforeignkeys:
    public static final String[] :uniquename:PKfields = { :repeatforeignkeyfields:":pktable_o:.:primarycolumn_o:":,::repeatforeignkeyfields: };
    public static final String[] :uniquename:FKfields = { :repeatforeignkeyfields:":table_o:.:foreigncolumn_o:":,::repeatforeignkeyfields: };
:repeatforeignkeys:

//Custom code, do not change this line
//Put custom variables here
//Custom code, do not change this line

:repeatfields:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Column:();

    /**
     * set :column: value
     * @param :columnjavaname:: new value
     */
    public void set:Column:(:columntype: :columnjavaname:);

:repeatfields:
:repeatforeignkeys:
:notpk:
    /**
     * 
     * @return foreign key :uniquename:PK, instance of :Pktable:PK
     */
    public I:Pktable:PK get:Uniquename:PK();

    /**
     * set foreign key :pktable:
     * @param :uniquename:PK: instance of :Pktable:PK
     */
    public void set:Uniquename:PK(I:Pktable:PK :uniquename:PK);

:notpk:
:repeatforeignkeys:
    
//Custom code, do not change this line
//Put custom functions here
    /**
     * @return Entity string representation
     */
    public String toString();
//Custom code, do not change this line
}
