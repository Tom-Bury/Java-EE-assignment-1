package session;

import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.Quote;
import rental.ReservationConstraints;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    
    List<Quote> createQuote(ReservationConstraints constraint);
    
    List<Quote> getCurrentQuotes();
    
    void setName(String name);
}
