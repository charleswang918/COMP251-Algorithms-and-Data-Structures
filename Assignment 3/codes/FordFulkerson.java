import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> DFS(int current, int destination, HashMap<Integer, HashMap<Integer, Integer>> adjacency_list, boolean[] visited, ArrayList<Integer> grow_path) {
		if (current == destination) return grow_path;
		if (adjacency_list.containsKey(current)) {
			for (Map.Entry<Integer, Integer> entry : adjacency_list.get(current).entrySet()) {
				int next = entry.getKey(), weight = entry.getValue();
				if (!visited[next] && weight > 0) {
					grow_path.add(next);
					visited[next] = true;
					ArrayList<Integer> path = DFS(next, destination, adjacency_list, visited, grow_path);
					if (path != null) return path;
					grow_path.remove(grow_path.size() - 1);
				}
			}
		}
		return null;
	}
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();

		ArrayList<Edge> edges = graph.getEdges();
		HashMap<Integer, HashMap<Integer, Integer>> adjacency_list = new HashMap<Integer, HashMap<Integer, Integer>>();
		for (Edge edge: edges) {
			if (!adjacency_list.containsKey(edge.nodes[0])) adjacency_list.put(edge.nodes[0], new HashMap<Integer, Integer>());
			adjacency_list.get(edge.nodes[0]).put(edge.nodes[1], edge.weight);
		}
		boolean[] visited = new boolean[graph.getNbNodes()];
		visited[source] = true;
		ArrayList<Integer> grow_path = new ArrayList<Integer>();
		grow_path.add(source);

		path = DFS(source, destination, adjacency_list, visited, grow_path);
		return path;
	}



	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;

		ArrayList<Edge> edges = graph.getEdges();
		HashMap<Integer, HashMap<Integer, Integer>> adjacency_list = new HashMap<Integer, HashMap<Integer, Integer>>();
		for (Edge edge: edges) {
			if (!adjacency_list.containsKey(edge.nodes[0])) adjacency_list.put(edge.nodes[0], new HashMap<Integer, Integer>());
			adjacency_list.get(edge.nodes[0]).put(edge.nodes[1], edge.weight);
		}
		WGraph residual_graph = new WGraph(graph);
		ArrayList<Integer> residual_path = pathDFS(residual_graph.getSource(), residual_graph.getDestination(), residual_graph);
		while (residual_path != null) {
			int path_flow = -1;
			for (int i = 0; i + 1 < residual_path.size(); ++i) {
				int capacity = residual_graph.getEdge(residual_path.get(i), residual_path.get(i + 1)).weight;
				if (!(path_flow != -1 && path_flow <= capacity)) path_flow = capacity;
			}
			for (int i = 0; i + 1 < residual_path.size(); i++) {
				if (residual_graph.getEdge(residual_path.get(i), residual_path.get(i + 1)) == null) {
					residual_graph.addEdge(new Edge(residual_path.get(i), residual_path.get(i + 1), 0));
				}
				int edge_weight = residual_graph.getEdge(residual_path.get(i), residual_path.get(i + 1)).weight;
				residual_graph.setEdge(residual_path.get(i), residual_path.get(i + 1), edge_weight - path_flow);

				if (residual_graph.getEdge(residual_path.get(i + 1), residual_path.get(i)) == null) {
					residual_graph.addEdge(new Edge(residual_path.get(i + 1), residual_path.get(i), 0));
				}
				edge_weight = residual_graph.getEdge(residual_path.get(i + 1), residual_path.get(i)).weight;
				residual_graph.setEdge(residual_path.get(i + 1), residual_path.get(i), edge_weight + path_flow);
			}
			maxFlow += path_flow;
			residual_path = pathDFS(residual_graph.getSource(), residual_graph.getDestination(), residual_graph);
		}
		for (Edge edge: edges) {
			Edge residual_edge = residual_graph.getEdge(edge.nodes[1], edge.nodes[0]);
			int weight = 0;
			if (residual_edge != null) weight = residual_edge.weight;
			graph.setEdge(edge.nodes[0], edge.nodes[1], weight);
		}

		answer += maxFlow + "\n" + graph.toString();
		return answer;
	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}

