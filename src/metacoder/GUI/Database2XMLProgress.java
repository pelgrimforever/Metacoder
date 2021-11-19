/*
 * GUI Database XML generation progress
 */
package metacoder.GUI;

import base.config.Componenttxt;
import base.config.TXT;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import metacoder.controller.Controller;

/**
 * GUI Database XML generation progress
 * contains XML generation progress bar
 * @author Franky Laseure
 */
public class Database2XMLProgress extends VBox {
    
    private Controller controller;
    private Componenttxt txt;

    //controlls
    private Label progresslabel;
    private ProgressBar progressbar;
    
    //layout
    private static final String style = "defaultscreenbody";
    
    /**
     * create GUI for Database Project Generation
     * @param controller: application controller
     */
    public Database2XMLProgress(Controller controller) {
        this.controller = controller;
        txt = TXT.getComponenttxt("projectprogress");
        
        //controls
        progresslabel = new Label();
        progresslabel.textProperty().bind(controller.serviceexport2xml.progressstringProperty());
        progressbar = new ProgressBar();
        progressbar.setPrefWidth(400);
        progressbar.progressProperty().bind(controller.serviceexport2xml.progresspercProperty());
        
        //layout
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.getChildren().addAll(progressbar, progresslabel);
        this.getStyleClass().add(style);
    }
    
}
