import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N + 2];
        int[] price = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            time[i] = Integer.parseInt(input[0]);
            price[i] = Integer.parseInt(input[1]);
        }

        int[] dp = new int[N + 2];
        int max = 0;
        for (int i = 1; i <= N + 1; i++) {
            max = Math.max(max, dp[i]);
            int next = i + time[i];
            if (next >= N + 2) continue;
            dp[next] = Math.max(dp[next], max + price[i]);
        }
        System.out.println(max);
    }
}
