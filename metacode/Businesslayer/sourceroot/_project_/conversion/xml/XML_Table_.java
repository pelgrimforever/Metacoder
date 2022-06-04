/*
 * XML:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */
 
package :project:.conversion.xml;

import XML.XMLElement;
import java.io.IOException;
import object.Objectoperation;
import data.conversion.JSONConversion;
import data.gis.shape.GISConversion;
import data.interfaces.db.EntityPK;
import data.interfaces.db.Fieldsearcher;
import :project:.entity.pk.:Table:PK;
import :project:.interfaces.entity.pk.I:Table:PK;
import :project:.logicentity.:Table:;
import :project:.searchentity.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import org.jdom2.Element;

/**
 *
 * @author Franky Laseure
 */
public class XML:Table: {
    
    /**
     * 
     * @return all keys and fields as XML
     */
    public static void addXML(Element PK, I:Table:PK :table:PK) {
:repeatpkfields:
:iffieldtype:
:java.sql.Date:
        if(:table:PK.get:Column:()!=null) {
          PK.addContent(XMLElement.newContent(":column:", :table:PK.get:Column:().getTime()));
        }
:java.sql.Date:
:java.sql.Time:
        if(:table:PK.get:Column:()!=null) {
          PK.addContent(XMLElement.newContent(":column:", :table:PK.get:Column:().getTime()));
        }
:java.sql.Time:
:java.sql.Timestamp:
        if(:table:PK.get:Column:()!=null) {
          PK.addContent(XMLElement.newContent(":column:", :table:PK.get:Column:().getTime()));
        }
:java.sql.Timestamp:
:other:
        PK.addContent(XMLElement.newContent(":column:", :table:PK.get:Column:()));
:other:
:iffieldtype:
:repeatpkfields:
    }

    /**
     * 
     * @return all keys and fields in a JSONObject
     */
    public static void addXML(Element :Table:XML, :Table: :table:) {
        Element PK = XMLElement.newContent("PK", "");
        addXML(PK, :table:.getPrimaryKey());
        :Table:XML.addContent(PK);
:repeatforeignkeys:
:notpk:
        if(:table:.get:Uniquename:PK()!=null) {
            Element :uniquename:PK = XMLElement.newContent(":uniquename:PK", "");
            XML:Pktable:.addXML(:uniquename:PK, :table:.get:Uniquename:PK());
            :Table:XML.addContent(:uniquename:PK);
        }
:notpk:
:repeatforeignkeys:
:repeatfields:
:iffieldtype:
:java.sql.Date:
        if(:table:.get:Column:()!=null) {
            :Table:XML.addContent(XMLElement.newContent(":column:", :table:.get:Column:().getTime()));
        }
:java.sql.Date:
:java.sql.Time:
        if(:table:.get:Column:()!=null) {
            :Table:XML.addContent(XMLElement.newContent(":column:", :table:.get:Column:().getTime()));
        }
:java.sql.Time:
:java.sql.Timestamp:
        if(:table:.get:Column:()!=null) {
            :Table:XML.addContent(XMLElement.newContent(":column:", :table:.get:Column:().toString()));
        }
:java.sql.Timestamp:
:piJson:
:piJson:
:piPoint:
        if(:table:.get:Column:()!=null) {
            :Table:XML.addContent(XMLElement.newContent(":column:", GISConversion.toJSON(:table:.get:Column:()).toJSONString()));
        }
:piPoint:
:piShape:
        if(:table:.get:Column:()!=null) {
            :Table:XML.addContent(XMLElement.newContent(":column:", GISConversion.toJSON(:table:.get:Column:()).toJSONString()));
        }
:piShape:
:java.lang.Object:
        try {
            :Table:XML.addContent(XMLElement.newContent(":column:", Objectoperation.objecttobase64(:table:.get:Column:())));
        }
        catch(IOException e) {
            System.err.println("XML:Table: " + e.getMessage());
        }
:java.lang.Object:
:other:
        :Table:XML.addContent(XMLElement.newContent(":column:", :table:.get:Column:()));
:other:
:iffieldtype:
:repeatfields:
    }
}

