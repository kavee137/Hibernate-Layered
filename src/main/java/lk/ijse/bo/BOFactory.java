package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,PRODUCT,EMPLOYEE,SUPPLIER,FABRIC,USER,RESERVATION,RESERVATIONDETAILS,PLACERESERVATION,VIEWRESERVATION,ORDER,VIEWORDER,MAINDASHBOARD,LOGIN
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;
        }
    }

}
