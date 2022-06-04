/*
 * :Project:ContextListener.java
 *
 * Created on Dec 23, 2012, 6:22 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.listeners;

import :project:.BusinessObject.security.Security;
import :project:.servlets.Contextparameters;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Laseure Franky
 */
@WebListener()
public class :Project:ContextListener implements ServletContextListener, Contextparameters {
   
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        Security security = new Security();
        context.setAttribute(SECURITY, security);
    }

    public void contextDestroyed(ServletContextEvent event) {

    }
}

