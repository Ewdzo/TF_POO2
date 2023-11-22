package Interface;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class mainApplication extends Application{

	  @Override
	    public void start(Stage stage) throws IOException {
	        FXMLLoader fxmlLoader = new FXMLLoader(mainApplication.class.getResource("main-view.fxml"));
	        Scene scene = new Scene((Parent) fxmlLoader.load(), 500, 650);
	        stage.setTitle("Hello!");
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
	    }
	public static void main(String[] args) {
		launch();
		 

	}

}
