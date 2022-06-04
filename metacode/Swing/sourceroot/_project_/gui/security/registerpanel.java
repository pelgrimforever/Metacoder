package :project:.gui.security;

import general.exception.CustomException;


import swing.STextField;
import java.awt.event.ActionListener;
import swing.SButton;
import swing.action.ActionSuperClass;
import swing.panel.PanelEditBlank;

/**
 *
 * @author Franky Laseure
 */
public class registerpanel extends PanelEditBlank {
    
    //Logged on user
    public final static String defaultuser = "Anoniem";

    public static final String[] labels = { "Gebruiker", "Paswoord" };
    public static final String[] fieldnames = { "username", "password" };
    public static final byte USERNAME = 0;
    public static final byte PASSWORD = 1;
    /**
     * Swing controls
     */
    protected ActionSuperClass registeraction = new ActionSuperClass("Registeer", "Registeer");
    protected SButton registerbutton = new SButton(registeraction, true);

    public registerpanel(ActionListener parent) throws CustomException {
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

    
    public void loaddata() throws CustomException {
    }

    public void load() {
        c_username.setMaxlength(10);
        this.addControl(labels[USERNAME], c_username, USERNAME);
        c_password.setMaxlength(10);
        this.addControl(labels[PASSWORD], c_password, PASSWORD);
        this.addButton(registerbutton, 1);
    }
    
    private void initevents() {
        registeraction.SetButtonOwner(this.getParentActionlistener());
    }

    public String getUsername() {
        return this.c_username.getText();
    }
    
    public String getPassword() {
        return this.c_password.getText();
    }
}

