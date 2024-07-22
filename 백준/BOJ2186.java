import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2186 {
    static int N, M, K;
    static char[][] map;
    static char[] word;
    static int[][][] dp;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        word = br.readLine().toCharArray();
        dp = new int[word.length][N][M];
        for (int i = 0; i < word.length; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == word[0])
                    dfs(i, j, 0);
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == word[0])
                    ans += dp[0][i][j];
            }
        }
        System.out.println(ans);
    }

    static public int dfs(int y, int x, int idx) {
        if (dp[idx][y][x] != -1)
            return dp[idx][y][x];
        if (idx == word.length - 1)
            return dp[idx][y][x] = 1;
        dp[idx][y][x] = 0;
        for (int d = 0; d < 4; d++) {
            for (int i = 1; i <= K; i++) {
                int nx = x + dx[d] * i;
                int ny = y + dy[d] * i;
                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (map[ny][nx] == word[idx + 1])
                    dp[idx][y][x] += dfs(ny, nx, idx + 1);
            }
        }
        return dp[idx][y][x];
    }
}
