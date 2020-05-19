public class AdminAccount extends Accounts {
    private String position;
    AdminAccount(String email, String username, String password, String position) {
        super(email, username, password);
        this.position = position;
    }
}
