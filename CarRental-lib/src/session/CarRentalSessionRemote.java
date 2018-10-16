package session;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.CarType;
import rental.Quote;
import rental.ReservationConstraints;
import rental.ReservationException;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    
    Quote createQuote(ReservationConstraints constraint);
    
    List<Quote> getCurrentQuotes();
    
    void setName(String name);
    
    void confirmQuotes() throws ReservationException;
    
    Set<CarType> checkForAvailableCarTypes(Date start, Date end);
}
