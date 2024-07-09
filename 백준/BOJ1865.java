import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1865 {

    static int N, M, W;
    static ArrayList<ArrayList<Edge>> adj;

    static class Edge {
        int v;
        int c;

        public Edge(int v, int c) {
            this.v = v;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            adj = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adj.add(new ArrayList<>());
            }
            // 도로
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj.get(a).add(new Edge(b, c));
                adj.get(b).add(new Edge(a, c));
            }
            // 웜홀
            for (int i = 1; i <= W; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                adj.get(a).add(new Edge(b, -c));
            }
            boolean flag = false;
            for (int i = 1; i <= N; i++) {
                if (bellmanFord(i)) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                ans.append("YES").append("\n");
            else
                ans.append("NO").append("\n");
        }
        System.out.println(ans);
    }

    public static boolean bellmanFord(int start) {
        boolean check = false;
        int[] dis = new int[N + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        for (int i = 1; i <= N; i++) {
            check = false;
            for (int j = 1; j <= N; j++) {
                if (dis[j] == Integer.MAX_VALUE) continue;
                for (Edge next : adj.get(j)) {
                    if (dis[next.v] > dis[j] + next.c) {
                        dis[next.v] = dis[j] + next.c;
                        check = true;
                        if (i == N) return true;
                    }
                }
            }
            if (!check)
                break;
        }
        return false;
    }
}
