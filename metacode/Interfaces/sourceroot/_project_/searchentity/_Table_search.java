/*
 * :Table:search.java
 *
 * Created on Feb 29, 2012, 18:15 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.searchentity;

import :project:.interfaces.searchentity.I:Table:search;
import :project:.interfaces.entity.pk.*;
import data.interfaces.db.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.searchentity.*;
import :project:.logicentity.:Table:;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Search class for :Table: table
 * construct sql where part and parameter array from search parameters
 * @author Franky Laseure
 */
public class :Table:search extends Tablesearch implements I:Table:search {

:repeatpkfields:
:notfk:
:iffieldtype:
:java.sql.Date:
    Datesearch :columnjavaname: = new Datesearch(":table_o:.:column_o:");
:java.sql.Date: 
:java.sql.Time:
    Timesearch :columnjavaname: = new Timesearch(":table_o:.:column_o:");
:java.sql.Time: 
:java.sql.Timestamp:
    Timesearch :columnjavaname: = new Timesearch(":table_o:.:column_o:");
:java.sql.Timestamp: 
:byte:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:byte:
:int:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:int:
:long:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:long: 
:double:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:double:
:float:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:float: 
:boolean:
    Booleansearch :columnjavaname: = new Booleansearch(":table_o:.:column_o:");
:boolean: 
:other:
    Stringsearch :columnjavaname: = new Stringsearch(":table_o:.:column_o:");
:other:
:iffieldtype:
:notfk:
:repeatpkfields:
:repeatfields:
:iffieldtype:
:java.sql.Date:
    Datesearch :columnjavaname: = new Datesearch(":table_o:.:column_o:");
:java.sql.Date: 
:java.sql.Time:
    Timesearch :columnjavaname: = new Timesearch(":table_o:.:column_o:");
:java.sql.Time: 
:java.sql.Timestamp:
    Timesearch :columnjavaname: = new Timesearch(":table_o:.:column_o:");
:java.sql.Timestamp: 
:byte:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:byte:
:int:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:int:
:long:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:long: 
:double:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:double: 
:float:
    Numbersearch :columnjavaname: = new Numbersearch(":table_o:.:column_o:");
:float: 
:boolean:
    Booleansearch :columnjavaname: = new Booleansearch(":table_o:.:column_o:");
:boolean: 
:other:
    Stringsearch :columnjavaname: = new Stringsearch(":table_o:.:column_o:");
:other:
:iffieldtype:
:repeatfields:
//foreign keys
:repeatforeignkeys:
    Foreignkeysearch :uniquename:searcher = new Foreignkeysearch(":pktable_o:", I:Table:.:uniquename:PKfields, I:Table:.:uniquename:FKfields);
:repeatforeignkeys:
//external foreign keys
:repeatexternalforeignkeys:
:ifforeignkey:
    //foreign key
    Primarykeysearch :uniquename:searcher = new Primarykeysearch(":extablename_o:", I:Extablename:.:exfkuniquename:PKfields, I:Extablename:.:exfkuniquename:FKfields);
:ifforeignkey:
:ifrelational:
    //relational key
    Relationalkeysearch rel:uniquename:searcher = new Relationalkeysearch(":reltablename_o:", I:Reltablename:.:exfkuniquename:PKfields, I:Reltablename:.:exfkuniquename:FKfields, ":extablename:", I:Reltablename:.:relfkuniquename:PKfields, I:Reltablename:.:relfkuniquename:FKfields);
:ifrelational:
:repeatexternalforeignkeys:

    /**
     * @return tablename
     */
    public String getTable() {
        return :Table:.table;
    }

    /**
     * Constructor
     * add IFieldsearcher classes for all relevant fields
     */
    public :Table:search() {
        setFieldsearchers();
    }

    /**
     * Constructor
     * add IFieldsearcher classes for all relevant fields
     * set andor parameter
     * @param andor: containts AND or OR contant, indicates all conditions must apply or only one
     */
    public :Table:search(byte andor) {
        super(andor);
        setFieldsearchers();
    }

