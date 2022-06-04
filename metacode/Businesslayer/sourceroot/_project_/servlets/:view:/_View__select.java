/*
 * Created on Dec 23, 2012, 7:24 PM
 * Generated on :metacoder_date:
 */

package :project:.servlets.:table:;

import general.exception.CustomException;
import data.interfaces.db.Filedata;
import :project:.interfaces.logicview.I:Table:;
import :project:.interfaces.servlet.I:Table:Operation;
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
//Metacoder: NO AUTHOMATIC UPDATE
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        loadAuthorization(request);

        Object dataobject = null;
        :Table:_usecases :table:usecases = new :Table:_usecases(authenticated);
        try {
            switch(this.operation) {
                case I:Table:Operation.SELECT_ALL:
                    dataobject = :table:usecases.get_all();
                    break;
            }
        } 
        catch(CustomException e) {
            dataobject = e;
        }
        this.sendData(response, dataobject);
    } 

    @Override
    public String getServletInfo() {
        return ":table:_select";
    }

}

