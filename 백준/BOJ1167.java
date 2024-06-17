import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1167 {

    static int V, max_v, max_c;
    static ArrayList<ArrayList<Edge>> adj;
    static boolean[] visit;
    static int[] dis;

    static class Edge {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            while (true) {
                int v2 = Integer.parseInt(st.nextToken());
                if(v2==-1) break;
                int cost = Integer.parseInt(st.nextToken());
                adj.get(v1).add(new Edge(v2, cost));
            }
        }
        // 정점 1에서 가장 먼 정점 찾기
        visit = new boolean[V+1];
        dis = new int[V+1];
        visit[1]=true;
        max_v = 1;
        max_c = 0;
        dfs(1);
        // max_v에서 지름 찾기
        visit = new boolean[V + 1];
        dis = new int[V + 1];
        visit[max_v] = true;
        max_c = 0;
        dfs(max_v);
        System.out.println(max_c);
    }

    public static void dfs(int v) {
        for (Edge e : adj.get(v)) {
            if(!visit[e.vertex]) {
                dis[e.vertex] = dis[v] + e.cost;
                visit[e.vertex] = true;
                if(dis[e.vertex] > max_c) {
                    max_c = dis[e.vertex];
                    max_v = e.vertex;
                }
                dfs(e.vertex);
            }
        }
    }
}
