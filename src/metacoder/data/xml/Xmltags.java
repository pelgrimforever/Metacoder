/*
 * xml funtions for xmlreader and xml builder
 */
package metacoder.data.xml;

/**
 * xml funtions for xmlreader and xml builder
 * used in Xmlreader and Xmlbuilder
 * @author Franky Laseure
 */
public class Xmltags implements Ixmltags {
    
    /**
     * @param fieldname String
     * @return fieldname in lower case
     */
    public static String Low(String fieldname) {
        return fieldname.toLowerCase();
    }

    /**
     * @param fieldname String
     * @return first character in upper case, rest lower case
     */
    public static String Ulow(String fieldname) {
        return fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1).toLowerCase();
    }

    /**
     * @param fieldname String
     * @return fieldname in upper case
     */
    public static String Upper(String fieldname) {
        return fieldname.toUpperCase();
    }
    
}
