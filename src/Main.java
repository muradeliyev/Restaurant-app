import javax.sound.midi.ShortMessage;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static DataContainer data = new DataContainer();

    public static void main(String args[]) {
        homePage();
    }

    static void homePage() {
        System.out.println("\n\n      Welcome      ");

        System.out.println("1. Log in");
        System.out.println("2. Create new account\n");

        String c = input("-> ");
        while (true) {
            if (c.equals("1") || c.equals("2")) break;

            c = input("-> ");
        }

        switch (c) {
            case "1":
                login();
                break;

            default:
                System.out.println("Create:\n1. Customer account\n2. Administrator account");

                c = input("-> ");

                if (c.equals("1")) signin("customer");
                else if (c.equals("2")) signin("admin");
                break;
        }
    }
    static String input(String message) {
        System.out.print(message);
        return scan.nextLine();
    }

    static void signin(String as) {
        scan.next();

    }
    static void login() {
        String email = input("email   : ");
        String password = input("password: ");

        Account account = data.getAccount(email, password);

        if (account instanceof CustomerAccount) customerMenu(account);
        else if (account instanceof AdminAccount) adminMenu(account);
        else {
            System.out.println("either the password or the email is wrong");
            System.out.println("1. back");
            System.out.println("2. try again");

            String c = input("-> ");
            while (true) {
                if (c.equals("1") || c.equals("2")) break;

                c = input("-> ");
            }

            if (c.equals("1")) homePage();
            else login();
        }
    }
    static void customerMenu(Account account) {}
    static void adminMenu(Account account) {}
}
