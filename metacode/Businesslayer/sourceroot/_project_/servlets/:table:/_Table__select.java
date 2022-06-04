/*
 * Created on Dec 23, 2012, 7:24 PM
 * Generated on :metacoder_date:
 */

package :project:.servlets.:table:;

import general.exception.*;
import data.interfaces.db.Filedata;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.I:Table:;
import :project:.interfaces.servlet.I:Table:Operation;
import :project:.interfaces.searchentity.I:Table:search;
import :project:.servlets.DataServlet;
import :project:.servlets.SecurityDataServlet;
import :project:.usecases.:Table:_usecases;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Franky Laseure
 */
@WebServlet(name=":Table:_select", urlPatterns={"/:project:.:Table:_select"})
public class :Table:_select extends SecurityDataServlet {
   
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        loadAuthorization(request);
        Object dataobject = null;
        :Table:_usecases :table:usecases = new :Table:_usecases(authenticated);
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
        try {
            switch(this.operation) {
                case I:Table:Operation.SELECT_ALL:
                    dataobject = :table:usecases.get_all();
                    break;
                case I:Table:Operation.SELECT_:TABLE::
                    dataobject = get_:table:_with_primarykey(:table:usecases);
                    break;
:repeatforeignkeys:
                case I:Table:Operation.SELECT_:Uniquename::
                    dataobject = get_:table:_with_foreignkey_:uniquename:(:table:usecases);
                    break;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
                case I:Table:Operation.SELECT_:Uniquename::
                    dataobject = get_:table:_with_externalforeignkey_:uniquename:(:table:usecases);
                    break;
:ifforeignkey:
:repeatexternalforeignkeys:
                case I:Table:Operation.SELECT_SEARCH:
                    dataobject = search_:table:(:table:usecases);
                    break;
                case I:Table:Operation.SELECT_SEARCHCOUNT:
                    dataobject = search_:table:_count(:table:usecases);
                    break;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   
            }

        } 
        catch(CustomException e) {
            dataobject = e;
        }
        finally {
        }
        this.sendData(response, dataobject);
    } 

//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    private Object get_:table:_with_primarykey(:Table:_usecases :table:usecases) throws DBException {
        I:Table:PK :table:PK = (I:Table:PK)parser.getJavaObject(":table:pk");
        return :table:usecases.get_:table:_by_primarykey(:table:PK);
    }

:repeatforeignkeys:
    private Object get_:table:_with_foreignkey_:uniquename:(:Table:_usecases :table:usecases) throws CustomException {
        I:Pktable:PK :uniquename:PK = (I:Pktable:PK)parser.getJavaObject(":pktable:pk");
        return :table:usecases.get_:table:_with_foreignkey_:uniquename:(:uniquename:PK);
    }
    
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    private Object get_:table:_with_externalforeignkey_:uniquename:(:Table:_usecases :table:usecases) throws CustomException {
        I:Extablename:PK :uniquename:PK = (I:Extablename:PK)parser.getJavaObject(":extablename:pk");
        return :table:usecases.get_:table:_with_externalforeignkey_:uniquename:(:uniquename:PK);
    }
    
:ifforeignkey:
:repeatexternalforeignkeys:
    private Object search_:table:(:Table:_usecases :table:usecases) throws CustomException {
        I:Table:search search = (I:Table:search)parser.getJavaObject("search");
        return :table:usecases.search_:table:(search);
    }
    
    private Object search_:table:_count(:Table:_usecases :table:usecases) throws CustomException {
        I:Table:search :table:search = (I:Table:search)parser.getJavaObject("search");
        return :table:usecases.search_:table:_count(:table:search);
    }

    @Override
    public String getServletInfo() {
        return ":table:_select";
    }

}

