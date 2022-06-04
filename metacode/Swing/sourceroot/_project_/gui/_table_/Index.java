package :project:.gui.:table:;

import base.config.TXT;
import base.config.Widgettxt;
import :project:.entity.pk.*;
import :project:.datahandlers.:Table:handler;
import :project:.interfaces.entity.pk.*;
import :project:.interfaces.logicentity.*;
import :project:.interfaces.BusinessObject.*;
import general.exception.CustomException;
import swing.menu.EditFrame;
import swing.menu.List;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.SwingConstants;
import swing.GUIConstants;
import swing.SButton;
import swing.SPanel;
import swing.datalist;
import swing.frame.FrameEdit;
import swing.interfaces.InterfaceFrameOwner;
import swing.panel.PanelTabel;

/**
 *
 * @author franky
 */
public class Index extends PanelTabel implements ActionListener, InterfaceFrameOwner {

    //swing
    private SPanel panelcanvas = new SPanel(false);
    
    //data
    private Widgettxt txt;

    private Meta meta = new Meta();
    private ArrayList :table:s;
    private datalist data_:table:s;
    private String[] labels = meta.labelsREAD;
    private boolean[] visible = meta.visibleREAD;
    private boolean[] enabled = meta.readonlyREAD;
    private ArrayList buttonnames;
:repeatexternalforeignkeys:
:ifforeignkey:
    private String[] :exfkuniquename:_:Extablename:button = { ":Extablename:s", "Open:extablename:s" };
:ifforeignkey:
:repeatexternalforeignkeys:
:repeatforeignkeys:
:inpk:
    protected I:Pktable:PK :uniquename:PK;
    protected I:Pktable: p_:uniquename:;
:inpk:
:repeatforeignkeys:

    public Index() throws CustomException {
        super(true);
        txt = TXT.getWidgettxt(":TABLE:");
        loadlabels();
        loaddata();
        init();
    }

    protected Index(boolean readonly) throws CustomException {
        super(readonly);
        txt = TXT.getWidgettxt(":TABLE:");
        loadlabels();
    }
    
    private void loadlabels() {
:repeatallfields:
        labels[Meta.:COLUMN:] = txt.txt("LABEL_:COLUMN:");
:repeatallfields:
:repeatexternalforeignkeys:
:ifforeignkey:
        :exfkuniquename:_:Extablename:button[1] = txt.txt("BUTTON_:exfkuniquename:_:Extablename:");
:ifforeignkey:
:repeatexternalforeignkeys:
    }
    
//in case a foreign key is used more then once in the primary key
//:uniquename: is used in the function name to avoid double constructors
//for that purpose the private Index(boolean random) constructor exists
:repeatforeignkeys:
:inpk:
    public static Index getIndex_:uniquename:(I:Pktable:PK :uniquename:PK) throws CustomException {
        Index index = new Index(true);
        index.:uniquename:PK = :uniquename:PK;
        index.loaddata();
        index.init();
        return index;
    }

:inpk:
:repeatforeignkeys:
    protected void loaddata() throws CustomException {
        :table:s = null;
:repeatforeignkeys:
:inpk:
        if(this.:uniquename:PK!=null) {
            :table:s = :Table:handler.load:Table:s4:uniquename:(:uniquename:PK);
        }
:inpk:
:repeatforeignkeys:
        if(:table:s==null) {
            :table:s = :Table:handler.load:Table:s();
        }
        I:Table: :table:;
        data_:table:s = new datalist();
        Object[] dataline;
        Vector data = new Vector();
        for(int u=0; u<:table:s.size(); u++) {
            :table: = (I:Table:):table:s.get(u);
            dataline = new Object[labels.length];
:repeatpkfields:
            dataline[meta.:COLUMN:] = :table:.getPrimaryKey().get:Column:();
:repeatpkfields:
:repeatfields:
            dataline[meta.:COLUMN:] = :table:.get:Column:();
:repeatfields:
            data.addElement(dataline);
        }
        data_:table:s.setData(data, labels);
        data_:table:s.setVisible(visible);
        data_:table:s.setEnabled(enabled);
        buttonnames = new ArrayList();
:repeatexternalforeignkeys:
:ifforeignkey:
        buttonnames.add(:exfkuniquename:_:Extablename:button);
:ifforeignkey:
:repeatexternalforeignkeys:
    }

