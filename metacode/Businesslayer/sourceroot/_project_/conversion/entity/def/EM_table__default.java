/*
 * EMalliance_default.java
 *
 * Created on Okt 8, 2021
 * Generated on :metacoder_date:
 *
 */
package :project:.conversion.entity.def;

import data.gis.shape.*;
import data.interfaces.db.LogicEntity;
import data.json.piJson;
import db.TableMapper;
import :project:.entity.pk.*;
import :project:.logicentity.:Table:;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * EM:table:_default
 * Maps SQL ResultSet to :project:.logicentity objects
 * @author Franky Laseure
 */
public class EM:table:_default implements TableMapper {
    
    public static final String SQLWhere1 = ":repeatpkfields::column_o: = ::table_o:.:column_o:::separator: and :separator::repeatpkfields:";
    public static final String SQLSelect1 = "select :table_o:.* from :table_o: where " + SQLWhere1;
    public static final String SQLSelectPKexists = "select count(*) as count from :table_o: where " + SQLWhere1;
    public static final String SQLSelectAll = "select :table_o:.* from :table:";

    public static final String SQLSelect = "select :table_o:.* from :table_o:";
:repeatforeignkeys:
    public static final String SQLWhere:uniquename: = ":repeatforeignkeyfields::foreigncolumn_o: = ::pktable_o:.:primarycolumn_o:::separator: and :separator::repeatforeignkeyfields:";
:repeatforeignkeys:

//Custom code, do not change this line
    public static final String OrderBy = " order by :repeatpkfields::column_o::,::repeatpkfields:";
//Custom code, do not change this line

:repeatforeignkeys:
    public static final String SQLSelect4:uniquename: = "select * from :table_o: where " + SQLWhere:uniquename: + OrderBy;
    public static final String SQLDelete4:uniquename: = "delete from :table_o: where " + SQLWhere:uniquename:;
:repeatforeignkeys:

    /**
     * 
     * @return SQL where clause for one :Table: (=Primarykey)
     */
    @Override
    public String getSQLWhere1() { return SQLWhere1; };

    /**
     * 
     * @return SQL select statement for one :Table: (=Primarykey)
     */
    @Override
    public String getSQLSelect1() { return SQLSelect1; };

    /**
     * @return Select statement for Primary key, with count field as result
     * count = 1: exists
     * count = 0: not found
     */
    @Override
    public String getSQLPKExcists() { return SQLSelectPKexists; };
    
    /**
     * 
     * @return SQL select statement for all :Table:s
     */
    @Override
    public String getSQLSelectAll() { return SQLSelectAll; };

    /**
     * Map ResultSet Field values to :Table:
     * @param dbresult: Database ResultSet
     */
    @Override
    public Object mapResultSet2Entity(ResultSet dbresult) throws SQLException {
        :Table:PK :table:PK = null;
        :Table: :table:;
        if(dbresult==null) {
            :table: = new :Table:(:table:PK);
        } else {
            try {
                :table:PK = new :Table:PK(:repeatpkfields::columncast:dbresult.:getcolumndbfunction::,::repeatpkfields:);
                :table: = new :Table:(:table:PK);
:repeatforeignkeys:
:notpk:
                :table:.init:Uniquename:PK(new :Pktable:PK(:repeatforeignkeyfields::iffieldtype::java.sql.Time:dbresult.getString(":column_o:") == null ? null : Time.valueOf(dbresult.getString(":column_o:")):java.sql.Time::other:dbresult.:getcolumndbfunction::other::iffieldtype::,::repeatforeignkeyfields:));
                if(dbresult.wasNull()) :table:.set:Uniquename:PK(null);                
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:java.sql.Time:
                :table:.init:Column:(dbresult.getString(":column:") == null ? null : Time.valueOf(dbresult.getString(":column:")));
:java.sql.Time:
:piJson:
                String o_:columnjavaname: = dbresult.getString(":column:");
                if(o_:columnjavaname:!=null) {
                    try {
                        Object :columnjavaname:_o_a = (new JSONParser()).parse(o_:columnjavaname:);
                        :table:.init:Column:(piJson.parse(:columnjavaname:_o_a));
                    }
                    catch(ParseException e) {
                    }                    
                }
:piJson:
:piPoint:
                Object o_:columnjavaname: = dbresult.:getcolumndbfunction:;
                if(o_:columnjavaname:!=null) {
                    :columntype: c_:columnjavaname: = new :columncustomtype:((:columncast:)o_:columnjavaname:);
                    :table:.init:Column:(c_:columnjavaname:.abstractclone());
                }
:piPoint:
:piShape:
                Object o_:columnjavaname: = dbresult.:getcolumndbfunction:;
                if(o_:columnjavaname:!=null) {
                    :columntype: c_:columnjavaname: = new :columncustomtype:((:columncast:)o_:columnjavaname:);
                    :table:.init:Column:(c_:columnjavaname:.abstractclone());
                }
:piShape:
:java.lang.Object:
                Object bytea:column: = dbresult.getBytes(":column_o:");
                if(bytea:column:!=null) {
                    :table:.init:Column:(deserialize((byte[])bytea:column:));
                }
:java.lang.Object:
:other:
                :table:.init:Column:(dbresult.:getcolumndbfunction:);
:other:
:iffieldtype:
:repeatfields:
            }
            catch(SQLException sqle) {
                throw sqle;
            }
        }
        return :table:;
    }

}

