import java.time.LocalDate;
import java.util.Arrays;

public class Reservation {
    private String location;
    private int[] meals;
    private String date;
    Functions functions = new Functions();

    Reservation(String loc, String date, int... mealnums) {
        this.location = loc;
        this.date = date;
        this.meals = mealnums;
    }
    public String toString() {
        return String.format("%s%s%s",
                functions.center(this.location, 20, ' '),
                functions.center(Arrays.toString(this.meals), 20, ' '),
                functions.center(this.date, 7, ' '));
    }
    static boolean isValidDate(String date) {
        String[] parts = date.trim().split("/");
        try {
            int day = Integer.parseInt(parts[0].trim());
            int month = Integer.parseInt(parts[1].trim());
            int year = Integer.parseInt(parts[2].trim());

            String[] now = LocalDate.now().toString().split("-");
            int now_day = Integer.parseInt(now[2]);
            int now_month = Integer.parseInt(now[1]);
            int now_year = Integer.parseInt(now[0]);

            if (year < now_year) return false;
            else if (year == now_year) {
                if (month < now_month) return false;
                else if (month == now_month) {
                    if (day <= now_day) return false;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }
}