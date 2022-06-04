/*
 * :Table:data.java
 *
 * Created on Jan 01, 2013, 13:34 PM
 * Generated on :metacoder_date:
 *
 */
package :project:.datahandlers.data;

import :project:.datahandlers.data.base.:Table:data_base;
import :project:.interfaces.logicview.I:Table:;
import :project:.logicview.:Table:;
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

    public void set:Table:data(I:Table: :table:) {
        this.source:table: = :table:;
        reset();
    }
    
    public void setCustomfields() {
    }        

    public void reset() {
        reset_all();
        //set custom fields of this subclass in a different function,
        //they will not be initialised until the constructor of the superclass is completed
        //the reset() function, called in set:Table:data, is called in the superclass constructor
    }
}

