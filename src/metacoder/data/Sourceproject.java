package metacoder.data;

import XML.XMLElement;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Element;

/**
 * Sourceproject
 * Project path with linked project metacode maps
 * @author Franky Laseure
 */
public class Sourceproject {
    
    private Element xmlelement = null;
    
    /**
     * constructor
     * initiates a project path without metacode maps
     * @param projectdirectory project path
     */
    public Sourceproject(String projectdirectory) {
        setProjectdirectory(projectdirectory);
        setMetacodelist(new ArrayList<String>());
    }

    /**
     * constructor
     * read project path and metacode mpas from XML
     * @param xmlelement sourceproject XML element
     */
    public Sourceproject(Element xmlelement) {
        this.xmlelement = xmlelement;
        reset();
    }
    
    /**
     * Read configuuration from XML
     */
    public void reset() {
        if(xmlelement!=null) {
            setProjectname(xmlelement.getChild("projectname").getValue());
            setProjectdirectory(xmlelement.getChild("projectdirectory").getValue());
            ArrayList<String> metacodes = new ArrayList<String>();
            for(Element child: xmlelement.getChild("metacodes").getChildren()) {
                metacodes.add(child.getValue());
            }
            setMetacodelist(metacodes);
        }
    }
    
    /**
     * @return XML element with configuration for  this project path
     */
    public Element get() {
        Element javaprojectelement = XMLElement.newContent("javaproject", null);
        javaprojectelement.addContent(XMLElement.newContent("projectname", getProjectname()));
        javaprojectelement.addContent(XMLElement.newContent("projectdirectory", getProjectdirectory()));
        Element metacodeselement = XMLElement.newContent("metacodes", null);
        javaprojectelement.addContent(metacodeselement);
        for(String metacode: getMetacodelist()) {
            metacodeselement.addContent(XMLElement.newContent("metacode", metacode));
        }
        return javaprojectelement;
    }
    
    private StringProperty projectname = new SimpleStringProperty();
    public final String getProjectname(){return projectname.get();}
    public final void setProjectname(String value) { projectname.set(value); }
    public StringProperty projectnameProperty() {return projectname;}

    private StringProperty projectdirectory = new SimpleStringProperty();
    public final String getProjectdirectory(){return projectdirectory.get();}
    public final void setProjectdirectory(String value) { 
        projectdirectory.set(value); 
        String dir = value;
        String name = dir.substring(dir.lastIndexOf(File.separator)+1);
        while(name.equals("src") || name.equals("java") || name.equals("web")) {
            dir = dir.substring(0, dir.lastIndexOf(File.separator));
            name = dir.substring(dir.lastIndexOf(File.separator)+1);
        }
        setProjectname(name);
    }
    public StringProperty projectdirectoryProperty() {return projectdirectory;}
    
    private ObjectProperty<ArrayList<String>> metacodelist = new SimpleObjectProperty<ArrayList<String>>();
    public final ArrayList<String> getMetacodelist(){return metacodelist.get();}
    public final void setMetacodelist(ArrayList<String> value) { metacodelist.set(value); }
    public ObjectProperty<ArrayList<String>> metacodelistProperty() {return metacodelist;}
    
    /**
     * add Metacode to project
     * @param newmetacode Metacode map
     */
    public void addMetacode(String newmetacode) {
        ArrayList<String> metacodelist = new ArrayList<String>();
        boolean found = false;
        if(getMetacodelist()!=null) {
            for(String metacode: getMetacodelist()) {
                if(metacode.equals(newmetacode)) {
                    found = true;
                }
                metacodelist.add(metacode);
            }
        }
        if(!found) metacodelist.add(newmetacode);
        setMetacodelist(metacodelist);
    }
 
    /**
     * remove Metacode map from project
     * @param removemetacode Metacode map
     */
    public void removeMetacode(String removemetacode) {
        ArrayList<String> metacodelist = new ArrayList<String>();
        for(String metacode: getMetacodelist()) {
            if(!metacode.equals(removemetacode)) {
                metacodelist.add(metacode);
            }
        }
        setMetacodelist(metacodelist);
    }
    
}
