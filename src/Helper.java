import java.util.ArrayList;

public class Helper {
    DataContainer data = new DataContainer();
    Functions functions = new Functions();
    Menu menu = new Menu();
    void homePage() {
        System.out.println("\n\n      Welcome      ");

        System.out.println("1. Log in");
        System.out.println("2. Create new account");
        System.out.println("3. Quit\n");

        String c = functions.input("=> ").trim();
        while (true) {
            if (c.equals("1") || c.equals("2") || c.equals("3")) break;

            c = functions.input("=> ");
        }

        switch (c) {
            case "1":
                login();
                break;

            case "2":
                System.out.println("Create:\n1. Customer account\n2. Administrator account");

                c = functions.input("=> ");
                while (true) {
                    if (c.equals("1") || c.equals("2")) break;
                    c = functions.input("=> ");
                }

                if (c.equals("1")) signIn("customer");
                else signIn("admin");
                break;

            default:
                break;
        }
    }
    void signIn(String as) {
        System.out.printf("   sign in as %s\n", as);
        String email;
        do {
            email = functions.input(      "email (example@mail.com)    : ");
        } while (!(data.isValidEmail(email)));

        String username = functions.input("username                    : ");
        String password = functions.input("password (at least 7char)   : ");
        while (true) {
            if (password.length() >= 7) break;
            password = functions.input("password (at least 7char)   : ");
        }

        if (as.equals("admin")) {
            System.out.println("choose your position (1 or 2):\n1. Manager\n2. Chef");
            String position;
            do {
                position = functions.input("=> ");
            } while (!("12".contains(position) && !position.equals("")));

            AdminAccount account = new AdminAccount(email, username, password, position);
            data.add(account);
            adminMenu(account);
        } else {
            CustomerAccount account = new CustomerAccount(email, username, password);
            data.add(account);
            customerMenu(account);
        }
    }
    void login() {
        String email = functions.input("email   : ");
        String password = functions.input("password: ");

        Account account = data.getAccount(email, password);

        if (account instanceof CustomerAccount) customerMenu((CustomerAccount) account);
        else if (account instanceof AdminAccount) adminMenu((AdminAccount)account);
        else {
            System.out.println("\neither the password or the email is wrong");
            System.out.println("1. back");
            System.out.println("2. try again");

            String c = functions.input("=> ");
            while (true) {
                if (c.equals("1") || c.equals("2")) break;

                c = functions.input("=> ");
            }

            if (c.equals("1")) homePage();
            else login();
        }
    }
    void customerMenu(CustomerAccount account) {
        System.out.println(String.format("\n   %s, Hello   ", account.getUsername()));
        System.out.println("1. Make a reservation\n2. Order a delivery\n3. Show reservations and deliveries\n4. Log out");

        String c = functions.input("=> ");
        while (true) {
            if (c.equals("1")) {
                makeReservation(account);
                break;
            } else if (c.equals("2")) {
                orderDelivery( account);
                break;
            } else if (c.equals("3")) {
                ArrayList<Reservation> reserves = account.getReservations();
                System.out.println("Reservations:");
                System.out.println("         LOCATION             MEALS         DATE");
                for (int i = 0; i < reserves.size(); i++) {
                    System.out.println(i+1 +". "+ reserves.get(i).toString());
                }
                ArrayList<Delivery> deliveries = account.getDeliveries();
                System.out.println("\nDeliveries:");
                System.out.println("          ADDRESS         PAYMENT METHOD / CARD NUMBER");
                for (int i = 0; i < deliveries.size(); i++) {
                    System.out.println(i+1 +". " + deliveries.get(i).toString());
                }
                System.out.println("------------------------------------------------------------------\n");
            } else if (c.equals("4")) {
                homePage();
                break;
            }
            c = functions.input("=> ");
        }
    }
    void adminMenu(AdminAccount account) {
        System.out.println(functions.center("HOME", 16, ' '));
        System.out.println(String.format("\n   %s, Hello   ", account.getUsername()));

        if (account.getPosition().equals("Manager")) {
             System.out.println("1. Add worker\n2. Remove worker\n3. Worker table\n4. Log out");
             String c;
             do {
                 c = functions.input("=> ").trim();
             } while (!("1234".contains(c) && !c.equals("")));

             // adding new worker
             if (c.equals("1")) {
                 String name = functions.input("Enter worker's name           : ");
                 String email = functions.input("email                         : ");
                 while (!(email.contains("@") && !email.equals(""))) {
                     email = functions.input("email (must contain @ sign)   : ");
                 }

                 System.out.println("1. Chef\n2. Waiter\n3. Dish washer");
                 String position;
                 do {
                     position = functions.input("=> ").trim();
                 } while (!("123".contains(position) && !position.equals("")));

                 switch (position) {
                     case "1":
                         position = "Chef";
                         break;
                     case "2":
                         position = "Waiter";
                         break;
                     case "3":
                         position = "Dish washer";
                         break;
                 }

                 String address = functions.input("Enter worker's address: ");

                 System.out.println("\n1. Add\n2. Cancel");
                 do {
                     c = functions.input("=> ").trim();
                 } while (!(c.equals("1") || c.equals("2")));

                 if (c.equals("1")) { // confirmed to be added as a new worker
                     data.add(new Worker(name, email, position, address));
                     adminMenu(account);
                 }
                 else adminMenu(account);
             }
             // removing worker
             else if (c.equals("2") && data.workers.size() != 0) {
                 show_workers();
                 System.out.println("Enter id number from the table above:");
                 int id = 0;
                 boolean found = false;
                 while (!found) {
                     try {
                         id = Integer.parseInt(functions.input("=> "));
                     } catch (Exception e) {
                         continue;
                     }
                     for (int i = 0; i < data.workers.size(); i++)
                         if (id == data.workers.get(i).id) {
                             found = true;
                             break;
                         }
                 }
                 System.out.println("\n1. Remove\n2. Cancel");
                 do {
                     c = functions.input("=> ").trim();
                 } while (!(c.equals("1") || c.equals("2")));

                 if (c.equals("1")) {
                     data.removeWorker(id);
                     System.out.println("The worker is successfully removed...");
                     show_workers();
                     System.out.println("--------------------------------------");
                 }

                 adminMenu(account);
             }
             else if (c.equals("3")) {
                 show_workers();
                 adminMenu(account);
             }
             else homePage();
        }
        else {
            System.out.println("1. Add new meal\n2. Remove meal\n3. Log out");
            String c;
            do {
                c = functions.input("=> ");
            } while (!("123".contains(c) && !c.equals("")));
            // ADDING NEW MEAL TO THE MENU
            if (c.equals("1")) {
                System.out.println("1. Starters\n2. Desserts\n3. Drinks\n4. Main Course\n5. Side Dishes");
                int section = 0;
                do {
                    try {
                        section = Integer.parseInt(functions.input("=> ").trim());
                    } catch (Exception e) {
                        continue;
                    }
                } while (!(0 < section && section <= 5));

                String mealName = functions.input("Enter meal name: ");
                String p;
                float price;
                while (true){
                    try {
                        p = functions.input("Enter the price: ").trim();
                    price = Float.parseFloat(p);
                    break;
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
                System.out.println("1. add\n2. cancel");
                while (true) {
                    c = functions.input("=> ").trim();
                    if (c.equals("1")) {
                        menu.add(section, mealName, price);
                        menu.print_menu();
                        System.out.println("New meal is successfully added...");
                        break;
                    } else if (c.equals("2")) break;
                }
            }
            // REMOVING SPECIFIED MEALS FROM THE MENU
            else if (c.equals("2")) {
                menu.print_menu();
                System.out.println("enter number of meal:");
                int meal;
                while (true) {
                    try {
                        meal = Integer.parseInt(functions.input("=> ").trim());
                        menu.remove(meal);
                        System.out.println("\nmeal is successfully removed...\n");
                        menu.print_menu();
                        adminMenu(account);
                    }
                    catch (Exception e) {
                        continue;
                    }
                        break;
                    }
                }
            }
            adminMenu(account);

    }
    void makeReservation(CustomerAccount account) {
        System.out.println("\n\n  New Reservation  ");
        System.out.println("Choose our location:\n1. Genclik\n2. 28 May\n3. Neftchiler\n4. Nerimanov\n5. Memar Acami");
        String location;
        do {
            location = functions.input("=> ").trim();
        } while (!("12345".contains(location) && !location.equals("")));

        switch (location) {
            case "1":
                location = "Genclik";
                break;
            case "2":
                location = "28 May";
                break;
            case "3":
                location = "Neftchiler";
                break;
            case "4":
                location = "Nerimanov";
                break;
            case "5":
                location = "Memar Acami";
                break;
        }
        menu.print_menu();
        int[] items = functions.readStream("enter the numbers of meals seperated with a single space(enter to skip): ", -1);

        String date;
        do {
            date = functions.input("Enter the date (dd/mm/yyyy): ");
        } while (!(Reservation.isValidDate(date)));

        System.out.println("\n1. reserve\n2. cancel");
        String end = functions.input("=> ").trim();
        while (true) {
            if (end.equals("1")) {
                account.makeReservation(location, date, items);
                break;
            } else if (end.equals("2"))
                break;
            end = functions.input("=> ");
        }
        customerMenu(account);
    }
    void orderDelivery(CustomerAccount account) {
        menu.print_menu();
        int[] items = functions.readStream("enter the meal numbers(up to 5 meals): ", -1);
        String address = functions.input("enter your address: ");

        System.out.println("Enter payment method:\n1. pay at delivery\n2. pay with credit card");
        String card = "";
        while (true) {
            String payment = functions.input("=> ");
            if (payment.equals("1")) break;
            else if (payment.equals("2")) {
                System.out.println("Enter your credit card number(must contain 16 digits):");
                while (true) {
                    // checking if entered card number is valid
                    card = functions.input("card number: ");
                    if (card.length() == 16) {
                        try {
                            Double.parseDouble(card);
                        } catch (Exception e) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
        System.out.printf("total price: $%.2g%n", menu.calcPrice(items));
        System.out.println("\n1. Order\n2. Cancel");
        while (true) {
            String c = functions.input("=> ");
            if (c.equals("1")) {
                account.orderDelivery(items, address,
                        !card.equals("") ? card : "at delivery");
                break;
            } else if (c.equals("2")) {
                break;
            }
        }
        customerMenu(account);
    }
    void show_workers() {
        System.out.println("ID          Name          Position          Address");
        for (int i = 0; i < data.workers.size(); i++) {
            System.out.print(String.format("%3d     ", data.workers.get(i).id));
            System.out.print(functions.center(data.workers.get(i).name, 14, ' '));
            System.out.print(functions.center(data.workers.get(i).position, 18, ' '));
            System.out.print(functions.center(data.workers.get(i).address, 17, ' '));
            System.out.println();
        }
        System.out.println("\n");
    }    
}
