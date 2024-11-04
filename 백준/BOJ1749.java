import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1749 {

    static int N, M;
    static int[][] nums, sum;
    static final int MIN = -999_999_999;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N + 1][M + 1];
        sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + nums[i][j];
            }
        }
        int ans = MIN;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                ans = Math.max(ans, cal(i, j));
            }
        }
        System.out.println(ans);
    }

    public static int cal(int r, int c) {
        int max = nums[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                max = Math.max(max, sum[r][c] - sum[i][c] - sum[r][j] + sum[i][j]);
            }
        }
        return max;
    }
}
