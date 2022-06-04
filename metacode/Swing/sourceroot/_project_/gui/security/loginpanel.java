package :project:.gui.security;

import general.exception.CustomException;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import swing.STextField;
import swing.SComboBox;
import swing.datalist;
import swing.SButton;
import swing.action.ActionSuperClass;
import swing.panel.PanelEditBlank;

/**
 *
 * @author Franky Laseure
 */
public class loginpanel extends PanelEditBlank {
//Metacoder: NO AUTHOMATIC UPDATE
    
    //Logged on user
    public final static String defaultuser = "Anoniem";

    public static final String[] labels = { "Gebruiker", "Paswoord" };
    public static final String[] fieldnames = { "username", "password" };
    public static final byte USERNAME = 0;
    public static final byte PASSWORD = 1;
    /**
     * Swing controls
     */
    protected ActionSuperClass loginaction = new ActionSuperClass("Login", "Login");
    protected SButton loginbutton = new SButton(loginaction, true);
    protected ActionSuperClass forgotaction = new ActionSuperClass("Vergeten", "Vergeten");
    protected SButton forgotbutton = new SButton(forgotaction, true);
    protected ActionSuperClass guestaction = new ActionSuperClass("Bezoeker", "Bezoeker");
    protected SButton guestbutton = new SButton(guestaction, true);

    /**
     * Data 
     */
    private ArrayList users = new ArrayList();
    private datalist dl_users;
    
    public loginpanel(ActionListener parent) throws CustomException {
        super(1);
        super.loadParent(parent);
        loaddata();
        load();
        initevents();
    }

    /**
     * Data Controls 
     */
    private STextField c_username = new STextField("");
    private STextField c_password = new STextField("");
    private SComboBox cb_username;

    
    public void loaddata() throws CustomException {
        //users = Server.;
        //dl_users = Server.getComboDatalist(users);
    }

    public void load() {
	//cb_username = new SComboBox(dl_users, Server.KEY, Server.VALUE);
        //this.addControl(labels[USERNAME], cb_username, USERNAME);
        
        c_username.setMaxlength(10);
        this.addControl(labels[USERNAME], c_username, USERNAME);
        c_password.setMaxlength(10);
        this.addControl(labels[PASSWORD], c_password, PASSWORD);
        this.addButton(forgotbutton, 0);
        this.addButton(loginbutton, 1);
        this.addButton(guestbutton, 2);
    }
    
    private void initevents() {
        loginaction.SetButtonOwner(this.getParentActionlistener());
        forgotaction.SetButtonOwner(this.getParentActionlistener());
        guestaction.SetButtonOwner(this.getParentActionlistener());
    }

    public String getUsername() {
        //return this.cb_username.getInputKey();
        return this.c_username.getText();
    }
    
    public String getPassword() {
        return this.c_password.getText();
    }
}

