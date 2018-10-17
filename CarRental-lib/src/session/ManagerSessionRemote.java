
package session;

import java.util.Collection;
import javax.ejb.Remote;
import rental.CarType;



@Remote
public interface ManagerSessionRemote {

    public Collection<CarType> requestAllCarTypesOf(String company);

    public int getNumberOfReservationsFor(String carType, String company);
    
    public String getCustomerWithMostReservations(String company);
    
}
