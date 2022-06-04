/*
 * EMalliance_default.java
 *
 * Created on Okt 8, 2021
 * Generated on :metacoder_date:
 *
 */
package :project:.conversion.entity.def;

import data.gis.shape.*;
import data.interfaces.db.View;
import data.json.piJson;
import db.ViewMapper;
import :project:.logicview.:Table:;
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
public class EM:table:_default implements ViewMapper {
    
    public static final String SQLSelectAll = "select :table_o:.* from :table_o:";
	  
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
        :Table: :table: = new :Table:();
        if(dbresult!=null) {
            try {
:repeatfields:
:iffieldtype:
:java.sql.Time:
                :table:.set:Column:(dbresult.getString(":column:") == null ? null : Time.valueOf(dbresult.getString(":column:")));
:java.sql.Time:
:customfieldtype:
                Object o_:columnjavaname: = dbresult.:getcolumndbfunction:;
                if(o_:columnjavaname:!=null) {
                    :columntype: c_:columnjavaname: = new :columncustomtype:((:columncast:)o_:columnjavaname:);
                    :table:.set:Column:(c_:columnjavaname:.abstractclone());
                }
:customfieldtype:
:other:
                :table:.set:Column:(dbresult.:getcolumndbfunction:);
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

