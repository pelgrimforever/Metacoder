package metacoder.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import metacoder.data.xml.Xmlreader;

/**
 * Database connection string parameters
 * defined in database xml
 * @author Franky Laseure
 */
public class DBparameters {
    
    private Xmlreader dbxml = null;
    
    /**
     * constructor, empty parameters
     */
    public DBparameters() {
    }
    
    /**
     * constructor, read from xml
     * @param dbxml Xmlreader
     */
    public DBparameters(Xmlreader dbxml) {
        this.dbxml = dbxml;
        reset();
    }
    
    public void reset() {
        if(dbxml!=null) {
            setDbtool(dbxml.getDatabase().getDbtool());
            setDatabasename(dbxml.getDatabase().getOriginalname());
            setIp(dbxml.getDatabase().getIp());
            setPort(dbxml.getDatabase().getPort());
            setUrl(dbxml.getDatabase().getUrl());
            setUser(dbxml.getDatabase().getUser());
        }
    }
    
    private StringProperty dbtool = new SimpleStringProperty();
    public final String getDbtool(){return dbtool.get();}
    public final void setDbtool(String value) { dbtool.set(value); }
    public StringProperty dbtoolProperty() {return dbtool;}

    private StringProperty databasename = new SimpleStringProperty();
    public final String getDatabasename(){return databasename.get();}
    public final void setDatabasename(String value) { databasename.set(value); }
    public StringProperty databasenameProperty() {return databasename;}
    
    private StringProperty ip = new SimpleStringProperty();
    public final String getIp(){return ip.get();}
    public final void setIp(String value) { ip.set(value); }
    public StringProperty ipProperty() {return ip;}
    
    private StringProperty port = new SimpleStringProperty();
    public final String getPort(){return port.get();}
    public final void setPort(String value) { port.set(value); }
    public StringProperty portProperty() {return port;}
    
    private StringProperty url = new SimpleStringProperty();
    public final String getUrl(){return url.get();}
    public final void setUrl(String value) { url.set(value); }
    public StringProperty urlProperty() {return url;}
    
    private StringProperty user = new SimpleStringProperty();
    public final String getUser(){return user.get();}
    public final void setUser(String value) { user.set(value); }
    public StringProperty userProperty() {return user;}
    
    private StringProperty password = new SimpleStringProperty();
    public final String getPassword(){return password.get();}
    public final void setPassword(String value) { password.set(value); }
    public StringProperty passwordProperty() {return password;}
    
}
