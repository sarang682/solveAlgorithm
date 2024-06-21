import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {

    static int N, ans = 0;
    static int[][] map, dp;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(j, i));
            }
        }
        System.out.println(ans);
    }

    static int dfs(int x, int y) {
        if (dp[y][x] > 0)
            return dp[y][x];
        dp[y][x] = 1;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            // 다음으로 가지 못함
            if (nx >= N || ny >= N || nx < 0 || ny < 0)
                continue;
            if (map[y][x] >= map[ny][nx])
                continue;
            // 다음으로 갈 수 있음
            dp[y][x] = Math.max(dp[y][x], dfs(nx, ny) + 1);
        }
        return dp[y][x];
    }
}
