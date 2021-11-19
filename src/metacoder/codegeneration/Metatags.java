/*
 * Metatags
 */
package metacoder.codegeneration;

/**
 * All metatags representing datase variables and functions
 * @author Franky Laseure
 */
public interface Metatags {

    //database driver dependent variables
    public static final String DBTOOL = ":dbtool:"; //db tool name
    public static final String SQL_CONVERTOR_CLASS = ":sqlconvertor:"; //db jbdc driver convertor class
    
    //general tags
    public static final String COMMASEPARATOR = ", ";
    public static final String RETURNSEPARATOR = "\n";
    public static final String SEPARATORTAG = ":separator:";
    public static final String METACODERDATE = ":metacoder_date:";
        
    public static final String TAGSYM = ":";
    public static final String TAGCOMMA = ":,:";
    public static final String EMPTYTAGSYM = TAGSYM + TAGSYM;
    public static final String NOUPDATE = "//Metacoder: NO AUTHOMATIC UPDATE";
    public static final String CUSTOMCODEBLOCK = "//Custom code, do not change this line";
    
    /** START database names name formatting
    variable names and data types **/
    
    //project
    public static final String PROJECT_LOWERCASE = ":project:";
    public static final String PROJECT_ULOWERCASE = ":Project:";
    public static final String PROJECT_UPPERCASE = ":PROJECT:";
    public static final String PROJECT_ORIGINAL = ":project_o:";
    
    //table
    public static final String TABLE_LOWERCASE = ":table:";
    public static final String TABLE_ULOWERCASE = ":Table:";
    public static final String TABLE_UPPERCASE = ":TABLE:";
    public static final String TABLE_ORIGINAL = ":table_o:";
    public static final String TABLENAME_LOWERCASE = ":tablename:";
    public static final String TABLENAME_ULOWERCASE = ":Tablename:";
    public static final String TABLENAME_ORIGINAL = ":tablename_o:";
    
    //view
    public static final String VIEW_LOWERCASE = ":view:";
    public static final String VIEW_ULOWERCASE = ":View:";
    
    //column
    public static final String COLUMN_LOWERCASE = ":column:";
    public static final String COLUMN_ULOWERCASE = ":Column:";
    public static final String COLUMN_UPPERCASE = ":COLUMN:";
    public static final String COLUMN_ORIGINAL = ":column_o:";
    public static final String COLUMN_JAVANAME = ":columnjavaname:"; //name change in case a java restricted naming was used (ex. public)
    public static final String COLUMN_TYPE = ":columntype:"; //programming language data type
    public static final String COLUMN_SQLTYPE = ":columntypesql:"; //SQL database data type
    public static final String COLUMN_SIZE = ":columnsize:";
    public static final String COLUMN_GETDBFUNCTION = ":getcolumndbfunction:"; //java.sql.ResultSet get functions for the column data type (in most cases getObject()
    public static final String COLUMN_DBCAST = ":columncast:"; //Databasedriver dependent java cast type Object (in case of custom objects). ex: GIS shapes
    public static final String COLUMN_CUSTOMTYPE = ":columncustomtype:"; //java custom data type, typical a custom object developed for this project
    public static final String COLUMN_DBPOSITION = ":columnposition:"; //db table column ordinal position
    
    //foreign key
    public static final String FOREIGNKEY_UNIQUENAME_LOWERCASE = ":uniquename:";
    public static final String FOREIGNKEY_UNIQUENAME_ULOWERCASE = ":Uniquename:";
    public static final String FOREIGNKEY_UNIQUENAME_ORIGINAL = ":uniquename_o:";
    public static final String FOREIGNKEY_JAVANAME = ":fkjavaname:"; //add a sequence number when a foreign key is used more then once in a table
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_LOWERCASE = ":pktable:";
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_ULOWERCASE = ":Pktable:";
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_ORIGINAL = ":pktable_o:";
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_JAVANAME_LOWERCASE = ":pktablejavaname:"; //add a sequence number when a key is used more then once in a table
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_JAVANAME_ULOWERCASE = ":Pktablejavaname:";
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_JAVANAMEPK_LOWERCASE = ":pktablejavanamePK:"; //add PK to the name, with sequence number if needed
    public static final String FOREIGNKEY_PRIMARYKEYTABLE_JAVANAMEPK_ULOWERCASE = ":PktablejavanamePK:";

    //foreign key column
    public static final String FOREIGNKEY_COLUMN_LOWERCASE = ":foreigncolumn:";
    public static final String FOREIGNKEY_COLUMN_ULOWERCASE = ":Foreigncolumn:";
    public static final String FOREIGNKEY_COLUMN_ORIGINAL = ":foreigncolumn_o:";
    public static final String FOREIGNKEY_PRIMARYKEYKCOLUMN_LOWERCASE = ":primarycolumn:";
    public static final String FOREIGNKEY_PRIMARYKEYCOLUMN_ULOWERCASE = ":Primarycolumn:";
    public static final String FOREIGNKEY_PRIMARYKEYCOLUMN_ORIGINAL = ":primarycolumn_o:";
    
