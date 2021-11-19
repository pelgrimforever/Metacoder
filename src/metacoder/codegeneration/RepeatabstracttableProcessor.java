/*
 * Abstraction for general code used for views and tables
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;

/**
 *
 * @author Franky Laseure
 */
public abstract class RepeatabstracttableProcessor extends RepeatProcessor {
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param tag: Metatag
     * @param code: program code
     */
    public RepeatabstracttableProcessor(Programlanguage planguage, String tag, StringBuilder code) {
        super(planguage, tag, code);
    }

    /**
     * process all repeater processors for a table or view
     * @param planguage program language properties
     * @param code program code
     * @param table table metadata
     * @param xmlreader database project properties
     */
    protected static void processTable(Programlanguage planguage, StringBuilder code, Xmlreader.Table table, Xmlreader xmlreader) {
//tables
        Metatagsprocessor.processTablename(code, table);
//keys
        //:repeatforeignkeys:
        new RepeatforeignkeysProcessor(planguage, code, table).process();
        //:repeatuniqueforeignkeys:
        new RepeatuniqueforeignkeysProcessor(planguage, code, table).process();
        //:repeatexternalforeignkeys:
        new RepeatexternalforeignkeysProcessor(planguage, code, table, xmlreader).process();
        //:repeatallexternalforeignkeys:
        new RepeatallexternalforeignkeysProcessor(planguage, code, table, xmlreader).process();
        //:repeatuniqueexternalforeignkeys:
        new RepeatuniqueexternalforeignkeysProcessor(planguage, code, table, xmlreader).process();
        //:repeatreferences:
        new RepeatreferencesProcessor(planguage, code, table, xmlreader).process();
//fields        
        //:pkfieldlist:
        new RepeatpkfieldsProcessor(planguage, code, table).process();
//fields not part of any key
        //:repeatfields:
        new RepeatfieldsProcessor(planguage, code, table).process();
//all fields
        //:repeatallfields:
        new RepeatallfieldsProcessor(planguage, code, table).process();
//counter field
        Metatagsprocessor.processCounter(code);
    }
}
