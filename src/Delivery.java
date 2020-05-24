import java.util.Arrays;

public class Delivery {
    private int[] meals;
    private String adress;
    private String paymentMethod;
    Delivery(int[] meals, String adress, String paymentMethod) {
        this.meals = meals;
        this.adress = adress;
        this.paymentMethod = paymentMethod;
    }
    public String toString() {
        return String.format("address: %s, payment or card number: %s",
             this.adress, this.paymentMethod);
    }
}
