import java.util.Scanner;

public class Functions {
    private Scanner scan = new Scanner(System.in);

    String input(String message) {
        System.out.print(message);
        return scan.nextLine();
    }
    boolean in(String item, String[] arr) {
        for (String s : arr)
            if (s.equals(item))
                return true;
        return false;
    }
    int[] readStream(String message, int length) {
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
    String center(String string, int length, char ch) {
        String result = "";
        int i;
        for (i = 0; i < (length-string.length())/2; i++)
            result += ch;
        result += string;
        for (;i < length-string.length(); i++)
            result += ch;

        return result;
    }
}
