/*
 * Application controller
 */
package metacoder.controller;

import XML.XMLfile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import metacoder.codegeneration.Metacodeprocessor;
import metacoder.data.Databaseproject;
import metacoder.data.Sourceproject;
import metacoder.data.DBparameters;
import metacoder.data.xml.Connectionsetup;
import metacoder.data.xml.Ixmltags;
import metacoder.data.xml.Xmlreader;
import metacoder.util.Fileutilities;
import static metacoder.util.Fileutilities.getCurrentDir;

/**
 * Application controller
 * @author Franky Laseure
 */
public class Controller {

    public static final String PROJECTSDIRECTORY = "databases" + File.separator + "projects";
        
    private Connectionsetup connectionsetup = new Connectionsetup();
    
    //TO BE IMPLEMENTED
    //if guilocked is set, ignore all GUI actions or make them inactive
    private BooleanProperty guilocked = new SimpleBooleanProperty(false);
    public final Boolean getGuilocked() { return guilocked.get(); }
    public final void setGuilocked(Boolean value) { guilocked.set(value); }
    public BooleanProperty guilockedProperty() { return guilocked; }
    
    //list of database tools, stored in connectionsetup.xml
    private ObjectProperty<HashMap<String, Connectionsetup.DB>> dbtools = new SimpleObjectProperty<HashMap<String, Connectionsetup.DB>>();
    public final HashMap<String, Connectionsetup.DB> getDbtools(){return dbtools.get();}
    private final void setDbtools(HashMap<String, Connectionsetup.DB> value) { dbtools.set(value); }
    public ObjectProperty<HashMap<String, Connectionsetup.DB>> dbtoolsProperty() {return dbtools;}
    
    public ObservableList<String> dbtoolslist = FXCollections.observableArrayList();

    //database list, the list of xml files stored in the databases map
    private ObjectProperty<ArrayList<Xmlreader>> dblist = new SimpleObjectProperty<ArrayList<Xmlreader>>();
    public final ArrayList<Xmlreader> getDblist(){return dblist.get();}
    public final void setDblist(ArrayList<Xmlreader> value) { dblist.set(value); }
    public ObjectProperty<ArrayList<Xmlreader>> dblistProperty() {return dblist;}

    //database parameters for panel Databaseparameters
    private ObjectProperty<DBparameters> databaseparameters = new SimpleObjectProperty<DBparameters>(new DBparameters());
    public final DBparameters getDatabaseparameters(){return databaseparameters.get();}
    public final void setDatabaseparameters(DBparameters value) { databaseparameters.set(value); }
    public ObjectProperty<DBparameters> databaseparametersProperty() {return databaseparameters;}

    //database metadata properties, read from a database xml and reprocessed
    private ObjectProperty<Xmlreader> dbproperties = new SimpleObjectProperty<Xmlreader>();
    public final Xmlreader getDbproperties(){return dbproperties.get();}
    public final void setDbproperties(Xmlreader value) { dbproperties.set(value); }
    public ObjectProperty<Xmlreader> dbpropertiesProperty() {return dbproperties;}

    //Project metacode list, all sub folders of the metacode root map
    private ObjectProperty<ArrayList<String>> metacodelist = new SimpleObjectProperty<ArrayList<String>>();
    public final ArrayList<String> getMetacodelist(){return metacodelist.get();}
    public final void setMetacodelist(ArrayList<String> value) { metacodelist.set(value); }
    public ObjectProperty<ArrayList<String>> metacodelistProperty() {return metacodelist;}

    //database project, representing all data and links (to be) stored in a database xml file
    private ObjectProperty<Databaseproject> databaseproject = new SimpleObjectProperty<Databaseproject>();
    public final Databaseproject getDatabaseproject(){return databaseproject.get();}
    public final void setDatabaseproject(Databaseproject value) { 
        databaseproject.set(value); 
        setSelectedproject(null);
    }
    public ObjectProperty<Databaseproject> databaseprojectProperty() {return databaseproject;}
    
    //Selected project map
    private ObjectProperty<Sourceproject> selectedproject = new SimpleObjectProperty<Sourceproject>();
    public final Sourceproject getSelectedproject(){return selectedproject.get();}
    public final void setSelectedproject(Sourceproject value) { selectedproject.set(value); }
    public ObjectProperty<Sourceproject> selectedprojectProperty() {return selectedproject;}
    
    private Xmlreader xmlreader;
    
    /**
     * constructor, load configuration xml files
     * - database tools
     * - metacode maps
     * Set default language to java
     */
    public Controller() {
        loadXMLdata();
        loadMetacodelist();
        serviceexport2xml.setOnSucceeded(onexport2xmlSucceededevent);
    }
    
    /**
     * load database tools and program language properties per database
     */
    private void loadXMLdata() {
        loadDatabasetools();
        loadDatabases();
    }
    
    /**
     * Load Database tools set from XML file
     */
    private void loadDatabasetools() {
        setDbtools(connectionsetup.getDatabases());
        Iterator<Connectionsetup.DB> dbtoolsI = connectionsetup.getDatabases().values().iterator();
        String dbtool;
        while(dbtoolsI.hasNext()) {
            dbtool = dbtoolsI.next().getName();
            dbtoolslist.add(dbtool);
        }
    }
    
