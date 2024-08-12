import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1943 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 3; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] coin = new int[N + 1][2];
            int goal = 0;
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                coin[i][0] = v;
                coin[i][1] = cnt;
                goal += v * cnt;
            }
            if (goal % 2 == 1) {
                System.out.println(0);
                continue;
            }
            boolean[][] dp = new boolean[N + 1][goal + 1];
            dp[0][0] = true;
            boolean flag = false;
            goal /= 2;
            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < goal; j++) {
                    if (dp[i - 1][j]) {
                        for (int k = 0; k <= coin[i][1]; k++) {
                            int cost = j + coin[i][0] * k;
                            dp[i][cost] = true;
                        }
                    }
                }
                if (dp[i][goal]) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}
