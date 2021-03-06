/*
 * :Table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.logicentity;

import data.gis.shape.*;
import data.interfaces.db.LogicEntity;
import data.interfaces.db.Filedata;
import data.json.piJson;
import :project:.entity.pk.*;
import :project:.interfaces.entity.pk.I:Table:PK;
import :project:.interfaces.logicentity.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

/**
 * Entity class :Table:
 *
 * Attributes: Database independent SQL-construction strings adjusments
 * extended attributes and methods possibility:
 * table level programming is done here
 *
 * @author Franky Laseure
 */
public class :Table: extends :project:.entity.e:Table: implements I:Table: {

//Custom code, do not change this line
//Custom code, do not change this line

    /**
     * Constructor
     * Creates an empty :Table: entity
     */
    public :Table:() {
    }
    
    /**
     * Constructor
     * build an empty :Table: entity with initialized field values
     */
    public :Table:(:repeatpkfields::columntype: :columnjavaname::,::repeatpkfields:) {
        super(:repeatpkfields::columnjavaname::,::repeatpkfields:);
    }

    /**
     * Constructor
     * build an empty :Table: entity with initialized Primarykey parameter
     * @param :table:PK: :Table: Primarykey
     */
    public :Table:(:Table:PK :table:PK) {
        super(:table:PK);
    }

//Custom code, do not change this line
    /**
     * @return Entity string representation
     */
    public String toString() {
        return super.toString();
    }
//Custom code, do not change this line
}
