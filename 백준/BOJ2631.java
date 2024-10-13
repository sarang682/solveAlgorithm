import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2631 {

    static int N;
    static int[] num, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[N + 1];
        for (int i = 1; i <= N; i++) num[i] = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                if (num[j] < num[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }
}
