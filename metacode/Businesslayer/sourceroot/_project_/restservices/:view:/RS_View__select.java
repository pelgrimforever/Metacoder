/*
 * Generated on :metacoder_date:
 */

package :project:.restservices.:table:;

import base.restservices.RS_json_login;
import data.conversion.JSONConversion;
import data.gis.shape.GISConversion;
import data.gis.shape.piPoint;
import :project:.BusinessObject.Logic.*;
import :project:.conversion.json.*;
import :project:.entity.pk.*;
import :project:.interfaces.logicview.I:Table:;
import :project:.interfaces.servlet.I:Table:Operation;
import :project:.usecases.:Table:_usecases;
import :project:.logicview.:Table:;
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
            I:Table: :table:;
            :Table:_usecases :table:usecases = new :Table:_usecases(loggedin);
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            switch(operation) {
                case I:Table:Operation.SELECT_ALL:
                    result = get_all_:table:(:table:usecases);
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

    private String get_all_:table:(:Table:_usecases :table:usecases) throws ParseException, CustomException {
    	return JSON:Table:.toJSONArray(:table:usecases.get_all()).toJSONString();
    }
}

