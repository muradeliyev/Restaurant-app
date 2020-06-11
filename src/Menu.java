public class Menu {
    Functions functions = new Functions();
    static int count = 20;
    private Dictionary starters = new Dictionary(
            new String[] {"Garlic bread", "Green salad", "Vegetable soup", "Chicken wings"},
            new float[] {2.1f, 2.9f, 2.1f, 4.10f}
    );
    private Dictionary desserts = new Dictionary(
            new String[] {"Apple pie", "Ice cream", "Fruit salad", "Pumpkin pie"},
            new float[] {3.0f, 3.7f, 4.0f, 3.3f}
    );
    private Dictionary drinks = new Dictionary(
            new String[] {"Sparkling water", "Orange juice", "Black coffee", "Coffee with milk"},
            new float[] {1.5f, 2.4f, 2.5f, 2.8f}
    );
    private Dictionary main_courses = new Dictionary(
            new String[] {"Spaghetti Bolognese", "Grilled Chicken", "Grilled Salmon", "Lamb chops"},
            new float[] {6.0f, 7.5f, 14.1f, 13.2f}
    );
    private Dictionary side_dishes = new Dictionary(
            new String[] {"Corn cob", "Baked beans", "Rice", "Grilled vegetables"},
            new float[] {1.9f, 2.1f, 3.1f, 5.0f}
    );
    private String[] menu_headings = {"Starters", "Desserts", "Drinks", "Main courses", "Side dishes"};
    private Dictionary[] menu_items = {starters, desserts, drinks, main_courses, side_dishes};
     double calcPrice(int[] nums) {
        float total = 0;
        for (int i : nums) {
            if (i > count || i < 1) continue;
            Dictionary section = menu_items[i/5];
            String meal = section.getKeys()[i - i / 5 *4 - 1];
            total += section.get(meal)*10;
        }
        return total/10;
    }
    void remove(int num) {
            int n = num;
            if (n > 0 && n <= count) {
                if (n < desserts.start){
                    starters.remove(n-1);
                    desserts.start--;
                    drinks.start--;
                    main_courses.start--;
                    side_dishes.start--;
                }
                else if (n < drinks.start) {
                    desserts.remove(n-1);
                    drinks.start--;
                    main_courses.start--;
                    side_dishes.start--;
                }
                else if (n < main_courses.start) {
                    drinks.remove(n-1);
                    main_courses.start--;
                    side_dishes.start--;
                }
                else if (n < side_dishes.start) {
                    main_courses.remove(n-1);
                    side_dishes.start--;
                }
            }
        count--;
    }
    void add(int section, String meal_name, float price) {
        switch (section) {
            case 1:
                starters.add(meal_name, price);
                desserts.start++;
                drinks.start++;
                main_courses.start++;
                side_dishes.start++;
                break;
            case 2:
                desserts.add(meal_name, price);
                drinks.start++;
                main_courses.start++;
                side_dishes.start++;
                break;
            case 3:
                drinks.add(meal_name, price);
                main_courses.start++;
                side_dishes.start++;
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
    void print_menu() {
        int n = 1;
        System.out.println("-----------------------------------------");
        for (int i = 0; i < menu_items.length; i++) {
            System.out.println(functions.center(menu_headings[i], 40, ' '));
            for (String meal : menu_items[i].getKeys()) {
//                System.out.printf(n+". %s %20s", meal, menu_items[i].get(meal)+"\n");
                menuRow(n+". "+meal, menu_items[i].get(meal), 40, ' ');
                n++;
            }
            System.out.println();
        }
    }
    void menuRow(String name, float price, int length, char ch) {
        String result = name;
        String pr = String.valueOf(price);
        for (int i = 0; i < length-name.length()-pr.length()-1; i++)
            result += ch;
        result += "$"+pr;
        System.out.println(result);
    }
}
