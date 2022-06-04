/*
 * e:Entity:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.HTTPtools.fileUpload;

//import :project:.general.log.:Project:Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * Simple File Upload class
 * Load form parameters and first file
 * all form parameters and files after first file are ignored
 * This class is replaced by Requesthandler which handles
 * all form data without restriction
 *
 * @author Franky Laseure
 */
public class singleFileUpload {

    private HashMap formdata = new HashMap();
    private ByteArrayOutputStream fileData = new ByteArrayOutputStream(8164);
    private String accept = null;
    private String referer = null;
    private String accept_language = null;
    private String content_type = null;
    private String accept_encoding = null;
    private String user_agent = null;
    private String host = null;
    private String content_length = null;
    private String connection = null;
    private String cache_control = null;
    private String ContextPath = null;
    private String boundary = null;
    private String Content_Disposition = null;
    private String name = null;
    private String filename = "";
    private String filecontent = null;
    private String[] header_line = new String[3];
//    private :Project:Log log;

    /**
     * Constructor
     * analyse HttpServletRequest and set all parameters and first file
     */
    public singleFileUpload(HttpServletRequest request) throws IOException {
    //	:Project:Log log = new :Project:Log(this);
        //Get properties from request header
        accept = request.getHeader("accept");
        referer = request.getHeader("referer");
        accept_language = request.getHeader("accept-language");
        content_type = request.getHeader("content-type");
        if(request.getContentType().length() > content_type.length()) {
          content_type = request.getContentType();
        }
        accept_encoding = request.getHeader("accept-encoding");
        user_agent = request.getHeader("user-agent");
        host = request.getHeader("host");
        content_length = request.getHeader("content-length");
        connection = request.getHeader("connection");
        cache_control = request.getHeader("cache-control");
        ContextPath = request.getContextPath();
        boundary = content_type.substring(content_type.indexOf("boundary=") + 9);

        //Get properties & file data from the inputstream
        ServletInputStream servletData = request.getInputStream();
        byte[] b = new byte[8164];
        int len=0;
        String line;
        //First line = boundary
        len = servletData.readLine(b, 0, b.length);
        line = new String(b, 0, len, "ISO-8859-1");
        header_line[0] = line;
        //Second line: Content-Disposition, name, filename
        //if there's other form data, repeat until the file is found
        do {
            len = servletData.readLine(b, 0, b.length);
            line = new String(b, 0, len, "ISO-8859-1");
            if(line.indexOf("Content-Disposition: form-data; name=\"")!=-1) {
                if(line.indexOf("filename=\"")==-1) {
                    //form data found, store in HashMap
                    String dataname = line.substring(line.indexOf("name=\"") + 6);
                    dataname = dataname.substring(0, dataname.indexOf("\""));
                    //read empty line
                    len = servletData.readLine(b, 0, b.length);
                    line = new String(b, 0, len, "ISO-8859-1");
                    //read first line
                    len = servletData.readLine(b, 0, b.length);
                    line = new String(b, 0, len, "ISO-8859-1");
                    String value = "";
                    while(line.indexOf(boundary)==-1) {
                        //cut away the last return character
                        value += line.substring(0, line.length() - 2);
                        len = servletData.readLine(b, 0, b.length);
                        line = new String(b, 0, len, "ISO-8859-1");
                    }
                    formdata.put(dataname, value);
                }
            }
        } while(line.indexOf("filename=\"")==-1);
        header_line[1] = line;
        Content_Disposition = line.substring(line.indexOf("Content-Disposition:") + 21, line.indexOf("name=")).trim();
        name = line.substring(line.indexOf("name=\"") + 6, line.indexOf("filename=\""));
        name = name.substring(0, name.indexOf("\""));
        filename = line.substring(line.indexOf("filename=\"") + 10);
        filename = filename.substring(0, filename.indexOf("\""));
        //Third line: Content-Type
        len = servletData.readLine(b, 0, b.length);
        line = new String(b, 0, len, "ISO-8859-1");
        header_line[2] = line;
        filecontent = line.substring(line.indexOf("Content-Type:") + 14);
        filecontent = filecontent.substring(0, filecontent.length() - 2).trim();
        //Forth line: empty line
        len = servletData.readLine(b, 0, b.length);
        //Begin of fileData
        boolean boundaryFound = false;
        while( ((len = servletData.readLine(b, 0, b.length)) != -1) && !boundaryFound ) {
            line = new String(b, 0, len, "ISO-8859-1");
            if(!(line.indexOf(boundary)>-1)) {
              fileData.write(b, 0, len);
            } else {
              boundaryFound = true;
            }
        }
        servletData.close();
        Enumeration e = request.getAttributeNames();
        //for (Enumeration e = request.getAttributeNames() ; e.hasMoreElements() ;) {
        //}
    }

