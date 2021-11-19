package metacoder.data.xml;

import java.sql.SQLException;
import java.util.HashMap;

import org.jdom2.Element;

import XML.XMLElement;
import XML.XMLfile;
import java.io.File;
import java.util.Iterator;
import metacoder.data.DBparameters;
import metacoder.data.metadata.Column;
import metacoder.data.metadata.Foreignkey;
import metacoder.data.metadata.Foreignkeycolumn;
import metacoder.data.metadata.Index;
import metacoder.data.metadata.Indexcolumn;
import metacoder.data.metadata.Primarykey;
import metacoder.data.metadata.Primarykeycolumn;
import metacoder.data.metadata.Table;
import static metacoder.data.xml.Ixmltags.COLUMN;
import static metacoder.data.xml.Ixmltags.COLUMNNAME;
import static metacoder.data.xml.Ixmltags.COLUMNS;
import static metacoder.data.xml.Ixmltags.DATATYPE;
import static metacoder.data.xml.Ixmltags.DATATYPENAME;
import static metacoder.data.xml.Ixmltags.DECIMALDIGITS;
import static metacoder.data.xml.Ixmltags.DEFAULTVALUE;
import static metacoder.data.xml.Ixmltags.FCOLUMNNAME;
import static metacoder.data.xml.Ixmltags.FILEONDISK;
import static metacoder.data.xml.Ixmltags.FKJAVANAME;
import static metacoder.data.xml.Ixmltags.FKNAME;
import static metacoder.data.xml.Ixmltags.FOREIGNKEY;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYS;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYSCHEMA;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYTABLE;
import static metacoder.data.xml.Ixmltags.FTABLE;
import static metacoder.data.xml.Ixmltags.FULLNAME;
import static metacoder.data.xml.Ixmltags.INDEX;
import static metacoder.data.xml.Ixmltags.INDEXES;
import static metacoder.data.xml.Ixmltags.INDEXNAME;
import static metacoder.data.xml.Ixmltags.JAVATYPE;
import static metacoder.data.xml.Ixmltags.NAME;
import static metacoder.data.xml.Ixmltags.NULLABLE;
import static metacoder.data.xml.Ixmltags.NUMERICPRECISIONRADIX;
import static metacoder.data.xml.Ixmltags.PARTOFPRIMARYKEY;
import static metacoder.data.xml.Ixmltags.PCOLUMNNAME;
import static metacoder.data.xml.Ixmltags.PKNAME;
import static metacoder.data.xml.Ixmltags.PRIMARYKEY;
import static metacoder.data.xml.Ixmltags.PRIMARYKEYSCHEMA;
import static metacoder.data.xml.Ixmltags.PRIMARYKEYTABLE;
import static metacoder.data.xml.Ixmltags.PTABLE;
import static metacoder.data.xml.Ixmltags.REMARKS;
import static metacoder.data.xml.Ixmltags.SCHEMA;
import static metacoder.data.xml.Ixmltags.SEQUENCE;
import static metacoder.data.xml.Ixmltags.SIZE;
import static metacoder.data.xml.Ixmltags.SQLDATATYPE;
import static metacoder.data.xml.Ixmltags.SQLDATETIMESUB;
import static metacoder.data.xml.Ixmltags.TABLEREMARKS;
import static metacoder.data.xml.Ixmltags.TABLESCHEMA;
import static metacoder.data.xml.Ixmltags.TABLETYPE;
import static metacoder.data.xml.Ixmltags.UNIFORMDATATYPE;
import static metacoder.data.xml.Ixmltags.UNIFORMSQLTYPE;
import static metacoder.data.xml.Ixmltags.UNIQUE;

/**
 * Xmlbuilder
 * build an xml file of the database metadata
 * @author Franky Laseure
 */
public class Xmlbuilder {
	
    /**
     * build xml file name
     * @param dbtool Database tool
     * @param ip IP address
     * @param port Port number
     * @param databasename Database name
     * @return filename (without extention)
     */
    private static String buildFilename(String dbtool, String ip, String port, String databasename) {
        String name = databasename;
        if(name.contains(File.separator)) {
            name = name.substring(name.lastIndexOf(File.separator)+1);
        }
        return name + "_" + ip + "_" + port + "_" + dbtool;
    }

    /**
     * build xml file name
     * @param dbtool Database tool
     * @param ip IP address
     * @param port Port number
     * @param databasename Database name
     * @return filename with xml extention
     */
    public static String buildXMLFilename(String dbtool, String ip, String port, String databasename) {
        return buildFilename(dbtool, ip, port, databasename) + ".xml";
    }
    
    /**
     * build xml file name
     * @param dbpar DBparameters
     * @return filename (without extention)
     */
    private static String buildFilename(DBparameters dbpar) {
        String dbtool = dbpar.getDbtool();
        String ip = dbpar.getIp();
        String port = dbpar.getPort();
        String databasename = dbpar.getDatabasename();
        return buildFilename(dbtool, ip, port, databasename);
    }
    
