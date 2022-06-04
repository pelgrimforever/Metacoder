/*
 * rawData.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.HTTPtools.fileUpload;

/**
 * Test class
 * put all TttpServletRequest data in html format
 *
 * @author Franky Laseure
 */

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class rawData {

    private HttpServletRequest thisRequest= null;

    /**
     * Constructor
     * set servlet request parameter
     * @param request: HttpServletRequest instance
     */
    public rawData(HttpServletRequest request) {
        thisRequest = request;
    }

    /**
     * output request inputstream to html
     * @return html String in table format
     */
    public String getData2HTML() throws IOException {
        String result = "<table>";
        //Get all lines and put them to html
        ServletInputStream servletData = thisRequest.getInputStream();
        byte[] b = new byte[8164];
        int len=0;
        int i=0;
        String line;
        while( (len = servletData.readLine(b, 0, b.length)) != -1 ) {
            line = new String(b, 0, len, "ISO-8859-1");
            result += "<tr><td>" + i + "</td>";
            result += "<td>" + line + "</td></tr>";
        }
        result += "</table>";
        return result;
    }
}