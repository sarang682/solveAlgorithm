import java.io.*;
import java.util.*;

public class BOJ5569 {

    static int w, h;
    static final int mod = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][][][] dp = new int[h + 1][w + 1][2][2];
        for (int i = 1; i <= h; i++) { // 북쪽 방향
            dp[i][1][1][0] = 1;
        }
        for (int i = 1; i <= w; i++) { // 동쪽
            dp[1][i][0][0] = 1;
        }
        for (int i = 2; i <= h; i++) {
            for (int j = 2; j <= w; j++) {
                // 동쪽 이동, 방향 안바꿈
                dp[i][j][0][0] = (dp[i][j - 1][0][0] + dp[i][j - 1][0][1]) % mod;
                // 동쪽 이동, 방향 바꿈
                dp[i][j][0][1] = dp[i][j - 1][1][0] % mod;
                // 북쪽 이동, 방향 안바꿈
                dp[i][j][1][0] = (dp[i - 1][j][1][0] + dp[i - 1][j][1][1]) % mod;
                // 북쪽 이동, 방향 바꿈
                dp[i][j][1][1] = dp[i - 1][j][0][0] % mod;
            }
        }
        System.out.println((dp[h][w][0][0] + dp[h][w][1][0] + dp[h][w][0][1] + dp[h][w][1][1]) % mod);
    }
}
