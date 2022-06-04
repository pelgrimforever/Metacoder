package :project:.gui.:table:;

import base.config.TXT;
import base.config.Widgettxt;
import base.framework.controller.AbstractDataController;
import :project:.datahandlers.*;
import :project:.entity.pk.*;
import :project:.logicentity.:Table:;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import general.exception.CustomException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import swing.SDateSpinner;
import swing.SDoubleField;
import swing.SFilebrowser;
import swing.SFloatField;
import swing.SIntField;
import swing.SLongField;
import swing.STextField;
import swing.STimeSpinner;
import swing.panel.PanelEdit;
import swing.SComboBox;
import swing.SCheckBox;
import swing.datalist;
import data.interfaces.db.Filedata;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.BusinessObject.*;
import data.interfaces.db.EntityPKInterface;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Franky Laseure
 */
public class Edit extends PanelEdit implements ActionListener{
    
    //caller panel
    Index parentindex = null;

    //data
    private Widgettxt txt;

    private boolean newline = true;
    private I:Table: :table: = null;
:repeatforeignkeys:
:notpk:
    private ArrayList :pktable:s;
    private datalist dl_:pktable:s;
:notpk:
:repeatforeignkeys:
:repeatforeignkeys:
:inpk:
    private I:Pktable:PK :uniquename:PK;
    private I:Pktable: p_:uniquename:;
:inpk:
:repeatforeignkeys:
    
    public Edit() throws CustomException {
        this(null);
    }
    
    public Edit(I:Table:PK :table:PK) throws CustomException {
        super(1);
        super.loadParent(this);
        txt = TXT.getWidgettxt(":TABLE:");
        if(:table:PK!=null) {
            :table: = :Table:handler.load:Table:(:table:PK);
            newline = false;
        } else {
            readonly = meta.readonlyNEW;
        }
        loaddata();
        load();
    }

    public void setParentindex(Index parentindex) {
        this.parentindex = parentindex;
    }

    //layout constraints
    private Meta meta = new Meta();
    private String[] labels = meta.labelsINPUT;
    private boolean[] visible = meta.visibleINPUT;
    private boolean[] readonly = meta.readonlyINPUT;
    
    //Data Controls
:repeatpkfields:
:iffieldtype:
:java.sql.Date:
    private SDateSpinner c_:column: = new SDateSpinner();
:java.sql.Date:
:java.sql.Timestamp:
    private STimeSpinner c_:column: = new STimeSpinner();
:java.sql.Timestamp:
:java.sql.Time:
    private STimeSpinner c_:column: = new STimeSpinner();
:java.sql.Time:
:int:
    private SIntField c_:column: = new SIntField();
:int:
:long:
    private SLongField c_:column: = new SLongField();
:long:
:double:
    private SDoubleField c_:column: = new SDoubleField();
:double:
:float:
    private SFloatField c_:column: = new SFloatField();
:float:
:boolean:
    private SCheckBox c_:column: = new SCheckBox(labels[meta.:COLUMN:], false);
:boolean:
:java.lang.String:
    private STextField c_:column: = new STextField("");
:java.lang.String:
:iffieldtype:
:repeatpkfields:
:repeatfields:
:iffieldtype:
:java.sql.Date:
    private SDateSpinner c_:column: = new SDateSpinner();
:java.sql.Date:
:java.sql.Timestamp:
    private STimeSpinner c_:column: = new STimeSpinner();
:java.sql.Timestamp:
:java.sql.Time:
    private STimeSpinner c_:column: = new STimeSpinner();
:java.sql.Time:
:int:
    private SIntField c_:column: = new SIntField();
:int:
:long:
    private SLongField c_:column: = new SLongField();
:long:
:double:
    private SDoubleField c_:column: = new SDoubleField();
:double:
:float:
    private SFloatField c_:column: = new SFloatField();
:float:
:boolean:
    private SCheckBox c_:column: = new SCheckBox(labels[meta.:COLUMN:], false);
:boolean:
:java.lang.String:
    private STextField c_:column: = new STextField("");
:java.lang.String:
:iffieldtype:
:repeatfields:
:repeatforeignkeys:
:notpk:
    private SComboBox cb_:uniquename:;
:notpk:
:repeatforeignkeys:
    
