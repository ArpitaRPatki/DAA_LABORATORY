//Arpita Patki 123B1F001
// Assignment 5 Implement an efficient algorithm (such as Dynamic Programming or Dijkstra‘s Algorithm) to find the optimal delivery route.
import java.util.*;

public class DAA_ASSGN_5 {

    static class Edge {
        int from, to;
        double cost;
        Edge(int f, int t, double c) {
            from = f;
            to = t;
            cost = c;
        }
    }

    static class Graph {
        int n;
        List<Edge> edges = new ArrayList<>();
        List<List<Edge>> adj;

        Graph(int nodes) {
            n = nodes;
            adj = new ArrayList<>();
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        }

        void addEdge(int from, int to, double cost) {
            Edge e = new Edge(from, to, cost);
            edges.add(e);
            adj.get(from).add(e);
        }

        double[] shortestPathDP(int source) {
            double[] dp = new double[n];
            Arrays.fill(dp, Double.POSITIVE_INFINITY);
            dp[source] = 0;

            for (int i = source; i < n; i++) {
                for (Edge e : adj.get(i)) {
                    if (dp[e.to] > dp[e.from] + e.cost)
                        dp[e.to] = dp[e.from] + e.cost;
                }
            }
            return dp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of nodes: ");
        int nodes = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        Graph g = new Graph(nodes);

        for (int i = 0; i < edges; i++) {
            System.out.println("\nEdge " + (i + 1) + ":");
            System.out.print("From: ");
            int from = sc.nextInt();
            System.out.print("To: ");
            int to = sc.nextInt();
            System.out.print("Cost: ");
            double cost = sc.nextDouble();
            g.addEdge(from, to, cost);
        }

        System.out.print("\nEnter source node: ");
        int source = sc.nextInt();

        double[] result = g.shortestPathDP(source);

        System.out.println("\nMinimum cost from source " + source + " to all other nodes:");
        for (int i = 0; i < result.length; i++) {
            if (result[i] == Double.POSITIVE_INFINITY)
                System.out.println("Node " + i + " is unreachable");
            else
                System.out.println("Node " + i + " : " + result[i]);
        }

        System.out.println("\n✅ Route optimization complete for SwiftCargo!");
    }
}

