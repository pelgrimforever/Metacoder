/*
 * Renaming variables
 * project: database name = project name
 * table: table name
 * pk: primary key
 * column: column name
 * columntype: java type
 * columnjavaname: column name, altered as suitable java variable name
 * 
 * repeat*****: repeat for all *****, has start and end REPEATALLFIELDS
 * ******list: list all *** in format: name1, name2, ..., namen
 * 
 * Renaming conventions for directories and tables
 * :project: project name
 * :Project: project name with 1st capital sign
 * :table: table name, repeat for each table
 * :Table: table name with 1st capital sign, repeat for each table
 * 
 * Renaming conventions for java file metacode
 * :project: project name
 * :Project: project 1st capital sign
 * :table: table name
 * :Table: table with 1st capital sign
 * :column: column name
 * :columnjavaname: column name, renamed for java language (in case of doubles or reserved words)
 * :Column: Column with capital sign
 * :COLUMN: COLUMN with all capital sign
 * :columntype: column java type
 * :columnsize: column size
 * :columnposition: ordinal position
 * 
 * **keys**
 * :repeatforeignkeys: repeat block for all foreign keys in table
 *     :inpk: block only for fields in primary key
 *     :notpk: block only for fields not in primary key
 *     if :inpk: and :notpk: are not present, all fields are processed
 * 
 *     :pktable: primarykey tablename from foreign key
 *     :Pktable: pktable with 1st capital sign
 *     :pktablejavaname: primarykey tablename from foreign key, renamed for java language (in case of double usage)
 *     :Pktablejavaname: pktablejavaname with 1st capital sign
 *     :repeatforeignkeyfields: repeat block for all fields in the foreign key
 *         :primarycolumn: primary key field name that is refered to in foreign key
 *         :Primarycolumn: primarycolumn with capital sign
 *         :foreigncolumn: field name in foreign key
 *         :Foreigncolumn: field name in foreign key
 * 
 * **fields**
 * :repeatpkfields: repeat block for primary key column field list
 *     :infk: block only for fields in a foreign key
 *     :notfk: block only for fields not in a foreign key
 *     if :infk: and :notfk: are not present, all fields are processed
 * 
 * :repeatfields: repeat block for all fields not part of any key (pk, fk)
 * :repeatallfields: repeat block for all fields
 * 
 * :,: end each repeating block with ", " except the last block, only valid at end of repeatblock
 * 
 * Custom metacode block:
 * //Custom metacode, do not change this line
 * All metacode between these tags will be copied from the existing project sources
 * and put into the new generated metacode
 */
package metacoder.codegeneration;

import base.AppController;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Databaseproject;
import metacoder.data.Programlanguage;
import metacoder.data.Sourceproject;
import metacoder.data.xml.Xmlreader;
import metacoder.data.xml.Xmlreader.Table;
import metacoder.util.Fileutilities;
import static metacoder.util.Fileutilities.getCurrentDir;

/**
 * Generate metacode and export this to projects
 3 objects must be set in order to function
 databaseproject: active database with linked projects
 planguage: active program language
 dbproperties: active database metadata
 * @author Franky Laseure
 */
public class Export2Projects {
    
    public static final String PROJECTSOURCEROOT = "sourceroot"; //root of the metacode source metacode -> to be placed in project source root
    public static final String PROJECTROOT = "projectroot"; //project root files, -> to be placed in the project root map

    HashMap<String, Programlanguage> programlanguages = new HashMap<>();
    //next objects need to be set before the convertor can be used
    private Databaseproject databaseproject;
    private Xmlreader dbproperties;

    //current metacode file program language
    private Programlanguage planguage;
        
    public final static String tags[] = {
        Metatags.REPEATALLFIELDS, Metatags.REPEATPRIMARYKEYFIEDLS, Metatags.REPEATFOREIGNKEYFIELDS, 
        Metatags.REPEATFOREIGNKEYS, Metatags.REPEATFIELDS
    };
    
    /**
     * Export to Projects class
     */
    public Export2Projects() {
        loadLanguages();
    }
    
    /**
     * load program language list
     */
    private void loadLanguages() {
        File metacodedir = new File(getCurrentDir() + Metacodeprocessor.METACODEDIRECTORY);
        File[] languages = metacodedir.listFiles();
        Programlanguage programlang;
        for(File language: languages) {
            if(language.isFile() && language.getName().endsWith(".xml")) {
                programlang = new Programlanguage(language);
                programlanguages.put(programlang.getName(), programlang);
            }
        }
    }
    
    public void setDatabaseproject(Databaseproject databaseproject) {
        this.databaseproject = databaseproject;
    }
    
    public void setDbproperties(Xmlreader dbproperties) {
        this.dbproperties = dbproperties;
    }
    
    private void setLanguage(String languagename) {
        this.planguage = this.programlanguages.get(languagename);
        Metatagsprocessor.setProgramlanguage(planguage);
    }
    
    public void processmetacode(Sourceproject sourceproject, String metacodename) {
        try {
            String metacodedir = Fileutilities.getCurrentDir() + Metacodeprocessor.METACODEDIRECTORY + File.separator + metacodename;
            String metacodesourceroot = metacodedir + File.separator + PROJECTSOURCEROOT;
            String metacodeprojectroot = metacodedir + File.separator + PROJECTROOT;
            String projectroot = sourceproject.getProjectdirectory();
            projectroot = projectroot.substring(0, projectroot.lastIndexOf(sourceproject.getProjectname())+sourceproject.getProjectname().length());
            File sourcemetacode = new File(metacodesourceroot);
            //duplicate directory structure from metacode source map to project source map
            Export2Projects.this.processmetacodemapstructure(sourcemetacode, sourceproject.getProjectdirectory());
            copydirectory(metacodeprojectroot, projectroot);
        }
        catch(Exception e) {
            AppController.showMessage(e.getMessage());
        }
    }

