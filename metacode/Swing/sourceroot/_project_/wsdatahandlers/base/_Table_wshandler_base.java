/*
 * Photowshandler_base.java
 *
 * Created on Jan 01, 2013, 20:09
 * Generated on 13.5.2013 15:40
 *
 */

package :project:.wsdatahandlers.base;

import base.framework.controller.AbstractData;
import general.exception.DatahandlerException;
import base.servlets.DataHandler;
import :project:.conversion.json.*;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.webservice.WSI:Table:;
import :project:.logicentity.:Table:;
import :project:.searchentity.:Table:search;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Datahandler (base) class :Table:wshandler_base: super class for :Table:wshandler
 *
 * Class for communication between client and server
 * sends data and operation commands
 * receives data
 *
 * @author Franky Laseure
 */
public class :Table:wshandler_base extends AbstractData {
	
    public final static String WEBSERVICE = DataHandler.DEFAULTWEBSERVICE;
    public final static String servicename = "WS:Table:Service";

    private static ArrayList toArrayList(String jsonstring) {
        ArrayList :table:s = new ArrayList();
        if(jsonstring!=null) {
            JSONParser parser = new JSONParser();
            try {
                JSONArray json = (JSONArray)parser.parse(jsonstring);
                for(int i=0; i<json.size(); i++) {
                    :table:s.add(JSON:Table:.to:Table:((JSONObject)json.get(i)));
                }
            }
            catch(ParseException e) {
            }
        }
        return :table:s;
    }    

    public static ArrayList load:Table:s() throws DatahandlerException {
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            String result = :table:port.get:Table:s();
            ArrayList :table:s = toArrayList(result);
            return :table:s;
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static ArrayList search(:Table:search :table:search) throws DatahandlerException {
        String jsonstring = JSON:Table:.toJSON(:table:search).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            String result = :table:port.search(jsonstring);
            ArrayList :table:s = toArrayList(result);
            return :table:s;
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static :Table: load:Table:(:Table:PK :table:PK) throws DatahandlerException {
        String jsonstring = JSON:Table:.toJSON(:table:PK).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            String result = :table:port.get:Table:(jsonstring);
            :Table: :table: = null;
            if(result!=null) {
                JSONParser parser = new JSONParser();
                try {
                    JSONObject json = (JSONObject)parser.parse(result);
                    :table: = JSON:Table:.to:Table:(json);
                }
                catch(ParseException e) {
                }
            }
            return :table:;
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static void insert:Table:(:Table: :table:) throws DatahandlerException {
        String jsonstring = JSON:Table:.toJSON(:table:).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            :table:port.insert:Table:(jsonstring);
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static void update:Table:(:Table: :table:) throws DatahandlerException {
        String jsonstring = JSON:Table:.toJSON(:table:).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            :table:port.update:Table:(jsonstring);
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static void delete:Table:(:Table: :table:) throws DatahandlerException {
        String jsonstring = JSON:Table:.toJSON(:table:).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            :table:port.delete:Table:(jsonstring);
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

:repeatforeignkeys:
    public static ArrayList load:Table:s4:uniquename:(I:Pktable:PK :pktable:PK) throws DatahandlerException {
        String jsonstring = JSON:Pktable:.toJSON(:pktable:PK).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            String result = :table:port.get:Table:s4:uniquename:(jsonstring);
            ArrayList :table:s = toArrayList(result);
            return :table:s;
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

    public static void delete:Table:s4:uniquename:(I:Pktable:PK :pktable:PK) throws DatahandlerException {
        String jsonstring = JSON:Pktable:.toJSON(:pktable:PK).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            :table:port.delete4:uniquename:(jsonstring);
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    public static :Table: load:Table:4:uniquename:(I:Extablename:PK :uniquename:PK) throws DatahandlerException {
        String jsonstring = JSON:Extablename:.toJSON(:uniquename:PK).toJSONString();
        try {
            URL url = new URL(WEBSERVICE + servicename + "?wsdl");
            QName qname = new QName("http://webservices.:project:/", servicename);
            Service service = Service.create(url, qname);
            WSI:Table: :table:port = service.getPort(WSI:Table:.class);
            String result = :table:port.get:Table:s4:uniquename:(jsonstring);
            :Table: :table: = null;
            if(result!=null) {
                JSONParser parser = new JSONParser();
                try {
                    JSONObject json = (JSONObject)parser.parse(result);
                    :table: = JSON:Table:.to:Table:(json);
                }
                catch(ParseException e) {
                }
            }
            return :table:;
        }
        catch(MalformedURLException e) {
            throw new DatahandlerException(e);
        }
    }

:ifforeignkey:
:repeatexternalforeignkeys:

}

