/*
 * Metafilenaming snippets
 */
package metacoder.codegeneration;

import text.Conversion;

/**
 * All meta naming snippets representing datase variables
 * @author Franky Laseure
 */
public class Metafilenaming {
    
    public static final String[] PROJECT_LOWERCASE = { "_project_", Metatags.PROJECT_LOWERCASE };
    public static final String[] PROJECT_ULOWERCASE = { "_Project_", Metatags.PROJECT_ULOWERCASE };
    public static final String[] TABLE_LOWERCASE = { "_table_", Metatags.TABLE_LOWERCASE };
    public static final String[] TABLE_ULOWERCASE = { "_Table_", Metatags.TABLE_ULOWERCASE };
    public static final String[] VIEW_LOWERCASE = { "_view_", Metatags.VIEW_LOWERCASE };
    public static final String[] VIEW_ULOWERCASE = { "_View_", Metatags.VIEW_ULOWERCASE };
 
    public static final String[][] naming = { 
        PROJECT_LOWERCASE, PROJECT_ULOWERCASE, 
        TABLE_LOWERCASE, TABLE_ULOWERCASE,
        VIEW_LOWERCASE, VIEW_ULOWERCASE
    };
    
    public static final String[][] table = { 
        TABLE_LOWERCASE, TABLE_ULOWERCASE
    };
    
    public static final String[][] view = { 
        VIEW_LOWERCASE, VIEW_ULOWERCASE
    };
    
    /**
     * check if a filename contains a table metatag
     * @param filename: short filename
     * @return true/false
     */
    public static boolean has_metatag_table(String filename) {        
        boolean found = false;
        for(String[] filenametags: table) {
            found = found || filename.contains(filenametags[0]) || filename.contains(filenametags[1]);
        }
        return found;
    }
    
    /**
     * check if a filename contains a view tag
     * @param filename: short filename
     * @return true/false
     */
    public static boolean has_metatag_view(String filename) {        
        boolean found = false;
        for(String[] filenametags: view) {
            found = found || filename.contains(filenametags[0]) || filename.contains(filenametags[1]);
        }
        return found;
    }
    
    /**
     * check if a filename contains a table or view metatag
     * @param filename: short filename
     * @return true/false
     */
    public static boolean has_metatag_table_view(String filename) {        
        return has_metatag_table(filename) || has_metatag_view(filename);
    }
    
    /**
     * convert filename tags to metatags
     * This is done to make the translation of metafilenaming uniform to metatags (ex _table_ to :table:)
     * @param filename: short filename
     * @return filename with metatags
     */
    public static String convert2Metatags(String filename) {
        String newname = filename;
        for(String[] filenametags: naming) {
            newname = Conversion.replaceAll(newname, filenametags[0], filenametags[1]);
        }
        return newname;
    }
}
