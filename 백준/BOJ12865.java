import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K;
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 물품 수
        K = Integer.parseInt(input[1]); // 무게
        int[][] dp = new int[N+1][K + 1]; // idx 무게일 때 얻을 수 있는 최대 가치
        int[][] things = new int[N+1][2];
        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            things[i][0] = Integer.parseInt(input[0]); // 무게
            things[i][1] = Integer.parseInt(input[1]); // 가치
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= N; i++) { // 물품 탐색
                dp[i][k] = dp[i-1][k];
                if (things[i][0] <= k) {
                    dp[i][k] = Math.max(dp[i - 1][k], things[i][1] + dp[i - 1][k - things[i][0]]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
