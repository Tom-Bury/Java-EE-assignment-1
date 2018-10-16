package client;

import java.util.Date;
import javax.ejb.EJB;
import rental.ReservationConstraints;
import session.CarRentalSessionRemote;

public class Main {
    
    @EJB
    static CarRentalSessionRemote session;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        session.setName("Tom");
      
        
        
        System.out.println("CLIENT MAIN: found rental companies: "+session.getAllRentalCompanies());
        
        session.createQuote(new ReservationConstraints(new Date(), new Date(2018, 11, 12), "Compact", "Antwerp"));
        
        System.out.println("CLIENT MAIN: quotes: " + session.getCurrentQuotes());
    }
}
