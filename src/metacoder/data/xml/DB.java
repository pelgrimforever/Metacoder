package metacoder.data.xml;

import org.jdom2.Element;
import static metacoder.data.xml.Ixmltags.DATABASENAME;
import static metacoder.data.xml.Ixmltags.IP;
import static metacoder.data.xml.Ixmltags.ODBCDRIVER;
import static metacoder.data.xml.Ixmltags.ODBCDRIVERVERSION;
import static metacoder.data.xml.Ixmltags.PORT;
import static metacoder.data.xml.Ixmltags.PRODUCTNAME;
import static metacoder.data.xml.Ixmltags.PRODUCTVERSION;
import static metacoder.data.xml.Ixmltags.TABLECOUNT;
import static metacoder.data.xml.Ixmltags.TOOL;
import static metacoder.data.xml.Ixmltags.URL;
import static metacoder.data.xml.Ixmltags.USER;
import static metacoder.data.xml.Ixmltags.XMLFILE;

/**
 * XML database representation
 * @author Franky Laseure
 */
public class DB {
    private String xmlfile;
    private String dbtool;
    private String ip;
    private String port;
    private String databasename;
    private String user;
    private String url;
    private String jdbcdriver;
    private String jdbcdriverversion;
    private String productname;
    private String productversion;
    private int tablecount;

    /**
     * constructor
     * read XML database metadata
     * @param db DB metadata
     */
    protected DB(Element db) {
        xmlfile = db.getChildText(XMLFILE);
        dbtool = db.getChildText(TOOL);
        ip = db.getChildText(IP);
        port = db.getChildText(PORT);
        databasename = db.getChildText(DATABASENAME);
        user = db.getChildText(USER);
        url = db.getChildText(URL);
        jdbcdriver = db.getChildText(ODBCDRIVER);
        jdbcdriverversion = db.getChildText(ODBCDRIVERVERSION);
        productname = db.getChildText(PRODUCTNAME);
        productversion = db.getChildText(PRODUCTVERSION);
        tablecount = Integer.valueOf(db.getChildText(TABLECOUNT));
    }

    /**
     * @return database xml file
     */
    public String getXmlfile() {
        return xmlfile;
    }

    /**
     * @return database tool name
     */
    public String getDbtool() {
        return dbtool;
    }

    /**
     * @return IP address
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return String - port number
     */
    public String getPort() {
        return port;
    }

    /**
     * @return database name (first character upper case)
     */
    public String getDatabasename() {
        return Xmltags.Ulow(databasename);
    }

    /**
     * @return lower case database name
     */
    public String getdatabasename() {
        return Xmltags.Low(databasename);
    }

    /**
     * @return database original name
     */
    public String getOriginalname() {
        return databasename;
    }

    /**
     * @return user name
     */
    public String getUser() {
        return user;
    }

    /**
     * @return database url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return jdbc java class
     */
    public String getJDBCdriver() {
        return jdbcdriver;
    }

    /**
     * @return jdbc java class driver version
     */
    public String getJDBCdriverversion() {
        return jdbcdriverversion;
    }

    /**
     * @return database tool name
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @return database tool version number
     */
    public String getProductversion() {
        return productversion;
    }

    /**
     * @return table count
     */
    public int getTablecount() {
        return tablecount;
    }
}

