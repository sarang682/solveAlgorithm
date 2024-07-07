import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1005 {

    static ArrayList<ArrayList<Integer>> from;
    static int[] building, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N, K;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            building = new int[N + 1];
            dp = new int[N + 1];
            Arrays.fill(dp, -1);
            from = new ArrayList<>();
            from.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                building[i] = Integer.parseInt(st.nextToken());
                from.add(new ArrayList<>());
            }

            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                from.get(b).add(a);
            }
            int W = Integer.parseInt(br.readLine());
            System.out.println(dfs(W));
        }
    }

    public static int dfs(int x) {
        if (from.get(x).isEmpty()) {
            return dp[x] = building[x];
        }
        int max = -1;
        for (int prev : from.get(x)) {
            if (dp[prev] == -1)
                max = Math.max(max, dfs(prev));
            else
                max = Math.max(max, dp[prev]);
        }
        return dp[x] = max + building[x];
    }
}
