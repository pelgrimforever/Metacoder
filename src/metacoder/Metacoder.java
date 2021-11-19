/*
 * Application
 */
package metacoder;

import base.AppController;
import base.config.Config;
import base.config.TXT;
import base.framework.TabToolbarApp;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import metacoder.controller.Controller;
import metacoder.util.Fileutilities;

/**
 * Application main class
 * with usage of some classes from FXProjectBase
 * without full usage of the widget framework in FXProjectBase
 * @author Franky Laseure
 */
public class Metacoder extends Application {

    //server properties
    public static final String server = "";
    public static final String projectpath = Fileutilities.getCurrentDir();

    //visual properties
    private static boolean isEmbedded;
    public static boolean getisEmbedded() { return isEmbedded; }
    private static Rectangle2D visualbounds;
    private final static float prefferedwidth = 1400;
    ObservableList<Screen> screens;
    
    private static Map applicationArguments;
    
    private static HostServices services;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * initialize Config from config.xml
     * construct GUI
     * @param primaryStage Application stage
     */
    @Override
    public void start(Stage primaryStage) {
        //get application arguments
        applicationArguments = getParameters().getNamed();
        //get server host services
        services = getHostServices();
        
        //VISUAL PROPERTIES
        //check if application is embedded
        isEmbedded = primaryStage.getWidth()>0;        
        visualbounds = Screen.getPrimary().getVisualBounds();
        if(visualbounds.getWidth()>prefferedwidth) { 
            visualbounds = new Rectangle2D(visualbounds.getMinX(), visualbounds.getMinY(), prefferedwidth, visualbounds.getHeight());
        }
        screens = Screen.getScreens();
        
        //load local config
        Config config = Config.buildInstance(server, projectpath);
        
        primaryStage.titleProperty().bind(TXT.applicationTitleProperty());
        
        StackPane root = new StackPane();
        Scene scene = new Scene(root, visualbounds.getWidth(), visualbounds.getHeight());
        //load resources
        scene.getStylesheets().add(this.getClass().getResource("default.css").toExternalForm());        
        
        if(config.hasMenu()) {
            TabToolbarApp tabtoolbarcontrol = new TabToolbarApp();
            root.getChildren().add(tabtoolbarcontrol);
        } else {
            try {
                Node node = (Node)Class.forName(config.DEFAULT_SCREEN).newInstance();
                root.getChildren().add(node);
            }
            catch(ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                AppController.showMessage(e.getMessage());
            }
        }
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
