public class AdminAccount extends Accounts {
    private String position;
    AdminAccount(String email, String username, String password) {
        super(email, username, password);
        this.position = "manager";
    }
}
