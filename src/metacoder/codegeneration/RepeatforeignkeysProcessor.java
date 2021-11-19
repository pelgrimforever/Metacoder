/*
 * process all foreign keys
 */
package metacoder.codegeneration;

import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import text.Conversion;

/**
 * process all foreign keys
 * @author Franky Laseure
 */
public class RepeatforeignkeysProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language
     * @param code: program code
     * @param table: table metadata
     */
    public RepeatforeignkeysProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table) {
        super(planguage, Metatags.REPEATFOREIGNKEYS, code);
        this.table = table;
        String[] tags = { Metatags.INPRIMARYKEY, Metatags.NOTPRIMARYKEY };
        blocktags = tags;
    }
    
    /**
     * process Foreign key Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder newsubcontent = new StringBuilder();
        StringBuilder[] newblocks = new StringBuilder[blocktags.length];
        //initialize
        for(int i=0; i<blocktags.length; i++) {
            newblocks[i] = new StringBuilder();
        }
        StringBuilder newallblock = new StringBuilder();
        Xmlreader.Table.Primarykey.Column pkc;
        for(Xmlreader.Table.Foreignkey fk: table.getForeignkeys().values()) {
            if(blockfound) {
                //if a blogtags[i] was found, the code fragment is stored in blocks[i]
                //blocks[i] == null means blogtags[i] is not in de code
                for(int i=0; i<blocktags.length; i++) {
                    if(blocks[i]!=null) {
                        StringBuilder newblock = new StringBuilder(blocks[i].toString());
                        if(blocktags[i].equals(Metatags.INPRIMARYKEY) && fk.isPartofprimarykey()) {
                            Metatagsprocessor.processForeignkey(newblock, table, fk);
                            codefragment.addSeparators(newblock);
                            newblocks[i].append(newblock);
                        }
                        if(blocktags[i].equals(Metatags.NOTPRIMARYKEY) && !fk.isPartofprimarykey()) {
                            Metatagsprocessor.processForeignkey(newblock, table, fk);
                            codefragment.addSeparators(newblock);
                            newblocks[i].append(newblock);
                        }
                    }
                }
            } else {
                StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                Metatagsprocessor.processForeignkey(newblock, table, fk);
                codefragment.addSeparators(newblock);
                newallblock.append(newblock);
            }
        }
        if(!blockfound) {
            newsubcontent = newallblock;
        } else {
            if(blockstart[0]<blockstart[1]) {
                newsubcontent.append(newblocks[0]).append(newblocks[1]);
            } else {
                newsubcontent.append(newblocks[1]).append(newblocks[0]);
            }
        }
        return newsubcontent;
    }
}
