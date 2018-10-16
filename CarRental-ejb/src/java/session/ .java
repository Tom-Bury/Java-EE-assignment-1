package session;

import java.util.HashSet;
import java.util.Set;
import javax.ejb.Stateful;
import rental.RentalStore;
import rental.ReservationConstraints;
import rental.Quote;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    String name;
    

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    public Quote createQuote(ReservationConstraints constraint) {
        
    }

    
    
}
