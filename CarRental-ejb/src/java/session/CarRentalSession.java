package session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import rental.RentalStore;
import rental.ReservationConstraints;
import rental.Quote;
import rental.CarRentalCompany;
import rental.ReservationException;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    
    public String name;
    public List<Quote> quotes = new ArrayList<Quote>();

 
    public void setName(String name) {
        this.name = name;
    }
    

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    @Override
    public List<Quote> createQuote(ReservationConstraints constraint) {
        Set<String> companies = getAllRentalCompanies();
        
        
        for (String companyName : companies) {
            CarRentalCompany currCompany = RentalStore.getRental(companyName);
            try {
                Quote currQuote = currCompany.createQuote(constraint, name);
                this.quotes.add(currQuote);
            } catch (ReservationException ex) {
                System.out.println("CLIENT EJB: could not create quote with company "  + currCompany);
                Logger.getLogger(CarRentalSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.quotes;        
    }
    
    
    
    public List<Quote> getCurrentQuotes() {
        return this.quotes;
    }

    
    
}
