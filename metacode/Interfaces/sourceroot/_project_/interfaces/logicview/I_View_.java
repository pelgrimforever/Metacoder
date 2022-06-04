/*
 * I:Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.interfaces.logicview;

import data.gis.shape.*;
import data.interfaces.db.View;
import data.interfaces.db.LogicEntity;
import data.interfaces.db.Filedata;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * View Interface I:Table:
 *
 * extended attributes and methods possibility:
 * view level programming is done here
 *
 * @author Franky Laseure
 */
public interface I:Table: extends View {

:repeatfields:
    public static byte :COLUMN: = :columnposition:;
:repeatfields:

:repeatfields:
    public static final int SIZE_:COLUMN: = :columnsize:;
:repeatfields:
    public static final String[] fieldnames = { :repeatfields:":column_o:":,::repeatfields: };

//Custom code, do not change this line
//Put custom variables here
//Custom code, do not change this line

:repeatfields:
    /**
     * 
     * @return :columnjavaname: value
     */
    public :columntype: get:Column:();

:repeatfields:
    
//Custom code, do not change this line
//Put custom functions here

//Custom code, do not change this line
}
