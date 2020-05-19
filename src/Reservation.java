public class Reservation {
    private String location;
    private int[] meals;
    private String date;

    Reservation(String loc, String date, int... mealnums) {
        this.location = loc;
        this.date = date;
        this.meals = mealnums;

    }
}
