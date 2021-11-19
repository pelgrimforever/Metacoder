package metacoder.data.xml;

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
import metacoder.data.Programlanguage;
import metacoder.data.jdbc.IDBconnection;
import metacoder.data.xml.Xmlreader.Table.ExternalForeignkey;
import metacoder.data.xml.Xmlreader.Table.RelationalForeignkey;
import static metacoder.data.xml.Ixmltags.COLUMNNAME;
import static metacoder.data.xml.Ixmltags.COLUMNS;
import static metacoder.data.xml.Ixmltags.DATATYPE;
import static metacoder.data.xml.Ixmltags.DATATYPENAME;
import static metacoder.data.xml.Ixmltags.DB;
import static metacoder.data.xml.Ixmltags.DECIMALDIGITS;
import static metacoder.data.xml.Ixmltags.DEFAULTVALUE;
import static metacoder.data.xml.Ixmltags.FCOLUMNNAME;
import static metacoder.data.xml.Ixmltags.FILEONDISK;
import static metacoder.data.xml.Ixmltags.FKJAVANAME;
import static metacoder.data.xml.Ixmltags.FKNAME;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYS;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYSCHEMA;
import static metacoder.data.xml.Ixmltags.FOREIGNKEYTABLE;
import static metacoder.data.xml.Ixmltags.FTABLE;
import static metacoder.data.xml.Ixmltags.FULLNAME;
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
import static metacoder.data.xml.Ixmltags.TABLES;
import static metacoder.data.xml.Ixmltags.TABLESCHEMA;
import static metacoder.data.xml.Ixmltags.TABLETYPE;
import static metacoder.data.xml.Ixmltags.UNIFORMDATATYPE;
import static metacoder.data.xml.Ixmltags.UNIFORMSQLTYPE;
import static metacoder.data.xml.Ixmltags.UNIQUE;

import metacoder.util.SortedHashMap;

/**
 * Xmlreader
 * read database metadata
 * build DB object from XML
 * prepare metadata for code conversion
 * @author Franky Laseure
 */
public class Xmlreader {

    //xml
    private Document xmldoc;
    private Element root;

    //data structures
    private DB database;
    private HashMap<String, Table> tables = new HashMap<String, Table>();

