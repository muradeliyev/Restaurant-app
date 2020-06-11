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
    }

}
