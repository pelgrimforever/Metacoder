/*
 * GUI database properties panel / XML generation
 */
package metacoder.GUI;

import base.config.Componenttxt;
import base.config.TXT;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import metacoder.controller.Controller;
import metacoder.data.DBparameters;

/**
 * GUI database properties panel / XML generation
 * contains all database connection details
 * and XML generation button
 * @author Franky Laseure
 */
public class Databaseparameters extends GridPane {
    
    private Controller controller;
    private Componenttxt txt;
    
    private boolean bindbusy = false;
    
    //data listeners
    /**
     * database changed
     * unbind previous Database parameters object
     * bind selected Database parameters object
     */
    protected ChangeListener databaseparameterschanged = new ChangeListener<DBparameters>() {
        @Override public void changed(ObservableValue<? extends DBparameters> o,DBparameters oldVal, DBparameters newVal){
            bindbusy = true;
            unbind();
            bind();
            bindbusy = false;
        }
    };
    
    //eventhandlers
    /**
     * set default database parameters for selected database type
     */
    private EventHandler dbtoolsactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            if(!bindbusy) {
                controller.setDatabasedefaults(dbtools.getValue().toString());
            }
        }
    };

    //layout
    private static final String style = "defaultscreenbody";
    
    /**
     * unbind Project parameters from GUI controls
     */
    private void unbind() {
        dbtools.valueProperty().unbindBidirectional(controller.getDatabaseparameters().dbtoolProperty());
        ipaddress.textProperty().unbindBidirectional(controller.getDatabaseparameters().ipProperty());
        port.textProperty().unbindBidirectional(controller.getDatabaseparameters().portProperty());
        databasename.textProperty().unbindBidirectional(controller.getDatabaseparameters().databasenameProperty());
        user.textProperty().unbindBidirectional(controller.getDatabaseparameters().userProperty());
        controller.getDatabaseparameters().passwordProperty().unbind();
        password.setText("");
    }
    
    /**
     * bind Project parameters to GUI controls
     */
    private void bind() {
        dbtools.valueProperty().bindBidirectional(controller.getDatabaseparameters().dbtoolProperty());
        dbtools.setValue(controller.getDatabaseparameters().getDbtool());
        ipaddress.textProperty().bindBidirectional(controller.getDatabaseparameters().ipProperty());
        port.textProperty().bindBidirectional(controller.getDatabaseparameters().portProperty());
        databasename.textProperty().bindBidirectional(controller.getDatabaseparameters().databasenameProperty());
        user.textProperty().bindBidirectional(controller.getDatabaseparameters().userProperty());
        controller.getDatabaseparameters().passwordProperty().bind(password.textProperty());
    }
    
    //eventhandlers
    /**
     * Write to XML button event
     * Start export to XML
     */
    private EventHandler buttontoxmlactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.export_toxml();
        }
    };

    //controls
    private ComboBox dbtools;
    private TextField ipaddress = new TextField();
    private TextField port = new TextField();
    private TextField databasename = new TextField();
    private TextField user = new TextField();
    private PasswordField password = new PasswordField();
    
    /**
     * create GUI for Project Generation
     * @param controller: application controller
     */
    public Databaseparameters(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("databaseparameters");
        
        //data listeners
        controller.databaseparametersProperty().addListener(databaseparameterschanged);
        
        //controls
        Label labeldbtools = new Label(txt.txt("LABEL_DATABASE"));
        dbtools = new ComboBox(controller.dbtoolslist);
        Label labelipaddress = new Label(txt.txt("LABEL_IP"));
        Label labelport = new Label(txt.txt("LABEL_PORT"));
        Label labeldatabasename = new Label(txt.txt("LABEL_DATABASENAME"));
        Label labeluser = new Label(txt.txt("LABEL_USER"));
        Label labelpassword = new Label(txt.txt("LABEL_PASSWORD"));
        
        Button buttontoxml = new Button(txt.txt("BUTTON_TOXML"));
        buttontoxml.setOnAction(buttontoxmlactionevent);

        //control listeners
        dbtools.setOnAction(dbtoolsactionevent);

        //layout
        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(10));
        
        int y = 0;
        this.add(labeldbtools, 0, y);
        dbtools.setPrefWidth(200);
        this.add(dbtools, 1, y);
        y++;
        this.add(labelipaddress, 0, y);
        this.add(ipaddress, 1, y);
        y++;
        this.add(labelport, 0, y);
        this.add(port, 1, y);
        y++;
        this.add(labeldatabasename, 0, y);
        this.add(databasename, 1, y);
        y++;
        this.add(labeluser, 0, y);
        this.add(user, 1, y);
        y++;
        this.add(labelpassword, 0, y);
        this.add(password, 1, y);
        y++;
        HBox buttonbox = new HBox();
        buttonbox.setSpacing(10);
        buttonbox.getChildren().addAll(buttontoxml);
        this.add(buttonbox, 0, y, 2, 1);
        this.getStyleClass().add(style);
    }
}
