package Implement;

import java.util.HashMap;
import java.util.Map;

public class Test_StringSort {

    public static void main(String[] args) {
        String s = "iwjviwnviwadaca";

        Map<String, Object> map = new HashMap();
//        If you're only interested in the keys, you can iterate through the keySet() of the map:
        for (String key : map.keySet()) {
            // ...
        }
//If you only need the values, use values():
        for (Object value : map.values()) {
            // ...
        }
//Finally, if you want both the key and value, use entrySet():
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // ...
        }
    }
}
