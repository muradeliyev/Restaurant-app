public class Worker {
    String name, email, position, address;
    int id;
    static int nums = 1;

    Worker(String name, String email, String pos, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.position = pos;
        this.id = nums; nums++;
//        switch (pos) {
//            case 1:
//                this.position = "Assistant manager";
//                break;
//            case 2:
//                this.position = "Chef";
//                break;
//            case 3:
//                this.position = "Waiter";
//                break;
//            case 4:
//                this.position = "Dish washer";
//                break;
//            default:
//                this.position = "";
//                break;
//        }
    }

}
