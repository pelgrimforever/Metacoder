/*
 * B:table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.BusinessObject.view;

import BusinessObject.BLview;
import db.SQLMapperFactory;
import data.gis.shape.*;
import db.SQLMapper_pgsql;
import :project:.conversion.entity.EM:table:;
import general.exception.*;
import java.util.ArrayList;
import :project:.logicview.:Table:;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import org.postgresql.geometric.PGpoint;
import org.postgis.PGgeometry;

/**
 * Business View class B:table:
 *
 * Superclass for manipulating data- and database objects
 * for View :Table: and direct related data
 * This class is overwritten each time the code generator runs
 * and is not meant to be changed
 *
 * @author Franky Laseure
 */
public abstract class B:table: extends BLview {

    /**
     * Constructor, sets :Table: as default Entity
     */
    public B:table:() {
        super(new :Table:(), new EM:table:());
    }

    /**
     * get all :Table: objects from database
     * @return ArrayList of :Table: objects
     * @throws DBException
     */
    public ArrayList<:Table:> get:Table:s() throws DBException {
        return getEntities(EM:table:.SQLSelectAll);
    }
}
