/*
 * process all unique external foreign keys
 */
package metacoder.codegeneration;

import java.util.HashMap;
import java.util.Iterator;
import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import metacoder.data.xml.Xmlreader.Table.ExternalForeignkey;
import metacoder.data.xml.Xmlreader.Table.Foreignkey;
import metacoder.data.xml.Xmlreader.Table.RelationalForeignkey;
import text.Conversion;

/**
 * process all unique external foreign keys
 * meaning max 1 reference to a table is produced in de code
 * @author Franky Laseure
 */
public class RepeatuniqueexternalforeignkeysProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    private Xmlreader xmlreader;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param table: table metadata
     * @param xmlreader: XML database projects
     */
    public RepeatuniqueexternalforeignkeysProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table, Xmlreader xmlreader) {
        super(planguage, Metatags.REPEATUNIQUEEXTERNALFOREIGNKEYS, code);
        this.table = table;
        this.xmlreader = xmlreader;
        String[] tags = { Metatags.IFFOREIGNKEY, Metatags.IFRELATIONAL };
        blocktags = tags;
    }
    
    /**
     * process all unique external foreign keys Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        if(!blockfound) {
            for(int i=0; i<blocks.length; i++) {
                blocks[i] = new StringBuilder(replacesubcode.toString());
            }
        }
        StringBuilder newsubcontent = new StringBuilder();
        Xmlreader.Table.Column column;
        
        Iterator<RelationalForeignkey> Irelfks;
        HashMap fkhash = new HashMap();
        fkhash.put(table.getname(), table.getname());
        for(ExternalForeignkey efk: table.getExternalforeignkeys()) {
            if(efk.isPartofpk()) {
                if(!fkhash.containsKey(efk.getFk().getforeignkeytable())) {
                    fkhash.put(efk.getFk().getforeignkeytable(), efk.getFk().getforeignkeytable());
                    //if a blogtags[i] was found, the code fragment is stored in blocks[i]
                    //blocks[i] == null means blogtags[i] is not in de code
                    for(int i=0; i<blocktags.length; i++) {
                        if(blocks[i]!=null) {
                            StringBuilder newblock = new StringBuilder(blocks[i].toString());
                            if(blocktags[i].equals(Metatags.IFFOREIGNKEY)) {
                                Metatagsprocessor.processExternalforeignkey(newblock, efk);
                                codefragment.addSeparators(newblock);
                                newsubcontent.append(newblock);
                            }
                        }
                    }
                }
                //check if table is relation table
                for(RelationalForeignkey relfk: efk.getRelationalforeignkeys()) {
                    //chekc that relational table is not already referenced as a normal foreign key in primary table
                    boolean efkisused = false;
                    for(Foreignkey fk: table.getForeignkeys().values()) {
                        efkisused = relfk.getFk().getprimarykeytable().equals(fk.getprimarykeytable());
                        if(efkisused) {
                            break;
                        }
                    }
                    if(!efkisused) {
                        if(!fkhash.containsKey(efk.getFk().getforeignkeytable())) {
                            fkhash.put(efk.getFk().getforeignkeytable(), efk.getFk().getforeignkeytable());
                            //if a blogtags[i] was found, the code fragment is stored in blocks[i]
                            //blocks[i] == null means blogtags[i] is not in de code
                            for(int i=0; i<blocktags.length; i++) {
                                if(blocks[i]!=null) {
                                    StringBuilder newblock = new StringBuilder(blocks[i].toString());
                                    if(blocktags[i].equals(Metatags.IFRELATIONAL)) {
                                        Metatagsprocessor.processRelationalforeignkey(newblock, efk, relfk);
                                        codefragment.addSeparators(newblock);
                                        newsubcontent.append(newblock);
                                    }
                                }
                            }
                        }
                    }
                }            
            }
        }
        return newsubcontent;
    }
}
