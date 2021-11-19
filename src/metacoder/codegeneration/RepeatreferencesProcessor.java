/*
 * process all references used in all tables
 */
package metacoder.codegeneration;

import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import text.Conversion;
import text.StringBuilderutils;

/**
 * process all references used in all tables
 * @author Franky Laseure
 */
public class RepeatreferencesProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    private Xmlreader xmlreader;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param table: table metadata
     * @param xmlreader: Xml database projects
     */
    public RepeatreferencesProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table, Xmlreader xmlreader) {
        super(planguage, Metatags.REPEATREFERENCES, code);
        this.table = table;
        this.xmlreader = xmlreader;
    }
    
    /**
     * process Foreign key reference Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        HashMap cascade = new HashMap();
        Xmlreader.Table.Column column;
        Iterator Iforeignkey;
        for(Xmlreader.Table ftable: xmlreader.getTables().values()) {
            for(Xmlreader.Table.Foreignkey fk: ftable.getForeignkeys().values()) {
                if(!fk.isPartofprimarykey() && fk.getprimarykeytable().equals(table.getname()) && cascade.get(fk.getforeignkeytable())==null) {
                    StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                    StringBuilderutils.replaceAll(newblock, Metatags.EXERTNALFOREIGNKEY_TABLE_LOWERCASE, planguage.processTablename(ftable.getname()));
                    StringBuilderutils.replaceAll(newblock, Metatags.EXERTNALFOREIGNKEY_TABLE_ULOWERCASE, planguage.processTablename(ftable.getName()));
                    StringBuilderutils.replaceAll(newblock, Metatags.EXERTNALFOREIGNKEY_TABLE_ORIGINAL, ftable.getOriginalname());
                    Metatagsprocessor.processForeignkey(newblock, ftable, fk);
                    codefragment.addSeparators(newblock);
                    newsubcontent.append(newblock);
                    cascade.put(fk.getforeignkeytable(), fk);
                }
            }

        }
        return newsubcontent;
    }
}
