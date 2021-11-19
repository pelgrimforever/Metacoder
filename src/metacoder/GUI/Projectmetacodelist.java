/*
 * GUI Project Metacode list
 */
package metacoder.GUI;

import base.config.Componenttxt;
import base.config.TXT;
import java.util.HashMap;
import java.util.Iterator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import metacoder.controller.Controller;
import metacoder.data.Sourceproject;
import metacoder.data.DBparameters;

/**
 * GUI Project Metacode list
 * List of all maps from the metacode maps
 * Each map represents a project Metacode
 * @author Franky Laseure
 */
public class Projectmetacodelist extends ScrollPane {
    
    private Controller controller;
    private Componenttxt txt;
        
    //data listeners
    /**
     * Project metacode list change
     * build new metacode list
     * linked metacodes to a selected project are selected toggle buttons
     */
    protected ChangeListener metacodelistchanged = new ChangeListener<DBparameters>() {
        @Override public void changed(ObservableValue<? extends DBparameters> o,DBparameters oldVal, DBparameters newVal){
            populatemetacodelist();
        }
    };
    
    /**
     * Selected project changed
     * update selected metacodes from project
     */
    protected ChangeListener selectedprojectchanged = new ChangeListener<Sourceproject>() {
        @Override public void changed(ObservableValue<? extends Sourceproject> o,Sourceproject oldVal, Sourceproject newVal){
            //set the toggles
            resetToggles();
            for(String metacodename: newVal.getMetacodelist()) {
                metacodetoggles.get(metacodename).setSelected(true);
            }
        }
    };
    
    /**
     * reset all metacode toggle buttons
     */
    private void resetToggles() {
        for(ToggleButton toggle: metacodetoggles.values()) {
            toggle.setSelected(false);
        }
    }
    
    //event listeners
    /**
     * metacode toggle button pressed
     * add/remove project metacode to/from a project
     */
    private EventHandler metacodetogglechanged = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            ToggleButton metacodetoggle = (ToggleButton)e.getSource();
            String metacodename = (String)metacodetoggle.getUserData();
            if(metacodetoggle.isSelected()) {
                controller.addMetacode(metacodename);
            } else {
                controller.removeMetacode(metacodename);
            }
        }
    };

    /**
     * create GUI metacode list
     */
    private void populatemetacodelist() {
        content.getChildren().clear();
        ToggleButton metacodetoggle;
        for(String metacodename: controller.getMetacodelist()) {
            metacodetoggle = new ToggleButton(metacodename);
            metacodetoggle.setUserData(metacodename);
            metacodetoggle.setAlignment(Pos.CENTER);
            metacodetoggle.setOnAction(metacodetogglechanged);
            content.getChildren().add(metacodetoggle);
            metacodetoggles.put(metacodename, metacodetoggle);
        }
    }
    
    //layout
    private static final String style = "defaultscreenbody";
    
    //controls
    private VBox content = new VBox();
    private HashMap<String, ToggleButton> metacodetoggles = new HashMap<String, ToggleButton>();
    
    /**
     * create GUI metacode list
     * @param controller: application controller
     */
    public Projectmetacodelist(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("projectmetacodelist");
        
        //data listeners
        controller.metacodelistProperty().addListener(metacodelistchanged);
        controller.selectedprojectProperty().addListener(selectedprojectchanged);
        
        //controls
        populatemetacodelist();
        
        //layout
        this.getStyleClass().add(style);
        content.setPadding(new Insets(10));
        content.setSpacing(10);

        this.setPrefSize(450, 2000);
        this.setContent(content);
    }
}