    /**
     * saves file in given directory
     * @param dir: directory
     * @return true if successfull
     */
    public boolean saveFile(String dir) throws IOException {
        return saveFileAs(dir, this.getFilename());
    }

    /**
     * saves file in given directory with new filename
     * @return true if successfull
     */
    public boolean saveFileAs(String dir, String filename) throws IOException {
        boolean result = false;
        File destination = new File(dir);
        if(!destination.exists()) destination.mkdirs();
        FilterOutputStream fileOutputStream = null;
        byte[] b = fileData.toByteArray();
        try {
            File f = new File(dir + filename);
            fileOutputStream = new BufferedOutputStream(new FileOutputStream(f), 8 * 1024);
            fileOutputStream.write(b, 0, b.length - 2);
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        finally {
            result = true;
        }
        return result;
    }

    /**
     * parameter value
     * @return Parameter value as String
     */
    public String getParameter(String parameter) {
        String returnvalue = (String)formdata.get(parameter);
        if(returnvalue!=null)
            if(returnvalue.trim().equals("")) returnvalue = null;
        return returnvalue;
    }

    /**
     * accept header value
     * @return accept header value
     */
    public String getaccept() {
        return accept;
    }

    /**
     * referer header value
     * @return referer header value
     */
    public String getreferer() {
        return referer;
    }

    /**
     * accept language header value
     * @return accept language header value
     */
    public String getaccept_language() {
        return accept_language;
    }

    /**
     * content type header value
     * @return content type header value
     */
    public String getcontent_type() {
        return content_type;
    }

    /**
     * accept encoding header value
     * @return accept encoding header value
     */
    public String getaccept_encoding() {
        return accept_encoding;
    }

    /**
     * user agent header value
     * @return user agent header value
     */
    public String getuser_agent() {
        return user_agent;
    }

    /**
     * host header value
     * @return host header value
     */
    public String gethost() {
        return host;
    }

    /**
     * content length header value
     * @return content length header value
     */
    public String getcontent_length() {
        return content_length;
    }

    /**
     * connection header value
     * @return connection header value
     */
    public String getconnection() {
        return connection;
    }

    /**
     * cache control header value
     * @return cache control header value
     */
    public String getcache_control() {
        return cache_control;
    }

    /**
     * context path header value
     * @return context path header value
     */
    public String getContextPath() {
        return ContextPath;
    }

    /**
     * boundary header value
     * @return boundary header value
     */
    public String getboundary() {
        return boundary;
    }

    /**
     * Content Disposition header value
     * @return Content Disposition header value
     */
    public String getContent_Disposition() {
        return Content_Disposition;
    }

    /**
     * filename
     * @return filename
     */
    public String getname() {
        return name;
    }

    /**
     * headerline header value
     * @param line: line number
     * @return headerline header value
     */
    public String getHeader_Line(int line) {
        return header_line[line];
    }

    /**
     * file content header value
     * @return file content header value
     */
    public String getfilecontent() {
        return filecontent;
    }

    /**
     * file path
     * @return file path
     */
    public String getfullFilename() {
        return filename;
    }

    /**
     * filename (no path)
     * @return filename
     */
    public String getFilename() {
        return filename.substring(filename.lastIndexOf("\\") + 1);
    }

    /**
     * file path
     * @return file path
     */
    public String getDirname() {
        return filename.substring(0, filename.lastIndexOf("\\"));
    }
}