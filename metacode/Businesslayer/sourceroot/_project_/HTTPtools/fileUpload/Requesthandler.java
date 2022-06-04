/*
 * Requesthandler.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.HTTPtools.fileUpload;

import general.Conversion;
import data.interfaces.db.Filedata;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * Requesthandler class
 * Load form parameters and files
 * methods to transfor request parameters to java types
 * methods to save files
 *
 * @author Franky Laseure
 */
public class Requesthandler {

    //String formdata
    private HashMap formdata = new HashMap();
    //JavaObject formdata (for JavaFX client communication)
    private HashMap javadata = new HashMap();
    //File formdata
    private HashMap filedata = new HashMap();

    //when filename starts with this prefix, it's not a file but a JavaObject
    private static final String JAVAOBJECTPREFEX = "JAVAOBJECT_";

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
    private String header_line = null;
  
    /**
     * get saved Requesthandler in request attribute
     * if null, create new Requesthandler
     * @param request: HttpServletRequest from browser or from redirect
     * @return initialized Requesthandler
     * @throws IOException
     */
    public static Requesthandler get(HttpServletRequest request) throws IOException {
        if(request.getAttribute("parser")==null) {
            return new Requesthandler(request);
        } else {
            return (Requesthandler)request.getAttribute("parser");
        }
    }

