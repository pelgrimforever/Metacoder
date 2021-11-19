/*
 * GUI Project Generation panel
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import metacoder.controller.Controller;
import metacoder.data.Databaseproject;

/**
 * GUI Project Generation panel
 * Starts code generation 
 * Contains generate button and progress bar
 * @author Franky Laseure
 */
public class ProjectGeneration extends GridPane {
    
    private Controller controller;
    private Componenttxt txt;

    //data listeners
    /**
     * Selected database changed in panel Databaselist
     * update database name in GUI
     */
    protected ChangeListener databasechanged = new ChangeListener<Databaseproject>() {
        @Override public void changed(ObservableValue<? extends Databaseproject> o,Databaseproject oldVal, Databaseproject newVal){
            databasename.setText(controller.getDatabaseproject().getDatabasename());
        }
    };
    
    //event listeners
    /**
     * Generate button event
     * Start code generation
     */
    private EventHandler generatebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.export_toprojects();
        }
    };
        
    //layout
    private static final String style = "defaultscreenbody";
    
    //controls
    private Label databasename = new Label();
    private Label progresslabel;
    private ProgressBar progressbar;
    
    /**
     * create GUI for Project Generation
     * @param controller: application controller
     */
    public ProjectGeneration(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("projectgeneration");
        
        //data listeners
        controller.databaseprojectProperty().addListener(databasechanged);
        
        //controls
        Label projectlabel = new Label(txt.txt("LABEL_DATABASE"));
        Button generatebutton = new Button(txt.txt("BUTTON_GENERATE"));
        generatebutton.setOnAction(generatebuttonactionevent);
        
        progresslabel = new Label();
        progresslabel.textProperty().bind(controller.serviceexport2projects.progressstringProperty());
        progressbar = new ProgressBar();
        progressbar.setPrefWidth(800);
        progressbar.progressProperty().bind(controller.serviceexport2projects.progresspercProperty());
        
        //layout
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(10));
        this.getStyleClass().add(style);
        int y = 0;
        this.add(projectlabel, 0, y);
        this.add(databasename, 1, y);
        this.add(generatebutton, 2, y);
        y++;
        this.add(progresslabel, 0, y);
        y++;
        this.add(progressbar, 0, y, 3, 1);
    }
    
}
