/*
 * EM:table:.java
 *
 * Created on Okt 8, 2021
 * Generated on :metacoder_date:
 *
 */
package :project:.conversion.entity;

import data.interfaces.db.LogicEntity;
import :project:.conversion.entity.def.EM:table:_default;
import :project:.logicentity.:Table:;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * EM:table:
 * Custom transformation from ResultSet to Logic Entity
 * @author Franky Laseure
 */
public class EM:table: extends EM:table:_default {
//Metacoder: NO AUTHOMATIC UPDATE
    
//Custom code, do not change this line
    public static final String SQLSelectAll = SQLSelect + OrderBy;
//Custom code, do not change this line

    /**
     * Map ResultSet Field values to :Table:
     * @param dbresult: Database ResultSet
     * @return :Table:
     * @throws java.sql.SQLException
     */
    @Override
    public Object mapResultSet2Entity(ResultSet dbresult) throws SQLException {
        :Table: :table: = (:Table:)super.mapResultSet2Entity(dbresult);
        return :table:;
    }    
    
}

