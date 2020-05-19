import java.util.ArrayList;

public class CustomerAccount extends Accounts {
    private ArrayList<Reservation> reservations;

    CustomerAccount(String email, String username, String password) {
        super(email, username, password);
        this.reservations = new ArrayList<Reservation>();
    }

    public void makeReservation(String loc, String date, int ...meals) {
        reservations.add(new Reservation(loc, date, meals));
    }
}
