/*
 * Service: Export code to projects
 */
package metacoder.controller;

import metacoder.codegeneration.Export2Projects;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import metacoder.data.Sourceproject;

/**
 * Service: Export code to projects
 * Generate code based on selected database, linked projects, metacode maps per project and program language parameters for each metacode
 * Store code in project maps
 * @author Franky Laseure
 */
public class ServiceExport2Projects extends Service<Boolean> {
    
    private final Controller controller;
    
    private final Export2Projects export2projects;
    
    //Export progress message to display alongside the progress bar
    private final StringProperty progressstring = new SimpleStringProperty();
    public final String getProgressstring(){return progressstring.get();}
    public final void setProgressstring(String value) { progressstring.set(value); }
    public StringProperty progressstringProperty() {return progressstring;}

    //progress bar value
    private final DoubleProperty progressperc = new SimpleDoubleProperty(0);
    public final Double getProgressperc(){return progressperc.get();}
    public final void setProgressperc(double value) { progressperc.set(value); }
    public DoubleProperty progresspercProperty() {return progressperc;}
    
    public ServiceExport2Projects(Controller controller) {
        this.controller = controller;
        export2projects = new Export2Projects();
    }
    
    /**
     * Code generation
     * Iterate all selected metacode maps per project
     * @return true
     */
    @Override
    protected Task createTask() {
        return new Task<Boolean>() {
            protected Boolean call() throws Exception {
                Platform.runLater(() -> {
                    controller.setGuilocked(true);
                    setProgressstring("INIT");
                });
                export2projects.setDatabaseproject(controller.getDatabaseproject());
                export2projects.setDbproperties(controller.getDbproperties());
                int projectcount = controller.getDatabaseproject().getProjectlist().size();
                int projectcounter = 0;
                //loop all project maps linked to a database
                Platform.runLater(() -> {
                    setProgressperc(0d);
                });
                for(Sourceproject project: controller.getDatabaseproject().getProjectlist()) {
                    int metacodecount = project.getMetacodelist().size();
                    int metacodecounter = 0;
                    final String projectname = project.getProjectname();
                    final String projectdir = project.getProjectdirectory();
                    //loop all metacode linked to a project map
                    for(final String metacode: project.getMetacodelist()) {
                        Platform.runLater(() -> {
                            setProgressstring(projectname + " - " + metacode);
                        });
                        //create project files start
                        export2projects.processmetacode(project, metacode);
                        //create project files end
                        metacodecounter++;
                        final double metacodeprogress = (double)metacodecounter / metacodecount;
                        final double javaprogress = 
                                (double)projectcounter / projectcount
                                + (double)1 / projectcount * metacodeprogress;
                        Platform.runLater(() -> {
                            setProgressperc(javaprogress);
                        });
                    }
                    projectcounter++;
                    final double javaprogress = (double)projectcounter / projectcount;
                    Platform.runLater(() -> {
                        setProgressperc(javaprogress);
                    });
                }
                
                controller.setGuilocked(false);
                return true;
            }
        };
    }
    
    
}
