import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1135 {

    static int N;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
        st.nextToken();
        for (int i = 1; i < N; i++) { // i의 부모 a
            int a = Integer.parseInt(st.nextToken());
            adj.get(a).add(i);
        }
        System.out.println(dfs(0));
    }
    static int dfs(int x) {
        for (int next : adj.get(x)) {
            dp[next] = 1 + dfs(next);
        }
        adj.get(x).sort(((o1, o2) -> dp[o2] - dp[o1]));
        int max = 0;
        for (int i = 0; i < adj.get(x).size(); i++) {
            int num = adj.get(x).get(i);
            max = Math.max(max, dp[num] + i);
        }
        return max;
    }
}
