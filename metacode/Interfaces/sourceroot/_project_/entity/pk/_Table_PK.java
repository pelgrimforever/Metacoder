/*
 * :Table:PK.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.entity.pk;

import data.interfaces.db.EntityPK;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.I:Table:;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import db.SQLparameters;
import db.Entityvalues;

/**
 * Primarykey class :Table:PK
 * 
 * Attributes: primary key fields and foreign keys
 * Methods: conversion to and from string for use in GUI
 * @author Franky Laseure
 */
public class :Table:PK implements I:Table:PK {

:repeatforeignkeys:
:inpk:
    private I:Pktable:PK :uniquename:PK = new :Pktable:PK();
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
    private :columntype: :columnjavaname:;
:notfk:
:repeatpkfields:
  
    /** 
     * Constructor
     * Creates an empty :Table:PK
     */
    public :Table:PK() {
    }

    /**
     * Constructor
     * build an empty :Table:PK with initialized field values
     */
    public :Table:PK(:repeatpkfields::columntype: :columnjavaname::,::repeatpkfields:) {
:repeatforeignkeys:
:inpk:
        this.:uniquename:PK = new :Pktable:PK(:repeatforeignkeyfields::foreigncolumn::,::repeatforeignkeyfields:);
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
        this.:columnjavaname: = :columnjavaname:;
:notfk:
:repeatpkfields:
    }

    /**
     * 
     * @return primarykey fields (fieldname, value) as a SQLparameters object
     */
    public SQLparameters getSQLprimarykey() {
        Object[][] keyfields = { 
:repeatpkfields:
:infk:
            {":table_o:.:foreigncolumn_o:", :uniquename:PK.get:Primarycolumn:()}
:infk:
:notfk:
            {":table_o:.:column_o:", :columnjavaname:}
:notfk:
:,:
:repeatpkfields:
        };
        return new SQLparameters(keyfields);
    }

    /**
     * 
     * @return primarykey fields (fieldreference, value) as Entityvalues
     */
    public Entityvalues getPrimarykeyvalues() {
        Object[][] keyfields = { 
:repeatpkfields:
:infk:
            {I:Table:.:COLUMN:, :uniquename:PK.get:Primarycolumn:()}
:infk:
:notfk:
            {I:Table:.:COLUMN:, :columnjavaname:}
:notfk:
:,:
:repeatpkfields:
        };
        return new Entityvalues(keyfields);
    }

:repeatforeignkeys:
:inpk:
    /**
     * 
     * @return foreign key :pktablejavanamePK:, instance of I:Pktable:PK
     */
    public I:Pktable:PK get:Uniquename:PK() {
        return this.:uniquename:PK;
    }

    /**
     * set foreign key :uniquename:PK
     * @param :pktable:PK: instance of I:Pktable:PK
     */
    public void set:Uniquename:PK(I:Pktable:PK :pktable:PK) {
        this.:uniquename:PK = :pktable:PK;
    }

:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Foreigncolumn:() {
        return this.:uniquename:PK.get:Primarycolumn:();
    }

    /**
     * set :columnjavaname: value
     * @param :columnjavaname:: new value
     */
    public void set:Foreigncolumn:(:columntype: :foreigncolumn:) {
        this.:uniquename:PK.set:Primarycolumn:(:foreigncolumn:);
    }

:infk:
:notfk:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Column:() {
        return this.:columnjavaname:;
    }

    /**
     * set :column: value
     * @param :columnjavaname:: new value
     */
    public void set:Column:(:columntype: :columnjavaname:) {
        this.:columnjavaname: = :columnjavaname:;
    }

:notfk:
:repeatpkfields:
    /**
     * 
     * @return primary key in one formatted string
     */
    public String getKeystring() {
        String key = "";
:repeatpkfields:
:iffieldtype:
:java.lang.String:
        if(get:Column:()!=null) key += get:Column:().length() + "_" + get:Column:();
:java.lang.String:
:java.sql.Date:
        if(get:Column:()!=null) key += get:Column:().getTime();
:java.sql.Date:
:java.sql.Time:
        if(get:Column:()!=null) key += get:Column:().getTime();
:java.sql.Time:
:java.sql.Timestamp:
        if(get:Column:()!=null) key += get:Column:().getTime();
:java.sql.Timestamp:
:boolean:
        key += get:Column:().toString();
:boolean:
:other:
        key += get:Column:();
:other:
:iffieldtype:
:separator:
        key += "_";
:separator:
:repeatpkfields:
        return key;
    }

