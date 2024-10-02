import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2533 {

    static int N, ans;
    static boolean[] visit;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N + 1];
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        dp = new int[N + 1];
        visit[1]= true;
        dfs(1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (dp[i] > 0) ans++;
        }
        System.out.println(ans);
    }

    public static int dfs(int v) {
        int cnt = 0;
        for (int nxt : adj.get(v)) {
            if (!visit[nxt]) {
                visit[nxt] = true;
                if (dfs(nxt) == 0) cnt++;
            }
        }
        return cnt;
    }
}
