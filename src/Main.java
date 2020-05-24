import java.util.ArrayList;
import java.util.Scanner;

class Main {
    static Scanner scan = new Scanner(System.in);
    static DataContainer data = new DataContainer();

    public static void main(String args[]) {
        homePage();
    }

    static void homePage() {
        System.out.println("\n\n      Welcome      ");

        System.out.println("1. Log in");
        System.out.println("2. Create new account");
        System.out.println("3. Quit\n");

        String c = input("-> ").trim();
        while (true) {
            if (c.equals("1") || c.equals("2") || c.equals("3")) break;

            c = input("-> ");
        }

        switch (c) {
            case "1":
                login();
                break;

            case "2":
                System.out.println("Create:\n1. Customer account\n2. Administrator account");

                c = input("-> ");
                while (true) {
                    if (c.equals("1") || c.equals("2")) break;
                    c = input("-> ");
                }

                if (c.equals("1")) signIn("customer");
                else signIn("admin");
                break;

            default:
                break;
        }
    }
    static void signIn(String as) {
        System.out.printf("   sign in as %s", as);
        String email = input(   "\nemail                       : ");
        while (!(email.contains("@"))) {
            email = input(      "email (must contain @ sign) : ");
        }
        String username = input("username                    : ");
        String password = input("password (at least 7char)   : ");
        while (true) {
            if (password.length() >= 7) break;
            password = input("password (at least 7char)   : ");
        }

        if (as.equals("admin")) {
            System.out.println("choose your position (1/2/3):\n1. Manager\n2. Assistant Manager\n3. Chef");
            String position = input("-> ");
            while (true) {
                if (position.equals("1") || position.equals("2") || position.equals("3")) break;
                position = input("-> ");
            }
            AdminAccount account = new AdminAccount(email, username, password, position);
            data.add(account);
            adminMenu(account);
        } else {
            CustomerAccount account = new CustomerAccount(email, username, password);
            data.add(account);
            customerMenu(account);
        }
    }
    static void login() {
        String email = input("email   : ");
        String password = input("password: ");

        Account account = data.getAccount(email, password);

        if (account instanceof CustomerAccount) customerMenu(account);
        else if (account instanceof AdminAccount) adminMenu(account);
        else {
            System.out.println("\neither the password or the email is wrong");
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
    static void customerMenu(Account account) {
        System.out.println(String.format("\n   %s, Hello   ", account.getUsername()));
        System.out.println("1. Make a reservation\n2. Order a delivery\n3. Show reservations and deliveries\n4. Log out");

        String c = input("-> ");
        while (true) {
            if (c.equals("1")) {
                makeReservation((CustomerAccount) account);
                break;
            } else if (c.equals("2")) {
                orderDelivery((CustomerAccount) account);
                break;
            } else if (c.equals("3")) {
                ArrayList<Reservation> reserves = ((CustomerAccount) account).getReservations();
                System.out.println("Reservations:");
                for (int i = 0; i < reserves.size(); i++) {
                    System.out.println(i+1 +". "+ reserves.get(i).toString());
                }
                System.out.println("\nDeliveries:");
                ArrayList<Delivery> deliveries = ((CustomerAccount) account).getDeliveries();
                for (int i = 0; i < deliveries.size(); i++) {
                    System.out.println(i+1 +". " + deliveries.get(i).toString());
                }

            } else if (c.equals("4")) {
                homePage();
                break;
            }
            c = input("-> ");
        }
    }
    static void adminMenu(Account account) {
        System.out.println(String.format("\n   %s, Hello   ", account.getUsername()));
        System.out.println("1. Edit meal menu\n2. Edit workers' table\n3. Log out");

        String c = input("-> ");
        while (true) {
            if (c.equals("1")) {
                editMealMenu((AdminAccount) account);
                break;
            } else if (c.equals("2")) {
                editWorkersTable((AdminAccount) account);
                break;
            } else if (c.equals("3")) {
                homePage();
                break;
            }
            c = input("-> ");
        }
    }
    static void makeReservation(CustomerAccount account) {
        System.out.println("\n\n  New Reservation  ");
        System.out.println("Choose our location:\n1. Genclik\n2. 28 May\n3. Neftchiler\n4. Nerimanov\n5. Memar Acami");
        String[] arr = {"1", "2", "3", "4", "5"};
        String location = input("-> ");
        while (true) {
            if (in(location, arr)) break;
            location = input("-> ");
        }
        Menu.print_menu();
        int[] items = readStream("enter the numbers of meals seperated with a single space(enter to skip): ", -1);

        String date;
        while (true) {
            date = input("enter date and time (dd/mm/yyyy): ");
            if (Reservation.isValidDate(date)) break;
        }

        System.out.println("\n1. reserve\n2. cancel");
        String end = input("-> ").trim();
        while (true) {
            if (end.equals("1")) {
                account.makeReservation(location, date, items);
                break;
            } else if (end.equals("2"))
                break;
            end = input("-> ");
        }
        customerMenu(account);
    }
    static void orderDelivery(CustomerAccount account) {
        // entering credit card is not working...
        // get rid of int card and replace it with normal String
        // hint: creditCard.matches("[0-9]+")
        // using regex can solve this problem
        // 16 digit number can not be stored in an integer value.
        Menu.print_menu();
        int[] items = readStream("enter the meal numbers(up to 5 meals): ", -1);
        String adress = input("enter your address: ");

        System.out.println("Enter payment method:\n1. pay at delivery\n2. pay with credit card");
        int card = 0;
        while (true) {
            String payment = input("-> ");
            if (payment.equals("1")) card = 0;
            else if (payment.equals("2")) {
                System.out.println("Enter your credit card number(must contain 16 digits):");
                while (true) {
                    String creditCard = input("card number: ");
                    if (creditCard.length() != 16) continue;
                    else break;
                }
                break;
            }
        }
        System.out.printf("total price: %s", Menu.calcPrice(items));
        System.out.println("\n1. Order\n2. Cancel");
        while (true) {
            String c = input("-> ");
            if (c.equals("1")) {
                account.orderDelivery(items, adress,
                        card!=0?""+card:"at delivery");
                break;
            } else if (c.equals("2")) {
                break;
            }
        }
        customerMenu(account);
    }

    static void editMealMenu(AdminAccount account) {
        System.out.println("1. Add meal\n2. Remove\n3. Back");
        while (true) {
            String c = input("-> ");
            if (c.equals("1")) {
                System.out.println("1. Starters\n2. Desserts\n3. Drinks\n4. Main Course\n5. Side Dishes");
                int section;
                while (true) {
                    section = Integer.parseInt(input("-> ").trim());
                    if (0 < section && section <= 5) break;
                }
                String mealName = input("Enter meal name: ");
                String p = input("Enter the price: ");
                float price = Float.parseFloat(p);
                System.out.println("1. add\n2. cancel");
                while (true) {
                    c = input("-> ").trim();
                    if (c.equals("1")) {
                        Menu.add(section, mealName, price);
                        adminMenu(account);
                        break;
                    } else if (c.equals("2")) {
                        editMealMenu(account);
                        break;
                    }
                }
                break;
            }
            else if (c.equals("2")) {
                System.out.println("enter numbers of meals:");
                int[] meals;
                while (true) {
                    meals = readStream("-> ", -1);
                    if (meals.length == 1 && meals[0] == 0) {
                        continue;
                    }
                    else {
                        Menu.remove(meals);
                        adminMenu(account);
                        break;
                    }
                }
            }
            else if (c.equals("3")) {
                adminMenu(account);
                break;
            }
        }
    }
    static void editWorkersTable(AdminAccount account) {
        System.out.println("\n1. Add new worker\n2. remove\n3. back");
        while (true) {
            String c = input("-> ").trim();
            // adding new worker
            if (c.equals("1")) {
                String name = input( "Enter worker's name           : ");
                String email = input("email                       : ");
                while (!(email.contains("@"))) {
                    email = input(   "email (must contain @ sign)   : ");
                }

                System.out.println("1. Assistant manager\n2. Chef\n3. Waiter\n4. Dish washer");
                String position;
                while (true) {
                    position = input("-> ");
                    if (position.equals("1")) {
                        position = "Assitant manager";
                        break;
                    }
                    else if (position.equals("2")) {
                        position = "Chef";
                        break;
                    }
                    else if (position.equals("3")) {
                        position = "Waiter";
                        break;
                    }
                    else if (position.equals("4")) {
                        position = "Dish washer";
                        break;
                    }
                }
                String address = input("Enter worker's address: ");

                System.out.println("\n1. Add\n2. Cancel");
                while (true) {
                    c = input("-> ").trim();
                    if (c.equals("1")) {
                        data.add(new Worker(name, email, position, address));
                        adminMenu(account);
                        break;
                    } else if (c.equals("2")) {
                        editWorkersTable(account);
                        break;
                    }
                }
                break;
            }
            // removing worker////////////////////////////////////
            else if (c.equals("2") && data.workers.size() != 0) {
                show_workers();
                System.out.println("enter id number from the list above: ");
                int id = 0;
                boolean found = false;
                while (!found) {
                    try {
                        id = Integer.parseInt(input("-> "));
                    } catch (Exception e) {
                        continue;
                    }
//                    if (id > 0 && id <= Worker.nums) break;
                    for (int i = 0; i < data.workers.size(); i++) {
                        if (id == data.workers.get(i).id) {
                            found = true;
                            break;
                        }
                    }
                }
                System.out.println("\n1. Remove\n2. Cancel");
                while (true) {
                    c = input("-> ").trim();
                    if (c.equals("1")) {
                        data.removeWorker(id);
                        adminMenu(account);
                        break;
                    } else if (c.equals("2")) {
                        editWorkersTable(account);
                        break;
                    }
                }
                break;
            }
            else if (c.equals("3")) {
                adminMenu(account);
                break;
            }
        }
    }
    static void show_workers() {
        System.out.println("ID          Name          Position          Address");
        for (int i = 0; i < data.workers.size(); i++) {
            System.out.print(String.format("%3d     ", data.workers.get(i).id));
            System.out.print(Menu.center(data.workers.get(i).name, 14, ' '));
            System.out.print(Menu.center(data.workers.get(i).position, 18, ' '));
            System.out.print(Menu.center(data.workers.get(i).address, 17, ' '));
            System.out.println();
        }
        System.out.println("\n");
    }
    static String input(String message) {
        System.out.print(message);
        return scan.nextLine();
    }
    static boolean in(String item, String[] arr) {
        for (String s : arr)
            if (s.equals(item))
                return true;
        return false;
    }
    static int[] readStream(String message, int length) {
        try {
            String[] nums = input(message).trim().split(" ");
            if (length == -1) length = nums.length;
            int[] result = new int[length];
            for (int i = 0; i < length; i++)
                result[i] = Integer.parseInt(nums[i]);
            return result;
        }
        catch (Exception e) {
            return new int[]{0};
        }
    }
}
