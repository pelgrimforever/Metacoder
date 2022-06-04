import base.config.Config;
import base.config.UIsettings;
import base.config.menu.Menutabconfig;
import base.config.menu.Menutabcontrolconfig;
import base.config.menu.Menutabpanelconfig;
import :project:.gui.security.Login;
import :project:.gui.security.logininterface;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import swing.GUIConstants;
import swing.SLayeredFrame;
import swing.SToggleButton;
import swing.UILoader;
import swing.action.ActionSuperClass;
import swing.action.MainmenuAction;
import swing.img.ImgLoader;
import :project:.interfaces.logicentity.ISECURITY;
import swing.menu.Applicationpanel;
import swing.menu.Projectmenubar;
import swing.menu.Mainmenupanel;
import java.awt.Dimension;
import java.net.URL;
import swing.SwingTools;
import swing.interfaces.InterfaceFrameOwner;

/**
 * @author Franky Laseure
 * Main class
 */
public class :Project: extends SLayeredFrame implements ActionListener, ChangeListener, InterfaceFrameOwner, logininterface {

    //server properties
    public static final String server = "http://141.135.208.48:8080/";
    //public static final String server = "http://192.168.0.50:8080/";
    public static final String projectpath = ":project:/";
    public static final String demoxml = "demo.xml";
    public static final String demowidgetsxml = "demowidgets.xml";

    /**
     * Layout constraints 
     */
    private GridBagLayout canvas = new GridBagLayout();
    private Insets borderspace = GUIConstants.borderspace;

    /**
     * Menu:
     */	
    private Projectmenubar projectmenubar = new Projectmenubar(this);
    private MainmenuAction mmaction = new MainmenuAction();
    private JMenu subjectmenuheader = new JMenu(mmaction);

    /**
     * Panel: 
     */	
    private Icon menuicon = ImgLoader.getLoader().get(UIsettings.menuiconpath);
    private SToggleButton menubutton = new SToggleButton("", menuicon);
    private JMenuItem menupanelbutton = new JMenuItem("Menupaneel");
    private Applicationpanel applicationpanel = new Applicationpanel(projectmenubar);
    private Mainmenupanel mainmenupanel;
	
    /**
     * SECURITY DATA
     */
    private ISECURITY user = null;

    /**
     * Constructor
     * Build JFrame
     */
    public :Project:() {
        super(":Project:");
        //load online config
        Config config = Config.buildInstance(server, projectpath);
        URL demoxmlpath = this.getClass().getResource(demoxml);
        URL demowidgetsxmlpath = this.getClass().getResource(demowidgetsxml);
        config.readdemoxml(demoxmlpath, demowidgetsxmlpath);

        new Login(this, this, this);

        if(this.user!=null) {
            GUIConstants.mainscreen = this;
            this.setCursor(Cursor.WAIT_CURSOR);
            this.setLayout(canvas);

            //load menu panel and menubar
            menubutton.setSelected(true);
            mainmenupanel = new Mainmenupanel(menubutton, subjectmenuheader);
            mainmenupanel.setMinimumSize(new Dimension(150, 150));

            //set menubar
            subjectmenuheader.add(menupanelbutton);
            projectmenubar.add(subjectmenuheader);
            projectmenubar.init();
            this.setJMenuBar(projectmenubar);

            //set panels
            this.add(mainmenupanel, new GridBagConstraints(0, 0, 1, 1, 0.5, 10.0
                            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, borderspace, 0, 0));
            this.add(applicationpanel, new GridBagConstraints(1, 0, 2, 2, 20.0, 10.0
                            ,GridBagConstraints.NORTH, GridBagConstraints.BOTH, borderspace, 0, 0));
            this.pack();
            //this.setResizable(false);

            //events
            menubutton.addChangeListener(this);
            menupanelbutton.addActionListener(this);
            initevent(mmaction);
            applicationpanel.addChangeListener(projectmenubar);

            this.setVisible(true);
            this.setCursor(Cursor.DEFAULT_CURSOR);
        } else {
            System.exit(0);
        }
    }
		
    private void initevent(ActionSuperClass action) {
        action.SetButtonOwner(this);
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * dispatch action event to "owners"
     */
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(menupanelbutton)) {
            //set selected button
            menubutton.setSelected(true);                
        } else if(ae.getSource().getClass().getName().equals(Mainmenupanel.class.getName())) {
            //(de)activate tabpanel
            boolean show = String.valueOf(true).equals(ae.getActionCommand());
            Menutabconfig mainmenu = mainmenupanel.getMainmenu(ae.getID());
            Icon icon = ImgLoader.getLoader().get(Config.configmap + mainmenu.getIcon());
            if(show) {
                applicationpanel.addTab(mainmenu.getLabel(), icon);
                if(applicationpanel.getTabcount()==1) {
                    //this.maximize();
                    SwingTools.putScreen(this, SwingTools.MAXIMIZE);
                }
            } else {
                applicationpanel.remove(mainmenu.getLabel());
                if(applicationpanel.getTabcount()==0) {
                    this.pack();
                }
            }
        } else {
            //load panel in active tabpanel
            applicationpanel.load(ae);
        }
        this.paint(this.getGraphics());
        this.update(this.getGraphics());
    }

    public void stateChanged(ChangeEvent e) {
        mainmenupanel.setVisible(menubutton.isSelected());
        if(applicationpanel.getTabcount()==0) this.pack();
    }

    public void setSecurity(ISECURITY security) {
        this.user = security;
    }

    public void reload() {
    }

    /**
     * Default method for Eindejaar
     * @param args
     */
    public static void main(String[] args) {
        UILoader.loadUI();
        new Film();
    }

    /* (non-Javadoc)
     * @see java.awt.Window#processWindowEvent(java.awt.event.WindowEvent)
     * Close application
     */
    public void processWindowEvent(WindowEvent e) {
        if(e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
            System.exit(0);
        }
        super.processWindowEvent(e);
    }
}

