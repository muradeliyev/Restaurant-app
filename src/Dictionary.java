import java.util.ArrayList;

public class Dictionary {
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Float> values = new ArrayList<>();
    static int count = 0;
    int start;

    Dictionary(String[] keys, float[] values) {
        this.start = count;
        for (String key : keys) {
            this.keys.add(key);
            count++;
        }

        for (float value : values)
            this.values.add(value);
    }
    void add(String key, float value) {
        if (!this.keys.contains(key)) {
            this.keys.add(key);
            this.values.add(value);
        }
    }
    float get(String key) {
        if (this.keys.contains(key)) return values.get(keys.indexOf(key));
        return 0;
    }
    void remove(int index) {
        this.keys.remove(this.keys.get(index-this.start));
        this.values.remove(this.values.get(index-this.start));
    }
    String[] getKeys() {
        String[] result = new String[this.keys.size()];
        for (int i = 0; i < result.length; i++) result[i] = this.keys.get(i);
        return result;
    }
}
