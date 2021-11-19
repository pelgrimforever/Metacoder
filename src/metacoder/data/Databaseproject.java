package metacoder.data;

import XML.XMLElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Databaseproject
 * project maps linked to a database name
 * @author Franky Laseure
 */
public class Databaseproject {
    
    private Document xmldoc = null;
 
    /**
     * constructor
     * @param databasename Database
     */
    public Databaseproject(String databasename) {
        setDatabasename(databasename);
    }
    
    /**
     * constructor, reads linked projects from xml
     * @param xmldoc database projects XML
     */
    public Databaseproject(Document xmldoc) {
        this.xmldoc = xmldoc;
        reset();
    }
    
    /**
     * reset values to XML file if an xml document is used
     */
    public void reset() {
        if(xmldoc!=null) {
            Element root = xmldoc.getRootElement();
            Element databaseelement = root.getChild("databasename");
            setDatabasename(databaseelement.getValue());
            Element projectselement = root.getChild("projects");
            ArrayList<Sourceproject> projects = new ArrayList<Sourceproject>();
            for(Element child: projectselement.getChildren()) {
                projects.add(new Sourceproject(child));
            }
            setProjectlist(projects);
        }
    }
    
    /**
     * @return XML Document with changes
     */
    public Document get() {
        xmldoc = new Document();
        Element rootelement = XMLElement.newContent("databaseproject", null);
        Element projectselement = XMLElement.newContent("projects", null);
        Element databaseelement = XMLElement.newContent("databasename", getDatabasename());
        xmldoc.setRootElement(rootelement);
        rootelement.addContent(databaseelement);
        rootelement.addContent(projectselement);
        for(Sourceproject project: getProjectlist()) {
            projectselement.addContent(project.get());
        }
        return xmldoc;
    }
    
    private StringProperty databasename = new SimpleStringProperty();
    public final String getDatabasename(){return databasename.get();}
    public final void setDatabasename(String value) { databasename.set(value); }
    public StringProperty databasenameProperty() {return databasename;}

    private ObjectProperty<ArrayList<Sourceproject>> projectlist = new SimpleObjectProperty<ArrayList<Sourceproject>>(new ArrayList<Sourceproject>());
    public final ArrayList<Sourceproject> getProjectlist(){return projectlist.get();}
    public final void setProjectlist(ArrayList<Sourceproject> value) { projectlist.set(value); }
    public ObjectProperty<ArrayList<Sourceproject>> projectlistProperty() {return projectlist;}
    
    /**
     * add project path to databaseprojects
     * @param newproject Sourceproject 
     */
    public void addProject(Sourceproject newproject) {
        ArrayList<Sourceproject> projectlist = new ArrayList<Sourceproject>();
        boolean found = false;
        for(Sourceproject project: getProjectlist()) {
            if(project.getProjectname().equals(newproject.getProjectname())) {
                found = true;
            }
            projectlist.add(project);
        }
        if(!found) projectlist.add(newproject);
        setProjectlist(projectlist);
    }
    
    /**
     * remove project path from databaseprojects
     * @param deleteproject Sourceproject
     */
    public void deleteProject(Sourceproject deleteproject) {
        ArrayList<Sourceproject> projectlist = new ArrayList<Sourceproject>();
        for(Sourceproject project: getProjectlist()) {
            if(!project.getProjectname().equals(deleteproject.getProjectname())) {
                projectlist.add(project);
            }
        }
        setProjectlist(projectlist);
    }
}
