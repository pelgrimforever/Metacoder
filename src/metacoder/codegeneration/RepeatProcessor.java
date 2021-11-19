/*
 * Abstract base class for all Repeat processors
 */
package metacoder.codegeneration;

import metacoder.data.Programlanguage;
import text.StringBuilderutils;

/**
 * Abstract base class for all Repeat processors
 contains the general logic for finding code blocks contained with 2 equal Metatags and to replace this code
 * @author Franky Laseure
 */
public abstract class RepeatProcessor {
    
    protected Programlanguage planguage;
    protected String tag;
    protected Codefragment codefragment;
    
    /**
     * constructor
     * @param planguage: program language properties
     * @param tag: Metatag
     * @param code: program code
     */
    public RepeatProcessor(Programlanguage planguage, String tag, StringBuilder code) {
        this.planguage = planguage;
        this.tag = tag;
        codefragment = new Codefragment(code, tag);
    }
 
    /**
     * find code blocks between 2 Metatags
 process this code blocks
 replace original code blocks with formatted code
     */
    public void process() {
        //find next repeater metatag
        while(codefragment.findtag()) {
            //find constraint metatags if they are defined
            findblocks(codefragment.getCodefragment());
            //translate metatags to program code
            StringBuilder processedcode = processrepeator(codefragment.getCodefragment());
            //replace tag with new code
            codefragment.replacetagcode(processedcode);
        }
    }
    
    protected String[] blocktags;
    protected int[] blockstart;
    protected int[] blockend;
    protected StringBuilder[] blocks;
    protected boolean blockfound;
    
    /**
     * find code blocks for each tag in blocktags
     * one tag from blocktags can only be used once in a repeater
     * - block[i]: code fragment contained in a blocktag[i]
     * - blockstart[]: code block start position
     * - blockend[]: code block end position
     * - blockfound: min one of the tags in blogtags is found
     * this result is used in the subclass to be processed
     * @param subcode metacode code part
     */
    protected void findblocks(StringBuilder subcode) {
        int count = 0;
        if(blocktags!=null) {
            count = blocktags.length;
        }
        blockstart = new int[count];
        blockend = new int[count];
        blocks = new StringBuilder[count];
        blockfound = false;
        boolean endswithnewline = false;
        for(int i=0; i<count; i++) {
            blockstart[i] = subcode.indexOf(blocktags[i]);
            blockend[i] = subcode.indexOf(blocktags[i], blockstart[i]+1) + blocktags[i].length();
            if(blockstart[i]>-1 && blockend[i]>blocktags[i].length()-1) {
                blocks[i] = new StringBuilder(subcode.substring(blockstart[i] + blocktags[i].length(), blockend[i] - blocktags[i].length()));
                if(StringBuilderutils.startsWith(blocks[i], Metatags.RETURNSEPARATOR)) StringBuilderutils.subString(blocks[i], 1);
                if(StringBuilderutils.endsWith(blocks[i], Metatags.RETURNSEPARATOR)) {
                    StringBuilderutils.subString(blocks[i], 0, blocks[i].length()-1);
                    //if one block has a return, all will get return
                    endswithnewline = true;
                }
                blockfound = true;
                //if one block is found, override endswithnewline with format in block
                codefragment.getCodeformatter().setEndswithnewline(false);
            } else {
                //initialize
                blocks[i] = null;                
            }
        }
        if(endswithnewline) {
            //override endswithnewline, use the format found in block
            codefragment.getCodeformatter().setEndswithnewline(true);
        }
    }
    
    /**
     * abstract processrepeator
     * @param replacesubcode: program code fragment
     * @return program code
     */
    protected abstract StringBuilder processrepeator(StringBuilder replacesubcode);
    
}
