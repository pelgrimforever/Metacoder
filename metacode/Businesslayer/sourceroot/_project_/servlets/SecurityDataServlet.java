/*
 * SecurityDataServlet.java
 *
 * Created on Dec 23, 2012, 6:33 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.servlets;

import :project:.BusinessObject.security.Security;
import general.exception.DBException;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Franky Laseure
 */
public abstract class SecurityDataServlet extends DataServlet implements Contextparameters {
//Metacoder: NO AUTHOMATIC UPDATE

    protected void loadAuthorization(HttpServletRequest request) throws IOException {
        userID = null;
        password = null;
        authenticated = false;
        HttpSession session = request.getSession();
        //userprofile = (Userprofile)session.getAttribute("userprofile");
        //if(userprofile==null) {
            getAuthorisation(request);
            synchronized(getServletContext()) {
                ServletContext context = getServletContext();
                Security security = (Security)context.getAttribute(SECURITY);
                try {
                    if(security.check(userID, password)) {
                        authenticated = true;
                        //ArrayList sitegroups = security.getGroups(userID);
                        //ArrayList profiles = security.getProfiles(userID);
                        //userprofile = new Userprofile(sitegroups, profiles);
                        //session.setAttribute("userprofile", userprofile);
                    }
                }
                catch(DBException e) {
                }
            }
        //} else {
        //    authenticated = true;
        //}
    }

}

