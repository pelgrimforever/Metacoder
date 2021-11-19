/*
 * process all foreign key fields
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import text.StringBuilderutils;

/**
 * process all foreign key fields
 * @author Franky Laseure
 */
public class RepeatfkfieldsProcessor extends RepeatProcessor {

    private Xmlreader.Table.Foreignkey fk;
    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language
     * @param content: program code
     * @param table: table metadata
     * @param fk: foreign key metadata
     */
    public RepeatfkfieldsProcessor(Programlanguage planguage, StringBuilder content, Xmlreader.Table table, Xmlreader.Table.Foreignkey fk) {
        super(planguage, Metatags.REPEATFOREIGNKEYFIELDS, content);
        this.table = table;
        this.fk = fk;
    }
    
    /**
     * process foreign key column Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        Xmlreader.Table.Column column;
        for(Xmlreader.Table.Foreignkey.Column fkcolumn: fk.getSortedColumns()) {
            column = (Xmlreader.Table.Column)table.getColumns().get(fkcolumn.getforeigncolumn());
            StringBuilder newblock = new StringBuilder(replacesubcode.toString());
            Metatagsprocessor.processForeignkeycolumn(newblock, fkcolumn);
            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
            codefragment.addSeparators(newblock);
            newsubcontent.append(newblock);
        }
        return newsubcontent;
    }
}
