import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724 {

    static int N, M, ans;
    static char[][] map;
    static boolean[][] visit, safe;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visit = new boolean[N][M];
        safe = new boolean[N][M];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j]) {
                    dfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }

    static void dfs(int r, int c) {
        if (visit[r][c]) { // 방문했던 곳 또 왔을 때
            if (!safe[r][c]) ans++;
            safe[r][c] = true;
            return;
        }
        visit[r][c] = true;
        // 앞으로 가기
        if (map[r][c] == 'U') dfs(r - 1, c);
        if (map[r][c] == 'D') dfs(r + 1, c);
        if (map[r][c] == 'L') dfs(r, c - 1);
        if (map[r][c] == 'R') dfs(r, c + 1);
        safe[r][c] = true;
    }
}
