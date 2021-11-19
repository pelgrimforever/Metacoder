/*
 * process all fields from a table not part of a primary key or foreign key 
 * or view
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;

/**
 * process all fields from a table not part of a primary key or foreign key 
 * or view
 * @author Franky Laseure
 */
public class RepeatfieldsProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language
     * @param code: program code
     * @param table: table metadata
     */
    public RepeatfieldsProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table) {
        super(planguage, Metatags.REPEATFIELDS, code);
        this.table = table;
    }
    
    /**
     * process column Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        for(Xmlreader.Table.Column column: table.getSortedColumns()) {
            if(!table.ispartofanykey(column)) {
                StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                codefragment.addSeparators(newblock);
                newsubcontent.append(newblock);
            }
        }
        return newsubcontent;
    }
}
