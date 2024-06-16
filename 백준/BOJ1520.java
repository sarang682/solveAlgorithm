import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1520 {

    static int M, N;
    static int[][] map, dp;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]); // 세로
        N = Integer.parseInt(input[1]); // 가로
        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                dp[i][j] = -1;
            }
        }
        // DFS
        System.out.println(dfs(0, 0));
    }

    static public int dfs(int y, int x) {
        if (y == M - 1 && x == N - 1) {
            return 1;
        }
        if (dp[y][x] != -1)
            return dp[y][x];
        dp[y][x] = 0; // 방문 표시
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= N || ny >= M || nx < 0 || ny < 0)
                continue;
            if (map[y][x] > map[ny][nx])
                dp[y][x] += dfs(ny, nx);
        }
        return dp[y][x];
    }
}
