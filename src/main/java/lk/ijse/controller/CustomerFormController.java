package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.view.tdm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    CustomerBO customerBO  = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    public void initialize() {
        getCurrentCustomerId();
        setCellValueFactory();
        loadAllCustomers();
        showSelectedUserDetails();
    }

    private void showSelectedUserDetails() {
        CustomerTm selectedUser = tblCustomer.getSelectionModel().getSelectedItem();
        tblCustomer.setOnMouseClicked(event -> showSelectedUserDetails());
        if (selectedUser != null) {
            txtID.setText(String.valueOf(selectedUser.getId()));
            txtName.setText(selectedUser.getName());
            txtAge.setText(String.valueOf(selectedUser.getAge()));
            txtAddress.setText(selectedUser.getAddress());
            txtPhoneNumber.setText(selectedUser.getPhoneNumber());
        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        try {
            /*Get all customers*/
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();

            for (CustomerDTO c : allCustomers) {
                tblCustomer.getItems().add(new CustomerTm(c.getId(),c.getName(),c.getAddress(), c.getAge(), c.getPhoneNumber()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void getCurrentCustomerId() {
        try {
            int nextCustomerId = customerBO.generateNewCustomerID();

            txtID.setText(String.valueOf(nextCustomerId));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        getCurrentCustomerId();
    }

    private void clearFields() {
        txtName.setText(null);
        txtAddress.setText(null);
        txtPhoneNumber.setText(null);
        txtAge.setText(null);
        getCurrentCustomerId();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtID.getText());

        try {
            boolean isDeleted = customerBO.deleteCustomer(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();
                initialize();
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
                    initialize();
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
                initialize();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int id = Integer.parseInt(txtID.getText());
        Customer customer = customerBO.customerSearch(id);

        if (customer != null) {
            txtID.setText(String.valueOf(customer.getId()));
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtPhoneNumber.setText(customer.getPhoneNumber());
            txtAge.setText(String.valueOf(customer.getAge()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }
}
