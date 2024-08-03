import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9370 {

    static class Edge {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    static int vertex_size, edge_size, des_size;
    static int s, g, h;
    static ArrayList<ArrayList<Edge>> adj;
    static TreeMap<Integer, Boolean> des;
    static final int MAX_VALUE = 2_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            vertex_size = Integer.parseInt(st.nextToken());
            edge_size = Integer.parseInt(st.nextToken());
            des_size = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            for (int i = 0; i <= vertex_size; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 1; i <= edge_size; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                adj.get(a).add(new Edge(b, d));
                adj.get(b).add(new Edge(a, d));
            }
            des = new TreeMap<>();
            for (int i = 1; i <= des_size; i++) {
                int v = Integer.parseInt(br.readLine());
                des.put(v, false);
            }
            int[] s_dis = dijkstra(s);
            int[] h_dis = dijkstra(h);
            int[] g_dis = dijkstra(g);
            int hg = h_dis[g];
            for (int key : des.keySet()) {
                int cal1 = hg + s_dis[h] + g_dis[key];
                int cal2 = hg + s_dis[g] + h_dis[key];
                if (cal1 == s_dis[key] || cal2 == s_dis[key])
                    sb.append(key).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static int[] dijkstra(int start) {
        int[] d = new int[vertex_size + 1];
        Arrays.fill(d, MAX_VALUE);
        Queue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        q.offer(new Edge(start, 0));
        d[start] = 0;
        while (!q.isEmpty()) {
            Edge now = q.poll();
            if (now.cost > d[now.vertex])
                continue;
            for (Edge e : adj.get(now.vertex)) {
                if (now.cost + e.cost < d[e.vertex]) {
                    d[e.vertex] = now.cost + e.cost;
                    q.offer(new Edge(e.vertex, d[e.vertex]));
                }
            }
        }
        return d;
    }
}
