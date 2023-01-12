package hu.petrik.konyvtarasztali;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("konyvtar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Könyvtár");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        if (Arrays.asList(args).contains("--stat")){
            Statisztika.main(args);
        }else{
            launch();
        }
    }
}