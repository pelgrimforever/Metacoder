/*
 * :Table:handler_base.java
 *
 * Created on Jan 01, 2013, 20:09
 * Generated on :metacoder_date:
 *
 */

package :project:.datahandlers.base;

import base.framework.controller.AbstractData;
import general.exception.DatahandlerException;
import base.interfaces.servlet.IDataServlet;
import base.servlets.DataHandler;
import base.servlets.Servlethandler;
import :project:.entity.pk.*;
import data.interfaces.db.Filedata;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.searchentity.I:Table:search;
import :project:.interfaces.servlet.I:Table:Operation;
import :project:.logicentity.*;
import general.exception.CustomException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Datahandler (base) class :Table:handler_base: super class for :Table:handler
 *
 * Class for communication between client and server
 * sends data and operation commands
 * receives data
 *
 * @author Franky Laseure
 */
public class :Table:handler_base extends AbstractData {
	
    public final static String WEBSERVICE = DataHandler.DEFAULTWEBSERVICE;
    public final static String servlet = ":project:.:Table:";

    public static ArrayList load:Table:s() throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_ALL);
        //send request
        return (ArrayList)handler.post();
    }
    
    public static I:Table: load:Table:(I:Table:PK :table:pk) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_:TABLE:);
        handler.addjavaobject(":table:pk", :table:pk);
        //send request
        return (I:Table:)handler.post();
    }
 
    public static ArrayList search(I:Table:search search) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_SEARCH);
        handler.addjavaobject("search", search);
        //send request
        return (ArrayList)handler.post();
    }

    public static void insert:Table:(I:Table: :table:) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_INSERT, I:Table:Operation.INSERT_:TABLE:);
        handler.addjavaobject(":table:", :table:);
        //send request
        handler.post();
    }

    public static void update:Table:(I:Table: :table:) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_UPDATE, I:Table:Operation.UPDATE_:TABLE:);
        handler.addjavaobject(":table:", :table:);
        //send request
        handler.post();
    }
    
    public static void delete:Table:(I:Table: :table:) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_UPDATE, I:Table:Operation.DELETE_:TABLE:);
        handler.addjavaobject(":table:", :table:);
        //send request
        handler.post();
    }

:repeatforeignkeys:
    public static ArrayList load:Table:s4:uniquename:(I:Pktable:PK :pktable:PK) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_:Uniquename:);
        handler.addjavaobject(":pktable:pk", :pktable:PK);
        //send request
        return (ArrayList)handler.post();
    }

    public static ArrayList delete:Table:s4:uniquename:(I:Pktable:PK :pktable:PK) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.DELETE_:Uniquename:);
        handler.addjavaobject(":pktable:pk", :pktable:PK);
        //send request
        return (ArrayList)handler.post();
    }

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    public static ArrayList load:Table:s4:uniquename:(I:Extablename:PK :extablename:PK) throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_:Uniquename:);
        handler.addjavaobject(":extablename:pk", :extablename:PK);
        //send request
        return (ArrayList)handler.post();
    }

:ifforeignkey:
:repeatexternalforeignkeys:
}
