/*
 * Export Metacode to project
 */
package metacoder.codegeneration;

import base.AppController;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import metacoder.data.xml.Xmlreader.Table;
import text.StringBuilderutils;

/**
 * Export Metacode to project
 convert Metacode to code defined in the program language object
 * @author pelgrim
 */
public class Metacodeprocessor {
    
    public static final String METACODEDIRECTORY = "metacode";
    
    private Xmlreader dbproperties;
    private Programlanguage planguage;
    private String destinationpath;
    StringBuilder metacode;
    
    private String date; //timestamp to put in metacode comment
    
    private boolean noupdate = false;
    /**
     * if file exists in project, do not update
     * @return true/false
     */
    public boolean isNoupdate() {
        return noupdate;
    }
    private ArrayList<String> customcodeblocks = new ArrayList<String>();
    private StringBuilder oldprojectcode;
    
    /**
     * Initialize metacode
     * - read metacode
     * - set file destination path
     * - set database metadata
     * - set program language
     * @param metacodefilename: full path to metacode file
     * @param destinationpath: full path to file destination in project
     * @param dbproperties: Xmlreader with database metadata
     * @param planguage: program language properties
     */
    public Metacodeprocessor(String metacodefilename, String destinationpath, Xmlreader dbproperties, Programlanguage planguage) {
        this.destinationpath = destinationpath;
        this.dbproperties = dbproperties;
        this.planguage = planguage;
        //set timestamp
        GregorianCalendar gc = new GregorianCalendar();
        date = gc.get(GregorianCalendar.DAY_OF_MONTH) + ".";
        date += gc.get(GregorianCalendar.MONTH) + ".";
        date += gc.get(GregorianCalendar.YEAR) + " ";
        date += gc.get(GregorianCalendar.HOUR_OF_DAY) + ":";
        date += gc.get(GregorianCalendar.MINUTE);
        metacode = new StringBuilder();
        //read metacode
        File inputfile = new File(metacodefilename);
        try {
            DataInputStream is = new DataInputStream(new FileInputStream(inputfile));
            String temp = "";
            while ((temp = is.readLine())!=null) {
                metacode.append(temp).append("\n");
            }
            is.close();
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
    }

    /**
     * check if metacode contains the NOUPDATE tag
     * @param metacode: program metacode
     * @return true/false
     */
    private boolean noupdate(StringBuilder metacode) {
        return StringBuilderutils.contains(metacode, Metatags.NOUPDATE);
    }
    
    /**
     * process metacode for specified table and save in destination file
     * @param metacodename: metacode map, containing a table or view tag
     * @param table: table metadata
     */
    public void processTable(String metacodename, Table table) {
        //translate tags
        StringBuilder tablenewname = new StringBuilder(metacodename);
        Metatagsprocessor.processTablename(tablenewname, table, planguage);
        //full file destination path
        StringBuilder filename = new StringBuilder(destinationpath).append(File.separator).append(tablenewname.toString());
        //read existing file in projet if it exsists
        readoldcontent(filename.toString());
        //if file does not exist yet or does not containt the NOUPDATE tag
        if(!isNoupdate()) {
            StringBuilder tablecontent = new StringBuilder(metacode.toString());
            //translate tags to program metacode
            if(table.isTable() && Metafilenaming.has_metatag_table(metacodename)) {
                processtablecontent(tablecontent, table);
            } else {
                processviewcontent(tablecontent, table);
            }
            //write to destination
            if(notequaltoOld(tablecontent)) {
                writeFile(filename.toString(), tablecontent.toString());
            }
        }        
    }
    
    /**
     * process metacode for specified table and save in destination file
     * @param metacodename: metacode map, containing a table or view tag
     * @param tablecontraint: optional table metadata, meaning this file is in a subfolder with a table or view tag
     * In that case tags are processed for this table
     */
    public void processFile(String metacodename, Table tablecontraint) {
        //full file destination path
        StringBuilder filename = new StringBuilder(destinationpath).append(File.separator).append(metacodename);
        //read existing file in projet if it exsists
        readoldcontent(filename.toString());
        //if file does not exist yet or does not containt the NOUPDATE tag
        if(!isNoupdate()) {
            //translate tags to program metacode
            StringBuilder code = new StringBuilder(metacode.toString());
            processTableViewRepeaters(code);
            if(tablecontraint!=null) {
                if(tablecontraint.isTable()) {
                    processtablecontent(code, tablecontraint);
                }
                if(tablecontraint.isView()) {
                    processviewcontent(code, tablecontraint);
                }
            }
            //write to destination
            if(notequaltoOld(code)) {
                writeFile(filename.toString(), code.toString());
            }
        }
    }
    
    /**
     * read existing project file
     * @param filename: full path to project program file
     */
    public void readoldcontent(String filename) {
        //initialize
        noupdate = false;
        customcodeblocks = new ArrayList<String>();
        //read old file
        File oldfile = new File(filename);
        if(oldfile.exists()) {
            StringBuilder oldcode = readFile(filename);
            oldprojectcode = new StringBuilder(oldcode.toString());
            noupdate = noupdate(oldcode);
            if(!noupdate) {
                //filter custom metacode blocks from old file
                //everything else is ignored
                String codeblock;
                int start = oldcode.indexOf(Metatags.CUSTOMCODEBLOCK) + Metatags.CUSTOMCODEBLOCK.length();
                if(start>Metatags.CUSTOMCODEBLOCK.length()-1) {
                    if(oldcode.substring(start).startsWith(Metatags.RETURNSEPARATOR)) start++;
                }
                int end = oldcode.indexOf(Metatags.CUSTOMCODEBLOCK, start);
                while(start>Metatags.CUSTOMCODEBLOCK.length()-1 && end>-1) {
                    codeblock = oldcode.substring(start, end);
                    customcodeblocks.add(codeblock);
                    StringBuilderutils.subString(oldcode, end + Metatags.CUSTOMCODEBLOCK.length());
                    start = oldcode.indexOf(Metatags.CUSTOMCODEBLOCK) + Metatags.CUSTOMCODEBLOCK.length();
                    if(start>Metatags.CUSTOMCODEBLOCK.length()-1) {
                        if(oldcode.substring(start).startsWith(Metatags.RETURNSEPARATOR)) start++;
                        end = oldcode.indexOf(Metatags.CUSTOMCODEBLOCK, start);
                    }
                }
            }
        }
    }

    private static final String checkGenerated = "Generated on ";
    private static final int removecharacters = 36;
    
    /**
     * check if there is a code change compared to the previous project code
     * this assumes that "Generated on " is placed in the header comments
     * @param content: generated code
     * @return true/false
     */
    public boolean notequaltoOld(StringBuilder content) {
        if(oldprojectcode==null) {
            return true;
        } else {
            StringBuilder oldcontent = new StringBuilder(oldprojectcode.toString());
            StringBuilder newcontent = new StringBuilder(content.toString());
            if(StringBuilderutils.contains(oldcontent, checkGenerated) && StringBuilderutils.contains(newcontent, checkGenerated)) {
                StringBuilderutils.subString(oldcontent, oldcontent.indexOf(checkGenerated) + removecharacters);
                StringBuilderutils.subString(newcontent, content.indexOf(checkGenerated) + removecharacters);
            }
            return !oldcontent.equals(newcontent);
        }
    }
    
    /**
     * find all customcode tags
 copy the metacode contained between those tags to the new file
     * @param code: program metacode
     */
    public void replacecustomcodeblocks(StringBuilder code) {
        //filter custom metacode blocks from old file
        //everything else is ignored
        String codeblock;
        int counter = 0;
        int start = code.indexOf(Metatags.CUSTOMCODEBLOCK);
        int usestart = start + Metatags.CUSTOMCODEBLOCK.length();
        if(start>-1 && code.substring(usestart).startsWith(Metatags.RETURNSEPARATOR)) usestart++;
        int end = code.indexOf(Metatags.CUSTOMCODEBLOCK, usestart);
        while(customcodeblocks.size()>counter && start>-1 && end>-1) {
            
            code.replace(usestart, end, customcodeblocks.get(counter));
            //code = new StringBuilder(code.substring(0, usestart)).
            //        append(customcodeblocks.get(counter)).
            //        append(code.substring(end));
            start = code.indexOf(Metatags.CUSTOMCODEBLOCK, usestart + customcodeblocks.get(counter).length() + Metatags.CUSTOMCODEBLOCK.length());
            if(start>-1) {
                usestart = start + Metatags.CUSTOMCODEBLOCK.length();
                if(code.substring(usestart).startsWith(Metatags.RETURNSEPARATOR)) usestart++;
                end = code.indexOf(Metatags.CUSTOMCODEBLOCK, usestart);
            }
            counter++;
        }
    }
    
    /**
     * process tablerepeaters and viewrepeaters
     * @param code: program metacode
     */
    public void processTableViewRepeaters(StringBuilder code) {
        new Repeattables_viewsProcessor(this.planguage, code, this.dbproperties).process();
        new RepeatviewsProcessor(this.planguage, code, this.dbproperties).process();
        new RepeattablesProcessor(this.planguage, code, this.dbproperties).process();
        Metatagsprocessor.processDatabasetoolname(code, this.dbproperties.getDatabase());
        Metatagsprocessor.processProjectname(code, this.dbproperties.getDatabase());
        Metatagsprocessor.processDate(code, date);
        replacecustomcodeblocks(code);
    }
    
    /**
     * process tablerepeaters and viewrepeaters
 process table Metatags for specified table
     * @param code: program metacode
     * @param table: table metadata
     */
    public void processtablecontent(StringBuilder code, Xmlreader.Table table) {
        //tables
        //new RepeattablesProcessor(this.planguage, code, this.dbproperties).process();
/* debug 1 singel table / metacode */            
/*        if(table.getname().equals("orders") && StringBuilderutils.contains(metacode, "public void checkDATA(I:Table:" )) {
            int i = 0;
        }*/
        RepeattablesProcessor.processTable(this.planguage, code, table, this.dbproperties);
        processTableViewRepeaters(code);
    }
    
    /**
     * process tablerepeaters and viewrepeaters
 process view Metatags for specified table
     * @param code: program metacode
     * @param view: view metadata
     */
    public void processviewcontent(StringBuilder code, Xmlreader.Table view) {
        //:repeatfields:
        new RepeatfieldsProcessor(this.planguage, code, view).process();
        //tables
        Metatagsprocessor.processTablename(code, view);
        processTableViewRepeaters(code);
    }
    
    /**
     * read file metacode
     * @param filename: full file path
     * @return file metacode
     */
    public static StringBuilder readFile(String filename) {
        StringBuilder content = new StringBuilder();
        File inputfile = new File(filename);
        try {
            DataInputStream is = new DataInputStream(new FileInputStream(inputfile));
            String temp = "";
            while ((temp = is.readLine())!=null) {
                content.append(temp).append("\n");
            }
            is.close();
        }
        catch(FileNotFoundException e) {}
        catch(IOException e) {}
        return content;
    }
            
    /**
     * write file metacode
     * @param filename: full file path
     * @param content: file text
     */
    private static void writeFile(String filename, String content) {
        File outfile = new File(filename);
        try {
            DataOutputStream os = new DataOutputStream(new FileOutputStream(outfile));
            os.writeBytes(content);
            os.close();
        }
        catch(FileNotFoundException e) {
            AppController.showMessage(e.getMessage());
        }
        catch(IOException e) {
            AppController.showMessage(e.getMessage());
        }
    }
}