    /**
     * add IFieldsearcher classes for all relevant fields
     */
    private void setFieldsearchers() {
:repeatpkfields:
:notfk:
        addFieldsearcher(:columnjavaname:);
:notfk:
:repeatpkfields:
:repeatfields:
        addFieldsearcher(:columnjavaname:);
:repeatfields:
:repeatforeignkeys:
        addKeysearcher(:uniquename:searcher);
:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
        addKeysearcher(:uniquename:searcher);
:ifforeignkey:
:ifrelational:
        addKeysearcher(rel:uniquename:searcher);
:ifrelational:
:repeatexternalforeignkeys:
    }

    /**
     * add a primary key instance to search for
     * @param :table:PK: :Table: primery key
     */
    public void addPrimarykey(I:Table:PK :table:PK) {
        super.addPrimarykey(:table:PK);
    }

:repeatpkfields:
:notfk:
:iffieldtype:
:java.sql.Date:
    /**
     * add Date search values for field :column:, default OR operator is used
     * @param values: Array of date search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Date[] values, byte[] operators) {
        addDatevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Date search values for field :column:
     * @param values: Array of date search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Date[] values, byte[] operators, byte andor) {
        addDatevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Date: 
:java.sql.Time:
    /**
     * add Time search values for field :column:, default OR operator is used
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Time[] values, byte[] operators) {
        addTimevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Time search values for field :column:
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Time[] values, byte[] operators, byte andor) {
        addTimevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Time: 
:java.sql.Timestamp:
    /**
     * add Timestamp search values for field :column:, default OR operator is used
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Timestamp[] values, byte[] operators) {
        addTimevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Timestamp search values for field :column:
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Timestamp[] values, byte[] operators, byte andor) {
        addTimevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Timestamp:
:byte:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(byte[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(byte[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:byte: 
:int:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(long[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(long[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:int: 
:long:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(long[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(long[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:long: 
:double:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:double: 
:float:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:float: 
:boolean:
    /**
     * add Boolean search values for field :column:
     * @param value: true or false
     */
    public void :columnjavaname:(Boolean value) {
        addBooleanvalue(:columnjavaname:, value);
    }
    
:boolean: 
:other:
    /**
     * add String search values for field :column:, default OR and LIKE operators are used
     * @param values: Array of String search values
     */
    public void :columnjavaname:(String[] values) {
        addStringvalues(:columnjavaname:, values);
    }
    