    /**
     * constructor
     * read database xml data
     * analyse data
     * @param xmlfile full filename
     */
    public Xmlreader(String xmlfile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            SAXBuilder saxbuilder = new SAXBuilder();
            xmldoc = saxbuilder.build(new File(xmlfile));
            root = xmldoc.getRootElement();
            database = new DB(root.getChild(DB));
            for(Element entry: root.getChild(TABLES).getChildren()) {
                Table t = new Table(entry);
                tables.put(t.getname(), t);
            }
            renameforeignkeys();
            analysetablerelations();
            searchexternalforeignkeys();
            analysetabletype();
        }
        catch(JDOMException jde) {
        }
        catch(IOException ioe) {
        }		
    }

    public DB getDatabase() {
        return database;
    }

    public HashMap<String, Table> getTables() {
        return tables;
    }

    private void renameforeignkeys() {
        //INTERNAL foreign keys
        HashMap doublefks = new HashMap();
        int counter1;
        int counter2;
        String newname;
        Table.Foreignkey.Column fkcol;
        //find double references to the same table and give unique name
        //loop all tables
        for(Table table: getTables().values()) {
            counter1 = 0;
            doublefks.clear();
            //loop foreign keys in table, find doubles
            for(Table.Foreignkey fk: table.getForeignkeys().values()) {
                //not already found a double for this foreignkey
                if(!doublefks.containsKey(fk.getprimarykeytable())) {
                    counter2 = 0;
                    for(Table.Foreignkey fk2: table.getForeignkeys().values()) {
                        if(counter1!=counter2 && fk.getprimarykeytable().equals(fk2.getprimarykeytable())
                                || fk.getprimarykeytable().equals(table.getname())) {
                            doublefks.put(fk.getprimarykeytable(), 1);
                        }
                        counter2++;
                    }
                }
                counter1++;
            }
            //process doubles
            //rename doubles
            for(Table.Foreignkey fk: table.getForeignkeys().values()) {
                if(doublefks.containsKey(fk.getprimarykeytable())) {
                    newname = fk.getprimarykeytable();
                    Iterator<Table.Foreignkey.Column> Icolumns = fk.getSortedColumns().iterator();
                    while(Icolumns.hasNext()) {
                        fkcol = Icolumns.next();
                        newname += fkcol.getForeigncolumn();
                    }
                    fk.uniquename = newname;
                } else {
                    fk.uniquename = fk.getprimarykeytable();
                }
            }            
        }
    }
    
    /**
     * analyse foreign key usage
     * - is primary key used as a foreign key ?
     * - is that foreign key completely part of a primary key ?
     * - is a foreign key table used more then once in a table ?
     */
    private void analysetablerelations() {
        //foreign key usage
        for(Table table: getTables().values()){
            for(Table table2: getTables().values()) {
                //count usage in primary keys and outside primary keys
                Xmlreader.Table.Column column;
                for(Xmlreader.Table.Foreignkey fk: table2.getForeignkeys().values()) {
                    if(fk.getprimarykeytable().equals(table.getname())) {
                        //check if every field is part of primary key
                        boolean ispartofprimarykey = true;
                        for(Xmlreader.Table.Foreignkey.Column fkc: fk.getSortedColumns()) {
                            column = (Xmlreader.Table.Column)table2.getColumns().get(fkc.getforeigncolumn());
                            ispartofprimarykey = ispartofprimarykey && table2.ispartofprimarykey(column);
                        }
                        if(ispartofprimarykey) {
                            table.pkusagecount++; //counter: is used in
                            table2.pkforeigntables.put(fk.getPrimarykeytable(), table); //uses table in pk
                            fk.partofprimarykey = true;
                        }
                        else table.fkusagecount++;
                    }
                }
            }            
        }
    }

    /**
     * analyse external foreign keys
     * - detect relational tables
     * - rename foreign keys and relational keys when used double
     */
    private void searchexternalforeignkeys() {
        ExternalForeignkey externalforeignkey;
        RelationalForeignkey relationalforeignkey;
        for(Xmlreader.Table table: getTables().values()) {
            for(Xmlreader.Table ftable: getTables().values()) {
                if(table!=ftable) {
                    for(Xmlreader.Table.Foreignkey extfk: ftable.getForeignkeys().values()) {
                        if(extfk.getprimarykeytable().equals(table.getname())) {
                            externalforeignkey = table.addExternalForeignkey(extfk);
                            //check if table is relation table
                            if(extfk.isPartofprimarykey()) {
                                for(Xmlreader.Table.Foreignkey relfk: ftable.getForeignkeys().values()) {
                                    if(relfk.isPartofprimarykey() && !relfk.getprimarykeytable().equals(table.getname())) {
                                        relationalforeignkey = externalforeignkey.addRelationalForeignkey(relfk);
                                        table.relationalforeignkeys.add(relationalforeignkey);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for(Xmlreader.Table table: getTables().values()) {
            table.renameExternalForeignkeys();
            table.renameRelationalForeignkeys();
        }
    }
    
    //PK TYPE
    // x-y: x=aantal foreign keys in primary key, y=aantal vrije velden
    // x = table.fkcount
    // y = table.freefieldcount
    // 0-n: FREE          - enkel vrije velden
    // 1-0: EXTENTION     - 1 op 1 relatie met hoofdtabel: logische uitbreiding van hoofdtabel
    // 1-n: CHILD         - parent/child relatie, dit is een child tabel
    // n-0: RELATION      - pure relatietabel
    // n-n: CHILDRELATION - relatietabel / child tabel
    //
    //TABLE TYPE
    // p-f: pkusagecount - fkusagecount
    // pkusagecount > 0: gebruikt als parent tabel
    // fkusagecount > 0: gebruikt als codetabel
    //
    // 0-0: FREE: vrije tabel
    // 0-n: CODE: code tabel
    // n-0: PARENT: parent tabel
    // n-n: CODE: code tabel
    
    /**
     * analyse table usage
     * - free table
     * - extention table
     * - child table
     * - code table
     */
    public void analysetabletype() {
        for(Table table: getTables().values()) {
            //PK type
            if(table.fkcount==0) {
                table.pktype = table.FREE;
            } else if(table.fkcount==1) {
                if(table.freefieldcount==0) table.pktype = table.EXTENTION;
                else table.child = true;
            } else {
                table.relation = true;
                if(table.freefieldcount>0) table.child = true;
            }
            //Table type
            if(table.pkusagecount==0) {
                if(table.fkusagecount==0) {
                    table.tabletype = table.FREE;
                    table.parent = true;
                }
                else table.tabletype = table.CODE;
            } else {
                if(table.fkusagecount==0) table.parent = true;
                else table.tabletype = table.CODE;
            }
        }
    }
    
    /**
     * Table metadata for code generation
     */
    public class Table {

        private String name;
        private String externalname;
        private String type;
        private String schema;
        private String remarks;
        private SortedHashMap columns;
        private Primarykey primarykey;
        //Foreign key tables referred to in primary key
        private HashMap<String, Table> pkforeigntables = new HashMap<String, Table>();
        private HashMap<String, Foreignkey> foreignkeys = new HashMap<String, Foreignkey>();
        private HashMap<String, Index> indexes = new HashMap<String, Index>();
        //primary key analysis fields
        private int fkcount = 0; //number of foreign keys in primary key
        private int freefieldcount = 0; //number of fields not part of a foreign field in primary key
        //relational analysis
        private int pkusagecount = 0; //times used in a primary key in other tables
        private int fkusagecount = 0; //times used as foreign keys in other tables (in and outside pk)
        
        //external foreign keys
        private ArrayList<ExternalForeignkey> externalforeignkeys = new ArrayList<ExternalForeignkey>();
        //relationalkeys, each key is also bound inside a externalforeignkey
        //they are listed here for easy access
        private ArrayList<RelationalForeignkey> relationalforeignkeys = new ArrayList<RelationalForeignkey>();

        private byte pktype = OTHER;    //primary key type
        private byte tabletype = OTHER; //tabel type
        private boolean parent = false;
        private boolean child = false;
        private boolean relation = false;
        
        public static final byte FREE = 0;
        public static final byte EXTENTION = 1;
        public static final byte CODE = 2;
        public static final byte OTHER = 99;
    //TABLE TYPE
    // x-y: x=aantal foreign keys in primary key, y=aantal vrije velden
    // x = table.fkcount
    // y = table.freefieldcount
    //                      pktype
    // 0-n: FREE          - enkel vrije velden
    // 1-0: EXTENTION     - 1 op 1 relatie met hoofdtabel: logische uitbreiding van hoofdtabel
    // 1-n: CHILD         - parent/child relatie, dit is een child tabel
    // n-0: RELATION      - pure relatietabel
    // n-n: CHILDRELATION - relatietabel / child tabel
    //
    // p-f: pkusagecount - fkusagecount
    // pkusagecount > 0: gebruikt als parent tabel
    // fkusagecount > 0: gebruikt als codetabel
    //      tabletype
    // 0-0: FREE: vrije tabel
    // 0-n: CODE: code tabel
    // n-0: PARENT: parent tabel
    // n-n: CODE: code tabel
        

        /**
         * constructor
         * create Table metadata from XML element
         * @param table Table element
         */
        private Table(Element table) {
            name = table.getName();
            type = table.getChildText(TABLETYPE);
            schema = table.getChildText(TABLESCHEMA);
            remarks = table.getChildText(TABLEREMARKS);
            List entries = table.getChild(COLUMNS).getChildren();
            columns = new SortedHashMap(entries.size());
            for(int c=0; c<entries.size(); c++) {
                Column tc = new Column((Element)entries.get(c));
                //columns.put(tc.getName(), tc, tc.getOrdinalposition() - 1);
                columns.put(tc.getname(), tc, c);
            }
            if(type.equals("TABLE")) {
                primarykey = new Primarykey(table.getChild(PRIMARYKEY));
                //only used to check if foreign key is also taken as index
                HashMap foreignkeynames = new HashMap();
                for(Element entry: table.getChild(FOREIGNKEYS).getChildren()) {
                    Foreignkey fk = new Foreignkey(entry);
                    foreignkeys.put(fk.getPkname() + fk.getinternalname(), fk);
                    foreignkeynames.put(fk.getFkname(), fk.getFkname());
                }
                for(Element entry: table.getChild(INDEXES).getChildren()) {
                    Index index = new Index(entry);
                    //check if this index is a foreign key
                    if(!foreignkeynames.containsKey(index.getname()))
                        indexes.put(index.getname(), index);
                }
                analyseprimarykey();
                analyseforeignkeys();
            }
        }

        /**
         * @return is table
         */
        public boolean isTable() {
            return type.equals(IDBconnection.TYPE_TABLE);
        }
        
        /**
         * @return is view
         */
        public boolean isView() {
            return type.equals(IDBconnection.TYPE_VIEW);
        }

        /**
         * add external foreign key to table
         * @param fk Foreignkey
         * @return ExternalForeignkey
         */
        private ExternalForeignkey addExternalForeignkey(Foreignkey fk) {
            ExternalForeignkey efk = new ExternalForeignkey(fk);
            externalforeignkeys.add(efk);
            return efk;
        }
        
        /**
         * @return table name (Name format)
         */
        public String getName() {
            return Xmltags.Ulow(name);
        }

        /**
         * @return table name (name format)
         */
        public String getname() {
            return Xmltags.Low(name);
        }

        /**
         * @return table name as defined in database
         */
        public String getOriginalname() {
            return name;
        }
        
        /**
         * @return table type (table / view)
         */
        public String getType() {
            return type;
        }

        /**
         * @return table schema
         */
        public String getSchema() {
            return schema;
        }

        /**
         * @return remarks
         */
        public String getRemarks() {
            return remarks;
        }

        /**
         * @return columns as SortedHashMap
         */
        public SortedHashMap getColumns() {
            return columns;
        }

        /**
         * @return sorted columns
         */
        public ArrayList<Table.Column> getSortedColumns() {
            return columns.getSortedArrayList();
        }

        /**
         * @return Primarykey
         */
        public Primarykey getPrimarykey() {
            return primarykey;
        }
        
        /**
         * @return Primarykeytype
         */
        public byte getPrimarykeyType() {
            return this.pktype;
        }
        
        /**
         * @return table type
         */
        public byte getTableType() {
            return this.tabletype;
        }
        
        /**
         * @return is table parent
         */
        public boolean isParent() {
            return parent;
        }

        /**
         * @return is table child
         */
        public boolean isChild() {
            return child;
        }
        
        /**
         * @return is a relation table ?
         */
        public boolean isRelation() {
            return relation;
        }
        
        /**
         * @return number of foreign keys in primary key
         */
        public int getPKfkcount() {
            return this.fkcount;
        }

        /**
         * @return table type
         */
        public byte getTabletype() {
            return this.tabletype;
        }
        
        /**
         * @return Foreign keys part of primary key
         */
        public HashMap getPKforeigntables() {
            return this.pkforeigntables;
        }

        /**
         * @return tables with no foreign keys (indirectly) linked to this table
         */
        public HashMap getTopparenttables() {
            HashMap topparents = new HashMap();
            if(this.isChild()) {
                Xmlreader.Table.Foreignkey fk;
                for(Xmlreader.Table fktable: (ArrayList<Xmlreader.Table>)getPKforeigntables().values()) {
                    HashMap parents = new HashMap();
                    if(fktable.isChild()) {
                        topparents.putAll(fktable.getTopparenttables());
                    } else if(fktable.isParent()) {
                        topparents.put(fktable.getname(), fktable);
                    }
                }
            }
            return topparents;
        }

        /**
         * @return foreign keys
         */
        public HashMap<String, Foreignkey> getForeignkeys() {
            return foreignkeys;
        }

        /**
         * @return all external foreign keys and relational keys
         */
        public ArrayList<ExternalForeignkey> getAllRelationalkeys() {
            ArrayList<ExternalForeignkey> all = new ArrayList<ExternalForeignkey>();
            all.addAll(externalforeignkeys);
            all.addAll(relationalforeignkeys);
            return all;
        }

        /**
         * @return external foreign keys
         */
        public ArrayList<ExternalForeignkey> getExternalforeignkeys() {
            return externalforeignkeys;
        }
        
        /**
         * @return relational foreign keys
         */
        public ArrayList<RelationalForeignkey> getRelationalforeignkeys() {
            return relationalforeignkeys;
        }
        
        /**
         * @return table indexes
         */
        public HashMap getIndexes() {
            return indexes;
        }

        /**
         * @param column: Column
         * @return is column part of primary key
         */
        public boolean ispartofprimarykey(Column column) {
            if(primarykey!=null)
                return primarykey.columns.containsKey(column.getname());
            else
                return false;
        }

        
        /**
         * check if column is part of a foreign key
         * @param column: table column
         * @return true/false
         */
        public boolean ispartofforeignkey(Column column) {
            boolean ispart = false;
            if(!this.getForeignkeys().isEmpty()) {
                for(Foreignkey foreignkey: getForeignkeys().values()) {
                    ispart = ispart || foreignkey.getColumns().containsKey(column.getname());
                }
            }
            return ispart;
        }

        /**
         * check if column is part of a foreign key,
         * and if that foreign key is completely part of the primary key
         * @param column: table column
         * @return true/false
         */
        public boolean ispartofpkforeignkey(Column column) {
            boolean ispart = false;
            if(!this.getForeignkeys().isEmpty()) {
                for(Foreignkey foreignkey: getForeignkeys().values()) {
                    ispart = ispart || 
                            foreignkey.partofprimarykey && foreignkey.getColumns().containsKey(column.getname());
                }
            }
            return ispart;
        }

        /**
         * find (first) foreign key with column
         * @param column Column
         * @return Foreignkey if found
         */
        public Foreignkey getForeignkey(Column column) {
            boolean ispart = false;
            Iterator<Foreignkey> Iforeignkey = this.getForeignkeys().values().iterator();
            Foreignkey foreignkey = null;
            while(Iforeignkey.hasNext() && !ispart) {
                foreignkey = Iforeignkey.next();
                ispart = foreignkey.getColumns().containsKey(column.getname());
            }
            if(!ispart) foreignkey = null;
            return foreignkey;
        }

        /**
         * @param column Column
         * @return is column part of primary of foreign key
         */
        public boolean ispartofanykey(Column column) {
            return ispartofprimarykey(column) || ispartofforeignkey(column);
        }
        
        /**
         * count primary key fields not part of any key
         * count foreign keys in primary key
         */
        private void analyseprimarykey() {
            //Count "free fields", fields not part of a primary key
            for(Xmlreader.Table.Primarykey.Column pkc: getPrimarykey().getSordedColumns()) {
                Xmlreader.Table.Column column = (Xmlreader.Table.Column)this.getColumns().get(pkc.getname());
                if(!this.ispartofforeignkey(column)) {
                    freefieldcount++;
                }
            }
            //count foreign keys
            Xmlreader.Table.Column column;
            for(Xmlreader.Table.Foreignkey fk: getForeignkeys().values()) {
                Xmlreader.Table.Foreignkey.Column fkc = (Xmlreader.Table.Foreignkey.Column)fk.getSortedColumns().get(0);
                column = (Xmlreader.Table.Column)this.getColumns().get(fkc.getforeigncolumn());
                if(this.ispartofprimarykey(column)) fkcount++;
            }
        }

        /**
         * search if a table is referenced double
         * used for giving unique names to foreign keys
         */
        private void analyseforeignkeys() {
            int fkcounter = 0;
            for(Xmlreader.Table.Foreignkey fk: getForeignkeys().values()) {
                byte counter = 1;
                int fkcounter2 = 0;
                if(fk.getPrimarykeytable().equals(this.getName())) {
                    fk.pksequencenr = counter++;
                    fk.doubleuse = true;
                } else {
                    for(Xmlreader.Table.Foreignkey fk2: getForeignkeys().values()) {
                        if(fkcounter2!=fkcounter) {
                            //don't compare with itself
                            if(!fk2.doubleuse && fk.getprimarykeytable().equals(fk2.getprimarykeytable())) {
                                if(!fk.doubleuse) {
                                    fk.pksequencenr = counter++;
                                    fk.doubleuse = true;
                                }
                            }
                        }
                        fkcounter2++;
                    }
                }
                fkcounter++;
            }
        }

        /**
         * rename double used tables in foreign keys
         */
        public void renameExternalForeignkeys() {
            HashMap doubles = new HashMap();
            HashMap doublecount = new HashMap();
            //count
            int amount;
            String fktablename;
            for(ExternalForeignkey efk:externalforeignkeys) {
                amount = 1;
                fktablename = efk.getFk().getforeignkeytable();
                if(doubles.containsKey(fktablename)) {
                    amount += (Integer)doubles.get(fktablename);
                } else {
                    doublecount.put(fktablename, 1);
                }
                doubles.put(fktablename, amount);
            }
            //process doubles
            String newname;
            for(ExternalForeignkey efk:externalforeignkeys) {
                fktablename = efk.getFk().getforeignkeytable();
                amount = (Integer)doubles.get(fktablename);
                if(amount>1) {
                    newname = fktablename;
                    for(Table.Foreignkey.Column fkcol: efk.getFk().getSortedColumns()) {
                        newname += fkcol.getForeigncolumn();
                    }
                    efk.setUniquename(newname);
                }
            }
        }

        /**
         * rename double used tables in relational keys
         */
        public void renameRelationalForeignkeys() {
            HashMap doubles = new HashMap();
            HashMap doublecount = new HashMap();
            HashMap namecount = new HashMap();
            //count
            int amount;
            String pktablename;
            for(RelationalForeignkey rfk: relationalforeignkeys) {
                amount = 1;
                pktablename = rfk.getFk().getprimarykeytable();
                if(doubles.containsKey(pktablename)) {
                    amount += (Integer)doubles.get(pktablename);
                } else {
                    doublecount.put(pktablename, 1);
                    namecount.put(pktablename, 0);
                }
                doubles.put(pktablename, amount);
            }
            //process doubles
            String newname;
            Table.Foreignkey.Column fkcol;
            int sequencenr;
            for(RelationalForeignkey rfk: relationalforeignkeys) {
                pktablename = rfk.getFk().getprimarykeytable();
                amount = (Integer)doubles.get(pktablename);
                if(amount>1) {
                    sequencenr = (Integer)namecount.get(pktablename)+1;
                    namecount.put(pktablename, sequencenr);
                    newname = pktablename + sequencenr;
                    rfk.setUniquename(newname);
                }
            }
        }

        /**
         * Column metadata for code generation
         */
        public class Column {

            private String name;
            private int datatype;
            private int uniformdatatype;
            private int size;
            private int decimaldigits;
            private int numprecradix;
            private boolean nullable;
            private String def;
            private int ordinalposition;
            private String fullname;
            private String javatype;
            private String remarks;
            private String schema;
            private int sqldatatype;
            private String sqldatetimesub;
            private String datatypename;
            private int uniformsqltype;
            private boolean isfileondisk;

            /**
             * constructor
             * create column metadata from column Element
             * @param column Column
             */
            private Column(Element column) {
                name = column.getChildText(COLUMNNAME);
                datatype = Integer.valueOf(column.getChildText(DATATYPE));
                uniformdatatype = Integer.valueOf(column.getChildText(UNIFORMDATATYPE));
                size = Integer.valueOf(column.getChildText(SIZE));
                decimaldigits = Integer.valueOf(column.getChildText(DECIMALDIGITS));
                numprecradix = Integer.valueOf(column.getChildText(NUMERICPRECISIONRADIX));
                nullable = Integer.valueOf(column.getChildText(NULLABLE)) == 1;
                def = column.getChildText(DEFAULTVALUE);
                ordinalposition = Integer.valueOf(column.getChildText(SEQUENCE));
                fullname = column.getChildText(FULLNAME);
                javatype = column.getChildText(JAVATYPE);
                remarks = column.getChildText(REMARKS);
                schema = column.getChildText(SCHEMA);
                sqldatatype = Integer.valueOf(column.getChildText(SQLDATATYPE));
                sqldatetimesub = column.getChildText(SQLDATETIMESUB);
                datatypename = column.getChildText(DATATYPENAME);
                uniformsqltype = Integer.valueOf(column.getChildText(UNIFORMSQLTYPE));
                try {
                    isfileondisk = Integer.valueOf(column.getChildText(FILEONDISK)) == 1;
                }
                catch(NumberFormatException e) {
                    isfileondisk = false;
                }
            }

            public String getname() {
                return Xmltags.Low(name);
            }

            public String getName() {
                return Xmltags.Ulow(name);
            }

            public String getNAME() {
                return name.toUpperCase();
            }

            public String getOriginalname() {
                return name;
            }
            
            public String getvarname() {
                String varname = Xmltags.Low(name);
                if(name.equals("public")) varname += "f_";
                return varname;
            }

            public int getDatatype() {
                return datatype;
            }

            public int getUniformdatatype() {
                return uniformdatatype;
            }
            
            public int getSize() {
                return size;
            }

            public int getDecimaldigits() {
                return decimaldigits;
            }

            public int getNumberprecitionRadix() {
                return numprecradix;
            }

            public boolean getIsnullable() {
                return nullable;
            }

            public String getDefaultvalue() {
                return def;
            }

            public int getOrdinalposition() {
                return ordinalposition;
            }

            public String getFullname() {
                return fullname;
            }

            public String getRemarks() {
                return remarks;
            }

            public String getSchema() {
                return schema;
            }

            public int getSqldatatype() {
                return sqldatatype;
            }

            public String getSqldatetimesub() {
                return sqldatetimesub;
            }

            public String getDatatypename() {
                return datatypename;
            }

            public int getUniformsqltype() {
                return uniformsqltype;
            }

            public boolean isCustomVariable(Programlanguage planguage) {
                return planguage.getObjectcasting(javatype, datatypename)!=null;
            }
            
            public String getDBVariabletype(Programlanguage planguage) {
                return planguage.getDatabasedriverdatatype(this.javatype, this.datatypename);
            }

            public String getCustomVariabletype(Programlanguage planguage) {
                return planguage.getCustomdatatype(this.javatype, this.datatypename);
            }

            public String getVariabletype(Programlanguage planguage) {
                return planguage.getVariabletype(this.javatype, this.datatypename);
            }

            public String getVariabletypesql(Programlanguage planguage) {
                return planguage.getVariabletypesql(this.javatype, this.datatypename);
            }

            public String getDBfunction(Programlanguage planguage) {
                return planguage.getFunction(this.javatype);
            }
            
            public boolean getIsfileondisk() {
                return isfileondisk;
            }
        }
        
        /**
         * Primary key metadata for code generation
         */
        public class Primarykey {

            private String name;
            private SortedHashMap columns;

            /**
             * constructor
             * create Primarykey metadata from primarykey Element
             * @param primarykey XML Element
             */
            private Primarykey(Element primarykey) {
                if(primarykey!=null) {
                    name = primarykey.getChildText(NAME);
                    List<Element> entries = primarykey.getChildren();
                    //first entry is name tag, all else are columns
                    entries.remove(0);
                    columns = new SortedHashMap(entries.size());
                    for(Element entry: entries) {
                        Column tc = new Column(entry);
                        columns.put(tc.getname(), tc, tc.getSequence()-1);
                    }		    
                }
            }

            public String getname() {
                return Xmltags.Low(name);
            }

            public String getName() {
                return Xmltags.Ulow(name);
            }

            public String getOriginalname() {
                return name;
            }
            
            public HashMap getColumns() {
                return columns;
            }

            public ArrayList<Table.Primarykey.Column> getSordedColumns() {
                return columns.getSortedArrayList();
            }

            public class Column {

                private String name;
                private int sequence;

                private Column(Element column) {
                    name = column.getChildText(COLUMNNAME);
                    sequence = Integer.valueOf(column.getChildText(SEQUENCE));
                }

                public String getname() {
                    return Xmltags.Low(name);
                }

                public String getName() {
                    return Xmltags.Ulow(name);
                }

                public String getNAME() {
                    return name.toUpperCase();
                }

                public String getOrignalname() {
                    return name;
                }
                
                public int getSequence() {
                    return sequence;
                }
            }
        }

        /**
         * Foreign key metadata for code generation
         */
        public class Foreignkey {
            private String sequentialname;
            private String uniquename;
            private String pkeyschema;
            private String pkeytable;
            private String fkeyschema;
            private String fkeytable;
            private String pkname;
            private String fkname;
            private String fkjavaname;
            private SortedHashMap columns;
            private boolean partofprimarykey = false;
            private boolean doubleuse = false;
            private byte pksequencenr = 0;

            /**
             * constructor
             * create foreign key metadata from foreignkey Element
             * @param foreignkey XML Element
             */
            public Foreignkey(Element foreignkey) {
                sequentialname = foreignkey.getName();
                int entrycount = 0;
                pkeyschema = foreignkey.getChildText(PRIMARYKEYSCHEMA);
                entrycount++;
                pkeytable = foreignkey.getChildText(PRIMARYKEYTABLE);
                entrycount++;
                fkeyschema = foreignkey.getChildText(FOREIGNKEYSCHEMA);
                entrycount++;
                fkeytable = foreignkey.getChildText(FOREIGNKEYTABLE);
                entrycount++;
                pkname = foreignkey.getChildText(PKNAME);
                entrycount++;
                fkname = foreignkey.getChildText(FKNAME);
                entrycount++;
                fkjavaname = foreignkey.getChildText(FKJAVANAME);
                //older version had no fkjavaname in xml
                if(fkjavaname!=null) entrycount++;
                List entries = foreignkey.getChildren();
                columns = new SortedHashMap(foreignkey.getChildren().size()-entrycount);
                for(int c=entrycount; c<entries.size(); c++) {
                    Column tc = new Column((Element)entries.get(c));
                    columns.put(tc.getforeigncolumn(), tc, tc.getSequence()-1);
                }		    
            }

            public String getuniquename() {
                return uniquename;
            }

            public String getUniquename() {
                return Xmltags.Ulow(uniquename);
            }
            
            public String getForeignKeySchema() {
                return fkeyschema;
            }

            public String getforeignkeytable() {
                return Xmltags.Low(fkeytable);
            }

            public String getForeignkeytable() {
                return Xmltags.Ulow(fkeytable);
            }

            public String getOriginalforeignkeytable() {
                return fkeytable;
            }

            public String getPrimaryKeySchema() {
                return pkeyschema;
            }

            public String getprimarykeytable() {
                return Xmltags.Low(pkeytable);
            }

            public Table getPrimarykeyTable() {
                return tables.get(this.getprimarykeytable());
            }
            
            public String getOriginalprimarykeytable() {
                return pkeytable;
            }
            
            public Table getForeignkeyTable() {
                return tables.get(this.getforeignkeytable());
            }
            
            public String getPrimarykeytable() {
                return Xmltags.Ulow(pkeytable);
            }

            public String getPkname() {
                return pkname;
            }

            public String getFkname() {
                return fkname;
            }

            public boolean isPartofprimarykey() {
                return partofprimarykey;
            }

            public boolean hasDoubleuse() {
                return doubleuse;
            }
            
            public byte getPKsequencenumber() {
                return this.pksequencenr;
            }
            
            public String getpkjavaname() {
                if(doubleuse) {
                    return this.getprimarykeytable() + this.pksequencenr;
                } else {
                    return this.getprimarykeytable();
                }
            }
            
            public String getPkjavaname() {
                if(doubleuse) {
                    return Xmltags.Ulow(this.getprimarykeytable()) + this.pksequencenr;
                } else {
                    return Xmltags.Ulow(this.getprimarykeytable());
                }
            }

            public String getpkjavanamePK() {
                if(doubleuse) {
                    return this.getprimarykeytable() + "PK" + this.pksequencenr;
                } else {
                    return this.getprimarykeytable() + "PK";
                }
            }
            
            public String getPkjavanamePK() {
                if(doubleuse) {
                    return Xmltags.Ulow(this.getprimarykeytable()) + "PK" + this.pksequencenr;
                } else {
                    return Xmltags.Ulow(this.getprimarykeytable()) + "PK";
                }
            }

            public String getfkjavaname() {
                return this.fkjavaname;
            }

            public String getfkJavaname() {
                return Xmltags.Ulow(this.fkjavaname);
            }

            public String getjavaname() {
                String jname = Xmltags.Low(this.fkeytable) + Xmltags.Ulow(this.pkeytable);
                if(doubleuse) jname += this.pksequencenr;
                //int fknumber = Integer.valueOf(javaname.substring(10, javaname.length()));
                //String jname = Xmltags.Low(this.fkeytable) + ULow(this.pkeytable);
                //if(fknumber>0) jname += fknumber;
                return jname;
            }
            
            public String getjnamePK() {
                if(doubleuse) {
                    return this.getprimarykeytable() + this.pksequencenr;
                } else {
                    return this.getprimarykeytable();
                }
            }

            public String getjCONSTANTNAMEPK() {
                if(doubleuse) {
                    return this.getprimarykeytable().toUpperCase() + this.pksequencenr;
                } else {
                    return this.getprimarykeytable().toUpperCase();
                }
            }

            private String getinternalname() {
                int fknumber = Integer.valueOf(sequentialname.substring(10, sequentialname.length()));
                String jname = Xmltags.Low(this.fkeytable) + Xmltags.Ulow(this.pkeytable);
                if(fknumber>0) jname += fknumber;
                return jname;
            }

            public SortedHashMap getColumns() {
                return columns;
            }

            public ArrayList<Table.Foreignkey.Column> getSortedColumns() {
                return columns.getSortedArrayList();
            }

            public boolean compare(Foreignkey fk) {
                Xmlreader.Table.Foreignkey.Column pkfkc1;
                Xmlreader.Table.Foreignkey.Column pkfkc2;
                pkfkc1 = (Xmlreader.Table.Foreignkey.Column)this.getSortedColumns().get(0);
                pkfkc2 = (Xmlreader.Table.Foreignkey.Column)fk.getSortedColumns().get(0);
                return pkfkc1.getforeigncolumn().equals(pkfkc2.getforeigncolumn());
            }

            /**
             * Foreign key column metadata for code generation
             */
            public class Column {

                private String ptable;
                private String pcolumn;
                private String ftable;
                private String fcolumn;
                private int sequence;
                private boolean partofprimarykey;

                /**
                 * constructor
                 * create column metadata from column Element
                 * @param column XML Element
                 */
                private Column(Element column) {
                    ptable = column.getChildText(PTABLE);
                    pcolumn = column.getChildText(PCOLUMNNAME);
                    ftable = column.getChildText(FTABLE);
                    fcolumn = column.getChildText(FCOLUMNNAME);
                    sequence = Integer.valueOf(column.getChildText(SEQUENCE));
                    partofprimarykey = Integer.valueOf(column.getChildText(PARTOFPRIMARYKEY))==1;
                }

                public String getPrimaryTable() {
                    return ptable;
                }

                public String getprimarycolumn() {
                    return Xmltags.Low(pcolumn);
                }

                public String getPrimarycolumn() {
                    return Xmltags.Ulow(pcolumn);
                }

                public String getOriginalprimarycolumn() {
                    return pcolumn;
                }

                public String getForeignTable() {
                    return ftable;
                }

                public String getforeigncolumn() {
                    return Xmltags.Low(fcolumn);
                }

                public String getForeigncolumn() {
                    return Xmltags.Ulow(fcolumn);
                }

                public String getOriginalforeigncolumn() {
                    return fcolumn;
                }
                
                public int getSequence() {
                    return sequence;
                }

                public boolean getPartofprimarykey() {
                    return partofprimarykey;
                }
            }
        }

        /**
         * External Foreign key metadata for code generation
         */
        public class ExternalForeignkey {
            
            protected Foreignkey fk;
            private boolean partofpk;
            protected String uniquename;
            private ArrayList<RelationalForeignkey> relationalforeignkeys = new ArrayList<RelationalForeignkey>();

            /**
             * constructor
             * create external foreign key metadata from Foreignkey
             * @param fk Foreign key used as external foreign key
             */
            public ExternalForeignkey(Foreignkey fk) {
                this.fk = fk;
                this.partofpk = fk.isPartofprimarykey();
                this.uniquename = fk.getforeignkeytable();
            }
            
            public Foreignkey getFk() {
                return fk;
            }

            public void setFk(Foreignkey fk) {
                this.fk = fk;
            }

            public boolean isPartofpk() {
                return partofpk;
            }
            
            public String getuniquename() {
                return uniquename;
            }

            public String getUniquename() {
                return Xmltags.Ulow(uniquename);
            }

            public void setUniquename(String uniquename) {
                this.uniquename = uniquename;
            }
            
            public RelationalForeignkey addRelationalForeignkey(Foreignkey relfk) {
                RelationalForeignkey relationalfk = new RelationalForeignkey(relfk);
                relationalforeignkeys.add(relationalfk);
                return relationalfk;
            }
            
            public ArrayList<RelationalForeignkey> getRelationalforeignkeys() {
                return relationalforeignkeys;
            }
            
        }

        /**
         * Relational foreign key metadata for code generation
         */
        public class RelationalForeignkey extends ExternalForeignkey {

            /**
             * constructor
             * create relational foreign key metadata from Foreignkey
             * @param relfk Foreign key used in relational key
             */
            public RelationalForeignkey(Foreignkey relfk) {
                super(relfk);
                this.uniquename = relfk.getprimarykeytable();
            }
        }

        /**
         * Index metadata for code generation
         */
        public class Index {

            private String name;
            private boolean unique;
            private HashMap columns = new HashMap();

            /**
             * constructor
             * create index metadata from index Element
             * @param index 
             */
            private Index(Element index) {
                name = index.getChildText(INDEXNAME);
                unique = index.getChildText(UNIQUE).equals("true");
                for(Element entry: index.getChild(COLUMNS).getChildren()) {
                    Column ic = new Column(entry);
                    columns.put(ic.getname(), ic);
                }		    
            }

            public String getname() {
                return Xmltags.Low(name);
            }

            public String getName() {
                return Xmltags.Ulow(name);
            }

            public boolean getIsunique() {
                return unique;
            }

            public HashMap getColumns() {
                return columns;
            }

            /**
             * index column metadata for code generation
             */
            public class Column {

                private String name;
                private int sequence;

                /**
                 * constructor
                 * create index column metadata from index column Element
                 * @param column 
                 */
                private Column(Element column) {
                    name = column.getChildText(COLUMNNAME);
                    sequence = Integer.valueOf(column.getChildText(SEQUENCE));
                }

                public String getname() {
                    return Xmltags.Low(name);
                }

                public String getName() {
                    return Xmltags.Ulow(name);
                }

                public int getSequence() {
                    return sequence;
                }
            }
        }
    }	
}