    /**
     * build xml file name
     * @param dbpar DBparameters
     * @return filename with extention
     */
    private static String buildXMLFilename(DBparameters dbpar) {
        return buildFilename(dbpar) + ".xml";
    }
    
    /**
     * load Tables data into dbxml
     * @param dbxml XMLfile (end result)
     * @param tables XML tables element
     * @param dbtable Table metadata
     * @param oldversion previous XMLfile
     */
    public static void gettableElement(XMLfile dbxml, Element tables, Table dbtable, Xmlreader oldversion) {
        Xmlreader.Table oldtable = null;
        if(oldversion!=null) {
            oldtable = (Xmlreader.Table)oldversion.getTables().get(dbtable.getName());
        }

        String[][] tableproperties = { {dbtable.getName(), TABLETYPE, dbtable.getType() },
            {dbtable.getName(), TABLESCHEMA, dbtable.getSchema() },
            {dbtable.getName(), TABLEREMARKS, dbtable.getRemarks()} };
        dbxml.addTree(tables, tableproperties);
        Element tableelement = tables.getChild(dbtable.getName());

        //columns
        Element columns = XMLElement.newContent(COLUMNS, "");
        tableelement.addContent(columns);
        int counter = 0;
        for(Column col : dbtable.getColumns()) {
            //get data from old version
            String fileondisk = "0";
            if(oldtable!=null) {
                Xmlreader.Table.Column oldcolumn = (Xmlreader.Table.Column)oldtable.getColumns().get(col.getName());
                if(oldcolumn!=null) fileondisk = oldcolumn.getIsfileondisk() ? "1": "0";
            }
            //get data from old version
            String[][] colproperties = { {COLUMN + counter, COLUMNNAME, col.getName() },
                {COLUMN + counter, DATATYPE, "" + col.getDatatype() },
                {COLUMN + counter, UNIFORMDATATYPE, "" + col.getUniformdatatype() },
                {COLUMN + counter, SIZE, "" + col.getSize() },
                {COLUMN + counter, DECIMALDIGITS, "" + col.getDecimaldigits() },
                {COLUMN + counter, NUMERICPRECISIONRADIX, "" + col.getNumberprecisionradix() },
                {COLUMN + counter, NULLABLE, "" + col.getNullable() },
                {COLUMN + counter, DEFAULTVALUE, col.getColumndefinition() },
                //bug in postgresql if a drop column has occured, wrong ordinal position
                //{COLUMN + counter, SEQUENCE, "" + col.getordinalPosition() },
                {COLUMN + counter, SEQUENCE, "" + (counter + 1) },
                {COLUMN + counter, FULLNAME, "" + col.getFullname() },
                {COLUMN + counter, JAVATYPE, "" + col.getJavatype() },
                {COLUMN + counter, REMARKS, "" + col.getRemarks() },
                {COLUMN + counter, SCHEMA, "" + col.getSchema() },
                {COLUMN + counter, SQLDATATYPE, "" + col.getSqldatatype() },
                {COLUMN + counter, SQLDATETIMESUB, "" + col.getSqldatetimesub() },
                {COLUMN + counter, DATATYPENAME, "" + col.getTypename() },
                {COLUMN + counter, UNIFORMSQLTYPE, "" + col.getDatatype() },
                {COLUMN + counter, FILEONDISK, fileondisk } };
            dbxml.addTree(columns, colproperties);
            counter++;
        }
    }

    /**
     * load primary key elements into dbxml
     * @param dbxml XMLfile (end result)
     * @param tables XML tables element
     * @param dbtable Table metadata
     */
    public static void getprimarykeyElement(XMLfile dbxml, Element tables, Table dbtable) {
        Element tableelement = tables.getChild(dbtable.getName());
        HashMap primarykeyhash = new HashMap();
        Primarykey primarykey = dbtable.getPrimarykey();
        if(primarykey!=null) {
            Element pkcolumnelement;
            int counter = 0;
            Element pkelement = XMLElement.newContent(PRIMARYKEY, "");
            pkelement.addContent(XMLElement.newContent(NAME, primarykey.getName()));
            tableelement.addContent(pkelement);
            for(Primarykeycolumn primarykeycolumn : primarykey.getColumns()) {
                pkcolumnelement = XMLElement.newContent(COLUMN, "");
                pkcolumnelement.addContent(XMLElement.newContent(COLUMNNAME, primarykeycolumn.getName()));
                pkcolumnelement.addContent(XMLElement.newContent(SEQUENCE, "" + primarykeycolumn.getKeysequence()));
                pkelement.addContent(pkcolumnelement);
                primarykeyhash.put(primarykeycolumn.getName(), primarykeycolumn.getName());
                counter++;
            }
        }
    }

