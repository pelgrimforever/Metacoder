/*
 * Security.java
 *
 * Created on Dec 23, 2012, 6:11 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.BusinessObject.security;

import general.exception.DBException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author Franky Laseure
 */
public class Security {
//Metacoder: NO AUTHOMATIC UPDATE

    public Security() {
    }

    public boolean check(String siteusername, String password) throws DBException {
        boolean authenticated = false;
//        try {
            if(siteusername!=null && siteusername.length()>0) {
                //to be completed
            }
//        }
//        catch(sitesecurity.general.exception.DBException sitesecurityDBException) {
//            throw new DBException(sitesecurityDBException);
//        }
        return authenticated;
    }

    public String encrypt(String string) {
        String returnstring = null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(string.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            returnstring = sb.toString();
        }
        catch(NoSuchAlgorithmException uee) {
        }
        return returnstring;
    }
}

