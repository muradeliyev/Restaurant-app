public class AdminAccount extends Account {
    private String position;
    AdminAccount(String email, String username, String password, String position) {
        super(email, username, password);

        if (position.equals("1")) this.position = "Manager";
        else this.position = "Chef";
    }

    public String getPosition() {
        return position;
    }
}