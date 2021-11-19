/*
 * process all tables and views
 */
package metacoder.codegeneration;

import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;

/**
 * process all tables and views
 * @author Franky Laseure
 */
public class Repeattables_viewsProcessor extends RepeatabstracttableProcessor {

    private Xmlreader xmlreader;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param xmlreader: XML database projects
     */
    public Repeattables_viewsProcessor(Programlanguage planguage, StringBuilder code, Xmlreader xmlreader) {
        super(planguage, Metatags.REPEATTABLES_VIEWS, code);
        this.xmlreader = xmlreader;
    }
    
    /**
     * process table and view Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        for(Xmlreader.Table table: xmlreader.getTables().values()) {
            if(table.isTable() || table.isView()) {
                StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                processTable(planguage, newblock, table, xmlreader);
                codefragment.addSeparators(newblock);
                newsubcontent.append(newblock);
            }
        }
        return newsubcontent;
    }
}