    protected void init() {
        //super.loadData(data_:table:s, this, buttonnames);
        super.loadData(data_:table:s, this, null);
:repeatallfields:
:iffieldtype:
:java.lang.String:
        super.setdimension(meta.:COLUMN:, GUIConstants.labeldimension, I:Table:.SIZE_:COLUMN:, SwingConstants.LEFT);
:java.lang.String:
:other:
        super.setdimension(meta.:COLUMN:, GUIConstants.labeldimension, SwingConstants.LEFT);
:other:
:iffieldtype:
:repeatallfields:
    }
    
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * dispatch action event to "owners"
	 */
	public void actionPerformed(ActionEvent ae) {
            try {
                String actioncommand = ae.getActionCommand();
                int ID = ((SButton)ae.getSource()).getID();
:repeatpkfields:
:iffieldtype:
:boolean:
                boolean :Column: = (super.getBooleanat(ID, Meta.:COLUMN:));
:boolean:
:java.sql.Date:
                Date :Column: = (super.getDateat(ID, Meta.:COLUMN:));
:java.sql.Date:
:java.sql.Timestamp:
                Timestamp :Column: = (super.getTimestampat(ID, Meta.:COLUMN:));
:java.sql.Timestamp:
:java.sql.Time:
                Timestamp :Column: = (super.getTimestampat(ID, Meta.:COLUMN:));
:java.sql.Time:
:java.lang.String:
                String f_:column: = super.getStringat(ID, Meta.:COLUMN:);
:java.lang.String:
:float:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Float f_:column: = java.lang.Float.valueOf(string:column:);
:float:
:double:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Double f_:column: = java.lang.Double.valueOf(string:column:);
:double:
:long:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Long f_:column: = java.lang.Long.valueOf(string:column:);
:long:
:int:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Integer f_:column: = java.lang.Integer.valueOf(string:column:);
:int:
:other:
                String f_:column: = super.getStringat(ID, Meta.:COLUMN:);
:other:
:iffieldtype:
:repeatpkfields:
                I:Table:PK :table:PK = new :Table:PK(:repeatpkfields::iffieldtype:
:boolean::Column::boolean:
:java.sql.Date::Column::java.sql.Date:
:java.sql.Timestamp::Column::java.sql.Timestamp:
:java.sql.Time::Column::java.sql.Time:
:other:f_:column::other:
:iffieldtype::,::repeatpkfields:);
                I:Table: :table: = :Table:handler.load:Table:(:table:PK);
                if(actioncommand.equals("save")) {
:repeatfields:
:iffieldtype:
:boolean:
                :table:.set:Column:(super.getCBooleanat(ID, Meta.:COLUMN:));
:boolean:
:java.sql.Date:
                :table:.set:Column:(super.getCDateat(ID, Meta.:COLUMN:));
:java.sql.Date:
:java.sql.Timestamp:
                :table:.set:Column:(super.getCTimestampat(ID, Meta.:COLUMN:));
:java.sql.Timestamp:
:java.sql.Time:
                :table:.set:Column:(super.getCTimeat(ID, Meta.:COLUMN:));
:java.sql.Time:
:java.lang.String:
                String f_:column: = super.getCStringat(ID, Meta.:COLUMN:);
                :table:.set:Column:(f_:column:);
:java.lang.String:
:float:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Float f_:column: = java.lang.Float.valueOf(string:column:);
                :table:.set:Column:(f_:column:);
:float:
:double:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Double f_:column: = java.lang.Double.valueOf(string:column:);
                :table:.set:Column:(f_:column:);
:double:
:long:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Long f_:column: = java.lang.Long.valueOf(string:column:);
                :table:.set:Column:(f_:column:);
:long:
:int:
                String string:column: = super.getStringat(ID, Meta.:COLUMN:);
                java.lang.Integer f_:column: = java.lang.Integer.valueOf(string:column:);
                :table:.set:Column:(f_:column:);
:int:
:other:
                String f_:column: = super.getStringat(ID, Meta.:COLUMN:);
                //:table:.set:Column:(f_:column:);
:other:
:iffieldtype:
:repeatfields:
                    :Table:handler.update:Table:(:table:);
                }
                if(actioncommand.equals("del")) {
                    :Table:handler.delete:Table:(:table:);
                    super.removeRow(ID);
                }
                if(actioncommand.equals("new")) {
                    Edit editpanel = new Edit();
                    editpanel.setParentindex(this);
:repeatforeignkeys:
:inpk:
                    editpanel.set:Uniquename:PK(:uniquename:PK);
:inpk:
:repeatforeignkeys:
                    new FrameEdit(GUIConstants.mainscreen, this, "Nieuwe :table:", editpanel);
                }
                if(actioncommand.equals("open")) {
                    openEdit(:table:PK, :table:.toString());
                }
            }
            catch(CustomException e) {
            }
        }

        public void openEdit(I:Table:PK :table:PK, String description) throws CustomException {
            ArrayList tabpanels = new ArrayList();
            Edit editpanel = new Edit(:table:PK);
            List.Tabpanel tabpanel = new List.Tabpanel(":Table:", editpanel);
            tabpanels.add(tabpanel);
:repeatexternalforeignkeys:
:ifforeignkey:
            :project:.gui.:extablename:.Index :exfkuniquename:_:Extablename:index = this.new Index_:exfkuniquename:_:Extablename:(:table:PK);
            tabpanel = new List.Tabpanel(this.:exfkuniquename:_:Extablename:button[0], :exfkuniquename:_:Extablename:index);
            tabpanel.setScrolllist(true);
            tabpanels.add(tabpanel);
:ifforeignkey:
:repeatexternalforeignkeys:
            EditFrame listframe = new EditFrame(this, ":Table: " + description, tabpanels);
            listframe.setAlwaysOnTop(true);
        }
        
        //fired when data changes in calles JDialog
        public void reload() {
            try {
                loaddata();
                init();
            }
            catch(CustomException e) {
            }
        }

        public void reopenEdit(I:Table: :table:)  throws CustomException {
            openEdit(:table:.getPrimaryKey(), :table:.toString());
        }

:repeatexternalforeignkeys:
:ifforeignkey:
    public class Index_:exfkuniquename:_:Extablename: extends :project:.gui.:extablename:.Index {
        
        public Index_:exfkuniquename:_:Extablename:(I:Table:PK :exfkuniquename:PK) throws CustomException {
            super(true);
            super.:exfkuniquename:PK = :exfkuniquename:PK;
            loaddata();
            init();
        }
    }

:ifforeignkey:
:repeatexternalforeignkeys:

}

