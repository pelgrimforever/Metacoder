/*
 * process all fields from a table or view
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import text.Conversion;

/**
 * process all fields from a table or view
 * @author Franky Laseure
 */
public class RepeatallfieldsProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param table: table metadata
     */
    public RepeatallfieldsProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table) {
        super(planguage, Metatags.REPEATALLFIELDS, code);
        this.table = table;
        String[] tags = { Metatags.INKEY, Metatags.NOTKEY, Metatags.INPRIMARYKEY, Metatags.NOTPRIMARYKEY, 
            Metatags.INFOREIGNKEY, Metatags.NOTFOREIGNKEY, Metatags.INFOREIGNKEYNOTPRIMARYKEY };
        blocktags = tags;
    }
    
    /**
     * process column Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        String[] newblocks = new String[blocktags.length];
        //initialize
        for(int i=0; i<blocktags.length; i++) {
            newblocks[i] = "";
        }
        String newsubcontent = "";
        StringBuilder newallblock = new StringBuilder();
        for(Xmlreader.Table.Column column: table.getSortedColumns()) {
            if(blockfound) {
                //if a blogtags[i] was found, the code fragment is stored in blocks[i]
                //blocks[i] == null means blogtags[i] is not in de code
                for(int b=0; b<blocktags.length; b++) {
                    if(blocks[b]!=null) {
                        StringBuilder newblock = new StringBuilder(blocks[b].toString());
                        if(blocktags[b].equals(Metatags.INPRIMARYKEY) && table.ispartofprimarykey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.NOTPRIMARYKEY) && !table.ispartofprimarykey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.INFOREIGNKEY) && table.ispartofforeignkey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.NOTFOREIGNKEY) && !table.ispartofforeignkey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.INKEY) && table.ispartofanykey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.NOTKEY) && !table.ispartofanykey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.INFOREIGNKEYNOTPRIMARYKEY) && table.ispartofforeignkey(column) && !table.ispartofprimarykey(column)) {
                            Metatagsprocessor.processColumn(codefragment.getCodeformatter(), newblock, column);
                            codefragment.addSeparators(newblock);
                            newallblock.append(newblock);
                        }
                    }
                }
            } else {
                StringBuilder result = new StringBuilder(replacesubcode.toString());
                Metatagsprocessor.processColumn(codefragment.getCodeformatter(), result, column);
                codefragment.addSeparators(result);
                newallblock.append(result);
            }
        }
        return newallblock;
    }
}
