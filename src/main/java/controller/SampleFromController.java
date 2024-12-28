package controller;

import model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SampleFromController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtQuantity;
    public TextField txtPrice;
    public TextField txtDiscription;
    public TableView tblItem;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colQuantity;
    public TableColumn colPrice;
    public TableColumn colDescription;

    ArrayList<Item> itemArrayList = new ArrayList<>();

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        Integer quantity = Integer.parseInt(txtQuantity.getText());
        Double price = Double.parseDouble(txtPrice.getText());
        String discription = txtDiscription.getText();

        Item item = new Item(id,name,quantity,price,discription);

        itemArrayList.add(item);
        loadTable();

        System.out.println("--------------------------");
        itemArrayList.forEach(itemObj->{
            System.out.println(itemObj);
        });
        System.out.println("--------------------------");

    }

    private void loadTable(){

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost3306/thogakade",
                    "root",
                    "12345678");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();

        itemArrayList.forEach(item -> {
            itemObservableList.add(item);
        });

        tblItem.setItems(itemObservableList);

    }

    public void btnViewItemFormOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/view_item_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }
}
