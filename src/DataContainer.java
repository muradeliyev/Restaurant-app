import java.util.ArrayList;

public class DataContainer {
    ArrayList<CustomerAccount> customers;
    ArrayList<AdminAccount> admins;
    ArrayList<Worker> workers;

    public DataContainer() {
        customers = new ArrayList<>();
        admins = new ArrayList<>();
        workers = new ArrayList<>();
    }

    void add(AdminAccount account) {
        admins.add(account);
    }
    void add(CustomerAccount account) {
        customers.add(account);
    }
    void add(Worker account) {
        workers.add(account);
    }
    void removeWorker(int id) {
        for (int i = 0; i < workers.size(); i++) {
            if (id == workers.get(i).id) {
                workers.remove(workers.get(i));
                break;
            }
        }
    }
    void removeAdmin(String username) {
        for (int i = 0; i < admins.size(); i++) {
            AdminAccount account = admins.get(i);
            if (account.getUsername().equals(username))
                admins.remove(account);
        }
    }
    void removeCustomer(String username) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerAccount account = customers.get(i);
            if (account.getUsername().equals(username))
                customers.remove(account);
        }
    }
    boolean isValidUsername(String username) {
        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).getUsername().equals(username))
                return false;

        for (int i = 0; i < admins.size(); i++)
            if (admins.get(i).getUsername().equals(username))
                return false;

        return true;
    }
    boolean isValidEmail(String email) {
        if (!(email.contains("@"))) return false;

        for (int i = 0; i < customers.size(); i++)
            if (customers.get(i).getEmail().equals(email))
                return false;

        for (int i = 0; i < admins.size(); i++)
            if (admins.get(i).getEmail().equals(email))
                return false;

        return true;
    }
    CustomerAccount getCustomer(String email, String password) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerAccount account = customers.get(i);
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }
    AdminAccount getAdmin(String email, String password) {
        for (int i = 0; i < admins.size(); i++) {
            AdminAccount account = admins.get(i);
            if (account.getEmail().equals(email) && account.getPassword().equals(password)) {
                return account;
            }
        }
        return null;
    }
    public Account getAccount(String email, String password) {
        Account account = getCustomer(email, password);
        if (account != null) return account;
        account = getAdmin(email, password);
        if (account != null) return account;

        return null;
    }
}