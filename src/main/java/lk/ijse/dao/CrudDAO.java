package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean add(T entity) throws SQLException, ClassNotFoundException;
    public boolean update(T entity) throws SQLException, ClassNotFoundException;
    public boolean exist(String id) throws SQLException, ClassNotFoundException;
    public int generateNewID() throws SQLException, ClassNotFoundException;
    public boolean delete(int id) throws SQLException, ClassNotFoundException;
    public T search(Object... args) throws SQLException, ClassNotFoundException;
}
