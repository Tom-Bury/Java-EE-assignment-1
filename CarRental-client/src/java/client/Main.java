package client;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import rental.ReservationConstraints;
import session.CarRentalSessionRemote;
import rental.CarType;
import rental.Reservation;
import rental.ReservationException;
import session.ManagerSessionRemote;

public class Main extends AbstractTestAgency<CarRentalSessionRemote, ManagerSessionRemote>{
    
    //@EJB
    //static CarRentalSessionRemote session;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Main main = new Main("simpleTrips");
        try {
            main.run();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Main(String scriptFile) {
        super(scriptFile);
    }

    
    /*
    Methodes afkomstig van AbstractTestAgency --> moeten wij een implementatie voor voorzien
    Niet vergeten om als de implementatie van een methode af is; de exception throw weg te doen.
    */
    
    
    /*
    METHODS FROM CLIENT SESSION BEAN
     */
    @Override
    protected CarRentalSessionRemote getNewReservationSession(String name) throws Exception {
        // De volgende code is overgenomen van de opgave deel 3.3
        InitialContext context = new InitialContext();
        CarRentalSessionRemote session = (CarRentalSessionRemote) context.lookup(CarRentalSessionRemote.class.getName());
        session.setName(name);
        
        System.out.println("\nCLIENT MAIN: started new reservationSession by " + name + "\n");
        
        return session;
    }
    

     @Override
    protected void checkForAvailableCarTypes(CarRentalSessionRemote session, Date start, Date end) throws Exception {
        Set<CarType> availableCarTypes = session.checkForAvailableCarTypes(start, end);
        
        System.out.println("CLIENT MAIN: Check available car types from " + start + " to " + end);
        
        for (CarType ct : availableCarTypes) {
            System.out.println(ct.toString());
        }
        System.out.println("\n");
    }
    
    
    
    @Override
    protected void addQuoteToSession(CarRentalSessionRemote session, String name, Date start, Date end, String carType, String region) throws Exception {
        ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
        session.createQuote(constraints);
    }

    
    @Override
    protected List<Reservation> confirmQuotes(CarRentalSessionRemote session, String name) throws ReservationException {
        return session.confirmQuotes(); 
    }
    
    
    

    


    
    /*
    METHODS FROM MANAGER SESSION BEAN
    */
    
    @Override
    protected ManagerSessionRemote getNewManagerSession(String name, String carRentalName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected int getNumberOfReservationsBy(ManagerSessionRemote ms, String clientName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected int getNumberOfReservationsForCarType(ManagerSessionRemote ms, String carRentalName, String carType) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   

    

    
}
