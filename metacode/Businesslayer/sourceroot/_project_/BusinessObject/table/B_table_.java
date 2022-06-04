/*
 * B:table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.BusinessObject.table;

import BusinessObject.BLtable;
import general.exception.*;
import java.util.ArrayList;
import db.SQLMapperFactory;
import db.SQLparameters;
import data.gis.shape.*;
import data.json.piJson;
import data.json.psqlJsonobject;
import db.SQLMapper_pgsql;
import data.interfaces.db.Filedata;
import :project:.BusinessObject.Logic.*;
import :project:.conversion.json.JSON:Table:;
import :project:.conversion.entity.EM:table:;
import :project:.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.searchentity.I:Table:search;
import :project:.logicentity.:Table:;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import org.postgresql.geometric.PGpoint;
import org.postgis.PGgeometry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Business Entity class B:table:
 *
 * Superclass for manipulating data- and database objects
 * for Entity :Table: and direct related data
 * This class is overwritten each time the code generator runs
 * and is not meant to be changed
 *
 * @author Franky Laseure
 */
public abstract class B:table: extends BLtable {

    /**
     * Constructor, sets :Table: as default Entity
     */
    public B:table:() {
        super(new :Table:(), new EM:table:());
    }

    /**
     * Constructor, sets :Table: as default Entity
     * sets transaction queue from given GeneralEntityObject implementation
     * all transactions will commit at same time
     * @param transactionobject: GeneralEntityObjects that holds the transaction queue
     */
    public B:table:(BLtable transactionobject) {
        super(transactionobject, new :Table:(), new EM:table:());
    }

    /**
     * create new empty :Table: object
     * @return empty I:Table:
     */
    public I:Table: new:Table:() {
    	return new :Table:();
    }
    
    /**
     * create new empty :Table: object
     * create new primary key with given parameters
:repeatpkfields:
     * @param :columnjavaname: primary key field
:repeatpkfields:
     * @return I:Table: with primary key
     */
    public I:Table: new:Table:(:repeatpkfields::columntype: :columnjavaname::,::repeatpkfields:) {
        return new :Table:(:repeatpkfields::columnjavaname::,::repeatpkfields:);
    }

    /**
     * create new empty :Table: object with given primary key
     * @param :table:PK: primary key for :Table:
     * @return I:Table: with primary key
     */
    public I:Table: new:Table:(I:Table:PK :table:PK) {
        return new :Table:((:Table:PK):table:PK);
    }

    /**
     * create new empty primary key
     * @return empty :Table:PK
     */
    public I:Table:PK new:Table:PK() {
        return new :Table:PK();
    }

    /**
     * create new primary key with given parameters
:repeatpkfields:
     * @param :columnjavaname: primary key field
:repeatpkfields:
     * @return new I:Table:PK
     */
    public I:Table:PK new:Table:PK(:repeatpkfields::columntype: :columnjavaname::,::repeatpkfields:) {
        return new :Table:PK(:repeatpkfields::columnjavaname::,::repeatpkfields:);
    }

    /**
     * get all :Table: objects from database
     * @return ArrayList of :Table: objects
     * @throws DBException
     */
    public ArrayList<:Table:> get:Table:s() throws DBException {
        return (ArrayList<:Table:>)super.getEntities(EM:table:.SQLSelectAll);
    }

    /**
     * search :Table: for primary key
     * @param :table:PK: :Table: primary key
     * @return :Table: object
     * @throws DBException
     */
    public :Table: get:Table:(I:Table:PK :table:PK) throws DBException {
        return (:Table:)super.getEntity((:Table:PK):table:PK);
    }

    /**
     * search :table: with I:Table:search parameters
     * @param search I:Table:search
     * @return ArrayList of :Table:
     * @throws DBException 
     */
    public ArrayList<:Table:> search:table:s(I:Table:search search) throws DBException {
        return (ArrayList<:Table:>)this.search(search);
    }

    /**
     * search :table: with I:Table:search parameters, order by orderby sql clause
     * @param search I:Table:search
     * @param orderby sql order by string
     * @return ArrayList of :Table:
     * @throws DBException 
     */
    public ArrayList<:Table:> search:table:s(I:Table:search search, String orderby) throws DBException {
        return (ArrayList<:Table:>)this.search(search, orderby);
    }

    /**
     * Search :table: in database for :table:PK:
     * @param :table:PK: :Table: Primary Key, only valid for the initialized Entity
     * @return true if found in database
     * @throws DBException
     */
    public boolean get:Table:Exists(I:Table:PK :table:PK) throws DBException {
        return super.getEntityExists((:Table:PK):table:PK);
    }

    /**
     * try to insert :Table: in database
     * @param :table: :Table: object
     * @throws DBException
     * @throws DataException
     */
    public void insert:Table:(I:Table: :table:) throws DBException, DataException {
        super.insertEntity(:table:);
:repeatallfields:
:iffieldtype:
:java.lang.Object:
        if(:table:.isUpdated(I:Table:.:COLUMN:)) {
            addBinaryfieldStatement(:table:, I:Table:.:COLUMN:, serialize(:table:.get:Column:()));
        }
:java.lang.Object:
:iffieldtype:
:iffileupload:
        super.addFiletransaction(:table:.getFile_:columnjavaname:(), "file_:columnjavaname:\"
:repeatpkfields:
            + table.getPrimaryKey().get:Column:() + "\"
:repeatpkfields:
        );
:iffileupload:
:repeatallfields:
    }

