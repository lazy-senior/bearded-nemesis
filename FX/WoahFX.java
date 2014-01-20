package FX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WoahFX extends Application {

	public static void main(String[] args) {
        // Launch the Application 
        launch(args);   
    }

	@Override
	public void start(Stage stage) throws Exception {
		WoahGUI wg = new WoahGUI();
//		Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
		Scene scene = new Scene(wg);
		scene.getStylesheets().add("FX/WoahGUI.css");
		
		stage.setTitle("WoahTTT");
		
		stage.setScene(scene);
		
		stage.setMinWidth(200);
		stage.setMaxWidth(200);
		
		stage.show();
		
	}

		

}