    public void loaddata() throws CustomException {
:repeatforeignkeys:
:notpk:
        :pktable:s = :Pktable:handler.load:Pktable:s();
        dl_:pktable:s = AbstractDataController.getComboDatalist(:pktable:s);
:notpk:
:repeatforeignkeys:
    }

    public void load() {
:repeatforeignkeys:
:notpk:
        cb_:uniquename: = new SComboBox(dl_:pktable:s, AbstractDataController.KEY, AbstractDataController.VALUE);
:notpk:
:repeatforeignkeys:
        if(!newline) {
:repeatpkfields:
:iffieldtype:
:java.lang.String:
            c_:column:.setText(:table:.getPrimaryKey().get:Column:());
            c_:column:.setMaxlength(I:Table:.SIZE_:COLUMN:);
            this.addControl(labels[Meta.:COLUMN:], c_:column:, Meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.lang.String:
:java.sql.Date:
            c_:column:.setSqlDate(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Date:
:java.sql.Timestamp:
            c_:column:.setTime(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Timestamp:
:java.sql.Time:
            c_:column:.setTime(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Time:
:int:
            c_:column:.setInt(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:int:
:long:
            c_:column:.setLong(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:long:
:double:
            c_:column:.setDouble(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:double:
:float:
            c_:column:.setFloat(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:float:
:boolean:
            c_:column:.setSelected(:table:.getPrimaryKey().get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:boolean:
:iffieldtype:
:repeatpkfields:
:repeatfields:
:iffieldtype:
:java.lang.String:
            c_:column:.setText(:table:.get:Column:());
            c_:column:.setMaxlength(I:Table:.SIZE_:COLUMN:);
            this.addControl(labels[Meta.:COLUMN:], c_:column:, Meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.lang.String:
:java.sql.Date:
            c_:column:.setSqlDate(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Date:
:java.sql.Timestamp:
            c_:column:.setTime(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Timestamp:
:java.sql.Time:
            c_:column:.setTime(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:java.sql.Time:
:int:
            c_:column:.setInt(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:int:
:long:
            c_:column:.setLong(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:long:
:double:
            c_:column:.setDouble(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:double:
:float:
            c_:column:.setFloat(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:float:
:boolean:
            c_:column:.setSelected(:table:.get:Column:());
            this.addControl(labels[meta.:COLUMN:], c_:column:, meta.:COLUMN:);
            this.setVisible(Meta.:COLUMN:, visible[Meta.:COLUMN:]);
            this.setReadonly(Meta.:COLUMN:, readonly[Meta.:COLUMN:]);
:boolean:
:iffieldtype:
:repeatfields:
            int fkcounter = 1000;
:repeatforeignkeys:
:notpk:
            if(:table:.get:Uniquename:PK()!=null) cb_:uniquename:.setInput(:table:.get:Uniquename:PK().getKeystring());
            this.addControl(txt.txt("LABEL_fk:Uniquename:"), cb_:uniquename:, fkcounter++);
:notpk:
:repeatforeignkeys:
        }
        super.loadParent(this);
    }

:repeatforeignkeys:
:inpk:
    public void set:Uniquename:PK(I:Pktable:PK :uniquename:PK) {
        this.:uniquename:PK = :uniquename:PK;
:repeatforeignkeyfields:
:iffieldtype:
:java.lang.String:
        if(:uniquename:PK!=null) c_:column:.setText(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:java.lang.String:
:java.sql.Date:
        if(:uniquename:PK!=null) c_:column:.setSqlDate(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:java.sql.Date:
:java.sql.Timestamp:
        if(:uniquename:PK!=null) c_:column:.setTime(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:java.sql.Timestamp:
:java.sql.Time:
        if(:uniquename:PK!=null) c_:column:.setTime(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:java.sql.Time:
:int:
        if(:uniquename:PK!=null) c_:column:.setInt(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:int:
:long:
        if(:uniquename:PK!=null) c_:column:.setLong(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:long:
:double:
        if(:uniquename:PK!=null) c_:column:.setDouble(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:double:
:float:
        if(:uniquename:PK!=null) c_:column:.setFloat(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:float:
:boolean:
        if(:uniquename:PK!=null) c_:column:.setSelected(:uniquename:PK.get:Primarycolumn:());
        setReadonly(meta.:COLUMN:, false);
:boolean:
:iffieldtype:
:repeatforeignkeyfields:
    }

:inpk:
:repeatforeignkeys:

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * dispatch action event to "owners"
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            String actioncommand = ae.getActionCommand();
            if(actioncommand.equals("save")) {
:repeatpkfields:
:iffieldtype:
:java.lang.String:
            String f_:column: = c_:column:.getText();
:java.lang.String:
:java.sql.Date:
            java.sql.Date f_:column: = c_:column:.getSelectedSqlDate();
:java.sql.Date:
:java.sql.Timestamp:
            java.sql.Timestamp f_:column: = c_:column:.getSelectedTimestamp();
:java.sql.Timestamp:
:java.sql.Time:
            java.sql.Time f_:column: = c_:column:.getSelectedTime();
:java.sql.Time:
:int:
            int f_:column: = c_:column:.getInt();
:int:
:long:
            long f_:column: = c_:column:.getLong();
:long:
:double:
            double f_:column: = c_:column:.getDouble();
:double:
:float:
            float f_:column: = c_:column:.getFloat();
:float:
:boolean:
            boolean f_:column: = c_:column:.isSelected();
:boolean:
:iffieldtype:
:repeatpkfields:
            :Table:PK :table:PK = new :Table:PK(:repeatpkfields:f_:column::,::repeatpkfields:);
            if(newline) :table: = new :Table:(:table:PK);
            else :table: = :Table:handler.load:Table:(:table:PK);
:repeatfields:
:iffieldtype:
:java.lang.String:
            String f_:column: = c_:column:.getText();
            :table:.set:Column:(f_:column:);
:java.lang.String:
:java.sql.Date:
            java.sql.Date f_:column: = c_:column:.getSelectedSqlDate();
            :table:.set:Column:(f_:column:);
:java.sql.Date:
:java.sql.Timestamp:
            java.sql.Timestamp f_:column: = c_:column:.getSelectedTimestamp();
            :table:.set:Column:(f_:column:);
:java.sql.Timestamp:
:java.sql.Time:
            java.sql.Time f_:column: = c_:column:.getSelectedTime();
            :table:.set:Column:(f_:column:);
:java.sql.Time:
:int:
            int f_:column: = c_:column:.getInt();
            :table:.set:Column:(f_:column:);
:int:
:long:
            long f_:column: = c_:column:.getLong();
            :table:.set:Column:(f_:column:);
:long:
:double:
            double f_:column: = c_:column:.getDouble();
            :table:.set:Column:(f_:column:);
:double:
:float:
            float f_:column: = c_:column:.getFloat();
            :table:.set:Column:(f_:column:);
:float:
:boolean:
            boolean f_:column: = c_:column:.isSelected();
            :table:.set:Column:(f_:column:);
:boolean:
:iffieldtype:
:repeatfields:
:repeatforeignkeys:
:notpk:
            :table:.set:Uniquename:PK(:Pktable:PK.getKey(cb_:uniquename:.getInputKey()));
:notpk:
:repeatforeignkeys:
            if(newline) :Table:handler.insert:Table:(:table:);
            else :Table:handler.update:Table:(:table:);
            super.closeParent();
            if(parentindex!=null)
                parentindex.reopenEdit(:table:);
            }
            if(actioncommand.equals("close")) {
                super.closeParent();
            }
        }
        catch(CustomException e) {
        }
    }
}

