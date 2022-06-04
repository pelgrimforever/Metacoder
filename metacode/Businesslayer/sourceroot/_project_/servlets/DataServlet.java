/*
 * DataServlet.java
 *
 * Created on Dec 23, 2012, 6:30 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.servlets;

import :project:.HTTPtools.fileUpload.Requesthandler;
import base.interfaces.servlet.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;

/**
 * DataServlet is the superclass for all servlets generated in this project
 * Contains standard variables and a default html output
 *
 * @author Franky Laseure
 */
 public abstract class DataServlet extends javax.servlet.http.HttpServlet implements SingleThreadModel {

    //Respnose content type
    protected static final String CONTENT_TYPE = "text/html";

    //Logged on user
    protected String userID = null;
    protected String password = null;
    protected boolean authenticated = false;

    //Requested operation
    protected byte operationtype;
    protected byte operation;

     //Data variables
     protected Requesthandler parser;

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public DataServlet() {
            super();
    } 
	
    /* (non-Javadoc)
     * @see javax.servlet.Servlet#destroy()
     */
    public void destroy() {
            super.destroy();
    }   	
	
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException;

    protected void getAuthorisation(HttpServletRequest request) throws IOException {
        userID = null;
        password = null;
        authenticated = false;
        // Get the Authorization header, if one was supplied
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();
                // We only handle HTTP Basic authentication
                if (basic.equalsIgnoreCase("Basic")) {
                    String credentials = st.nextToken();
                    Base64 decoder = new Base64();
                    String userPass =
                    new String(decoder.decode(credentials));
                    // The decoded string is in the form
                    // "userID:password".
                    int p = userPass.indexOf(":");
                    if (p != -1) {
                        userID = userPass.substring(0, p);
                        password = userPass.substring(p+1);
                    }
                }
            }
        }
    }

    protected void sendData(HttpServletResponse response, Object data) throws ServletException, IOException {
        try {
            ServletOutputStream outputstream = response.getOutputStream();
            response.setContentType("");

            ByteArrayOutputStream streamout = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(streamout);
            outputStream.writeObject(data);

            outputstream.write(streamout.toByteArray());
            outputstream.flush();
            outputstream.close();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getOperationalVariables(request);
        processRequest(request, response);
    }  	

    /* (non-Java-doc)
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getOperationalVariables(request);
        processRequest(request, response);
    }   	  	  

    protected void getOperationalVariables(HttpServletRequest request) throws IOException {
        this.parser = new Requesthandler(request);
        this.operation = parser.getByteParameter(HTTPOperationconstants.VAR_OPERATION);
    }

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    public void init() throws ServletException {
        super.init();
    }
}

