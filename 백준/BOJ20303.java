import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20303 {

    static int N, M, K, cnt;
    static int[] candy, sum, fri;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candy = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
        }
        adj = new ArrayList<>();
        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        visit = new boolean[N + 1];
        sum = new int[N + 1];
        fri = new int[N + 1];
        cnt = 0;
        for (int i = 1; i <= N; i++) {
            chk(i);
        }
        int[][] bundle = new int[cnt + 1][2]; // 친구수, 사탕수
        int idx = 1;
        for (int i = 1; i <= N; i++) {
            if (fri[i] > 0) {
                bundle[idx][0] = fri[i];
                bundle[idx][1] = sum[i];
                idx++;
            }
        }
        int[][] dp = new int[cnt + 1][K];
        for (int i = 0; i <= cnt; i++)
            Arrays.fill(dp[i], -1);
        dp[0][0] = 0;
        for (int i = 1; i <= cnt; i++) { // i번째 캔디 넣기
            for (int j = 0; j < K; j++) {
                if (dp[i - 1][j] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    if (j + bundle[i][0] < K) {
                        int nxt = j + bundle[i][0];
                        dp[i][nxt] = Math.max(dp[i][nxt], dp[i - 1][j] + bundle[i][1]);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < K; i++) {
            ans = Math.max(ans, dp[cnt][i]);
        }
        System.out.println(ans);
    }

    public static void chk(int i) {
        if (!visit[i]) {
            cnt++;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visit[i] = true;
            fri[i]++;
            sum[i] += candy[i];
            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (int nxt : adj.get(now)) {
                    if (!visit[nxt]) {
                        sum[i] += candy[nxt];
                        fri[i]++;
                        visit[nxt] = true;
                        queue.offer(nxt);
                    }
                }
            }
        }
    }
}
