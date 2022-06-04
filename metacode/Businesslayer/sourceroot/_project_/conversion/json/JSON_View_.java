/*
 * JSON:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */
 
package :project:.conversion.json;

import data.conversion.JSONConversion;
import data.gis.shape.GISConversion;
import :project:.interfaces.logicview.I:Table:;
import :project:.logicview.:Table:;
import :project:.searchentity.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Franky Laseure
 */
public class JSON:Table: {
    
    public static JSONArray toJSONArray(ArrayList :table:s) {
        JSONArray json:table:s = new JSONArray();
        Iterator :table:sI = :table:s.iterator();
        while(:table:sI.hasNext()) {
            json:table:s.add(JSON:Table:.toJSON((:Table:):table:sI.next()));
        }
        return json:table:s;
    }

    /**
     * 
     * @return all keys and fields in a JSONObject
     */
    public static JSONObject toJSON(I:Table: :table:) {
        JSONObject json = new JSONObject();
:repeatfields:
:iffieldtype:
:java.sql.Array::java.sql.Array:
:java.sql.Date:
        if(:table:.get:Column:()!=null) {
	        json.put(":column:", :table:.get:Column:().getTime());
        }
:java.sql.Date:
:java.sql.Time:
        if(:table:.get:Column:()!=null) {
	        json.put(":column:", :table:.get:Column:().getTime());
        }
:java.sql.Time:
:java.sql.Timestamp:
        if(:table:.get:Column:()!=null) {
	        json.put(":column:", :table:.get:Column:().getTime());
        }
:java.sql.Timestamp:
:long:
        json.put(":column:", String.valueOf(:table:.get:Column:()));
:long:
:customfieldtype:
        if(:table:.get:Column:()!=null) {
            json.put(":column:", GISConversion.toJSON(:table:.get:Column:()));
        }
:customfieldtype:
:other:
        json.put(":column:", :table:.get:Column:());
:other:
:iffieldtype:
:repeatfields:
//Custom code, do not change this line
//Custom code, do not change this line
        return json;
    }

    public static :Table: to:Table:(JSONObject json) {
        :Table: :table: = new :Table:();
:repeatfields:
:iffieldtype:
:java.sql.Array::java.sql.Array:
:java.lang.String:
        :table:.set:Column:(JSONConversion.getString(json, ":column:"));
:java.lang.String:
:java.sql.Time:
        :table:.set:Column:(JSONConversion.getTime(json, ":column:"));
:java.sql.Time:
:java.sql.Timestamp:
        :table:.set:Column:(JSONConversion.getTimestamp(json, ":column:"));
:java.sql.Timestamp:
:java.sql.Date:
        :table:.set:Column:(JSONConversion.getDate(json, ":column:"));
:java.sql.Date:
:customfieldtype:
        :table:.set:Column:(GISConversion.topiShape((JSONObject)json.get(":column:")));
:customfieldtype:
:other:
        :table:.set:Column:(JSONConversion.get:columntype:(json, ":column:"));
:other:
:iffieldtype:
:repeatfields:
        return :table:;
    }

    /**
     * 
     * @param json: JSONObject with the :Table:search parameters
     * @return 
     */
    public static :Table:search to:Table:search(JSONObject json) {
        :Table:search :table:search = new :Table:search();
        :table:search.setANDORoperator(JSONConversion.getbyte(json, "andor"));
        :table:search.setMaxresults(JSONConversion.getint(json, "maxresults"));
        :table:search.setDocount(JSONConversion.getboolean(json, "docount"));
        JSONObject fss = (JSONObject)json.get("fields");
        JSONObject field;
:repeatfields:
:iffieldtype:
:java.lang.String:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            String[] valuearray = JSONConversion.getStringvalues(field);
            byte compareoperator = JSONConversion.getbyte(field, "compareoperator");
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, compareoperator, andor);
        }
:java.lang.String:
:java.sql.Date:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Date[] valuearray = JSONConversion.getDatevalues(field);
            byte[] operators = JSONConversion.getDateoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:java.sql.Date: 
:java.sql.Time:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Time[] valuearray = JSONConversion.getTimevalues(field);
            byte[] operators = JSONConversion.getTimeoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:java.sql.Time: 
:int:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Double[] valuearray = JSONConversion.getDoublevalues(field);
            byte[] operators = JSONConversion.getNumberoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:int: 
:long:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Double[] valuearray = JSONConversion.getDoublevalues(field);
            byte[] operators = JSONConversion.getNumberoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:long: 
:double:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Double[] valuearray = JSONConversion.getDoublevalues(field);
            byte[] operators = JSONConversion.getNumberoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:double: 
:float:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Double[] valuearray = JSONConversion.getDoublevalues(field);
            byte[] operators = JSONConversion.getNumberoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:float: 
:boolean:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            boolean value = JSONConversion.getBooleanvalue(field);
            :table:search.:columnjavaname:(value);
        }
:boolean: 
:other:
:other:
:iffieldtype:
:repeatfields:
        return :table:search;
    }
}

