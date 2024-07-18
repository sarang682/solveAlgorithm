import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10942 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1]; // i에서 j까지 팰린드롬?
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            if (nums[i] == nums[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        for (int l = 3; l <= N; l++) { // 길이
            for (int i = 1; i + l - 1 <= N; i++) {
                int end = i + l - 1;
                if (nums[i] == nums[end] && dp[i + 1][end - 1] == 1)
                    dp[i][end] = 1;
            }
        }

        StringBuilder ans = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ans.append(dp[s][e]).append("\n");
        }
        System.out.println(ans);
    }
}
