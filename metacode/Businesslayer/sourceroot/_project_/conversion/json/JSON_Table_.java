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
import data.interfaces.db.EntityPK;
import data.interfaces.db.Fieldsearcher;
import data.json.piJson;
import :project:.entity.pk.:Table:PK;
import :project:.interfaces.entity.pk.I:Table:PK;
import :project:.interfaces.logicentity.I:Table:;
import :project:.logicentity.:Table:;
import :project:.searchentity.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * JSON fields are by default ignored
 * @author Franky Laseure
 */
public class JSON:Table: {
    
    public static JSONArray toJSONArray(ArrayList :table:s) {
        JSONArray json:table:s = new JSONArray();
        Iterator :table:sI = :table:s.iterator();
        while(:table:sI.hasNext()) {
            json:table:s.add(toJSON((:Table:):table:sI.next()));
        }
        return json:table:s;
    }

    /**
     * 
     * @return all keys and fields in a JSONObject
     */
    public static JSONObject toJSON(I:Table:PK :table:PK) {
        JSONObject json = null;
        if(:table:PK!=null) {
            json = new JSONObject();
:repeatpkfields:
:iffieldtype:
:java.sql.Date:
            if(:table:PK.get:Column:()!=null) {
                json.put(":column:", :table:PK.get:Column:().getTime());
            }
:java.sql.Date:
:java.sql.Time:
            if(:table:PK.get:Column:()!=null) {
                json.put(":column:", :table:PK.get:Column:().getTime());
            }
:java.sql.Time:
:java.sql.Timestamp:
            if(:table:PK.get:Column:()!=null) {
                json.put(":column:", :table:PK.get:Column:().getTime());
            }
:java.sql.Timestamp:
:long:
            json.put(":column:", String.valueOf(:table:PK.get:Column:()));
:long:
:other:
            json.put(":column:", :table:PK.get:Column:());
:other:
:iffieldtype:
:repeatpkfields:
        }
        return json;
    }

