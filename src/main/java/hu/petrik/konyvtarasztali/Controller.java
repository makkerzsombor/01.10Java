package hu.petrik.konyvtarasztali;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView booksTableView;
    @FXML
    private TableColumn<Konyv, String> cimCol;
    @FXML
    private TableColumn<Konyv, String> szerzoCol;
    @FXML
    private TableColumn<Konyv, Integer> kiadasCol;
    @FXML
    private TableColumn<Konyv, Integer> oldalCol;

    private KonyvDB db;


    @FXML
    public void initialize(){
        cimCol.setCellValueFactory(new PropertyValueFactory<>("cim"));
        szerzoCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        kiadasCol.setCellValueFactory(new PropertyValueFactory<>("publish_year"));
        oldalCol.setCellValueFactory(new PropertyValueFactory<>("page_count"));
        try {
            db = new KonyvDB();
            db.readKonyv();
        } catch (SQLException e) {
            Platform.runLater(() -> {
                alert(Alert.AlertType.WARNING, "Hiba történt az adatbázis kapcsolat kialakításakor!",
                        e.getMessage());
            });
        }
    }

    private Optional<ButtonType> alert(Alert.AlertType alertType, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait();
    }



    public void torlesClick(ActionEvent actionEvent) {

    }
}