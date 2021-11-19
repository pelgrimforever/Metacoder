/*
 * process all views
 */
package metacoder.codegeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;

/**
 * process all views
 * @author Franky Laseure
 */
public class RepeatviewsProcessor extends RepeatabstracttableProcessor {

    private Xmlreader xmlreader;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param xmlreader: XML database projects
     */
    public RepeatviewsProcessor(Programlanguage planguage, StringBuilder code, Xmlreader xmlreader) {
        super(planguage, Metatags.REPEATVIEWS, code);
        this.xmlreader = xmlreader;
    }
    
    /**
     * process view Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        //sort views by name
        List<Xmlreader.Table> tables = new ArrayList<>(xmlreader.getTables().values());
        Collections.sort(tables, Comparator.comparing(Xmlreader.Table::getname));
        for(Xmlreader.Table table: tables) {
            if(table.isView()) {
                StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                processTable(planguage, newblock, table, xmlreader);
                codefragment.addSeparators(newblock);
                newsubcontent.append(newblock);
            }
        }
        return newsubcontent;
    }
}
