/*
 * Translate Metatags into programming language
 */
package metacoder.codegeneration;

import metacoder.data.Databaseproject;
import metacoder.data.Programlanguage;
import metacoder.data.xml.DB;
import metacoder.data.xml.Xmlreader;
import metacoder.data.xml.Xmltags;
import text.StringBuilderutils;

/**
 * Translate Metatags into programming language
 * @author Franky Laseure
 */
public class Metatagsprocessor {
    
    private static Programlanguage planguage;

    /**
     * @param programlanguage: active program language properties
     */
    public static void setProgramlanguage(Programlanguage programlanguage) {
        planguage = programlanguage;
    }
    
    /**
     * replace DBTOOL with database tool name
     * @param code: program code
     * @param database DB metadata
     */
    public static void processDatabasetoolname(StringBuilder code, DB database) {
        StringBuilderutils.replaceAll(code, Metatags.DBTOOL, database.getDbtool());
    }
    
    /**
     * replace METACODERDATE with a formatted timestamp
     * @param code: program code
     * @param date: formatted timestring
     */
    public static void processDate(StringBuilder code, String date) {
        StringBuilderutils.replaceAll(code, Metatags.METACODERDATE, date);
    }
    
    /**
     * replace projectname tags with database name
     * @param code: program code
     * @param database: database metadata
     */
    public static void processProjectname(StringBuilder code, DB database) {
        processProjectname(code, database.getDatabasename());
    }
    
    /**
     * replace projectname tags with database name
     * @param code: program code
     * @param databaseproject: XML database project file code
     */
    public static void processProjectname(StringBuilder code, Databaseproject databaseproject) {
        processProjectname(code, databaseproject.getDatabasename());
    }
    
    /**
     * replace projectname tags with given database name
     * @param code: program code
     * @param databasename: database name
     */
    private static void processProjectname(StringBuilder code, String databasename) {
        StringBuilderutils.replaceAll(code, Metatags.PROJECT_LOWERCASE, Xmltags.Low(databasename));
        StringBuilderutils.replaceAll(code, Metatags.PROJECT_ULOWERCASE, Xmltags.Ulow(databasename));
        StringBuilderutils.replaceAll(code, Metatags.PROJECT_UPPERCASE, Xmltags.Upper(databasename));
        StringBuilderutils.replaceAll(code, Metatags.PROJECT_ORIGINAL, databasename);
    }
    
    /**
     * replace tablename tags with table name
     * replace viewname tags with view name
     * @param code: program code
     * @param table: table metadata
     */
    public static void processTablename(StringBuilder code, Xmlreader.Table table) {
        StringBuilderutils.replaceAll(code, Metatags.TABLENAME_LOWERCASE, table.getname());
        StringBuilderutils.replaceAll(code, Metatags.TABLENAME_ULOWERCASE, table.getName());
        StringBuilderutils.replaceAll(code, Metatags.TABLENAME_ORIGINAL, table.getOriginalname());
        StringBuilderutils.replaceAll(code, Metatags.TABLE_LOWERCASE, planguage.processTablename(table.getname()));
        StringBuilderutils.replaceAll(code, Metatags.TABLE_ULOWERCASE, planguage.processTablename(table.getName()));        
        StringBuilderutils.replaceAll(code, Metatags.TABLE_UPPERCASE, planguage.processTablename(table.getname().toUpperCase()));
        StringBuilderutils.replaceAll(code, Metatags.TABLE_ORIGINAL, planguage.processTablename(table.getOriginalname()));
        StringBuilderutils.replaceAll(code, Metatags.VIEW_LOWERCASE, table.getname());
        StringBuilderutils.replaceAll(code, Metatags.VIEW_ULOWERCASE, table.getName());
    }    

    /**
     * replace tablename tags with table name
     * replace viewname tags with view name
     * new name are checked with program language restrictions (reserved words)
     * @param code: program code
     * @param table: table metadata
     * @param planguage: program language properties
     */
    public static void processTablename(StringBuilder code, Xmlreader.Table table, Programlanguage planguage) {
        StringBuilderutils.replaceAll(code, Metatags.TABLENAME_LOWERCASE, planguage.processTablename(table.getname()));
        StringBuilderutils.replaceAll(code, Metatags.TABLENAME_ULOWERCASE, planguage.processTablename(table.getName()));
        StringBuilderutils.replaceAll(code, Metatags.TABLE_LOWERCASE, planguage.processTablename(table.getname()));
        StringBuilderutils.replaceAll(code, Metatags.TABLE_ULOWERCASE, planguage.processTablename(table.getName()));        
        StringBuilderutils.replaceAll(code, Metatags.VIEW_LOWERCASE, planguage.processTablename(table.getname()));
        StringBuilderutils.replaceAll(code, Metatags.VIEW_ULOWERCASE, planguage.processTablename(table.getName()));
    }    

