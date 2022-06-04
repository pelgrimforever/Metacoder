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
import :project:.usecases.*;
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
@Path("rs:table:_update")
public class RS:Table:_update extends RS_json_login {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String post(String jsonstring) {
        try {
            Consume_jsonstring(jsonstring);
            setLoggedin(Security_usecases.check_authorization(authorisationstring));
            :Table:_usecases :table:usecases = new :Table:_usecases(loggedin);
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            switch(operation) {
                case I:Table:Operation.UPDATE_:TABLE::
                    update_:table:(:table:usecases, json);
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

    private void update_:table:(:Table:_usecases :table:usecases, JSONObject json) throws ParseException, CustomException {
        I:Table: :table: = (I:Table:)JSON:Table:.to:Table:((JSONObject)json.get(":table:"));
        :table:usecases.secureupdate:Table:(:table:);
        setReturnstatus("OK");
    }
}

