package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private TextField txtID;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private Label lblId;

    @FXML
    private AnchorPane nodePane;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);




    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();
//                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        int age = Integer.parseInt(txtAge.getText());
        String tel = txtPhoneNumber.getText();

        CustomerDTO customerDTO = new CustomerDTO(id, name, tel, address, age);
            try {
                boolean isSaved = customerBO.addCustomer(customerDTO);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());
        String name = txtName.getText();
        String address = txtAddress.getText();
        String tel = txtPhoneNumber.getText();
        int age = Integer.parseInt(txtAge.getText());

        CustomerDTO customerDTO = new CustomerDTO(id, name, tel, address, age);

            try {
                boolean isUpdated = customerBO.updateCustomer(customerDTO);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
    }






    private void clearFields() {
        txtName.setText("");
        txtName.setStyle(""); // Reset style to default

        txtAddress.setText("");
        txtAddress.setStyle(""); // Reset style to default

        txtPhoneNumber.setText("");
        txtAge.setStyle(""); // Reset style to default
    }



}
