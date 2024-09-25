package lk.ijse.dao.custom.impl;

import lk.ijse.config.SessionFactoryConfiguration;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        try {
            // HQL query to fetch all customers
            Query<Customer> query = session.createQuery("FROM Customer", Customer.class);

            // Fetch result list from query
            List<Customer> customerList = query.list();

            // Convert List to ArrayList (if needed)
            return new ArrayList<>(customerList);

        } finally {
            session.close(); // Always close the session after use
        }
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public int generateNewID() throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        try {
            // HQL query to fetch the latest customer id
            Query<Integer> query = session.createQuery("SELECT c.id FROM Customer c ORDER BY c.id DESC", int.class);
            query.setMaxResults(1); // Limit to get only the latest result

            int lastId = query.uniqueResult(); // Fetch the last customerID

            if (lastId != 0) {
                return lastId + 1;
            } else {
                return 1;
            }
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery query = session.createNativeQuery("delete from customer where id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Customer search(Object... args) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfiguration.getInstance().getSession();

        try {
            // HQL query to search customer by id
            String hql = "FROM Customer c WHERE c.id = :id";
            Query<Customer> query = session.createQuery(hql, Customer.class);

            // Set the id parameter from args
            query.setParameter("id", args[0].toString());

            // Execute query and get single result or return null if no result
            Customer customer = query.uniqueResult();

            return customer; // Return the found customer or null if not found

        } finally {
            session.close(); // Always close the session
        }
    }

}
