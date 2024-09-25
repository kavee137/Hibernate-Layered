package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public boolean deleteCustomer(int id) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException ;

    public int generateNewCustomerID() throws SQLException, ClassNotFoundException;

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
}
