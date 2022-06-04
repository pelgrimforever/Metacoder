/*
 * Browsersniffer.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.HTTPtools.browser;

import java.util.*;
import java.io.*;
import javax.servlet.http.HttpServletRequest;

/**
 * Determine client Browser and OS
 * Keep track of which client is compatible with the project
 *
 * @author Franky Laseure
 */
public final class Browsersniffer {

    private HttpServletRequest request = null;
    private String useragent = null;
    private boolean netEnabled = false;
    private boolean ie = false;
    private boolean ns = false;
    private boolean mo = false;

    private String browsername;
    private String browserversion;

    private String[] incompatible = {
        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)"
    };

    /**
     * Constructor, extracts browser information from HttpServletRequest
     * @param req: HttpServletRequest from client
     */
    public Browsersniffer(HttpServletRequest req) {
        request = req;
        useragent = request.getHeader("User-Agent");
        String user = useragent.toLowerCase();
        //browsernaam
        if(user.indexOf("msie") != -1) {
            ie = true;
            browsername = "MSIE";
        } else if(user.indexOf("netscape") != -1) {
            ns = true;
            browsername = "Netscape";
        } else if(user.indexOf("mozilla") != -1) {
            mo = true;
            browsername = "Mozilla";
        }
        //browserversion
        if(ie) {
            browserversion = user.substring(user.indexOf("msie")+4);
            if(browserversion.length()>5) browserversion = browserversion.substring(1, 5).trim();
        }
        if(ns) {
            browserversion = user.substring(user.indexOf("netscape")+8);
            if(browserversion.length()>5) browserversion = browserversion.substring(1, 5).trim();
        }
        if(mo) {
            browserversion = user.substring(user.indexOf("mozilla")+7);
            if(browserversion.length()>5) browserversion = browserversion.substring(1, 5).trim();
        }

        if(user.indexOf(".net clr") != -1)
            netEnabled = true;
    }

    /**
     * complete user-agent attribute, unformatted
     * @return useragent property
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * netenabled
     * @return boolean Netenabled
     */
    public boolean isNetEnabled() {
        return netEnabled;
    }

    /**
     * Browserfamily name extracted from user agent
     * @return Browsername
     */
    public String getBrowsername() {
        return browsername;
    }

    /**
     * Browser version extracted from user agent
     * @return Browser version
     */
    public String getBrowserversion() {
        return browserversion;
    }

    /**
     * is browser Internet Explorer
     * @return boolean ie
     */
    public boolean isIE() {
        return ie;
    }

    /**
     * is browser Netscape
     * @return boolean ns
     */
    public boolean isNS() {
        return ns;
    }

    /**
     * is browser Mozilla
     * @return boolean mo
     */
    public boolean isMo() {
        return mo;
    }

    /**
     * is browser compatible with this project
     * @return boolean compatible
     */
    public boolean isCompatible() {
        boolean compatible = true;
        for(int i=0; i<incompatible.length; i++) {
            compatible = compatible && !useragent.equals(incompatible[i]);
        }
        //compatible = false;
        return compatible;
    }
}