    /**
     * Constructor
     * analyse HttpServletRequest and set all parameters and files
     * put Requesthandler object in Attribute "parser" of request object
     */
    public Requesthandler(HttpServletRequest request) throws IOException {
        //Get properties from request header
        accept = request.getHeader("accept");
        referer = request.getHeader("referer");
        accept_language = request.getHeader("accept-language");
        content_type = request.getHeader("content-type");
        if(content_type!=null && request.getContentType().length() > content_type.length()) {
            content_type = request.getContentType();
        }
        accept_encoding = request.getHeader("accept-encoding");
        user_agent = request.getHeader("user-agent");
        host = request.getHeader("host");
        content_length = request.getHeader("content-length");
        connection = request.getHeader("connection");
        cache_control = request.getHeader("cache-control");
        ContextPath = request.getContextPath();
        if(content_type!=null) {
            boundary = content_type.substring(content_type.indexOf("boundary=") + 9);
            if(content_type.equals("application/x-www-form-urlencoded")) {
                Enumeration eparameters = request.getParameterNames();
                String p;
                while(eparameters.hasMoreElements()) {
                    p=(String)eparameters.nextElement();
                    formdata.put(p, request.getParameter(p));
                }
            } else {
                //Get properties & file data from the inputstream
                ServletInputStream servletData = request.getInputStream();
                byte[] b = new byte[8164];
                int len=0;
                String line = null;
                //First line = boundary
                len = servletData.readLine(b, 0, b.length);
                if(len!=-1)
                    line = new String(b, 0, len, "ISO-8859-1");
                header_line = line;
                //Second line: Content-Disposition, name, filename
                while((len = servletData.readLine(b, 0, b.length))!=-1) {
                    //form data
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
                        } else {
                            //file or JavaObject found
                            Filedata fd = new Filedata();
                            fd.setHeader_line_0(line);
                            fd.setContent_Disposition(line.substring(line.indexOf("Content-Disposition:") + 21, line.indexOf("name=")).trim());
                            String name = line.substring(line.indexOf("name=\"") + 6, line.indexOf("filename=\""));
                            fd.setName(name.substring(0, name.indexOf("\"")));
                            String filename = line.substring(line.indexOf("filename=\"") + 10);
                            fd.setFilename(filename.substring(0, filename.indexOf("\"")));
                            //Third line: Content-Type
                            len = servletData.readLine(b, 0, b.length);
                            line = new String(b, 0, len, "ISO-8859-1");
                            fd.setHeader_line_1(line);
                            String filecontent = line.substring(line.indexOf("Content-Type:") + 14);
                            fd.setFilecontent(filecontent.substring(0, filecontent.length() - 2).trim());
                            //Forth line: empty line
                            len = servletData.readLine(b, 0, b.length);
                            //Begin of fileData
                            boolean boundaryFound = false;
                            ByteArrayOutputStream streamout = new ByteArrayOutputStream();
                            len = servletData.readLine(b, 0, b.length);
                            while( (len != -1) && !boundaryFound ) {
                                line = new String(b, 0, len, "ISO-8859-1");
                                if(!(line.indexOf(boundary)>-1)) {
                                  streamout.write(b, 0, len);
                                  len = servletData.readLine(b, 0, b.length);
                                } else {
                                  boundaryFound = true;
                                }
                            }
                            if(fd.getFilename().startsWith(JAVAOBJECTPREFEX)) {
                                String javadataname = fd.getFilename().substring(JAVAOBJECTPREFEX.length());
                                ByteArrayInputStream streamin = new ByteArrayInputStream(streamout.toByteArray());
                                ObjectInputStream objectreader = new ObjectInputStream(streamin);
                                try {
                                    javadata.put(javadataname, objectreader.readObject());
                                }
                                catch(ClassNotFoundException e) {
                                    String message = e.getMessage();
                                }
                            } else {
                                fd.setBinarydata(streamout);
                                filedata.put(fd.getName(), fd);
                            }
                        }
                    }
                }
                servletData.close();
            }
        } else {
            Enumeration e = request.getParameterNames();
            String dataname;
            String value;
            while(e.hasMoreElements()) {
                dataname = (String)e.nextElement();
                value = request.getParameter(dataname);
                formdata.put(dataname, value);
            }
        }
        request.setAttribute("parser", this);
    }

    /**
     * save file (select by name) in given directory
     * @param name: filename
     * @param dir: directory to save file in
     * @return true if successfull
     */
    public boolean saveFile(String name, String dir) throws IOException {
        String filename = ((Filedata)filedata.get(name)).getFilename();
        return saveFileAs(name, dir, filename);
    }

    /**
     * save file (select by name) in given directory as filename
     * @param name: filename
     * @param dir: directory to save file in
     * @param filename: new filename to use as local name
     * @return true if successfull
     */
    public boolean saveFileAs(String name, String dir, String filename) throws IOException {
        boolean result = false;
        File destination = new File(dir);
        if(!destination.exists()) destination.mkdirs();
        FilterOutputStream fileOutputStream = null;
        Filedata fd = (Filedata)filedata.get(name);
        byte[] b = fd.getBinarydata();
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
     * does parameter exist
     * @param parameter: parameter name
     * @return true if exists
     */
    public boolean getParemeterExist(String parameter) {
        return formdata.containsKey(parameter);
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
     * Content disposition of file
     * @param name: filename from client
     * @return Content disposition of file
     */
    public String getContent_Disposition(String name) {
        return ((Filedata)filedata.get(name)).getContent_Disposition();
    }

    /**
     * headerline header value
     * @return headerline header value
     */
    public String getHeader_Line() {
        return header_line;
    }

    /**
     * File content for file
     * @param name: filename from client
     * @return File content
     */
    public String getfilecontent(String name) {
        return ((Filedata)filedata.get(name)).getFilecontent();
    }

    /**
     * full filename of file
     * @param name: filename from client
     * @return full filename (with path)
     */
    public String getfullFilename(String name) {
        String filename = ((Filedata)filedata.get(name)).getFilename();
        return filename;
    }

    /**
     * Filename of file
     * @param name: filename from client
     * @return filename (without path)
     */
    public String getFilename(String name) {
        String filename = ((Filedata)filedata.get(name)).getFilename();
        return filename.substring(filename.lastIndexOf("\\") + 1);
    }

    /**
     * Dirname of file
     * @param name: filename from client
     * @return directory of file
     */
    public String getDirname(String name) {
        String filename = ((Filedata)filedata.get(name)).getFilename();
        return filename.substring(0, filename.lastIndexOf("\\"));
    }

    /**
     * get File for formvariable name
     * @param name: form variable
     * @return Filedata with file
     */
    public Filedata getFiledata(String name) {
        return (Filedata)filedata.get(name);
    }

    /**
     * get JavaObject for formvariable name
     * @param name: form variable
     * @return Object
     */
    public Object getJavaObject(String name) {
        return javadata.get(name);
    }

    /**
     * @param name: parameter name
     * @return matching value, parsed to String
     */
    public String getStringParameter(String name) {
        return formdata.get(name)==null ? null : String.valueOf(formdata.get(name));
    }

    /**
     * @param name: parameter name
     * @return matching value, parsed to Byte, Byte(0) if conversion has errors
     */
    public Byte getByteParameter(String name) {
        try {
            String number = (String)getParameter(name);
            if(number==null) number = "0";
            return Byte.valueOf(number);
        }
        catch(NumberFormatException e) {
            return new Byte("0");
        }
    }

    public boolean getBooleanParameter(String name) {
        try {
            String number = (String)getParameter(name);
            if(number==null) number = "0";
            return !number.equals("0");
        }
        catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param name: parameter name
     * @return matching value, parsed to Integer, Integer(0) if conversion has errors
     */
    public Integer getIntegerParameter(String name) {
        try {
            String number = (String)getParameter(name);
            if(number==null) number = "0";
            return Integer.valueOf(number);
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    /**
     * @param name: parameter name
     * @return matching value, parsed to Long, Long(0) if conversion has errors
     */
    public Long getLongParameter(String name) {
        try {
            String number = (String)getParameter(name);
            if(number==null) number = "0";
            return Long.valueOf(number);
        }
        catch(NumberFormatException e) {
            return new Long(0);
        }
    }

    /**
     * @param name: parameter name
     * @return matching value, parsed to Float, Float(0) if conversion has errors
     */
    public Float getFloatParameter(String name) {
        try {
            String number = (String)getParameter(name);
            if(number==null) number = "0";
            return Float.valueOf(number);
        }
        catch(NumberFormatException e) {
            return new Float(0);
        }
    }

    public Date getDateParameter(String name) {
        String date = (String)getParameter(name);
        String expectedPattern = "dd.M.yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        if(date==null) return null;
        else {
            try {
                return new Date(formatter.parse(date).getTime());
            }
            catch(ParseException e) {
                return null;
            }
        }
    }

    public Timestamp getTimestampParameter(String name) {
        String timestamp = (String)getParameter(name);
        if(timestamp==null) return null;
        else return Timestamp.valueOf(timestamp);
    }

    public Time getTimeParameter(String name) {
        String timestring = (String)getParameter(name);
        if(timestring==null) return null;
        else return Conversion.convertStringToTime(timestring);
    }
}
