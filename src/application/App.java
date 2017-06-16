package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		final String appName = "Kody Korekcyjne";
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/pl/kodykorekcyjne/view/MainPane.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz zakoñczyæ program?");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
					} else
					{
						we.consume();
					}
				});
			});

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args)
	{
		launch(args);
	}
	
}
