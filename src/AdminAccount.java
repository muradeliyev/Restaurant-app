public class AdminAccount extends Account {
    private String position;
    AdminAccount(String email, String username, String password, String position) {
        super(email, username, password);
        this.position = position;
    }
}