/*
 * GUI Main panel
 */
package metacoder.GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import metacoder.controller.Controller;

/**
 * GUI Main panel
 * Contains application controller
 * Manage all sub panels
 * Arranges all subpanels in a grid
 * @author Franky Laseure
 */
public class Projectcanvas extends GridPane {
    
    private Controller controller = new Controller();
    
    //layout
    private static final String style = "defaultscreen";

    /**
     * Constructor
     * Create Project Main panel
     * Initiate all sub panels
     * Arrange GUI details for sub panels
     */
    public Projectcanvas() {
        //controls
        Databaselist databaselist = new Databaselist(controller);
        Databaseparameters databaseparameters = new Databaseparameters(controller);
        Database2XMLProgress database2xmlprogress = new Database2XMLProgress(controller);
        Projectmetacodelist projectmetacodemaps = new Projectmetacodelist(controller);
        Projectdirectories projectdirectories = new Projectdirectories(controller);
        ProjectGeneration projectgeneration = new ProjectGeneration(controller);
        
        //layout
        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(10));
        
        this.add(databaselist, 0, 0);
        this.add(databaseparameters, 0, 1);
        this.add(database2xmlprogress, 0, 2);
        this.add(projectdirectories, 1, 0);
        this.add(projectmetacodemaps, 2, 0);
        this.add(projectgeneration, 1, 1, 2, 1);
        this.getStyleClass().add(style);
    }
    
}
