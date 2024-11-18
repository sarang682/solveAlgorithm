import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1949 {

    static int N;
    static int[] village;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        village = new int[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) village[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        dp = new int[N + 1][2];
        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int v, int parent) {
        for (int nxt : adj.get(v)) {
            if (nxt != parent) {
                dfs(nxt, v);
                dp[v][0] += Math.max(dp[nxt][0], dp[nxt][1]);
                dp[v][1] += dp[nxt][0];
            }
        }
        dp[v][1] += village[v];
    }
}
