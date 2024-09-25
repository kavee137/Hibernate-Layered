package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,FABRIC,USER,RESERVATION,RESERVATIONDETAILS,PAYMENT,VIEWRESERVATION,ORDER,ORDERDETAILS,VIEWORDER,MAINDASHBOARD,QUERY,LOGIN
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }

}
