import java.io.*;
import java.util.*;

public class BOJ1941 {

    static final int N = 5;
    static int ans = 0;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[N][N];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        visit = new boolean[N * N];

        combi(0, 0);
        System.out.println(ans);
    }

    public static void combi(int d, int start) {
        if (d >= 7) {
            if (check()) ans++;
            return;
        }
        for (int i = start; i < N * N; i++) {
            visit[i] = true;
            combi(d + 1, i + 1);
            visit[i] = false;
        }
    }

    public static boolean check() {
        boolean[][] connect = new boolean[N][N];
        int Y = 0;
        for (int i = 0; i < N * N; i++) {
            if (visit[i]) {
                int y = i / N;
                int x = i % N;
                connect[y][x] = true;
                if (map[y][x] == 'Y') Y++;
                if (Y > 3) return false;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (connect[i][j]) {
                    Queue<int[]> queue = new LinkedList<>();
                    int cnt = 1;
                    queue.offer(new int[]{i, j});
                    connect[i][j] = false;
                    while (!queue.isEmpty()) {
                        int[] now = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = now[0] + dr[d];
                            int nc = now[1] + dc[d];
                            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                            if (!connect[nr][nc]) continue;
                            connect[nr][nc] = false;
                            cnt++;
                            queue.offer(new int[]{nr, nc});
                        }
                    }
                    return cnt >= 7;
                }
            }
        }
        return false;
    }
}