    /**
     * 
     * @param keystring: formated string from getKeystring() method
     * @return :Table:PK constructed from keystring
     */
    public static :Table:PK getKey(String keystring) {
        if(keystring==null || keystring.length()==0) return null;
        else {
            String keys = keystring;
            int keylength = 0;
:repeatpkfields:
:iffieldtype:
:java.lang.String:
            keylength = Integer.valueOf(keys.substring(0, keys.indexOf("_")));
            keys.substring(keys.indexOf("_")+1);
            String :columnjavaname: = keys.substring(0, keylength);
:java.lang.String:
:other:
            if(keys.indexOf("_")==-1) {
                keylength = keys.length();
            } else {
                keylength = Integer.valueOf(keys.substring(0, keys.indexOf("_")).length());
            }
:other:
:iffieldtype:
:iffieldtype:
:java.lang.String::java.lang.String:
:java.sql.Date:
            :columntype: :columnjavaname: = new Date(Long.valueOf(keys.substring(0, keylength)));
:java.sql.Date:
:java.sql.Time:
            :columntype: :columnjavaname: = new Time(Long.valueOf(keys.substring(0, keylength)));
:java.sql.Time:
:java.sql.Timestamp:
            :columntype: :columnjavaname: = new Timestamp(Long.valueOf(keys.substring(0, keylength)));
:java.sql.Timestamp:
:int:
            :columntype: :columnjavaname: = Integer.valueOf(keys.substring(0, keylength));
:int:
:long:
            :columntype: :columnjavaname: = Long.valueOf(keys.substring(0, keylength));
:long:
:double:
            :columntype: :columnjavaname: = Double.parseDouble(keys.substring(0, keylength));
:double:
:float:
            :columntype: :columnjavaname: = Float.parseFloat(keys.substring(0, keylength));
:float:
:boolean:
            :columntype: :columnjavaname: = Boolean.valueOf(keys.substring(0, keylength));
:boolean:
:other:
            :columntype: :columnjavaname: = (String)(keys.substring(0, keylength));
:other:
:iffieldtype:
:separator:
            keys = keys.substring(keylength+1);
:separator:
:repeatpkfields:
            return new :Table:PK(:repeatpkfields::columnjavaname::,::repeatpkfields:);
        }
    }

    /**
     * compare this primary key with second primary key
     * @param :table:PK2: :Table:PK instance
     * @return true if all fields and foreign keys are equal
     */
    public boolean equals(I:Table:PK :table:PK2) {
        boolean isequal = :table:PK2!=null;
        if(isequal) {
:repeatforeignkeys:
:inpk:
            isequal = isequal && this.:uniquename:PK.equals(:table:PK2.get:Uniquename:PK());
:inpk:
:repeatforeignkeys:
:repeatpkfields:
:notfk:
:iffieldtype:
:java.lang.String:
            isequal = isequal && this.:columnjavaname:.equals(:table:PK2.get:Column:());
:java.lang.String:
:java.sql.Date:
            isequal = isequal && this.:columnjavaname:.equals(:table:PK2.get:Column:());
:java.sql.Date:
:java.sql.Time:
            isequal = isequal && this.:columnjavaname:.equals(:table:PK2.get:Column:());
:java.sql.Time:
:java.sql.Timestamp:
            isequal = isequal && this.:columnjavaname:.equals(:table:PK2.get:Column:());
:java.sql.Timestamp:
:other:
            isequal = isequal && this.:columnjavaname: == :table:PK2.get:Column:();
:other:
:iffieldtype:
:notfk:
:repeatpkfields:
        }
        return isequal;
    }
}
