/*
 * BL:table:.java
 *
 * Created on March 26, 2007, 5:44 PM
 * Generated on :metacoder_date:
 *
 */

package :project:.BusinessObject.Logic;

import BusinessObject.BLtable;
import general.exception.DBException;
import general.exception.DataException;
import data.interfaces.db.LogicEntity;
import :project:.interfaces.logicentity.I:Table:;
import :project:.logicentity.:Table:;
import :project:.BusinessObject.table.B:table:;
import general.exception.DataException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Business Logic Entity class BL:table:
 *
 * Class for manipulating data- and database objects
 * for Entity :Table: and direct related data
 * This class is only generated once
 * Implement here all additional business logic
 *
 * @author Franky Laseure
 */
public class BL:table: extends B:table: {
//Metacoder: NO AUTHOMATIC UPDATE
    private boolean isprivatetable = false; //set this to true if only a loggin account has access to this data
	
    /**
     * Constructor, sets :Table: as default Entity
     */
    public BL:table:() {
        this.setLogginrequired(isprivatetable);
    }

    /**
     * Constructor, sets :Table: as default Entity
     * sets transaction queue from given GeneralObject implementation
     * all transactions will commit at same time
     * @param transactionobject: GeneralObjects that holds the transaction queue
     */
    public BL:table:(BLtable transactionobject) {
        super(transactionobject);
        this.setLogginrequired(isprivatetable);
    }

    /**
     * try to insert :Table: object in database
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    @Override
    public void insert:Table:(I:Table: :table:) throws DBException, DataException {
        trans_insert:Table:(:table:);
        super.Commit2DB();
    }
    
    /**
     * try to insert :Table: object in database
     * an alternative to insert:Table:, which can be made an empty function
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    public void secureinsert:Table:(I:Table: :table:) throws DBException, DataException {
        trans_insert:Table:(:table:);
        super.Commit2DB();
    }
    
    /**
     * try to update :Table: object in database
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    @Override
    public void update:Table:(I:Table: :table:) throws DBException, DataException {
        trans_update:Table:(:table:);
        super.Commit2DB();
    }
    
    /**
     * try to update :Table: object in database
     * an alternative to update:Table:, which can be made an empty function
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    public void secureupdate:Table:(I:Table: :table:) throws DBException, DataException {
        trans_update:Table:(:table:);
        super.Commit2DB();
    }
    
    /**
     * try to delete :Table: object in database
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     */
    @Override
    public void delete:Table:(I:Table: :table:) throws DBException {
        trans_delete:Table:(:table:);
        super.Commit2DB();
    }

    /**
     * try to delete :Table: object in database
     * an alternative to delete:Table:, which can be made an empty function
     * commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     */
    public void securedelete:Table:(I:Table: :table:) throws DBException {
        trans_delete:Table:(:table:);
        super.Commit2DB();
    }

    /**
     * try to insert :Table: object in database
     * do not commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    public void trans_insert:Table:(I:Table: :table:) throws DBException, DataException {
        super.checkDATA(:table:);
        super.insert:Table:((:Table:):table:);
    }
    
    /**
     * try to update :Table: object in database
     * do not commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     * @throws general.exception.DataException
     */
    public void trans_update:Table:(I:Table: :table:) throws DBException, DataException {
        super.checkDATA(:table:);
        super.update:Table:((:Table:):table:);
    }
    
    /**
     * try to delete :Table: object in database
     * do not commit transaction
     * @param :table:: :Table: Entity Object
     * @throws general.exception.DBException
     */
    public void trans_delete:Table:(I:Table: :table:) throws DBException {
        super.delete:Table:((:Table:):table:);
    }
}
