public class Accounts {
    private String email;
    private String username;
    private String password;

    Accounts(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
}

