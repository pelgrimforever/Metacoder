/*
 * :Table:data.java
 *
 * Created on Jan 01, 2013, 13:34 PM
 * Generated on :metacoder_date:
 *
 */
package :project:.datahandlers.data;

import :project:.datahandlers.data.base.:Table:data_base;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.I:Table:PK;
import :project:.interfaces.logicentity.I:Table:;
import :project:.logicentity.:Table:;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import text.Conversion;

/**
 *
 * @author Franky Laseure
 */
public class :Table:data extends :Table:data_base {
//Metacoder: NO AUTHOMATIC UPDATE
    
    public :Table:data(I:Table: :table:) {
        super(:table:);
        setCustomfields();
    }

    public static :Table:data create() {
        :Table:data :table:data = :Table:data_base.create();
        return :table:data;
    }
    
    @Override
    public void set:Table:data(I:Table: :table:) {
        this.source:table: = :table:;
        reset();
    }
    
    @Override
    public void reset() {
        reset_all();
        //set custom fields of this subclass in a different function,
        //they will not be initialised until the constructor of the superclass is completed
        //the reset() function, called in set:Table:data, is called in the superclass constructor
    }
    
    public void setCustomfields() {
    }        

    @Override
    public void update() {
        update_all();
    }
}

