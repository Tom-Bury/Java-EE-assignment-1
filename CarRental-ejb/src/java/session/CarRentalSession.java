package session;

import java.util.ArrayList;
import java.util.Date;
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
import rental.CarType;
import rental.ReservationException;
import rental.Reservation;



// STATEFULL SESSION BEAN : retains state between method invocations
@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    
    /*
    VARIABLES
    */
    
    public String name;
    public List<Quote> quotes = new ArrayList<Quote>();

 
    /*
    Name
    */
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
   
    
    /*
    RentalCompanies
    */

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    
    
    /*
    Quotes
    */
    
    @Override
    public Quote createQuote(ReservationConstraints constraint) {
        Set<String> companies = getAllRentalCompanies();
        
        for (String companyName : companies) {
            CarRentalCompany currCompany = RentalStore.getRental(companyName);
            try {
                Quote currQuote = currCompany.createQuote(constraint, name);
                this.quotes.add(currQuote);
                return currQuote;
            } catch (ReservationException ex) {
                System.out.println("CarRentalSession: could not create quote with company "  + currCompany);
                Logger.getLogger(CarRentalSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;        
    }
    
    @Override
    public List<Quote> getCurrentQuotes() {
        return this.quotes;
    }
    
    @Override
    public void confirmQuotes() throws ReservationException {
        List<Reservation> reservations = new ArrayList<Reservation>();
        
        try {
            for (Quote q : this.quotes) {
                CarRentalCompany company = RentalStore.getRental(q.getRentalCompany());
                Reservation r = company.confirmQuote(q);
                reservations.add(r);
            }
        } catch (ReservationException e) {
            undoReservations(reservations);
            System.out.println("CarRentalSession: could not confirm quote");
            Logger.getLogger(CarRentalSession.class.getName()).log(Level.SEVERE, null, e);
            throw e;                
        }
        
    }
       
    private void undoReservations(List<Reservation> reservations) {
        for (Reservation r : reservations) {
            CarRentalCompany company = RentalStore.getRental(r.getRentalCompany());
            company.cancelReservation(r);
        }
    }
    
    
    
    /*
    Car availability
    */
    
    @Override
    public Set<CarType> checkForAvailableCarTypes(Date start, Date end) {
        Set<String> companies = getAllRentalCompanies();
        Set<CarType> availableCarTypes = new HashSet<CarType>();
        
        for (String companyName : companies){
            CarRentalCompany currCompany = RentalStore.getRental(companyName);
            Set<CarType> currAvailable = currCompany.getAvailableCarTypes(start, end);
            availableCarTypes.addAll(currAvailable);
        }
        
        return availableCarTypes;
    }
    
}
