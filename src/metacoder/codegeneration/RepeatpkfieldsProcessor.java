/*
 * process all primary key fields
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import metacoder.data.xml.Xmlreader;
import text.Conversion;

/**
 * process all primary key fields
 * @author Franky Laseure
 */
public class RepeatpkfieldsProcessor extends RepeatProcessor {

    private Xmlreader.Table table;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param code: program code
     * @param table: table metadata
     */
    public RepeatpkfieldsProcessor(Programlanguage planguage, StringBuilder code, Xmlreader.Table table) {
        super(planguage, Metatags.REPEATPRIMARYKEYFIEDLS, code);
        this.table = table;
        String[] tags = { Metatags.INFOREIGNKEY, Metatags.NOTFOREIGNKEY };
        blocktags = tags;
    }
    
    /**
     * process primary key columns Metatags
     * @param replacesubcode: program code
     * @return program code
     */
    @Override
    protected StringBuilder processrepeator(StringBuilder replacesubcode) {
        StringBuilder[] newblocks = new StringBuilder[blocktags.length];
        //initialize
        for(int i=0; i<blocktags.length; i++) {
            newblocks[i] = new StringBuilder();
        }
        StringBuilder newsubcontent = new StringBuilder();
        StringBuilder newallblock = new StringBuilder();
        for(Xmlreader.Table.Primarykey.Column pkc: table.getPrimarykey().getSordedColumns()) {
            Xmlreader.Table.Column col = (Xmlreader.Table.Column)table.getColumns().get(pkc.getname());
            if(blockfound) {
                //if a blogtags[i] was found, the code fragment is stored in blocks[i]
                //blocks[i] == null means blogtags[i] is not in de code
                for(int b=0; b<blocktags.length; b++) {
                    if(blocks[b]!=null) {
                        StringBuilder newblock = new StringBuilder(blocks[b].toString());
                        if(blocktags[b].equals(Metatags.INFOREIGNKEY) && table.ispartofpkforeignkey(col)) {
                            Xmlreader.Table.Foreignkey fk = table.getForeignkey(col);
                            Xmlreader.Table.Foreignkey.Column fkc = (Xmlreader.Table.Foreignkey.Column)fk.getColumns().get(col.getname());
                            Metatagsprocessor.processforeignkeytable(newblock, fk);
                            Metatagsprocessor.processForeignkeycolumn(newblock, fkc);
                            processSnippet(newblock, col);
                            newblocks[b].append(newblock);
                        }
                        if(blocktags[b].equals(Metatags.NOTFOREIGNKEY) && !table.ispartofpkforeignkey(col)) {
                            processSnippet(newblock, col);
                            newblocks[b].append(newblock);
                        }
                    }
                }
            } else {
                StringBuilder newblock = new StringBuilder(replacesubcode.toString());
                processSnippet(newblock, col);
                newallblock.append(newblock);
            }
        }
        if(!blockfound) {
            newsubcontent = newallblock;
        } else {
            for(int b=0; b<blocktags.length; b++) {
                newsubcontent.append(newblocks[b]);
            }
        }
        return newsubcontent;
    }
    
    private void processSnippet(StringBuilder snippet, Xmlreader.Table.Column col) {
        Metatagsprocessor.processColumn(codefragment.getCodeformatter(), snippet, col);
        codefragment.addSeparators(snippet);
    }
}
