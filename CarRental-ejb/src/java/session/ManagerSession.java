package session;

import java.util.List;
import javax.ejb.Stateless;
import rental.CarType;


// STATELESS SESSION BEAN : retains NO state between method invocations
@Stateless
public class ManagerSession implements ManagerSessionRemote {

    /*
    Request a list of cartypes from the given car rental company.
    */
    @Override
    public List<CarType> requestAllCarTypesOf(String company) {
        return null;
    }
    
    
    /*
    Request the number of reservations for each car type in the given car rental company.
    */
    @Override
    public int getNumberOfReservationsFor(String carType, String company) {
        return 0;
    }
    
    
    public String getCustomerWithMostReservations() {
        return null;
    }
}