    /**
     * recursive loop through all submaps
     * @param metacodedir: metacode (sub) map
     * @param projectdir: project (sub) map
     */
    private void processmetacodemapstructure(File metacodedir, String projectdir) {
        processmetacodemapstructure(metacodedir, projectdir, null);
    }
    
    /**
     * recursive lop through all submaps
     * @param metacodedir: metacode (sub) map
     * @param projectdir: project (sub) map
     * @param tablecontraint 
     */
    private void processmetacodemapstructure(File metacodedir, String projectdir, Table tablecontraint) {
        File[] metacodecontent = metacodedir.listFiles();
        String itemname;
        StringBuilder newname;
        for (File metacodeitem : metacodecontent) {
            itemname = metacodeitem.getName();
            newname = new StringBuilder(Metafilenaming.convert2Metatags(itemname));
            Metatagsprocessor.processProjectname(newname, this.databaseproject);
            if(metacodeitem.isDirectory() && !metacodeitem.getName().contains(".svn")) {
                if(Metafilenaming.has_metatag_table_view(itemname)) {
                    //loop tables
                    Iterator<Table> tablesI;
                    if(tablecontraint==null) {
                        tablesI = dbproperties.getTables().values().iterator();                    
                    } else {
                        ArrayList<Table> singletablearray = new ArrayList<>();
                        singletablearray.add(tablecontraint);
                        tablesI = singletablearray.iterator();
                    }
                    Table table;
                    StringBuilder tablenewname;
                    while(tablesI.hasNext()) {
                        table = tablesI.next();
                        if(table.isTable() && Metafilenaming.has_metatag_table(itemname)
                                || table.isView() && Metafilenaming.has_metatag_view(itemname)) {
                            tablenewname = new StringBuilder(newname.toString());
                            Metatagsprocessor.processTablename(tablenewname, table);
                            String subdirname = new StringBuilder(projectdir).append(File.separator).append(tablenewname.toString()).toString();
                            File subdir = new File(subdirname);
                            if(!subdir.exists()) subdir.mkdir();
                            processmetacodemapstructure(metacodeitem, subdir.getAbsolutePath(), table);
                        }
                    }                    
                } else {
                    File subdir = new File(projectdir + File.separator + newname);
                    if(!subdir.exists()) subdir.mkdir();
                    Export2Projects.this.processmetacodemapstructure(metacodeitem, subdir.getAbsolutePath());
                }
            } else if(metacodeitem.isFile() 
                    && !metacodeitem.getName().endsWith("~")
                    && !metacodeitem.getName().contains(".svn")) {
                String extention = metacodeitem.getName().substring(metacodeitem.getName().lastIndexOf(".")+1);
                setLanguage(extention);
                Metacodeprocessor metacodeprocessor = new Metacodeprocessor(metacodeitem.getAbsolutePath(), projectdir, this.dbproperties, this.planguage);
                if(Metafilenaming.has_metatag_table_view(itemname)) {
                    //loop tables
                    Iterator<Table> tablesI;
                    if(tablecontraint==null) {
                        tablesI = dbproperties.getTables().values().iterator();                    
                    } else {
                        ArrayList<Table> singletablearray = new ArrayList<>();
                        singletablearray.add(tablecontraint);
                        tablesI = singletablearray.iterator();
                    }
                    Table table;
                    String tablenewname;
                    String tablecontent;
                    String filename;
                    while(tablesI.hasNext()) {
                        table = tablesI.next();
                        if(table.isTable() && Metafilenaming.has_metatag_table(itemname)
                                || table.isView() && Metafilenaming.has_metatag_view(itemname)) {
                            metacodeprocessor.processTable(newname.toString(), table);
                        }
                    }
                } else {
                    metacodeprocessor.processFile(newname.toString(), tablecontraint);
                }
            }
        }
    }
    
    /**
     * copy directory structure with all metacode to project
     * @param source: source path
     * @param destination: destination path
     */
    public void copydirectory(String source, String destination) {
        File destdir;
        destdir = new File(destination);
        if(!destdir.exists()) { destdir.mkdirs(); }
        File sourcedir = new File(source);
        if(sourcedir.exists()) {
            File[] files = sourcedir.listFiles();
            File copyfile, destfile;
            for (File file : files) {
                if (file.isFile()) {
                    String filename = file.getAbsolutePath();
                    if (filename.charAt(0) != '.') {
                        copyfile = new File(source + File.separator + file.getName());
                        destfile = new File(destination + File.separator + file.getName());
                        //copy
                        try {
                            FileInputStream fis;
                            try (FileOutputStream fos = new FileOutputStream(destfile)) {
                                fis = new FileInputStream(copyfile);
                                byte buffer[] = new byte[1024];
                                int n;
                                while ((n = fis.read(buffer)) > 0) {
                                    fos.write(buffer, 0, n);
                                }
                            }
                            fis.close();
                        }
                        catch(IOException ioe) {
                            String msg = ioe.getMessage();
                        }
                    }
                } else {
                    if (file.getName().charAt(0) != '.') {
                        copydirectory(file.getAbsolutePath(), destination + File.separator + file.getName());
                    }
                }
            }
        }
    }
        
}
