/*
 * Generated on :metacoder_date:
 */

package :project:.restservices;

import base.servlets.Securitycheck;
import data.conversion.JSONConversion;
import data.gis.shape.GISConversion;
import data.gis.shape.piPoint;
import :project:.BusinessObject.Logic.*;
import :project:.conversion.json.*;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.searchentity.I:Table:search;
import :project:.interfaces.servlet.I:Table:Operation;
import :project:.logicentity.:Table:;
import :project:.searchentity.:Table:search;
import :project:.servlets.DataServlet;
import :project:.usecases.Security_usecases;
import general.exception.CustomException;
import general.exception.DataException;
import general.exception.DBException;
import java.sql.Date;
import java.sql.Time;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Franky Laseure
 */
@Path("rs:table:")
public class RS:Table: {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String jsonstring) {
        String result = "";
        BL:table: bl:table: = new BL:table:();
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject)parser.parse(jsonstring);
            JSONObject jsonoperation = (JSONObject)json.get("operation");
            byte operationtype = JSONConversion.getbyte(jsonoperation, "type");
            byte operation = JSONConversion.getbyte(jsonoperation, "operation");
            I:Table:PK :table:PK;
            I:Table: :table:;
            boolean loggedin = RSsecurity.check(json);
            bl:table:.setAuthenticated(loggedin);
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            switch(operationtype) {
                case DataServlet.SELECT:
                    switch(operation) {
                        case I:Table:Operation.SELECT_COUNT:
                            JSONObject jsoncount = new JSONObject();
                            jsoncount.put("recordcount", bl:table:.count());
                            result = jsoncount.toJSONString();
                            break;
                        case I:Table:Operation.SELECT_ALL:
                            result = JSON:Table:.toJSONArray(bl:table:.get:Table:s()).toJSONString();
                            break;
                        case I:Table:Operation.SELECT_:TABLE::
                            :table:PK = (I:Table:PK)JSON:Table:.to:Table:PK((JSONObject)json.get(":table:pk"));
                            result = JSON:Table:.toJSON(bl:table:.get:Table:(:table:PK)).toJSONString();
                            break;
:repeatforeignkeys:
                        case I:Table:Operation.SELECT_:Uniquename::
                            I:Pktable:PK :uniquename:PK = (I:Pktable:PK)JSON:Pktable:.to:Pktable:PK((JSONObject)json.get(":pktable:pk"));
                            result = JSON:Table:.toJSONArray(bl:table:.get:Table:s4:uniquename:(:uniquename:PK)).toJSONString();
                            break;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
                        case I:Table:Operation.SELECT_:Uniquename::
                            I:Extablename:PK :uniquename:PK = (I:Extablename:PK)JSON:Extablename:.to:Extablename:PK((JSONObject)json.get(":extablename:pk"));
                            result = JSON:Table:.toJSON(bl:table:.get:Uniquename:(:uniquename:PK)).toJSONString();
                            break;
:ifforeignkey:
:repeatexternalforeignkeys:
                        case I:Table:Operation.SELECT_SEARCH:
                            I:Table:search search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
                            result = JSON:Table:.toJSONArray(bl:table:.search(search)).toJSONString();
                            break;
                        case I:Table:Operation.SELECT_SEARCHCOUNT:
                            I:Table:search :table:search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
                            JSONObject jsonsearchcount = new JSONObject();
                            jsonsearchcount.put("recordcount", bl:table:.searchcount(:table:search));
                            result = jsonsearchcount.toJSONString();
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.INSERT:
                    switch(operation) {
                        case I:Table:Operation.INSERT_:TABLE::
                            :table: = (I:Table:)JSON:Table:.to:Table:((JSONObject)json.get(":table:"));
                            bl:table:.insert:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.UPDATE:
                    switch(operation) {
                        case I:Table:Operation.UPDATE_:TABLE::
                            JSONObject json:table: = (JSONObject)json.get(":table:");
                            :table:PK = JSON:Table:.to:Table:PK((JSONObject)json:table:.get("PK"));
                            :table: = bl:table:.get:Table:(:table:PK);
                            JSON:Table:.update:Table:(:table:, json:table:);
                            bl:table:.update:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.DELETE:
                    switch(operation) {
                        case I:Table:Operation.DELETE_:TABLE::
                            :table: = (I:Table:)JSON:Table:.to:Table:((JSONObject)json.get(":table:"));
                            bl:table:.delete:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.SECURESELECT:
                    switch(operation) {
                        case I:Table:Operation.SELECT_COUNT:
                            JSONObject jsoncount = new JSONObject();
                            jsoncount.put("recordcount", bl:table:.count());
                            result = jsoncount.toJSONString();
                            break;
                        case I:Table:Operation.SELECT_ALL:
                            result = JSON:Table:.toJSONArray(bl:table:.get:Table:s()).toJSONString();
                            break;
                        case I:Table:Operation.SELECT_:TABLE::
                            :table:PK = (I:Table:PK)JSON:Table:.to:Table:PK((JSONObject)json.get(":table:pk"));
                            result = JSON:Table:.toJSON(bl:table:.get:Table:(:table:PK)).toJSONString();
                            break;
:repeatforeignkeys:
                        case I:Table:Operation.SELECT_:Uniquename::
                            I:Pktable:PK :uniquename:PK = (I:Pktable:PK)JSON:Pktable:.to:Pktable:PK((JSONObject)json.get(":pktable:pk"));
                            result = JSON:Table:.toJSONArray(bl:table:.get:Table:s4:uniquename:(:uniquename:PK)).toJSONString();
                            break;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
                        case I:Table:Operation.SELECT_:Uniquename::
                            I:Extablename:PK :uniquename:PK = (I:Extablename:PK)JSON:Extablename:.to:Extablename:PK((JSONObject)json.get(":extablename:pk"));
                            result = JSON:Table:.toJSON(bl:table:.get:Uniquename:(:uniquename:PK)).toJSONString();
                            break;
:ifforeignkey:
:repeatexternalforeignkeys:
                        case I:Table:Operation.SELECT_SEARCH:
                            I:Table:search search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
                            result = JSON:Table:.toJSONArray(bl:table:.search(search)).toJSONString();
                            break;
                        case I:Table:Operation.SELECT_SEARCHCOUNT:
                            I:Table:search :table:search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
                            JSONObject jsonsearchcount = new JSONObject();
                            jsonsearchcount.put("recordcount", bl:table:.searchcount(:table:search));
                            result = jsonsearchcount.toJSONString();
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.SECUREINSERT:
                    switch(operation) {
                        case I:Table:Operation.INSERT_:TABLE::
                            :table: = (I:Table:)JSON:Table:.to:Table:((JSONObject)json.get(":table:"));
                            bl:table:.secureinsert:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.SECUREUPDATE:
                    switch(operation) {
                        case I:Table:Operation.UPDATE_:TABLE::
                            JSONObject json:table: = (JSONObject)json.get(":table:");
                            :table:PK = JSON:Table:.to:Table:PK((JSONObject)json:table:.get("PK"));
                            :table: = bl:table:.get:Table:(:table:PK);
                            JSON:Table:.update:Table:(:table:, json:table:);
                            bl:table:.secureupdate:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
                case DataServlet.SECUREDELETE:
                    switch(operation) {
                        case I:Table:Operation.DELETE_:TABLE::
                            :table: = (I:Table:)JSON:Table:.to:Table:((JSONObject)json.get(":table:"));
                            bl:table:.securedelete:Table:(:table:);
                            result = returnstatus("OK");
                            break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
                    }
                    break;
            }
        }
        catch(ParseException e) {
            result = returnstatus(e.getMessage());
        }
        catch(CustomException e) {
            result = returnstatus(e.getMessage());
        }
        return result;
    }

    private String returnstatus(String status) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        return json.toJSONString();
    }
}

