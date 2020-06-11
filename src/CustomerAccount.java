import java.util.ArrayList;

public class CustomerAccount extends Account {
    private ArrayList<Reservation> reservations;
    private ArrayList<Delivery> deliveries;

    CustomerAccount(String email, String username, String password) {
        super(email, username, password);
        this.reservations = new ArrayList<Reservation>();
        this.deliveries = new ArrayList<Delivery>();
    }

    public void makeReservation(String loc, String date, int ...meals) {
        reservations.add(new Reservation(loc, date, meals));
    }

    public void orderDelivery(int[] meals, String address, String paymentMethod) {
        this.deliveries.add(new Delivery(meals, address, paymentMethod));
    }
    public ArrayList<Reservation> getReservations() {
        return this.reservations;
    }
    public ArrayList<Delivery> getDeliveries() {
        return this.deliveries;
    }
}