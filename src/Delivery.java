import java.util.Arrays;

public class Delivery {
    private int[] meals;
    private String address;
    private String paymentMethod;
    Functions functions = new Functions();
    Delivery(int[] meals, String address, String paymentMethod) {
        this.meals = meals;
        this.address = address;
        this.paymentMethod = paymentMethod;
    }
    public String toString() {
        return String.format("%s%s",
                functions.center(this.address, 21, ' '),
                functions.center(this.paymentMethod, 32, ' '));
    }
}
