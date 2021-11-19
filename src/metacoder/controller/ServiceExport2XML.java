/*
 * Export Database metadata to XML
 */
package metacoder.controller;

import XML.XMLElement;
import XML.XMLfile;
import base.AppController;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.jdom2.Element;
import metacoder.data.DBparameters;
import metacoder.data.jdbc.JDBCmetadata;
import metacoder.data.metadata.Database;
import metacoder.data.metadata.Table;
import metacoder.data.xml.Connectionsetup;
import metacoder.data.xml.Ixmltags;
import metacoder.data.xml.Xmlbuilder;
import metacoder.data.xml.Xmlreader;

/**
 * Service
 * Read Database metadata
 * Export metadata to XML
 * expose parameters for GUI
 * @author Franky Laseure
 */
public class ServiceExport2XML extends Service<Boolean> {
    
    private Connectionsetup.DB dbsetup;
    public void setDB(Connectionsetup.DB dbsetup) { this.dbsetup = dbsetup; }
    
    private DBparameters parameters;
    public void setDBparameters(DBparameters parameters) { this.parameters = parameters; }
    
    private final StringProperty progressstring = new SimpleStringProperty();
    public final String getProgressstring(){return progressstring.get();}
    public final void setProgressstring(String value) { progressstring.set(value); }
    public StringProperty progressstringProperty() {return progressstring;}

    private final IntegerProperty tablecount = new SimpleIntegerProperty(0);
    public final Integer getTablecount(){return tablecount.get();}
    public final void setTablecount(Integer value) { tablecount.set(value); }
    public IntegerProperty tablecountProperty() {return tablecount;}
    
    private final IntegerProperty progresscounter = new SimpleIntegerProperty(0);
    public final Integer getProgresscounter(){return progresscounter.get();}
    public final void setProgresscounter(Integer value) { 
        progresscounter.set(value); 
        double perc = 0;
        if(getTablecount()>0) perc = (double)value / getTablecount();
        setProgressperc(perc);
        setProgressstring("" + value + " / " + getTablecount());
    }
    public IntegerProperty progresscounterProperty() {return progresscounter;}
    
    private DoubleProperty progressperc = new SimpleDoubleProperty(0);
    public final Double getProgressperc(){return progressperc.get();}
    public final void setProgressperc(double value) { progressperc.set(value); }
    public DoubleProperty progresspercProperty() {return progressperc;}
    
    /**
     * contructor
     */
    public ServiceExport2XML() {
    }
    
    /**
     * Read database structure
     * Export to XML
     * @return true
     */
    @Override
    protected Task createTask() {
        return new Task<Boolean>() {
            protected Boolean call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        setProgressstring("INIT");
                    }
                });
                //check parameters
                String dbtool = parameters.getDbtool();
                String ip = parameters.getIp();
                String port = parameters.getPort();
                String databasename = parameters.getDatabasename();
                String user = parameters.getUser();
                String password = parameters.getPassword();
                String url = dbsetup.buildURL(ip, port, databasename);
                final JDBCmetadata metadata = new JDBCmetadata(dbtool, dbsetup.getJavaclass(), url, user, password);
                final Database database = metadata.getMetadatabase();
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        setTablecount(database.getTablecount());
                        setProgresscounter(0);
                    }
                });

                XMLfile dbxml = new XMLfile();
                String filename = Ixmltags.PATH + File.separator + Xmlbuilder.buildXMLFilename(dbtool, ip, port, databasename);
                Xmlreader oldversion = null;

                File xmlfile = new File(filename);                    
                if(xmlfile.exists()) oldversion = new Xmlreader(filename);
                xmlfile.mkdirs();
                if(xmlfile.exists()) dbxml.Replace(filename);
                else dbxml.Open(filename, false);
                dbxml.initialize(Ixmltags.DATABASE, Ixmltags.NAMEVALUE);
                String[][] dbproperties = { {Ixmltags.DB, Ixmltags.TOOL, dbtool},
                    {Ixmltags.DB, Ixmltags.IP, ip },
                    {Ixmltags.DB, Ixmltags.PORT, port },
                    {Ixmltags.DB, Ixmltags.DATABASENAME, databasename },
                    {Ixmltags.DB, Ixmltags.USER, user },
                    {Ixmltags.DB, Ixmltags.URL, metadata.getDatabaseURL() },
                    {Ixmltags.DB, Ixmltags.ODBCDRIVER, database.getDriver()},
                    {Ixmltags.DB, Ixmltags.ODBCDRIVERVERSION, database.getDriverVersion()},
                    {Ixmltags.DB, Ixmltags.PRODUCTNAME, database.getProductName()},
                    {Ixmltags.DB, Ixmltags.PRODUCTVERSION, database.getProductVersion()},
                    {Ixmltags.DB, Ixmltags.TABLECOUNT, "" + database.getTablecount()} };
                dbxml.addTree(dbproperties);

                Element dbelement = dbxml.getDocument().getRootElement();
                Element tables = XMLElement.newContent(Ixmltags.TABLES, "");
                dbelement.addContent(tables);

                int counter = database.getTablecount();
                boolean hasprimarykey = false;
                try {
                    for(Table dbtable : database.getTables()) {
                        //only non system tables
                        //only tables with a primary key
                        //all views
                        System.out.println(dbtable.getSchema() + " " + dbtable.getName());
                        if(!dbtable.isSystemtable() && dbtable.getPrimarykey()!=null || dbtable.isView()) {
                            Xmlbuilder.gettableElement(dbxml, tables, dbtable, oldversion);
                            if(dbtable.isTable()) {
                                Xmlbuilder.getprimarykeyElement(dbxml, tables, dbtable);
                                Xmlbuilder.getforeignkeysElement(dbxml, tables, dbtable);
                                Xmlbuilder.getindexElement(dbxml, tables, dbtable);
                            }
                        }
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                setProgresscounter(getProgresscounter()+1);
                            }
                        });
                    }
                }
                catch(Exception e) {
                    AppController.showMessage(e.getMessage());
                }
                try {
                    dbxml.Write();
                }
                catch(IOException e) {
                    AppController.showMessage(e.getMessage());
                }
                return true;
            }
        };
    }
    
    
}