    /**
     * Load configured databases from XML files
     */
    private void loadDatabases() {
        ArrayList<Xmlreader> dbdata = new ArrayList<>();
        File[] dirContent = new java.io.File(Ixmltags.PATH).listFiles();
        Xmlreader xmlreader;
        if(dirContent!=null) {
            for(File xml: dirContent) {
                if(xml.isFile() && xml.getName().endsWith(".xml")) {
                    String fileline = xml.getName();
                    xmlreader = new Xmlreader(Ixmltags.PATH + File.separator + fileline);
                    dbdata.add(xmlreader);
                }
            }
        }
        setDblist(dbdata);
    }
    
    /**
     * Load metacode list
     */
    private void loadMetacodelist() {
        File[] metacodedirs = new File(getCurrentDir() + Metacodeprocessor.METACODEDIRECTORY).listFiles();
        ArrayList<String> metacodearray = new ArrayList<String>();
        for(File metacodedir: metacodedirs) {
            if(metacodedir.isDirectory()) {
                metacodearray.add(metacodedir.getName());
            }
        }
        setMetacodelist(metacodearray);
    }
    
    /**
     * Initialize new Database parameters
     */
    public void newDatabase() {
        setDatabaseparameters(new DBparameters());
    }
    
    /**
     * Set selected database
     * Read Database metadate from xml
     * @param dbxml: Xmlreader
     */
    public void selectDatabase(Xmlreader dbxml) {
        setDatabaseparameters(new DBparameters(dbxml));
        this.setDbproperties(dbxml);
        readDatabaseproject();
    }
    
    /**
     * Set project defaults in GUI Databaseprojectsparameters panel
     * @param dbtoolname: database tool
     */
    public void setDatabasedefaults(String dbtoolname) {
        DBparameters parameters = new DBparameters();
        Connectionsetup.DB dbtool = getDbtools().get(dbtoolname);
        parameters.setDbtool(dbtool.getName());
        parameters.setIp(dbtool.getDefault().getIp());
        parameters.setPort(dbtool.getDefault().getPort());
        parameters.setUser(dbtool.getDefault().getUser());
        parameters.setDatabasename(dbtool.getDefault().getDatabasename());
        setDatabaseparameters(parameters);
    }
    
    public ServiceExport2XML serviceexport2xml = new ServiceExport2XML();
    
    /**
     * Start service serviceexport2xml
     */
    public void export_toxml() {
        //check parameters
        String dbtool = this.getDatabaseparameters().getDbtool();
        Connectionsetup.DB dbsetup = (Connectionsetup.DB)connectionsetup.getDatabases().get(dbtool);
        serviceexport2xml.setOnSucceeded(onexport2xmlSucceededevent);
        serviceexport2xml.setDB(dbsetup);
        serviceexport2xml.setDBparameters(this.getDatabaseparameters());
        serviceexport2xml.restart();
    }
    
    /**
     * serviceexport2xml finished succesfully event
     * reload databases list
     * reloadload all database properties of each database
     */
    private EventHandler onexport2xmlSucceededevent = new EventHandler<WorkerStateEvent>() {
        @Override
        public void handle(WorkerStateEvent t) {
            //update list
            loadDatabases();
            for(Xmlreader xmlreader: getDblist()) {
                if(xmlreader.getDatabase().getDatabasename().toLowerCase().equals(getDatabaseparameters().getDatabasename().toLowerCase())
                        && xmlreader.getDatabase().getIp().equals(getDatabaseparameters().getIp())) {
                    setDatabaseparameters(new DBparameters(xmlreader));
                    setDbproperties(xmlreader);
                    readDatabaseproject();
                }
            }
            
        }
    };    
    
    public ServiceExport2Projects serviceexport2projects = new ServiceExport2Projects(this);

    /**
     * Start the service serviceexport2projects
 this is the metacode generation in the project maps
     */
    public void export_toprojects() {
        writeDatabaseproject();
        serviceexport2projects.restart();
    }
    
    /**
     * remove project link from database project
     * @param project Sourceproject
     */
    public void deleteProject(Sourceproject project) {
        getDatabaseproject().deleteProject(project);
    }
    
    /**
     * add project metacode to active project
     * @param metacode: project metacode map
     */
    public void addMetacode(String metacode) {
        if(getSelectedproject()!=null) {
            getSelectedproject().addMetacode(metacode);
        }
    }

    /**
     * remove project metacode from active project
     * @param metacode: metacode name = project metacode map
     */
    public void removeMetacode(String metacode) {
        if(getSelectedproject()!=null) {
            getSelectedproject().removeMetacode(metacode);
        }
    }
    
    /**
     * Read Database project XML
     */
    public void readDatabaseproject() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            SAXBuilder saxbuilder = new SAXBuilder();
            Document xmldoc = saxbuilder.build(new File(buildDatabaseprojectfilename()));
            setDatabaseproject(new Databaseproject(xmldoc));
        }
        catch(JDOMException e) {
            setDatabaseproject(new Databaseproject(getDatabaseparameters().getDatabasename()));
        }
        catch(IOException e) {
            setDatabaseproject(new Databaseproject(getDatabaseparameters().getDatabasename()));
        }
    }

    /**
     * Write Database project XML
     */
    public void writeDatabaseproject() {
        try {
            XMLfile file = new XMLfile();
            file.Replace(buildDatabaseprojectfilename());
            file.setDocument(getDatabaseproject().get());
            file.Write();
        } 
        catch(FileNotFoundException e) {
        }
        catch(IOException e) {
        }
    }

    /**
     * construct database project xml filename
     * @return filename
     */
    public String buildDatabaseprojectfilename() {
        return Fileutilities.getCurrentDir() + PROJECTSDIRECTORY + File.separator + this.getDatabaseparameters().getDatabasename() + ".xml";
    }
    
}
