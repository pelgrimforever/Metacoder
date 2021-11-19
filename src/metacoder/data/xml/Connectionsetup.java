package metacoder.data.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import metacoder.util.Fileutilities;

/**
 * XML connectionsetup.xml data
 * this class reads this setup file to configure database connections
 * @author pelgrim
 */
public class Connectionsetup {

    //xml
    private Document xmldoc;
    private Element root;

    //constants
    //db
    public static final String XMLFILE = "connectionsetup.xml";
    public static final String JAVACLASS = "javaclass";
    public static final String CONNECTIONPREFIX = "connectionprefix";
    public static final String CONNECTIONTOOL = "connectiontool";
    public static final String DATABASESEPARATOR = "databaseseparator";

    //data structures
    private DB database;
    private HashMap<String, Connectionsetup.DB> databases = new HashMap<String, Connectionsetup.DB>();

    /**
     * constructor, read XML
     */
    public Connectionsetup() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            SAXBuilder saxbuilder = new SAXBuilder();
            xmldoc = saxbuilder.build(new File(Fileutilities.getCurrentDir() + XMLFILE));
            root = xmldoc.getRootElement();
            for(Element entry: root.getChildren()) {
                database = new DB(entry);
                databases.put(database.getName(), database);
            }		    
        }
        catch(JDOMException jde) {
            System.out.println(jde.getMessage());
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }		
    }

    /**
     * database list
     * @return HashMap
     */
    public HashMap<String, Connectionsetup.DB> getDatabases() {
        return databases;
    }

    /**
     * Database tool
     * contains all attributes to build a database connection url
     */
    public class DB {

        private final String DEFAULT = "default";

        private String name; 
        private String javaclass;
        private String connectionprefix;
        private String connectiontool;
        private String databaseseparator;
        private Defaults defaults;

        /**
         * constructor
         * read database tool parts from an XML element
         * @param db 
         */
        private DB(Element db) {
            name = db.getName();
            javaclass = db.getChildText(JAVACLASS);
            connectionprefix = db.getChildText(CONNECTIONPREFIX);
            connectiontool = db.getChildText(CONNECTIONTOOL);
            databaseseparator = db.getChildText(DATABASESEPARATOR);
            defaults = new Defaults(db.getChild(DEFAULT));
        }

        /**
         * @return database tool name
         */
        public String getName()  {
            return name;
        }

        /**
         * @return connection java class name
         */
        public String getJavaclass() {
            return javaclass;
        }

        /**
         * @return database connection prefix
         */
        public String getConnectionprefix() {
            return connectionprefix;
        }

        /**
         * @return connection tool
         */
        public String getConnectiontool() {
            return connectiontool;
        }

        /**
         * database separator
         * @return Database separator character(s)
         */
        public String getDatabaseseparator() {
            return databaseseparator;
        }

        /**
         * database url default properties
         * @return Database connection defaults
         */
        public Defaults getDefault() {
            return defaults;
        }

        /**
         * construct connection url with pre-configures properties
         * @param ip: database server ip address
         * @param port: database server port number
         * @param databasename: database name
         * @return connection url
         */
        public String buildURL(String ip, String port, String databasename) {
            return connectionprefix + connectiontool + ip + defaults.portseparator + port + databaseseparator + databasename;
        }

        /**
         * database connection url default properties
         */
        public class Defaults {
            private final String IP = "ip";
            private final String PORTSEPARATOR = "portseparator";
            private final String PORT = "port";
            private final String DATABASENAME = "databasename";
            private final String USER = "user";

            private String ip;
            private String portseparator;
            private String port;
            private String databasename;
            private String user;

            /**
             * constructor
             * read all default attributes to build a database connection url
             * @param defaults Connection default values
             */
            public Defaults(Element defaults) {
                ip = defaults.getChildText(IP);
                portseparator = defaults.getChildText(PORTSEPARATOR);
                port = defaults.getChildText(PORT);
                databasename = defaults.getChildText(DATABASENAME);
                user = defaults.getChildText(USER);
            }

            public String getIp() {
                return ip;
            }

            public String getPortseparator() {
                return portseparator;
            }

            public String getPort() {
                return port;
            }

            public String getDatabasename() {
                return databasename;
            }

            public String getUser() {
                return user;
            }
        }
    }	
}
