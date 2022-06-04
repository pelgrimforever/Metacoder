/*
 * :Table:handler_base.java
 *
 * Created on Jan 01, 2013, 20:09
 * Generated on :metacoder_date:
 *
 */

package :project:.datahandlers.base;

import general.exception.DatahandlerException;
import base.interfaces.servlet.IDataServlet;
import base.servlets.DataHandler;
import base.servlets.Servlethandler;
import :project:.entity.pk.*;
import data.interfaces.db.Filedata;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicview.*;
import :project:.logicentity.*;
import general.exception.CustomException;
import java.util.ArrayList;
import :project:.interfaces.servlet.I:Table:Operation;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Datahandler class :Table:handler, super class for :Table:handler
 *
 * Class for communication between client and server
 * sends data and operation commands
 * receives data
 *
 * @author Franky Laseure
 */
public class :Table:handler_base {
	
    public final static String WEBSERVICE = DataHandler.DEFAULTWEBSERVICE;
    public final static String servlet = ":project:.:Table:";

    public static ArrayList load:Table:s() throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servlet, IDataServlet.OPERATIONTYPE_SELECT, I:Table:Operation.SELECT_ALL);
        //send request
        return (ArrayList)handler.post();
    }
}