    /**
     * check if :Table:PK exists
     * insert if not, update if found
     * do not commit transaction
     * @param :table: :Table: object
     * @throws DBException
     * @throws DataException
     */
    public void insertupdate:Table:(I:Table: :table:) throws DBException, DataException {
        if(this.get:Table:Exists(:table:.getPrimaryKey())) {
            super.updateEntity(:table:);
        } else {
            super.insertEntity(:table:);
        }
    }

    /**
     * try to update :Table: in database
     * @param :table: :Table: object
     * @throws DBException
     * @throws DataException
     */
    public void update:Table:(I:Table: :table:) throws DBException, DataException {
        super.updateEntity(:table:);
:repeatallfields:
:iffieldtype:
:java.lang.Object:
        if(:table:.isUpdated(I:Table:.:COLUMN:)) {
            addBinaryfieldStatement(:table:, I:Table:.:COLUMN:, serialize(:table:.get:Column:()));
        }
:java.lang.Object:
:iffieldtype:
:iffileupload:
        super.addDeleteFiletransaction(:table:.getFile_:columnjavaname:(), "file_:columnjavaname:\"
:repeatpkfields:
            + table.getPrimaryKey().get:Column:() + "\"
:repeatpkfields:
        );
:iffileupload:
:repeatallfields:
:repeatallfields:
:iffileupload:
        super.addFiletransaction(:table:.getFile_:columnjavaname:(), "file_:columnjavaname:\"
:repeatpkfields:
            + table.getPrimaryKey().get:Column:() + "\"
:repeatpkfields:
        );
:iffileupload:
:repeatallfields:
    }

    /**
     * try to delete :Table: in database
     * @param :table: :Table: object
     * @throws DBException
     */
    public void delete:Table:(I:Table: :table:) throws DBException {
        cascadedelete:Table:(:table:.getPrimaryKey());
        super.deleteEntity(:table:);
:repeatallfields:
:iffileupload:
        super.addDeleteFiletransaction(:table:.getFile_:columnjavaname:(), "file_:columnjavaname:\"
:repeatpkfields:
            + table.getPrimaryKey().get:Column:() + "\"
:repeatpkfields:
        );
:iffileupload:
:repeatallfields:
    }

