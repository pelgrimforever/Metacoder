/*
 * downloadfile.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.servlets;

import data.interfaces.db.Filedata;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet, sends file to browser as binary stream
 *
 * @author Franky Laseure
 */
 public class downloadfile extends javax.servlet.http.HttpServlet implements SingleThreadModel {

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public downloadfile() {
            super();
    }

    /* (non-Javadoc)
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy() {
            super.destroy();
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Filedata filedata = (Filedata)request.getAttribute("filedata");
        ServletOutputStream outputstream = response.getOutputStream();
        response.setContentType("");
        outputstream.write(filedata.getBinarydata(), 0, filedata.getBinarydata().length);
        outputstream.flush();
        outputstream.close();
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
