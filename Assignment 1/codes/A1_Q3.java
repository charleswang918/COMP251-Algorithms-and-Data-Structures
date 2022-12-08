import java.util.*;

public class A1_Q3 {

	public static ArrayList<String> Discussion_Board(String[] posts) {
		// Using hashmap to store a list of words for each user said
		Map<String, List<String>> uniqueUsers = new HashMap<String, List<String>>();
		for (int i = 0; i < posts.length; i++) {
			String splitted[] = posts[i].split(" ", 2); // split user's name and their posts.
			String user = splitted[0];
			List<String> msglist = Arrays.asList(splitted[1].split(" ")); // split every post into a list of words.
			if (uniqueUsers.containsKey(user)) {
				List<String> updatelist = new ArrayList<String>();
				List<String> prelist = uniqueUsers.get(user);
				updatelist.addAll(prelist);
				updatelist.addAll(msglist);
				uniqueUsers.put(user, updatelist); // add new list of words to update list.
			} else {
				uniqueUsers.put(user, msglist); // add list of words to a new user.
			}
		}

		Map<String, Integer> freqmap = new HashMap<String, Integer>(); // create a hashmap to store the frequency of words.

		// using for loop to iterating the hashmap to count the occurrences of each word.
		for (Map.Entry entry : uniqueUsers.entrySet()) {
			List<String> tmp = new ArrayList<String>();
			tmp = (List<String>) entry.getValue();
			for (String word : tmp){
				if (!freqmap.containsKey(word)){
					freqmap.put(word, 1);
				} else {
					freqmap.put(word, freqmap.get(word) + 1);
				}
			}
		}

		// using an ArrayList to store the intersection of all user's words.
		List<String> intersection = new ArrayList<String>();
		for (Map.Entry entry : uniqueUsers.entrySet()) {
			if (intersection.isEmpty()) {
				List<String> tmp = new ArrayList<String>();
				tmp = (List<String>) entry.getValue();
				intersection.addAll(tmp); // add first user's words
			} else {
				List<String> tmp = new ArrayList<String>();
				tmp = (List<String>) entry.getValue();
				intersection.retainAll(tmp); // retain all words of the rest of users.
			}
		}

		// using iterator to delete words that are not belong to intersection List from frequency hashmap.
		Iterator<Map.Entry<String,Integer>> iter = freqmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,Integer> entry = iter.next();
			if(!intersection.contains(entry.getKey())) {
				iter.remove();
			}
		}

		// sorting the word frequency map by their occurrences.
		List<Map.Entry<String, Integer>> list = new ArrayList<>(freqmap.entrySet());
		list.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

		// Using an ArrayList to store those words we have.
		ArrayList<String> arrlist = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : list) {
			arrlist.add(entry.getKey());
		}

		return arrlist;
	}

}
