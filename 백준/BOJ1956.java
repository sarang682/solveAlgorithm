import java.io.*;
import java.util.*;

public class BOJ1956 {

    static int V, E;
    static int[][] dis;
    static final int INF = 10_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dis = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) Arrays.fill(dis[i], INF);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dis[a][b] = c;
        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        int ans = INF;
        for (int i = 1; i <= V; i++) {
            ans = Math.min(ans, dis[i][i]);
        }
        ans = ans == INF ? -1 : ans;
        System.out.println(ans);
    }
}
