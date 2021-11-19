package metacoder.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import text.StringBuilderutils;

/**
 * Program language specifications
 * @author Franky Laseure
 */
public class Programlanguage {
    
    public static final String UNKONWNDATATYPE = "UNKNOWN";
    public static final String OBJECT = Object.class.getName();
    
    private String name;
    private ArrayList<String> tablenamefilter = new ArrayList<>();
    public String getName() { return name; }
    private String sqlconvertorclass;
    public String getSQLconvertorclass() { return sqlconvertorclass; }
    private Element root = null;
    private HashMap<String, String> dbfunctions = new HashMap<String, String>();
    private HashMap<String, Customdatatype> objectcasting = new HashMap<String, Customdatatype>();
    
    /**
     * constructor, read program language XML
     * @param languagexmlfile XML File
     */
    public Programlanguage(File languagexmlfile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            SAXBuilder saxbuilder = new SAXBuilder();
            Document xmldoc = saxbuilder.build(languagexmlfile);
            root = xmldoc.getRootElement();
            reset();
        }
        catch(JDOMException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * reset all values to XML file values
     */
    public void reset() {
        if(root!=null) {
            name = root.getChild("name").getValue();
            for(Element child: root.getChild("tablenamefilter").getChildren()) {
                tablenamefilter.add(child.getValue());
            }
            for(Element child: root.getChild("dbfunctions").getChildren()) {
                dbfunctions.put(child.getName(), child.getValue());
            }
            Element sqlconvertorelement = root.getChild("Objectcasting").getChild("sqlconverter");
            sqlconvertorclass = sqlconvertorelement.getValue();
            Customdatatype customdatatype;
            for(Element child: root.getChild("Objectcasting").getChild("datatypes").getChildren()) {
                customdatatype = new Customdatatype(
                        child.getChildText("databasedriverdatatype"),
                        child.getChildText("customdatatype"),
                        child.getChildText("datatype"));
                objectcasting.put(child.getName(), customdatatype);
            }
        }
    }
    
    /**
     * remove all characters defined in tablenamefilter list
     * @param tablename Table name
     * @return formatted Table name
     */
    public String processTablename(String tablename) {
        StringBuilder result = new StringBuilder(tablename);
        for(String filteritem: tablenamefilter) {
            StringBuilderutils.replaceAll(result, filteritem, "");
        }
        return result.toString();
    }
    
    /**
     * @param datatype Java data type name
     * @return function defined in XML dbfunctions list
     */
    public String getFunction(String datatype) {
        String function = dbfunctions.get(datatype);
        if(function==null) function = dbfunctions.get(UNKONWNDATATYPE);
        return function;
    }

    /**
     * @param datatype Java data type name
     * @param sqldatatypename sql data type name (database dependent)
     * @return Customdatatype configures in XML Objectcasting - datatypes
     */
    public Customdatatype getObjectcasting(String datatype, String sqldatatypename) {
        Customdatatype customdatatype = null;
        if(datatype.equals(OBJECT)) {
            customdatatype = objectcasting.get(sqldatatypename);
        }
        return customdatatype;
    }
    
    /**
     * @param datatype Java data type name
     * @param sqldatatypename sql data type name (database dependent)
     * @return Customdatatype.datatype = generic data class name defined in ProjectInterfaces project
     */
    public String getVariabletype(String datatype, String sqldatatypename) {
        String variabletype = datatype;
        if(datatype.equals(OBJECT)) {
            Customdatatype customdatatype = getObjectcasting(datatype, sqldatatypename);
            if(customdatatype!=null) {
                variabletype = customdatatype.getDatatype();
            }
        }
        return variabletype;
    }
    
    /**
     * may be removed in the future
     * @param datatype Java data type name
     * @param sqldatatypename sql data type name (database dependent)
     * @return Customdatatype.datatype = generic data class name defined in ProjectInterfaces project
     */
    public String getVariabletypesql(String datatype, String sqldatatypename) {
        String variabletype = datatype;
        //Extended datatypes
        if(sqldatatypename.equals("smallserial")) {
            variabletype = "int_serial";
        }
        if(sqldatatypename.equals("serial")) {
            variabletype = "int_serial";
        }
        if(sqldatatypename.equals("bigserial")) {
            variabletype = "long_serial";
        }
        if(datatype.equals(OBJECT)) {
            Customdatatype customdatatype = getObjectcasting(datatype, sqldatatypename);
            if(customdatatype!=null) {
                variabletype = customdatatype.getDatatype();
            }
        }
        return variabletype;
    }

    /**
     * @param datatype Java data type name
     * @param sqldatatypename sql data type name (database dependent)
     * @return Customdatatype.customdatatype = jdbc data class name
     */
    public String getCustomdatatype(String datatype, String sqldatatypename) {
        String customdatatypestring = "";
        Customdatatype customdatatype = getObjectcasting(datatype, sqldatatypename);
        if(customdatatype!=null) customdatatypestring = customdatatype.getCustomdatatype();
        return customdatatypestring;
    }
    
    /**
     * @param datatype Java data type name
     * @param sqldatatypename sql data type name (database dependent)
     * @return Customdatatype.databasedriverdatatype = database dependent custom data class name defined in Project"databasetool" (ex: ProjectPostgresql)
     */
    public String getDatabasedriverdatatype(String datatype, String sqldatatypename) {
        String databasedriverdatatype = "";
        Customdatatype customdatatype = getObjectcasting(datatype, sqldatatypename);
        if(customdatatype!=null) databasedriverdatatype = customdatatype.getDatabasedriverdatatype();
        return databasedriverdatatype;
    }
    
}
