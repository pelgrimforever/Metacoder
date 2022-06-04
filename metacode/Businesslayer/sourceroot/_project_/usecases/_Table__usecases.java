/*
 * Generated on :metacoder_date:
 */

package :project:.usecases;

import data.conversion.JSONConversion;
import data.interfaces.db.Filedata;
import data.gis.shape.piPoint;
import :project:.BusinessObject.Logic.*;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.searchentity.*;
import :project:.interfaces.entity.pk.*;
import :project:.logicentity.:Table:;
import general.exception.*;
import java.sql.Date;
import java.util.*;
import java.io.IOException;
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
    
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    public long count() throws DBException {
        return bl:table:.count();
    }
    
    public ArrayList<:Table:> get_all() throws DBException {
        return bl:table:.get:Table:s();
    }
    
    public boolean get:Table:Exists(I:Table:PK :table:PK) throws DBException {
        return bl:table:.getEntityExists(:table:PK);
    }
    
    public :Table: get_:table:_by_primarykey(I:Table:PK :table:PK) throws DBException {
        return bl:table:.get:Table:(:table:PK);
    }

:repeatforeignkeys:
    public ArrayList<:Table:> get_:table:_with_foreignkey_:uniquename:(I:Pktable:PK :uniquename:PK) throws CustomException {
        return bl:table:.get:Table:s4:uniquename:(:uniquename:PK);
    }
    
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    public :Table: get_:table:_with_externalforeignkey_:uniquename:(I:Extablename:PK :uniquename:PK) throws CustomException {
        return bl:table:.get:Uniquename:(:uniquename:PK);
    }
    
:ifforeignkey:
:repeatexternalforeignkeys:
    public ArrayList<:Table:> search_:table:(I:Table:search :table:search) throws CustomException {
        return bl:table:.search(:table:search);
    }
    
    public long search_:table:_count(I:Table:search :table:search) throws CustomException {
        return bl:table:.searchcount(:table:search);
    }

    public void secureinsert:Table:(I:Table: :table:) throws DBException, DataException {
        bl:table:.secureinsert:Table:(:table:);
    }

    public void secureupdate:Table:(I:Table: :table:) throws DBException, DataException {
        bl:table:.secureupdate:Table:(:table:);
    }

    public void securedelete:Table:(I:Table: :table:) throws DBException, DataException {
        bl:table:.securedelete:Table:(:table:);
    }
}

