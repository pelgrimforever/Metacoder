/*
 * :Table:handler_base.java
 *
 * Created on Jan 01, 2013, 20:09
 * Generated on :metacoder_date:
 *
 */

package :project:.datahandlers.base;

import general.exception.DatahandlerException;
import base.servlets.*;
import data.interfaces.db.Filedata;
import :project:.interfaces.logicview.*;
import general.exception.CustomException;
import java.util.ArrayList;
import java.util.Iterator;
import :project:.interfaces.servlet.I:Table:Operation;
import :project:.datahandlers.data.:Table:data;
import :project:.logicview.:Table:;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javafx.scene.image.Image;

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
    public final static String servletSELECT = ":project:.:Table:_select";

    public static ArrayList<:Table:data> beans(ArrayList serverlist) {
        ArrayList<:Table:data> beanlist = new ArrayList<>();
        Iterator<:Table:> serverlistI = serverlist.iterator();
        while(serverlistI.hasNext()) {
            beanlist.add(bean(serverlistI.next()));
        }
        return beanlist;
    }
    
    public static :Table:data bean(:Table: :table:) {
        return new :Table:data(:table:);
    }
    
    public static ArrayList load:Table:s() throws DatahandlerException, CustomException {
        Servlethandler handler = new Servlethandler(WEBSERVICE + servletSELECT, I:Table:Operation.SELECT_ALL);
        //send request
        return (ArrayList)handler.post();
    }
}
