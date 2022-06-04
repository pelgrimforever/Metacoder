/*
 * I:Table:PK.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.entity.pk;

import java.io.Serializable;
import data.interfaces.db.EntityPK;
import db.SQLparameters;

/**
 * Primary Key class :Table:PK for entity class :Table:
 * 
 * Attributes: primary key fields and foreign keys
 * Methods: conversion to and from string for use in GUI
 * @author Franky Laseure
 */
public interface I:Table:PK extends EntityPK, Serializable {

:repeatforeignkeys:
:inpk:
    /**
     * 
     * @return foreign key :pktable:PK, instance of I:Pktable:PK
     */
    public I:Pktable:PK get:Uniquename:PK();
    /**
     * set foreign key :Pktable:PK
     * @param :pktable:PK: instance of I:Pktable:PK
     */
    public void set:Uniquename:PK(I:Pktable:PK :pktable:PK);

:inpk:
:repeatforeignkeys:
:repeatpkfields:
:infk:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Foreigncolumn:();

    /**
     * set :columnjavaname: value
     * @param :columnjavaname:: new value
     */
    public void set:Foreigncolumn:(:columntype: :foreigncolumn:);

:infk:
:notfk:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Column:();

    /**
     * set :column: value
     * @param :columnjavaname:: new value
     */
    public void set:Column:(:columntype: :columnjavaname:);

:notfk:
:repeatpkfields:

    /**
     * compare this primary key with second primary key
     * @param :table:PK2: :Table:PK instance
     * @return true if all fields and foreign keys are equal
     */
    public boolean equals(I:Table:PK :table:PK2);

}
