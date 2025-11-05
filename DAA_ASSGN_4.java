//Arpita Patki 123B1F001
// Assignment 4 Implement Dijkstra‘s algorithm to find the shortest path from the ambulance's current location (S) to all possible hospitals.
import java.util.*;

class Edge {
    int dest, weight;
    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    int V;
    ArrayList<ArrayList<Edge>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    void addEdge(int src, int dest, int weight) {
        adj.get(src).add(new Edge(dest, weight));
        adj.get(dest).add(new Edge(src, weight)); // assuming bidirectional roads
    }

    void updateTraffic(int src, int dest, int newWeight) {
        for (Edge e : adj.get(src)) {
            if (e.dest == dest) {
                e.weight = newWeight;
                break;
            }
        }
        for (Edge e : adj.get(dest)) {
            if (e.dest == src) {
                e.weight = newWeight;
                break;
            }
        }
    }

    void dijkstra(int src, List<Integer> hospitals) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];

            for (Edge e : adj.get(u)) {
                int v = e.dest;
                int weight = e.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("\n Shortest travel time from Ambulance (Node " + src + "):");
        for (int h : hospitals) {
            System.out.println("Hospital at Node " + h + " -> " + dist[h] + " minutes");
        }

        int nearestHospital = -1, minTime = Integer.MAX_VALUE;
        for (int h : hospitals) {
            if (dist[h] < minTime) {
                minTime = dist[h];
                nearestHospital = h;
            }
        }

        if (nearestHospital == -1) {
            System.out.println("No reachable hospital found!");
            return;
        }

        System.out.println("\n🩺 Nearest Hospital: Node " + nearestHospital + " (Time: " + minTime + " mins)");

        System.out.println("\n Optimal Route:");
        printPath(parent, nearestHospital);
        System.out.println(" -> Hospital");
    }

    void printPath(int[] parent, int j) {
        if (parent[j] == -1) {
            System.out.print(j);
            return;
        }
        printPath(parent, parent[j]);
        System.out.print(" -> " + j);
    }
}

public class DAA_ASSGN_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of intersections (nodes): ");
        int V = sc.nextInt();

        Graph g = new Graph(V);

        System.out.print("Enter number of roads (edges): ");
        int E = sc.nextInt();

        for (int i = 0; i < E; i++) {
            System.out.println("\nEnter road " + (i + 1) + " details:");
            System.out.print("From node: ");
            int u = sc.nextInt();
            System.out.print("To node: ");
            int v = sc.nextInt();
            System.out.print("Travel time (mins): ");
            int w = sc.nextInt();
            g.addEdge(u, v, w);
        }

        System.out.print("\nEnter ambulance starting node: ");
        int src = sc.nextInt();

        System.out.print("Enter number of hospitals: ");
        int hCount = sc.nextInt();

        List<Integer> hospitals = new ArrayList<>();
        System.out.println("Enter hospital node numbers:");
        for (int i = 0; i < hCount; i++) {
            hospitals.add(sc.nextInt());
        }

        g.dijkstra(src, hospitals);

        System.out.print("\nDo you want to update traffic (y/n)? ");
        char choice = sc.next().charAt(0);
        if (choice == 'y' || choice == 'Y') {
            System.out.print("Enter source node of road to update: ");
            int u = sc.nextInt();
            System.out.print("Enter destination node: ");
            int v = sc.nextInt();
            System.out.print("Enter new travel time (mins): ");
            int newW = sc.nextInt();
            g.updateTraffic(u, v, newW);

            System.out.println("\nTraffic updated. Recalculating shortest paths...");
            g.dijkstra(src, hospitals);
        }

        sc.close();
    }
}

