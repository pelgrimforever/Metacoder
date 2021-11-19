/*
 * GUI Database / Project links
 */
package metacoder.GUI;

import base.config.Componenttxt;
import base.config.TXT;
import base.controls.DIRFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JFileChooser;
import metacoder.controller.Controller;
import metacoder.data.Databaseproject;
import metacoder.data.Sourceproject;

/**
 * GUI Database / Project links
 * Manage Project folders to be linked to the active database from Panel Projectlist
 * @author Franky Laseure
 */
public class Projectdirectories extends VBox {
    
    private Controller controller;
    private Componenttxt txt;
        
    //data listeners
    /**
     * Selected database changed in panel Projectlist
     * Display project name
     * unbind old projectlist
     * bind new projectlist
     * build projects list
     */
    protected ChangeListener databasechanged = new ChangeListener<Databaseproject>() {
        @Override public void changed(ObservableValue<? extends Databaseproject> o,Databaseproject oldVal, Databaseproject newVal){
            databasename.setText(controller.getDatabaseproject().getDatabasename());
            if(oldVal!=null) oldVal.projectlistProperty().removeListener(projectschanged);
            controller.getDatabaseproject().projectlistProperty().addListener(projectschanged);
            showprojectsgrid();
        }
    };
    
    /**
     * Projects list (from selected database) changed
     * Show projects list
     */
    protected ChangeListener projectschanged = new ChangeListener<ArrayList<Sourceproject>>() {
        @Override public void changed(ObservableValue<? extends ArrayList<Sourceproject>> o,ArrayList<Sourceproject> oldVal, ArrayList<Sourceproject> newVal){
            showprojectsgrid();
        }
    };
    
    //event listeners
    /**
     * Save all changes to XML
     */
    private EventHandler savebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            controller.writeDatabaseproject();
        }
    };
    
    /**
     * Browse button event
     * Select new project map to add (to be linked to the selected database and added in the project list)
     */
    private EventHandler browsebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser(projectdir.getText());
            filechooser.addChoosableFileFilter(new DIRFilter());
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int chooserresult = filechooser.showDialog(null, "Select");
            if(chooserresult==filechooser.APPROVE_OPTION) {
                File selectedfile = filechooser.getSelectedFile();
                String newfilename = selectedfile.getAbsolutePath();
                projectdir.setText(newfilename);
            }
        }
    };

    /**
     * Add Project event
     * Link to the selected database
     * Add the the project list
     */
    private EventHandler addprojectbuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Sourceproject project = new Sourceproject(projectdir.getText());
            controller.getDatabaseproject().addProject(project);
        }
    };

    /**
     * Delete button event (each project has a delete button - X)
     * Remove project link from selected database
     */
    private EventHandler deletebuttonactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Button button = (Button)e.getSource();
            Sourceproject project = (Sourceproject)button.getUserData();
            controller.deleteProject(project);
        }
    };
    
    /**
     * Selected project in project list changed
     * Arrange toggle buttons
     * Set selected project
     */
    private EventHandler projecttoggleactionevent = new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            ToggleButton toggle = (ToggleButton)e.getSource();
            Sourceproject project = (Sourceproject)toggle.getUserData();
            if(toggle.isSelected()) {
                controller.setSelectedproject(project);
            } else {
                controller.setSelectedproject(null);
            }
        }
    };
    
    /**
     * Build GUI projects grid
     */
    private void showprojectsgrid() {
        projectgrid.getChildren().clear();
        ToggleButton toggle;
        Button deletebutton;
        int y = 0;
        for(Sourceproject project: controller.getDatabaseproject().getProjectlist()) {
            toggle = new ToggleButton(project.getProjectname());
            toggle.setUserData(project);
            toggle.setToggleGroup(projecttogglegroup);
            toggle.setAlignment(Pos.TOP_LEFT);
            toggle.setPrefWidth(200);
            toggle.setOnAction(projecttoggleactionevent);
            deletebutton = new Button("X");
            deletebutton.setOnAction(deletebuttonactionevent);
            projectgrid.add(toggle, 0, y);
            deletebutton.setUserData(project);
            projectgrid.add(deletebutton, 1, y);
            y++;
        }
    }
    
    //layout
    private static final String style = "defaultscreenbody";
    
    //controls
    private Label databasename = new Label();
    private TextField projectdir;
    private ScrollPane projectscroll = new ScrollPane();
    private GridPane projectgrid = new GridPane();
    private ToggleGroup projecttogglegroup = new ToggleGroup();
    
    /**
     * Create GUI for Project directories
     * @param controller: application controller
     */
    public Projectdirectories(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("projectdirectories");
        
        //data listeners
        controller.databaseprojectProperty().addListener(databasechanged);
        if(controller.getDatabaseproject()!=null) {
            controller.getDatabaseproject().projectlistProperty().addListener(projectschanged);
        }
        
        //controls
        Label projectlabel = new Label(txt.txt("LABEL_DATABASE"));
        Button savebutton = new Button(txt.txt("BUTTON_SAVE"));
        savebutton.setOnAction(savebuttonactionevent);
        projectdir = new TextField();
        Button browsebutton = new Button(txt.txt("BUTTON_BROWSE"));
        browsebutton.setOnAction(browsebuttonactionevent);
        Button addprojectbutton = new Button(txt.txt("BUTTON_ADDPROJECT"));
        addprojectbutton.setOnAction(addprojectbuttonactionevent);
        
        //layout
        this.getStyleClass().add(style);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
        
        HBox selectedprojectbox = new HBox();
        selectedprojectbox.setSpacing(10);
        selectedprojectbox.getChildren().addAll(projectlabel, databasename, savebutton);
        
        HBox addprojectbox = new HBox();
        addprojectbox.setSpacing(10);
        addprojectbox.getChildren().addAll(projectdir, browsebutton, new Separator(Orientation.HORIZONTAL), addprojectbutton);
        
        projectgrid.setVgap(10);
        projectgrid.setHgap(10);
        projectscroll.setContent(projectgrid);
        projectscroll.getStyleClass().add(style);
        projectscroll.setPrefSize(450, 2000);
        
        this.getChildren().addAll(selectedprojectbox, addprojectbox, projectscroll);
    }
    
}
