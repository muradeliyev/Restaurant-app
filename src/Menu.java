public class Menu {
    private static float[] prices = {
            0, 2.1f, 2.9f, 2.1f, 4.10f,
            3.0f, 3.7f, 4.0f, 3.3f,
            1.5f, 2.4f, 2.5f, 2.8f,
            6.0f, 7.5f, 14.1f, 13.2f,
            1.9f, 2.1f, 3.1f, 5.0f
    };
    static int count = 20;
    private static Dictionary starters = new Dictionary(
            new String[] {"Garlic bread", "Green salad", "Vegetable soup", "Chicken wings"},
            new float[] {2.1f, 2.9f, 2.1f, 4.10f}
    );
    private static Dictionary desserts = new Dictionary(
            new String[] {"Apple pie", "Ice cream", "Fruit salad", "Pumpkin pie"},
            new float[] {3.0f, 3.7f, 4.0f, 3.3f}
    );
    private static Dictionary drinks = new Dictionary(
            new String[] {"Sparkling water", "Orange juice", "Black coffee", "Coffee with milk"},
            new float[] {1.5f, 2.4f, 2.5f, 2.8f}
    );
    private static Dictionary main_courses = new Dictionary(
            new String[] {"Spaghetti Bolognese", "Grilled Chicken", "Grilled Salmon", "Lamb chops"},
            new float[] {6.0f, 7.5f, 14.1f, 13.2f}
    );
    private static Dictionary side_dishes = new Dictionary(
            new String[] {"Corn cob", "Baked beans", "Rice", "Grilled vegetables"},
            new float[] {1.9f, 2.1f, 3.1f, 5.0f}
    );
    private static String[] menu_headings = {"Starters", "Desserts", "Drinks", "Main courses", "Side dishes"};
    private static Dictionary[] menu_items = {starters, desserts, drinks, main_courses, side_dishes};
    static double calcPrice(int[] nums) {
        double total = 0;
        return total;

    }
    static void remove(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n > 0 && n <= count) {
                if (n < desserts.start) {
                    starters.remove(n-1);
                }
                else if (n < drinks.start) desserts.remove(n-1);
                else if (n < main_courses.start) drinks.remove(n-1);
                else if (n < side_dishes.start) main_courses.remove(n-1);
            }
        }
        count--;
    }
    static void showMenu() {
        System.out.println("\n                                      MENU                                          ");
        System.out.println("        Starters           |         Desserts        |            Drinks            ");
        System.out.println("1. Garlic bread      $2.10 | 5. Apple pie      $3.00 | 9. Sparkling water      $1.50");
        System.out.println("2. Green salad       $2.90 | 6. Ice cream      $3.70 | 10. Orange juice        $2.40");
        System.out.println("3. Vegetable soup    $2.10 | 7. Fruit salad    $4.00 | 11. Black coffee        $2.50");
        System.out.println("4. Chicken wings     $4.10 | 8. Pumpkin pie    $3.30 | 12. Coffee with milk    $2.80");
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("                 Main Courses                |              Side Dishes             ");
        System.out.println("13. Spaghetti Bolognese                $6.00 | 17. Corn cob                    $1.90");
        System.out.println("14. Grilled Chicken                    $7.50 | 18. Baked beans                 $2.10");
        System.out.println("15. Grilled Salmon                    $14.10 | 19. Rice                        $3.10");
        System.out.println("16. Lamb chops                        $13.20 | 20. Grilled vegetables          $5.00");
        System.out.println();
    }
    static void add(int section, String meal_name, float price) {
        switch (section) {
            case 1:
                starters.add(meal_name, price);
                desserts.start++;
                break;
            case 2:
                desserts.add(meal_name, price);
                drinks.start++;
                break;
            case 3:
                drinks.add(meal_name, price);
                main_courses.start++;
                break;
            case 4:
                main_courses.add(meal_name, price);
                side_dishes.start++;
                break;
            default:
                side_dishes.add(meal_name, price);
//        menu_items[section+1].add(meal_name, price);
        }
        count++;
    }
    static void print_menu() {
        int n = 1;
        System.out.println("-----------------------------------------");
        for (int i = 0; i < menu_items.length; i++) {
            System.out.println(center(menu_headings[i], 40, ' '));
            for (String meal : menu_items[i].getKeys()) {
//                System.out.printf(n+". %s %20s", meal, menu_items[i].get(meal)+"\n");
                menuRow(n+". "+meal, menu_items[i].get(meal), 40, ' ');
                n++;
            }
            System.out.println();
        }
    }
    static String center(String string, int length, char ch) {
        String result = "";
        int i;
        for (i = 0; i < (length-string.length())/2; i++)
            result += ch;
        result += string;
        for (;i < length-string.length(); i++)
            result += ch;

        return result;
    }
    static void menuRow(String name, float price, int length, char ch) {
        String result = name;
        String pr = String.valueOf(price);
        for (int i = 0; i < length-name.length()-pr.length()-1; i++)
            result += ch;
        result += "$"+pr;
        System.out.println(result);
    }
}
