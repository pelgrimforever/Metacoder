/*
 * GUI database list
 */
package metacoder.GUI;

import base.config.Componenttxt;
import base.config.TXT;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import metacoder.controller.Controller;
import metacoder.data.xml.Xmlreader;

/**
 * GUI database list
 * Manage databases
 * 
 * New Database button (new database)
 * List of all database XML files in the databases subfolder
 * set Selected database in application controller
 * @author Franky Laseure
 */
public class Databaselist extends ScrollPane {
    
    private Controller controller;
    private Componenttxt txt;
    
    //data listeners
    /**
     * database list changed
     * show new database list
     */
    protected ChangeListener dblistchanged = new ChangeListener<ArrayList>() {
        @Override public void changed(ObservableValue<? extends ArrayList> o,ArrayList oldVal, ArrayList newVal){
            populate();
        }
    };
    
    /**
     * build GUI database list
     */
    private void populate() {
        content.getChildren().clear();
        Label labelip;
        Label labeldbtool;
        Button button;
        int y = 0;
        button = new Button(txt.txt("BUTTON_NEW"));
        button.setOnAction(buttonnewactionevent);
        content.add(button, 2, y);
        y++;
        for(Xmlreader dbxml: controller.getDblist()) {
            labelip = new Label(dbxml.getDatabase().getIp());
            labeldbtool = new Label(dbxml.getDatabase().getProductname());
            button = new Button(dbxml.getDatabase().getOriginalname());
            button.setOnAction(buttonselectactionevent);
            button.setUserData(dbxml);
            button.setPrefWidth(150);
            content.add(labelip, 0, y);
            content.add(labeldbtool, 1, y);
            content.add(button, 2, y);
            y++;
        }
    }
    
    //eventhandlers
    /**
     * New Database button event
     * Initialize Databaseparameters panel
     */
    private EventHandler buttonnewactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.newDatabase();
        }
    };

    /**
     * Database button event
     * Set selected database
     */
    private EventHandler buttonselectactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Button button = (Button)e.getSource();
            Xmlreader dbxml = (Xmlreader)button.getUserData();
            controller.selectDatabase(dbxml);
        }
    };

    //controls
    GridPane content = new GridPane();
    
    //layout
    private static final String style = "defaultscreenbody";
    
    /**
     * create GUI for Database list
     * @param controller: application controller
     */
    public Databaselist(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("projectlist");
        
        //data listeners
        controller.dblistProperty().addListener(dblistchanged);
        
        //controls
        content.setHgap(5);
        content.setVgap(5);
        content.setPadding(new Insets(10));
        
        this.setPrefSize(450, 2000);
        this.setContent(content);
        
        //layout
        this.getStyleClass().add(style);
        populate();
    }
}
