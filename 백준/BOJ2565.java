import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ2565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] line = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            line[i][0] = Integer.parseInt(st.nextToken());
            line[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(line, (Comparator.comparingInt(o -> o[0])));
        int[] dp = new int[501];
        for (int i = 0; i < N; i++) {
            int v = line[i][1];
            int max = 0;
            for (int j = 0; j < v; j++) {
                max = Math.max(max, dp[j]);
            }
            dp[v] = max + 1;
        }
        int ans = 0;
        for (int i = 1; i <= 500; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(N - ans);
    }
}
