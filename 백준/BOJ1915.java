import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] box = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            char[] in = br.readLine().toCharArray();
            for (int j = 1; j <= m; j++) {
                box[i][j] = in[j - 1] - '0';
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (box[i][j] == 0) continue;
                int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                dp[i][j] = min + 1;
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (ans < dp[i][j])
                    ans = dp[i][j];
            }
        }
        System.out.println(ans * ans);
    }
}