    /**
     * add String search values for field :column:
     * @param values: Array of String search values
     * @param andor; AND/OR constant
     * @param compare: EQUAL/LIKE constant
     */
    public void :columnjavaname:(String[] values, byte compare, byte andor) {
        addStringvalues(:columnjavaname:, values);
        :columnjavaname:.setCompareoperator(compare);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:other:
:iffieldtype:
:notfk:
:repeatpkfields:
:repeatfields:
:iffieldtype:
:java.sql.Date:
    /**
     * add Date search values for field :column:, default OR operator is used
     * @param values: Array of date search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Date[] values, byte[] operators) {
        addDatevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Date search values for field :column:
     * @param values: Array of date search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Date[] values, byte[] operators, byte andor) {
        addDatevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Date: 
:java.sql.Time:
    /**
     * add Time search values for field :column:, default OR operator is used
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Time[] values, byte[] operators) {
        addTimevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Time search values for field :column:
     * @param values: Array of time search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Time[] values, byte[] operators, byte andor) {
        addTimevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Time: 
:java.sql.Timestamp:
    /**
     * add Timestamp search values for field :column:, default OR operator is used
     * @param values: Array of timestamp search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Timestamp[] values, byte[] operators) {
        addTimevalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Timestamp search values for field :column:
     * @param values: Array of timestamp search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Timestamp[] values, byte[] operators, byte andor) {
        addTimevalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:java.sql.Timestamp:
:byte:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(byte[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(byte[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:byte: 
:int:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:int: 
:long:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:long: 
:double:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:double: 
:float:
    /**
     * add Numeric search values for field :column:, default OR operator is used
     * @param values: Array of numeric search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     */
    public void :columnjavaname:(Double[] values, byte[] operators) {
        addNumbervalues(:columnjavaname:, values, operators);
    }
    
    /**
     * add Numeric search values for field :column:
     * @param values: Array of String search values
     * @param operators: Array of byte contants for comparison (= < <= > >=)
     * @param andor; AND/OR constant
     */
    public void :columnjavaname:(Double[] values, byte[] operators, byte andor) {
        addNumbervalues(:columnjavaname:, values, operators);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:float: 
:boolean:
    /**
     * add Boolean search values for field :column:
     * @param value: true or false
     */
    public void :columnjavaname:(Boolean value) {
        addBooleanvalue(:columnjavaname:, value);
    }
    
:boolean: 
:other:
    /**
     * add String search values for field :column:, default OR and LIKE operators are used
     * @param values: Array of String search values
     */
    public void :columnjavaname:(String[] values) {
        addStringvalues(:columnjavaname:, values);
    }
    
    /**
     * add String search values for field :column:
     * @param values: Array of String search values
     * @param andor; AND/OR constant
     * @param compare: EQUAL/LIKE constant
     */
    public void :columnjavaname:(String[] values, byte compare, byte andor) {
        addStringvalues(:columnjavaname:, values);
        :columnjavaname:.setCompareoperator(compare);
        :columnjavaname:.setAndoroperator(andor);
    }
    
:other:
:iffieldtype:
:repeatfields:
:repeatforeignkeys:
    /**
     * set foreign key subsearch :uniquename: I:Pktable:search
     * @param :pktable:search: I:Pktable:search
     */
    public void :uniquename:(I:Pktable:search :pktable:search) {
        :uniquename:searcher.setTablesearch(:pktable:search);
    }
    
    /**
     * get foreign key subsearch :uniquename: I:Pktable:search
     * @return Tablesearch for :Table:
     */
    public I:Pktable:search get:Uniquename:search() {
        if(:uniquename:searcher.used()) {
            return (I:Pktable:search):uniquename:searcher.getTablesearches().get(0);
        } else {
            return null;
        }
    }

    /**
     * force to return inner join statement
     * ignore if :uniquename:searcher is not used
     * @return inner join statement
     */
    public String get:Uniquename:Innerjoin() {
        return :uniquename:searcher.getInnerjoin();
    }

:repeatforeignkeys:
:repeatexternalforeignkeys:
:ifforeignkey:
    /**
     * set external key - foreign key subsearch I:Extablename:search
     * @param :extablename:search: I:Extablename:search
     */
    public void :uniquename:(I:Extablename:search :extablename:search) {
        :uniquename:searcher.setTablesearch(:extablename:search);
    }
    
    /**
     * get external key - foreign key subsearch I:Extablename:search
     * @return Tablesearch for I:Extablename:search
     */
    public I:Extablename:search get:Uniquename:search() {
        if(:uniquename:searcher.used()) {
            return (I:Extablename:search):uniquename:searcher.getTablesearches().get(0);
        } else {
            return null;
        }
    }

:ifforeignkey:
:ifrelational:
    /**
     * set external key - relational subsearch :extablename: tablesearch
     * @param :extablename:search: I:Extablename:search
     */
    public void rel:uniquename:(I:Extablename:search :extablename:search) {
        rel:uniquename:searcher.setTablesearch(:extablename:search);
    }
    
    /**
     * get external key - relational subsearch I:Extablename:search
     * @return Tablesearch for I:Extablename:search
     */
    public I:Extablename:search getRel:Uniquename:search() {
        if(rel:uniquename:searcher.used()) {
            return (I:Extablename:search)rel:uniquename:searcher.getTablesearches().get(0);
        } else {
            return null;
        }
    }

:ifrelational:
:repeatexternalforeignkeys:
}
