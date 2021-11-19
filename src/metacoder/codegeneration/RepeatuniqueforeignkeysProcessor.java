/*
 * process unique foreign keys
 */
package metacoder.codegeneration;

import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;

/**
 * process unique foreign keys
 * meaning an external primary key is only produced once in the code
 * @author Franky Laseure
 */
public class RepeatuniqueforeignkeysProcessor extends RepeatProcessor {


    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param table: table metadata
     */
    public RepeatuniqueforeignkeysProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table) {
        super(planguage, Metatags.REPEATUNIQUEFOREIGNKEYS, code);
        this.table = table;
        String[] tags = { Metatags.INPRIMARYKEY, Metatags.INPRIMARYKEY };
        blocktags = tags;
    }
    
    /**
     * process unique foreign key Metatags
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
        HashMap fkhash = new HashMap();
        fkhash.put(table.getname(), table.getname());
        for(Xmlreader.Table.Foreignkey fk: table.getForeignkeys().values()) {
            if(!fkhash.containsKey(fk.getprimarykeytable())) {
                fkhash.put(fk.getprimarykeytable(), fk.getprimarykeytable());
                if(blockfound) {
                    for(int i=0; i<blocktags.length; i++) {
                        if(blocks[i]!=null) {
                            StringBuilder newblock = new StringBuilder(blocks[i].toString());
                            if(blocktags[i].equals(Metatags.INPRIMARYKEY) && fk.isPartofprimarykey()) {
                                Metatagsprocessor.processForeignkey(newblock, table, fk);
                                codefragment.addSeparators(newblock);
                                newblocks[i].append(newblock);
                            }
                            if(blocktags[i].equals(Metatags.INPRIMARYKEY) && !fk.isPartofprimarykey()) {
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
