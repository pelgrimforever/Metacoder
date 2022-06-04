/*
 * I:Table:Operation.java
 *
 * Created on Dec 13, 2012, 12:22 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.servlet;

/**
 * Interface I:Table:Operation
 * 
 * Attributes: Defines the possible operations for the specified table
 * Methods: 
 * @author Franky Laseure
 */
public interface I:Table:Operation {

    //SELECT OPERATIONS
    public static final byte SELECT_COUNT = 0;
    public static final byte SELECT_ALL = 1;
    public static final byte SELECT_:TABLE: = 2;
    public static final byte SELECT_SEARCH = 3;
    public static final byte SELECT_SEARCHCOUNT = 4;
:repeatforeignkeys:
    public static final byte SELECT_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    public static final byte SELECT_:Uniquename: = 100 + :counter:;
:ifforeignkey:
:repeatexternalforeignkeys:
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    //UPDATE OPERATIONS
    public static final byte UPDATE_:TABLE: = 10;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    //INSERT OPERATIONS
    public static final byte INSERT_:TABLE: = 20;
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    //DELETE OPERATIONS
    public static final byte DELETE_:TABLE: = 30;
:repeatforeignkeys:
    public static final byte DELETE_:Uniquename: = 100 + :counter:;
:repeatforeignkeys:
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

    //BACKUP OPERATIONS
//Custom code, do not change this line
//add here custom operations
//Custom code, do not change this line   

}

