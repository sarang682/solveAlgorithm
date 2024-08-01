import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1774 {

    static class Edge {
        int a;
        int b;
        double d;

        public Edge(int a, int b, double d) {
            this.a = a;
            this.b = b;
            this.d = d;
        }
    }

    static int N, M;
    static int[] parent;
    static int[][] god;
    static PriorityQueue<Edge> pq;
    static double ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        god = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            god[i][0] = Integer.parseInt(st.nextToken());
            god[i][1] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        pq = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(o1.d, o2.d);
        });

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                pq.offer(new Edge(i, j, getDis(i, j)));
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.a) != find(e.b)) {
                union(e.a, e.b);
                ans += e.d;
            }
        }
        System.out.printf("%.2f\n", ans);
    }

    public static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static double getDis(int a, int b) {
        long xDis = Math.abs(god[a][0] - god[b][0]);
        long yDis = Math.abs(god[a][1] - god[b][1]);
        return Math.sqrt(xDis * xDis + yDis * yDis);
    }
}