    /**
     * check data rules in :Table:, throw DataException with customized message if rules do not apply
     * @param :table: :Table: object
     * @throws DataException
     * @throws DBException
     */
    public void checkDATA(I:Table: :table:) throws DataException, DBException {
        StringBuffer message = new StringBuffer();
:repeatpkfields:
:infk:
        //foreign key :Table:.:Column: - :Pktable:.:Primarycolumn:
:infk:
:notfk:
        //Primary key
:notfk:
:iffieldtype:
:java.lang.String:
        if(:table:.getPrimaryKey().get:Column:()!=null && :table:.getPrimaryKey().get:Column:().length()>I:Table:.SIZE_:COLUMN:) {
            message.append(":Column: is langer dan toegestaan. Max aantal karakters: " + I:Table:.SIZE_:COLUMN: + "\n");
        }
:java.lang.String:
:iffieldtype:
:iffieldtype:
:isnotnullable:
:java.lang.String:
        if(:table:.getPrimaryKey().get:Column:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:java.lang.String:
:float:
:float:
:double:
:double:
:long:
:long:
:int:
:int:
:boolean:
:boolean:
:byte:
:byte:
:other:
        if(:table:.getPrimaryKey().get:Column:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:other:
:isnotnullable:
:iffieldtype:
:repeatpkfields:
:repeatforeignkeys:
:notpk:
:repeatforeignkeyfields:
:iffieldtype:
:java.lang.String:
        if(:table:.get:Uniquename:PK()!=null && :table:.get:Uniquename:PK().get:Primarycolumn:()!=null && :table:.get:Uniquename:PK().get:Primarycolumn:().length()>I:Table:.SIZE_:COLUMN:) {
            message.append(":Column: is langer dan toegestaan. Max aantal karakters: " + I:Table:.SIZE_:COLUMN: + "\n");
        }
:java.lang.String:
:iffieldtype:
:iffieldtype:
:isnotnullable:
:java.lang.String:
        if(:table:.get:Uniquename:PK()==null || :table:.get:Uniquename:PK().get:Primarycolumn:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:java.lang.String:
:float:
:float:
:double:
:double:
:long:
:long:
:int:
:int:
:byte:
:byte:
:boolean:
:boolean:
:other:
        if(:table:.get:Uniquename:PK()==null || :table:.get:Uniquename:PK().get:Primarycolumn:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:other:
:isnotnullable:
:iffieldtype:
:repeatforeignkeyfields:
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:java.lang.String:
        if(:table:.get:Column:()!=null && :table:.get:Column:().length()>I:Table:.SIZE_:COLUMN:) {
            message.append(":Column: is langer dan toegestaan. Max aantal karakters: ").append(I:Table:.SIZE_:COLUMN:).append("\n");
        }
:java.lang.String:
:iffieldtype:
:iffieldtype:
:isnotnullable:
:java.lang.String:
        if(:table:.get:Column:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:java.lang.String:
:float:
:float:
:double:
:double:
:long:
:long:
:int:
:int:
:byte:
:byte:
:boolean:
:boolean:
:other:
        if(:table:.get:Column:()==null) {
            message.append(":Column: mag niet leeg zijn.\n");
        }
:other:
:isnotnullable:
:iffieldtype:
:repeatfields:
        if(message.length()>0) {
            throw new DataException(message.toString());
        }
    }
        
    /**
     * delete all records in tables where :table:PK is used in a primary key
     * @param :table:PK: :Table: primary key
     */
    public void cascadedelete:Table:(I:Table:PK :table:PK) {
:repeatexternalforeignkeys:
:ifforeignkey:
        BL:extablename: bl:uniquename: = new BL:extablename:(this);
        bl:uniquename:.delete4:exfkuniquename:(:table:PK);
:ifforeignkey:
:repeatexternalforeignkeys:
    }

:repeatforeignkeys:
    /**
     * @param :pktable:PK: foreign key for :Pktable:
     * @delete all :Table: Entity objects for :Pktable: in database
     */
    public void delete4:uniquename:(I:Pktable:PK :pktable:PK) {
        super.addStatement(EM:table:.SQLDelete4:uniquename:, :pktable:PK.getSQLprimarykey());
    }

    /**
     * @param :pktable:PK: foreign key for :Pktable:
     * @return all :Table: Entity objects for :Pktable:
     * @throws CustomException
     */
    public ArrayList<:Table:> get:Table:s4:uniquename:(I:Pktable:PK :pktable:PK) throws CustomException {
        return super.getEntities(EM:table:.SQLSelect4:uniquename:, :pktable:PK.getSQLprimarykey());
    }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    /**
     * @param :extablename:PK: parent :Extablename: for child object :Table: Entity
     * @return child :Table: Entity object
     * @throws CustomException
     */
    public :Table: get:Uniquename:(I:Extablename:PK :extablename:PK) throws CustomException {
        :Table:PK :table:PK = new :Table:PK(:repeatforeignkeyfields::extablename:PK.get:Column:():,::repeatforeignkeyfields:);
        return this.get:Table:(:table:PK);
    }

:ifforeignkey:
:repeatexternalforeignkeys:

    /**
     * get all :Table: objects for sqlparameters
     * @param sqlparameters SQLparameters object
     * @param andoroperator "and"/"or"
     * @param sortlist sql sort string
     * @param sortoperator asc/desc
     * @return ArrayList of :Table: objects
     * @throws DBException
     */
    public ArrayList<:Table:> get:Table:s(SQLparameters sqlparameters, String andoroperator, String sortlist, String sortoperator) throws DBException {
        StringBuilder sql = new StringBuilder(EM:table:.SQLSelect);
        ArrayList<Object[]> parameters = sqlparameters.getParameters();
        int l = parameters.size();
        if(l>0) {
            sql.append(" where ");
            for(int i=0; i<l; i++) {
                sql.append(String.valueOf(parameters.get(i)[0])).append(" = :").append(String.valueOf(parameters.get(i)[0])).append(": ");
                if(i<l-1) sql.append(" ").append(andoroperator).append(" ");
            }
        }
        if(sortlist.length()>0) {
            sql.append(" order by ").append(sortlist).append(" ").append(sortoperator);
        }
        return (ArrayList<:Table:>)super.getEntities(sql.toString(), sqlparameters);
    }

    /**
     * delete all :Table: objects for sqlparameters
     * @param sqlparameters SQLparameters object
     * @param andoroperator "and"/"or"
     * @throws DBException
     */
    public void del:Table:(SQLparameters sqlparameters, String andoroperator) throws DBException {
        StringBuilder sql = new StringBuilder("delete from ").append(:Table:.table);
        ArrayList<Object[]> parameters = sqlparameters.getParameters();
        int l = parameters.size();
        if(l>0) {
            sql.append(" where ");
            for(int i=0; i<l; i++) {
                sql.append(String.valueOf(parameters.get(i)[0])).append(" = :").append(String.valueOf(parameters.get(i)[0])).append(": ");
                if(i<l-1) sql.append(" ").append(andoroperator).append(" ");
            }
        }
        this.addStatement(sql.toString(), sqlparameters);
    }

:repeatfields:
:iffileupload:
	/**
     * get file linked to field :columnname:
     * @param :table: :Table:
     * @return Filedata with binary file
     * @throws CustomException
     */
    public Filedata getFile_:columnname:(I:Table: :table:) throws CustomException {
        return super.getFiledata("file_:column:/":repeatpkfields: + :table:.getPrimaryKey().get:Column:() + "/":repeatpkfields:, :table:.get:Column:());
    }

:iffileupload:
:repeatfields:

}
