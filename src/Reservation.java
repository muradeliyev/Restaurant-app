import java.util.Arrays;
import java.util.Calendar;

public class Reservation {
    private String location;
    private int[] meals;
    private String date;

    Reservation(String loc, String date, int... mealnums) {
        this.location = loc;
        this.date = date;
        this.meals = mealnums;

    }
    public String toString() {
        return String.format("Location: %s,  meals: %s,  date: %s",
                this.location, Arrays.toString(this.meals), this.date);
    }

    static boolean isValidDate(String date) {
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        String[] parts = date.trim().split("/");
        try {
            int day = Integer.parseInt(parts[0].trim());
            int month = Integer.parseInt(parts[1].trim());
            int year = Integer.parseInt(parts[2].trim());

            cal.getTime();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}