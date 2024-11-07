import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

    static int N, M, ans;
    static boolean[][] visit;
    static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'x')
                    visit[i][j] = true;
            }
        }
        ans = 0;
        for (int i = 0; i < N; i++) {
            if (!visit[i][0]) dfs(i, 0);
        }
        System.out.println(ans);
    }

    public static boolean dfs(int y, int x) {
        visit[y][x] = true;
        if (x >= M - 1) {
            ans++;
            return true;
        }
        boolean find = false; // 설치 가능 여부
        for (int i = 0; i < 3; i++) {
            int ny = y + dy[i];
            int nx = x + 1;
            if (ny < 0 || ny >= N) continue;
            if (visit[ny][nx]) continue;
            find = dfs(ny, nx);
            if (find) break;
        }
        return find;
    }
}
