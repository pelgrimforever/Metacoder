/*
 * process all external foreign keys
 */
package metacoder.codegeneration;

import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import metacoder.data.xml.Xmlreader.Table.ExternalForeignkey;
import metacoder.data.xml.Xmlreader.Table.RelationalForeignkey;
import text.Conversion;

/**
 * process all external foreign keys
 * @author Franky Laseure
 */
public class RepeatallexternalforeignkeysProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    private Xmlreader xmlreader;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param content: program code
     * @param table: table metadata
     * @param xmlreader: XML database project
     */
    public RepeatallexternalforeignkeysProcessor(Programlanguage planguage, StringBuilder content, Xmlreader.Table table, Xmlreader xmlreader) {
        super(planguage, Metatags.REPEATALLEXTERNALFOREIGNKEYS, content);
        this.table = table;
        this.xmlreader = xmlreader;
    }
    
    /**
     * process external foreign keys Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        Xmlreader.Table.Column column;
        
        for(ExternalForeignkey efk: table.getExternalforeignkeys()) {
            StringBuilder addsubcode = new StringBuilder(replacesubcode.toString());
            Metatagsprocessor.processExternalforeignkey(addsubcode, efk);
            codefragment.addSeparators(addsubcode);
            newsubcontent.append(addsubcode);
        }
        return newsubcontent;
    }
    
}
