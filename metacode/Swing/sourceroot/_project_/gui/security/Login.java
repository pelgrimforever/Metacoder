package :project:.gui.security;

import base.config.UIsettings;
import general.exception.CustomException;
import :project:.logicentity.SECURITY;
import swing.menu.Applicationpanel;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import swing.GUIConstants;
import swing.SLayeredDialog;
import swing.SPanel;
import swing.SwingTools;
import swing.img.ImgLoader;
import swing.interfaces.InterfaceFrameOwner;

/**
 *
 * @author Franky Laseure
 */
public class Login extends SLayeredDialog implements ActionListener {
//Metacoder: NO AUTHOMATIC UPDATE

    //Swing Controls
    private Frame owner;
    private InterfaceFrameOwner frameowner;
    private logininterface loginmain;
    
    /**
     * Layout constraints 
     */
    private GridBagLayout canvas = new GridBagLayout();
    private Insets borderspace = GUIConstants.borderspace;
    
    /**
     * Panel:
     */
    private Applicationpanel applicationpanel = new Applicationpanel();
    private loginpanel login;
    private registerpanel register;
    private Icon emptyicon = ImgLoader.getLoader().get(UIsettings.emptyiconpath);
    
    public Login(Frame owner, InterfaceFrameOwner frameowner, logininterface loginmain) {
        super(owner, frameowner, "Login");
        this.owner = owner;
        this.frameowner = frameowner;
        this.loginmain = loginmain;
	owner.setCursor(Cursor.WAIT_CURSOR);
	this.setLayout(canvas);

        try {
            login = new loginpanel(this);
            register = new registerpanel(this);
        }
        catch(CustomException e) {
        }
        SPanel panel1 = new SPanel(true);
        panel1.add(login);
        SPanel panel2 = new SPanel(true);
        panel2.add(register);
        applicationpanel.addTab("login", null, panel1);
        applicationpanel.addTab("registreer", null, panel2);
        //set panels
        this.add(applicationpanel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, borderspace, 0, 0));
        
        this.setBounds(0, 0, 330, 160);
        SwingTools.putScreen(this, SwingTools.CENTER);
	//this.pack();
        this.setVisible(true);
        owner.setCursor(Cursor.DEFAULT_CURSOR);
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * dispatch action event to "owners"
     */
    public void actionPerformed(ActionEvent ae) {
//        try {
            String actioncommand = ae.getActionCommand();
            if(actioncommand.equals("Login")) {
		String username = login.getUsername();
		String password = login.getPassword();
            }
            if(actioncommand.equals("Registreer")) {
                //String username = this.cb_username.getInputKey();
		String username = register.getUsername();
		String password = register.getPassword();
            }
            if(actioncommand.equals("Vergeten")) {
		String username = login.getUsername();
            }
            if(actioncommand.equals("Bezoeker")) {
                loginmain.setSecurity(new SECURITY());
                this.dispose();
            }
//        }
//        catch(CustomException e) {
//        }
    }
    
  /* (non-Javadoc)
   * @see java.awt.Window#processWindowEvent(java.awt.event.WindowEvent)
   * Close application
   */
  public void processWindowEvent(WindowEvent e) {
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      this.dispose();
    }
    super.processWindowEvent(e);
  }

  public void dispose() {
    owner.setEnabled(true);
    frameowner.reload();
    super.dispose();
    super.setVisible(false);
  }
}

