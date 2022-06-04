/*
 * I:Table:search.java
 *
 * Created on Feb 29, 2012, 18:15 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.searchentity;

import data.interfaces.db.Tablesearcher;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import :project:.interfaces.searchentity.*;

/**
 * Search Interface for :Table: table
 * construct sql where part and parameter array from search parameters
 * @author Franky Laseure
 */
public interface I:Table:search extends Tablesearcher {

:repeatfields:
:iffieldtype:
:java.sql.Date:
	/**
	 * add Date search values for field :column:, default OR operator is used
	 * @param values: Array of date search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Date[] values, byte[] operators);
	
	/**
	 * add Date search values for field :column:
	 * @param values: Array of date search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Date[] values, byte[] operators, byte andor);
	
:java.sql.Date: 
:java.sql.Time:
	/**
	 * add Time search values for field :column:, default OR operator is used
	 * @param values: Array of time search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Time[] values, byte[] operators);
	
	/**
	 * add Time search values for field :column:
	 * @param values: Array of time search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Time[] values, byte[] operators, byte andor);
	
:java.sql.Time: 
:java.sql.Timestamp:
	/**
	 * add Timestamp search values for field :column:, default OR operator is used
	 * @param values: Array of timestamp search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Timestamp[] values, byte[] operators);
	
	/**
	 * add Timestamp search values for field :column:
	 * @param values: Array of timestamp search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Timestamp[] values, byte[] operators, byte andor);
	
:java.sql.Timestamp:
:byte:
	/**
	 * add Numeric search values for field :column:, default OR operator is used
	 * @param values: Array of numeric search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(byte[] values, byte[] operators);
	
	/**
	 * add Numeric search values for field :column:
	 * @param values: Array of String search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(byte[] values, byte[] operators, byte andor);
	
:byte: 
:int:
	/**
	 * add Numeric search values for field :column:, default OR operator is used
	 * @param values: Array of numeric search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Double[] values, byte[] operators);
	
	/**
	 * add Numeric search values for field :column:
	 * @param values: Array of String search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Double[] values, byte[] operators, byte andor);
	
:int: 
:long:
	/**
	 * add Numeric search values for field :column:, default OR operator is used
	 * @param values: Array of numeric search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Double[] values, byte[] operators);
	
	/**
	 * add Numeric search values for field :column:
	 * @param values: Array of String search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Double[] values, byte[] operators, byte andor);
	
:long: 
:double:
	/**
	 * add Numeric search values for field :column:, default OR operator is used
	 * @param values: Array of numeric search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Double[] values, byte[] operators);
	
	/**
	 * add Numeric search values for field :column:
	 * @param values: Array of String search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Double[] values, byte[] operators, byte andor);
	
:double: 
:float:
	/**
	 * add Numeric search values for field :column:, default OR operator is used
	 * @param values: Array of numeric search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 */
	public void :columnjavaname:(Double[] values, byte[] operators);
	
	/**
	 * add Numeric search values for field :column:
	 * @param values: Array of String search values
	 * @param operators: Array of byte contants for comparison (= < <= > >=)
	 * @param andor; AND/OR constant
	 */
	public void :columnjavaname:(Double[] values, byte[] operators, byte andor);
	
:float: 
:boolean:
	/**
	 * add Boolean search values for field :column:
	 * @param value: true or false
	 */
	public void :columnjavaname:(Boolean value);
	
:boolean: 
:other:
	/**
	 * add String search values for field :column:, default OR and LIKE operators are used
	 * @param values: Array of String search values
	 */
	public void :columnjavaname:(String[] values);
	
	/**
	 * add String search values for field :column:
	 * @param values: Array of String search values
	 * @param andor; AND/OR constant
	 * @param compare: EQUAL/LIKE constant
	 */
	public void :columnjavaname:(String[] values, byte compare, byte andor);
	
:other:
:iffieldtype:
:repeatfields:
}