    //external foreign key = from a table perspective, foreign keys in other tables pointing to this table primary key
    //in other words: where is the primary key of a table used ?
    public static final String EXERTNALFOREIGNKEY_TABLE_LOWERCASE = ":extablename:";
    public static final String EXERTNALFOREIGNKEY_TABLE_ULOWERCASE = ":Extablename:";
    public static final String EXERTNALFOREIGNKEY_TABLE_ORIGINAL = ":extablename_o:";
    public static final String EXERTNALFOREIGNKEY_FOREIGNKEYUNIQUENAME_LOWERCASE = ":exfkuniquename:"; //FOREIGNKEY_UNIQUENAME_LOWERCASE from referencing table
    public static final String EXERTNALFOREIGNKEY_FOREINGKEYUNIQUENAME_ULOWERCASE = ":Exfkuniquename:"; //FOREIGNKEY_UNIQUENAME_LOWERCASE from referencing table
    public static final String EXERTNALFOREIGNKEY_UNIQUENAME_LOWERCASE = ":uniquename:"; //FOREIGNKEY_UNIQUENAME_LOWERCASE from referencing table
    public static final String EXERTNALFOREIGNKEY_UNIQUENAME_ULOWERCASE = ":Uniquename:"; //FOREIGNKEY_UNIQUENAME_LOWERCASE from referencing table
    
    //relational foreign key = from a table perspective, foreign keys in other tables pointing to this table primary key
    //additional, in the referencing table, the primary key consists of this foreign key with min. one other foreign key
    public static final String RELATIONALFOREIGNKEY_TABLE_LOWERCASE = ":reltablename:";
    public static final String RELATIONALFOREIGNKEY_TABLE_ULOWERCASE = ":Reltablename:";
    public static final String RELATIONALFOREIGNKEY_TABLE_ORIGINAL = ":reltablename_o:";
    public static final String RELATIONALFOREIGNKEY_FOREIGNKEYUNIQUENAME_LOWERCASE = ":relfkuniquename:";
    public static final String RELATIONALFOREIGNKEY_FOREIGNKEYUNIQUENAME_ULOWERCASE = ":relFkuniquename:";
    public static final String RELATIONALFOREIGNKEY_UNIQUENAME_LOWERCASE = ":uniquename:";
    public static final String RELATIONALFOREIGNKEY_UNIQUENAME_ULOWERCASE = ":Uniquename:";
    
    
    /** END database names name formatting
    variable names and data types **/
    
    //extra data fields
    public static final String COUNTERTAG = ":counter:";
    
    //data types
    public static final String CUSTOMFIELDTYPE = ":customfieldtype:";
    
    //repeaters
    public static final String REPEATTABLES = ":repeattables:";
    public static final String REPEATVIEWS = ":repeatviews:";
    public static final String REPEATTABLES_VIEWS = ":repeattablesviews:";
    public static final String REPEATPRIMARYKEYFIEDLS = ":repeatpkfields:";
    public static final String REPEATFOREIGNKEYS = ":repeatforeignkeys:";
    public static final String REPEATUNIQUEFOREIGNKEYS = ":repeatuniqueforeignkeys:";
    public static final String REPEATALLEXTERNALFOREIGNKEYS = ":repeatallexternalforeignkeys:";
    public static final String REPEATEXTERNALFOREIGNKEYS = ":repeatexternalforeignkeys:"; //External foreign key is part of primary key
    public static final String REPEATUNIQUEEXTERNALFOREIGNKEYS = ":repeatuniqueexternalforeignkeys:";//=REPEATEXTERNALFOREIGNKEYS, double references to other tables removed
    public static final String REPEATFOREIGNKEYFIELDS = ":repeatforeignkeyfields:";
    public static final String REPEATREFERENCES = ":repeatreferences:"; //all distinct tables that have a reference as foreign keys from complete database, not part of a primary key (excluding doubles)
    public static final String REPEATFIELDS = ":repeatfields:"; //all fields not part of any key
    public static final String REPEATALLFIELDS = ":repeatallfields:";
    
    //constraints
    public static final String INPRIMARYKEY = ":inpk:";
    public static final String NOTPRIMARYKEY = ":notpk:";
    public static final String INFOREIGNKEY = ":infk:";
    public static final String NOTFOREIGNKEY = ":notfk:";
    public static final String INFOREIGNKEYNOTPRIMARYKEY = ":infknotpk:";
    public static final String INKEY = ":inkey:"; //is part of any key
    public static final String NOTKEY = ":notkey:"; //is not part of any key
    public static final String IFFOREIGNKEY = ":ifforeignkey:"; //used with REPEATEXTERNALFOREIGNKEYS / REPEATALLEXTERNALFOREIGNKEYS
    public static final String IFRELATIONAL = ":ifrelational:"; //used with REPEATEXTERNALFOREIGNKEYS / REPEATALLEXTERNALFOREIGNKEYS
    
    public static final String IFFIELDTYPECONSTRAINT = ":iffieldtype:";
    public static final String IFFIELDOTHER = ":other:";
    public static final String ISNOTNULLABLECONSTRAINT = ":isnotnullable:";
    
    //special case - used for a field that used it's field value as a file name on disk
    //probably not needed anymore
    public static final String ISFILECONSTRAINT = ":iffileupload:";
}
