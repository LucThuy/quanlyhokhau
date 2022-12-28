package cnpm.QuanLyNhanKhau;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    
    private static Scene sceneForm;
    private static Stage stageForm;

	private static Connector connector;
	
    @Override
    public void start(Stage stage) throws IOException {
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	double screenWidth = screenSize.getWidth();
    	double screenHeight = screenSize.getHeight();
    	
    	App.stage = stage;
    	App.stage.setWidth(screenWidth);
    	App.stage.setHeight(screenHeight);
    	App.stage.setMaximized(true);
    	
    	stageForm = new Stage();
    	App.stageForm.setWidth(screenWidth*0.5);
    	App.stageForm.setHeight(screenHeight*0.8);
    	
    	setConnector(new Connector());
    	
        scene = new Scene(loadFXML("view/ViewSignIn"));
        App.stage.setScene(scene);
        App.stage.show();
    }
    
    public static void addStageForm(String fxml) throws IOException {
    	if(stageForm.isShowing()) {
    		stageForm.requestFocus();
    		return;
    	}
    	sceneForm = new Scene(loadFXML(fxml));
    	stageForm.setScene(sceneForm);
    	stageForm.show();
    }
    
    public static void closeStageForm() {
    	if(stageForm.isShowing()) {
    		stageForm.close();
    	}
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static void setRootSceneForm(String fxml) throws IOException {
        sceneForm.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Scene getSceneForm() {
		return sceneForm;
	}
	public static void setSceneForm(Scene sceneForm) {
		App.sceneForm = sceneForm;
	}
	public static Connector getConnector() {
		return connector;
	}
	public static void setConnector(Connector connector) {
		App.connector = connector;
	}

}