/*
 * Created on Dec 23, 2012, 7:24 PM
 * Generated on :metacoder_date:
 */

package :project:.servlets.:table:;

import general.exception.CustomException;
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
@WebServlet(name=":Table:_insert", urlPatterns={"/:project:.:Table:_insert"})
public class :Table:_insert extends SecurityDataServlet {
   
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
                case I:Table:Operation.INSERT_:TABLE::
                    insert_:table:(:table:usecases);
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

    private void insert_:table:(:Table:_usecases :table:usecases) throws CustomException {
        I:Table: :table: = (I:Table:)parser.getJavaObject(":table:");
        :table:usecases.secureinsert:Table:(:table:);
    }
    
    @Override
    public String getServletInfo() {
        return ":table:_insert";
    }

}

