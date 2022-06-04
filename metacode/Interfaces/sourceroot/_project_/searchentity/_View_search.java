/*
 * :Table:search.java
 *
 * Created on Feb 29, 2012, 18:15 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.searchentity;

import :project:.interfaces.searchentity.I:Table:search;
import data.interfaces.db.*;
import :project:.interfaces.logicview.*;
import :project:.interfaces.searchentity.*;
import :project:.logicview.:Table:;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Search class for :Table: table
 * construct sql where part and parameter array from search parameters
 * @author Franky Laseure
 */
public class :Table:search extends Tablesearch implements I:Table:search {

:repeatfields:
:iffieldtype:
:java.sql.Date:
    Datesearch :columnjavaname: = new Datesearch(":table:.:column:");
:java.sql.Date: 
:java.sql.Time:
    Timesearch :columnjavaname: = new Timesearch(":table:.:column:");
:java.sql.Time: 
:java.sql.Timestamp:
    Timesearch :columnjavaname: = new Timesearch(":table:.:column:");
:java.sql.Timestamp: 
:byte:
    Numbersearch :columnjavaname: = new Numbersearch(":table:.:column:");
:byte:
:int:
    Numbersearch :columnjavaname: = new Numbersearch(":table:.:column:");
:int:
:long:
    Numbersearch :columnjavaname: = new Numbersearch(":table:.:column:");
:long: 
:double:
    Numbersearch :columnjavaname: = new Numbersearch(":table:.:column:");
:double: 
:float:
    Numbersearch :columnjavaname: = new Numbersearch(":table:.:column:");
:float: 
:boolean:
    Booleansearch :columnjavaname: = new Booleansearch(":table:.:column:");
:boolean: 
:other:
    Stringsearch :columnjavaname: = new Stringsearch(":table:.:column:");
:other:
:iffieldtype:
:repeatfields:

    /**
     * @return viewname
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
:repeatfields:
        addFieldsearcher(:columnjavaname:);
:repeatfields:
    }

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
}
