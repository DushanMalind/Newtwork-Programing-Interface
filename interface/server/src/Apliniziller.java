import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Apliniziller extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(this.getClass().getResource("view/serverFrom.fxml"));
        Scene mainScene=new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
