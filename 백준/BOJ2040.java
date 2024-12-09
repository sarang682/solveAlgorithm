import java.io.*;
import java.util.*;

public class BOJ2040 {

    static int N;
    static int[] cards, dp, sum;
    static final int INF = 99_999_999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= 3; test_case++) {
            cards = new int[N + 1];
            dp = new int[N + 1];
            sum = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cards[N - i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= N; i++) {
                sum[i] = sum[i - 1] + cards[i];
            }
            Arrays.fill(dp, INF);
            play(1);
            if (dp[1] < 0) System.out.println("A");
            else if (dp[1] > 0) System.out.println("B");
            else System.out.println("D");
        }
    }

    public static int play(int idx) {
        if (idx > N) return 0;
        if (dp[idx] != INF) return dp[idx];
        int sum = INF;
        for (int i = idx; i <= N; i++) {
            sum = Math.min(sum, get_sum(idx, i) - play(i + 1));
        }
        return dp[idx] = sum;
    }

    public static int get_sum(int s, int e) {
        return sum[e] - sum[s - 1];
    }
}
