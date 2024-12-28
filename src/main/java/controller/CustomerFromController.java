package controller;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFromController implements Initializable {

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || txtAddress.getText().isEmpty() || txtSalary.getText().isEmpty()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Empty Fields");
            errorAlert.setHeaderText("Empty Fields");
            errorAlert.setContentText("Please fill all the required fields");
            errorAlert.showAndWait();
            return;
        }

        boolean isAdded = DBConnection.getInstance().getConnection().add(new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        )
        );

        if (isAdded) {
            loadTable();
            Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setTitle("Item Added");
            errorAlert.setHeaderText("Item Added Successfully.");
            errorAlert.showAndWait();

            clearFields();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Item Cant Added");
            errorAlert.setHeaderText("Failed to add item.");
            errorAlert.showAndWait();
        }

    }
    public void clearFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    @FXML
    void btnRemoveOnAction(ActionEvent event) {
        System.out.println("customer");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewItemFormOnAction(ActionEvent event) {

    }

    List<Customer> customerList = new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
    }

    private void loadTable() {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thogakade",
                    "root",
                    "12345678");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
            while (resultSet.next()){
                customerList.add(new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4)
                        )
                );
            }


            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        customerList.forEach(customer -> {
            customerObservableList.add(customer);
        });


        tblCustomers.setItems(customerObservableList);

        System.out.println(customerObservableList);
    }
}
