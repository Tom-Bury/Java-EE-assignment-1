
package session;

import java.util.List;
import javax.ejb.Remote;
import rental.CarType;



@Remote
public interface ManagerSessionRemote {

    public List<CarType> requestAllCarTypesOf(String company);

    public int getNumberOfReservationsFor(String carType, String company);
    
}
