import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2629 {

    static int N, M;
    static int[] weight, bead;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = new int[N + 1];
        int max = 0; // 추 무게 합
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            max += weight[i];
        }
        M = Integer.parseInt(br.readLine());
        bead = new int[M + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            bead[i] = Integer.parseInt(st.nextToken());
        }
        // 만들 수 있는 무게
        boolean[][] dp = new boolean[N + 1][max + 1];
        dp[0][0] = true;
        for (int i = 1; i <= N; i++) { // i 번째 추
            for (int j = 0; j < max; j++) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    dp[i][j + weight[i]] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) { // 구슬
            boolean f = false;
            for (int j = 0; bead[i] + j <= max; j++) {
                if (dp[N][j] && dp[N][bead[i] + j]) {
                    f = true;
                    break;
                }
            }
            if (f) sb.append("Y");
            else sb.append("N");
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
