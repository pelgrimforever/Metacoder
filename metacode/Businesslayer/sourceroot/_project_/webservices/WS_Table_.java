/*
 * WS:Table:.java
 *
 * Created on Dec 23, 2012, 7:24 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.webservices;

import :project:.BusinessObject.Logic.*;
import :project:.conversion.json.*;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.webservice.WSI:Table:;
import :project:.logicentity.:Table:;
import :project:.searchentity.:Table:search;
import general.exception.CustomException;
import general.exception.DataException;
import general.exception.DBException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Franky Laseure
 */
@WebService(endpointInterface = ":project:.interfaces.webservice.WSI:Table:")
public class WS:Table: implements WSI:Table: {

    private JSONArray toJSONArray(ArrayList :table:s) {
        JSONArray json:table:s = new JSONArray();
        Iterator :table:sI = :table:s.iterator();
        while(:table:sI.hasNext()) {
            json:table:s.add(JSON:Table:.toJSON((:Table:):table:sI.next()));
        }
        return json:table:s;
    }

    /**
     * Web service operation
     */
    //@WebMethod(operationName = "get:Table:s")
    @Override
    public String get:Table:s() {
        try {
            BL:table: bl:table: = new BL:table:();
            ArrayList :table:s = bl:table:.getAll();
            JSONArray json:table:s = toJSONArray(:table:s);
            return json:table:s.toJSONString();
        }
        catch(DBException e) {
            return null;
        }
    }

    //@WebMethod(operationName = "search")
    @Override
    public String search(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        String result = "";
        :Table: :table:;
        try {
            :Table:search :table:search = JSON:Table:.to:Table:search((JSONObject)parser.parse(json));
            ArrayList :table:s = bl:table:.search(:table:search);
            JSONArray json:table:s = toJSONArray(:table:s);
            result = json:table:s.toJSONString();
        }
        catch(ParseException e) {
            result = "";
        }
        catch(DBException e) {
            result = "";
        }
        return result;
    }

    //@WebMethod(operationName = "get:Table:")
    @Override
    public String get:Table:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        String result = "";
        :Table: :table:;
        try {
            :Table:PK :table:PK = JSON:Table:.to:Table:PK((JSONObject)parser.parse(json));
            :table: = bl:table:.get:Table:(:table:PK);
            if(:table:!=null) {
                result = JSON:Table:.toJSON(:table:).toJSONString();
            }
        }
        catch(ParseException e) {
        }
        catch(DBException e) {
        }
        return result;
    }

    //@WebMethod(operationName = "insert:Table:")
    @Override
    public void insert:Table:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        try {
            I:Table: :table: = JSON:Table:.to:Table:((JSONObject)parser.parse(json));
            bl:table:.insert:Table:(:table:);
        }
        catch(ParseException e) {
        }
        catch(DataException e) {
        }
        catch(DBException e) {
        }
    }

    //@WebMethod(operationName = "update:Table:")
    @Override
    public void update:Table:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        try {
            I:Table: :table: = JSON:Table:.to:Table:((JSONObject)parser.parse(json));
            bl:table:.update:Table:(:table:);
        }
        catch(ParseException e) {
        }
        catch(DataException e) {
        }
        catch(DBException e) {
        }
    }

    //@WebMethod(operationName = "delete:Table:")
    @Override
    public void delete:Table:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        try {
            I:Table: :table: = JSON:Table:.to:Table:((JSONObject)parser.parse(json));
            bl:table:.delete:Table:(:table:);
        }
        catch(ParseException e) {
        }
        catch(DBException e) {
        }
    }

:repeatforeignkeys:
    //@WebMethod(operationName = "get:Table:s4:uniquename:")
    @Override
    public String get:Table:s4:uniquename:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        :Table: :table:;
        try {
            I:Pktable:PK :uniquename:PK = JSON:Pktable:.to:Pktable:PK((JSONObject)parser.parse(json));
            ArrayList :table:s = bl:table:.get:Table:s4:uniquename:(:uniquename:PK);
            JSONArray json:table:s = toJSONArray(:table:s);
            return json:table:s.toJSONString();
        }
        catch(ParseException e) {
            return null;
        }
        catch(DBException e) {
            return null;
        }
        catch(CustomException e) {
            return null;
        }
    }

    //@WebMethod(operationName = "delete4:uniquename:")
    @Override
    public void delete4:uniquename:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        :Table: :table:;
        try {
            I:Pktable:PK :uniquename:PK = JSON:Pktable:.to:Pktable:PK((JSONObject)parser.parse(json));
            bl:table:.delete4:uniquename:(:uniquename:PK);
        }
        catch(ParseException e) {
        }
    }

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    //@WebMethod(operationName = "get:Table:s4:uniquename:")
    @Override
    public String get:Table:s4:uniquename:(String json) {
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        :Table: :table:;
        try {
            String result = null;
            I:Extablename:PK :uniquename:PK = JSON:Extablename:.to:Extablename:PK((JSONObject)parser.parse(json));
            :table: = (:Table:)bl:table:.get:Uniquename:(:uniquename:PK);
            if(:table:!=null) {
                result = JSON:Table:.toJSON(:table:).toJSONString();
            }
            return result;
        }
        catch(ParseException e) {
            return null;
        }
        catch(DBException e) {
            return null;
        }
        catch(CustomException e) {
            return null;
        }
    }

:ifforeignkey:
:repeatexternalforeignkeys:

}