    /**
     * load foreign key elements into dbxml
     * @param dbxml XMLfile (end result)
     * @param tables XML tables element
     * @param dbtable Table metadata
     */
    public static void getforeignkeysElement(XMLfile dbxml, Element tables, Table dbtable) {
        Element tableelement = tables.getChild(dbtable.getName());
        Element fkeys = XMLElement.newContent(FOREIGNKEYS, "");
        HashMap foreignkeyhash = new HashMap();
        tableelement.addContent(fkeys);
        int counter = 0;
        //Set java names for foreign keys, find doubles and give numbered name
        HashMap<String, String> variablenames = new HashMap<>();
        HashMap<String, Integer> javanamedoubles = new HashMap<>();
        String previousfkname = "";
        for(Foreignkey fk : dbtable.getForeignkeys()) {
            if(!previousfkname.equals(fk.getPKname() + fk.getFKname())) {
                previousfkname = fk.getPKname() + fk.getFKname();
                variablenames.put(FOREIGNKEY + counter, fk.getPKtable());
                counter++;
            }
        }
        //find doubles
        int count1 = 0;
        for(String variablename1: variablenames.values()) {
            int count2 = 0;
            for(String jname2: variablenames.values()) {
                if(count1!=count2 && variablename1.equals(jname2)) {
                    javanamedoubles.put(variablename1, 1);
                }
                count2++;
            }
            count1++;
        }
        //process doubles
        counter = 0;
        for(String variablename1: variablenames.values()) {
            if(javanamedoubles.containsKey(variablename1)) {
                int number = (Integer)javanamedoubles.get(variablename1);
                variablenames.put(FOREIGNKEY + counter, variablename1 + number);
                javanamedoubles.put(variablename1, ++number);
            }
            counter++;
        }
        counter = 0;
        previousfkname = "";
        for(Foreignkey metafk : dbtable.getForeignkeys()) {
            if(!previousfkname.equals(metafk.getPKname() + metafk.getFKname())) {
                previousfkname = metafk.getPKname() + metafk.getFKname();
                String[][] fkproperties = { {FOREIGNKEY + counter, PRIMARYKEYSCHEMA, metafk.getPKschema() },
                    {FOREIGNKEY + counter, PRIMARYKEYTABLE, metafk.getPKtable() },
                    {FOREIGNKEY + counter, FOREIGNKEYSCHEMA, metafk.getFKschema() },
                    {FOREIGNKEY + counter, FOREIGNKEYTABLE, metafk.getFKtable() },
                    {FOREIGNKEY + counter, PKNAME, metafk.getPKname() },
                    {FOREIGNKEY + counter, FKNAME, metafk.getFKname() },
                    {FOREIGNKEY + counter, FKJAVANAME, (String)variablenames.get(FOREIGNKEY + counter) } };
                dbxml.addTree(fkeys, fkproperties);
                int i = 0;
                for(Foreignkeycolumn fkc : metafk.getColumns()) {
                    Element fkelement = fkeys.getChild(FOREIGNKEY + counter);
                    //in mysql, fkc.getFKname is null
                    if(metafk.getFKname().equals(fkc.getFKname()) || fkc.getFKname()==null) {
                        String[][] fkcproperties = { {COLUMN + i, PTABLE, fkc.getPKtable() },
                            {COLUMN + i, PCOLUMNNAME, fkc.getPKcolumn() },
                            {COLUMN + i, FTABLE, fkc.getFKtable() },
                            {COLUMN + i, FCOLUMNNAME, fkc.getFKcolumn() },
                            {COLUMN + i, SEQUENCE, "" + fkc.getKeysequence() },
                            {COLUMN + i, PARTOFPRIMARYKEY, foreignkeyhash.containsKey(fkc.getFKcolumn()) ?  "1" : "0" } };
                        dbxml.addTree(fkelement, fkcproperties);
                        i++;
                    }
                }
                counter++;
            }
        }
    }

    /**
     * load index elements into dbxml
     * @param dbxml XMLfile (end result)
     * @param tables XML tables element
     * @param dbtable Table metadata
     */
    public static void getindexElement(XMLfile dbxml, Element tables, Table dbtable) {
        Element tableelement = tables.getChild(dbtable.getName());
        Element indexes = XMLElement.newContent(INDEXES, "");
        tableelement.addContent(indexes);
        int counter = 0;
        String indexname = "";
        for(Index index: dbtable.getIndexes()) {
            if(!indexname.equals(index.getName()) && !index.getName().equals("PRIMARY")) {
                indexname = index.getName();
                String[][] indexproperties = { {INDEX + counter, INDEXNAME, index.getName()},
                    {INDEX + counter, UNIQUE, "" + index.isUnique()},
                    {INDEX + counter, COLUMNS, ""} };
                dbxml.addTree(indexes, indexproperties);
                Element columnse = indexes.getChild(INDEX + counter).getChild(COLUMNS);
                Element indexelement = indexes.getChild(INDEX + counter);
                int i = 0;
                for(Indexcolumn indexcolumn: index.getColumns()) {
                    if(indexcolumn.getName().equals(index.getName())) {
                        String[][] fkcproperties = { {COLUMN + i, COLUMNNAME, indexcolumn.getColumn() },
                            {COLUMN + i, SEQUENCE, "" + indexcolumn.getSequence()} };
                        dbxml.addTree(columnse, fkcproperties);
                    }
                    i++;
                }
                counter++;
            }
        }
    }
	
}
