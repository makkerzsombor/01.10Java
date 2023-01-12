package hu.petrik.konyvtarasztali;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    @FXML
    private TableView<Konyv> booksTableView;
    @FXML
    private TableColumn<Konyv, String> cimCol;
    @FXML
    private TableColumn<Konyv, String> szerzoCol;
    @FXML
    private TableColumn<Konyv, Integer> kiadasCol;
    @FXML
    private TableColumn<Konyv, Integer> oldalCol;

    private KonyvDB db;

    private List<Konyv> lista= new ArrayList<Konyv>();
    @FXML
    public void initialize(){
        cimCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        szerzoCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        kiadasCol.setCellValueFactory(new PropertyValueFactory<>("publish_year"));
        oldalCol.setCellValueFactory(new PropertyValueFactory<>("page_count"));
        try {
            db = new KonyvDB();
            readKonyv();
        } catch (SQLException e) {
            Platform.runLater(() -> {
                alert(Alert.AlertType.WARNING, "Hiba történt az adatbázis kapcsolat kialakításakor!",
                        e.getMessage());
                Platform.exit();
            });
        }
    }

    private void readKonyv() throws SQLException {
        List<Konyv> konyvek = db.readKonyv();
        booksTableView.getItems().clear();
        booksTableView.getItems().addAll(konyvek);
        lista.addAll(konyvek);
    }

    private Optional<ButtonType> alert(Alert.AlertType alertType, String headerText, String contentText){
        Alert alert = new Alert(alertType);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        return alert.showAndWait();
    }

    public void torlesClick(ActionEvent actionEvent) {
        Konyv selected = getSelectedKonyv();
        if (selected == null) return;

        Optional<ButtonType> optionalButtonType = alert(Alert.AlertType.CONFIRMATION,"Biztos, hogy törölni szeretné a kiválasztott könyvet?","");
        if (optionalButtonType.isEmpty() || !optionalButtonType.get().equals(ButtonType.OK) && !optionalButtonType.get().equals(ButtonType.YES)){
            return;
        }
        try {
            if (db.deleteKonyv(selected.getId())) {
                alert(Alert.AlertType.WARNING, "Sikeres Törlés!", "");
            }else{
                alert(Alert.AlertType.WARNING, "Sikertelen törlés!", "");
            }
            readKonyv();
        } catch (SQLException e) {
            sqlAlert(e);
        }
    }

    private void sqlAlert(SQLException e) {
        Platform.runLater(() -> {
            alert(Alert.AlertType.WARNING, "Hiba történt az adatbázis kapcsolat kialakításakor!",
                    e.getMessage());
        });
    }

    private Konyv getSelectedKonyv() {
        int selectedIndex = booksTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert(Alert.AlertType.WARNING, "Előbb válasszon ki egy könyvet a táblázatból!","");
            return null;
        }
        Konyv selected = booksTableView.getSelectionModel().getSelectedItem();
        return selected;
    }
}