package session;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import rental.CarRentalCompany;
import rental.CarType;
import rental.RentalStore;


// STATELESS SESSION BEAN : retains NO state between method invocations
@Stateless
public class ManagerSession implements ManagerSessionRemote {

    /*
    Request a list of cartypes from the given car rental company.
    */
    @Override
    public Collection<CarType> requestAllCarTypesOf(String company) {
        CarRentalCompany currCompany = RentalStore.getRental(company);
        return currCompany.getCarTypes();
    }
    
    
    /*
    Request the number of reservations for each car type in the given car rental company.
    */
    @Override
    public int getNumberOfReservationsFor(String carType, String company) {
        CarRentalCompany currCompany = RentalStore.getRental(company);
        return currCompany.getNbReservationsFor(carType);
    }
    
    
    @Override
    public String getCustomerWithMostReservations(String company) {
        CarRentalCompany currCompany = RentalStore.getRental(company);
        return currCompany.getCustomerWithMostReservations();
    }
    
    
    @Override
    public int getNbOfReservationsBy(String clientName) {
        List<CarRentalCompany> allCompanies = RentalStore.getAllCarRentalCompanies();
        int counter = 0;
        
        for (CarRentalCompany crc : allCompanies) {
            counter += crc.getNbReservationsBy(clientName);
        }
        
        return counter;
    }
}
