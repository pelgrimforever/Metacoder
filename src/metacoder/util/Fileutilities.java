/*
 * Several file functions
 */
package metacoder.util;

import java.io.File;

/**
 * Several file functions
 * generaly used in the project
 * @author pelgrim
 */
public class Fileutilities {
    
    /**
     * get application directory
     * @return absolute path
     */
    public static String getCurrentDir() {
        String currentdir = new File(".").getAbsolutePath();
        currentdir = currentdir.substring(0, currentdir.length()-1);
        return currentdir;
    }
    
}