    /**
     * replace foreign key tags with formatted names
     * @param code: program code
     * @param table: table metadata
     * @param fk: foreign key metadata
     */
    public static void processForeignkey(StringBuilder code, Xmlreader.Table table, Xmlreader.Table.Foreignkey fk) {
        processforeignkeytable(code, fk);
        new RepeatfkfieldsProcessor(planguage, code, table, fk).process();
    }    

    /**
     * replace foreign key table tags with formatted names
     * foreign key table = table referred to in a foreign key
     * @param code: program code
     * @param fk: foreign key metadata
     */
    public static void processforeignkeytable(StringBuilder code, Xmlreader.Table.Foreignkey fk) {
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_UNIQUENAME_LOWERCASE, planguage.processTablename(fk.getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_UNIQUENAME_ULOWERCASE, planguage.processTablename(fk.getUniquename()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_UNIQUENAME_ORIGINAL, fk.getuniquename());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_JAVANAME, planguage.processTablename(fk.getjavaname()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_LOWERCASE, planguage.processTablename(fk.getPrimarykeyTable().getname()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_ULOWERCASE, planguage.processTablename(fk.getPrimarykeyTable().getName()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_ORIGINAL, fk.getPrimarykeyTable().getOriginalname());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_JAVANAME_LOWERCASE, planguage.processTablename(fk.getpkjavaname()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_JAVANAME_ULOWERCASE, planguage.processTablename(fk.getPkjavaname()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_JAVANAMEPK_LOWERCASE, planguage.processTablename(fk.getpkjavanamePK()));
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYTABLE_JAVANAMEPK_ULOWERCASE, planguage.processTablename(fk.getPkjavanamePK()));
    }
    
    /**
     * replace column related tags to formatted code
     * update codeformatter settings
     * @param codeformatter: Codeformatter for the programcode fragment
     * @param code: program code
     * @param column: column metadata
     */
    public static void processColumn(Codeformatter codeformatter, StringBuilder code, Xmlreader.Table.Column column) {
        processConstraint(codeformatter, code, Metatags.ISFILECONSTRAINT, column.getIsfileondisk());
        processConstraint(codeformatter, code, Metatags.ISNOTNULLABLECONSTRAINT, !column.getIsnullable());
        processFieldtypecontraint(codeformatter, code, column);
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_LOWERCASE, column.getname());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_JAVANAME, column.getvarname());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_ULOWERCASE, column.getName());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_UPPERCASE, column.getNAME());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_ORIGINAL, column.getOriginalname());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_TYPE, column.getVariabletype(planguage));
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_SQLTYPE, column.getVariabletypesql(planguage));
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_SIZE, String.valueOf(column.getSize()));
        StringBuilder getcolumndbfunction = new StringBuilder(column.getDBfunction(planguage));
        StringBuilderutils.replaceAll(getcolumndbfunction, Metatags.COLUMN_LOWERCASE, column.getname());
        StringBuilderutils.replaceAll(getcolumndbfunction, Metatags.COLUMN_ORIGINAL, column.getOriginalname());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_GETDBFUNCTION, getcolumndbfunction.toString());
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_DBCAST, column.getDBVariabletype(planguage));
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_CUSTOMTYPE, column.getCustomVariabletype(planguage));
        StringBuilderutils.replaceAll(code, Metatags.COLUMN_DBPOSITION, String.valueOf(column.getOrdinalposition()));
        StringBuilderutils.replaceAll(code, Metatags.SQL_CONVERTOR_CLASS, planguage.getSQLconvertorclass());
    }    

    /**
     * replace foreign key column with formatted code
     * @param code: program code
     * @param fkcolumn: foreign key column
     */
    public static void processForeignkeycolumn(StringBuilder code, Xmlreader.Table.Foreignkey.Column fkcolumn) {
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_COLUMN_LOWERCASE, fkcolumn.getforeigncolumn());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_COLUMN_ULOWERCASE, fkcolumn.getForeigncolumn());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_COLUMN_ORIGINAL, fkcolumn.getOriginalforeigncolumn());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYKCOLUMN_LOWERCASE, fkcolumn.getprimarycolumn());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYCOLUMN_ULOWERCASE, fkcolumn.getPrimarycolumn());
        StringBuilderutils.replaceAll(code, Metatags.FOREIGNKEY_PRIMARYKEYCOLUMN_ORIGINAL, fkcolumn.getOriginalprimarycolumn());
    }    
    
    /**
     * replace external foreign key tags with formatted code
     * external foreign key: foreign key used in an external table with reference to the active table
     * @param code: program code
     * @param efk: external foreign key
     */
    public static void processExternalforeignkey(StringBuilder code, Xmlreader.Table.ExternalForeignkey efk) {
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_LOWERCASE, planguage.processTablename(efk.getFk().getforeignkeytable()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_ULOWERCASE, planguage.processTablename(efk.getFk().getForeignkeytable()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_ORIGINAL, efk.getFk().getOriginalforeignkeytable());
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_FOREIGNKEYUNIQUENAME_LOWERCASE, planguage.processTablename(efk.getFk().getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_FOREINGKEYUNIQUENAME_ULOWERCASE, planguage.processTablename(efk.getFk().getUniquename()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_UNIQUENAME_LOWERCASE, planguage.processTablename(efk.getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_UNIQUENAME_ULOWERCASE, planguage.processTablename(efk.getUniquename()));
        processForeignkey(code, efk.getFk().getForeignkeyTable(), efk.getFk());
    }    
        
    /**
     * replace relational foreign key tags with formatted code
     * relational foreign key = external foreign key that is part of a primary key in a relational table
     * @param code: program code
     * @param efk: external foreign key
     * @param relfk: relational foreign key
     */
    public static void processRelationalforeignkey(StringBuilder code, Xmlreader.Table.ExternalForeignkey efk, Xmlreader.Table.RelationalForeignkey relfk) {
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_LOWERCASE, planguage.processTablename(relfk.getFk().getprimarykeytable()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_ULOWERCASE, planguage.processTablename(relfk.getFk().getPrimarykeytable()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_TABLE_ORIGINAL, relfk.getFk().getOriginalprimarykeytable());
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_FOREIGNKEYUNIQUENAME_LOWERCASE, planguage.processTablename(efk.getFk().getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.EXERTNALFOREIGNKEY_FOREINGKEYUNIQUENAME_ULOWERCASE, planguage.processTablename(efk.getFk().getUniquename()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_TABLE_LOWERCASE, planguage.processTablename(relfk.getFk().getforeignkeytable()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_TABLE_ULOWERCASE, planguage.processTablename(relfk.getFk().getForeignkeytable()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_TABLE_ORIGINAL, relfk.getFk().getOriginalforeignkeytable());
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_FOREIGNKEYUNIQUENAME_LOWERCASE, planguage.processTablename(relfk.getFk().getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_FOREIGNKEYUNIQUENAME_ULOWERCASE, planguage.processTablename(relfk.getFk().getUniquename()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_UNIQUENAME_LOWERCASE, planguage.processTablename(relfk.getuniquename()));
        StringBuilderutils.replaceAll(code, Metatags.RELATIONALFOREIGNKEY_UNIQUENAME_ULOWERCASE, planguage.processTablename(relfk.getUniquename()));
    }    

    /**
     * replace field type constraints with formatted code
     * @param codeformatter: Codeformatter for the programcode fragment
     * @param code: program code
     * @param column: column metadata
     */
    public static void processFieldtypecontraint(Codeformatter codeformatter, StringBuilder code, Xmlreader.Table.Column column) {
        //remove endswithnewline
        //except when a comma is found, in that case leave as configured previously
        if(StringBuilderutils.endsWith(code, Metatags.IFFIELDTYPECONSTRAINT) && !codeformatter.isEndswithcomma()) codeformatter.setEndswithnewline(false);
        //:iffieldtype: block
        String iffieldtypeblock = null;
        int ifstart = code.indexOf(Metatags.IFFIELDTYPECONSTRAINT);
        int ifend = code.indexOf(Metatags.IFFIELDTYPECONSTRAINT, ifstart+1) + Metatags.IFFIELDTYPECONSTRAINT.length();
        String coltype = new StringBuilder(Metatags.TAGSYM).append(column.getVariabletype(planguage)).append(Metatags.TAGSYM).toString();
        int replacementstart;
        int replacementend;
        String replacement;
        while(ifstart>-1 && ifend>-1) {
            iffieldtypeblock = code.substring(ifstart + Metatags.IFFIELDTYPECONSTRAINT.length(), ifend - Metatags.IFFIELDTYPECONSTRAINT.length());
            if(coltype.equals(Metatags.EMPTYTAGSYM)) {
                // "::" means column.getVariabletype(planguage) returned nothing, no definition in "language".xml for this fieldtype
                replacementstart = -1;
                replacementend = -1;
            } else {
                replacementstart = iffieldtypeblock.indexOf(coltype);
                if(replacementstart>-1) replacementstart += coltype.length();
                replacementend = iffieldtypeblock.indexOf(coltype, replacementstart);
            }
            //if type is not found, use ":customfieldtype:"
            if(column.isCustomVariable(planguage) && 
                    (replacementstart==-1 || replacementend==-1)) {
                replacementstart = iffieldtypeblock.indexOf(Metatags.CUSTOMFIELDTYPE);
                if(replacementstart>-1) replacementstart += Metatags.CUSTOMFIELDTYPE.length();
                replacementend = iffieldtypeblock.indexOf(Metatags.CUSTOMFIELDTYPE, replacementstart+1);
            }
            //if type is not found, use ":other:"
            if(replacementstart==-1 || replacementend==-1) {
                replacementstart = iffieldtypeblock.indexOf(Metatags.IFFIELDOTHER);
                if(replacementstart>-1) replacementstart += Metatags.IFFIELDOTHER.length();
                replacementend = iffieldtypeblock.indexOf(Metatags.IFFIELDOTHER, replacementstart+1);
            }
            if(code.length()>ifend && code.substring(ifend, ifend+1).equals(Metatags.RETURNSEPARATOR)) {
                ifend++;
            }
            if(replacementstart>-1 && replacementend>=replacementstart) {
                replacement = iffieldtypeblock.substring(replacementstart, replacementend);
                if(replacement.startsWith(Metatags.RETURNSEPARATOR)) replacement = replacement.substring(1);
                
                code.replace(ifstart, ifend, replacement);
                //code = new StringBuilder(block1).append(replacement).append(block2);
            } else {
                
                code.replace(ifstart, ifend, "");
                //code = new StringBuilder(block1).append(block2);
            }
                
            ifstart = code.indexOf(Metatags.IFFIELDTYPECONSTRAINT);
            ifend = code.indexOf(Metatags.IFFIELDTYPECONSTRAINT, ifstart+1) + Metatags.IFFIELDTYPECONSTRAINT.length();
        }
    }
    
    /**
     * replace general constraints with formatted code
     * @param codeformatter: Codeformatter for the programcode fragment
     * @param code: program code
     * @param contrainttag: Metatag
     * @param allow: flag to indicate if the codeblock between de Metatags must be used
 this condition is configured based on other parameters
     */
    public static void processConstraint(Codeformatter codeformatter, StringBuilder code, String contrainttag, boolean allow) {
        if(StringBuilderutils.endsWith(code, contrainttag)) codeformatter.setEndswithnewline(false);
        //constraint block
        int ifstart = code.indexOf(contrainttag);
        int ifend = code.indexOf(contrainttag, ifstart+1) + contrainttag.length();
        int replacementstart;
        int replacementend;
        String replacement;
        while(ifstart>-1 && ifend>-1) {
            if(allow) {
                replacementstart = ifstart + contrainttag.length();
                replacementend = ifend - contrainttag.length();

                replacement = code.substring(replacementstart, replacementend);
                if(replacement.startsWith(Metatags.RETURNSEPARATOR)) replacement = replacement.substring(1);
            } else {
                replacement = "";
            }
            if(code.length()>ifend && code.substring(ifend, ifend+1).equals(Metatags.RETURNSEPARATOR)) {
                ifend++;
            }

            code.replace(ifstart, ifend, replacement);
            //code = new StringBuilder(block1).append(replacement).append(block2);
                
            ifstart = code.indexOf(contrainttag);
            ifend = code.indexOf(contrainttag, ifstart+1) + contrainttag.length();
        }
    }

    /**
     * replace COUNTERTAG with an increasing counter starting from 0
     * @param code: program code
     */
    public static void processCounter(StringBuilder code) {
        int counter = 0;
        int start = code.indexOf(Metatags.COUNTERTAG);
        String block1, block2;
        while(start>-1) {
            code.replace(start, start + Metatags.COUNTERTAG.length(), "" + counter);
            counter++;
            start = code.indexOf(Metatags.COUNTERTAG);
        }
    }
}
