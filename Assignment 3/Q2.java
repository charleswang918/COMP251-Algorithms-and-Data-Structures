import java.util.*;


public class Q2 {

	public static int rings(Hashtable<Integer, ArrayList<Integer>> graph, int[]location) {
		int[] in_degree = new int[location.length];
		graph.forEach((key, value) -> {
			value.forEach(i -> in_degree[i]++);
		});
		int min_transport = location.length;
		HashSet<Integer> init_locations = new HashSet<>();
		for (int i = 0; i < in_degree.length; i++)
			if (in_degree[i] == 0) init_locations.add(location[i]);
		int min = Collections.min(init_locations), max = Collections.max(init_locations);
		for (int start_location = min; start_location <= max; ++start_location) {
			int current_location = start_location, transport = 0;

			int[] in_degree_copy = new int[in_degree.length];
			System.arraycopy(in_degree, 0, in_degree_copy, 0, in_degree.length);
			ArrayList<Queue<Integer>> queues = new ArrayList<Queue<Integer>>();
			for (int i = 0; i < 3; i++)
				queues.add(new LinkedList<Integer>());
			for (int i = 0; i < in_degree.length; i++)
				if (in_degree[i] == 0) queues.get(location[i]).offer(i);

			while (!(queues.get(1).isEmpty() && queues.get(2).isEmpty())) {
				while (!queues.get(current_location).isEmpty()) {
					int head = queues.get(current_location).poll();
					if (graph.containsKey(head)) {
						for (int next : graph.get(head)) {
							in_degree_copy[next]--;
							if (in_degree_copy[next] == 0) queues.get(location[next]).offer(next);
						}
					}
				}
				current_location = 3 - current_location;
				transport++;
			}
			transport--;
			if (min_transport > transport) min_transport = transport;
		}
		return min_transport;
	}

	public static void main(String[] args) {
		Hashtable<Integer, ArrayList<Integer>> graph = new Hashtable<Integer, ArrayList<Integer>>();
		/*graph.put(0, new ArrayList<Integer>(Arrays.asList(1, 2)));
		graph.put(1, new ArrayList<Integer>(Arrays.asList(3, 4)));
		graph.put(2, new ArrayList<Integer>(Arrays.asList(4, 3)));
		System.out.println(rings(graph, new int[]{1, 2, 1, 2, 1}));*/
		graph.put(0, new ArrayList<Integer>(Arrays.asList(1)));
		graph.put(1, new ArrayList<Integer>(Arrays.asList(2)));
		System.out.println(rings(graph, new int[]{2, 1, 2, 1}));
	}
}
