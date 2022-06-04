/*
 * Generated on :metacoder_date:
 */

package :project:.restservices.:table:;

import base.restservices.RS_json_login;
import data.conversion.JSONConversion;
import data.gis.shape.GISConversion;
import data.gis.shape.piPoint;
import :project:.conversion.json.*;
import :project:.entity.pk.*;
import :project:.usecases.:Table:_usecases;
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
import org.json.simple.parser.ParseException;

/**
 * @author Franky Laseure
 */
@Path("rs:table:_select")
public class RS:Table:_select extends RS_json_login {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String jsonstring) {
        try {
            Consume_jsonstring(jsonstring);
            setLoggedin(Security_usecases.check_authorization(authorisationstring));
            I:Table:PK :table:PK;
            I:Table: :table:;
            :Table:_usecases :table:usecases = new :Table:_usecases(loggedin);
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            switch(operation) {
                case I:Table:Operation.SELECT_COUNT:
                    result = count_records(:table:usecases);
                    break;
                case I:Table:Operation.SELECT_ALL:
                    result = get_all_:table:(:table:usecases);
                    break;
                case I:Table:Operation.SELECT_:TABLE::
                    result = get_:table:_with_primarykey(:table:usecases, json);
                    break;
:repeatforeignkeys:
                case I:Table:Operation.SELECT_:Uniquename::
                    result = get_:table:_with_foreignkey_:uniquename:(:table:usecases, json);
                    break;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
                case I:Table:Operation.SELECT_:Uniquename::
                    result = get_:table:_with_externalforeignkey_:uniquename:(:table:usecases, json);
                    break;
:ifforeignkey:
:repeatexternalforeignkeys:
                case I:Table:Operation.SELECT_SEARCH:
                    result = search_:table:(:table:usecases, json);
                    break;
                case I:Table:Operation.SELECT_SEARCHCOUNT:
                    result = search_:table:_count(:table:usecases, json);
                    break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            }
        }
        catch(ParseException | CustomException | IOException e) {
            setReturnstatus(e.getMessage());
        }
        return result;
    }

//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    private String count_records(:Table:_usecases :table:usecases) throws ParseException, CustomException {
        JSONObject jsoncount = new JSONObject();
        jsoncount.put("recordcount", :table:usecases.count());
        return jsoncount.toJSONString();
    }
    
    private String get_all_:table:(:Table:_usecases :table:usecases) throws ParseException, CustomException {
    	return JSON:Table:.toJSONArray(:table:usecases.get_all()).toJSONString();
    }
    
    private String get_:table:_with_primarykey(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Table:PK :table:PK = (I:Table:PK)JSON:Table:.to:Table:PK((JSONObject)json.get(":table:pk"));
	return JSON:Table:.toJSON(:table:usecases.get_:table:_by_primarykey(:table:PK)).toJSONString();
    }
    
:repeatforeignkeys:
    private String get_:table:_with_foreignkey_:uniquename:(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Pktable:PK :uniquename:PK = (I:Pktable:PK)JSON:Pktable:.to:Pktable:PK((JSONObject)json.get(":pktable:pk"));
        return JSON:Table:.toJSONArray(:table:usecases.get_:table:_with_foreignkey_:uniquename:(:uniquename:PK)).toJSONString();
    }
    
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    private String get_:table:_with_externalforeignkey_:uniquename:(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Extablename:PK :uniquename:PK = (I:Extablename:PK)JSON:Extablename:.to:Extablename:PK((JSONObject)json.get(":extablename:pk"));
        return JSON:Table:.toJSON(:table:usecases.get_:table:_with_externalforeignkey_:uniquename:(:uniquename:PK)).toJSONString();
    }
    
:ifforeignkey:
:repeatexternalforeignkeys:
    private String search_:table:(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Table:search search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
        return JSON:Table:.toJSONArray(:table:usecases.search_:table:(search)).toJSONString();
    }
    
    private String search_:table:_count(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Table:search :table:search = (I:Table:search)JSON:Table:.to:Table:search((JSONObject)json.get("search"));
        JSONObject jsonsearchcount = new JSONObject();
        jsonsearchcount.put("recordcount", :table:usecases.search_:table:_count(:table:search));
        return jsonsearchcount.toJSONString();
    }
}

