/*
 * Codefragment processing
 */
package metacoder.codegeneration;

import text.Conversion;
import text.StringBuilderutils;

/**
 * Codefragment processing
 * find the next metatag codeblock and determine characteristics for formatting the resulting code
 * store original codeblock and replace it with processed code in a given program text
 * @author Franky Laseure
 */
public class Codefragment {
    
    private String tag;
    private boolean tagfound = false;
    
    private StringBuilder code;
    private int tagstart = 0;
    private int tagend = 0;
    private StringBuilder originalcodefragment = null;
    private StringBuilder codefragment = null;
    private String separatorblock = null;
    private StringBuilder replaceseparatorblock = null;
    
    private Codeformatter codeformatter = new Codeformatter();
    
    /**
     * constructor
     * @param code: program code
     * @param tag: searh tag
     */
    public Codefragment(StringBuilder code, String tag) {
        this.code = code;
        this.tag = tag;
    }
    
    /**
     * find first code block that starts and end with the initiated tag
     * set internal var originalcodefragment to the original code found
     * set internal var codefragment to the same code, with some pre-formatting
     * @return true if start and end tag is found
     */
    public boolean findtag() {
        this.tag = tag;
        tagfound = false;
        codeformatter.initialize();
        int start = code.indexOf(tag);
        int end = code.indexOf(tag, start+1) + tag.length();
        if(start>-1 && end>tag.length()-1) {
            tagfound = true;
            tagstart = start;
            tagend = end;
            originalcodefragment = new StringBuilder(code.substring(start, end));
            if(code.length()>end+1 && code.substring(end, end+1).equals(Metatags.RETURNSEPARATOR)) {
                originalcodefragment.append(Metatags.RETURNSEPARATOR);
                tagend++;
            }
            //remove tags at start and end
            codefragment = new StringBuilder(code.substring(start + tag.length(), end - tag.length()));
            //remove leading return character
            if(StringBuilderutils.startsWith(codefragment, Metatags.RETURNSEPARATOR)) {
                StringBuilderutils.subString(codefragment, 1);
            }
            //how to end each repeating block ?
            if(StringBuilderutils.endsWith(codefragment, Metatags.TAGCOMMA)) {
                codeformatter.setEndswithcomma(true);
                StringBuilderutils.subString(codefragment, 0, codefragment.length() - 3);
            } else if(StringBuilderutils.endsWith(codefragment, Metatags.TAGCOMMA + Metatags.RETURNSEPARATOR)) {
                codeformatter.setEndswithcomma(true);
                codeformatter.setEndswithnewline(true);
                StringBuilderutils.subString(codefragment, 0, codefragment.length() - 4);
            } else if(StringBuilderutils.endsWith(codefragment, Metatags.RETURNSEPARATOR)) {
                codeformatter.setEndswithnewline(true);
                StringBuilderutils.subString(codefragment, 0, codefragment.length() - 1);
            }
            //check if a separator is present (for repeater block)
            //don't check for a separator inside a separator ...
            if(StringBuilderutils.endsWith(codefragment, Metatags.SEPARATORTAG)) {
                codeformatter.setSeparatorfound(true);
                StringBuilderutils.subString(codefragment, 0, codefragment.lastIndexOf(Metatags.SEPARATORTAG));
                int tagpos = codefragment.lastIndexOf(Metatags.SEPARATORTAG);
                separatorblock = codefragment.substring(tagpos + Metatags.SEPARATORTAG.length());
                replaceseparatorblock = new StringBuilder(separatorblock);
                if(StringBuilderutils.startsWith(replaceseparatorblock, Metatags.RETURNSEPARATOR)) 
                    StringBuilderutils.subString(replaceseparatorblock, 1);
                StringBuilderutils.subString(codefragment, 0, tagpos);
            }
            return true;
        } else {
            codefragment = null;
            return false;
        }
    }

    /**
     * is tag found in code
     * @return true/false
     */
    public boolean tagfound() {
        return tagfound;
    }

    /**
     * @return Codeformatter for this code fragment
     */
    public Codeformatter getCodeformatter() {
        return codeformatter;
    }
    
    /**
     * @return found code block contained between the start/end tag, pre-formatting is done
     */
    public StringBuilder getCodefragment() {
        return codefragment;
    }
    
    /**
     * @return separator code snippet if one is found
     */
    public String getSeparatorblock() {
        return separatorblock;
    }
    
    /**
     * add replaceseparatorblock code with formatting to a given code fragment
     * @param code: program code
     */
    public void addSeparators(StringBuilder code) {
        //don't do formatting on empty code
        if(code.length()>0) {
            if(codeformatter.isSeparatorfound()) {
                code.append(replaceseparatorblock);
            }
            if(codeformatter.isEndswithcomma()) code.append(Metatags.COMMASEPARATOR);
            if(codeformatter.isEndswithnewline()) code.append(Metatags.RETURNSEPARATOR);
        }
    }
    
    /**
     * format the replacement code
     * replace the found code fragment with the replacement
     * @param replacewithblock processed program code block
     */
    public void replacetagcode(StringBuilder replacewithblock) {
        boolean addnewline = false;
        //format the end if this code
        if(codeformatter.isEndswithcomma() && !codeformatter.isEndswithnewline() && StringBuilderutils.endsWith(replacewithblock, Metatags.COMMASEPARATOR)) 
            StringBuilderutils.subString(replacewithblock, 0, replacewithblock.length() - 2);
        if(codeformatter.isEndswithcomma() && codeformatter.isEndswithnewline() && StringBuilderutils.endsWith(replacewithblock, Metatags.COMMASEPARATOR + Metatags.RETURNSEPARATOR)) {
            StringBuilderutils.subString(replacewithblock, 0, replacewithblock.length() - 3);
            addnewline = true;
        }
        if(codeformatter.isSeparatorfound()) {
            if(replacewithblock.length()>0) {
                int pos = replacewithblock.lastIndexOf(replaceseparatorblock.toString());
                StringBuilderutils.subString(replacewithblock, 0, pos);
            }
        }
        if(addnewline) replacewithblock.append(Metatags.RETURNSEPARATOR);
        
        //replace original code found in tag
        code.replace(tagstart, tagend, replacewithblock.toString());
    }

}
