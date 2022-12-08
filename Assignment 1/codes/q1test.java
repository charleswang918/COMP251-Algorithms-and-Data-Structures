import java.util.*;

public class q1test {
    public static void main(String[] args){
        Chaining chaining = new Chaining(10, 0, -1);
        int [] rank = new int[5];
        Map<String, String> uniqueUsers = new HashMap<String, String>();
        uniqueUsers.put("David", "no no no");
        uniqueUsers.put("Charles", "I am Charles");
        uniqueUsers.put("Alyssa", "Hello my husband");
        ArrayList<Map.Entry<String, String>> msgList = new ArrayList<Map.Entry<String, String>>(uniqueUsers.entrySet());
        ArrayList<ArrayList<String>> all = new ArrayList<>();
        String msg = "hello my friend i am charles";
        String word[] = msg.split(" ");
        List<String> intlist = new ArrayList<String>();
        intlist.add("hello");
        intlist.add("asdf");
        List<String> int2list = new ArrayList<String>();
        int2list.add("nichoa");
        intlist.addAll(int2list);

        System.out.println(intlist);
    }
}