    /**
     * 
     * @return all keys and fields in a JSONObject
     */
    public static JSONObject toJSON(I:Table: :table:) {
        JSONObject json = new JSONObject();
        json.put("PK", toJSON(:table:.getPrimaryKey()));
:repeatforeignkeys:
:notpk:
        json.put(":uniquename:PK", JSON:Pktable:.toJSON(:table:.get:Uniquename:PK()));
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
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
:piJson:
        if(:table:.get:Column:()!=null) {
            json.put(":column:", "");
        }
:piJson:
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

    /**
     * 
     * @return all keys and fields in a JSONObject
     */
    public static JSONObject toJSON(:Table:search :table:search) {
        JSONObject json = new JSONObject();
        if(:table:search.used()) {
            byte andoroperator = :table:search.getAndoroperator();
            int maxresults = :table:search.getMaxresults();
            boolean docount = :table:search.getDocount();
            Iterator<EntityPK> primarykeysI = :table:search.getPrimarykeys().iterator();
            Iterator<Fieldsearcher> fieldsearchersI = :table:search.getFieldsearchers().iterator();
            EntityPK primarykey;
            Fieldsearcher fieldsearcher;
            
            json.put("andor", andoroperator);
            json.put("maxresults", maxresults);
            json.put("docount", docount);
            JSONArray pks = new JSONArray();
            int i = 0;
            while(primarykeysI.hasNext()) {
                primarykey = primarykeysI.next();
                pks.add(primarykey.getKeystring());
            }
            json.put("primarykeys", pks);
            JSONObject fss = new JSONObject();
            while(fieldsearchersI.hasNext()) {
                fieldsearcher = fieldsearchersI.next();
                if(fieldsearcher.used()) {
                    fss.put(fieldsearcher.getShortFieldname(), JSONConversion.toJSON(fieldsearcher));
                }
            }
            json.put("fields", fss);
            JSONObject kss = new JSONObject();
:repeatforeignkeys:
            if(:table:search.get:Uniquename:search()!=null && :table:search.get:Uniquename:search().used()) {
                kss.put(":uniquename:searcher", JSON:Pktable:.toJSON((:Pktable:search):table:search.get:Uniquename:search()));
            }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
            if(:table:search.get:Uniquename:search()!=null && :table:search.get:Uniquename:search().used()) {
                kss.put(":uniquename:searcher", JSON:Extablename:.toJSON((:Extablename:search):table:search.get:Uniquename:search()));
            }
:ifforeignkey:
:ifrelational:
            if(:table:search.getRel:Uniquename:search()!=null && :table:search.getRel:Uniquename:search().used()) {
                kss.put(":uniquename:searcher", JSON:Reltablename:.toJSON((:Reltablename:search):table:search.getRel:Uniquename:search()));
            }
:ifrelational:
:repeatexternalforeignkeys:
            json.put("keysearch", kss);
        }
        return json;
    }

    /**
     * 
     * @param json: JSONObject with the Filmsearch parameters
     * @return 
     */
    public static :Table:search to:Table:search(JSONObject json) {
        :Table:search :table:search = new :Table:search();
        :table:search.setANDORoperator(JSONConversion.getbyte(json, "andor"));
        :table:search.setMaxresults(JSONConversion.getint(json, "maxresults"));
        :table:search.setDocount(JSONConversion.getboolean(json, "docount"));
        JSONArray pks = (JSONArray)json.get("primarykeys");
        for(int i=0; i<pks.size(); i++) {
            :table:search.addPrimarykey(:Table:PK.getKey((String)pks.get(i)));
        }
        JSONObject fss = (JSONObject)json.get("fields");
        JSONObject field;
:repeatpkfields:
:notfk:
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
:java.sql.Timestamp:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Timestamp[] valuearray = JSONConversion.getTimestampvalues(field);
            byte[] operators = JSONConversion.getTimeoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:java.sql.Timestamp: 
:int:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            long[] valuearray = JSONConversion.getLongvalues(field);
            byte[] operators = JSONConversion.getNumberoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:int: 
:long:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            long[] valuearray = JSONConversion.getLongvalues(field);
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
:notfk:
:repeatpkfields:
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
:java.sql.Timestamp:
        field = (JSONObject)fss.get(":column:");
        if(field!=null) {
            Timestamp[] valuearray = JSONConversion.getTimestampvalues(field);
            byte[] operators = JSONConversion.getTimeoperators(field);
            byte andor = JSONConversion.getbyte(field, "andor");
            :table:search.:columnjavaname:(valuearray, operators, andor);
        }
:java.sql.Timestamp: 
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
        JSONObject kss = (JSONObject)json.get("keysearch");
        JSONArray keysearch;
:repeatforeignkeys:
        keysearch = (JSONArray)kss.get(":uniquename:searcher");
        if(keysearch!=null) {
            for(int i=0; i<keysearch.size(); i++) {
                :Pktable:search :uniquename:search = JSON:Pktable:.to:Pktable:search((JSONObject)keysearch.get(i));
                :table:search.:uniquename:(:uniquename:search);
            }
        }
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
        keysearch = (JSONArray)kss.get(":uniquename:searcher");
        if(keysearch!=null) {
            for(int i=0; i<keysearch.size(); i++) {
                :Extablename:search :uniquename:search = JSON:Extablename:.to:Extablename:search((JSONObject)keysearch.get(i));
                :table:search.:uniquename:(:uniquename:search);
            }
        }
:ifforeignkey:
:ifrelational:
        keysearch = (JSONArray)kss.get(":uniquename:searcher");
        if(keysearch!=null) {
            for(int i=0; i<keysearch.size(); i++) {
                :Extablename:search :uniquename:search = JSON:Extablename:.to:Extablename:search((JSONObject)keysearch.get(i));
                :table:search.rel:uniquename:(:uniquename:search);
            }
        }
:ifrelational:
:repeatexternalforeignkeys:
        return :table:search;
    }
    
    public static :Table:PK to:Table:PK(JSONObject json) {
        :Table:PK :table:PK = null;
        if(json!=null) {
            :table:PK = new :Table:PK(:repeatpkfields::iffieldtype::java.lang.String:JSONConversion.getString(json, ":column:"):java.lang.String::java.sql.Time:JSONConversion.getTime(json, ":column:"):java.sql.Time::java.sql.Timestamp:JSONConversion.getTimestamp(json, ":column:"):java.sql.Timestamp::java.sql.Date:JSONConversion.getDate(json, ":column:"):java.sql.Date::other:JSONConversion.get:columntype:(json, ":column:"):other::iffieldtype::,::repeatpkfields:);
        }
        return :table:PK;
    }

    public static :Table: to:Table:(JSONObject json) {
        :Table: :table: = new :Table:(to:Table:PK((JSONObject)json.get("PK")));
        update:Table:(:table:, json);
        return :table:;
    }

    public static void update:Table:(I:Table: :table:, JSONObject json) {
:repeatforeignkeys:
:notpk:
        :table:.set:Uniquename:PK(JSON:Pktable:.to:Pktable:PK((JSONObject)json.get(":uniquename:PK")));
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
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
:java.lang.Object:
        :table:.set:Column:(JSONConversion.getObject(json, ":column:"));
:java.lang.Object:
:piJson:
:piJson:
:other:
        :table:.set:Column:(JSONConversion.get:columntype:(json, ":column:"));
:other:
:iffieldtype:
:repeatfields:
    }

    public static :Table: init:Table:(JSONObject json) {
        :Table: :table: = new :Table:(to:Table:PK((JSONObject)json.get("PK")));
:repeatforeignkeys:
:notpk:
        :table:.init:Uniquename:PK(JSON:Pktable:.to:Pktable:PK((JSONObject)json.get(":uniquename:PK")));
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:java.lang.String:
        :table:.init:Column:(JSONConversion.getString(json, ":column:"));
:java.lang.String:
:java.sql.Time:
        :table:.init:Column:(JSONConversion.getTime(json, ":column:"));
:java.sql.Time:
:java.sql.Timestamp:
        :table:.init:Column:(JSONConversion.getTimestamp(json, ":column:"));
:java.sql.Timestamp:
:java.sql.Date:
        :table:.init:Column:(JSONConversion.getDate(json, ":column:"));
:java.sql.Date:
:piPoint:
        :table:.init:Column:(GISConversion.topiShape((JSONObject)json.get(":column:")));
:piPoint:
:piShape:
        :table:.init:Column:(GISConversion.topiShape((JSONObject)json.get(":column:")));
:piShape:
:java.lang.Object:
        :table:.init:Column:(JSONConversion.getObject(json, ":column:"));
:java.lang.Object:
:piJson:
:piJson:
:other:
        :table:.init:Column:(JSONConversion.get:columntype:(json, ":column:"));
:other:
:iffieldtype:
:repeatfields:
        return :table:;
    }
}

