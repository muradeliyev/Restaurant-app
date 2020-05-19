import java.util.ArrayList;

public class DataContainer {
    static ArrayList<CustomerAccount> customers;
    static ArrayList<AdminAccount> admins;

    public DataContainer() {
        customers = new ArrayList<>();
        admins = new ArrayList<>();
    }

    void add(AdminAccount account) {
        admins.add(account);
    }
    void add(CustomerAccount account) {
        customers.add(account);
    }
    void remcveAdmin(String username) {
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

}
