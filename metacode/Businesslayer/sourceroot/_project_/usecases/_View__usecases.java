/*
 * Generated on :metacoder_date:
 */

package :project:.usecases;

import data.conversion.JSONConversion;
import data.gis.shape.piPoint;
import :project:.interfaces.searchentity.*;
import :project:.logicview.*;
import :project:.BusinessObject.Logic.*;
import :project:.entity.pk.*;
import :project:.logicview.:Table:;
import general.exception.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.parser.ParseException;

/**
 * @author Franky Laseure
 */
public class :Table:_usecases {

    private boolean loggedin = false;
    private BL:table: bl:table: = new BL:table:();
    
    public :Table:_usecases() {
        this(false);
    }
    
    public :Table:_usecases(boolean loggedin) {
        this.loggedin = loggedin;
        bl:table:.setAuthenticated(loggedin);
    }
    
    public ArrayList<:Table:> get_all() throws DBException {
        return bl:table:.get:Table:s();
    }
    
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

}

